package tip.storycreator.server;


import java.net.ServerSocket;



public class StoryCreatorServer {

	private ServerSocket ss;
	private static ServerListener serverListener;
	
	
	public StoryCreatorServer() {
		ServerGUI pf = new ServerGUI();
		ss = pf.getServerSocket();
		new InfoServerGUI();
		InfoServerGUI.addMessage("The Story Creator GUI");
		listenForConnections();
	}
	
	private void listenForConnections() {
		serverListener = new ServerListener(ss);
		InfoServerGUI.setServerListener(serverListener);
		serverListener.start();
	}
	
	public static void sendUserArchiveFile() {
		
		//Waiting to be implemented
		//when the user connect to server, should send all the information the user have on their game right now
		
		

	}
		
//	public static void main(String [] args) {
//		new StoryCreatorServer();
//	}
		
	

}
