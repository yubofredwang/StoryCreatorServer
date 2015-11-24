package tip.storycreator.client.MainArea;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import tip.storycreator.game.GameContent;
import tip.storycreator.game.GameProject;
import tip.storycreator.game.Scene;
import tip.storycreator.game.SceneState;
import tip.storycreator.game.SceneStatePair;

public class SceneEditPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6635856566326765806L;
	
	private Scene mScene;
	private GameProject mGameProject;
	private InputHelper mInputHelper;
	private JTable contentTable;
	private JTable lsTable;
	private DefaultTableModel statesTableModel;
	private JTable statesTable;
	
	public SceneEditPanel(Scene holdScene, GameProject holdProject, InputHelper holdHelper) {
		mScene = holdScene;
		mGameProject = holdProject;
		mInputHelper = holdHelper;
		createGUI();
	}
	
	public void refreshST()
	{
		for(int i = statesTable.getRowCount() - 1; i >= 0;--i)
		{
			statesTableModel.removeRow(i);
		}
		for(SceneState st: mScene.getAllSceneStates().values())
		{
			statesTableModel.addRow(new Object[]{st.getName(), st.getDescriptionID()});
		}
	}
	
	private void createGUI(){
		
		JPanel namePanel = new JPanel(); 
		JLabel nameLabel = new JLabel("Name");
		JTextField nameTF = new JTextField(10);
		mInputHelper.setSceneInputListener(nameTF, mScene);
		namePanel.add(nameLabel);
		namePanel.add(nameTF);
		namePanel.setBorder(new EmptyBorder(10, 10, 0, 10));
		
		JPanel statesPanel = new JPanel();
		statesPanel.setLayout(new BoxLayout(statesPanel, BoxLayout.Y_AXIS));
		
		JPanel statesLabelPanel = new JPanel();
		statesLabelPanel.setLayout(new BoxLayout(statesLabelPanel, BoxLayout.X_AXIS));
		JLabel statesLabel = new JLabel("States Contained");	
		statesLabelPanel.add(statesLabel);	
		statesPanel.add(statesLabelPanel);
		
		String[] statesColNames = new String[] {"Name", "Description"};
		statesTableModel =  new DefaultTableModel(statesColNames, 0){
			private static final long serialVersionUID = -5942350964201006168L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		for(SceneState st: mScene.getAllSceneStates().values())
		{
			statesTableModel.addRow(new Object[]{st.getName(), st.getDescriptionID()});
		}
		statesTable = new JTable(statesTableModel);
		statesTable.setRowHeight(30);
		JScrollPane statesjsp = new JScrollPane(statesTable, 
									JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
									JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		statesPanel.add(statesjsp);
		statesPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		
		JPanel lsPanel = new JPanel();
		lsPanel.setLayout(new BoxLayout(lsPanel, BoxLayout.Y_AXIS));
		JPanel lsLabelPanel = new JPanel();
		lsLabelPanel.setLayout(new BoxLayout(lsLabelPanel, BoxLayout.X_AXIS));
		JLabel lsLabel = new JLabel("All Game Buttons in the Scene");
		JButton lsButton = new JButton("Clear");
		lsLabelPanel.add(lsLabel);
		lsLabelPanel.add(lsButton);
		lsPanel.add(lsLabelPanel);
		String[] lsColNames = new String[] {"Button Description", "Button Information"};
		DefaultTableModel lsTableModel =  new DefaultTableModel(lsColNames, 0){
			private static final long serialVersionUID = -5942350964201006168L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		refreshSSP(lsTableModel);
		
		lsTable = new JTable(lsTableModel);
		lsTable.setRowHeight(30);
		lsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				for(int i = lsTableModel.getRowCount() -1 ; i >= 0 ; i--)
				{
					lsTableModel.removeRow(i);					
				}
				ArrayList<Integer> toClear = new ArrayList<Integer>();
				for(SceneStatePair ssp: mScene.getAllSceneStatePairs().values())
				{
					if(ssp.usability == 0)
					{
						toClear.add(ssp.getID());
					}
				}
				for(int i: toClear)
				{
					mScene.removelinkedScenes(i);
				}
				refreshSSP(lsTableModel);
				lsTable.updateUI();
			}
		});
		JScrollPane lsjsp = new JScrollPane(lsTable, 
									JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
									JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		lsPanel.add(lsjsp);
		lsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
				
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		JPanel contentLabelPanel = new JPanel();
		contentLabelPanel.setLayout(new BoxLayout(contentLabelPanel, BoxLayout.X_AXIS));
		JLabel contentLabel = new JLabel("All Game Content Contained");
		JButton gcButton = new JButton("Clear");
		contentLabelPanel.add(contentLabel);
		contentLabelPanel.add(gcButton);
		contentPanel.add(contentLabelPanel);
		String[] contentColNames = new String[] {"Name", "Description"};
		DefaultTableModel contentTableModel =  new DefaultTableModel(contentColNames, 0){
			private static final long serialVersionUID = -5942350964201006168L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		refreshGC(contentTableModel);
		contentTable = new JTable(contentTableModel);
		contentTable.setRowHeight(30);
		gcButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				ArrayList<Integer> toClear = new ArrayList<Integer>();
				for(GameContent gc: mScene.getAllGameContents().values())
				{
					if(gc.usability == 0)
					{
						toClear.add(gc.getID());
					}
				}
				for(int i: toClear)
				{
					mScene.removeGameContent(i);
				}
				for(int i = contentTableModel.getRowCount() -1; i >= 0; i--)
				{
					contentTableModel.removeRow(i);					
				}
				refreshGC(contentTableModel);
				contentTable.updateUI();
			}
		});
		JScrollPane contentjsp = new JScrollPane(contentTable, 
									JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
									JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentPanel.add(contentjsp);
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));		
		JPanel confirmPanel = new JPanel();
		confirmPanel.setLayout(new BoxLayout(confirmPanel, BoxLayout.X_AXIS));
//		JButton confirmButton = new JButton("Confirm");
//		confirmPanel.add(Box.createGlue());
//		confirmPanel.add(confirmButton);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel mainPanel = new JPanel(new GridLayout(3, 1));
		add(namePanel);
		mainPanel.add(statesPanel);
		mainPanel.add(lsPanel);
		mainPanel.add(contentPanel);
		add(mainPanel);
//		add(confirmPanel);
	}
	
	public void refreshSSP(DefaultTableModel tableModel)
	{
		for(SceneStatePair ssp: mScene.getAllSceneStatePairs().values())
		{
			if(ssp.usability == 0)
			{
				tableModel.addRow(new Object[]{"*** " + ssp.getDescription(), 
						"to Scene: " + mGameProject.getScene(ssp.sceneID) + " to State: " + mGameProject.getScene(ssp.sceneID).getSceneState(ssp.stateID).getName()});
			}
			else
			{
				tableModel.addRow(new Object[]{ssp.getDescription(), 
						"to Scene: " + mGameProject.getScene(ssp.sceneID) + " to State: " + mGameProject.getScene(ssp.sceneID).getSceneState(ssp.stateID).getName()});
			}
		}
	}
	
	public void refreshGC(DefaultTableModel tableModel)
	{
		for(GameContent gc: mScene.getAllGameContents().values())
		{
			if(gc.usability == 0)
			{
				tableModel.addRow(new Object[]{"*** " + gc.getName(), gc.lookUp()});
			}
			else
			{
				tableModel.addRow(new Object[]{gc.getName(), gc.lookUp()});
			}
		}
	}
}
