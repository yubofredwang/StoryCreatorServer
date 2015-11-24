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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import library.FontLibrary;
import library.ImageLibrary;
import popupWindow.OneSentenceTwoButton;
import popupWindow.OneTextFieldTwoButton;
import tip.storycreator.client.Constants;
import tip.storycreator.client.DefaultButton;
import tip.storycreator.game.GameContent;
import tip.storycreator.game.GameProject;
import tip.storycreator.game.Scene;
import tip.storycreator.game.SceneState;
import tip.storycreator.game.SceneStatePair;

public class SceneStateEditPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5120341053810471461L;

	private Scene mScene;
	private SceneState mState;
	private GameProject mGameProject;
	private InputHelper mInputHelper;
	private boolean CtrlPressed = false;
	private boolean AltPressed = false;

	private final static Image notifyImg = ImageLibrary
			.getImage(Constants.pathname + Constants.createfile + "ll" + Constants.png);

	public SceneStateEditPanel(Scene holdScene, GameProject holdProject, InputHelper holdHelper)// Scene
																								// holdScene)
	{
		mScene = holdScene;
		mState = mScene.getCurrentSceneState();
		mGameProject = holdProject;
		mInputHelper = holdHelper;
		createGUI();
	}
	
	private void createFileChooser(JTextField ImageInput) {
		
		JFileChooser fileChooser1 = new JFileChooser();
    	fileChooser1.setCurrentDirectory(new File("."));
        int returnValue1 = fileChooser1.showOpenDialog(null);
        if (returnValue1 == JFileChooser.APPROVE_OPTION) {
        	File selectedFile = fileChooser1.getSelectedFile();
        	ImageInput.setText(selectedFile.getAbsolutePath());
        }
	}

	private void createGUI() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// Top part
		JPanel topPanel = new JPanel();
		JPanel jPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		JPanel promptPanel = new JPanel(new GridLayout(3, 1, 10, 10));
		JPanel inputPanel = new JPanel(new GridLayout(3, 1));

		JLabel name = new JLabel("Name");
		JLabel description = new JLabel("Description");
		JLabel image = new JLabel("Image");

		JTextField nameInput = new JTextField(15);
		mInputHelper.setSceneStateInputListener(nameInput, mState, InputHelper.NAME);
		JTextField descriptionInput = new JTextField(15);
		mInputHelper.setSceneStateInputListener(descriptionInput, mState, InputHelper.DESCRIPTION);
		JTextField imageInput = new JTextField(15);
		mInputHelper.setSceneStateInputListener(imageInput, mState, InputHelper.IMAGE);

		JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
		JButton browseImageButton = new JButton("browse");
		browseImageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				createFileChooser(imageInput);
			}
		});
		buttonPanel.add(new JLabel(""));
		buttonPanel.add(new JLabel(""));
		buttonPanel.add(browseImageButton);

		promptPanel.add(name);
		promptPanel.add(description);
		promptPanel.add(image);

		inputPanel.add(nameInput);
		inputPanel.add(descriptionInput);
		inputPanel.add(imageInput);

		topPanel.add(Box.createHorizontalGlue());
		jPanel.add(promptPanel);
		jPanel.add(inputPanel);
		jPanel.add(buttonPanel);
		topPanel.add(jPanel);
		topPanel.add(Box.createHorizontalGlue());
		topPanel.setBorder(new TitledBorder("Properties"));
		JPanel topEmptyPanel = new JPanel(new BorderLayout());
		topEmptyPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		topEmptyPanel.add(topPanel, BorderLayout.CENTER);

		// Game Content Part
		JPanel centerPanel = new JPanel(new BorderLayout());

		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		JPanel c1Panel = new JPanel();
		c1Panel.setLayout(new BoxLayout(c1Panel, BoxLayout.X_AXIS));
		JLabel content = new JLabel("Game Contents in the current Scene: ");
		List<GameContent> list = new ArrayList<GameContent>();
		for (GameContent gc : mScene.getAllGameContents().values()) {
			list.add(gc);
		}
		GameContent[] gcs = list.toArray(new GameContent[list.size()]);
		JComboBox<GameContent> contentBox = new JComboBox<GameContent>(gcs);
		c1Panel.add(Box.createGlue());
		c1Panel.add(content);
		c1Panel.add(contentBox);
		c1Panel.add(Box.createGlue());

		JPanel c2Panel = new JPanel();
		c2Panel.setLayout(new BoxLayout(c2Panel, BoxLayout.X_AXIS));
		JButton addGameContentButton = new JButton("Add Selected");
		JButton createGameContentButton = new JButton("Create New");
		c2Panel.add(addGameContentButton);
		c2Panel.add(createGameContentButton);

		contentPanel.add(c1Panel);
		contentPanel.add(c2Panel);

		String[] contentColNames = new String[] { "GameContent Name", "GameContent Description" };
		DefaultTableModel contentTableModel = new DefaultTableModel(contentColNames, 0) {
			private static final long serialVersionUID = -59423502301006168L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		for (int i : mState.getGameContentChoices()) {
			GameContent tempgc = mScene.getGameContent(i);
			contentTableModel.addRow(new Object[] { tempgc, new GameContentInfor(tempgc) });
		}
		JTable contentTable = new JTable(contentTableModel);
		contentTable.setRowHeight(30);
		contentTable.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					CtrlPressed = false;
				} else if (e.getKeyCode() == KeyEvent.VK_ALT) {
					AltPressed = false;
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					CtrlPressed = true;
				} else if (e.getKeyCode() == KeyEvent.VK_ALT) {
					AltPressed = true;
				}
			}
		});
		contentTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (CtrlPressed) {
					int row = contentTable.rowAtPoint(e.getPoint());// get
																	// mouse-selected
																	// row
					int col = contentTable.columnAtPoint(e.getPoint());// get
																		// mouse-selected
					Object object = contentTableModel.getValueAt(row, col);
					if (object instanceof GameContent) {
						GameContent gc = (GameContent) object;
						OneSentenceTwoButton os1 = new OneSentenceTwoButton("Do you want to delete?",
								SceneStateEditPanel.this, "yes", "no", "Delete");
						int choice = os1.getContent();
						if (choice == 1) {
							--gc.usability;
							mState.removeGameContentChoice(gc.getID());
							contentTableModel.removeRow(row);
						}
					}
					CtrlPressed = false;
				} else if (AltPressed) {
					int row = contentTable.rowAtPoint(e.getPoint());// get
																	// mouse-selected
																	// row
					int col = contentTable.columnAtPoint(e.getPoint());// get
																		// mouse-selected
					Object object = contentTableModel.getValueAt(row, col);
					if (object instanceof GameContentInfor) {
						OneTextFieldTwoButton as = new OneTextFieldTwoButton(SceneStateEditPanel.this,
								"New description", "New description", "ok", "cancel");
						String choice = as.getContent();
						GameContent gc = ((GameContentInfor) object).getContent();
						if (choice != null)
							gc.setDescription(choice);
						contentTable.updateUI();
					} else if (object instanceof GameContent) {
						OneTextFieldTwoButton as = new OneTextFieldTwoButton(SceneStateEditPanel.this, "New Name",
								"New Name", "ok", "cancel");
						String choice = as.getContent();
						GameContent gc = (GameContent) object;
						if (choice != null)
							gc.setName(choice);
						contentTable.updateUI();
					}
					AltPressed = false;
				}
			}
		});
		JScrollPane centerjsp = new JScrollPane(contentTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		addGameContentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameContent tempgc = (GameContent) contentBox.getSelectedItem();
				mState.addGameContent(tempgc.getID());
				contentTableModel.addRow(new Object[] { tempgc, new GameContentInfor(tempgc) });
			}
		});
		createGameContentButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CreateGameContentDialog cgcd = new CreateGameContentDialog(SceneStateEditPanel.this);
				GameContent mGC = cgcd.getContent();
				if (mGC == null) {
					System.out.println("it's null!");
					return;
				} else {
					mScene.addGameContent(mGC);
					mState.addGameContent(mGC.getID());
					contentTableModel.addRow(new Object[] { mGC, new GameContentInfor(mGC) });
					contentBox.removeAllItems();
					for (GameContent gc : mScene.getAllGameContents().values()) {
						contentBox.addItem(gc);
					}
				}
			}
		});
		centerPanel.add(contentPanel, BorderLayout.NORTH);
		centerPanel.add(centerjsp, BorderLayout.CENTER);
		centerPanel.setBorder(new TitledBorder("GameContent"));
		JPanel centerEmptyPanel = new JPanel(new BorderLayout());
		centerEmptyPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		centerEmptyPanel.add(centerPanel, BorderLayout.CENTER);

		// Linked Scene
		// -----------------------------------------------------------------------
		// -----------------------------------------------------------------------
		// -----------------------------------------------------------------------
		JPanel bottomPanel = new JPanel(new BorderLayout());

		JPanel lsPanel = new JPanel();
		lsPanel.setLayout(new BoxLayout(lsPanel, BoxLayout.Y_AXIS));
		JPanel l1Panel = new JPanel();
		l1Panel.setLayout(new BoxLayout(l1Panel, BoxLayout.X_AXIS));
		JLabel linkedSceneLabel = new JLabel("Buttons in the Current Scene: ");
		List<LinkedSceneInfor> list1 = new ArrayList<LinkedSceneInfor>();
		for (SceneStatePair ssp : mScene.getAllSceneStatePairs().values()) {
			list1.add(new LinkedSceneInfor(mGameProject, ssp));
		}
		LinkedSceneInfor[] lsis = list1.toArray(new LinkedSceneInfor[list1.size()]);
		JComboBox<LinkedSceneInfor> linkedListBox = new JComboBox<LinkedSceneInfor>(lsis);
		l1Panel.add(Box.createGlue());
		l1Panel.add(linkedSceneLabel);
		l1Panel.add(linkedListBox);
		l1Panel.add(Box.createGlue());

		JPanel l2Panel = new JPanel();
		l2Panel.setLayout(new BoxLayout(l2Panel, BoxLayout.X_AXIS));
		JButton addlinkedSceneButton = new JButton("Add Selected");
		JButton createlinkedSceneButton = new JButton("Create New");
		l2Panel.add(addlinkedSceneButton);
		l2Panel.add(createlinkedSceneButton);

		lsPanel.add(l1Panel);
		lsPanel.add(l2Panel);

		String[] lsColumnNames = new String[] { "Button Description", "Button Information" };

		DefaultTableModel lsTableModel = new DefaultTableModel(lsColumnNames, 0) {
			private static final long serialVersionUID = -59423502301006168L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		for (int i : mState.getSceneChoices()) {
			SceneStatePair ssp = mScene.getSceneStatePair(i);
			lsTableModel.addRow(new Object[] { ssp, new LinkedSceneInfor(mGameProject, ssp) });
		}
		JTable lsTable = new JTable(lsTableModel);
		lsTable.setRowHeight(30);
		lsTable.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					CtrlPressed = false;
				} else if (e.getKeyCode() == KeyEvent.VK_ALT) {
					AltPressed = false;
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					CtrlPressed = true;
				} else if (e.getKeyCode() == KeyEvent.VK_ALT) {
					AltPressed = true;
				}
			}
		});
		lsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (CtrlPressed) {
					int row = lsTable.rowAtPoint(e.getPoint());// get
																// mouse-selected
																// row
					int col = lsTable.columnAtPoint(e.getPoint());// get
																	// mouse-selected
					Object object = lsTableModel.getValueAt(row, col);
					if (object instanceof SceneStatePair) {
						SceneStatePair ssp = (SceneStatePair) object;
						OneSentenceTwoButton os2 = new OneSentenceTwoButton("Do you want to delete?",
								SceneStateEditPanel.this, "yes", "no", "Delete");
						int choice = os2.getContent();
						if (choice == 1) {
							--ssp.usability;
							mState.removelinkedScene(ssp.getID());
							lsTableModel.removeRow(row);
						}
					}
					CtrlPressed = false;
				} else if (AltPressed) {
					int row = lsTable.rowAtPoint(e.getPoint());// get
																// mouse-selected
																// row
					int col = lsTable.columnAtPoint(e.getPoint());// get
																	// mouse-selected
					Object object = lsTableModel.getValueAt(row, col);
					if (object instanceof SceneStatePair) {
						OneTextFieldTwoButton as = new OneTextFieldTwoButton(SceneStateEditPanel.this,
								"New description", "New description", "ok", "cancel");
						String choice = as.getContent();
						SceneStatePair ssp = ((SceneStatePair) object);
						if (choice != null)
							ssp.setDescription(choice);
						lsTable.updateUI();
					} else if (object instanceof LinkedSceneInfor) {
						SceneStateUpdateDialog ssup = new SceneStateUpdateDialog(mGameProject,
								SceneStateEditPanel.this);
						int[] choice = ssup.getContent();// JOptionPane.showInputDialog("New
															// Name");
						LinkedSceneInfor lsi = ((LinkedSceneInfor) object);
						SceneStatePair ssp = lsi.getContent();
						if (choice[0] != -1 && choice[1] != -1) {
							ssp.sceneID = choice[0];
							ssp.stateID = choice[1];
						}
						lsTable.updateUI();
					}
					AltPressed = false;
				}
			}
		});
		JScrollPane bottomjsp = new JScrollPane(lsTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		bottomPanel.add(lsPanel, BorderLayout.NORTH);
		bottomPanel.add(bottomjsp, BorderLayout.CENTER);
		addlinkedSceneButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LinkedSceneInfor lsi = (LinkedSceneInfor) linkedListBox.getSelectedItem();
				SceneStatePair tempssp = lsi.getContent();
				mState.addSceneID(tempssp.getID());
				lsTableModel.addRow(new Object[] { tempssp, new LinkedSceneInfor(mGameProject, tempssp) });
			}
		});
		createlinkedSceneButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SceneStateCreateDialog sscd = new SceneStateCreateDialog(mGameProject, SceneStateEditPanel.this);
				String[] strings = sscd.getContent();
				if (strings[0].equals("-1") || strings[1].equals("-1") || strings[2].equals("")) {
					JOptionPane.showMessageDialog(SceneStateEditPanel.this, "Invalid Input", "Error",
							JOptionPane.PLAIN_MESSAGE, new ImageIcon(notifyImg));
				} else {
					SceneStatePair sceneStatePair = new SceneStatePair(Integer.parseInt(strings[0]),
							Integer.parseInt(strings[1]));
					sceneStatePair.setDescription(strings[2]);
					mScene.addlinkedScenes(sceneStatePair);
					mState.addSceneID(sceneStatePair.getID());
					lsTableModel.addRow(
							new Object[] { sceneStatePair, new LinkedSceneInfor(mGameProject, sceneStatePair) });
					linkedListBox.removeAllItems();
					for (SceneStatePair ssp : mScene.getAllSceneStatePairs().values()) {
						linkedListBox.addItem(new LinkedSceneInfor(mGameProject, ssp));
					}
				}

			}
		});
		bottomPanel.setBorder(new TitledBorder("All Scenes Linked to the Current State"));
		JPanel bottomEmptyPanel = new JPanel(new BorderLayout());
		bottomEmptyPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		bottomEmptyPanel.add(bottomPanel, BorderLayout.CENTER);

		JPanel testp = new JPanel();
		JButton test = new JButton("test");
		test.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				checkAll();
			}
		});
		testp.add(test);

		add(topEmptyPanel);
		add(centerEmptyPanel);
		add(bottomEmptyPanel);
		add(testp);
	}

	public class LinkedSceneInfor {
		private GameProject mGameProject;
		private SceneStatePair mSceneStatePair;

		public LinkedSceneInfor(GameProject gp, SceneStatePair ssp) {
			mGameProject = gp;
			mSceneStatePair = ssp;
		}

		public SceneStatePair getContent() {
			return mSceneStatePair;
		}

		public String toString() {
			Scene scene = mGameProject.getScene(mSceneStatePair.sceneID);
			SceneState state = scene.getSceneState(mSceneStatePair.stateID);
			return "Button: " + mSceneStatePair.getDescription() + " leads to: (Scene: ) " + scene.getName()
					+ " (at State: ) " + state.getName();
		}
	}

	public class GameContentInfor {
		private GameContent mGameContent;

		public GameContentInfor(GameContent gc) {
			mGameContent = gc;
		}

		public GameContent getContent() {
			return mGameContent;
		}

		public String toString() {
			return mGameContent.lookUp();
		}
	}

	public class SceneStateCreateDialog {
		private int SceneID = -1;
		private int StateID = -1;
		private String desString = null;
		final Font font = FontLibrary.getFont(Constants.pathname + "fonts/System San Francisco Display Regular.ttf",
				Font.PLAIN, 18);
		final Font font1 = FontLibrary.getFont(Constants.pathname + "fonts/System San Francisco Display Regular.ttf",
				Font.PLAIN, 15);
		Color color = new Color(35, 152, 208);

		public SceneStateCreateDialog(GameProject mGameProject, Component c) {
			JDialog mainPanel = new JDialog();
			mainPanel.setTitle("Ceate New Button");
			mainPanel.setModal(true);
			UIManager.put("Label.font", font);
			UIManager.put("TextField.font", font1);
			mainPanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			mainPanel.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					SceneID = StateID = -1;
					super.windowClosing(e);
				}
			});

			JPanel inputPanel = new JPanel();
			JLabel promptLabel = new JLabel("Button Links");
			Border emptyBorder = BorderFactory.createEmptyBorder(0, 30, 0, 0);
			promptLabel.setBorder(emptyBorder);
			promptLabel.setForeground(color);
			inputPanel.setLayout(new GridBagLayout());

			GridBagConstraints d = new GridBagConstraints();
			d.fill = GridBagConstraints.HORIZONTAL;
			d.gridx = 0;
			d.gridy = 0;
			d.ipadx = 30;
			d.ipady = 10;
			inputPanel.add(promptLabel, d);
			List<Scene> list = new ArrayList<Scene>();
			for (Scene s : mGameProject.getSceneMap().values()) {
				list.add(s);
			}
			Scene[] sceneList = list.toArray(new Scene[list.size()]);
			JComboBox<Scene> sceneBox = new JComboBox<Scene>(sceneList);
			sceneBox.setSelectedItem(null);
			JComboBox<SceneState> stateBox = new JComboBox<SceneState>();
			stateBox.setForeground(color);
			sceneBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					Scene s = (Scene) sceneBox.getSelectedItem();
					stateBox.setSelectedIndex(-1);
					stateBox.removeAllItems();
					for (SceneState ss : s.getAllSceneStates().values()) {
						stateBox.addItem(ss);
					}
					SceneID = s.getID();
				}
			});

			stateBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(stateBox.getSelectedItem() != null)
					{
						StateID = ((SceneState) stateBox.getSelectedItem()).getID();
					}
				}
			});

			sceneBox.setForeground(color);
			d.gridx = 1;
			d.gridy = 0;
			JLabel jl = new JLabel("Scene:");
			jl.setForeground(color);
			inputPanel.add(jl, d);
			d.gridx = 2;
			d.gridy = 0;
			inputPanel.add(sceneBox, d);
			d.gridx = 3;
			d.gridy = 0;
			JLabel jl1 = new JLabel("State:");
			jl1.setForeground(color);
			inputPanel.add(jl1, d);
			d.gridx = 4;
			d.gridy = 0;
			inputPanel.add(stateBox, d);
			emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 30);
			stateBox.setBorder(emptyBorder);

			// inputPanel.add(promptLabel);
			// inputPanel.add(sceneBox);
			// inputPanel.add(stateBox);

			// JPanel desPanel = new JPanel();
			JLabel desLabel = new JLabel("Description: ");
			emptyBorder = BorderFactory.createEmptyBorder(0, 30, 0, 0);
			desLabel.setBorder(emptyBorder);
			desLabel.setForeground(color);
			JTextField desField = new JTextField(15);
			desField.setForeground(color);
			d.gridx = 0;
			d.gridy = 1;
			inputPanel.add(desLabel, d);
			d.gridx = 1;
			d.gridy = 1;
			d.gridwidth = 4;
			inputPanel.add(desField, d);
			//emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 30);
			//desField.setBorder(emptyBorder);
			// desPanel.add(desLabel);
			// desPanel.add(desField);

			JPanel bottomPanel = new JPanel();
			JButton confirmButton = new DefaultButton("Confirm",
					ImageLibrary.getImage("resources/create_file/blue_button_light.png"),
					ImageLibrary.getImage("resources/create_file/blue_button_pressed.png"));
			confirmButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					desString = desField.getText();
					mainPanel.dispose();
				}
			});
			emptyBorder = BorderFactory.createEmptyBorder(0, 0, 30, 0);
			bottomPanel.setBorder(emptyBorder);
			mainPanel.getRootPane().setDefaultButton(confirmButton);
			bottomPanel.add(confirmButton);

			mainPanel.setLayout(new BoxLayout(mainPanel.getContentPane(), BoxLayout.Y_AXIS));
			inputPanel.setBackground(Color.WHITE);
			mainPanel.add(inputPanel);
			// mainPanel.add(desPanel);
			bottomPanel.setBackground(Color.WHITE);
			mainPanel.add(bottomPanel);
			mainPanel.setBackground(Color.WHITE);
			mainPanel.setMinimumSize(new Dimension(650, 250));
			mainPanel.setMaximumSize(new Dimension(650, 250));
			
			mainPanel.setLocationRelativeTo(null);
			//mainPanel.pack();
			mainPanel.setVisible(true);
		}

		public String[] getContent() {
			return new String[] { Integer.toString(SceneID), Integer.toString(StateID), desString };
		}
	}

	public class SceneStateUpdateDialog {
		private int SceneID = -1;
		private int StateID = -1;

		public SceneStateUpdateDialog(GameProject mGameProject, Component c) {
			final Font font = FontLibrary.getFont(Constants.pathname + "fonts/System San Francisco Display Regular.ttf",
					Font.PLAIN, 18);
			Color color = new Color(35, 152, 208);
			UIManager.put("Label.font", font);
			UIManager.put("TextField.font", font);
			JDialog mainPanel = new JDialog();
			mainPanel.setMinimumSize(new Dimension(650, 250));
			mainPanel.setMaximumSize(new Dimension(650, 250));
			mainPanel.setTitle("Ceate New Button");
			mainPanel.setModal(true);
			mainPanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			mainPanel.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					SceneID = StateID = -1;
					super.windowClosing(e);
				}
			});

			JPanel inputPanel = new JPanel();
			JLabel promptLabel = new JLabel("Button Links");
			promptLabel.setForeground(color);
			List<Scene> list = new ArrayList<Scene>();
			for (Scene s : mGameProject.getSceneMap().values()) {
				list.add(s);
			}
			Scene[] sceneList = list.toArray(new Scene[list.size()]);
			JComboBox<Scene> sceneBox = new JComboBox<Scene>(sceneList);
			sceneBox.setSelectedItem(null);
			JComboBox<SceneState> stateBox = new JComboBox<SceneState>();

			sceneBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					Scene s = (Scene) sceneBox.getSelectedItem();
					stateBox.setSelectedIndex(-1);
					stateBox.removeAllItems();
					for (SceneState ss : s.getAllSceneStates().values()) {
						stateBox.addItem(ss);
					}
					SceneID = s.getID();
				}
			});

			stateBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED)
						StateID = ((SceneState) stateBox.getSelectedItem()).getID();
				}
			});
			stateBox.setForeground(color);
			sceneBox.setForeground(color);
			GridBagConstraints d = new GridBagConstraints();
			inputPanel.setLayout(new GridBagLayout());
			d.fill = GridBagConstraints.HORIZONTAL;
			d.gridx = 0;
			d.gridy = 0;
			d.ipadx = 30;
			d.ipady = 10;
			inputPanel.add(promptLabel, d);
			d.gridx = 1;
			d.gridy = 0;
			JLabel jl = new JLabel("Scene:");
			jl.setForeground(color);
			inputPanel.add(jl, d);
			d.gridx = 2;
			d.gridy = 0;
			inputPanel.add(sceneBox, d);
			d.gridx = 3;
			d.gridy = 0;
			JLabel jl1 = new JLabel("State:");
			jl1.setForeground(color);
			inputPanel.add(jl1, d);
			d.gridx = 4;
			d.gridy = 0;
			inputPanel.add(stateBox, d);

			JPanel bottomPanel = new JPanel();
			JButton confirmButton = new DefaultButton("Confirm",
					ImageLibrary.getImage("resources/create_file/blue_button_light.png"),
					ImageLibrary.getImage("resources/create_file/blue_button_pressed.png"));
			confirmButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mainPanel.dispose();
				}
			});
			Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 30, 0);
			//confirmButton.setBorder(emptyBorder);
			mainPanel.getRootPane().setDefaultButton(confirmButton);
			bottomPanel.add(confirmButton);
			bottomPanel.setBorder(emptyBorder);
			mainPanel.setLayout(new BoxLayout(mainPanel.getContentPane(), BoxLayout.Y_AXIS));
			mainPanel.add(inputPanel);
			mainPanel.add(bottomPanel);
			inputPanel.setBackground(Color.WHITE);
			bottomPanel.setBackground(Color.WHITE);
			mainPanel.setBackground(Color.WHITE);
			mainPanel.setLocationRelativeTo(null);
			mainPanel.setVisible(true);
		}

		public int[] getContent() {
			return new int[] { SceneID, StateID };
		}
	}

	public void checkAll() {
		for (Scene s : mGameProject.getSceneMap().values()) {
			System.out.println("Scene Name: " + s.getName() + " ID: " + s.getID());
			for (GameContent gc : s.getAllGameContents().values()) {
				System.out.println("GameContent Name: " + gc.getName() + " ID: " + gc.getID());
			}
			for (SceneStatePair ssp : s.getAllSceneStatePairs().values()) {
				Scene scene = mGameProject.getScene(ssp.sceneID);
				SceneState state = scene.getSceneState(ssp.stateID);
				System.out.println("Pair: " + ssp.getDescription() + " leads to: (Scene: ) " + scene.getName()
						+ " (at State: ) " + state.getName());
			}
			for (SceneState st : s.getAllSceneStates().values()) {
				System.out.println("SceneState Name: " + st.getName() + " ID: " + st.getID());
				for (int i : st.getGameContentChoices()) {
					System.out.println("  containd gamecontent: " + s.getGameContent(i));
				}
				for (int i : st.getSceneChoices()) {
					System.out.println("  contained pair: " + s.getSceneStatePair(i).sceneID + " "
							+ s.getSceneStatePair(i).stateID);
				}
			}
		}
	}
}
