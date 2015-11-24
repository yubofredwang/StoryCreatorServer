package tip.storycreator.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import library.FontLibrary;
import library.ImageLibrary;
import popupWindow.OneSentenceTwoButton;
import popupWindow.OneTextFieldTwoButton;
import tip.storycreator.game.GameProject;
import tip.storycreator.game.GameSetting;
import tip.storycreator.game.gameGUI.GameTemplate;
import tip.storycreator.server.User;

public class CreateFileWindow extends JPanel {

	private static final long serialVersionUID = 6945651168916095210L;

	/**
	 * The user
	 */
	private User mUser;

	private JButton userButton;
	private JButton createNewButton;
	private JButton openFileButton;

	private JButton[] templateButtons;
	private JScrollPane templateSP;
	private GameTemplate mGameTemplates = new GameTemplate();

	private GameProject mProject;

	private ActionListener confirmAction;
//	private ActionListener logoutAction;
	
	//private GameProject mProject = null;
	
	private JButton createButton;

	private final static Color default_color = new Color(35, 152, 208);
	// private final static Color lighter_color = new Color(41, 178, 244);
	private final static Font font = FontLibrary
			.getFont(Constants.pathname + "fonts/System San Francisco Display Thin.ttf", Font.PLAIN, 25);

	public GameProject getGameProject() {
		return mProject;
	}

	public CreateFileWindow(ActionListener confirmAction, ActionListener logoutAction) throws IOException {
		UIManager.put("OptionPane.font", font);
		initializeVariables();
		createGUI();
		addActionAdapters(confirmAction, logoutAction);
	}

	private void initializeVariables() throws IOException {
		mUser = new User("Shirley", "123456");
		mProject = new GameProject("My Game");
		mProject = null;

		createButton = new DefaultButton("Create", 150, 50);
		createButton.setFont(font);
		createButton.setEnabled(false);

		Image userImage = ImageLibrary.getImage(Constants.pathname + Constants.createfile + "UserIcon" + Constants.png);
		userImage = userImage.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		ImageIcon userIcon = new ImageIcon(userImage);
		userButton = new JButton(userIcon);
		userButton.setPreferredSize(new Dimension(userIcon.getIconWidth(), userIcon.getIconHeight()));
		userButton.setBorder(null);
		userButton.setOpaque(false);

		Image newImage = ImageLibrary.getImage(Constants.pathname + Constants.createfile + "NewIcon" + Constants.png);
		newImage = newImage.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newImage);
		createNewButton = new JButton(newIcon);
		createNewButton.setPreferredSize(new Dimension(newIcon.getIconWidth(), newIcon.getIconHeight()));
		createNewButton.setBorder(null);
		createNewButton.setOpaque(false);

		Image fileImage = ImageLibrary
				.getImage(Constants.pathname + Constants.createfile + "FolderIcon" + Constants.png);
		fileImage = fileImage.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		ImageIcon fileIcon = new ImageIcon(fileImage);
		openFileButton = new JButton(fileIcon);
		openFileButton.setPreferredSize(new Dimension(fileIcon.getIconWidth(), fileIcon.getIconHeight()));
		openFileButton.setBorder(null);
		openFileButton.setOpaque(false);

