package tip.storycreator.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;



public class ServerListener extends Thread {
	
	private ServerSocket mServerSocket;
	private Vector<ServerClientCommunicator> sccVector;
	private ServerManager mServerManager;
	
	public ServerListener(ServerSocket ss) {
		// TODO Auto-generated constructor stub
		mServerSocket = ss;
		sccVector = new Vector<ServerClientCommunicator>();
		mServerManager = new ServerManager(this);
	}
	
	
	@Override
	public void run()
	{
		try {
			while(true)
			{
				Socket s = mServerSocket.accept();
				InfoServerGUI.addMessage("IP address: " + s.getInetAddress() + " has connected to server" );	
					// this line can throw an IOException
					// if it does, we won't start the thread
				ServerClientCommunicator scc = new ServerClientCommunicator(s, this,mServerManager);
				scc.start();
				sccVector.add(scc);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				if(mServerSocket!=null)
				{
					mServerSocket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//
	public void removeServerClientCommunicator(ServerClientCommunicator serverClientCommunicator) {
		
		sccVector.remove(serverClientCommunicator);
	}
	
	public void close()
	{
		if(sccVector.isEmpty())
		{
			return;
		}
		for(ServerClientCommunicator scc: sccVector)
		{
			scc.close();
		}
		try {
			mServerSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
