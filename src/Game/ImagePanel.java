package Game;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel{

	private BufferedImage image;
	JLabel l;
	public ImagePanel() {
		setBackground(Color.DARK_GRAY);
		l = new JLabel();
		l.setIcon(new ImageIcon("Resource/images.png"));
		add(l);
		validate();
	}



}