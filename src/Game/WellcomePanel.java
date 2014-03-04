package Game;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class WellcomePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	ImageIcon wlcm;
	
	public WellcomePanel(){
		ClassLoader loader = ContolersPanel.class.getClassLoader();
		setBackground(Color.BLACK);
		wlcm = new ImageIcon(loader.getResource("wellcome.png"));
		
		add(new JLabel(wlcm));
	}
	

}