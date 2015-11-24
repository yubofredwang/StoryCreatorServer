package tip.storycreator.server.Signal;


public class SignUpSuccessSignal extends Signal{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8010724363127567779L;
	
	public SignUpSuccessSignal(boolean in)
	{
		SignUpSuccess = in;
	}
	public boolean SignUpSuccess;
	
	
}
