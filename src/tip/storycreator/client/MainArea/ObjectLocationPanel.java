package tip.storycreator.client.MainArea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import library.FontLibrary;
import tip.storycreator.client.Constants;
import tip.storycreator.game.GameContent;
import tip.storycreator.game.SceneState;
import tip.storycreator.game.SceneStatePair;
import tip.storycreator.game.gameGUI.GamePanel;

public class ObjectLocationPanel extends JPanel{

	private static final long serialVersionUID = 6569831013567127417L;
	
	private static final String SCENESTATE = "SCENESTATE";
	private static final String SCENESTATEPAIR = "SCENESTATEPAIR";
	private static final String GAMECONTENT = "GAMECONTENT";
	
	private JTextField xfield;
	private JTextField yfield;
	private JTextField wfield;
	private JTextField hfield;
	private JSlider jsldHortX;
	private JSlider jsldHortY;
	private JSlider jsldHortW;
	private JSlider jsldHortH;
	private SceneStatePair mSceneStatePair = null;
	private SceneState mSceneState = null;
	private GameContent mgContent = null;
	private String type; 
	private int ix, iy, iw, ih;
	//private Vector<Thread> runningThread = new Vector<Thread>();
	Boolean run = false;
	
	private GamePanel mGamePanel;
	
	public void stopThread()
	{
		run = false;
	}

	ObjectLocationPanel(SceneState st, GamePanel gp) {
		type = SCENESTATE;
		mSceneState = st;
		mGamePanel = gp;
		ix = st.getX();
		iy = st.getY();
		iw = st.getW();
		ih = st.getH();
		DescriptionUpdateValue dUV = new DescriptionUpdateValue(mSceneState);
		instaniation("Description");
		dUV.setFieldAndSlider(xfield, yfield, jsldHortX, jsldHortY);
		dUV.start();
	}
	ObjectLocationPanel(SceneStatePair ssp, GamePanel gp) {
		type = SCENESTATEPAIR;
		mSceneStatePair = ssp;
		mGamePanel = gp;
		ix = ssp.getX();
		iy = ssp.getY();
		iw = ssp.getW();
		ih = ssp.getH();
		SSPUpdateValue sspUV = new SSPUpdateValue(ssp);
		instaniation(mSceneStatePair.getDescription());
		sspUV.setFieldAndSlider(xfield, yfield, jsldHortX, jsldHortY);
		sspUV.start();
	}
	ObjectLocationPanel(GameContent gc, GamePanel gp) {
		type = GAMECONTENT;
		mgContent = gc;
		mGamePanel = gp;
		ix = gc.getX();
		iy = gc.getY();
		iw = gc.getW();
		ih = gc.getH();
		GameContentUpdateValue gcUV = new GameContentUpdateValue(mgContent);
		instaniation(mgContent.getName());
		gcUV.setFieldAndSlider(xfield, yfield, jsldHortX, jsldHortY);
		gcUV.start();
	}

