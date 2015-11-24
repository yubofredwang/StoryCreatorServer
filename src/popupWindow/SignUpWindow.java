package popupWindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;


import library.FontLibrary;
import library.ImageLibrary;
import tip.storycreator.client.Constants;
import tip.storycreator.client.DefaultButton;
import tip.storycreator.client.LoginWindow;

public class SignUpWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1757547794583031148L;
	final Font font = FontLibrary.getFont(Constants.pathname + "fonts/System San Francisco Display Regular.ttf",
			Font.PLAIN, 18);
	JTextField usernameField = new JTextField(8);
	JTextField passwordField = new JTextField(8);
	private LoginWindow mLoginWindow;
	JPanel overallPanel = new JPanel();

	public SignUpWindow(LoginWindow lw) {
		setModal(true);
		mLoginWindow = lw;
		UIManager.put("Label.font", font);
		UIManager.put("TextField.font", font);
		UIManager.put("TextArea.font", font);
		
		usernameField.setForeground(new Color(35, 152, 208));
		passwordField.setForeground(new Color(35, 152, 208));
		setTitle("SignUp");
		setMinimumSize(new Dimension(350, 250));
		setMaximumSize(new Dimension(350, 250));
		setLocationRelativeTo(null);
		
		overallPanel.setLayout(new GridLayout(2, 1));
		JPanel firstPanel = new JPanel();
		JPanel secondPanel = new JPanel();
		JLabel username = new JLabel("Username");
		username.setForeground(Color.WHITE);
		JLabel password = new JLabel("Password");
		password.setForeground(Color.WHITE);
		firstPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 30;
		c.ipady = 10;
		firstPanel.add(username, c);
		c.gridx = 1;
		c.gridy = 0;
		firstPanel.add(usernameField, c);
		c.gridx = 0;
		c.gridy = 1;
		JLabel jl = new JLabel("");
		firstPanel.add(jl, c);
		c.gridx = 1;
		c.gridy = 1;
		firstPanel.add(jl, c);
		c.gridx = 0;
		c.gridy = 2;
		firstPanel.add(password, c);
		c.gridx = 1;
		c.gridy = 2;
		firstPanel.add(passwordField, c);

		//JButton confirm = new JButton("Confirm");
		JButton confirm = new DefaultButton("Confirm",
				  ImageLibrary.getImage("resources/create_file/blue_button_light.png"),
				  ImageLibrary.getImage("resources/create_file/blue_button_pressed.png"));
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Connect to the 
				if(mLoginWindow.isConnected())
				{
					lw.addUser(usernameField.getText(), passwordField.getText());
				}
				else{
					new OneSentenceTwoButton("<html>" + "Connection Error - Go to Setting -> Connection" + "</html>", mLoginWindow.getParent(), "OKEY", "DOKEY", "Error");	
				}
			}
		});
		getRootPane().setDefaultButton(confirm);
		JButton cancel = new DefaultButton("Cancel",
				  ImageLibrary.getImage("resources/create_file/blue_button_light.png"),
				  ImageLibrary.getImage("resources/create_file/blue_button_pressed.png"));
		
		//JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		secondPanel.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();
		d.fill = GridBagConstraints.HORIZONTAL;
		d.gridx = 0;
		d.gridy = 0;
		d.ipadx = 30;
		d.ipady = 10;
		secondPanel.add(confirm, d);
		d.gridx = 1;
		d.gridy = 0;
		secondPanel.add(cancel, d);
		overallPanel.add(firstPanel);
		overallPanel.add(secondPanel);
		firstPanel.setBackground(new Color(35, 152, 208));
		secondPanel.setBackground(new Color(35, 152, 208));
		add(overallPanel);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				LoginWindow.isPressed = false;
				dispose();
			}
		});
	}

	public void close() {
		setVisible(false);
		LoginWindow.isPressed = false;
		dispose();
	}

	

}
