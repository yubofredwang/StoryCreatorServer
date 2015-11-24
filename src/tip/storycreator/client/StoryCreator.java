package tip.storycreator.client;
import tip.storycreator.server.ServerWithoutGUI;
import tip.storycreator.server.StoryCreatorServer;

public class StoryCreator {
	
	StoryCreator() {
		new ServerWithoutGUI();
		new StoryCreatorClientWindow();
	}
	
	
//	public static void main (String [] args) {
//		new StoryCreator();
//	}
}
