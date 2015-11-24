package tip.storycreator.client.MainArea;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import library.FontLibrary;
import tip.storycreator.client.Constants;
import tip.storycreator.game.GameProject;
import tip.storycreator.game.GameSetting;
import tip.storycreator.game.Scene;
import tip.storycreator.game.SceneState;

public class GameSettingDialog extends JDialog {

	private static final long serialVersionUID = 1251305674033416268L;

	private static final Insets WEST_INSETS = new Insets(5, 0, 5, 5);
	private static final Insets EAST_INSETS = new Insets(5, 5, 5, 0);
	private JPanel mainPanel;
	private JTextField bgImgField;
	private JTextField pButtonField;
	private JTextField rButtonField;
	private JComboBox<Scene> sceneBox;
	private JComboBox<SceneState> stateBox;
	private JTextField fontField;
	private JPanel optionPanel;
	private GameSetting mGameSetting;

	private final Integer[] fontSizeList = { 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28 };
	private final Color[] colorList = { Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY,
			Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW,
			Color.GREEN };
	private final String[] fontColorList = { "BLACK", "BLUE", "CYAN", "DARK_GRAY", "GRAY", "LIGHT_GRAY", "MAGENTA",
			"ORANGE", "PINK", "RED", "WHITE", "YELLOW", "GREEN" };
	private JComboBox<Integer> ButtonfontSizeBox;
	private JComboBox<String> ButtonfontColorBox;
	private JComboBox<Integer> TextfontSizeBox;
	private JComboBox<String> TextfontColorBox;
	int sceneID = -1;
	int stateID = -1;

	Color color = new Color(35, 152, 208);
	final Font font = FontLibrary.getFont(Constants.pathname + "fonts/System San Francisco Display Regular.ttf",
			Font.PLAIN, 16);

	private void initVariable() {

		bgImgField.setText(mGameSetting.getBackgroundImage());
		pButtonField.setText(mGameSetting.getPressedButtonString());
		rButtonField.setText(mGameSetting.getReleasedButtonString());
		fontField.setText(mGameSetting.getFontString());
		sceneID = mGameSetting.getInitialScene();
		stateID = mGameSetting.getInitialState();
		for (int i = 0; i < sceneBox.getItemCount(); ++i) {
			if (sceneBox.getItemAt(i).getID() == sceneID) {
				sceneBox.setSelectedIndex(i);
				stateBox.setSelectedIndex(-1);
				stateBox.removeAllItems();
				for (SceneState ss : sceneBox.getItemAt(i).getAllSceneStates().values()) {
					stateBox.addItem(ss);
				}
			}
		}
		for (int i = 0; i < stateBox.getItemCount(); ++i) {
			if (stateBox.getItemAt(i).getID() == stateID) {
				stateBox.setSelectedIndex(i);
				break;
			}
		}
		ButtonfontSizeBox.setSelectedItem(mGameSetting.getButtonFontSize());
		ButtonfontColorBox.setSelectedItem(mGameSetting.getButtonFontColor());
		TextfontSizeBox.setSelectedItem(mGameSetting.getTextFontSize());
		TextfontColorBox.setSelectedItem(mGameSetting.getTextFontColor());

	}

