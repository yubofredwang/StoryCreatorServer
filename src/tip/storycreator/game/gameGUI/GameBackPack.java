
package tip.storycreator.game.gameGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import library.FontLibrary;
import library.ImageLibrary;
import sun.net.www.content.image.jpeg;
import tip.storycreator.client.PseudoButton;
import tip.storycreator.game.GameContent;
import tip.storycreator.server.CustomGUI.PaintedPanel;

public class GameBackPack extends JDialog{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1321144775302498412L;
	private static final Font TitleFont = FontLibrary.getFont("resources/fonts/Jurassic Park.ttf", Font.PLAIN, 40);
	private static final Font ContentFont = FontLibrary.getFont("resources/fonts/Jurassic Park.ttf", Font.PLAIN, 22);
	private static final String Title = new String("MY BAG");
	private JLabel TitleLabel = new JLabel(Title);
	private JPanel OverallPanel;
	private JPanel LowerPanel;
	private JPanel UpperPanel;
	private Image image;
	private JScrollPane jsp;
	private ArrayList<GameContent> mGameObjectList;
//	private ArrayList<JButton> mJButtonList;
	
	
	class GetIndexActionListener extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent arg0) {
			
		}
		
	}
	
	public GameBackPack(ArrayList<GameContent> GameObjectlist){
		setModal(true);
		 mGameObjectList = GameObjectlist;
		 if(mGameObjectList == null)
		 {
			 System.out.println("bag null");
			 mGameObjectList = new ArrayList<GameContent>();
		 }
		 else
		 {
			 System.out.println("bag" + mGameObjectList.size());
			 
		 }
		 //mJButtonList = new ArrayList<JButton>();
		 SetUpGUI();
//		 addInventoryToBag();
		 
	 }
	
//	
//	private void addInventoryToBag() {
//		for(int i = 0; i < mGameObjectList.size(); i++)
//		{
//			GameObject go = mGameObjectList.get(i);
//			PseudoButton pjb = new PseudoButton(go.getName(), null, null);
//			
//		}
//	}

	

	public void SetUpGUI()
	{
		image = ImageLibrary.getImage("resources/backpackbackground.png");
		OverallPanel = new PaintedPanel(image);
		
		TitleLabel.setAlignmentX(SwingConstants.CENTER);
		TitleLabel.setFont(TitleFont);
//		TitleLabel.setBorder(BorderFactory.createEmptyBorder());
		
		
		LowerPanel = new JPanel();
		UpperPanel = new JPanel();
		UpperPanel.setBorder(new EmptyBorder(new Insets(40, 0 ,30, 0)));
		UpperPanel.add(TitleLabel);
		UpperPanel.setOpaque(false);
		UpperPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		GridLayout gl = new GridLayout(3,3,3,3);
	
		LowerPanel.setLayout(gl);
		for(int i = 0; i<9; i++)
		{
			if(i <= mGameObjectList.size()-1)
			{
				GameContent gc = mGameObjectList.get(i);
				Image image = ImageLibrary.getImage(gc.getImagePath());
				PseudoButton jp = new PseudoButton(gc.getName(),image , image, gc);
				jp.addMouseListener(new lookupListener(gc.lookUp(), jp));
				jp.setOpaque(false);
				LowerPanel.add(jp);
			}
			else
			{
				PseudoButton jp = new PseudoButton("", ImageLibrary.getImage("resources/sucai.png"), ImageLibrary.getImage("resources/sucai.png"));
				jp.setOpaque(false);
				LowerPanel.add(jp);
			}
		}
		LowerPanel.setOpaque(false);
		LowerPanel.setBorder(new EmptyBorder(new Insets(0, 30 ,45, 30)));
		jsp = new JScrollPane(LowerPanel);
		jsp.setBorder(new EmptyBorder(new Insets(0, 30 ,45, 30)));
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setOpaque(false);
		jsp.getViewport().setView(LowerPanel);
		OverallPanel.setLayout(new BorderLayout());
		OverallPanel.add(UpperPanel, BorderLayout.NORTH);
		OverallPanel.add(LowerPanel, BorderLayout.CENTER);
		this.setResizable(false);
		this.setSize(new Dimension(300,400));
		this.add(OverallPanel);
		this.setVisible(true);
	}
	
	class lookupListener implements MouseListener
	{
		JDialog jdg;
		String description;
		PseudoButton parent;
		public lookupListener(String des, PseudoButton parent) {
			// TODO Auto-generated constructor stub
			des = description;
			this.parent = parent;
			jdg = new JDialog();
			//jdg.setUndecorated(true);
			JPanel jPanel = new JPanel();
			JLabel show = new JLabel(description);
			//show.setOpaque(false);
			jPanel.add(show);
			jdg.add(jPanel);
			jdg.pack();
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			parent.setBorder(null);
			jdg.setVisible(false);
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			//System.outs.print("ininininin");
			//parent.setBorder(new LineBorder(Color.black));
			jdg.setLocation(e.getXOnScreen(), e.getYOnScreen()-10);
			jdg.setVisible(true);
			//jdg.repaint();
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(SwingUtilities.isRightMouseButton ( e ))
			{
				
			}
			else if(SwingUtilities.isLeftMouseButton ( e ))
			{
				
			}
		}
	}
	
	public static void main(String[] args)
	{
		new GameBackPack(null);
	}
}
