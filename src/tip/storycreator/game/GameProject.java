package tip.storycreator.game;

import java.util.HashMap;

public class GameProject extends GameObject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4237286851278003129L;
	
	private HashMap<Integer, Scene> SceneList;
	private Player mPlayer;
	private GameSetting mGameSetting;

	public GameProject(String name)
	{
		super(name);
		SceneList = new HashMap<Integer, Scene>();
		mPlayer = null;
		mGameSetting = null;
	}
	
	public void addScene(Scene s)
	{
		SceneList.put(s.getID(), s);
	}
	
	public void removeScene(int id)
	{
		SceneList.remove(id);
	}
	
	public Scene getScene(int ID)
	{
		return SceneList.get(ID);
	}
	
	public HashMap<Integer, Scene> getSceneMap()
	{
		return SceneList;
	}
	
	public void setPlayer(Player p)
	{
		mPlayer = p;
	}
	
	public Player getPlayer()
	{
		return mPlayer;
	}
	
	public void setGameSetting(GameSetting mgGameSetting)
	{
		this.mGameSetting = mgGameSetting;
	}
	
	public GameSetting getGameSetting()
	{
		return mGameSetting;
	}

	public void setId(int int1) {
		this.setID(int1);
	}
}
