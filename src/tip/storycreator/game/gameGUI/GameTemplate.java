package tip.storycreator.game.gameGUI;

import java.util.Vector;

import tip.storycreator.game.GameSetting;

public class GameTemplate {
	private Vector<GameSetting> templates;
	private String ThemeNames[] = {"Pixal", "Medieval", "Space", "Fantasy", "Ocene", "Jungle", "USC"};
	public GameTemplate(){
		templates = new Vector<GameSetting>();
		GameSetting template1 = new GameSetting("resources/templates/template1_button.png",
												"resources/templates/template1_button_pressed.png", 
												"resources/templates/template1.png",
												"resources/templates/Minecraft.ttf", 25, "WHITE", 25, "WHITE", -1, -1);
		GameSetting template2 = new GameSetting("resources/templates/template2_button.png",
												"resources/templates/template2_button_pressed.png", 
												"resources/templates/template2.png",
												"resources/templates/MorrisRomanAlternate-Black.ttf", 25, "WHITE", 25, "YELLOW", -1, -1);
		GameSetting template3 = new GameSetting("resources/templates/template3_button.png",
												"resources/templates/template3_button_pressed.png", 
												"resources/templates/template3.png",
												"resources/templates/Lifeline.ttf", 25, "WHITE", 25, "GREY", -1, -1);
		GameSetting template4 = new GameSetting("resources/templates/template4_button.png",
												"resources/templates/template4_button_pressed.png", 
												"resources/templates/template4.png",
												"resources/templates/IMMORTAL.ttf", 25, "WHITE", 25, "BROWN", -1, -1);
		GameSetting template5 = new GameSetting("resources/templates/template5_button.png",
												"resources/templates/template5_button_pressed.png", 
												"resources/templates/template5.png",
												"resources/templates/Findet Nemo.ttf", 25, "WHITE", 25, "WHITE", -1, -1);
		GameSetting template6 = new GameSetting("resources/templates/template6_button.png",
												"resources/templates/template6_button_pressed.png", 
												"resources/templates/template6.png",
												"resources/templates/african.ttf", 25, "BROWN", 25, "GREEN", -1, -1);
		GameSetting template7 = new GameSetting("resources/templates/template7_button.png",
												"resources/templates/template7_button_pressed.png", 
												"resources/templates/template7.png",
												"resources/templates/a_CampusCaps Bold.ttf", 25, "YELLOW", 25, "YELLOW", -1, -1);
		
		GameSetting template = new GameSetting("resources/templates/template_button.png",
												"resources/templates/template_button_pressed.png",
												"resources/templates/template.png",
												"resources/fonts/System San Francisco Display Regular.ttf", 25, "BLACK", 25, "BLACK", -1, -1);
		templates.add(template1);//0
		templates.add(template2);//1
		templates.add(template3);//2
		templates.add(template4);//3
		templates.add(template5);
		templates.add(template6);
		templates.add(template7);
		
		templates.add(template);//4
		
	}
	
	public Vector<GameSetting> getTemplates(){
		return templates;
	}
	
	public GameSetting getGameSettingAt(int i){
		if(i >= 0 && i <= templates.size()-2){
			return templates.get(i);
		}
		return templates.get(templates.size()-1);
	}
	
	public String getTemplateName(int i){
		if(i >= 0 && i <= templates.size()-2){
			return ThemeNames[i];
		}
		return "Default";
	}
}
