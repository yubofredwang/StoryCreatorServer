package tip.storycreator.game;

import java.io.Serializable;

public class SceneStatePair implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7105794381779157695L;
	
	public int sceneID;
	public int stateID;
	private int ID;
	private String description;
	
	private int x, y, w, h;
	public int usability = 1;
	
	public SceneStatePair(int sceneID, int stateID)
	{
		ID = Global.getRandom();
		this.sceneID = sceneID;
		this.stateID = stateID;
		x = y = w = h = 1;
	}
	
	public void setID(int id)
	{
		ID = id;
	}
	
	public int getID()
	{
		return ID;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString()
	{
		return getDescription();
	}
}

