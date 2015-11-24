package tip.storycreator.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.mysql.jdbc.Driver;

import tip.storycreator.game.GameContent;
import tip.storycreator.game.GameProject;
import tip.storycreator.game.GameSetting;
import tip.storycreator.game.Player;
import tip.storycreator.game.Scene;
import tip.storycreator.game.SceneState;
import tip.storycreator.game.SceneStatePair;

public class MySQLDriver {

	private Connection con;
	
	private final static String selectUserName = "SELECT * FROM USER WHERE USERNAME=?";
	//private final static String addGameProject = "INSERT INTO GAMEPROJECT(GPID, GPNAME, GPUSERBELONGTO) VALUES(?,?,?)";
	private final static String addUserName = "INSERT INTO USER(USERNAME, PASSWORD) VALUES(?,?)";
	private final static String updateUser = "UPDATE USER SET PASSWORD=? WHERE USERNAME=?";
	//private final static String getAmount = "SELECT CREATED FROM FACTORYORDERS WHERE NAME=?";
	
	private final static String updatePlayer = "UPDATE PLAYER SET PNAME=?, PPROJECTBELONGTO=? WHERE PID=?";
	private final static String selectPlayer = "SELECT * FROM PLAYER WHERE PID=?";
	private final static String selectPlayerFromGPID = "SELECT * FROM PLAYER WHERE PPROJECTBELONGTO=?";
	private final static String addPlayer = "INSERT INTO PLAYER(PID, PNAME, PPROJECTBELONGTO) VALUES(?,?,?)";
	
	private final static String updateGameContent = "UPDATE GAMECONTENT SET GCNAME=?, GCIMAGEPATH=?, GCDESCRIPTION=?, GCPLAYERBELONGTO=?, GCSCENEBELONGTO=?, X=?, Y=?, W=?, H=? WHERE GCID=?";
	private final static String selectGameContent = "SELECT * FROM GAMECONTENT WHERE GCID=?";
	private final static String selectGameContentFromSceneBelongTo = "SELECT * FROM GAMECONTENT WHERE GCSCENEBELONGTO=?";
	private final static String selectGameContentFromPlayerBelongTo = "SELECT * FROM GAMECONTENT WHERE GCPLAYERBELONGTO=?";
	private final static String addGameContent = "INSERT INTO GAMECONTENT(GCID, GCNAME, GCIMAGEPATH, GCDESCRIPTION, GCPLAYERBELONGTO, GCSCENEBELONGTO, X, Y, W, H) VALUES(?,?,?,?,?,?,?,?,?,?)";
	
	private final static String updateGameProject = "UPDATE GAMEPROJECT SET GPNAME=?, GPUSERBELONGTO=? WHERE GPID=?";
	private final static String selectGameProject = "SELECT * FROM GAMEPROJECT WHERE GPID=?";
	private final static String selectGameProjectFromName = "SELECT * FROM GAMEPROJECT WHERE GPUSERBELONGTO=?";
	private final static String addGameProject = "INSERT INTO GAMEPROJECT(GPID, GPNAME, GPUSERBELONGTO) VALUES(?,?,?)";
	
	private final static String updateScene = "UPDATE SCENE SET SCENENAME=?, CURRENTSCENESTATE=?, GPIDBELONGTO=? WHERE SCENEID=?";
	private final static String selectScene = "SELECT * FROM SCENE WHERE SCENEID=?";
	private final static String selectSceneFromGPID = "SELECT * FROM SCENE WHERE GPIDBELONGTO=?";
	private final static String addScene = "INSERT INTO SCENE(SCENEID, SCENENAME, CURRENTSCENESTATE, GPIDBELONGTO) VALUES(?,?,?,?)";
	
	private final static String updateSceneStatePair = "UPDATE SCENESTATEPAIR SET SCENEID=?, STATEID=?, SCENEBELONGTO=?, X=?, Y=?, W=?, H=?, DESCRIPTION=? WHERE SSPID=?";
	private final static String selectSceneStatePair = "SELECT * FROM SCENESTATEPAIR WHERE SSPID=?";
	private final static String selectSceneStatePairFromSceneBelongTo = "SELECT * FROM SCENESTATEPAIR WHERE SCENEBELONGTO=?";
	private final static String addSceneStatePair = "INSERT INTO SCENESTATEPAIR(SSPID, SCENEID, STATEID, SCENEBELONGTO, X, Y, W, H, DESCRIPTION) VALUES(?,?,?,?,?,?,?,?,?)";
	
