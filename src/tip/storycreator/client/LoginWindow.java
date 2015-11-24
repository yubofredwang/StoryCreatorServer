package tip.storycreator.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import library.FontLibrary;
import library.ImageLibrary;
import popupWindow.ChangePassword;
import popupWindow.SignUpWindow;
import tip.storycreator.client.StoryCreatorClientWindow.LoginAsGuestListener;
import tip.storycreator.client.StoryCreatorClientWindow.LoginLoadingListener;
import tip.storycreator.client.StoryCreatorClientWindow.WindowServerActionListener;

public class LoginWindow extends JPanel {

	private static final long serialVersionUID = 6945651168916095210L;

	JLabel title;
	JLabel userLabel;
	JLabel passwordLabel;
	JTextField userField;
	JPasswordField passwordField;
	JButton loginButton;
	Icon gifIcon;
	JLabel signUp;
	JLabel guest;
	JLabel changePassword;
	BufferedImage image;
	JLabel logoLabel;
	JPanel buttonPanel;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem menuItem;
	MouseListener loginbuttonActionListener;
	MouseListener loginAsGuestListener;
	WindowServerActionListener windowServerActionListener;
	final Font font = FontLibrary.getFont(Constants.pathname + "fonts/System San Francisco Display Regular.ttf",
			Font.PLAIN, 16);
	final Font font1 = FontLibrary.getFont(Constants.pathname + "fonts/System San Francisco Display Bold.ttf",
			Font.PLAIN, 16);
	SignUpWindow sw;
	Color color = new Color(35, 152, 208);
	public static boolean isPressed = false;

	ClientListener mClientListener;

