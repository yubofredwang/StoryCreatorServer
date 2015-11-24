package tip.storycreator.client;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoadingWindow extends JPanel{
	private static final long serialVersionUID = 5259964608858901563L;
	{
//		setUndecorated(true);
//		setLocationRelativeTo(null);
//		setBackground(new Color(35, 152, 208));
//		setSize(640, 480);
//		setModal(true);
		
		
		try {
			File file = new File("resources/animation/loading.gif");
			String path = "file://" + file.getAbsolutePath();
			URL loadingURL = new URL(path);
			Icon gifIcon = new ImageIcon(loadingURL);
			JLabel gifLabel = new JLabel(gifIcon);
			this.add(gifLabel);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	

	

}