	private final static String updateSceneState = "UPDATE SCENESTATE SET SSNAME=?, SSDESCRIPTION=?, SSIMAGEPATH=?, SCENECHOICEAL=?, GCCHOICEAL=?, X=?, Y=?, W=?, H=?, SCENEBELONGTO=? WHERE SSID=?";
	private final static String selectSceneState = "SELECT * FROM SCENESTATE WHERE SSID=?";
	private final static String selectSceneStateFromSceneBelongTo = "SELECT * FROM SCENESTATE WHERE SCENEBELONGTO=?";
	private final static String addSceneState = "INSERT INTO SCENESTATE(SSID, SSNAME, SSDESCRIPTION, SSIMAGEPATH, SCENECHOICEAL, GCCHOICEAL, X, Y, W, H, SCENEBELONGTO) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	
	
	private final static String updateGameSetting = "UPDATE GAMESETTING SET BGIMAGESTRING=?, RELEASEBUTTONIMAGESTRING=?, PRESSEDBUTTONIMAGESTRING=?, FONTSTRING=?, TEXTFONTSIZE=?, TEXTFONTCOLOR=?, BUTTONFONTSIZE=?, BUTTONFONTCOLOR=?, INITIALSCENE=?,INITIALSTATE=? WHERE GPIDBELONGTO=?";
	private final static String selectGameSetting = "SELECT * FROM GAMESETTING WHERE GPIDBELONGTO=?";
	private final static String addGameSetting = "INSERT INTO GAMESETTING(GPIDBELONGTO, BGIMAGESTRING, RELEASEBUTTONIMAGESTRING, PRESSEDBUTTONIMAGESTRING, FONTSTRING, TEXTFONTSIZE, TEXTFONTCOLOR, BUTTONFONTSIZE, BUTTONFONTCOLOR, INITIALSCENE,INITIALSTATE) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	
	private String userpasswordtemp  = "Jdbc:mysql://localhost:3306/storycreator?user=root";
	
	
	public void setLoginString(String t)
	{
		userpasswordtemp = t;
	}
	
