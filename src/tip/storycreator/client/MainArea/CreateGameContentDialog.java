package tip.storycreator.client.MainArea;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import library.FontLibrary;
import library.ImageLibrary;
import tip.storycreator.client.Constants;
import tip.storycreator.client.DefaultButton;
import tip.storycreator.game.GameContent;

class CreateGameContentDialog {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 6100889137910184004L;
	private GameContent mGameContent;
	
	enum FieldTitle {
		NAME("Name"), Description("Description"), ImagePath("Image Path");
		private String title;

		private FieldTitle(String title) {
			this.title = title;
		}

		public String getTitle() {
			return title;
		}
	};
	private final Color color = new Color(35, 152, 208);
	private final Font font = FontLibrary.getFont(Constants.pathname + "fonts/System San Francisco Display Regular.ttf",
			Font.PLAIN, 17);
	private static final Insets WEST_INSETS = new Insets(5, 0, 5, 5);
	private static final Insets EAST_INSETS = new Insets(5, 5, 5, 0);
	private static final Image notifyImg = ImageLibrary.getImage("resources/main_window/ll.png");
	private Map<FieldTitle, JTextField> fieldMap = new HashMap<FieldTitle, JTextField>();
	private JTextField nameField;
	private JTextField dspField;
	private JTextField imgField;
	private JPanel mainPanel;
	private JPanel optionPanel;
	private JPanel previewPanel;
	private String imgPath;

