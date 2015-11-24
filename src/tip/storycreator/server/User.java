package tip.storycreator.server;

import java.io.Serializable;
import java.util.ArrayList;

import tip.storycreator.game.GameProject;

public class User implements Serializable{
	private static final long serialVersionUID = -7647614169545229620L;
	String userName;
	String encryptedPassword;
	ArrayList<GameProject> mGameProject; ///need to implement this class


	public User(String userName, String encryptedPassword){
		this.userName = userName;
		this.encryptedPassword = encryptedPassword;
		mGameProject = new ArrayList<GameProject>();
	}
	public void setGameProject(ArrayList<GameProject> gpArr){
		this.mGameProject = gpArr;
	}
	public ArrayList<GameProject> getGameProject(){
		return mGameProject;
	}	
	public String getName(){
		return userName;
	}
	public String getPassword(){
		return encryptedPassword;
	}
	
	public void setPassword(String newPassword){
		this.encryptedPassword = newPassword;
	}
	
	/**GameProject to be implemented**/
	/** uncomment the following once its implemented**/
	public GameProject getGameProject(String name){
		for(GameProject gp: mGameProject){
			if(gp.getName().equals(name))
				return gp;
		}
		return null;
	}
	public void addGameProject(GameProject gp){
		mGameProject.add(gp);
	}
	public void removeGameProject(GameProject gp){
		mGameProject.remove(gp);
	}
	@SuppressWarnings("unused")
	public void createNewProject(String projectName){
		//loop through arrayList of mGameProject to check if projectname already exists
		GameProject gp = new GameProject(projectName);
	}
//	public GameProject openProject(String projectName){
//		for(GameProject gp: mGameProject){
//			
//		}
//		return gp;
//	}
	
	public ArrayList<GameProject> getGameProjects(){
		return mGameProject;
	}
}
