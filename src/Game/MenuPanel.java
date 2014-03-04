package Game;
import javax.swing.*;
import java.awt.*;



public class MenuPanel extends JPanel{

	private JButton button_start;
	private JLabel p1, p2;
	private int score1, score2;
	 String name1, name2;



	public MenuPanel(String name1, String name2, int score1, int score2) {
		setBackground(Color.DARK_GRAY);
		this.score1 = score1;
		this.score2 = score2;
		this.name1 = name1;
		this.name2 = name2;
		p1 = new JLabel();
		p2 = new JLabel();
		p1.setForeground(Color.WHITE);
		p2.setForeground(Color.WHITE);
		p1.setText(""+this.name1+": "+score1);
		p2.setText(""+this.name2+": "+score2);

		setLayout(new BorderLayout(20, 0));
		add(p1, BorderLayout.WEST);
		add(new ImagePanel(), BorderLayout.CENTER);
		add(p2, BorderLayout.EAST);

	}

	public void updateScore(int score1, int score2){
		this.score1 = score1;
		this.score2 = score2;
		String s = ""+this.name1+": "+this.score1;
		p1 = new JLabel(s);
		System.out.println(p1.getText());
		s = ""+this.name2+": "+this.score2;
		p2.setText(s);
		System.out.println("======");
		System.out.println(""+this.name1+": "+this.score1);
		System.out.println(""+this.name2+": "+this.score2);
		System.out.println("======");
		
	}

}
