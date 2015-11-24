package tip.storycreator.game.gameGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.sun.nio.file.SensitivityWatchEventModifier;

import library.ImageLibrary;
import tip.storycreator.client.PseudoButton;
import tip.storycreator.game.GameContent;
import tip.storycreator.game.Player;
import tip.storycreator.game.Scene;
import tip.storycreator.game.SceneState;
import tip.storycreator.game.SceneStatePair;
import tip.storycreator.server.CustomGUI.PaintedPanel;

public class GamePanel extends PaintedPanel{
	
	private SceneState sceneState = null;
	private GameFrame mFrame;
	
	private HashMap<Integer, JComponent> allComponents;
	/**
	 * 
	 */
	private static final long serialVersionUID = -9035742618096321918L;

	public GamePanel(Scene scene, Image backgroundImage, Image releasedImage, Image pressedImage,
					 Font textFont, Font buttonFont, Color textColor, Color buttonColor, GameFrame mFrame, Player play)
	{
		
		super(null);
		class CheckListener extends MouseAdapter
		{
			GameContent mGameContent;
			public CheckListener(GameContent gc)
			{
				mGameContent = gc;
			}
			
			public void mouseClicked(MouseEvent e) 
			{
				OneImageTwoButton ot = new OneImageTwoButton(ImageLibrary.getImage(mGameContent.getImagePath()), 
						backgroundImage, releasedImage, pressedImage, releasedImage, pressedImage, mGameContent.lookUp(), 
						textFont, buttonFont, mGameContent.getName(), textColor, buttonColor, "Pick up");
				System.out.print("content gets" + ot.getContent());
				if(ot.getContent() == 1 && play != null)
				{
					play.pickup(mGameContent);
					System.out.println("player pick up");
				}
			}
		}
		this.mFrame = mFrame;
		this.sceneState = scene.getCurrentSceneState();
		allComponents = new HashMap<Integer, JComponent>();
		
		if(sceneState.getImageChoice() != null)
		{
			this.setbackgroundImage(ImageLibrary.getImage(sceneState.getImageChoice()));
		}
		else
		{
			this.setbackgroundImage(backgroundImage);
		}
		
		this.setLayout(null);
		
		ArrayList<SceneStatePair> statePairs = new ArrayList<SceneStatePair>();
		for(int i: sceneState.getSceneChoices())
		{
			statePairs.add(scene.getSceneStatePair(i));
		}
		
		ArrayList<GameContent> gameContents = new ArrayList<GameContent>();
		for(int i: sceneState.getGameContentChoices())
		{
			gameContents.add(scene.getGameContent(i));
		}
		
		JLabel desc = new JLabel(sceneState.getDescriptionID());
		System.out.println(sceneState.getDescriptionID());
		desc.setFont(textFont);
		if(mFrame == null)
		{
			DescriptionDragListener descriptionDragListener = new DescriptionDragListener(desc, sceneState);
			desc.addMouseListener(descriptionDragListener);
			desc.addMouseMotionListener(descriptionDragListener);
		}
		if(textColor != null){
			desc.setForeground(textColor);
		}
		desc.setBounds(sceneState.getX(), sceneState.getY(), sceneState.getW(), sceneState.getH());
		allComponents.put(sceneState.getID(), desc);
		this.add(desc);	
		
		for(GameContent gc: gameContents)
		{
			JLabel contentButton = null;
			if(gc.getImagePath() !=null)
			{
				contentButton = new JLabel(gc.getName());//new PaintedButton(gc.getName(), ImageLibrary.getImage(gc.getImagePath()), ImageLibrary.getImage(gc.getImagePath()));
			}
			else
			{
				contentButton = new JLabel(gc.getName());//new PaintedButton(gc.getName(), null, null);
			}
			contentButton.setFont(buttonFont);
			if(mFrame == null)
			{
				GCDragListener mDragListener = new GCDragListener(contentButton, gc);
				contentButton.addMouseListener(mDragListener);
				contentButton.addMouseMotionListener(mDragListener);
			}
			contentButton.addMouseListener(new CheckListener(gc));
			if(textColor != null)
				contentButton.setForeground(textColor);
			contentButton.setBounds(gc.getX(), gc.getY(), gc.getW(), gc.getH());
			allComponents.put(gc.getID(), contentButton);
			add(contentButton);
		}
		
		for(SceneStatePair ssp: statePairs)
		{
			JLabel sspButton = new PseudoButton(ssp.getDescription(), releasedImage, pressedImage);
			sspButton.setFont(buttonFont);
			sspButton.addMouseListener(new linkedListener(ssp));
			if(mFrame == null)
			{
				ButtonDragListener mDragListener = new ButtonDragListener(sspButton, ssp);
				sspButton.addMouseListener(mDragListener);
				sspButton.addMouseMotionListener(mDragListener);
			}
			if(buttonColor != null)
				sspButton.setForeground(buttonColor);
			sspButton.setBounds(ssp.getX(), ssp.getY(), ssp.getW(), ssp.getH());
			allComponents.put(ssp.getID(), sspButton);
			add(sspButton);
		}
		revalidate();
		repaint();
	}
	
