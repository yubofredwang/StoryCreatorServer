package tip.storycreator.server;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import library.ImageLibrary;
import tip.storycreator.server.CustomGUI.PaintedButton;
import tip.storycreator.server.CustomGUI.PaintedPanel;



public class ServerGUI extends JFrame {
	public static final long serialVersionUID = 1;
	private JTextField portTextField;
	private JLabel descriptionLabel, portLabel, portErrorLabel;
	private JButton submitPortButton;
	private Lock portLock;
	private Condition portCondition;
	private ServerSocket ss;

//	private  Image logoimage;
	private Image buttonimage; 
	private Image buttonpressed;

	
	public ServerGUI() {

		super("StoryCreator Port GUI");
		initializeVariables();
		createGUI();
		addActionAdapters();
		setVisible(true);
	}
	
	private void initializeVariables() {
		
//		try {
//			logoimage = ImageIO.read(new File("resources/logo1.png"));
//			buttonimage = ImageIO.read(new File("resources/BlueButtonIcon.png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			buttonimage = ImageIO.read(new File("resources/BlueButtonIcon.png"));
			buttonpressed = ImageIO.read(new File("resources/BlueButtonIconPressed.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		descriptionLabel = new JLabel(Constants.portDescriptionString);
		portLabel = new JLabel(Constants.portLabelString);
		portErrorLabel = new JLabel();
		portTextField = new JTextField(20);
		portTextField.setText("" + Constants.defaultPort);
		submitPortButton = new PaintedButton(Constants.submitPortString, buttonimage, buttonpressed);
		
		portLock = new ReentrantLock();
		portCondition = portLock.newCondition();
		ss = null;
	}
	
	private void createGUI() {
		setSize(Constants.portGUIwidth, Constants.portGUIheight);
		PaintedPanel WholePanel = new PaintedPanel(ImageLibrary.getImage("resources/serverlogo.png"));
		GridLayout gl = new GridLayout(4, 1);
		WholePanel.setLayout(gl);
		WholePanel.add(descriptionLabel);
		JPanel portFieldPanel = new JPanel();
		portFieldPanel.setLayout(new FlowLayout());
		portFieldPanel.add(portLabel);
		portFieldPanel.add(portTextField);
		portTextField.setOpaque(false);
		portTextField.setBorder(BorderFactory.createEmptyBorder());
		portFieldPanel.setOpaque(false);
		WholePanel.add(portErrorLabel);
		WholePanel.add(portFieldPanel);
		WholePanel.add(submitPortButton);
		this.add(WholePanel);
	}
	
	private void addActionAdapters() {
		class PortListener implements ActionListener {
			public void actionPerformed(ActionEvent ae) {
				String portStr = portTextField.getText();
				int portNumber = -1;
				try {
					portNumber = Integer.parseInt(portStr);
				} catch (Exception e) {
					portErrorLabel.setText(Constants.portErrorString);
					return;
				}
				if (portNumber > Constants.lowPort && portNumber < Constants.highPort) {
					try {
						ServerSocket tempss = new ServerSocket(portNumber);
						portLock.lock();
						ss = tempss;
						portCondition.signal();
						portLock.unlock();
						ServerGUI.this.setVisible(false);
					} catch (IOException ioe) {
						// this will get thrown if I can't bind to portNumber
						portErrorLabel.setText(Constants.portAlreadyInUseString);
					}
				}
				else {
					portErrorLabel.setText(Constants.portErrorString);
					return;
				}
			}
		}
		submitPortButton.addActionListener(new PortListener());
		portTextField.addActionListener(new PortListener());
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				
				System.exit(0);
			}
		});
	}
	
	public ServerSocket getServerSocket() {
		while (ss == null) {
			portLock.lock();
			try {
				portCondition.await();
			} catch (InterruptedException ie) {
				System.out.println("INTERRUPTED EXCEPTION THROWN");
			}
			portLock.unlock();
		}
		return ss;
	}
}
