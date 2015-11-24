package tip.storycreator.server;

public class mysqltest {
	public static void main(String [] args){
		MySQLDriver mysql = new MySQLDriver();
		mysql.connect();
		
		//User u = mysql.createNewUser("thanakorn", "password");
		//u.addGameProject(new GameProject("gp2"));
//		User u = mysql.createNewUser("thanakorn", "password");	
//		GameProject gp = new GameProject("myGP");
//		gp.setPlayer(new Player("player1"));
//		
//		
//		
//		GameSetting gs = new GameSetting();
//		gs.setInitialScene(1);
//		gs.setInitialState(0);
//		gp.setGameSetting(gs);
//		
//		Scene s = new Scene("scenename");
//		SceneStatePair ssp = new SceneStatePair(345, 123);
//		SceneState ss = new SceneState("scenestatename");
//		ss.addGameContent(2);
//		GameContent gc = new GameContent("gamecontentname","description");
//		
//		s.addGameContent(gc);
//		s.addSceneState(ss);
//		s.addlinkedScenes(ssp);
//		gp.addScene(s);
//		
//		if(u == null)
//			System.out.println("u null");
//		if(gp == null)
//			System.out.println("gp null");
//		u.addGameProject(gp);
//		
		
		int[] arr = new int[100];
		arr['a'] = 2;
		System.out.println(arr[48]);
		//User u = mysql.LogIn("thanakorn", "newPassword");
		User newUser = mysql.createNewUser("user3", "mypassowrd");
		if(newUser == null)
			System.out.println("nu null");
//		for(GameProject gp: u.getGameProjects()){
//			System.out.println("gp name: " + gp.getName());
//			HashMap<Integer, Scene> scenemap = gp.getSceneMap();
//			Iterator it = scenemap.entrySet().iterator();
//			while (it.hasNext()) {
//				        Map.Entry pair = (Map.Entry)it.next();
//				        int id = (Integer) pair.getKey();
//				        Scene s= (Scene) pair.getValue();
//				        System.out.println("scene name: " + s.getName());
//				        
//			}
//		}
		//GameProject gp = u.getGameProject("gameproject1");
		//gp.addScene(new Scene("scene1"));
//		GameProject gp = u.getGameProject("newname3");
//		if(gp==null)
//			System.out.println("gp null");
//		GameSetting gs = gp.getGameSetting();
//		gs.setBackgroundImage("bgimagestring");
//		if(gs==null)
//			System.out.println("gs null");
//		gs.setFont("latestfont");
//		gs.setTextFontColor("fontcolor");
		
		
		
//		GameSetting gs = gp.getGameSetting();
//		gs.setBackgroundImage("new background");
//		gs.setFont("new font");
		mysql.saveToDatabase(newUser);
		
		
//		GameContent gc = new GameContent("gc", "description");
//		Player p = new Player("player");
//		GameProject gp = new GameProject("gp");
//		mysql.addGameContent(gc, 8, 9);
//		mysql.addPlayer(p, 8);
//		mysql.addGameProject(gp, "thanakorns");
		
	}
}
