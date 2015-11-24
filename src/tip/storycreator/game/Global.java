package tip.storycreator.game;

import java.util.Random;

public class Global {
	
	/**
	 * Unique random generator.
	 */
	private final static Random rGenerator = new Random(System.currentTimeMillis());
	

	/**
	 * Get unique number through the entire game.
	 */
	public static int getRandom() 
	{
		return rGenerator.nextInt();
	}
}
