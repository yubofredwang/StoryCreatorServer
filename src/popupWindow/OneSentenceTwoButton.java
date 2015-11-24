package popupWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;

import library.FontLibrary;
import library.ImageLibrary;
import tip.storycreator.client.Constants;
import tip.storycreator.client.DefaultButton;

public class OneSentenceTwoButton {

	
	/**
	 * 
	 */
	final Font font = FontLibrary.getFont(Constants.pathname + "fonts/System San Francisco Display Regular.ttf",
			Font.PLAIN, 18);
	JPanel overallPanel = new JPanel();
	private int choice = -1;
	public boolean isPressed = false;

	public OneSentenceTwoButton(String content, Component c, String firstButtonName, String secondButtonName, String title) {
		JDialog jd = new JDialog();
		jd.setMinimumSize(new Dimension(350, 250));
		jd.setMaximumSize(new Dimension(350, 250));
		jd.setLocationRelativeTo(c);
		jd.setModal(true);
		jd.setTitle(title);
		UIManager.put("Button.font", font);
		UIManager.put("Label.font", font);
		UIManager.put("TextField.font", font);	
		overallPanel.setLayout(new GridLayout(2, 1));
		JPanel firstPanel = new JPanel();
		JPanel secondPanel = new JPanel();
		JLabel jl = new JLabel(content, SwingUtilities.CENTER);
		Border emptyBorder = BorderFactory.createEmptyBorder(60, 0, 0, 0);
		jl.setBorder(emptyBorder);
		jl.setForeground(Color.WHITE);
		firstPanel.setLayout(new BorderLayout());
		firstPanel.add(jl, BorderLayout.CENTER);
		JButton confirm = new DefaultButton(firstButtonName,
				  ImageLibrary.getImage("resources/create_file/blue_button_light.png"),
				  ImageLibrary.getImage("resources/create_file/blue_button_pressed.png"));
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				choice = 1;
				jd.dispose();
			}
		});
		jd.getRootPane().setDefaultButton(confirm);
		JButton cancel = new DefaultButton(secondButtonName,
				  ImageLibrary.getImage("resources/create_file/blue_button_light.png"),
				  ImageLibrary.getImage("resources/create_file/blue_button_pressed.png"));
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				choice = 0;
				jd.dispose();
				
			}
		});
		secondPanel.setLayout(new GridBagLayout());
		Border emptyBorder1 = BorderFactory.createEmptyBorder(0, 30, 0, 30);
		GridBagConstraints d = new GridBagConstraints();
		//d.fill = GridBagConstraints.HORIZONTAL;
		d.gridx = 0;
		d.gridy = 0;
		d.ipadx = 30;
		d.ipady = 30;
		d.weighty = 1.0;
		d.anchor = GridBagConstraints.PAGE_END;
		secondPanel.add(cancel, d);
		d.gridx = 1;
		d.gridy = 0;
		secondPanel.add(confirm, d);
		confirm.setBorder(emptyBorder1);
		overallPanel.add(firstPanel);
		overallPanel.add(secondPanel);
		firstPanel.setBackground(new Color(35, 152, 208));
		secondPanel.setBackground(new Color(35, 152, 208));
		jd.add(overallPanel);
		jd.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				choice = 2;
				jd.dispose();
			}
		});
		jd.pack();
		jd.setVisible(true);
		
	}
	
	public int getContent() {
		return choice;
	}
}

