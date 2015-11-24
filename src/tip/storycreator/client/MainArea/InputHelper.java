package tip.storycreator.client.MainArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import tip.storycreator.client.MainArea.MainWindow.UpdateTool;
import tip.storycreator.game.Scene;
import tip.storycreator.game.SceneState;

public class InputHelper {

	private UpdateTool mUpdateTool;
	public static final String NAME = "NAME";
	public static final String DESCRIPTION = "DESCRIPTION";
	public static final String IMAGE = "IMAGE";
	
	public InputHelper(UpdateTool holdTool)
	{
		mUpdateTool = holdTool;
	}
	
	public void setSceneInputListener(JTextField holdField, Scene holdScene)
	{
		
		holdField.setText(holdScene.getName());
		holdField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				holdScene.setName(holdField.getText());
				mUpdateTool.updateTree();
				holdField.setFocusable(false);
				holdField.setFocusable(true);
			}
		});
		holdField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					holdField.setFocusable(false);
					holdField.setFocusable(true);
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		holdField.addFocusListener(new FocusListener() {		
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				holdField.setText(holdScene.getName());
			}
			@Override
			public void focusGained(FocusEvent e) {}
		});
	}
	
	public void setSceneStateInputListener(JTextField holdField, SceneState holdState, String type)
	{
		
		class TypeIdentifier
		{
			public String getText()
			{
				if(type.equals(NAME))
				{
					return holdState.getName();
				}
				else if(type.equals(DESCRIPTION))
				{
					return holdState.getDescriptionID();
				}
				else if(type.equals(IMAGE))
				{
					return holdState.getImageChoice();
				}
				return null;
			}
			
			public void setText(String text)
			{
				if(type.equals(NAME))
				{
					holdState.setName(text);
				}
				else if(type.equals(DESCRIPTION))
				{
					holdState.setDescription(text);
				}
				else if(type.equals(IMAGE))
				{
					holdState.setImagePath(text);
				}
			}
		}
		TypeIdentifier mIdentifier = new TypeIdentifier();
		
		holdField.setText(mIdentifier.getText());
		holdField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mIdentifier.setText(holdField.getText());
				mUpdateTool.updateTree();
				holdField.setFocusable(false);
				holdField.setFocusable(true);
			}
		});
		holdField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					holdField.setFocusable(false);
					holdField.setFocusable(true);
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		holdField.addFocusListener(new FocusListener() {		
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				holdField.setText(mIdentifier.getText());
			}
			@Override
			public void focusGained(FocusEvent e) {}
		});
	}
}
