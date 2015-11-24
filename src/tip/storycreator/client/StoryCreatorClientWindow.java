package tip.storycreator.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import popupWindow.OneSentenceTwoButton;
import tip.storycreator.client.MainArea.MainWindow;
import tip.storycreator.game.GameProject;
import tip.storycreator.server.ServerWithoutGUI;
import tip.storycreator.server.User;
import tip.storycreator.server.Signal.ChangePasswordSuccessSignal;
import tip.storycreator.server.Signal.DropUserSignal;
import tip.storycreator.server.Signal.LoginSignal;
import tip.storycreator.server.Signal.LoginSuccessSignal;
import tip.storycreator.server.Signal.SignUpSuccessSignal;
import tip.storycreator.server.Signal.UserSignal;


public class StoryCreatorClientWindow extends JFrame{

	private static final long serialVersionUID = 5029643760680446864L;
	
	private final static Dimension minSize = new Dimension(960,640);
	
	private LoginWindow mLoginWindow;
	private CreateFileWindow mCreateFileWindow;
	private MainWindow mMainWindow;
	private LoadingWindow mLoadingWindow;
	private JPanel OverallPanel;
	private ClientListener cls;
	/**
	 * Constructor 
	 */

	
	class WindowServerActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			cls = new ClientListener(6789,"yubowang.imwork.net");
			cls.setStoryCreatorClientWindow(StoryCreatorClientWindow.this);
			mLoginWindow.setClientListener(cls);
			
		}
		
	}
	
	class LoginLoadingListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			//send a login signal
//			 InetAddress ipaddress = null;
//			try {
//				ipaddress = InetAddress.getByName("yubowang.imwork.net");
//				
//			} catch (UnknownHostException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			System.out.println(ipaddress);
			cls = new ClientListener(16789,"localhost");
			System.out.println("fuck");
			LoginSignal ls = new LoginSignal(mLoginWindow.getLoginName(), mLoginWindow.getPassword());
			cls.SendObjectToServer(ls);
			cls.setStoryCreatorClientWindow(StoryCreatorClientWindow.this);
			mLoginWindow.setClientListener(cls);
			OverallPanel.removeAll();
			
			//System.out.println("second");
