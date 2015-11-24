package popupWindow;

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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import library.FontLibrary;
import library.ImageLibrary;
import tip.storycreator.client.Constants;
import tip.storycreator.client.DefaultButton;

public class OneTextFieldTwoButton {

	/**
	 * 
	 */
	final Font font = FontLibrary.getFont(Constants.pathname + "fonts/System San Francisco Display Regular.ttf",
			Font.PLAIN, 18);
	// public boolean isPressed = false;
	JPanel overallPanel = new JPanel();
	private String input = "";

	public OneTextFieldTwoButton(Component c, String content, String title, String firstButtonName,
			String secondButtonName) {
		JDialog jd = new JDialog();
		jd.setModal(true);
		jd.setMinimumSize(new Dimension(350, 250));
		jd.setMaximumSize(new Dimension(350, 250));
		UIManager.put("Button.font", font);
		UIManager.put("Label.font", font);
		UIManager.put("TextField.font", font);
		jd.setTitle(title);
		overallPanel.setLayout(new GridLayout(2, 1));
		JPanel firstPanel = new JPanel();
		JPanel secondPanel = new JPanel();
		firstPanel.setLayout(new GridBagLayout());
		Border emptyBorder = BorderFactory.createEmptyBorder(40, 40, 0, 40);
		firstPanel.setBorder(emptyBorder);
		JLabel jl = new JLabel(" " + content);
		jl.setForeground(Color.WHITE);
		JTextField jt = new JTextField(10);
		jt.setForeground(new Color(35, 152, 208));
		jt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				input = jt.getText();
				if (!input.equals("")) {
					jd.dispose();
				}

			}
		});

		GridBagConstraints d = new GridBagConstraints();
		d.fill = GridBagConstraints.HORIZONTAL;
		d.gridx = 0;
		d.gridy = 0;
		d.ipadx = 30;
		d.ipady = 10;
		firstPanel.add(jl, d);
		JLabel jl1 = new JLabel();
		d.gridx = 0;
		d.gridy = 1;
		firstPanel.add(jl1, d);
		d.gridx = 0;
		d.gridy = 2;
		firstPanel.add(jt, d);

		JButton confirm = new DefaultButton(firstButtonName,
				ImageLibrary.getImage("resources/create_file/blue_button_light.png"),
				ImageLibrary.getImage("resources/create_file/blue_button_pressed.png"));
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// isPressed = true;
				input = jt.getText();
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
				input = null;
				jd.dispose();
			}
		});
		secondPanel.setLayout(new GridBagLayout());
		GridBagConstraints e = new GridBagConstraints();
		e.fill = GridBagConstraints.HORIZONTAL;
		e.gridx = 0;
		e.gridy = 0;
		e.ipadx = 30;
		e.ipady = 10;
		secondPanel.add(cancel, e);
		e.gridx = 1;
		e.gridy = 0;
		secondPanel.add(confirm, e);
		overallPanel.add(firstPanel);
		overallPanel.add(secondPanel);
		firstPanel.setBackground(new Color(35, 152, 208));
		secondPanel.setBackground(new Color(35, 152, 208));
		jd.add(overallPanel);
		// setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// setVisible(true);
		jd.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				// LoginWindow.isPressed = false;
				input = null;
				jd.dispose();
			}
		});
		
		jd.setLocationRelativeTo(c);
		jd.pack();
		jd.setVisible(true);
	}

	public OneTextFieldTwoButton(Component c) {
		JDialog jd = new JDialog();
		jd.setModal(true);
		//jd.setMinimumSize(new Dimension(350, 250));
		//jd.setMaximumSize(new Dimension(350, 250));
		UIManager.put("Button.font", font);
		UIManager.put("Label.font", font);
		UIManager.put("TextField.font", font);
		jd.setTitle("Add Scene");

		overallPanel.setLayout(new GridLayout(2, 1));
		JPanel firstPanel = new JPanel();
		JPanel secondPanel = new JPanel();
		firstPanel.setLayout(new GridBagLayout());
		Border emptyBorder = BorderFactory.createEmptyBorder(40, 40, 0, 40);
		firstPanel.setBorder(emptyBorder);
		JLabel jl = new JLabel(" Add Scene");
		jl.setForeground(Color.WHITE);
		JTextField jt = new JTextField(10);
		jt.setForeground(new Color(35, 152, 208));
		jt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				input = jt.getText();
				if (!input.equals("")) {
					jd.dispose();
				}

			}
		});

		GridBagConstraints d = new GridBagConstraints();
		d.fill = GridBagConstraints.HORIZONTAL;
		d.gridx = 0;
		d.gridy = 0;
		d.ipadx = 30;
		d.ipady = 10;
		firstPanel.add(jl, d);
		JLabel jl1 = new JLabel();
		d.gridx = 0;
		d.gridy = 1;
		firstPanel.add(jl1, d);
		d.gridx = 0;
		d.gridy = 2;
		firstPanel.add(jt, d);

		JButton confirm = new DefaultButton("Yes", ImageLibrary.getImage("resources/create_file/blue_button_light.png"),
				ImageLibrary.getImage("resources/create_file/blue_button_pressed.png"));
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// isPressed = true;
				input = jt.getText();
				if (!input.equals("")) {
					jd.dispose();
				}
			}
		});
		jd.getRootPane().setDefaultButton(confirm);
		JButton cancel = new DefaultButton("Cancel", ImageLibrary.getImage("resources/create_file/blue_button_light.png"),
				ImageLibrary.getImage("resources/create_file/blue_button_pressed.png"));

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				input = null;
				jd.dispose();
			}
		});
		secondPanel.setLayout(new GridBagLayout());
		GridBagConstraints e = new GridBagConstraints();
		e.fill = GridBagConstraints.HORIZONTAL;
		e.gridx = 0;
		e.gridy = 0;
		e.ipadx = 30;
		e.ipady = 10;
		secondPanel.add(cancel, e);
		e.gridx = 1;
		e.gridy = 0;
		secondPanel.add(confirm, e);
		overallPanel.add(firstPanel);
		overallPanel.add(secondPanel);
		firstPanel.setBackground(new Color(35, 152, 208));
		secondPanel.setBackground(new Color(35, 152, 208));
		jd.add(overallPanel);
		// setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// setVisible(true);
		jd.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				// LoginWindow.isPressed = false;
				input = null;
				jd.dispose();
			}
		});
		jd.pack();
		jd.setLocationRelativeTo(c);
		jd.setVisible(true);
	}

	public OneTextFieldTwoButton(Component c, String content, String title) {
		JDialog jd = new JDialog();
		jd.setModal(true);
		//jd.setMinimumSize(new Dimension(350, 250));
		//jd.setMaximumSize(new Dimension(350, 250));
		UIManager.put("Button.font", font);
		UIManager.put("Label.font", font);
		UIManager.put("TextField.font", font);
		jd.setTitle(title);

		overallPanel.setLayout(new GridLayout(2, 1));
		JPanel firstPanel = new JPanel();
		JPanel secondPanel = new JPanel();
		firstPanel.setLayout(new GridBagLayout());
		Border emptyBorder = BorderFactory.createEmptyBorder(40, 40, 0, 40);
		firstPanel.setBorder(emptyBorder);
		
		JLabel jl = new JLabel(" " + content);
		jl.setForeground(Color.WHITE);
		JTextField jt = new JTextField(10);
		
		jt.setForeground(new Color(35, 152, 208));
		jt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				input = jt.getText();
				if (!input.equals("")) {
					jd.dispose();
				}
			}
		});

		GridBagConstraints d = new GridBagConstraints();
		JLabel jl1 = new JLabel();
		d.fill = GridBagConstraints.HORIZONTAL;
		d.gridx = 0;
		d.gridy = 0;
		d.ipadx = 30;
		d.ipady = 10;
		firstPanel.add(jl, d);
		d.gridx = 0;
		d.gridy = 1;
		firstPanel.add(jl1, d);
		d.gridx = 0;
		d.gridy = 2;
		firstPanel.add(jt, d);

		JButton confirm = new DefaultButton("Yes", ImageLibrary.getImage("resources/create_file/blue_button_light.png"),
				ImageLibrary.getImage("resources/create_file/blue_button_pressed.png"));
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// isPressed = true;
				input = jt.getText();
				jd.dispose();
			}
		});
		jd.getRootPane().setDefaultButton(confirm);
		JButton cancel = new DefaultButton("Cancel", ImageLibrary.getImage("resources/create_file/blue_button_light.png"),
				ImageLibrary.getImage("resources/create_file/blue_button_pressed.png"));

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				input = null;
				jd.dispose();
			}
		});
		secondPanel.setLayout(new GridBagLayout());
		GridBagConstraints e = new GridBagConstraints();
		e.fill = GridBagConstraints.HORIZONTAL;
		e.gridx = 0;
		e.gridy = 0;
		e.ipadx = 40;
		e.ipady = 40;
		e.weighty = 1.0;
		e.anchor = GridBagConstraints.PAGE_END;
		secondPanel.add(cancel, e);
		e.gridx = 1;
		e.gridy = 0;
		secondPanel.add(confirm, e);
		overallPanel.add(firstPanel);
		overallPanel.add(secondPanel);
		firstPanel.setBackground(new Color(35, 152, 208));
		secondPanel.setBackground(new Color(35, 152, 208));
		jd.add(overallPanel);
		// setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// setVisible(true);
		jd.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				// LoginWindow.isPressed = false;
				input = null;
				jd.dispose();
			}
		});
		jd.pack();
		jd.setLocationRelativeTo(c);
		jd.setVisible(true);
	}

	public String getContent() {
		return input;
	}

}
