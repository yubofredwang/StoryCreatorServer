package tip.storycreator.server;

import java.util.HashMap;

import tip.storycreator.game.GameProject;
import tip.storycreator.server.Signal.ChangePasswordSuccessSignal;
import tip.storycreator.server.Signal.SaveRemoteSuccessSignal;
import tip.storycreator.server.Signal.SignUpSuccessSignal;
import tip.storycreator.server.Signal.UserSignal;

public class ServerManagerWithoutGUI {

	HashMap<ServerClientCommunicatorWithoutGUI, User> ClientUserMap;
	ServerListener mServerListener;
	MySQLDriver mysql;
	String s = "Jdbc:mysql://localhost:3306/storycreator?user=root&password=2330842aB@";
	public ServerManagerWithoutGUI(ServerListener sl)
	{
		mServerListener = sl;
		ClientUserMap = new HashMap<ServerClientCommunicatorWithoutGUI,User>();
		mysql = new MySQLDriver();
	//	mysql.setLoginString(s);
		mysql.connect();
	}
	
	



	public void addUser(ServerClientCommunicatorWithoutGUI scs, String name,String password)
	{
		User SendBackUser = mysql.createNewUser(name, password);
		if(SendBackUser == null)
		{
			scs.SendObject(new SignUpSuccessSignal(false));
		}
		else
		{
			scs.SendObject(new SignUpSuccessSignal(true));
			System.out.println("User: "+ name + "is added to the database");
		}
//		UserDatabase.
		
		
	}
	
	public void changePassword(ServerClientCommunicatorWithoutGUI scs, 
			String username, String oldpassword,String newpassword)
	{
		if(mysql.changePassword(username, oldpassword, newpassword)){
			scs.SendObject(new ChangePasswordSuccessSignal(true));
		}
		else{
			scs.SendObject(new ChangePasswordSuccessSignal(false));
		}
	
	
	}
	
	
	
	
		

	public void UserLogin(String name, String password, ServerClientCommunicatorWithoutGUI scc) {
		User s = mysql.getUserObject(name, password);
		if(s!=null)
		{
			ClientUserMap.put(scc, s);
		}
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserSignal us = new UserSignal(s);
		scc.SendObject(us);
	}

	public void dropUser(ServerClientCommunicatorWithoutGUI ServerClientCommunicatorWithoutGUI) {
		// TODO Auto-generated method stub
		System.out.println("User: "+ ClientUserMap.get(ServerClientCommunicatorWithoutGUI) + "is dropped");
		ClientUserMap.remove(ServerClientCommunicatorWithoutGUI);
		
	}

	public void saveGameProject(ServerClientCommunicatorWithoutGUI ServerClientCommunicatorWithoutGUI, GameProject obj) {
		
		User user = ClientUserMap.get(ServerClientCommunicatorWithoutGUI);
		user.addGameProject(obj);
		mysql.saveToDatabase(user);
		System.out.println(obj.getName() + " has been successfully added into User " + user.getName());
		ServerClientCommunicatorWithoutGUI.SendObject(new SaveRemoteSuccessSignal());
	}
}
