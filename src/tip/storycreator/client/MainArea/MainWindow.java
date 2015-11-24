package tip.storycreator.client.MainArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import library.FontLibrary;
import library.ImageLibrary;
import popupWindow.OneSentenceTwoButton;
import popupWindow.OneTextFieldTwoButton;
import tip.storycreator.client.ClientListener;
import tip.storycreator.client.Constants;
import tip.storycreator.client.DefaultButton;
import tip.storycreator.client.StoryCreatorClientWindow;
import tip.storycreator.client.StoryCreatorClientWindow.BackToCreateFile;
import tip.storycreator.game.GameContent;
import tip.storycreator.game.GameProject;
import tip.storycreator.game.GameSetting;
import tip.storycreator.game.Scene;
import tip.storycreator.game.SceneState;
import tip.storycreator.game.SceneStatePair;
import tip.storycreator.game.gameGUI.GameFrame;
import tip.storycreator.game.gameGUI.GamePanel;
import tip.storycreator.server.User;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = -6662858887829446884L;
	private JPanel mainPanel;
	private JPanel firstPanel;
	private JPanel upPanel;
	private JPanel bottomPanel;
	private JPanel secondPanel;
	private JPanel thirdPanel;
    private static String savedFilePath;
   // private GameProject mGameProject;
    //ArrayList<String> sceneList;
	private static Image notifyImg;
	private static Image playImg;
	private static Image stopImg;
	private static Image treeIcon1;
	private static Image treeIcon2;
	
	private GameProject mGameProject;
	private User mUser;
	
	private JScrollPane hierarchyContentPanel;
	private JTree hierarchyTree;
	private DefaultTreeModel defaultTreeModel;
	private CustomTreeCellRenderer ctcr;
	
	private JTabbedPane workPanel;
	private JPanel previewPanel;
	private JPanel editPanel;
	
	private UpdateTool mUpdateTool;
	private InputHelper mInputHelper;
	
	private JPanel settingContainer;
	private GameSetting mGameSetting;
	private final Dimension dSize = new Dimension(859,723);
	
	private JFrame thisWindow;
	
	private Scene ownScene = null;
	
	private ClientListener mClientListener;
	
	private GameFrame myFrame = null;
	ArrayList<ObjectLocationPanel> panelList = null;
	private BackToCreateFile mAction;
	
	@SuppressWarnings("rawtypes")
	private static void setUIFont(javax.swing.plaf.FontUIResource f)
	{   
	    java.util.Enumeration keys = UIManager.getDefaults().keys();
	    while(keys.hasMoreElements())
	    {
	        Object key = keys.nextElement();
	        Object value = UIManager.get(key);
	        if(value instanceof javax.swing.plaf.FontUIResource) UIManager.put(key, f);
	    }
	}
	
	private void saveToFileMethod(int choice) {
		if(choice == 1)
		{
			if(savedFilePath == null)
			{
				JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setCurrentDirectory(new File("."));
		        int returnValue = fileChooser.showSaveDialog(fileChooser);
		        if (returnValue == JFileChooser.APPROVE_OPTION) {
					File f = fileChooser.getSelectedFile();
					ObjectOutputStream oos;
					try {
						oos = new ObjectOutputStream(new FileOutputStream(f));
						oos.writeObject(mGameProject);
						oos.flush();
						oos.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					savedFilePath = f.getAbsolutePath();
		        }
			}
			else
			{
				File file = new File(savedFilePath);
				file.delete();
				File file2 = new File(savedFilePath);
				try
				{
					file2.createNewFile();
				}
				catch(IOException e1)
				{
					e1.printStackTrace();
				}

				try
				{
					ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(file2));
					ous.writeObject(mGameProject);
					ous.close();
				}
				catch(FileNotFoundException e2)
				{
					e2.printStackTrace();
				}
				catch(IOException e4)
				{
					e4.printStackTrace();
				}
			}
		}
        else if(choice == 0)
		{
			SaveRemote(mGameProject);
		}
	}
		
	private class NewFileActionListener implements ActionListener {
   	    public void actionPerformed(ActionEvent e) {
   	    	MainWindow.this.dispose();
   	    	JButton temp = new JButton("");
   	    	mAction.setUser(mUser);
   	    	temp.addActionListener(mAction);
   	    	temp.doClick();
   	    	
   	    }
	}
	    
	private class SaveActionListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	    	OneSentenceTwoButton ct = new OneSentenceTwoButton("Please Save Your Current File", null, "Local", "Remote", "Save");
	    	saveToFileMethod(ct.getContent());
	    }
	}
	
	private class OpenActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
	        JFileChooser fileChooser1 = new JFileChooser();
	        fileChooser1.setCurrentDirectory(new File("."));
	        int returnValue1 = fileChooser1.showOpenDialog(null);
	        if (returnValue1 == JFileChooser.APPROVE_OPTION) {
	        	File selectedFile = fileChooser1.getSelectedFile();
	          	System.out.println(selectedFile.getName());
				try {
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(selectedFile));
					GameProject emp1;
					emp1 = (GameProject)ois.readObject();
					mGameProject = emp1;
					ois.close();
					refreshFrame(mGameProject);
					System.out.println(mGameProject.getName());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch(IOException ioe) {
					ioe.printStackTrace();
				}
	        }
		}
	}
	
	private class CloseActionListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	    	OneSentenceTwoButton oTwoButton = new OneSentenceTwoButton("Do you want to save before closing the file?", MainWindow.this, "Not Save", "Save Please", "Close");
	    	if(oTwoButton.getContent() == 1)
	    	{
	    		/**
	    		 * Need more
	    		 */
	    		MainWindow.this.dispose();
	    		System.exit(0);
	    	}
	    	else if(oTwoButton.getContent() == 0)
	    	{
		    	OneSentenceTwoButton ct = new OneSentenceTwoButton("Please Save Your Current File", null, "Local", "Remote", "Save");
		    	saveToFileMethod(ct.getContent());
	    		MainWindow.this.dispose();
	    		System.exit(0);
	    	}
	    }
	}

	private class HelpMenuActionListener implements ActionListener {
		
		@SuppressWarnings("unused")
		Component parent;
		HelpMenuActionListener(Component parent) { this.parent = parent; }
		@Override
		public void actionPerformed(ActionEvent e) {
			new Help();
			// TODO Auto-generated method stub
			Help.display();
		}
		
	}
	
	private void initializeGUI() {
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
//		mainPanel.setBackground(Color.lightGray);
		
		firstPanel = new JPanel(new BorderLayout());
		firstPanel.setPreferredSize(new Dimension(250, mainPanel.getHeight()));
		firstPanel.setBorder(new EmptyBorder(10,10, 10, 10));
		upPanel = new JPanel(new BorderLayout());
		upPanel.setOpaque(false);
		
		//bottomPanel = new JPanel(new BorderLayout());
		
//		JLabel gameContentLabel = new JLabel("Game Content", SwingConstants.CENTER);
//		gameContentLabel.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.gray));
//		bottomPanel.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.gray));
//		bottomPanel.add(gameContentLabel, BorderLayout.NORTH);
//		gameContentLabel.setBackground(Color.lightGray);
//		gameContentLabel.setForeground(Color.WHITE);
//		gameContentLabel.setOpaque(true);
		
		//bottomPanel.setBackground(Color.WHITE);
		firstPanel.add(upPanel);
		//firstPanel.add(bottomPanel);
		firstPanel.setOpaque(false);
		
		secondPanel = new JPanel(new BorderLayout());
		secondPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		secondPanel.setOpaque(false);
		
		//outline panel
		thirdPanel = new JPanel(new BorderLayout());
		thirdPanel.setBackground(Color.darkGray);
		thirdPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		thirdPanel.setPreferredSize(new Dimension(250, mainPanel.getHeight()));
		thirdPanel.setOpaque(false);
		
		mainPanel.add(firstPanel, BorderLayout.WEST);
		mainPanel.add(secondPanel, BorderLayout.CENTER);
		mainPanel.add(thirdPanel, BorderLayout.EAST);
		add(mainPanel, BorderLayout.CENTER);
		
	}
	
	private void createMenu() {
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem createNewMenuItem = new JMenuItem("New");	
		createNewMenuItem.addActionListener(new NewFileActionListener());
		JMenuItem openMenuItem = new JMenuItem("Open File...");
		openMenuItem.addActionListener(new OpenActionListener());
		JMenuItem closeMenuItem = new JMenuItem("Close");
		closeMenuItem.addActionListener(new CloseActionListener());
		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.addActionListener(new SaveActionListener());
		fileMenu.add(createNewMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(closeMenuItem);
		fileMenu.add(saveMenuItem);
		JMenu projectMenu = new JMenu("Project");
		JMenuItem gameSettingMenuItem = new JMenuItem("Game Setting...");
		gameSettingMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GameSettingDialog gsd = new GameSettingDialog(mGameProject);
			}
		});
		projectMenu.add(gameSettingMenuItem);
		JMenu objectMenu = new JMenu("Object");
		JMenuItem addPlayerMenuItem = new JMenuItem("Player");
		addPlayerMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("------PLAYER NAME: " + mGameProject.getPlayer().getName());
				if(mGameProject.getPlayer() != null) {
					new PlayerInfoDialog(mGameProject.getPlayer().getName());
				}
				else {
					PlayerDialog pd = new PlayerDialog();
					mGameProject.setPlayer(pd.getContent());
				}
			}
		});
		objectMenu.add(addPlayerMenuItem);
		JMenu helpMenu = new JMenu("Help");
		JMenuItem tutorialMenuItem = new JMenuItem("Tutorial");
		ActionListener helpAL = new HelpMenuActionListener(this);
		tutorialMenuItem.addActionListener(helpAL);
		helpMenu.add(tutorialMenuItem);
		
		menuBar.add(fileMenu);
		menuBar.add(projectMenu);
		menuBar.add(objectMenu);
		menuBar.add(helpMenu);
		add(menuBar, BorderLayout.NORTH);
		
	}
	
	private void addNewScene() {
		OneTextFieldTwoButton os = new OneTextFieldTwoButton(thisWindow);
		String sceneName = os.getContent();
		if(sceneName != null) {
			Scene newScene = new Scene(sceneName);
	//		SceneState defaultState = new SceneState("Default State");
			mGameProject.addScene(newScene);
	//		newScene.addSceneState(defaultState);
			DefaultMutableTreeNode sceneRootNode = new DefaultMutableTreeNode(newScene);
	//		DefaultMutableTreeNode defaultStateNode = new DefaultMutableTreeNode(defaultState);
			DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) hierarchyTree.getModel().getRoot();
			addNodeToDefaultTreeModel(defaultTreeModel, rootNode, sceneRootNode);
	//		addNodeToDefaultTreeModel(defaultTreeModel, sceneRootNode, defaultStateNode);
				
		}

	}
	
	private void addNewState(Object selectedNode) {
		OneTextFieldTwoButton otf = new OneTextFieldTwoButton(null, "State Name:", "Add New State", "ok", "cancel");
		String stateName = otf.getContent();
		if(stateName != null) {
			SceneState newState = new SceneState(stateName);
			newState.setDescription("Default Description");
			DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) selectedNode;
			Scene parentSc = (Scene) rootNode.getUserObject();
			parentSc.addSceneState(newState);
			DefaultMutableTreeNode sceneRootNode = new DefaultMutableTreeNode(newState);
			addNodeToDefaultTreeModel( defaultTreeModel, rootNode, sceneRootNode );
			((SceneEditPanel)editPanel.getComponent(0)).refreshST();//getComponentAt(20, 20)).refreshST();
		}
	}
	
	private void addNodeToDefaultTreeModel(DefaultTreeModel treeModel, DefaultMutableTreeNode parentNode, DefaultMutableTreeNode node) {
		
		treeModel.insertNodeInto(node, parentNode, parentNode.getChildCount());
		
		if (parentNode == treeModel.getRoot()) {
			treeModel.nodeStructureChanged((TreeNode) treeModel.getRoot());
		}
	}
	
	private void createHierarchyPanel() {
		
		//play/stop Button
		JPanel topPanel = new JPanel(new GridLayout(2, 1));
		JPanel playPanel = new JPanel(new GridLayout(1, 2));
//		playPanel.setBackground(Color.WHITE);
		playPanel.setOpaque(false);
		JButton playButton = new JButton(new ImageIcon(playImg));
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(mGameProject.getGameSetting().getInitialScene() != -1 && mGameProject.getGameSetting().getInitialState() != -1)
				{
					myFrame = new GameFrame(mGameProject);
					myFrame.setVisible(true);
				}
			}
		});
		playButton.setBorder(null);
		playButton.setOpaque(false);
		JButton stopButton = new JButton(new ImageIcon(stopImg));
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(myFrame != null)
				{
					myFrame.dispose();
				}
			}
		});
		stopButton.setBorder(null);
		stopButton.setOpaque(false);