	public MySQLDriver() {
		try {
			new Driver();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	/**
	 * need to be changed
	 */
	public void connect() {
		try {
			con = DriverManager.getConnection(userpasswordtemp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stop() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String encrypt(String pw){
		return pw;
	}
	public User logIn(String username, String pw){
		if(doesUserPasswordExist(username, pw))
			return getUserObject(username, pw);
		else
			return null;
	}
	public User createNewUser(String username, String password){
		if(doesUserExist(username))
			return null;
		String encryptedPassword = encrypt(password);
		User newUser = new User(username, encryptedPassword);
		addUserName(username, encryptedPassword);
		return newUser;
	}
	public boolean doesUserExist(String userName){
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(selectUserName);
			ps.setString(1, userName);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
					return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean doesUserPasswordExist(String userName, String password) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(selectUserName);
			ps.setString(1, userName);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				//FactoryServerGUI.addMessage(result.getString(1) + " exists with count: " + result.getInt(2));
				if(password.equals(result.getString(2))){
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//FactoryServerGUI.addMessage("Unable to find product with name: " + productName);
		return false;
	}
	public boolean doesPlayerExist(int id) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(selectPlayer);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean doesGameContentExist(int id) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(selectGameContent);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean doesGameProjectExist(int id) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(selectGameProject);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean doesSceneExist(int id) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(selectScene);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean doesSceneStatePairExist(int id) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(selectSceneStatePair);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean doesSceneStateExist(int id){
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(selectSceneState);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean doesGameSettingExist(int gpIDBelongTo){
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(selectGameSetting);
			ps.setInt(1, gpIDBelongTo);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void addUserName(String userName, String password) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(addUserName);
			ps.setString(1, userName);
			ps.setString(2, password);
			ps.executeUpdate();
		//	FactoryServerGUI.addMessage("Adding product: " + productName + " to table with count 0.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean changePassword(String username, String password, String newPW){
		if(doesUserPasswordExist(username, password)){
			updateUser(username, newPW);
			return true;
		}
		else{
			return false;
		}
	}
	public void updateUser(String username, String password){
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(updateUser);
			ps.setString(1, password);
			ps.setString(2, username);
			ps.executeUpdate();
		//	FactoryServerGUI.addMessage("Adding product: " + productName + " to table with count 0.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void saveToDatabase(User user){
		if(user == null)
			return;
		if(!doesUserExist(user.getName())){
			addUserName(user.getName(), user.getPassword());
		}
		else{
			updateUser(user.getName(), user.getPassword());
		}
		for(GameProject gp: user.getGameProjects()){
			if(!doesGameProjectExist(gp.getID())){
				addGameProject(gp, user.getName());
			}
			else
				updateGameProject(gp, user.getName());	
			//below is to iterate through scenes
			HashMap<Integer, Scene> scenemap = gp.getSceneMap();
			Iterator it = scenemap.entrySet().iterator();
			while (it.hasNext()) {
				        Map.Entry pair = (Map.Entry)it.next();
				        int id = (Integer) pair.getKey();
				        Scene s = (Scene) pair.getValue();
				        if(!doesSceneExist(id)){
				        	addScene(s, gp.getID());
				        }
				        else
				        	updateScene(s, gp.getID());
				        
				        HashMap<Integer, SceneState> ssMap = s.getAllSceneStates();
				        Iterator it2 = ssMap.entrySet().iterator();
						while (it2.hasNext()) {
					        Map.Entry pair2 = (Map.Entry)it2.next();
					        int id2 = (Integer) pair2.getKey();
					        SceneState ss = (SceneState) pair2.getValue();
					        if(!doesSceneStateExist(id2)){
					        	addSceneState(ss, s.getID());
					        }
					        else
					        	updateSceneState(ss, s.getID());      
						}
						HashMap<Integer, SceneStatePair> sspMap = s.getAllSceneStatePairs();
						Iterator it3 = sspMap.entrySet().iterator();
						while (it3.hasNext()) {
					        Map.Entry pair3 = (Map.Entry)it3.next();
					        int id3 = (Integer) pair3.getKey();
					        SceneStatePair ssp = (SceneStatePair) pair3.getValue();
					        if(!doesSceneStatePairExist(id3)){
					        	addSceneStatePair(ssp, s.getID());
					        }
					        else
					        	updateSceneStatePair(ssp, s.getID());      
						}
						HashMap<Integer, GameContent> gcMap = s.getAllGameContents();
						Iterator it4 = gcMap.entrySet().iterator();
						while (it4.hasNext()) {
					        Map.Entry pair4 = (Map.Entry)it4.next();
					        int id4 = (Integer) pair4.getKey();
					        GameContent gc = (GameContent) pair4.getValue();
					        if(!doesGameContentExist(id4)){
					        	addGameContent(gc, null, s.getID());
					        }
					        else
					        	updateGameContent(gc, null, s.getID());      
						}
				        it.remove(); // avoids a ConcurrentModificationException
		    }
			///////////Parse the player
			Player p = gp.getPlayer();
			if(p!=null){
				if(!doesPlayerExist(p.getID())){
					addPlayer(p, gp.getID());
				}
				else{
					updatePlayer(p, gp.getID());
				}
				for(GameContent gc: p.getItems()){
			        if(!doesSceneStatePairExist(gc.getID())){
			        	addGameContent(gc, p.getID(), null);
			        }
			        else
			        	updateGameContent(gc, p.getID(), null);  
				}
			}
			/////////////parse the game setting
			GameSetting gs = gp.getGameSetting();
			if(gs!=null){
				if(!doesGameSettingExist(gp.getID())){
					addGameSetting(gs, user.getName(), gp);
				}
				else{
					System.out.println("update game setting");
					System.out.println("font string : " + gs.getFontString());
					System.out.println("gpidbelong to: " + gp.getID());
					updateGameSetting(gs, user.getName(), gp);
				}
			}
		}
		
	}
	
	public User getUserObject(String userName, String password)
	{
		if(!doesUserPasswordExist(userName,password))
		{
			return null;
		}
		else{
			String pw = encrypt(password);
			User user = new User(userName, pw);
			user.setGameProject(getGameProject(userName));
			return user;
		}
	}
	private Player getPlayer(GameProject gp){
		PreparedStatement ps;
		Player p = null;
		try {
			ps = con.prepareStatement(selectPlayerFromGPID);
			ps.setInt(1, gp.getID());
			ResultSet result = ps.executeQuery();
			while(result.next())
			{ //get game project
				p = new Player(result.getString(2));
				p.setID(result.getInt(1));
				getGameContentAndAddToPlayer(p);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;		
	}
	private void getSceneStateAndAddToScene(Scene s){
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(selectSceneStateFromSceneBelongTo);
			ps.setInt(1,s.getID());
			ResultSet result = ps.executeQuery();
			while(result.next())
			{ 
				SceneState ss = new SceneState(result.getString(2));
				ss.setID(result.getInt(1));
				ss.setDescription(result.getString(3));
				ss.setImagePath(result.getString(4));
				ss.setX(result.getInt(7));
				ss.setY(result.getInt(8));
				ss.setW(result.getInt(9));
				ss.setH(result.getInt(10));
				s.addSceneState(ss);
				String sceneChoiceString = result.getString(5);
				String gameChoiceString = result.getString(6);
				if(sceneChoiceString.equals("")){
					
				}
				else{
					String[] scArr = sceneChoiceString.split(",");
					for(int i = 0; i < scArr.length; ++i){
						int num = Integer.parseInt(scArr[i]);
						ss.addSceneID(num);
					}
				}
				if(gameChoiceString.equals("")){
					
				}
				else{
					String[] gcArr = gameChoiceString.split(",");
					for(int i = 0; i < gcArr.length; ++i){
						int num = Integer.parseInt(gcArr[i]);
						ss.addGameContent(num);
					}
				}
				//need to addscenechoice arrlist and addGCchoice arrlist
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void getSceneStatePairAndAddToScene(Scene s){
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(selectSceneStatePairFromSceneBelongTo);
			ps.setInt(1,s.getID());
			ResultSet result = ps.executeQuery();
			while(result.next())
			{ 
				SceneStatePair ssp = new SceneStatePair(result.getInt(2), result.getInt(3));
				ssp.setID(result.getInt(1));
				ssp.setX(result.getInt(5));
				ssp.setY(result.getInt(6));
				ssp.setW(result.getInt(7));
				ssp.setH(result.getInt(8));
				ssp.setDescription(result.getString(9));
				s.addlinkedScenes(ssp);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/** helper for reconstructing gamecontent to scene
	 */
	private void getGameContentAndAddToScene(Scene s){
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(selectGameContentFromSceneBelongTo);
			ps.setInt(1,s.getID());
			ResultSet result = ps.executeQuery();
			while(result.next())
			{ 
				GameContent gc = new GameContent(result.getString(2), result.getString(3));
				gc.setID(result.getInt(1));
				gc.setImage(result.getString(4));
				gc.setX(result.getInt(7));
				gc.setY(result.getInt(8));
				gc.setW(result.getInt(9));
				gc.setH(result.getInt(10));
				s.addGameContent(gc);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void getGameContentAndAddToPlayer(Player p){
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(selectGameContentFromPlayerBelongTo);
			ps.setInt(1,p.getID());
			ResultSet result = ps.executeQuery();
			while(result.next())
			{ 
				GameContent gc = new GameContent(result.getString(2), result.getString(3));
				gc.setID(result.getInt(1));
				gc.setImage(result.getString(4));
				gc.setX(result.getInt(7));
				gc.setY(result.getInt(8));
				gc.setW(result.getInt(9));
				gc.setH(result.getInt(10));
				p.pickup(gc);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

	private void getSceneAndAddToGP(GameProject gp){
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(selectSceneFromGPID);
			ps.setInt(1, gp.getID());
			ResultSet result = ps.executeQuery();
			while(result.next())
			{
				Scene s = new Scene(result.getString(2));
				s.setCurrentSceneState(result.getInt(3));
				s.setID(result.getInt(1));
				getSceneStateAndAddToScene(s);
				getSceneStatePairAndAddToScene(s);
				getGameContentAndAddToScene(s);
				gp.addScene(s);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private GameSetting getGameSetting(int gpIDBelongTo){
		GameSetting gs = null;
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(selectGameSetting);
			ps.setInt(1, gpIDBelongTo);
			ResultSet result = ps.executeQuery();
			while(result.next())
			{
				gs = new GameSetting();
				gs.setBackgroundImage(result.getString(2));
				gs.setReleasedButtonString(result.getString(3));
				gs.setPressedButtonImage(result.getString(4));
				gs.setFont(result.getString(5));
				gs.setTextFontSize(result.getInt(6));
				gs.setTextFontColor(result.getString(7));
				gs.setButtonFontSize(result.getInt(8));
				gs.setButtonFontColor(result.getString(9));
				gs.setInitialScene(result.getInt(10));
				gs.setInitialState(result.getInt(11));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gs;
	}
	
	private ArrayList<GameProject> getGameProject(String username){
		PreparedStatement ps;
		ArrayList<GameProject> gpArr = new ArrayList<GameProject>();
		try {
			ps = con.prepareStatement(selectGameProjectFromName);
			ps.setString(1, username);
			ResultSet result = ps.executeQuery();
			while(result.next())
			{ //get game project
				GameProject gp = new GameProject(result.getString(2));
				gp.setId(result.getInt(1));
				gp.setPlayer(getPlayer(gp));
				getSceneAndAddToGP(gp);
				gp.setGameSetting(getGameSetting(gp.getID()));
				gpArr.add(gp);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			gpArr = null;
			e.printStackTrace();
		}
		return gpArr;
	}
	/**
	 * function for adding new player
	 * @param p
	 * @param gpID
	 */
	public void addPlayer(Player p, int gpID){
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(addPlayer);
			ps.setInt(1,p.getID());
			ps.setString(2, p.getName());
			ps.setInt(3, gpID);
			ps.executeUpdate();
		//	FactoryServerGUI.addMessage("Adding product: " + productName + " to table with count 0.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	/**
	 * function for updating player
	 * @param p
	 * @param gpID
	 */
	@SuppressWarnings("unused")
	public void updatePlayer(Player p, int gpID){
		int id = p.getID();
		PreparedStatement ps1;
		PreparedStatement ps2;
		try {
			//update player
			ps2 = con.prepareStatement(updatePlayer);
			ps2.setString(1, p.getName());
			ps2.setInt(2, gpID);
			ps2.setInt(3, id);
			ps2.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * function for adding gamecontent
	 * @param gc
	 * @param pID
	 * @param sID
	 */
	public void addGameContent(GameContent gc, Integer pID, Integer sID){
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(addGameContent);
			ps.setInt(1,gc.getID());
			ps.setString(2,gc.getName());
			ps.setString(3, gc.getImagePath());
			ps.setString(4, gc.lookUp());
			if(pID == null)
				ps.setInt(5,-1);
			else
				ps.setInt(5, pID);
			if(sID == null)
				ps.setInt(6, -1);
			else
				ps.setInt(6, sID);
			ps.setInt(7, gc.getX());
			ps.setInt(8, gc.getY());
			ps.setInt(9, gc.getW());
			ps.setInt(10, gc.getH());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 	
	
	
	public void updateGameContent(GameContent gc, Integer pID, Integer sID){
		PreparedStatement ps;
		try {
			//update player
			ps = con.prepareStatement(updateGameContent);
			ps.setString(1, gc.getName());
			ps.setString(2, gc.getImagePath());
			ps.setString(3, gc.lookUp());
			if(pID == null)
				ps.setInt(4,-1);
			else
				ps.setInt(4, pID);
			if(sID == null)
				ps.setInt(5, -1);
			else
				ps.setInt(5, sID);
			ps.setInt(6, gc.getX());
			ps.setInt(7, gc.getY());
			ps.setInt(8, gc.getW());
			ps.setInt(9, gc.getH());
			ps.setInt(10, gc.getID());
			ps.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void addGameProject(GameProject gp, String gpUserBelongTo){
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(addGameProject);
			ps.setInt(1,gp.getID());
			ps.setString(2,gp.getName());
			ps.setString(3, gpUserBelongTo);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 	
	
	public void updateGameProject(GameProject gp, String gpUserBelongTo){
		PreparedStatement ps;
		try {
			//update player
			ps = con.prepareStatement(updateGameProject);
			ps.setString(1, gp.getName());
			ps.setString(2, gpUserBelongTo);
			ps.setInt(3, gp.getID());
			ps.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addScene(Scene s, int gpIDBelongTo){
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(addScene);
			ps.setInt(1,s.getID());
			ps.setString(2,s.getName());
			ps.setInt(3, s.getCurrentSceneStateNum());
			ps.setInt(4, gpIDBelongTo);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 	
	
	public void updateScene(Scene s, int gpIDBelongTo){
		PreparedStatement ps;
		try {
			//update player
			ps = con.prepareStatement(updateScene);
			ps.setString(1, s.getName());
			ps.setInt(2, s.getCurrentSceneStateNum());
			ps.setInt(3, gpIDBelongTo);
			ps.setInt(4, s.getID());
			ps.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void addSceneStatePair(SceneStatePair ssp, int sceneBelongTo){
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(addSceneStatePair);
			ps.setInt(1,ssp.getID());
			ps.setInt(2, ssp.sceneID);
			ps.setInt(3, ssp.stateID);
			ps.setInt(4, sceneBelongTo);
			ps.setInt(5, ssp.getX());
			ps.setInt(6, ssp.getY());
			ps.setInt(7, ssp.getW());
			ps.setInt(8, ssp.getH());
			ps.setString(9, ssp.getDescription());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 	
	
	public void updateSceneStatePair(SceneStatePair ssp, int sceneBelongTo){
		PreparedStatement ps;
		try {
			//update player
			ps = con.prepareStatement(updateSceneStatePair);
			ps.setInt(1, ssp.sceneID);
			ps.setInt(2, ssp.stateID);
			ps.setInt(3, sceneBelongTo);
			ps.setInt(4, ssp.getX());
			ps.setInt(5, ssp.getY());
			ps.setInt(6, ssp.getW());
			ps.setInt(7, ssp.getH());
			ps.setInt(8, ssp.getID());
			ps.setString(9, ssp.getDescription());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addSceneState(SceneState ss, int sceneID){
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(addSceneState);
			ps.setInt(1,ss.getID());
			ps.setString(2, ss.getName());
			ps.setString(3, ss.getDescriptionID());
			ps.setString(4, ss.getImageChoice());
			String sschoice="";
			for(Integer i: ss.getSceneChoices()){
				sschoice=i+",";
			}
			ps.setString(5,sschoice);
			String gcchoice="";
			for(Integer j: ss.getSceneChoices()){
				gcchoice=j+",";
			}
			ps.setString(6, gcchoice);
			ps.setInt(7, ss.getX());
			ps.setInt(8, ss.getW());
			ps.setInt(9, ss.getY());
			ps.setInt(10, ss.getH());
			ps.setInt(11, sceneID);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 	
	
	public void updateSceneState(SceneState ss, int sceneID){
		PreparedStatement ps;
		try {
			//update player
			ps = con.prepareStatement(updateSceneState);
			ps.setString(1, ss.getName());
			ps.setString(2, ss.getDescriptionID());
			ps.setString(3, ss.getImageChoice());
			String sschoice="";
			for(Integer i: ss.getSceneChoices()){
				sschoice=i+",";
			}
			ps.setString(4,sschoice);
			String gcchoice="";
			for(Integer j: ss.getSceneChoices()){
				gcchoice=j+",";
			}
			ps.setString(5, gcchoice);
			ps.setInt(6, ss.getX());
			ps.setInt(7, ss.getW());
			ps.setInt(8, ss.getY());
			ps.setInt(9, ss.getH());
			ps.setInt(10, sceneID);
			ps.setInt(11, ss.getID());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addGameSetting(GameSetting gs, String userBelongTo, GameProject gp){
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(addGameSetting);
			ps.setInt(1, gp.getID());
			ps.setString(2,gs.getBackgroundImage());
			ps.setString(3, gs.getReleasedButtonString());
			ps.setString(4, gs.getPressedButtonString());
			ps.setString(5, gs.getFontString());
			ps.setInt(6, gs.getTextFontSize());
			ps.setString(7, gs.getTextFontColor());
			ps.setInt(8, gs.getButtonFontSize());
			ps.setString(9, gs.getButtonFontColor());
			ps.setInt(10, gs.getInitialScene());
			ps.setInt(11, gs.getInitialState());
			gp.setGameSetting(gs);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 	
	//
	public void updateGameSetting(GameSetting gs, String userBelongTo, GameProject gp){
		PreparedStatement ps;
		try {
			//update player
			ps = con.prepareStatement(updateGameSetting);
			ps.setString(1, gs.getBackgroundImage());
			ps.setString(2, gs.getReleasedButtonString());
			ps.setString(3, gs.getPressedButtonString());
			ps.setString(4, gs.getFontString());
			System.out.println("in updateGameSetting fontstring: " + gs.getFontString());
			ps.setInt(5, gs.getTextFontSize());
			ps.setString(6, gs.getTextFontColor());
			ps.setInt(7, gs.getButtonFontSize());
			ps.setString(8, gs.getButtonFontColor());
			ps.setInt(9, gs.getInitialScene());
			ps.setInt(10, gs.getInitialState());
			ps.setInt(11, gp.getID());
			gp.setGameSetting(gs);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
	
}

