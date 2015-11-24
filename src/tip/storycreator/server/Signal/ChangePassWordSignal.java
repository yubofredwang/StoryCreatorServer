package tip.storycreator.server.Signal;

public class ChangePassWordSignal extends Signal{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6145079353107326193L;
	private String mUser;
	private String oldPassword;
	private String newPassword;
	
	public ChangePassWordSignal(String inUser,String inoldPassword,String innewPassword)
	{
		mUser = inUser;
		oldPassword = inoldPassword;
		newPassword = innewPassword;
	}
	
	public String getUser()
	{
		return mUser;
	}
	
	public String getoldPassword()
	{
		return oldPassword;
	}
	
	public String getnewPassword()
	{
		return newPassword;
	}
}