//		stopButton.setHorizontalAlignment(SwingConstants.LEFT);
		playPanel.add(playButton);
		playPanel.add(stopButton);
		DefaultButton addSceneButton = new DefaultButton("Add Scene", 
				  ImageLibrary.getImage("resources/main_window/blue_button.png"),
				  ImageLibrary.getImage("resources/main_window/blue_button_pressed.png"),
				  230, 25);
		topPanel.add(playPanel);
		topPanel.add(addSceneButton);
		upPanel.add(topPanel, BorderLayout.NORTH);
		//hierarchy Panel
		JPanel hierarchyPanel = new JPanel(new BorderLayout());
		hierarchyPanel.setBackground(Color.WHITE);
		hierarchyPanel.setBorder(BorderFactory.createMatteBorder(2,1,1,1,Color.gray));
		JLabel hierarchyLabel = new JLabel("Hierarchy", SwingConstants.CENTER);
		hierarchyLabel.setBorder(BorderFactory.createMatteBorder(0,1,1,1,Color.gray));
		hierarchyLabel.setForeground(Color.white);
		hierarchyLabel.setBackground(Color.lightGray);
		hierarchyLabel.setOpaque(true);
		//addSceneButton.setBorder(BorderFactory.createMatteBorder(0,1,0,1,Color.BLACK));
		hierarchyPanel.add(hierarchyLabel, BorderLayout.NORTH);
		//hierarchy Tree
		
		
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Hierarchy");
		hierarchyTree = new JTree(rootNode);
		ctcr = new CustomTreeCellRenderer();
	//	ctcr.setRendererIcon(new ImageIcon(treeIcon1));
		hierarchyTree.setCellRenderer(ctcr);
		hierarchyContentPanel = new JScrollPane(hierarchyTree);
		defaultTreeModel = new DefaultTreeModel(rootNode);
		hierarchyTree.setModel(defaultTreeModel);

		for(Scene s : mGameProject.getSceneMap().values()) {
			DefaultMutableTreeNode sceneNode = new DefaultMutableTreeNode(s);
			for(SceneState ss : s.getAllSceneStates().values()) {
				DefaultMutableTreeNode stateNode = new DefaultMutableTreeNode(ss);
				addNodeToDefaultTreeModel(defaultTreeModel, sceneNode, stateNode);
			}
			addNodeToDefaultTreeModel(defaultTreeModel, rootNode, sceneNode);
		}
		System.out.println("--------------roll count: " + hierarchyTree.getRowCount());



		hierarchyTree.addMouseListener ( new MouseAdapter ()
	    {
	        public void mousePressed ( MouseEvent e )
	        {
	            if ( SwingUtilities.isRightMouseButton ( e ) )
	            {
	                TreePath path = hierarchyTree.getPathForLocation ( e.getX (), e.getY () );
	                Rectangle pathBounds = hierarchyTree.getUI ().getPathBounds ( hierarchyTree, path );
	                if ( pathBounds != null && pathBounds.contains ( e.getX (), e.getY () ) )
	                {
	               		Object selectedNode = hierarchyTree.getLastSelectedPathComponent();
	               		if(selectedNode instanceof DefaultMutableTreeNode) {
							//type new_name = (type) ;
	               			int nodeLevel = ((DefaultMutableTreeNode) selectedNode).getLevel();
	               			if(nodeLevel == 1) {
	               				JPopupMenu menu = new JPopupMenu ();
		               			JMenuItem deleteScene = new JMenuItem("Delete Scene");
		               			deleteScene.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										OneSentenceTwoButton os = new OneSentenceTwoButton("Are you sure you want to delete the scene?", MainWindow.this, "no", "yes", "Delete Scene");
										int res = os.getContent();
										if(res == 0) {
											Object obj = ((DefaultMutableTreeNode) selectedNode).getUserObject();
											if(obj instanceof Scene) {
												Scene sc = (Scene) obj;
												mGameProject.removeScene(sc.getID());
												int id = sc.getID();
												for(Scene s: mGameProject.getSceneMap().values())
												{
													for(SceneStatePair ssp: s.getAllSceneStatePairs().values())
													{
														if(ssp.sceneID == id)
														{
															s.removelinkedScenes(ssp.getID());
														}
													}
												}
											}
											defaultTreeModel.removeNodeFromParent((MutableTreeNode) selectedNode);
											ownScene = null;
										}
										
									}
								});
		               			
		               			
		               			JMenuItem addState = new JMenuItem("Add State");
		               			addState.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										addNewState(selectedNode);
										
									}
								});
			                    menu.add(addState);
			                    menu.add(deleteScene);
			                    menu.show ( hierarchyTree, pathBounds.x, pathBounds.y + pathBounds.height );
	               			}
	               			else if(nodeLevel == 2) {
	               				JPopupMenu menu = new JPopupMenu ();
		               			JMenuItem deleteState = new JMenuItem("Delete State");
		               			deleteState.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the state?", "Delete State?", JOptionPane.YES_NO_OPTION, 0, new ImageIcon(notifyImg));
										if(res == 0) {
											
											DefaultMutableTreeNode parentScene = (DefaultMutableTreeNode) ((DefaultMutableTreeNode) selectedNode).getParent();
											if(parentScene.getUserObject() instanceof Scene) {
												Scene sc = (Scene) parentScene.getUserObject();
												SceneState ss = (SceneState) ((DefaultMutableTreeNode) selectedNode).getUserObject();
												sc.removeSceneState(ss.getID());
												int id = ss.getID();
												for(Scene s: mGameProject.getSceneMap().values())
												{
													for(SceneStatePair ssp: s.getAllSceneStatePairs().values())
													{
														if(ssp.stateID == id)
														{
															s.removelinkedScenes(ssp.getID());
														}
													}
												}
											}
											defaultTreeModel.removeNodeFromParent((MutableTreeNode) selectedNode);
										}
										
									}
								});
		               			
		               			
			                    menu.add(deleteState);
			                    menu.show ( hierarchyTree, pathBounds.x, pathBounds.y + pathBounds.height );
	               			}	
						} 
	                }
	            }
	            else if(SwingUtilities.isLeftMouseButton ( e ) ) {
            	  TreePath path = hierarchyTree.getPathForLocation ( e.getX (), e.getY () );
	                Rectangle pathBounds = hierarchyTree.getUI ().getPathBounds ( hierarchyTree, path );
	                if ( pathBounds != null && pathBounds.contains ( e.getX (), e.getY () ) )
	                {
	               		Object selectedNode = hierarchyTree.getLastSelectedPathComponent();
	               		if(selectedNode instanceof DefaultMutableTreeNode) {
							//type new_name = (type) ;
	               			int nodeLevel = ((DefaultMutableTreeNode) selectedNode).getLevel();
	               			if(nodeLevel == 1) {
	               				Object obj = ((DefaultMutableTreeNode) selectedNode).getUserObject();
	               				Scene sc = (Scene) obj;
	               				ownScene = null;
	               				previewPanel.removeAll();
	               				if(panelList != null)
	               				{
		               				if(!panelList.isEmpty())
		               				{
		               					for(ObjectLocationPanel o: panelList)
		               					{
		               						o.stopThread();
		               					}
		               					panelList.clear();
		               				}
	               				}
	               				editPanel.removeAll();
	               				editPanel.add(new SceneEditPanel(sc, mGameProject, mInputHelper));
	               				ownScene = null;
	               				workPanel.setSelectedIndex(1);
//	               				scene1.setCurrentSceneState(ss1.getID());
//	               				editPanel.add(new SceneStateEditPanel(scene1, mGameProject, mInputHelper));
	               				editPanel.revalidate();
	               				editPanel.repaint();
	               			}
	               			else if(nodeLevel == 2)
	               			{
	               				Object obj = ((DefaultMutableTreeNode) selectedNode).getUserObject();
	               				SceneState st = (SceneState) obj;
	               				DefaultMutableTreeNode tempNode = (DefaultMutableTreeNode) ((DefaultMutableTreeNode) selectedNode).getParent();
	               				Scene sc = (Scene) tempNode.getUserObject();
	               				sc.setCurrentSceneState(st.getID());	               				
	               				previewPanel.removeAll();
	               				if(panelList != null)
	               				{
		               				if(!panelList.isEmpty())
		               				{
		               					for(ObjectLocationPanel o: panelList)
		               					{
		               						o.stopThread();
		               					}
		               					panelList.clear();
		               				}
	               				}
	               				editPanel.removeAll();
	               				ownScene = sc;
	               				editPanel.add(new SceneStateEditPanel(sc, mGameProject, mInputHelper));
	               				//previewPanel.add(gamePanel);
	               				workPanel.setSelectedIndex(1);
	               				editPanel.revalidate();
	               				editPanel.repaint();
	               			}
	               		}
	                }

	            }
	        }
	    } );
		hierarchyTree.setRootVisible(false);
		

		addSceneButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addNewScene();
			}
		});
		hierarchyPanel.add(hierarchyContentPanel, BorderLayout.CENTER);
		
		upPanel.add(hierarchyPanel, BorderLayout.CENTER);
				
	}
	
	public void setPreviewandSetting(Scene sc)
	{
			
			Font textFont = FontLibrary.getFont(mGameSetting.getFontString(), Font.PLAIN, mGameSetting.getTextFontSize());
			Font buttonFont = FontLibrary.getFont(mGameSetting.getFontString(), Font.PLAIN, mGameSetting.getButtonFontSize());
			Color textColor = null;
			Color buttonColor = null;
			try {
			    Field field1 = Class.forName("java.awt.Color").getField(mGameSetting.getTextFontColor());
			    textColor = (Color)field1.get(null);
			    Field field2 = Class.forName("java.awt.Color").getField(mGameSetting.getButtonFontColor());
			    buttonColor = (Color)field2.get(null);
			} catch (Exception ee) {
				textColor = null;
				buttonColor = null;
			}
			Image releasedbuttonImage = ImageLibrary.getImage(mGameSetting.getReleasedButtonString());
			Image pressedbuttonImage = ImageLibrary.getImage(mGameSetting.getPressedButtonString());
			Image backgroundImage = ImageLibrary.getImage(mGameSetting.getBackgroundImage());
	
			GamePanel gamePanel = new GamePanel(sc, backgroundImage, 
					releasedbuttonImage, pressedbuttonImage,
					textFont, 
					buttonFont, 
					textColor, buttonColor, null, null);
			gamePanel.setPreferredSize(dSize);
			previewPanel.add(gamePanel);
			previewPanel.revalidate();
			previewPanel.repaint();
			
			panelList = new ArrayList<ObjectLocationPanel>();
			
			SceneState currentState = sc.getCurrentSceneState();
			ObjectLocationPanel desPanel = new ObjectLocationPanel(currentState, gamePanel);
			panelList.add(desPanel);
			
			for(int i: currentState.getGameContentChoices())
			{
				GameContent gContent = sc.getGameContent(i);
				ObjectLocationPanel gPanel = new ObjectLocationPanel(gContent, gamePanel);
				panelList.add(gPanel);
			}
			
			for(int i: currentState.getSceneChoices())
			{
				SceneStatePair sPair = sc.getSceneStatePair(i);
				ObjectLocationPanel pPanel = new ObjectLocationPanel(sPair, gamePanel);
				panelList.add(pPanel);
			}
			/**
			 *
			 */
			
			refreshSetting(panelList);	
	}
	
	
	private void createWorkPanel() {
		
		workPanel = new JTabbedPane();
		previewPanel = new JPanel();
		previewPanel.setBackground(Color.WHITE);
		editPanel = new JPanel(new BorderLayout());
		editPanel.setBackground(Color.WHITE);
		workPanel.add("Preview", previewPanel);
		workPanel.add("Edit   ", editPanel);
		workPanel.setSelectedIndex(1);
		secondPanel.add(workPanel);
	}
	
	private void createSettingPanel() {
		
		JLabel outlineLabel = new JLabel("Setting", SwingConstants.CENTER);
		outlineLabel.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.gray));
		outlineLabel.setBackground(Color.lightGray);
		outlineLabel.setForeground(Color.WHITE);
		outlineLabel.setOpaque(true);	
		settingContainer = new JPanel();
		settingContainer.setBackground(Color.WHITE);
		settingContainer.setLayout(new BoxLayout(settingContainer, BoxLayout.Y_AXIS));
		JScrollPane outlinePanel = new JScrollPane();
		outlinePanel.setPreferredSize(new Dimension(230, 280));
		outlinePanel.setBorder(BorderFactory.createMatteBorder(0,2,2,2,Color.gray));
