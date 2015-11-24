package tip.storycreator.server;

import java.util.HashMap;

import tip.storycreator.game.GameProject;
import tip.storycreator.server.Signal.ChangePasswordSuccessSignal;
import tip.storycreator.server.Signal.SaveRemoteSuccessSignal;
import tip.storycreator.server.Signal.SignUpSuccessSignal;
import tip.storycreator.server.Signal.UserSignal;

public class ServerManager {

	HashMap<ServerClientCommunicator, User> ClientUserMap;
	ServerListener mServerListener;
	MySQLDriver mysql;
	String s = "Jdbc:mysql://localhost:3306/storycreator?user=root&password=2330842aB@";
	public ServerManager(ServerListener sl)
	{
		mServerListener = sl;
		ClientUserMap = new HashMap<ServerClientCommunicator,User>();
		mysql = new MySQLDriver();
	//	mysql.setLoginString(s);
		mysql.connect();
	}
	
	public ServerManager(ServerWithoutGUI serverWithoutGUI) {
		
	}

	public void addUser(ServerClientCommunicator scs, String name,String password)
	{
		User SendBackUser = mysql.createNewUser(name, password);
		if(SendBackUser == null)
		{
			scs.SendObject(new SignUpSuccessSignal(false));
		}
		else
		{
			scs.SendObject(new SignUpSuccessSignal(true));
			InfoServerGUI.addMessage("User: "+ name + "is added to the database");
		}
//		UserDatabase.
		
		
	}
	
	public void changePassword(ServerClientCommunicator scs, 
			String username, String oldpassword,String newpassword)
	{
		if(mysql.changePassword(username, oldpassword, newpassword)){
			scs.SendObject(new ChangePasswordSuccessSignal(true));
		}
		else{
			scs.SendObject(new ChangePasswordSuccessSignal(false));
		}
	
	
	}
	
	
	
	
		

	public void UserLogin(String name, String password, ServerClientCommunicator scc) {
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

	public void dropUser(ServerClientCommunicator serverClientCommunicator) {
		// TODO Auto-generated method stub
		InfoServerGUI.addMessage("User: "+ ClientUserMap.get(serverClientCommunicator) + "is dropped");
		ClientUserMap.remove(serverClientCommunicator);
		
	}

	public void saveGameProject(ServerClientCommunicator serverClientCommunicator, GameProject obj) {
		
		User user = ClientUserMap.get(serverClientCommunicator);
		user.addGameProject(obj);
		mysql.saveToDatabase(user);
		InfoServerGUI.addMessage(obj.getName() + " has been successfully added into User " + user.getName());
		serverClientCommunicator.SendObject(new SaveRemoteSuccessSignal());
	}
}