	public GameSettingDialog(GameProject gp) {
		setModal(true);
		UIManager.put("Label.font", font);
		UIManager.put("TextField.font", font);
		UIManager.put("ComboBox.font", font);
		UIManager.put("Label.foreground", color);
		UIManager.put("TextField.foreground", color);
		UIManager.put("ComboBox.foreground", color);
		UIManager.put("Panel.background", Color.WHITE);
		//UIManager.put("TabbedPane.foreground", color);
		mGameSetting = gp.getGameSetting();
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(Color.WHITE);
		TitledBorder titledBorder = BorderFactory.createTitledBorder(null, "Game Setting Editor", TitledBorder.LEFT, TitledBorder.TOP, font, color);
		mainPanel.setBorder(titledBorder);

		//mainPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Game Setting Editor"),
		//		BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		GridBagConstraints gbc;
		gbc = createGbc(0, 0);
		mainPanel.add(new JLabel("Background Image Path" + ":", JLabel.LEFT), gbc);
		gbc = createGbc(1, 0);
		bgImgField = new JTextField(10);
		mainPanel.add(bgImgField, gbc);
		gbc = createGbc(2, 0);
		JButton imgBrowseButton = new JButton("Browse...");
		imgBrowseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createFileChooser(bgImgField);
				
			}
		});
		mainPanel.add(imgBrowseButton, gbc);
	

		gbc = createGbc(0, 1);
		mainPanel.add(new JLabel("Pressed Button Image Path" + ":", JLabel.LEFT), gbc);
		gbc = createGbc(1, 1);
		pButtonField = new JTextField(10);
		mainPanel.add(pButtonField, gbc);
		gbc = createGbc(2, 1);
		JButton pButtonBrowseButton = new JButton("Browse...");
		pButtonBrowseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createFileChooser(pButtonField);
				
			}
		});
		mainPanel.add(pButtonBrowseButton, gbc);
		
		gbc = createGbc(0, 2);
		mainPanel.add(new JLabel("Released Button Image Path" + ":", JLabel.LEFT), gbc);
		gbc = createGbc(1, 2);
		rButtonField = new JTextField(10);
		mainPanel.add(rButtonField, gbc);
		gbc = createGbc(2, 2);
		JButton rButtonBrowseButton = new JButton("Browse...");
		rButtonBrowseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createFileChooser(rButtonField);
				
			}
		});
		mainPanel.add(rButtonBrowseButton, gbc);

		gbc = createGbc(0, 3);
		mainPanel.add(new JLabel("Font Path" + ":", JLabel.LEFT), gbc);
		gbc = createGbc(1, 3);
		fontField = new JTextField(10);
		mainPanel.add(fontField, gbc);
		gbc = createGbc(2, 3);
		JButton fontBrowseButton = new JButton("Browse...");
		fontBrowseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createFileChooser(fontField);
				
			}
		});
		mainPanel.add(fontBrowseButton, gbc);

		gbc = createGbc(0, 4);
		mainPanel.add(new JLabel("Button Font Color" + ":", JLabel.LEFT), gbc);
		gbc = createGbc(1, 4);
		JPanel tempPanel3 = new JPanel(new GridLayout(1, 2));
		JPanel emptyPanel3 = new JPanel();
		ButtonfontColorBox = new JComboBox<String>(fontColorList);
		ComboBoxRenderer renderer = new ComboBoxRenderer(ButtonfontColorBox);

		renderer.setColors(colorList);
		renderer.setStrings(fontColorList);
		ButtonfontColorBox.setRenderer(renderer);

		tempPanel3.add(ButtonfontColorBox);
		tempPanel3.add(emptyPanel3);
		mainPanel.add(tempPanel3, gbc);

		gbc = createGbc(0, 5);
		mainPanel.add(new JLabel("Button Font Size" + ":", JLabel.LEFT), gbc);
		gbc = createGbc(1, 5);
		JPanel tempPanel = new JPanel(new GridLayout(1, 2));
		JPanel emptyPanel = new JPanel();
		ButtonfontSizeBox = new JComboBox<Integer>(fontSizeList);
		DefaultListCellRenderer dlcr1 = new DefaultListCellRenderer();
		dlcr1.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		ButtonfontSizeBox.setRenderer(dlcr1);
		tempPanel.add(ButtonfontSizeBox);
		tempPanel.add(emptyPanel);
		mainPanel.add(tempPanel, gbc);

		gbc = createGbc(0, 6);
		mainPanel.add(new JLabel("Text Font Color" + ":", JLabel.LEFT), gbc);
		gbc = createGbc(1, 6);
		JPanel tempPanel4 = new JPanel(new GridLayout(1, 2));
		JPanel emptyPanel4 = new JPanel();
		TextfontColorBox = new JComboBox<String>(fontColorList);
		ComboBoxRenderer renderer2 = new ComboBoxRenderer(TextfontColorBox);

		renderer2.setColors(colorList);
		renderer2.setStrings(fontColorList);
		TextfontColorBox.setRenderer(renderer2);

		tempPanel4.add(TextfontColorBox);
		tempPanel4.add(emptyPanel4);
		mainPanel.add(tempPanel4, gbc);

		gbc = createGbc(0, 7);
		mainPanel.add(new JLabel("Text Font Size" + ":", JLabel.LEFT), gbc);
		gbc = createGbc(1, 7);
		JPanel tempPanel5 = new JPanel(new GridLayout(1, 2));
		JPanel emptyPanel5 = new JPanel();
		TextfontSizeBox = new JComboBox<Integer>(fontSizeList);
		DefaultListCellRenderer dlcr4 = new DefaultListCellRenderer();
		dlcr4.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		TextfontSizeBox.setRenderer(dlcr4);
		tempPanel5.add(TextfontSizeBox);
		tempPanel5.add(emptyPanel5);
		mainPanel.add(tempPanel5, gbc);

		gbc = createGbc(0, 8);
		mainPanel.add(new JLabel("Initial Scene ID" + ":", JLabel.LEFT), gbc);
		gbc = createGbc(1, 8);
		JPanel tempPanel1 = new JPanel(new GridLayout(1, 2));
		JPanel emptyPanel1 = new JPanel();
		List<Scene> sceneList = new ArrayList<Scene>();
		for (Scene s : gp.getSceneMap().values()) {
			sceneList.add(s);
		}
		Scene[] sceneArray = sceneList.toArray(new Scene[sceneList.size()]);
		sceneBox = new JComboBox<Scene>(sceneArray);
		sceneBox.setSelectedIndex(-1);
		tempPanel1.add(sceneBox);
		tempPanel1.add(emptyPanel1);
		mainPanel.add(tempPanel1, gbc);

		gbc = createGbc(0, 9);
		mainPanel.add(new JLabel("Initial State ID" + ":", JLabel.LEFT), gbc);
		stateBox = new JComboBox<SceneState>();
		stateBox.setSelectedIndex(-1);
		gbc = createGbc(1, 9);
		JPanel tempPanel2 = new JPanel(new GridLayout(1, 2));
		JPanel emptyPanel2 = new JPanel();
		tempPanel2.add(stateBox);
		tempPanel2.add(emptyPanel2);
		mainPanel.add(tempPanel2, gbc);

		optionPanel = new JPanel(new GridLayout(1, 2));
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mGameSetting.setBackgroundImage(bgImgField.getText());
				mGameSetting.setButtonFontColor((String) ButtonfontColorBox.getSelectedItem());
				mGameSetting.setButtonFontSize((int) ButtonfontSizeBox.getSelectedItem());
				mGameSetting.setFont(fontField.getText());
				mGameSetting.setInitialScene(((Scene) sceneBox.getSelectedItem()).getID());
				mGameSetting.setInitialState(((SceneState) stateBox.getSelectedItem()).getID());
				mGameSetting.setPressedButtonImage(pButtonField.getText());
				mGameSetting.setReleasedButtonString(rButtonField.getText());
				mGameSetting.setTextFontColor((String) TextfontColorBox.getSelectedItem());
				mGameSetting.setTextFontSize((int) TextfontSizeBox.getSelectedItem());
			}
		});

		JButton cancel = new JButton("Revert");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initVariable();
			}
		});
		optionPanel.add(cancel);
		optionPanel.add(ok);

		add(mainPanel);
		add(optionPanel);

		sceneBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				Scene s = (Scene) sceneBox.getSelectedItem();
				stateBox.setSelectedIndex(-1);
				stateBox.removeAllItems();
				for (SceneState ss : s.getAllSceneStates().values()) {
					stateBox.addItem(ss);
				}
				sceneID = s.getID();
			}
		});

		stateBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					stateID = ((SceneState) stateBox.getSelectedItem()).getID();
			}
		});

		initVariable();
		repaint();
		setSize(new Dimension(600, 550));
		setMaximumSize(new Dimension(600, 550));
		setMinimumSize(new Dimension(600, 550));
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
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

