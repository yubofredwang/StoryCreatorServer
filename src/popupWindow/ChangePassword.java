package popupWindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;


import library.FontLibrary;
import library.ImageLibrary;
import tip.storycreator.client.ClientListener;
import tip.storycreator.client.Constants;
import tip.storycreator.client.DefaultButton;
import tip.storycreator.client.LoginWindow;
import tip.storycreator.server.Signal.ChangePassWordSignal;

public class ChangePassword extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8230609776900538599L;
	
	final Font font = FontLibrary.getFont(Constants.pathname + "fonts/System San Francisco Display Regular.ttf", Font.PLAIN, 18);

	JTextField username = new JTextField(8);
	JTextField oldPassword = new JTextField(8);
	JTextField newPassword = new JTextField(8);
	JTextField confirmNewPassword = new JTextField(8);
	
	private ClientListener mClientListener; 
	private LoginWindow mLoginWindow;
	
	public void setClientListener(ClientListener inClientListener)
	{
		mClientListener = inClientListener;
	}
	
	public void setLoginWindow(LoginWindow inLoginWindow)
	{
		mLoginWindow = inLoginWindow;
	}
	
	public ChangePassword () {
		
		setModal(true);
		UIManager.put("Label.font", font);
		UIManager.put("TextField.font", font);
		UIManager.put("MenuBar.font", font);
		UIManager.put("MenuItem.font", font);
		setTitle("Forget Password");
		
		setMinimumSize(new Dimension(450,520));
		//setMaximumSize(new Dimension(500,400));
		JPanel overallPanel = new JPanel();
		overallPanel.setLayout(new GridLayout(2, 1));
		JPanel firstPanel = new JPanel();
		JPanel secondPanel = new JPanel();
		JLabel usernameLabel = new JLabel("Username");
		JLabel oldPasswordLabel = new JLabel("Old Password");
		JLabel newPasswordLabel = new JLabel("New Password");
		JLabel confirmPasswordLabel = new JLabel("Confirm New Password");
		usernameLabel.setForeground(Color.WHITE);
		oldPasswordLabel.setForeground(Color.WHITE);
		newPasswordLabel.setForeground(Color.WHITE);
		confirmPasswordLabel.setForeground(Color.WHITE);
		
		username.setForeground(new Color(35, 152, 208));
		oldPassword.setForeground(new Color(35, 152, 208));
		newPassword.setForeground(new Color(35, 152, 208));
		confirmNewPassword.setForeground(new Color(35, 152, 208));
		
		firstPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(40,0,0,0);
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 30;
		c.ipady = 10;
		firstPanel.add(usernameLabel, c);
		c.gridx = 1;
		c.gridy = 0;
		firstPanel.add(username, c);
		c.insets = new Insets(0,0,0,0);
		JLabel jl = new JLabel("");
		c.gridx = 0;
		c.gridy = 1;
		firstPanel.add(jl, c);
		c.gridx = 1;
		c.gridy = 1;
		firstPanel.add(jl, c);
		c.gridx = 0;
		c.gridy = 2;
		firstPanel.add(oldPasswordLabel, c);
		c.gridx = 1;
		c.gridy = 2;
		firstPanel.add(oldPassword, c);
		JLabel jl1 = new JLabel("");
		c.gridx = 0;
		c.gridy = 3;
		firstPanel.add(jl1, c);
		c.gridx = 1;
		c.gridy = 3;
		firstPanel.add(jl1, c);
		c.gridx = 0;
		c.gridy = 4;
		firstPanel.add(newPasswordLabel, c);
		c.gridx = 1;
		c.gridy = 4;
		firstPanel.add(newPassword, c);
		c.gridx = 0;
		c.gridy = 5;
		firstPanel.add(newPasswordLabel, c);
		c.gridx = 1;
		c.gridy = 5;
		firstPanel.add(newPassword, c);
		JLabel jl2 = new JLabel("");
		c.gridx = 0;
		c.gridy = 6;
		firstPanel.add(jl2, c);
		c.gridx = 1;
		c.gridy = 6;
		firstPanel.add(jl2, c);
		c.gridx = 0;
		c.gridy = 7;
		firstPanel.add(confirmPasswordLabel, c);
		c.gridx = 1;
		c.gridy = 7;
		firstPanel.add(confirmNewPassword, c);
		JButton confirm = new DefaultButton("Confirm",
				  ImageLibrary.getImage("resources/create_file/blue_button_light.png"),
				  ImageLibrary.getImage("resources/create_file/blue_button_pressed.png"));
		JButton cancel = new DefaultButton("Cancel",
				  ImageLibrary.getImage("resources/create_file/blue_button_light.png"),
				  ImageLibrary.getImage("resources/create_file/blue_button_pressed.png"));
		
		confirm.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(mLoginWindow.isConnected())
				{
					if(confirmNewPassword.getText().equals(newPassword.getText()))
					{
						mClientListener.SendObjectToServer(new ChangePassWordSignal(username.getText(),oldPassword.getText(),newPassword.getText()));
						
					}
					else{
						new OneSentenceTwoButton("<html>" + "The new password and confirm new password does not match"
								+ "</html>", mLoginWindow.getParent(), "OKEY", "DOKEY", "Error");
						
					}
					
				}
				else{
					new OneSentenceTwoButton("<html>" + "Connection Error - Go to Setting ->"
							+ " Connection" + "</html>", mLoginWindow.getParent(), "OKEY", "DOKEY", "Error");
					
				}
				
			}
		});
		getRootPane().setDefaultButton(confirm);
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				close();
			}
		});
		secondPanel.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();
		
		d.fill = GridBagConstraints.HORIZONTAL;
		d.gridx = 0;
		d.gridy = 0;
		d.ipadx = 30;
		d.ipady = 10;
		secondPanel.add(confirm, d);
		d.gridx = 1;
		d.gridy = 0;
		secondPanel.add(cancel, d);
		overallPanel.add(firstPanel);
		secondPanel.setPreferredSize(new Dimension(500, 50));
		pack();
		overallPanel.add(secondPanel);
		firstPanel.setBackground(new Color(35, 152, 208)); 
		secondPanel.setBackground(new Color(35, 152, 208));
		add(overallPanel);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				LoginWindow.isPressed = false;
				dispose();
			}
		});
	}
	
	public void close() {
		LoginWindow.isPressed = false;
		dispose();
	}

}
