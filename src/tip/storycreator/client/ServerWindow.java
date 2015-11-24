package tip.storycreator.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import library.FontLibrary;

public class ServerWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2296318791337552784L;
	final Font font = FontLibrary.getFont(Constants.pathname + "fonts/System San Francisco Display Bold.ttf", Font.PLAIN, 16);

	public ServerWindow (ActionListener ServerWindowAc) {
		UIManager.put("Button.font", font);
		UIManager.put("Label.font", font);
		UIManager.put("TextField.font", font);
		setTitle("Server");
		setMinimumSize(new Dimension(400,200));
		setMaximumSize(new Dimension(400,200));
		setLocationRelativeTo(null);
		JPanel overallPanel = new JPanel();
		overallPanel.setLayout(new GridLayout(2, 1));
		JPanel firstPanel = new JPanel();
		JPanel secondPanel = new JPanel();
		JLabel hostname = new JLabel("Hostname");
		JLabel port = new JLabel("Port");
		JTextField hostnameField = new JTextField("localhost");
		JTextField portField = new JTextField("6789");
		firstPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 30;
		c.ipady = 10;
		firstPanel.add(hostname, c);
		c.gridx = 1;
		c.gridy = 0;
		firstPanel.add(hostnameField, c);
		c.gridx = 0;
		c.gridy = 1;
		firstPanel.add(port, c);
		c.gridx = 1;
		c.gridy = 1;
		firstPanel.add(portField, c);
		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(ServerWindowAc);
		JButton cancel = new JButton("Cancel");
		confirm.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ServerWindow.this.dispose();
			}
			
		});
		cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ServerWindow.this.dispose();
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
		firstPanel.setBackground(Color.WHITE); 
		secondPanel.setBackground(Color.WHITE); 
		add(overallPanel);
	}

}