//		outlinePanel.setBackground(Color.WHITE);
		outlinePanel.setOpaque(false);
		outlinePanel.getViewport().setView(settingContainer);
		outlinePanel.setBackground(Color.DARK_GRAY);
		thirdPanel.add(outlineLabel, BorderLayout.NORTH);
		thirdPanel.add(outlinePanel, BorderLayout.CENTER);
	}
	
	class tappedPaneListener implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			if(workPanel.getSelectedIndex() == 0 && ownScene != null)
			{
				setPreviewandSetting(ownScene);
				System.out.println(previewPanel.getWidth() + ":::" + previewPanel.getHeight());
			}
			else
			{
				previewPanel.removeAll();
   				if(panelList != null)
   				{
       				if(!panelList.isEmpty())
       				{
       					for(ObjectLocationPanel o: panelList)
       					{
       						o.stopThread();
       					}
       					panelList.clear();
       				}
   				}
				previewPanel.revalidate();
				previewPanel.repaint();
				settingContainer.removeAll();
				settingContainer.revalidate();
				settingContainer.repaint();
			}
		}
	}
	
	public MainWindow(GameProject gp, User user, BackToCreateFile newAction) {
		
		thisWindow = this;
		mUser = user;
		mAction = newAction;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());	
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		notifyImg = ImageLibrary.getImage("resources/main_window/ll.png");
		playImg = ImageLibrary.getImage("resources/main_window/playblue.png");
		stopImg = ImageLibrary.getImage("resources/main_window/stopblue.png");
		treeIcon1 = ImageLibrary.getImage("resources/main_window/icon.png");
		treeIcon2 = ImageLibrary.getImage("resources/main_window/icon1.png");
		final Font font = FontLibrary.getFont(Constants.pathname + "fonts/System San Francisco Display Regular.ttf",
				Font.PLAIN, 17);
		setUIFont(new javax.swing.plaf.FontUIResource(font));
		
		refreshFrame(gp);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setVisible(true);
		setResizable(false);
	}
	
	private void refreshFrame(GameProject gp)
	{
		this.getContentPane().removeAll();
		
		mGameSetting = gp.getGameSetting();
		mGameProject = gp;
		
		initializeGUI();
		createMenu();
		createHierarchyPanel();
		createWorkPanel();
		createSettingPanel();
		
		workPanel.addChangeListener(new tappedPaneListener());
		
		mUpdateTool = new UpdateTool();
		mInputHelper = new InputHelper(mUpdateTool);
		
		mUpdateTool.updateTree();
		revalidate();
		repaint();
	}
	
	public void refreshSetting(ArrayList<ObjectLocationPanel> lpList) {
		settingContainer.removeAll();
		for(ObjectLocationPanel olp : lpList) {
			settingContainer.add(olp);
		}
	}

	
