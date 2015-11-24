package tip.storycreator.game;

import java.io.Serializable;

public class GameSetting implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -637620263901316044L;
	private String bgimageString;
	private String releaseButtonimageString;
	private String pressedButtonimageString;
	private String fontString;
	private int textFontSize;
	private String textFontColor;
	private int buttonFontSize;
	private String buttonFontColor;
	private int initialScene;
	private int initialState;
	
	public int getInitialScene() {
		return initialScene;
	}

	public void setInitialScene(int initialScene) {
		this.initialScene = initialScene;
	}

	public int getInitialState() {
		return initialState;
	}

	public void setInitialState(int initialState) {
		this.initialState = initialState;
	}

	public GameSetting(){
		releaseButtonimageString = null;
		pressedButtonimageString = null;
		bgimageString = null;
		fontString = "Arial";
		textFontSize = 25;
		textFontColor = "black";
		buttonFontSize = 25;
		buttonFontColor = "black";
		initialScene = -1;
		initialState = -1;
	}
	
	public GameSetting(String buttonImageString1, String buttonImageString2, String backgroundImage, 
					   String fontString, int textFontSize, String textFontColor,
					   					  int buttonFontSize, String buttonFontColor, int initialScene, int initialState) {
		super();
		this.releaseButtonimageString = buttonImageString1;
		this.pressedButtonimageString = buttonImageString2;
		this.bgimageString = backgroundImage;
		this.fontString = fontString;
		this.textFontSize = textFontSize;
		this.textFontColor = textFontColor;
		this.buttonFontSize = buttonFontSize;
		this.buttonFontColor = buttonFontColor;
		this.initialScene = initialScene;
		this.initialState = initialState;
	}
	
	public void setBackgroundImage(String backgroundImage) {
		this.bgimageString = backgroundImage;
	}

	public String getBackgroundImage() {
		return bgimageString;
	}
	
	public void setReleasedButtonString(String url){
		releaseButtonimageString = url;
	}

	public String getReleasedButtonString(){
		return releaseButtonimageString;
	}
	
	public void setPressedButtonImage(String url){
		pressedButtonimageString = url;
	}
	
	public String getPressedButtonString(){
		return pressedButtonimageString;
	}
	
	public void setFont(String fontName){
		fontString = fontName;
	}
	
	public String getFontString(){
		return fontString;
	}
	
	public void setTextFontSize(int textFontSize){
		this.textFontSize = textFontSize;
	}
	
	public int getTextFontSize(){
		return textFontSize;
	}
	
	public void setTextFontColor(String textFontColor){
		this.textFontColor = textFontColor;
	}
	
	public String getTextFontColor(){
		return textFontColor;
	}
	
	public void setButtonFontSize(int buttonFontSize){
		this.buttonFontSize = buttonFontSize;
	}
	
	public int getButtonFontSize(){
		return buttonFontSize;
	}
	
	public void setButtonFontColor(String buttonFontColor){
		this.buttonFontColor = buttonFontColor;
	}
	
	public String getButtonFontColor(){
		return buttonFontColor;
	}
	
	
}
