package Game;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class ContolersPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon ctrl;
	
	public ContolersPanel(){
		ClassLoader loader = ContolersPanel.class.getClassLoader();
		setBackground(Color.BLACK);
		ctrl = new ImageIcon(loader.getResource("ctrl.png"));
		
		add(new JLabel(ctrl));
	}
	

}
