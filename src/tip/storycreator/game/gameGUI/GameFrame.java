package tip.storycreator.game.gameGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import library.FontLibrary;
import library.ImageLibrary;
import tip.storycreator.game.GameProject;
import tip.storycreator.game.GameSetting;
import tip.storycreator.game.Player;
import tip.storycreator.game.Scene;
import tip.storycreator.game.SceneStatePair;

public class GameFrame extends JFrame {
	private static final long serialVersionUID = 1215761905774134074L;
	
	private GameProject mGameProject;
	private Font textFont;
	private Font buttonFont;
	private Color textColor;
	private Color buttonColor;
	private Image releasedbuttonImage;
	private Image pressedbuttonImage;
	private Image backgroundImage;
	private Player mPlayer;
	
	private final Dimension dSize = new Dimension(859, 723);
	
	public GameFrame(GameProject gp){
		setSize(dSize);
		mGameProject = gp;
		GameSetting gs = mGameProject.getGameSetting();
		textFont = FontLibrary.getFont(gs.getFontString(), Font.PLAIN, gs.getTextFontSize());
		buttonFont = FontLibrary.getFont(gs.getFontString(), Font.PLAIN, gs.getButtonFontSize());
		try {
		    Field field1 = Class.forName("java.awt.Color").getField(gs.getTextFontColor());
		    textColor = (Color)field1.get(null);
		    Field field2 = Class.forName("java.awt.Color").getField(gs.getButtonFontColor());
		    buttonColor = (Color)field2.get(null);
		} catch (Exception e) {
			textColor = null;
			buttonColor = null;
		}
		releasedbuttonImage = ImageLibrary.getImage(gs.getReleasedButtonString());
		pressedbuttonImage = ImageLibrary.getImage(gs.getPressedButtonString());
		backgroundImage = ImageLibrary.getImage(gs.getBackgroundImage());
		Scene currentScene = mGameProject.getScene(gs.getInitialScene());
		currentScene.setCurrentSceneState(gs.getInitialState());
		/**
		 * Set up initial panel
		 */
		mPlayer = gp.getPlayer();
		GamePanel gp1 = new GamePanel(currentScene, backgroundImage, releasedbuttonImage, pressedbuttonImage,
							textFont, buttonFont, textColor, buttonColor, this, mPlayer);
		
		if(mPlayer != null)
		{
			System.out.println("player !!!!!");
			JMenuBar jmb = new JMenuBar();
			JMenuItem playItem = new JMenuItem(mPlayer.getName());
			playItem.addActionListener(new playerListener());
			jmb.add(playItem);
			this.setJMenuBar(jmb);
		}
		add(gp1);
	}
	
	class playerListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("player has" + mPlayer.getItems().size());
			GameBackPack gbp = new GameBackPack(mPlayer.getItems());
		}
	}
	
	
	public void updatePanel(SceneStatePair ssp){
		/**
		 * Remove previous panel, add new panel
		 */
		this.getContentPane().removeAll();
		Scene currentScene = mGameProject.getScene(ssp.sceneID);
		currentScene.setCurrentSceneState(ssp.stateID);
		GamePanel gp1 = new GamePanel(currentScene, backgroundImage, releasedbuttonImage, pressedbuttonImage,
				  						textFont, buttonFont, textColor, buttonColor, this, mPlayer);
		add(gp1);
		revalidate();
		repaint();
	}
	
//	public static void main(String [] args)
//	{
//		
//			GameProject mGameProject = new GameProject("gameproject1");
//			GameTemplate mgGameTemplate = new GameTemplate();
//			GameSetting mGameSetting = mgGameTemplate.getGameSettingAt(0);
//			Scene scene1 = new Scene("scene1");
////			Scene scene2 = new Scene("scene2");
////			Scene scene3 = new Scene("scene3");
//			SceneState ss1 = new SceneState("ss1");
//			ss1.setDescription("haasadfsd1");
//			ss1.setX(300);
//			ss1.setY(300);
//			ss1.setW(300);
//			ss1.setH(300);
//			SceneState ss2 = new SceneState("ss2");
//			ss2.setDescription("hadsfasdfas2");
//			ss2.setX(100);
//			ss2.setY(100);
//			ss2.setW(100);
//			ss2.setH(100);
////			SceneState ss3 = new SceneState("ss3");
////			ss3.setDescription("h3");
////			SceneState ss4 = new SceneState("ss4");
////			ss4.setDescription("h4");
////			SceneState ss5 = new SceneState("ss5");
////			ss5.setDescription("h5");
////			SceneState ss6 = new SceneState("ss6");
////			ss6.setDescription("h6");
////			
//			scene1.addSceneState(ss1);
//			scene1.addSceneState(ss2);
////			scene2.addSceneState(ss3);
////			scene2.addSceneState(ss4);
////			scene3.addSceneState(ss5);
////			scene3.addSceneState(ss6);
//////			
////			GameContent gc1 = new GameContent("gamecontent1", "jack");
////			gc1.setX(100);
////			gc1.setY(100);
////			gc1.setH(100);
////			gc1.setW(100);
////			GameContent gc2 = new GameContent("gamecontent2", "steven");
////			GameContent gc3 = new GameContent("gamecontent3", "zack");
////			
//			SceneStatePair ssp1 = new SceneStatePair(scene1.getID(), ss2.getID());
//			ssp1.setDescription("d1");
//			ssp1.setX(200);
//			ssp1.setY(200);
//			ssp1.setH(200);
//			ssp1.setW(200);
//		//	SceneStatePair ssp2 = new SceneStatePair(scene1.getID(), ss1.getID());
//			//ssp2.setDescription("d2");
////			SceneStatePair ssp3 = new SceneStatePair(scene3.getID(), ss5.getID());
////			ssp3.setDescription("d3");
////			SceneStatePair ssp4 = new SceneStatePair(scene3.getID(), ss6.getID());
////			ssp4.setDescription("d4");
////			SceneStatePair ssp5 = new SceneStatePair(scene1.getID(), ss1.getID());
////			ssp5.setDescription("d5");
////			SceneStatePair ssp6 = new SceneStatePair(scene1.getID(), ss2.getID());
////			ssp6.setDescription("d6");
////			
//			scene1.addlinkedScenes(ssp1);
////			scene1.addlinkedScenes(ssp2);
////			scene2.addlinkedScenes(ssp3);
////			scene2.addlinkedScenes(ssp4);
////			scene3.addlinkedScenes(ssp5);
////			scene3.addlinkedScenes(ssp6);
////			
////			scene1.addGameContent(gc1);
////			scene1.addGameContent(gc2);
////			//gc2.usability--;
////			scene3.addGameContent(gc3);
//			mGameProject.addScene(scene1);
////			mGameProject.addScene(scene2);
////			mGameProject.addScene(scene3);
//			
////			ss1.addGameContent(gc1.getID());
////			ss1.addGameContent(gc2.getID());
//			ss1.addSceneID(ssp1.getID());
////			ss1.addSceneID(ssp2.getID());
//			
//			mGameSetting.setInitialScene(scene1.getID());
//			mGameSetting.setInitialState(ss1.getID());
//			mGameProject.setGameSetting(mGameSetting);
//			
//	
//			GameFrame frame = new GameFrame(mGameProject);
//			frame.setVisible(true);	
//	}
}