	private void instaniation(String objectName)
	{
		final Font font = FontLibrary.getFont(Constants.pathname + "fonts/System San Francisco Display Regular.ttf",
				Font.PLAIN, 12);
		UIManager.put("Slider.font", font);
		UIManager.put("Label.font", font);
		UIManager.put("TextField.font", font);
		setPreferredSize(new Dimension(230, 280));
		setMaximumSize(new Dimension(230, 280));
		setMinimumSize(new Dimension(230, 280));
		setLayout(new GridLayout(8,1));
		setBorder(new TitledBorder(objectName));
		setBackground(Color.WHITE);
		JPanel xPanel = new JPanel(new GridBagLayout());
		xPanel.setBackground(Color.WHITE);
		JLabel x = new JLabel("Xaxis: ");
		xfield = new JTextField(5);
		xfield.setText(Integer.toString(ix));
		JLabel xlabel = new JLabel("   0 ~ 860");
		xlabel.setForeground(Color.GRAY);
		jsldHortX = new JSlider(JSlider.HORIZONTAL);
		jsldHortX.setMaximum(860);
		jsldHortX.addChangeListener(new SliderChangeListener(jsldHortX, xfield));
		jsldHortX.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				int offset = jsldHortX.getValue();
				if(type.equals(SCENESTATE))
				{
					mSceneState.setX(offset);
					mGamePanel.refreshLocation(mSceneState.getID(), mSceneState.getX(), mSceneState.getY(), mSceneState.getW(), mSceneState.getH());
				}
				else if(type.equals(SCENESTATEPAIR))
				{
					mSceneStatePair.setX(offset);
					mGamePanel.refreshLocation(mSceneStatePair.getID(), mSceneStatePair.getX(), mSceneStatePair.getY(), mSceneStatePair.getW(), mSceneStatePair.getH());
				}
				else if(type.equals(GAMECONTENT))
				{
					mgContent.setX(offset);
					mGamePanel.refreshLocation(mgContent.getID(), mgContent.getX(), mgContent.getY(), mgContent.getW(), mgContent.getH());
				}
			}
		});
		xfield.addActionListener(new InputChangeListener(xfield, jsldHortX));
		xfield.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int offset = Integer.parseInt(xfield.getText());
				if(type.equals(SCENESTATE))
				{
					mSceneState.setX(offset);
					mGamePanel.refreshLocation(mSceneState.getID(), mSceneState.getX(), mSceneState.getY(), mSceneState.getW(), mSceneState.getH());
				}
				else if(type.equals(SCENESTATEPAIR))
				{
					mSceneStatePair.setX(offset);
					mGamePanel.refreshLocation(mSceneStatePair.getID(), mSceneStatePair.getX(), mSceneStatePair.getY(), mSceneStatePair.getW(), mSceneStatePair.getH());
				}
				else if(type.equals(GAMECONTENT))
				{
					mgContent.setX(offset);
					mGamePanel.refreshLocation(mgContent.getID(), mgContent.getX(), mgContent.getY(), mgContent.getW(), mgContent.getH());
				}
			}
		});
		jsldHortX.setValue(ix);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		xPanel.add(x, c);
		c.gridx = 1;
		c.gridy = 1;
		xPanel.add(xfield, c);
		c.gridx = 2;
		c.gridy = 1;
		xPanel.add(xlabel,c);
		add(xPanel);
		add(jsldHortX);
		
		
		JPanel yPanel = new JPanel(new GridBagLayout());
		yPanel.setBackground(Color.WHITE);
		JLabel y = new JLabel("Yaxis: ");
		yfield = new JTextField(5);
		yfield.setText(Integer.toString(iy));
		JLabel ylabel = new JLabel("   0 ~ 723");
		ylabel.setForeground(Color.GRAY);
		jsldHortY = new JSlider(JSlider.HORIZONTAL);
		jsldHortY.setMaximum(723);
		jsldHortY.addChangeListener(new SliderChangeListener(jsldHortY, yfield));
		jsldHortY.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				int offset = jsldHortY.getValue();
				if(type.equals(SCENESTATE))
				{
					mSceneState.setY(offset);
					mGamePanel.refreshLocation(mSceneState.getID(), mSceneState.getX(), mSceneState.getY(), mSceneState.getW(), mSceneState.getH());
				}
				else if(type.equals(SCENESTATEPAIR))
				{
					mSceneStatePair.setY(offset);
					mGamePanel.refreshLocation(mSceneStatePair.getID(), mSceneStatePair.getX(), mSceneStatePair.getY(), mSceneStatePair.getW(), mSceneStatePair.getH());
				}
				else if(type.equals(GAMECONTENT))
				{
					mgContent.setY(offset);
					mGamePanel.refreshLocation(mgContent.getID(), mgContent.getX(), mgContent.getY(), mgContent.getW(), mgContent.getH());
				}
			}
		});
		yfield.addActionListener(new InputChangeListener(yfield, jsldHortY));
		yfield.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int offset = Integer.parseInt(yfield.getText());
				if(type.equals(SCENESTATE))
				{
					mSceneState.setY(offset);
					mGamePanel.refreshLocation(mSceneState.getID(), mSceneState.getX(), mSceneState.getY(), mSceneState.getW(), mSceneState.getH());
				}
				else if(type.equals(SCENESTATEPAIR))
				{
					mSceneStatePair.setY(offset);
					mGamePanel.refreshLocation(mSceneStatePair.getID(), mSceneStatePair.getX(), mSceneStatePair.getY(), mSceneStatePair.getW(), mSceneStatePair.getH());
				}
				else if(type.equals(GAMECONTENT))
				{
					mgContent.setY(offset);
					mGamePanel.refreshLocation(mgContent.getID(), mgContent.getX(), mgContent.getY(), mgContent.getW(), mgContent.getH());
				}
			}
		});
		jsldHortY.setValue(iy);
		c.gridx = 0;
		c.gridy = 2;
		yPanel.add(y, c);
		c.gridx = 1;
		c.gridy = 2;
		yPanel.add(yfield, c);
		c.gridx = 2;
		c.gridy = 2;
		yPanel.add(ylabel,c);
		add(yPanel);
		add(jsldHortY);
		
		
		
		JPanel wPanel = new JPanel(new GridBagLayout());
		wPanel.setBackground(Color.WHITE);
		JLabel w = new JLabel("Width: ");
		wfield = new JTextField(5);
		wfield.setText(Integer.toString(iw));
		JLabel wlabel = new JLabel("   0 ~ 860");
		wlabel.setForeground(Color.GRAY);
		jsldHortW = new JSlider(JSlider.HORIZONTAL);
		jsldHortW.setMaximum(860);
		jsldHortW.addChangeListener(new SliderChangeListener(jsldHortW, wfield));
		jsldHortW.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				int offset = jsldHortW.getValue();
				if(type.equals(SCENESTATE))
				{
					mSceneState.setW(offset);
					mGamePanel.refreshLocation(mSceneState.getID(), mSceneState.getX(), mSceneState.getY(), mSceneState.getW(), mSceneState.getH());
				}
				else if(type.equals(SCENESTATEPAIR))
				{
					mSceneStatePair.setW(offset);
					mGamePanel.refreshLocation(mSceneStatePair.getID(), mSceneStatePair.getX(), mSceneStatePair.getY(), mSceneStatePair.getW(), mSceneStatePair.getH());
				}
				else if(type.equals(GAMECONTENT))
				{
					mgContent.setW(offset);
					mGamePanel.refreshLocation(mgContent.getID(), mgContent.getX(), mgContent.getY(), mgContent.getW(), mgContent.getH());
				}
			}
		});
		wfield.addActionListener(new InputChangeListener(wfield, jsldHortW));
		wfield.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int offset = Integer.parseInt(wfield.getText());
				if(type.equals(SCENESTATE))
				{
					mSceneState.setW(offset);
					mGamePanel.refreshLocation(mSceneState.getID(), mSceneState.getX(), mSceneState.getY(), mSceneState.getW(), mSceneState.getH());
				}
				else if(type.equals(SCENESTATEPAIR))
				{
					mSceneStatePair.setW(offset);
					mGamePanel.refreshLocation(mSceneStatePair.getID(), mSceneStatePair.getX(), mSceneStatePair.getY(), mSceneStatePair.getW(), mSceneStatePair.getH());
				}
				else if(type.equals(GAMECONTENT))
				{
					mgContent.setW(offset);
					mGamePanel.refreshLocation(mgContent.getID(), mgContent.getX(), mgContent.getY(), mgContent.getW(), mgContent.getH());
				}
			}
		});
		jsldHortW.setValue(iw);
		c.gridx = 0;
		c.gridy = 3;
		wPanel.add(w, c);
		c.gridx = 1;
		c.gridy = 3;
		wPanel.add(wfield, c);
		c.gridx = 2;
		c.gridy = 3;
		wPanel.add(wlabel,c);
		add(wPanel);
		add(jsldHortW);
		
		JPanel hPanel = new JPanel(new GridBagLayout());
		hPanel.setBackground(Color.WHITE);
		JLabel h = new JLabel("Height: ");
		hfield = new JTextField(5);
		hfield.setText(Integer.toString(ih));
		JLabel hlabel = new JLabel("   0 ~ 723");
		hlabel.setForeground(Color.GRAY);
		jsldHortH = new JSlider(JSlider.HORIZONTAL);
		jsldHortH.setMaximum(723);
		jsldHortH.addChangeListener(new SliderChangeListener(jsldHortH, hfield));
		jsldHortH.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				int offset = jsldHortH.getValue();
				if(type.equals(SCENESTATE))
				{
					mSceneState.setH(offset);
					mGamePanel.refreshLocation(mSceneState.getID(), mSceneState.getX(), mSceneState.getY(), mSceneState.getW(), mSceneState.getH());
				}
				else if(type.equals(SCENESTATEPAIR))
				{
					mSceneStatePair.setH(offset);
					mGamePanel.refreshLocation(mSceneStatePair.getID(), mSceneStatePair.getX(), mSceneStatePair.getY(), mSceneStatePair.getW(), mSceneStatePair.getH());
				}
				else if(type.equals(GAMECONTENT))
				{
					mgContent.setH(offset);
					mGamePanel.refreshLocation(mgContent.getID(), mgContent.getX(), mgContent.getY(), mgContent.getW(), mgContent.getH());
				}
			}
		});
		hfield.addActionListener(new InputChangeListener(hfield, jsldHortH));
		hfield.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int offset = Integer.parseInt(hfield.getText());
				if(type.equals(SCENESTATE))
				{
					mSceneState.setH(offset);
					mGamePanel.refreshLocation(mSceneState.getID(), mSceneState.getX(), mSceneState.getY(), mSceneState.getW(), mSceneState.getH());
				}
				else if(type.equals(SCENESTATEPAIR))
				{
					mSceneStatePair.setH(offset);
					mGamePanel.refreshLocation(mSceneStatePair.getID(), mSceneStatePair.getX(), mSceneStatePair.getY(), mSceneStatePair.getW(), mSceneStatePair.getH());
				}
				else if(type.equals(GAMECONTENT))
				{
					mgContent.setH(offset);
					mGamePanel.refreshLocation(mgContent.getID(), mgContent.getX(), mgContent.getY(), mgContent.getW(), mgContent.getH());
				}
			}
		});
		jsldHortH.setValue(ih);
		c.gridx = 0;
		c.gridy = 4;
		hPanel.add(h, c);
		c.gridx = 1;
		c.gridy = 4;
		hPanel.add(hfield, c);
		c.gridx = 2;
		c.gridy = 4;
		hPanel.add(hlabel,c);
		add(hPanel);
		add(jsldHortH);
	}
	
	private class SliderChangeListener implements ChangeListener {
   	    JSlider parent;
   	    JTextField jtf;
   	    public SliderChangeListener(JSlider parent, JTextField jtf) { this.parent = parent; this.jtf = jtf;}
   	    public void stateChanged(ChangeEvent e) {
   	    	int value = parent.getValue();
   	    	String stringValue = String.valueOf(value);
   	    	jtf.setText(stringValue);
   	    	jtf.updateUI();
   	    }
	}
	
	private class InputChangeListener implements ActionListener
	{
		JSlider jSlider;
		JTextField parent;
		public InputChangeListener(JTextField parent, JSlider jsl) {
			// TODO Auto-generated constructor stub
			this.parent = parent;
			this.jSlider = jsl;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			String value = parent.getText();
			int num = Integer.parseInt(value);
			jSlider.setValue(num);
			jSlider.updateUI();
		}
		
	}
	
	class GameContentUpdateValue extends Thread
	{
		GameContent mGameContent;
		JTextField mTextFieldX;
		JSlider mSliderX;
		JTextField mTextFieldY;
		JSlider mSliderY;
		
		public GameContentUpdateValue(GameContent gc) 
		{
			mGameContent = gc;
			run = true;
		}
		
		public void run()
		{
			while(run)
			{
				if(mGameContent.getX() != Integer.parseInt(mTextFieldX.getText()))
				{
					mTextFieldX.setText(""+mGameContent.getX());
				}
				
				if(mGameContent.getX() != mSliderX.getValue())
				{
					mSliderX.setValue(mGameContent.getX());
				}
				
				if(mGameContent.getY() != Integer.parseInt(mTextFieldY.getText()))
				{
					mTextFieldY.setText(""+mGameContent.getY());
				}
				
				if(mGameContent.getY() != mSliderY.getValue())
				{
					mSliderY.setValue(mGameContent.getY());
				}
			}
		}
		public void setFieldAndSlider(JTextField xfield, JTextField yField, JSlider xSlider, JSlider ySlider)
		{
			mTextFieldX = xfield;
			mSliderX = xSlider;
			mTextFieldY = yField;
			mSliderY = ySlider;
		}
	}
	
	class DescriptionUpdateValue extends Thread
	{
		SceneState msSceneState;
		JTextField mTextFieldX;
		JSlider mSliderX;
		JTextField mTextFieldY;
		JSlider mSliderY;
		
		public DescriptionUpdateValue(SceneState st) 
		{
			msSceneState = st;
			run = true;
		}
		
		public void run()
		{
			while(run)
			{
				if(msSceneState.getX() != Integer.parseInt(mTextFieldX.getText()))
				{
					mTextFieldX.setText(""+msSceneState.getX());
				}
				
				if(msSceneState.getX() != mSliderX.getValue())
				{
					mSliderX.setValue(msSceneState.getX());
				}
				
				if(msSceneState.getY() != Integer.parseInt(mTextFieldY.getText()))
				{
					mTextFieldY.setText(""+msSceneState.getY());
				}
				
				if(msSceneState.getY() != mSliderY.getValue())
				{
					mSliderY.setValue(msSceneState.getY());
				}
			}
		}
		public void setFieldAndSlider(JTextField xfield, JTextField yField, JSlider xSlider, JSlider ySlider)
		{
			mTextFieldX = xfield;
			mSliderX = xSlider;
			mTextFieldY = yField;
			mSliderY = ySlider;
		}
	}
	
	class SSPUpdateValue extends Thread
	{
		SceneStatePair mPair;
		JTextField mTextFieldX;
		JSlider mSliderX;
		JTextField mTextFieldY;
		JSlider mSliderY;
		
		public SSPUpdateValue(SceneStatePair ssp) 
		{
			mPair = ssp;
			run = true;
		}
		
		public void run()
		{
			while(run)
			{
				if(mPair.getX() != Integer.parseInt(mTextFieldX.getText()))
				{
					mTextFieldX.setText(""+mPair.getX());
				}
				
				if(mPair.getX() != mSliderX.getValue())
				{
					mSliderX.setValue(mPair.getX());
				}
				
				if(mPair.getY() != Integer.parseInt(mTextFieldY.getText()))
				{
					mTextFieldY.setText(""+mPair.getY());
				}
				
				if(mPair.getY() != mSliderY.getValue())
				{
					mSliderY.setValue(mPair.getY());
				}
			}
		}
		
		public void setFieldAndSlider(JTextField xfield, JTextField yField, JSlider xSlider, JSlider ySlider)
		{
			mTextFieldX = xfield;
			mSliderX = xSlider;
			mTextFieldY = yField;
			mSliderY = ySlider;
		}
	}
	
	
}