	class ButtonDragListener extends MouseAdapter
	{
		JComponent contained = null;
		SceneStatePair msSceneStatePair;
		Boolean move = false;
		int x , y;
		public ButtonDragListener(JComponent jc, SceneStatePair ssp) {
			// TODO Auto-generated constructor stub
			contained = jc;
			msSceneStatePair = ssp;
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			move = true;
			x = e.getXOnScreen();
			y = e.getYOnScreen();
	    }
		
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			if(move)
			{
				int tempx = e.getXOnScreen() - x;
				int tempy = e.getYOnScreen() - y;	
				contained.setLocation(msSceneStatePair.getX() + tempx, msSceneStatePair.getY() + tempy);
			}
		}
		public void mouseReleased(MouseEvent e) {
			move = false;
			msSceneStatePair.setX(e.getXOnScreen()-x+msSceneStatePair.getX());
			msSceneStatePair.setY(e.getYOnScreen()-y+msSceneStatePair.getY());
			x = y = 0;
	    }
	}
	
	class GCDragListener extends MouseAdapter
	{
		JComponent contained = null;
		GameContent mGameContent;
		Boolean move = false;
		int x , y;
		public GCDragListener(JComponent jc, GameContent gc) {
			// TODO Auto-generated constructor stub
			contained = jc;
			mGameContent = gc;
		}
		
		public void mouseClicked(MouseEvent e) {
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			move = true;
			x = e.getXOnScreen();
			y = e.getYOnScreen();
	    }
		
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			if(move)
			{
				int tempx = e.getXOnScreen() - x;
				int tempy = e.getYOnScreen() - y;	
				contained.setLocation(mGameContent.getX() + tempx, mGameContent.getY() + tempy);
			}
		}
		public void mouseReleased(MouseEvent e) {
			move = false;
			mGameContent.setX(e.getXOnScreen()-x+mGameContent.getX());
			mGameContent.setY(e.getYOnScreen()-y+mGameContent.getY());
			x = y = 0;
	    }
	}
	
	class DescriptionDragListener extends MouseAdapter
	{
		JComponent contained = null;
		SceneState mSceneState;
		Boolean move = false;
		int x , y;
		public DescriptionDragListener(JComponent jc, SceneState st) {
			// TODO Auto-generated constructor stub
			contained = jc;
			mSceneState = st;
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			move = true;
			x = e.getXOnScreen();
			y = e.getYOnScreen();
	    }
		
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			if(move)
			{
				int tempx = e.getXOnScreen() - x;
				int tempy = e.getYOnScreen() - y;	
				contained.setLocation(mSceneState.getX() + tempx, mSceneState.getY() + tempy);
			}
		}
		public void mouseReleased(MouseEvent e) {
			move = false;
			mSceneState.setX(e.getXOnScreen()-x+mSceneState.getX());
			mSceneState.setY(e.getYOnScreen()-y+mSceneState.getY());
			x = y = 0;
	    }
	}
	
	public void refreshLocation(int id, int x, int y, int w, int h)
	{
		JComponent tempCp = allComponents.get(id);
		tempCp.setBounds(x, y, w, h);
	}
	
	class linkedListener extends MouseAdapter
	{
		SceneStatePair ssp;
		public linkedListener(SceneStatePair ssp) {
			this.ssp = ssp;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(mFrame != null)
				mFrame.updatePanel(ssp);
		}
	}
	
	public class OneImageTwoButton {
		private int choice = -1;
		
		public OneImageTwoButton(Image iconImage, Image background, Image button1Up, Image button1Down, Image button2Up,
				Image button2Down, String content, Font ButtonFont, Font TextFont, String title, Color textColor, Color buttonColor, String button1) {	
			PaintedPanel overallPanel = new PaintedPanel(background);
			UIManager.put("Button.font", button1Down);
			UIManager.put("TextArea.font", TextFont);
			UIManager.put("Label.font", TextFont);
			JDialog jd = new JDialog();
			jd.setModal(true);
			jd.setMinimumSize(new Dimension(600, 400));
			jd.setMaximumSize(new Dimension(600, 400));
			jd.setLocationRelativeTo(null);
			jd.setTitle(title);
			overallPanel.setLayout(new GridBagLayout());
			GridBagConstraints d = new GridBagConstraints();
			d.fill = GridBagConstraints.HORIZONTAL;
			d.insets = new Insets(30, 40, 30, 40);
			d.gridx = 0;
			d.gridy = 0;
			d.ipadx = 100;
			d.ipady = 60;	
			PaintedPanel iconPanel = new PaintedPanel(iconImage);
			iconPanel.setMaximumSize(new Dimension(400, 400));
			iconPanel.setMaximumSize(new Dimension(400, 400));
			overallPanel.add(iconPanel, d);
			d.gridx = 1;
			d.gridy = 0;
			JTextArea textField = new JTextArea(2, 4);
			textField.setForeground(textColor);
			textField.setEditable(false);
			textField.setLineWrap(true);
			textField.setWrapStyleWord(true);
			textField.setText(content);
			textField.setBorder(new TitledBorder("Description"));
			JScrollPane scroll = new JScrollPane(textField);
			scroll.setOpaque(false);
			scroll.getViewport().setOpaque(false);
			scroll.setBorder(null);
			scroll.setViewportBorder(null);
			textField.setBorder(null);
			textField.setBackground(new Color(0, 0, 0, 0));
			//textField.setBorder(emptyBorder);
			overallPanel.add(scroll, d);
			d.anchor = GridBagConstraints.PAGE_END;
			d.gridx = 0;
			d.gridy = 1;
			JComponent ps1;
			if(button1 != null)
			{
				ps1 = new PseudoButton(button1, button1Up, button1Down);
				ps1.setForeground(buttonColor);
				ps1.setOpaque(true);
				ps1.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						choice = 1;
						jd.dispose();
					}
				});
			}
			else
			{
				ps1 = new JPanel();
				ps1.setOpaque(false);
				//ps1.setBackground(new Color(0,0,0));
			}
			PseudoButton ps2 = new PseudoButton("Return", button2Up, button2Down);
			ps2.setForeground(buttonColor);
			ps2.setOpaque(true);
			ps2.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					choice = 0;
					jd.dispose();
				}
			});
			//ps2.setMaximumSize(new Dimension(150, 100));
			//ps2.setMinimumSize(new Dimension(150, 100));
			overallPanel.add(ps2, d);
			d.gridx = 1;
			d.gridy = 1;
			overallPanel.add(ps1, d);
			jd.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent we) {
					// LoginWindow.isPressed = false;
					jd.dispose();
				}
			});
			jd.add(overallPanel);
			jd.pack();
			jd.setVisible(true);
		}

		public int getContent() {
			return choice;
		}

	}
}