		JPanel templatesPanel = new JPanel(new GridLayout(2, 4, 30, 30));
		templatesPanel.setOpaque(false);
		templateButtons = new JButton[8];
		for (int i = 0; i < 8; ++i) {
			templateButtons[i] = new JButton("");
			templateButtons[i].setBorder(new LineBorder(Color.gray, 2));
			final int buttonSelection = i;
			GameSetting selectedGameSetting;
			String backgroundImage;
			selectedGameSetting = mGameTemplates.getGameSettingAt(buttonSelection);
			backgroundImage = selectedGameSetting.getBackgroundImage();
			templateButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					mProject = new GameProject("");
					mProject.setGameSetting(selectedGameSetting);
					for (JButton button : templateButtons) {
						button.setEnabled(true);
						button.setBorder(new LineBorder(Color.gray, 2));
					}
					templateButtons[buttonSelection].setEnabled(false);
					templateButtons[buttonSelection].setBorder(new LineBorder(default_color, 5));
					createButton.setEnabled(true);
				}
			});
			if (backgroundImage != null) {
				JPanel buttonPanel = new JPanel() {
					private static final long serialVersionUID = 6354799500838498628L;

					protected void paintComponent(Graphics g) {
						Image paintImage = ImageLibrary.getImage(backgroundImage);
						g.drawImage(paintImage, 0, 0, getWidth(), getHeight(), null);
					}
				};
				buttonPanel.setLayout(new BorderLayout());
				JButton buttonButton = new DefaultButton(mGameTemplates.getTemplateName(buttonSelection),
											ImageLibrary.getImage(selectedGameSetting.getReleasedButtonString()),
											ImageLibrary.getImage(selectedGameSetting.getPressedButtonString()), 150, 50);
				buttonButton.setFont(FontLibrary.getFont(selectedGameSetting.getFontString(), Font.PLAIN, 25));
				Color buttonColor;
				try {
					Field field = Class.forName("java.awt.Color").getField(selectedGameSetting.getButtonFontColor());
					buttonColor = (Color) field.get(null);
				} catch (Exception e) {
					buttonColor = null;
				}
				buttonButton.setForeground(buttonColor);
				buttonButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						templateButtons[buttonSelection].doClick();
					}
				});
				buttonPanel.add(buttonButton, BorderLayout.SOUTH);
				templateButtons[i].add(buttonPanel);
			}
			templatesPanel.add(templateButtons[i]);
		}
		templateSP = new JScrollPane(templatesPanel);
		templateSP.setBorder(new EmptyBorder(30, 30, 10, 30));
		templateSP.setOpaque(false);

		setBackground(Color.white);
	}

	private void createGUI() {
		setLayout(new BorderLayout());

		JPanel botPanel = new JPanel();
		botPanel.setLayout(new BoxLayout(botPanel, BoxLayout.X_AXIS));
		botPanel.setBackground(Color.white);
		botPanel.setBorder(new EmptyBorder(5, 0, 10, 5));
		botPanel.add(Box.createGlue());
		botPanel.add(createButton);

		JPanel leftPanel = new JPanel(new GridLayout(3, 1, 20, 20));
		leftPanel.setBackground(default_color);

		JPanel userPanel = new JPanel(new GridLayout(1, 1));
		userPanel.setBackground(default_color);
		userPanel.add(userButton);
		JPanel createNewPanel = new JPanel(new GridLayout(1, 1));
		createNewPanel.setBackground(default_color);
		createNewPanel.add(createNewButton);
		JPanel openFilePanel = new JPanel(new GridLayout(1, 1));
		openFilePanel.setBackground(default_color);
		openFilePanel.add(openFileButton);

		leftPanel.add(userPanel);
		leftPanel.add(createNewPanel);
		leftPanel.add(openFilePanel);

		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBackground(Color.white);
		centerPanel.add(templateSP);

		add(centerPanel, BorderLayout.CENTER);
		add(leftPanel, BorderLayout.WEST);
		centerPanel.add(botPanel, BorderLayout.SOUTH);
	}

	private void addActionAdapters(ActionListener confirmAction, ActionListener logoutAction) {
		this.confirmAction = confirmAction;
//		this.logoutAction = logoutAction;
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mProject != null && mProject.getName().equals("")) {
					OneTextFieldTwoButton otf = new OneTextFieldTwoButton(null,
							"Please enter a name for your Game Project", "Game Project name");
					String name = otf.getContent();
					if (name != null && !name.equals("")) {
						System.out.println(name);
						mProject.setName(name);
//						MainWindow mWindow = new MainWindow(mProject, mUser);
//						// MainWindow mWindow = new MainWindow(mProject);
//						mWindow.setClientListener(mClientListener);
						JButton tempButton = new JButton("");
						tempButton.addActionListener(confirmAction);
						tempButton.doClick();
						
					}
						//mWindow.setClientListener(mClientListener);
//						JButton tempButton = new JButton("");
//						tempButton.addActionListener(confirmAction);
//						tempButton.doClick();
					}	
				}
		});
		// createButton.addActionListener(confirmAction);
		userButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog userDialog = new JDialog();
				userDialog.setModal(true);
				userDialog.setSize(400, 250);
				userDialog.setLocationRelativeTo(null);
				userDialog.setLayout(new BorderLayout());

				Font dialogFont = font.deriveFont(Font.PLAIN, 20);
				JPanel labelPanel = new JPanel();
				labelPanel.setBackground(default_color);
				labelPanel.setOpaque(true);
				labelPanel.setLayout(new GridLayout(2, 1));
				JLabel userLabel = new JLabel("User: " + mUser.getName(), SwingConstants.CENTER);
				userLabel.setForeground(Color.white);
				userLabel.setFont(dialogFont);
				JLabel gameProjectLabel = new JLabel("Game Project Number: " + mUser.getGameProject().size(),
						SwingConstants.CENTER);
				gameProjectLabel.setFont(dialogFont);
				gameProjectLabel.setForeground(Color.white);
				labelPanel.add(userLabel);
				labelPanel.add(gameProjectLabel);
				userDialog.add(labelPanel, BorderLayout.CENTER);

				JPanel LogoutPanel = new JPanel();
				LogoutPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
				LogoutPanel.setBackground(default_color);
				JButton LogoutButton = new DefaultButton("Logout",
						ImageLibrary.getImage("resources/create_file/blue_button_light.png"),
						ImageLibrary.getImage("resources/create_file/blue_button.png"));
				LogoutButton.setFont(font.deriveFont(Font.BOLD, 15));
				// LogoutButton.setBorder(new EmptyBorder(10, 10, 30, 10));
				ActionListener aListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						OneSentenceTwoButton os = new OneSentenceTwoButton("Are you sure you want to logout?", null,
								"yes", "no", "Logout");
						int n = os.getContent();
						if (n == 1) {
							LogoutButton.addActionListener(logoutAction);
							LogoutButton.removeActionListener(this);
							userDialog.dispose();
							LogoutButton.doClick();
						} else {
							userDialog.dispose();
						}
					}
				};
				LogoutButton.addActionListener(aListener);
				LogoutPanel.add(LogoutButton);
				userDialog.add(LogoutPanel, BorderLayout.SOUTH);
				userDialog.setVisible(true);
			}
		});
		createNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (mProject != null && mProject.getName().equals("")) {
					 OneTextFieldTwoButton otf1 = new
					 OneTextFieldTwoButton(null, "Please enter a name for your Game Project", "Game Project name");
					 String name = otf1.getContent();
					if (name != null && !name.equals("")) {
						System.out.println(name);
						mProject.setName(name);
						// MainWindow mWindow = new MainWindow(mProject);
						JButton tempButton = new JButton("");
						tempButton.addActionListener(confirmAction);
						tempButton.doClick();
					}
				}
			}
		});
		openFileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Object[] options = { "local file", "remote file" };
				// int n = JOptionPane.showOptionDialog(null, "What kind of file
				// do you want to open?", "Open File",
				// JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
				// new ImageIcon(notifyImg), options, options[0]);

				OneSentenceTwoButton os = new OneSentenceTwoButton("What kind of file do you want to open?", null,
						"remote file", "local file", "Open File");
				int n = os.getContent();
				if (n == 0) {
					JFileChooser chooser = new JFileChooser();
					// FileNameExtensionFilter filter = new
					// FileNameExtensionFilter("TEXT FILES", "txt", "text");
					// chooser.setFileFilter(filter);
//				    FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
//				    chooser.setFileFilter(filter);
				    int returnVal = chooser.showOpenDialog(getParent());
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
//				       String filePath = chooser.getSelectedFile().getAbsolutePath();
				       File file = chooser.getSelectedFile();
				       try {
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
						mProject = (GameProject)ois.readObject();
						System.out.println("READ EXISTING FILE NAME: " + mProject.getName());
						ois.close();
//						MainWindow mWindow = new MainWindow(mProject,mUser);
						JButton temp = new JButton("");
						temp.addActionListener(confirmAction);
						temp.doClick();
						} catch (IOException e1) {
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						}
					}
				} else if (n == 1) {
					showRemoteFiles();
				}

			}
		});
	}

	private void showRemoteFiles() {
		ArrayList<GameProject> mGameProjects = mUser.getGameProjects();
		JDialog remoteFilesDialog = new JDialog();

		remoteFilesDialog.setModal(true);
		remoteFilesDialog.setLocationRelativeTo(userButton);
		remoteFilesDialog.setSize(300, 150);
		remoteFilesDialog.setLayout(new GridLayout(3, 1));

		Font dialogFont = font.deriveFont(Font.PLAIN, 20);
		Vector<String> items = new Vector<String>();
		for (GameProject gProject : mGameProjects) {
			items.add(gProject.getName());
		}

		JLabel gameProjectLabel = new JLabel("Your Game Projects:", SwingConstants.CENTER);
		gameProjectLabel.setFont(dialogFont);
		JComboBox<String> projectNameCB = new JComboBox<>(items);
		projectNameCB.setFont(dialogFont);
		JButton openButton = new JButton("Open");
		openButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remoteFilesDialog.dispose();
				mProject = mGameProjects.get(projectNameCB.getSelectedIndex());
				JButton tempButton = new JButton("");
				tempButton.addActionListener(confirmAction);
				tempButton.doClick();
			}
		});

		remoteFilesDialog.add(gameProjectLabel);
		if (mGameProjects.size() != 0) {
			remoteFilesDialog.add(projectNameCB);
			remoteFilesDialog.add(openButton);
		} else {
			JLabel noGameProjetLabel = new JLabel("No Created Game Projects yet.", SwingConstants.CENTER);
			noGameProjetLabel.setFont(dialogFont);
			JButton closeButton = new JButton("Close");
			closeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					remoteFilesDialog.dispose();
				}
			});
			closeButton.setFont(dialogFont);
			remoteFilesDialog.add(noGameProjetLabel);
			remoteFilesDialog.add(closeButton);
		}

		remoteFilesDialog.setVisible(true);
	}

	public static void main(String[] args) throws IOException {
		JFrame f = new JFrame("StoryCreator");
		f.setMinimumSize(new Dimension(960, 640));
		f.setLocationRelativeTo(null);
		CreateFileWindow createFileWindow = new CreateFileWindow(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		}, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});
		f.add(createFileWindow);
		f.setVisible(true);
	}

	public void setUser(User inUser) {
		mUser = inUser;
	}

	public User getUser() {
		return mUser;
	}
}