//			OverallPanel.setLayout(new BorderLayout());
			OverallPanel.add(mLoadingWindow);
			OverallPanel.revalidate();
			OverallPanel.repaint();
			
			
		}
		
	}
	
	class CreateFileConfirm implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
				OverallPanel.removeAll();
				GameProject mGameProject = mCreateFileWindow.getGameProject();
				User mUser = mCreateFileWindow.getUser();
				mMainWindow = new MainWindow(mGameProject, mUser, new BackToCreateFile());
				mMainWindow.setClientListener(cls);
				OverallPanel.revalidate();
				OverallPanel.repaint();
				StoryCreatorClientWindow.this.setVisible(false);
		}
		
	}
	
	class CreateFileLogOut implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//drops the user 
			if(cls != null)
			{
				cls.SendObjectToServer(new DropUserSignal());
			}
			// TODO Auto-generated method stub
			OverallPanel.removeAll();
			OverallPanel.add(mLoginWindow);
			OverallPanel.revalidate();
			OverallPanel.repaint();
		}
		
	}
	
	
	public StoryCreatorClientWindow() {
		
		OverallPanel = new JPanel(new BorderLayout());
	
		

		setTitle("StoryCreator");
		setSize(minSize);
		setMinimumSize(minSize);
		setMaximumSize(minSize);
//		add(new ClientPanel());
		mLoadingWindow= new LoadingWindow();
		mLoginWindow = new LoginWindow(new LoginLoadingListener(), new LoginAsGuestListener(), new WindowServerActionListener());
		try {
			mCreateFileWindow = new CreateFileWindow(new CreateFileConfirm(), new CreateFileLogOut());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		OverallPanel.add(mLoginWindow);
		this.add(OverallPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	
	/*
	 * the actionlistener that swithces between
	 */
	
	/**
	 * Main function to create StoryCreatorClient Window
	 * @param args
	 */
//	public static void main(String[] args) {
////		SwingUtilities.invokeLater(new Runnable() {
////		    public void run() {
////		    	new StoryCreatorClientWindow();
////		    }
////		});
//		new StoryCreatorClientWindow();
//	}
	
	public void getLoginSuccessSignal(LoginSuccessSignal lss)
	{
		
		
		OverallPanel.removeAll();
		if(lss.loginsuccess)
		{
			OverallPanel.add(mCreateFileWindow);
		}
		else
		{
			OverallPanel.add(mLoginWindow);
		}
		OverallPanel.revalidate();
		OverallPanel.repaint();
	}
	
	
	public void getSignUpSignal(SignUpSuccessSignal sss)
	{
		
		if(sss.SignUpSuccess == false)
		{
			new OneSentenceTwoButton(
					"<html>" + "Sign Up Failed, User Alreay Exists" + "</html>"
			, mLoginWindow.getParent(), "OKIE", "DOKIE", "Error");
			
		}
		else
		{
			new OneSentenceTwoButton(
					"<html>" + "Sign Up Successfully, You can Login Right Now" + "</html>"
			, mLoginWindow.getParent(), "OKIE", "DOKIE", "Successful" );
			mLoginWindow.closeSignUpWindow();
		}
		
		
		
	}
	
	
	class LoginAsGuestListener extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e) {
			//send a login signal
			
			OverallPanel.removeAll();
			//System.out.println("second");
//			OverallPanel.setLayout(new BorderLayout());
			OverallPanel.add(mCreateFileWindow);
			OverallPanel.revalidate();
			OverallPanel.repaint();			
		}

		
		
	}
	//protected autoScaling


	public void getUserSignal(UserSignal us) {
		if(us.getUser() == null)
		{
			BackToLoginWindow();
			new OneSentenceTwoButton(
					"<html>" + "Login Failed" + "</html>"
			, mLoginWindow.getParent(), "OKIE", "DOKIE", "Error");
		}
		else{
			mCreateFileWindow.setUser(us.getUser());
//			mCreateFileWindow.setClientListener(cls);
			OverallPanel.removeAll();
			//System.out.println("second");
//			OverallPanel.setLayout(new BorderLayout());
			OverallPanel.add(mCreateFileWindow);
			OverallPanel.revalidate();
			OverallPanel.repaint();	
		}
	
		//should move to the next state
		//mMainWindow also should keep the user;
		
	}


	public void BackToLoginWindow() {
		OverallPanel.removeAll();
		//System.out.println("second");
//		OverallPanel.setLayout(new BorderLayout());
		
		OverallPanel.add(mLoginWindow);
		OverallPanel.revalidate();
		OverallPanel.repaint();			
	}


	public void getChangepasswordsccSignal(ChangePasswordSuccessSignal cps) {
		
		if(cps.changepasswordsuccess == false)
		{
			new OneSentenceTwoButton(
					"<html>" + "Change Password Failed, Wrong User Name or Wrong Password" + "</html>"
			, mLoginWindow.getParent(), "OKIE", "DOKIE", "Error");
			
		}
		else
		{
			new OneSentenceTwoButton(
					"<html>" + "Change Password Successfully, You can Login Right Now" + "</html>"
			, mLoginWindow.getParent(), "OKIE", "DOKIE", "Successful");
		}
	}
	
	public void SaveFileSuccess(){
		mMainWindow.SaveSuccess();
	}
	
	public class BackToCreateFile implements ActionListener
	{
		User u;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				mCreateFileWindow = new CreateFileWindow(new CreateFileConfirm() , new CreateFileLogOut());
				mCreateFileWindow.setUser(u);
				OverallPanel.add(mCreateFileWindow);
				OverallPanel.revalidate();
				OverallPanel.repaint();
				StoryCreatorClientWindow.this.setVisible(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		public void setUser(User u)
		{
			this.u = u;
		}
	}
	
	public static void main(String[] args) {
		new StoryCreatorClientWindow();
	}
	
	
	
}