//	private class TutorialWindow extends JFrame {
//
//		private static final long serialVersionUID = 1L;
//		public TutorialWindow(String title) {
//			// TODO Auto-generated constructor stub
//			super(title);
//			setSize(new Dimension(480, 640));
//			setMaximumSize(new Dimension(480, 640));
//			setMinimumSize(new Dimension(480, 640));
//			setLocationRelativeTo(null);
//		}
//		
//	}
	
	class UpdateTool
	{
		public void updateTree()
		{
			hierarchyTree.updateUI();
		}
	}

	
//	
//	public static void main(String [] args) {
//		GameProject gProject = new GameProject("test");
//		GameTemplate gTemplate = new GameTemplate();
//		gProject.setGameSetting(gTemplate.getGameSettingAt(0));
//		new MainWindow(gProject);
//	}

	class CustomTreeCellRenderer extends DefaultTreeCellRenderer {
		
		private static final long serialVersionUID = -574963610380353039L;

		public Component getTreeCellRendererComponent(JTree tree,
	        Object value, boolean selected, boolean expanded,
	        boolean leaf, int row, boolean hasFocus){

	        super.getTreeCellRendererComponent(tree, value,
	        selected, expanded, leaf, row, hasFocus);

	        DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) value;
	        if(nodo.getLevel() == 1) {
	        	setIcon(new ImageIcon(treeIcon1));
	        }
	        else if(nodo.getLevel() == 2) {
	        	setIcon(new ImageIcon(treeIcon2));
	        }

	        return this;
	    }
	}

	public void setClientListener(ClientListener inClientListener) {
		mClientListener = inClientListener;
	}
	
	public void SaveRemote(GameProject gp)
	{
		mClientListener.SendObjectToServer(gp);
	}
	
	public void SaveSuccess(){
		new OneSentenceTwoButton(
				"<html>" + "Save To Remote Successfully" + "</html>"
		, this.getParent(), "OKIE", "DOKIE", "Error");
	}
}
