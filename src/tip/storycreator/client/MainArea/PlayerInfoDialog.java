package tip.storycreator.client.MainArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import library.FontLibrary;
import library.ImageLibrary;
import tip.storycreator.client.Constants;
import tip.storycreator.client.DefaultButton;
import tip.storycreator.game.Player;

public class PlayerInfoDialog extends JDialog{
	private static final long serialVersionUID = -1448993269273303391L;
	private static final Insets WEST_INSETS = new Insets(5, 0, 5, 5);
	private static final Insets EAST_INSETS = new Insets(5, 5, 5, 0);
	private JPanel mainPanel;
	private JLabel nameLabel;
	private JPanel optionPanel;
	private PlayerDialog thisPD;
	
	private final static Color default_color = new Color(35, 152, 208);
	private final static Font font = FontLibrary.getFont(Constants.pathname + "fonts/System San Francisco Display Thin.ttf", Font.PLAIN, 25);
	private final static Image notifyImg = ImageLibrary.getImage(Constants.pathname + Constants.createfile + "ll" + Constants.png);
	
	PlayerInfoDialog(String playerName) {	
		
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setTitle("Player Information");
		mainPanel = new JPanel(new GridLayout(2, 1));
		mainPanel.setBorder(new EmptyBorder(40, 40, 0, 40));
		mainPanel.setBackground(default_color);
		GridBagConstraints gbc;
		gbc = createGbc(0, 0);
		JLabel playerLabel = new JLabel("Player Name" + ":", JLabel.LEFT);
		playerLabel.setFont(font);
		playerLabel.setForeground(Color.white);
		mainPanel.add(playerLabel, gbc);
		gbc = createGbc(1, 0);
		nameLabel = new JLabel(playerName);
		nameLabel.setForeground(Color.white);
		nameLabel.setFont(font);
		mainPanel.add(nameLabel, gbc);
		
		
		optionPanel = new JPanel(new BorderLayout());
		optionPanel.setBackground(default_color);
		optionPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
		JButton ok = new DefaultButton("OK",
			   	ImageLibrary.getImage("resources/create_file/blue_button_light.png"),
				ImageLibrary.getImage("resources/create_file/blue_button_pressed.png"));
	   ok.addActionListener(new ActionListener() {	
	   @Override
			public void actionPerformed(ActionEvent e) {
		//   thisGCD.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		   		PlayerInfoDialog.this.dispose();
			}
	   });
		optionPanel.add(ok);
		
		add(mainPanel);
		add(optionPanel);
		
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(new Dimension(400, 250));
	    setMaximumSize(new Dimension(500, 150));
		setMinimumSize(new Dimension(500, 150));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	 private GridBagConstraints createGbc(int x, int y) {
		   GridBagConstraints gbc = new GridBagConstraints();
		   gbc.gridx = x;
		   gbc.gridy = y;
		   gbc.gridwidth = 1;
		   gbc.gridheight = 1;

		   gbc.anchor = (x == 0) ? GridBagConstraints.WEST : GridBagConstraints.EAST;
		   gbc.fill = (x == 0) ? GridBagConstraints.BOTH
	            : GridBagConstraints.HORIZONTAL;

		   gbc.insets = (x == 0) ? WEST_INSETS : EAST_INSETS;
		   gbc.weightx = (x == 0) ? 0.1 : 1.0;
		   gbc.weighty = 1.0;
		   return gbc;
	   }
	 
	 public static void main(String [] args) {
		 new PlayerInfoDialog("Sha Bi Xue Zhang");
	 }
}