//	public static void main(String[] args) {
//		GameProject gameProject = new GameProject("sss");
//		Scene scene1 = new Scene("scene1");
//		SceneState scenestate1 = new SceneState("scenestate1");
//		scene1.addSceneState(scenestate1);
//		gameProject.addScene(scene1);
//		Scene scene2 = new Scene("scene2");
//		SceneState scenestate2 = new SceneState("scenestate2");
//		scene2.addSceneState(scenestate2);
//		gameProject.addScene(scene2);
//		GameSetting gameSetting = new GameTemplate().getGameSettingAt(0);
//		gameSetting.setInitialScene(scene1.getID());
//		gameSetting.setButtonFontColor("WHITE");
//		gameSetting.setInitialState(scenestate1.getID());
//		gameProject.setGameSetting(gameSetting);
//		new GameSettingDialog(gameProject);
//	}

	private void createFileChooser(JTextField jtf) {
		
		JFileChooser fileChooser1 = new JFileChooser();
    	fileChooser1.setCurrentDirectory(new File("."));
        int returnValue1 = fileChooser1.showOpenDialog(null);
        if (returnValue1 == JFileChooser.APPROVE_OPTION) {
        	File selectedFile = fileChooser1.getSelectedFile();
        	jtf.setText(selectedFile.getAbsolutePath());
        }
	}
	
	@SuppressWarnings("rawtypes")
	class ComboBoxRenderer extends JPanel implements ListCellRenderer {

		private static final long serialVersionUID = -1L;
		private Color[] colors;
		private String[] strings;

		JPanel textPanel;
		JLabel text;

		public ComboBoxRenderer(JComboBox combo) {

			textPanel = new JPanel();
			textPanel.add(this);
			text = new JLabel();
			text.setOpaque(true);
			text.setFont(combo.getFont());
			textPanel.add(text);
		}

		public void setColors(Color[] col) {
			colors = col;
		}

		public void setStrings(String[] str) {
			strings = str;
		}

		public Color[] getColors() {
			return colors;
		}

		public String[] getStrings() {
			return strings;
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {

			if (isSelected) {
				Color c = new Color(255 - list.getSelectionBackground().getRed(),
						255 - list.getSelectionBackground().getGreen(), 255 - list.getSelectionBackground().getBlue());
				setBackground(c);
			} else {
				if (index > -1) {
					// Color c = new
					// Color(255-colors[index].getRed(),255-colors[index].getGreen(),255-colors[index].getBlue());
					setBackground(colors[index]);
				}
			}

			if (colors.length != strings.length) {
				System.out.println("colors.length does not equal strings.length");
				return this;
			} else if (colors == null) {
				System.out.println("use setColors first.");
				return this;
			} else if (strings == null) {
				System.out.println("use setStrings first.");
				return this;
			}

			text.setForeground(getBackground());

			text.setText(value.toString());
			if (index > -1) {
				// text.setForeground(colors[index]);
				text.setBackground(colors[index]);
			}
			return text;
		}
		
		
	}
}
