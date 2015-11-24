package tip.storycreator.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import library.FontLibrary;
import library.ImageLibrary;

public class DefaultButton extends JButton{
	private static final long serialVersionUID = -780861438858397848L;
	
	/**
	 * for default button:
	 * DefaultButton("Create",
	 *				  ImageLibrary.getImage("resources/create_file/blue_button.png"),
	 *				  ImageLibrary.getImage("resources/create_file/blue_button_pressed.png"));
	 */
	final Font font = FontLibrary.getFont(Constants.pathname + "fonts/System San Francisco Display Regular.ttf",
			Font.PLAIN, 18);
	final Font font1 = FontLibrary.getFont(Constants.pathname + "fonts/System San Francisco Display Bold.ttf",
			Font.PLAIN, 18);
	
	public DefaultButton(String name, Image inUp, Image inDown){
		super(name);
		Icon buttonIcon = new ImageIcon(inUp);
		Icon buttonPressedIcon = new ImageIcon(inDown);
		
//		setOpaque(true);
//		setBackground(new Color(0,0,0));
		setIcon(buttonIcon);
		setPressedIcon(buttonPressedIcon);
		setBorder(null);
		setFont(font);
		setPreferredSize(new Dimension(buttonIcon.getIconWidth(), buttonIcon.getIconHeight()));
		setHorizontalTextPosition(SwingConstants.CENTER);
		setForeground(Color.white);
		
		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				setFont(getFont().deriveFont(Font.BOLD));
			}
			public void mouseExited(MouseEvent arg0){
				setFont(getFont().deriveFont(Font.PLAIN));
			}
		});
	}
	
	public DefaultButton(String name, int width, int height){
		super(name);
		Image buttonImage = ImageLibrary.getImage("resources/create_file/blue_button.png");
		Image buttonPressedImage = ImageLibrary.getImage("resources/create_file/blue_button_pressed.png");
		buttonImage = buttonImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		buttonPressedImage = buttonPressedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		Icon buttonIcon = new ImageIcon(buttonImage);
		Icon buttonPressedIcon = new ImageIcon(buttonPressedImage);
		
		setIcon(buttonIcon);
		setPressedIcon(buttonPressedIcon);
		setBorder(null);
		setFont(font);
		setPreferredSize(new Dimension(buttonIcon.getIconWidth(), buttonIcon.getIconHeight()));
		setHorizontalTextPosition(SwingConstants.CENTER);
		setForeground(Color.white);
		
		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				setFont(getFont().deriveFont(Font.BOLD));
			}
			public void mouseExited(MouseEvent arg0){
				setFont(getFont().deriveFont(Font.PLAIN));
			}
		});
	}
	
	public void setPreferredSize(Dimension preferredSize)
	{
//		{
//			ImageIcon imageicon = (ImageIcon) this.getPressedIcon();
//			Image image = imageicon.getImage();
//			image = image.getScaledInstance(preferredSize.width, preferredSize.height, Image.SCALE_SMOOTH);
//			this.setPressedIcon(new ImageIcon(image));
//		}
//		
//		{
//			ImageIcon imageicon = (ImageIcon) this.getIcon();
//			Image image = imageicon.getImage();
//			image = image.getScaledInstance(preferredSize.width, preferredSize.height, Image.SCALE_SMOOTH);
//			this.setIcon(new ImageIcon(image));
//		}
		super.setPreferredSize(preferredSize);
	}

	
	public DefaultButton(String name, Image inUp, Image inDown, int width, int height){
		super(name);
		Image buttonImage = inUp;
		Image buttonPressedImage = inDown;
		buttonImage = buttonImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		buttonPressedImage = buttonPressedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		Icon buttonIcon = new ImageIcon(buttonImage);
		Icon buttonPressedIcon = new ImageIcon(buttonPressedImage);
		
		setIcon(buttonIcon);
		setPressedIcon(buttonPressedIcon);
		setBorder(null);
		setFont(font);
		setPreferredSize(new Dimension(buttonIcon.getIconWidth(), buttonIcon.getIconHeight()));
		setHorizontalTextPosition(SwingConstants.CENTER);
		setForeground(Color.white);
		
		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				setFont(getFont().deriveFont(Font.BOLD));
			}
			public void mouseExited(MouseEvent arg0){
				setFont(getFont().deriveFont(Font.PLAIN));
			}
		});
	}
}
