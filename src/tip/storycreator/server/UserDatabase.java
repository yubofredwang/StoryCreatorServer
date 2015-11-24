package tip.storycreator.server;

import java.io.Serializable;
import java.util.ArrayList;

public class  UserDatabase implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ArrayList<User> udb;
	private static MySQLDriver mysql;
	static{
		mysql = new MySQLDriver();
	}
	//
	/**log in for normal user**/
	public User LogIn(String username, String password){
		String encryptedPassword = encrypt(password);
		for(User currUser: udb){
			if(currUser.getName().equals(username) && currUser.getPassword().equals(encryptedPassword)){
				return currUser;
			}
		}
		return null;
	}
	
	/** use hash function to encrypt password **/
	public static String encrypt(String password){
		//do hash password
		return password;
	}
	public static void connect(){
		mysql.connect();
	}
	
	/**
	 * create new user, parse it into mysql
	 * @param username
	 * @param password
	 */
	public static void createNewUser(String username, String password){
		if(udb.size() > 0){}
		else{
			for(User existingUser: udb){
				if(existingUser.getName().equals(username)){//username or email already exists!
					return;
				}
			}
		}
		String encryptedPassword = encrypt(password);
		User newUser = new User(username, encryptedPassword);
		udb.add(newUser);
		mysql.addUserName(username, encryptedPassword);
		System.out.println("create user successfully");
	}
	
	
	/** change to new password**/
	public void changePassword(String username, String currPassword, String currPasswordConfirm, String newPassword){
		for(User existingUser: udb){
			if(existingUser.getName().equals(username) && existingUser.getPassword().equals(currPassword) && existingUser.getPassword().equals(currPasswordConfirm)  ){//username or email exists!
				newPassword = encrypt(newPassword);
				existingUser.setPassword(newPassword);
			}
		}
	}
	
	
}
