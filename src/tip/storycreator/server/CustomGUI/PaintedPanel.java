package tip.storycreator.server.CustomGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class PaintedPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5622638441247096273L;
	
	private Image backgroundImage;
	
	public PaintedPanel(Image backgroundImage)
	{
		setBackground(new Color(0,0,0,0));
		this.setOpaque(true);
		this.backgroundImage = backgroundImage;

	}
	
	public void setbackgroundImage(Image image)
	{
		this.backgroundImage = image;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if(backgroundImage != null)
		{
			g.drawImage(backgroundImage, 0, 0,this.getWidth(),this.getHeight(), null);
		}
		super.paintComponent(g);
	}
}