	public CreateGameContentDialog(Component c) {
		JDialog mDialog = new JDialog();
		mDialog.setModal(true);
		mGameContent = null;
		
		UIManager.put("Label.foreground", color);
		UIManager.put("Label.font", font);
		UIManager.put("TextField.font", font);
		UIManager.put("Button.font", font);
		mDialog.setLayout(new BoxLayout(mDialog.getContentPane(), BoxLayout.Y_AXIS));

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		// mainPanel.setBorder(BorderFactory.createCompoundBorder(
		// BorderFactory.createTitledBorder("Game Content Editor"),
		// BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		mainPanel.setBorder(BorderFactory.createTitledBorder(null, "Game Content Editor", TitledBorder.LEFT,
				TitledBorder.TOP, font, new Color(35, 152, 208)));
		GridBagConstraints gbc;

		FieldTitle fieldTitle = FieldTitle.values()[0];
		gbc = createGbc(0, 0);
		mainPanel.add(new JLabel(fieldTitle.getTitle() + ":", JLabel.LEFT), gbc);
		gbc = createGbc(1, 0);
		nameField = new JTextField(10);
		nameField.setForeground(color);
		mainPanel.add(nameField, gbc);
		fieldMap.put(fieldTitle, nameField);

		FieldTitle fieldTitle1 = FieldTitle.values()[1];
		gbc = createGbc(0, 1);
		mainPanel.add(new JLabel(fieldTitle1.getTitle() + ":", JLabel.LEFT), gbc);
		gbc = createGbc(1, 1);
		dspField = new JTextField(10);
		dspField.setForeground(color);
		mainPanel.add(dspField, gbc);
		fieldMap.put(fieldTitle, dspField);

		FieldTitle fieldTitle2 = FieldTitle.values()[2];
		gbc = createGbc(0, 2);
		mainPanel.add(new JLabel(fieldTitle2.getTitle() + ":", JLabel.LEFT), gbc);
		gbc = createGbc(1, 2);
		imgField = new JTextField(10);
		imgField.setForeground(color);
		mainPanel.add(imgField, gbc);
		fieldMap.put(fieldTitle, imgField);
		imgField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imgPath = imgField.getText();
				System.out.println(imgPath);
				previewPanel.setPreferredSize(new Dimension(250, 280));
				JLabel previewLabel = new JLabel();
				Image img = null;
				try {
					img = ImageIO.read(new File(imgPath));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(mDialog, "Error Image Path!", "Input Error", JOptionPane.OK_OPTION, new ImageIcon(notifyImg));
				}
				Image img1 = img.getScaledInstance(250, 250, Image.SCALE_DEFAULT);
				previewPanel.removeAll();
				previewLabel.setIcon(new ImageIcon(img1));
				previewLabel.revalidate();
				previewLabel.repaint();
				previewPanel.revalidate();
				previewPanel.repaint();
				previewPanel.add(previewLabel);
				mDialog.setSize(new Dimension(500, 550));

			}
		});
		JButton browseButton = new JButton("Browse...");
		browseButton.setForeground(color);
		browseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser1 = new JFileChooser();
		    	fileChooser1.setCurrentDirectory(new File("."));
		        int returnValue1 = fileChooser1.showOpenDialog(null);
		        if (returnValue1 == JFileChooser.APPROVE_OPTION) {
		        	File selectedFile = fileChooser1.getSelectedFile();
		        	imgField.setText(selectedFile.getAbsolutePath());
		        	imgField.getActionListeners()[0].actionPerformed(e);
		        }
				
			}
		});
		gbc = createGbc(2, 2);
		mainPanel.add(browseButton, gbc);

		previewPanel = new JPanel(new GridBagLayout());
		//previewPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Image Preview"),
				//BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		previewPanel.setBorder(BorderFactory.createTitledBorder(null, "Game Content Editor", TitledBorder.LEFT,
				TitledBorder.TOP, font, new Color(35, 152, 208)));
		optionPanel = new JPanel(new GridLayout(1, 2));
		JButton ok = new DefaultButton("OK",
										ImageLibrary.getImage("resources/create_file/blue_button_light.png"),
										ImageLibrary.getImage("resources/create_file/blue_button.png"), 120, 40);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("test" + nameField.getText());
				if (nameField.getText().equals("")) {
					System.out.println(1111);
					JOptionPane.showMessageDialog(mDialog, "Please Input the Name!", "Input Error",
							JOptionPane.OK_OPTION, new ImageIcon(notifyImg));
					return;
				}
				if (dspField.getText().equals("")) {
					JOptionPane.showMessageDialog(mDialog, "Please Input the Description!", "Input Error",
							JOptionPane.OK_OPTION, new ImageIcon(notifyImg));
					return;
				}
				if (!nameField.getText().equals("") && !dspField.equals("")) {
					mGameContent = new GameContent(nameField.getText(), dspField.getText());
					if (imgField.getText() != null ) {
						if(!imgField.getText().equals(""))
							mGameContent.setImage(imgField.getText());
					}
					mDialog.dispose();
					return;
				}
			}
		});

		JButton cancel = new DefaultButton("Cancel",
											ImageLibrary.getImage("resources/create_file/blue_button_light.png"),
											ImageLibrary.getImage("resources/create_file/blue_button.png"), 120, 40);
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// thisGCD.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				mDialog.dispose();
			}
		});
		
		optionPanel.add(cancel);
		optionPanel.add(ok);
		optionPanel.setPreferredSize(new Dimension(300, 200));

		JPanel mainEmptyPanel = new JPanel(new BorderLayout());
		mainEmptyPanel.setBackground(Color.white);
		mainEmptyPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainEmptyPanel.add(mainPanel, BorderLayout.CENTER);
		mDialog.add(mainEmptyPanel);
		
		JPanel previewEmptyPanel = new JPanel(new BorderLayout());
		previewEmptyPanel.setBackground(Color.white);
		previewEmptyPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		previewEmptyPanel.add(previewPanel, BorderLayout.CENTER);
		mDialog.add(previewEmptyPanel);
		
		mDialog.add(optionPanel);
		mDialog.setMaximumSize(new Dimension(500, 300));
		mDialog.setMinimumSize(new Dimension(500, 300));
		mDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		mainPanel.setBackground(Color.WHITE);
		optionPanel.setBackground(Color.WHITE);
		previewPanel.setBackground(Color.WHITE);
		mDialog.setLocationRelativeTo(c);
		mDialog.setVisible(true);
	}

	private GridBagConstraints createGbc(int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;

		gbc.anchor = (x == 0) ? GridBagConstraints.WEST : GridBagConstraints.EAST;
		gbc.fill = (x == 0) ? GridBagConstraints.BOTH : GridBagConstraints.HORIZONTAL;

		gbc.insets = (x == 0) ? WEST_INSETS : EAST_INSETS;
		gbc.weightx = (x == 0) ? 0.1 : 1.0;
		gbc.weighty = 1.0;
		return gbc;
	}

	public String getFieldText(FieldTitle fieldTitle) {
		return fieldMap.get(fieldTitle).getText();
	}
	
	public GameContent getContent()
	{
		return mGameContent;
	}

	public static void main(String[] arge) {
		new CreateGameContentDialog(null);
	}

}
