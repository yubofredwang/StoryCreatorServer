package tip.storycreator.game;

import java.io.Serializable;

public class GameObject implements Serializable
{	
	/**
	 * The serialUID used to networking File I/O
	 */
	private static final long serialVersionUID = -3875312859236465133L;

	/**
	 *  Unique ID.
	 */
	private int objectID;

	public void setID(int id)
	{
		objectID = id;
	}
	/**
	 * Object name
	 */
	private String name;
	
	public GameObject(String name)
	{
		objectID = Global.getRandom();
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	
	public int getID()
	{
		return objectID;
	}
	
	public boolean equalID(int id)
	{
		return id == objectID;
	}
	
}
