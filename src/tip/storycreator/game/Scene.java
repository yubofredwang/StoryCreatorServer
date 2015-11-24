package tip.storycreator.game;

import java.util.HashMap;

public class Scene extends  GameObject
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5758696413262970563L;

	/**
	 * All game state the scene has
	 */
	protected HashMap<Integer, SceneState> ownSceneState;
	
	/**
	 * All linked game scene this scene has 
	 */
	protected HashMap<Integer,SceneStatePair> linkedScenes;
	
	/**
	 * All game content it has
	 */
	protected HashMap<Integer, GameContent> mGameContents;
	
	/**
	 * Used for tracking the state of game.
	 */
	private int currentSceneState;
	
	public Scene(String name) 
	{
		super(name);
		ownSceneState = new HashMap<Integer, SceneState>();
		linkedScenes = new HashMap<Integer, SceneStatePair>();
		mGameContents = new HashMap<Integer, GameContent>();
		currentSceneState = -1;
	}
	
	/**
	 * add a SceneState to ownGameState;
	 * @param s
	 */
	public void addSceneState(SceneState s)
	{
		ownSceneState.put(s.getID(), s);
	}
	
	/**
	 * @param state
	 */
	public void removeSceneState(int stateID)
	{
		ownSceneState.remove(stateID);
	}
	
	public SceneState getSceneState(int ID)
	{
		return ownSceneState.get(ID);
	}
	
	public HashMap<Integer, SceneState> getAllSceneStates()
	{
		return ownSceneState;
	}
	
	public HashMap<Integer, SceneStatePair> getAllSceneStatePairs()
	{
		return linkedScenes;
	}
	
	public HashMap<Integer, GameContent> getAllGameContents()
	{
		return mGameContents;
	}
	
	/**
	 * set currentSceneState index 
	 * @param state
	 */
	public void setCurrentSceneState(int stateID)
	{
		currentSceneState = stateID;
	}
	
	public void addlinkedScenes(SceneStatePair ssp)
	{
		linkedScenes.put(ssp.getID(), ssp);
	}
	
	public void removelinkedScenes(int sspID)
	{
		linkedScenes.remove(sspID);
	}
	
	public SceneStatePair getSceneStatePair(int ID)
	{
		return linkedScenes.get(ID);
	}
	
	public void addGameContent(GameContent gc)
	{
		mGameContents.put(gc.getID(), gc);
	}
	
	public void removeGameContent(int gcID)
	{
		mGameContents.remove(gcID);
	}
	
	public GameContent getGameContent(int ID)
	{
		return mGameContents.get(ID);
	}
	
	public int getCurrentSceneStateNum()
	{
		return currentSceneState;		
	}
	
	public SceneState getCurrentSceneState()
	{
		return ownSceneState.get(currentSceneState);
	}
	
	public String toString()
	{
		return getName();
	}
}

