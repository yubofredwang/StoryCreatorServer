package tip.storycreator.game;

import java.util.ArrayList;

public class Player extends GameObject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5026203913955896401L;
	
	/**
	 * Store all game contents that player picked up during the game
	 */
	ArrayList<GameContent> itemList;
	
	public Player(String name)
	{
		super(name);
		itemList = new ArrayList<GameContent>();
	}
	
	/**
	 * Add game content to the item list
	 * @param GameContent item
	 */
	public void pickup(GameContent item)
	{
		if(itemList.size() < 9)
		{
			itemList.add(item);
		}
	}
	
	/**
	 * Remove a item based on its index in the list
	 * @param index
	 */
	public void drop(int index)
	{
		itemList.remove(index);
	}
	
	/**
	 * Get the entire itemList
	 * @return
	 */
	public ArrayList<GameContent> getItems()
	{
		return itemList;
	}
}
