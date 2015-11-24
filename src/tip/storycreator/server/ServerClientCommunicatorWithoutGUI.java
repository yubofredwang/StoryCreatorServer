package tip.storycreator.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import tip.storycreator.game.GameProject;
import tip.storycreator.server.Signal.AddUserSignal;
import tip.storycreator.server.Signal.ChangePassWordSignal;
import tip.storycreator.server.Signal.DropUserSignal;
import tip.storycreator.server.Signal.LoginSignal;

public class ServerClientCommunicatorWithoutGUI extends Thread {
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private ServerListener mServerListener;
	private ServerManagerWithoutGUI mServerManager;
	private ServerWithoutGUI mServerWithoutGUI;
	//need a SQL 
	
	public ServerClientCommunicatorWithoutGUI(Socket s,ServerWithoutGUI serverWithoutGUI,ServerManagerWithoutGUI sm )
	{
		socket = s;
		mServerManager = sm;
		mServerWithoutGUI = serverWithoutGUI;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	
	



	//use to send the whole Game Object after the request
//	public void sendUserSignal(UserSignal us, User user)
//	{
//		
//		try {
//			oos.writeObject(us);
//			oos.flush();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	
	public void SendObject(Object s)
	{
		try {
			oos.writeObject(s);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
		

	

	@Override 
	public void run()
	{
		try{
			while(true)
			{
				Object obj = ois.readObject();
				if(obj instanceof LoginSignal)
				{
					LoginSignal ls = (LoginSignal)obj;
					System.out.println("User: "+  ls.getUser() + " has send its password " + ls.getPassword());
//					User user = UserDatabase.login()
//					ServerManager.Login();
//					LoginSuccessSignal lss = new LoginSuccessSignal();
//					SendObject(lss);
					mServerManager.UserLogin(ls.getUser(),ls.getPassword(), this);
				}
				if(obj instanceof AddUserSignal)
				{
					AddUserSignal as = (AddUserSignal)obj;
					System.out.println("User: "+  as.getUser() + " trying to create a new user");
					mServerManager.addUser(this, as.getUser(),as.getPassword());
					//if successfully add the,
					//Server will send it back
					
				}
				if(obj instanceof ChangePassWordSignal){
					 ChangePassWordSignal ls = (ChangePassWordSignal)obj;
					 mServerManager.changePassword(this, ls.getUser(), ls.getoldPassword(), ls.getnewPassword());
					 System.out.println("User " + ls.getUser() + " with " + ls.getoldPassword() + " change password to " + ls.getnewPassword()) ;
				}
				if(obj instanceof DropUserSignal)
				{
					mServerManager.dropUser(this);
					
				}
				if(obj instanceof GameProject)
				{
					
					mServerManager.saveGameProject(this,(GameProject)obj);
					
				}
//				if(obj instanceof LoginSignal)
//				{
//					if(mServerManager.CheckLogin())
//					{
//						oos.writeObject(new LoginSuccessSignal());
//					}
//				}
			}
		}catch(ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException ioe) {
//			mServerListener.removeServerClientCommunicatorWithoutGUI(this);
//			System.out.println(socket.getInetAddress() + ":" + socket.getPort() + " - " + Constants.clientDisconnected);
			// this means that the socket is closed since no more lines are being received
//			msqld.stop();
			try {
				socket.close();
			} catch (IOException ioe1) {
				ioe1.printStackTrace();
			}
		}
	}





	public void close() {
		try {
			socket.close();
			System.out.println("socket closed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