	// public static void main(String[] arge) {
	// // for test reason create client listener here
	//
	// ClientListener cls = new ClientListener(6789, "localhost");
	//
	// JFrame jf = new JFrame();
	// jf.setMinimumSize(new Dimension(960, 640));
	// jf.setLocationRelativeTo(null);
	// jf.add(new LoginWindow(null, cls));
	// jf.setVisible(true);
	// cls.run();
	// }

	
	public class BackgroundMenuBar extends JMenuBar {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5899676110921947138L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(color);
			g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
		}
	}

	public class BackgroundMenu extends JMenu {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2223286384893132466L;

		public BackgroundMenu(String name) {
			super(name);
			setForeground(Color.GREEN);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(color);
			g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
		}
	}
	

	// the second parameter is for the use of adding client
	// listener into it
	public LoginWindow(LoginLoadingListener loginLoadingListener, LoginAsGuestListener inloginAsGuestListener, WindowServerActionListener windowServerActionListener) {
		loginbuttonActionListener = loginLoadingListener;
		loginAsGuestListener = inloginAsGuestListener;
		 this.windowServerActionListener = windowServerActionListener;
		// mClientListener = cls;
		final Font font2 = FontLibrary.getFont(Constants.pathname + "fonts/System San Francisco Display Regular.ttf",
				Font.PLAIN, 18);
		UIManager.put("Label.font", font);
		UIManager.put("Button.font", font2);
		UIManager.put("TextField.font", font);
		UIManager.put("MenuBar.font", font);
		UIManager.put("MenuItem.font", font);
		UIManager.put("Menu.font", font);
		setMaximumSize(new Dimension(960, 640));
		image = (BufferedImage) ImageLibrary.getImage("resources/logo2.png");
		
		try {
			File file = new File("resources/animation/login_animation1.gif");
			String path = "file://" + file.getAbsolutePath();
//			path = "file:///D:/storycreator-csci201/StoryCreator/resources/animation/login_animation1.gif";
			URL url = new URL(path);
			gifIcon = new ImageIcon(url);
			logoLabel = new JLabel(gifIcon);
			ChangeToNextLogo();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		
		setUp();
		setLayout(new BorderLayout());
		/**
		 * Using BorderLayout for this panel
		 */

		menuBar = new BackgroundMenuBar();
		menuBar.setForeground(Color.white);
		UIManager.put("MenuItem.selectionBackground", Color.WHITE);
		UIManager.put("MenuItem.selectionForeground", Color.WHITE);
		UIManager.put("Menu.selectionBackground", Color.WHITE);
		UIManager.put("Menu.selectionForeground", Color.WHITE);
		UIManager.put("MenuBar.selectionBackground", Color.WHITE);
		UIManager.put("MenuBar.selectionForeground", Color.WHITE);
		menu = new JMenu("Setting");
		menu.setOpaque(false);
		// menu.setFont(font);

		menuItem = new JMenuItem("Connect");
		

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServerWindow sw = new ServerWindow(windowServerActionListener);
				sw.setVisible(true);
			}
		});
		menu.add(menuItem);
		menuBar.add(menu);
		add(menuBar, BorderLayout.NORTH);
		// wait for image
		add(logoLabel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

	}

	private void ChangeToNextLogo() {
		Timer timer = new Timer();
		
		timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
				  
					try {
						File file = new File("resources/animation/login_animation2.gif");
						String path = "file://" + file.getAbsolutePath();
//						path = "file:///D:/storycreator-csci201/StoryCreator/resources/animation/login_animation2.gif";
						URL url = new URL(path);
						  gifIcon = new ImageIcon(url);
						  JLabel logo2Label = new JLabel(gifIcon);
						  LoginWindow.this.remove(logoLabel);
						  LoginWindow.this.add(logo2Label);
						  LoginWindow.this.revalidate();
						  LoginWindow.this.repaint();
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			  }
			}, 3500);
	}

	public void setClientListener(ClientListener cls) {
		mClientListener = cls;
	}

	/**
	 * Method used for setting up all elements
	 */
	public void setUp() {
		userLabel = new JLabel(" User", JLabel.LEFT);
		userLabel.setForeground(Color.WHITE);
		passwordLabel = new JLabel(" Password", JLabel.LEFT);
		passwordLabel.setForeground(Color.WHITE);
		userField = new JTextField(8);
		userField.setForeground(color);
		passwordField = new JPasswordField(8);
		passwordField.setForeground(color);
		passwordField.setPreferredSize(new Dimension(100, 26));
		/*
		 * loginButton = new JButton("Login");
		 * loginButton.setForeground(Color.WHITE);
		 * loginButton.setBackground(Color.GRAY); loginButton.setOpaque(true);
		 * loginButton.setBorderPainted(false);
		 * loginButton.setForeground(Color.WHITE); loginButton.setEnabled(true);
		 */
		loginButton = new DefaultButton("Login", ImageLibrary.getImage("resources/create_file/blue_button_pressed.png"),
				ImageLibrary.getImage("resources/create_file/blue_button_pressed.png"));
		loginButton.addMouseListener(loginbuttonActionListener);
		/**
		 * Set up login function buttons. Make them transparent and align to the
		 * left
		 */

		signUp = new JLabel("SignUp now             ");
		// signUp.setBorder(null);
		signUp.setOpaque(true);
		// Set the size of the label
		signUp.setBackground(new Color(35, 152, 208));
		signUp.setForeground(Color.WHITE);
		// signUp.setHorizontalAlignment(SwingConstants.LEFT);
		signUp.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				if (!isPressed) {
					sw = new SignUpWindow(LoginWindow.this);
					sw.setVisible(true);
					/*OneSentenceTwoButton os = new OneSentenceTwoButton("sd");
					os.setVisible(true);
					while (true) {
						if (os.isPressed) {
							System.out.println(os.returnChoice());
							break;
						}
					}*/
					/*AddScene os = new AddScene();
					while (true) {
						if (os.isPressed) {
							System.out.println(os.returnInput());
							break;
						}
					}*/
					
				}
			}

			public void mouseEntered(MouseEvent arg0) {
				signUp.setFont(font1);
			}

			public void mouseExited(MouseEvent arg0) {
				signUp.setFont(font);
			}

			public void mousePressed(MouseEvent arg0) {
				signUp.setForeground(Color.GRAY);
			}

			public void mouseReleased(MouseEvent arg0) {
				signUp.setForeground(Color.white);
			}
		});

		guest = new JLabel("Login as Guest    ");
		// guest.setBorder(null);
		guest.setOpaque(true);
		guest.setBackground(new Color(35, 152, 208));
		guest.setForeground(Color.WHITE);

		// guest.setHorizontalAlignment(SwingConstants.LEFT);
		guest.addMouseListener(loginAsGuestListener);
		guest.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent arg0) {
				guest.setFont(font1);
			}

			public void mouseExited(MouseEvent arg0) {
				guest.setFont(font);
			}

			public void mousePressed(MouseEvent arg0) {
				guest.setForeground(Color.GRAY);
			}

			public void mouseReleased(MouseEvent arg0) {
				guest.setForeground(Color.WHITE);
			}
		});

		changePassword = new JLabel("Change Password     ");
		// changePassword.setBorder(null);
		changePassword.setOpaque(true);
		changePassword.setBackground(new Color(35, 152, 208));
		changePassword.setForeground(Color.WHITE);
		// changePassword.setHorizontalAlignment(SwingConstants.LEFT);
		changePassword.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				if (!isPressed) {
					ChangePassword fuw = new ChangePassword();
					fuw.setLoginWindow(LoginWindow.this);
					fuw.setClientListener(mClientListener);
					fuw.setVisible(true);
				}
			}

			public void mouseEntered(MouseEvent arg0) {
				changePassword.setFont(font1);
			}

			public void mouseExited(MouseEvent arg0) {
				changePassword.setFont(font);
			}

			public void mousePressed(MouseEvent arg0) {
				changePassword.setForeground(Color.GRAY);
			}

			public void mouseReleased(MouseEvent arg0) {
				changePassword.setForeground(Color.WHITE);
			}
		});

		/**
		 * create a new panel for bottom elements
		 */
		buttonPanel = new JPanel();
		// buttonPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		JPanel firstPanel = new JPanel();
		JPanel secondPanel = new JPanel();
		JPanel thirdPanel = new JPanel();
		JPanel forthPanel = new JPanel();
		firstPanel.setBackground(new Color(0, 0, 0, 0));
		secondPanel.setBackground(new Color(0, 0, 0, 0));
		thirdPanel.setBackground(new Color(0, 0, 0, 0));
		forthPanel.setBackground(new Color(0, 0, 0, 0));
		firstPanel.setOpaque(true);
		secondPanel.setOpaque(true);
		thirdPanel.setOpaque(true);
		forthPanel.setOpaque(true);
		secondPanel.setLayout(new GridBagLayout());
		thirdPanel.setLayout(new GridBagLayout());
		forthPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		GridBagConstraints d = new GridBagConstraints();
		GridBagConstraints e = new GridBagConstraints();
		buttonPanel.setLayout(new GridLayout(1, 4));
		buttonPanel.add(firstPanel);
		//
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 30;
		c.ipady = 10;
		JButton emptyLabel = new JButton();
		emptyLabel.setEnabled(false);
		emptyLabel.setBackground(new Color(35, 152, 208));
		emptyLabel.setOpaque(true);
		emptyLabel.setBorderPainted(false);
		secondPanel.add(userLabel, c);
		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = 30;
		c.ipady = 10;
		c.gridwidth = 2;
		userField.setOpaque(true);
		secondPanel.add(userField, c);
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(70, 0, 10, 0);
		secondPanel.add(emptyLabel, c);
		//
		buttonPanel.add(secondPanel);
		d.fill = GridBagConstraints.HORIZONTAL;
		d.gridx = 0;
		d.gridy = 0;
		d.ipadx = 30;
		d.ipady = 10;
		thirdPanel.add(passwordLabel, d);
		d.gridx = 0;
		d.gridy = 1;
		d.ipadx = 30;
		d.ipady = 10;
		d.gridwidth = 2;
		passwordField.setOpaque(true);
		thirdPanel.add(passwordField, d);
		d.gridx = 0;
		d.gridy = 2;
		d.insets = new Insets(50, 0, 10, 0);
		thirdPanel.add(loginButton, d);
		thirdPanel.setBackground(new Color(35, 152, 208));
		//
		buttonPanel.add(thirdPanel);
		e.fill = GridBagConstraints.HORIZONTAL;
		e.gridx = 0;
		e.gridy = 0;
		forthPanel.add(signUp, e);
		e.gridx = 0;
		e.gridy = 1;
		e.insets = new Insets(20, 0, 0, 0);
		forthPanel.add(guest, e);
		e.gridx = 0;
		e.gridy = 2;
		e.insets = new Insets(20, 0, 0, 0);
		forthPanel.add(changePassword, e);
		forthPanel.setBackground(new Color(35, 152, 208));
		buttonPanel.add(forthPanel);
		// buttonPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		buttonPanel.setBackground(new Color(35, 152, 208));

	}

	public void addUser(String user, String password) {
		// TODO Auto-generated method stub
		mClientListener.signUp(user, password);
	}

	public void closeSignUpWindow() {
		
		sw.close();
	}
	// class LoginAction implements ActionListener{
	//
	// @Override
	// public void actionPerformed(ActionEvent arg0) {
	// // TODO Auto-generated method stub
	// //send to
	// String user= userField.getText();
	// String password = passwordField.getPassword().toString();
	//
	// }
	public boolean isConnected()
	{
		if(mClientListener == null)
		{
			return false;
		}
		else{
			return true;
		}
	}
	// }

	public String getLoginName() {
		return userField.getText();
		// TODO Auto-generated method stub
	}

	public String getPassword() {
		return String.valueOf(passwordField.getPassword());

	}

}
