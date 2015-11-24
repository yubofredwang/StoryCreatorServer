package tip.storycreator.server.CustomGUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PaintedOptionPanel extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4724643503283282256L;
	String content;
	JButton yes;
	JButton no;
	BufferedImage image;
	BufferedImage image1;
	private void readImage () {
		try {
			image = ImageIO.read(new File("resources/ButtonIcon.png"));
			image1 = ImageIO.read(new File("resources/BlueButtonIconPressed.png"));
			yes = new JButton("yes");
			no = new JButton("no");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
    }
	
	public PaintedOptionPanel(String content) {
		this.content = content;
		readImage();
		JPanel panel = new JPanel();
		JLabel label = new JLabel(content);
		panel.add(label);
		panel.add(yes);
		panel.add(no);
        add(panel);
        setVisible(true);
	}

    public static void main(String[] args) {       
             new PaintedOptionPanel("sad");
    }

}
