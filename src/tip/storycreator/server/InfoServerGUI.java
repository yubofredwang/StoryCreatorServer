package tip.storycreator.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InfoServerGUI extends JFrame{


	public static final long serialVersionUID = 1;
	private static JTextArea textArea;
	private JScrollPane textAreaScrollPane;
	private static ServerListener mServerListener;
	public InfoServerGUI() {
		super(Constants.StoryCreatorGUITitleString);
		initializeVariables();
		createGUI();
		addActionAdapters();
		setVisible(true);
	}
	
	private void initializeVariables() {
		textArea = new JTextArea();
		textArea.setForeground(Color.GREEN);
		textArea.setBackground(Color.BLACK);
		 Font font = new Font("Courier", Font.BOLD,16);
		textArea.setFont(font);
		textArea.setEditable(false);
		textAreaScrollPane = new JScrollPane(textArea);
	}
	
	private void createGUI() {
		setSize(Constants.StoryCreatorGUIwidth, Constants.StoryCreatorGUIheight);
		add(textAreaScrollPane, BorderLayout.CENTER);
	}
	
	private void addActionAdapters() {
		addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent we) {
				  System.out.println("test");
				  CloseOpertion();
				  System.out.println("close done");
				  System.exit(0);
			  }
		});
		
	}
	public static void setServerListener(ServerListener sl)
	{
		mServerListener = sl;
	}
	
	public synchronized static void CloseOpertion()
	{
		
		mServerListener.close();
	}
	
	public static void addMessage(String msg) {
		if (textArea.getText() != null && textArea.getText().trim().length() > 0) {
			textArea.append("\n" + msg);
		}
		else {
			textArea.setText(msg);
		}
	}
}
