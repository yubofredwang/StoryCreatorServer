package tip.storycreator.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import tip.storycreator.server.Signal.AddUserSignal;
import tip.storycreator.server.Signal.ChangePassWordSignal;
import tip.storycreator.server.Signal.ChangePasswordSuccessSignal;
import tip.storycreator.server.Signal.LoginSignal;
import tip.storycreator.server.Signal.LoginSuccessSignal;
import tip.storycreator.server.Signal.SaveRemoteSuccessSignal;
import tip.storycreator.server.Signal.SignUpSuccessSignal;
import tip.storycreator.server.Signal.UserSignal;

public class ClientListener extends Thread{
	
	private Socket mSocket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private StoryCreatorClientWindow mStoryCreatorClientWindow;
	
	
	public void setStoryCreatorClientWindow(StoryCreatorClientWindow scc)
	{
		mStoryCreatorClientWindow = scc;
	}
	
	
	public ClientListener(int port, String ipaddress)
	{
//		InetAddress[] x = null;
//		try {
//			DNSNameService dss = new DNSNameService();
//			x = dss.lookupAllHostAddr(ipaddress);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		for(InetAddress d: x)
//		{
//			System.out.println(d.getHostAddress());
//		}
//		
//		System.out.println(ipaddress);
//		try {
//
//				 InetAddress address = InetAddress.getByName("yubowang.imwork.net");
//			      System.out.println("IP address: " + address.getHostAddress());
//			      InetAddress[] inetAddressArray = InetAddress.getAllByName("yubowang.imwork.net");
//					for (int i = 0; i < inetAddressArray.length; i++) {
//						System.out.println("yubowang.imwork.net #" + (i + 1) + inetAddressArray[i]);
//					}
//		     
//		    } catch ( UnknownHostException e ) {
//		      System.out.println("Could not find IP address for: ");
//		    }
		  
		try {
			mSocket = new Socket(ipaddress,port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean SocketReady = initializeVariables();
		if(SocketReady)
		{
			start();
		}
	}
	
	public void Login(String username, String password) {
		LoginSignal ls = new LoginSignal(username,password);
		SendObjectToServer(ls);
		
	}
	
	public void signUp (String username, String password) {
		AddUserSignal as = new AddUserSignal(username,password);
		SendObjectToServer(as);
	}

	public void changePassword (String username, String oldpassword, String newpassword) {
		ChangePassWordSignal cps = new ChangePassWordSignal(username,oldpassword,newpassword);
		SendObjectToServer(cps);
	}
	
	
	private boolean initializeVariables() {
		try {
			oos = new ObjectOutputStream(mSocket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(mSocket.getInputStream());
		} catch (IOException ioe) {
			System.out.println("the input output is not ready");
			return false;
		}
		return true;
	}
	
	@Override
	public void run()
	{
		try {
			while (true) {
				Object obj = ois.readObject();
				System.out.println("obj received");
				if(obj instanceof UserSignal)
				{
					//get your fucking game obejct here man
					UserSignal us = (UserSignal)obj;
					mStoryCreatorClientWindow.getUserSignal(us);
				}
				else if(obj instanceof LoginSuccessSignal)
				{
					mStoryCreatorClientWindow.getLoginSuccessSignal((LoginSuccessSignal)obj);
				}
				else if(obj instanceof SignUpSuccessSignal)
				{
					mStoryCreatorClientWindow.getSignUpSignal((SignUpSuccessSignal)obj);
				}
				else if(obj instanceof ChangePasswordSuccessSignal)
				{
					mStoryCreatorClientWindow.getChangepasswordsccSignal((ChangePasswordSuccessSignal)obj);
				}
				else if(obj instanceof SaveRemoteSuccessSignal)
				{
					mStoryCreatorClientWindow.SaveFileSuccess();
				}
			}
		} catch (IOException | ClassNotFoundException ioe) {
			System.out.println("IOE in Client.run(): " + ioe.getMessage());
			JOptionPane.showMessageDialog(mStoryCreatorClientWindow, "Disconnect With The Server");
			mStoryCreatorClientWindow.BackToLoginWindow();
		}
		
	}
	
	public void SendObjectToServer(Object obj)
	{
		try {
			oos.writeObject(obj);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


}
