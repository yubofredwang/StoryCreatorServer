package tip.storycreator.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ServerWithoutGUI extends Thread{

	private ServerSocket mServerSocket;
	private Vector<ServerClientCommunicatorWithoutGUI> sccVector;
	private ServerManagerWithoutGUI mServerManagerWithoutGUI;
	
	public ServerWithoutGUI(){
		System.out.println("Starting Server");
		try {
			mServerSocket = new ServerSocket(16789);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sccVector = new Vector<ServerClientCommunicatorWithoutGUI>();
		mServerManagerWithoutGUI = new ServerManagerWithoutGUI(null);
	}
	
		@Override
		public void run()
		{
			try {
				while(true)
				{
					Socket s = mServerSocket.accept();
					System.out.println(("IP address: " + s.getInetAddress() + " has connected to server" ));	
						// this line can throw an IOException
						// if it does, we won't start the thread
					ServerClientCommunicatorWithoutGUI scc = new ServerClientCommunicatorWithoutGUI(s, this,mServerManagerWithoutGUI);
					scc.start();
					sccVector.add(scc);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally
			{
				try {
					for(ServerClientCommunicatorWithoutGUI scc: sccVector)
					{
						scc.close();
					}
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

	
	public static void main(String[] args) {
		ServerWithoutGUI swg = new ServerWithoutGUI();
		swg.start();
	}
	
	
}
