package tip.storycreator.server;

public class Constants {



		public static final int lowPort = 0;
		public static final int highPort = 65535;
		public static final int defaultPort = 6789;
		public static final String defaultHostname = "localhost";
		public static final String portDescriptionString = "<html>Enter the port number on which<br />you would like the server to listen.</html>";
		public static final String portLabelString = "Port";
		public static final String submitPortString = "Start Listening";
		public static final String portGUITitleString = "Story Creator Server - Port";
		public static final int portGUIwidth = 300;
		public static final int portGUIheight = 300;
		public static final String portErrorString = "<html><font color=\"red\">Please enter a valid port<br />between " + lowPort + " and " + highPort + "</font></html>";
		public static final String portAlreadyInUseString = "<html><font color=\"red\">Port already in use.  Select another port<br />between " + lowPort + " and " + highPort + "</font></html>";
		
		public static final String initialStoryCreatorTextAreaString = "Waiting for connections on port ";
		public static final String StoryCreatorGUITitleString = "Story Creator Server";
		public static final int StoryCreatorGUIwidth = 500;
		public static final int StoryCreatorGUIheight = 500;
		
		public static final String startClientConnectedString = "Client with IP address ";
		public static final String endClientConnectedString = " connected.";
		public static final String clientDisconnected = "Story Creator Client disconnected.";
		
		public static final String selectStoryCreatorButtonString = "Select StoryCreator";
		public static final String defaultResourcesDirectory = "resources/";

		public static final String unrecognizedLine = "Unrecognized line in file: ";
		public static final String StoryCreatorFileDelimeter = "|";
		public static final String StoryCreatorLoadedMessage = "Story Creator finished loading!";
	

}
