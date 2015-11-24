package tip.storycreator.server.Signal;
 

public class LoginSignal extends Signal{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5193130446093224195L;

	
	private String mUser;
	private String mPassword;
	
	public LoginSignal(String inUser, String inPassword)
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