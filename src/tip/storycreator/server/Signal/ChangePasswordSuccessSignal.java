package tip.storycreator.server.Signal;

public class ChangePasswordSuccessSignal extends Signal{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2421466671177816074L;
	public boolean changepasswordsuccess;
	public ChangePasswordSuccessSignal(boolean in)
	{
		changepasswordsuccess = in;
	}
}
