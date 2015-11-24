package tip.storycreator.server.Signal;

import tip.storycreator.server.User;

public class UserSignal extends Signal{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6886648817899758959L;

	private User user;
	public UserSignal(User s) {
		user = s;
	}

	public void setUser(User in)
	{
		user = in;
	}
	
	public User getUser()
	{
		return user;
	}
}
