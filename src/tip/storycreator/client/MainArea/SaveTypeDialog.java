package tip.storycreator.client.MainArea;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SaveTypeDialog extends JDialog{
	private static final long serialVersionUID = 4406390662163499076L;
	private JComboBox<String> jcb;
	private String saveLocation;
	public Boolean resume = false;
	
	SaveTypeDialog() {
		
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("Please Choose Your Saving Location: ");
		title.setBorder(new EmptyBorder(20, 0, 0, 0));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		//cPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
		
		
		JPanel jp = new JPanel();
		String options[] = {"Local", "Remote"};
		jcb = new JComboBox<String>(options);
		jp.setBorder(new EmptyBorder(10, 0, 0, 0));
		jp.add(jcb);
		
		
		JPanel buttonPanel = new JPanel(new GridLayout(1,2));
		buttonPanel.setBorder(new EmptyBorder(10,0,0,0));
		JButton cancel = new JButton("Cancel");
		JButton ok = new JButton("OK");
		buttonPanel.add(cancel);
		buttonPanel.add(ok);
		ok.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = jcb.getSelectedItem();
				String option = (String) obj;
				System.out.println(option);
				saveLocation = option;	
				resume = true;
			}
		});
		
		add(title);
		add(jp);
		add(buttonPanel);
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(new Dimension(300, 150));
	    setMaximumSize(new Dimension(300, 150));
		setMinimumSize(new Dimension(300, 150));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public String getSaveLocation() {
		return saveLocation;
	}
	
	public static void main (String [] args) {
		new SaveTypeDialog();
	}

}
