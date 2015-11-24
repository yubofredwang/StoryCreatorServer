package tip.storycreator.game;

public class GameContent extends GameObject
{

	private int x, y, w, h;
	
	public int usability = 1;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4419489739341394733L;
	
	/**
	 * Image file path. This should the first priority when asked for game content image.
	 * Only when this is null, using mImage. 
	 */
	private String imagePath = null;
	
	
	/**
	 * Game Content Description
	 */
	private String description;
	
	/**
	 * Construct a game content with a given name and a given description
	 * @param name
	 * @param description
	 */
	public GameContent(String name, String description)
	{
		super(name);
		this.description = description;
		x = y = w = h = 0;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	/**
	 * Change the game content description to the given one
	 * @param descrption the descriptive text that would be set as the description of the game content
	 */
	public void setDescription(String descrption)
	{
		this.description = descrption;
	}
	
	/**
	 * Get the description of the game content 
	 * @return String the description
	 */
	public String lookUp()
	{
		return description;
	}
	
	/**
	 * Set the image path
	 * @param path
	 */
	public void setImage(String path)
	{
		this.imagePath = path;
	}
	
	/**
	 * Return the stored image file path of the game content
	 * @return String
	 */
	public String getImagePath()
	{
		return imagePath;
	}
	
	public String toString()
	{
		return getName();
	}
}
