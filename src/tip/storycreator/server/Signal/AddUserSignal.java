package tip.storycreator.server.Signal;

public class AddUserSignal extends Signal{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1437990609945350449L;
	

	private String mUser;
	private String mPassword;
	
	public AddUserSignal(String inUser, String inPassword)
	{
		mUser = inUser;
		mPassword = inPassword;
	}
	
	public String getUser()
	{
		return mUser;
	}
	
	public String getPassword()
	{
		return mPassword;
	}
}
