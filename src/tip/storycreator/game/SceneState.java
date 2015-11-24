package tip.storycreator.game;

import java.util.ArrayList;

public class SceneState extends GameObject
{

	private static final long serialVersionUID = 8142486137654549595L;

	/**
	 * Used to store the IDs of all linked scenes.
	 */
	private ArrayList<Integer> sceneChoices;
	
	/**
	 * Used to store the IDs of all contained game content
	 */
	private ArrayList<Integer> gameContentChoices;
	
	/**
	 * Store description
	 */
	private String description;
	
	private int x, y, w, h;
	
	/**
	 * Store the image for the scene
	 */
	private String imagePath;
	
	public SceneState(String name) 
	{
		super(name);
		// TODO Auto-generated constructor stub
		sceneChoices =  new ArrayList<Integer>();
		gameContentChoices = new ArrayList<Integer>();
		imagePath = null;
		description = null;
		x=y=w=h=0;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	/**
	 * Add the ID of a linked scene into sceneChoices
	 * @param choice
	 */
	public void addSceneID(Integer sceneID)
	{
		sceneChoices.add(sceneID);
	}
	
	/**
	 * Remove the ID of a linked scene from sceneChoices
	 * @param sceneID
	 */
	public void removelinkedScene(Integer sceneID)
	{
		sceneChoices.remove((Object)sceneID);
	}
	
	/**
	 * Get the all the index of linked Scenes
	 * @return ArrayList<Integer>
	 */
	public ArrayList<Integer> getSceneChoices()
	{
		return sceneChoices;
	}
	
	/**
	 * Set the current game state description
	 * @param descriptionID
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	/**
	 * Get the image path
	 * @return
	 */
	public String getImageChoice()
	{
		return imagePath;
	}

	/**
	 * Set the image path
	 * @param imageChoice
	 */
	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}
	
	/**
	 * Get the current SceneState description
	 * @return String
	 */
	public String getDescriptionID()
	{
		return description;
	}
	
	/**
	 * Add the ID of a contained game content 
	 * @param choice
	 */
	public void addGameContent(Integer choice)
	{
		gameContentChoices.add(choice);
	}

	/**
	 * Remove the ID of a (contained) game content
	 * @param choice
	 */
	public void removeGameContentChoice(Integer choice)
	{
		gameContentChoices.remove((Object) choice);
	}

	/**
	 * Return all index of the contained game contents in the SceneState
	 * @return ArrayList<Integer>
	 */
	public ArrayList<Integer> getGameContentChoices()
	{
		return gameContentChoices;
	}
	
	public String toString()
	{
		return getName();
	}

}
