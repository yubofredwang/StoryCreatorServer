package tip.storycreator.client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import library.ImageLibrary;
import tip.storycreator.game.GameContent;

public class PseudoButton extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3652788831533661766L;
	private Image toDraw = null;
	private final Image mUp;
	private final Image mDown;
	private GameContent mGameContent;
	
	public PseudoButton(String name, Image inUp, Image inDown) {
		super();
		setText(name);
		toDraw = mUp = inUp;
		mDown = inDown;
		setHorizontalAlignment(SwingConstants.CENTER);
		setHorizontalTextPosition(JLabel.CENTER);
		setVerticalTextPosition(JLabel.CENTER);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				toDraw = mDown;
				repaint();
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				toDraw = mUp;
				repaint();
			}
		});
		
		setOpaque(true);
		setBackground(new Color(0,0,0,0));
	}
	
	public PseudoButton(String name, Image inUp, Image inDown, GameContent gc) {
		super();
		setText(name);
		mGameContent= gc;
		
		if(inUp == null)
		{
			mUp = mDown = ImageLibrary.getImage("resources/sucai.png");
		}
		else
		{
			toDraw = mUp = inUp;
			mDown = inDown;
		}
		
		setHorizontalAlignment(SwingConstants.CENTER);
		setHorizontalTextPosition(JLabel.CENTER);
		setVerticalTextPosition(JLabel.CENTER);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				toDraw = mDown;
				repaint();
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				toDraw = mUp;
				repaint();
			}
		});
		
		setOpaque(true);
		setBackground(new Color(0,0,0,0));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if(toDraw != null)
		{
			g.drawImage(toDraw, 0, 0, getWidth(), getHeight(), null);
		}
		super.paintComponent(g);
	}
	
	public GameContent getContent()
	{
		return mGameContent;
	}
	
	@Override
	protected void paintBorder(Graphics g) {
		//paint no border
	}

}
