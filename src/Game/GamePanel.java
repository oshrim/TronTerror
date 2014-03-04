package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MenuPanel topPanel;
	private GameLogic logic;

	private JLabel lblScore;

	boolean p1Fire, p2Fire;
	
	public static final int W = 1195, H = 855;

	/*
	 * 
	 * Setup TIMERS & LISTENERS !!!
	 * 
	 */


	Timer timerGeneral;
	Timer timerBullet;

	public GamePanel() {
		
		
		setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		setSize(W, H);
	
		
		
		logic = new GameLogic(W, H);
		lblScore = new JLabel(logic.getP2Name()+": "+logic.getPlayer2Point()+"     "+logic.getP1Name()+": "+logic.getPlayer1Point());
		lblScore.setForeground(Color.WHITE);
		lblScore.setHorizontalAlignment(JLabel.CENTER);

		add(lblScore, BorderLayout.SOUTH);

		//TEST!!!!
		timerGeneral = new Timer(30, this);
		timerBullet = new Timer(1, this);
		

	}

	private void p1Fire()
	{

		logic.fire(logic.getP1());
		p1Fire = true;
		timerBullet.start();

	}
	

	private void p2Fire()
	{
		logic.fire(logic.getP2());
		p2Fire = true;
		timerBullet.start();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(timerBullet.isRunning())
			paintBullets(g);
		logic.draw(g);
		g.setColor(Color.CYAN);				//paint borders
		g.drawRect(0, 0, W-1, H-1);
	}


	private void paintBullets(Graphics g)
	{
		if(p1Fire)
		{
			int bulletState = logic.getBullet1().fireStep(W, H);
			if(bulletState == Bullet.STILL_GO){   // CHANGE 500 TODO
				logic.draw(g);
				logic.bulletStep(g, bulletState);
			}
			else{
				logic.draw(g);
				p1Fire = false;
				timerBullet.stop();
			}
			
		}
		else if(p2Fire)
		{
			int bulletState = logic.getBullet2().fireStep(W, H);

			if(bulletState == Bullet.STILL_GO){   // CHANGE 500 TODO
				logic.draw(g);
				logic.bulletStep(g, bulletState);
			}
			else{
				logic.draw(g);
				p2Fire = false;
				timerBullet.stop();
			}
			
		}
		
	}
	
	public void start(){
		
		logic.restart();
		timerGeneral.start();
		System.out.println(getWidth()+"  "+getHeight());
	}
	
	/*
	 * 
	 * Actions! Timers, and keyActions will be down here!
	 * 
	 */

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==timerGeneral)
		{
			
			if(logic.stepAndOk())
				repaint();
			else
			{
				//logic.stepAndOk();
				timerGeneral.stop();	
				System.out.println("FUCK!");
				lblScore.setText(logic.getP2Name()+": "+logic.getPlayer2Point()+"     "+logic.getP1Name()+": "+logic.getPlayer1Point());
			}
			repaint();
		}
		if(e.getSource() == timerBullet)
		{
			repaint();
		}

		

	}

	@Override
	public void keyPressed(KeyEvent e) {
		/*
		 * Player1 Controls
		 */
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if(logic.getP1().isCanFire()){
				p1Fire();
				
				logic.getP1().setCanFire(false);
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			if(logic.getP1().getDirection() != Player.UP)
				logic.setP1Direction(Player.DOWN);
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP){
			if(logic.getP1().getDirection() != Player.DOWN)
				logic.setP1Direction(Player.UP);
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			if(logic.getP1().getDirection() != Player.RIGHT)
				logic.setP1Direction(Player.LEFT);
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			if(logic.getP1().getDirection() != Player.LEFT)
				logic.setP1Direction(Player.RIGHT);
		}

		/*
		 * Player2 Controls
		 */
		if(e.getKeyCode() == KeyEvent.VK_CONTROL)
		{
			if(logic.getP2().isCanFire()){
				p2Fire();
				
				logic.getP2().setCanFire(false);
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_S){
			if(logic.getP2().getDirection() != Player.UP)
				logic.setP2Direction(Player.DOWN);
		}
		else if(e.getKeyCode() == KeyEvent.VK_W){
			if(logic.getP2().getDirection() != Player.DOWN)
				logic.setP2Direction(Player.UP);
		}
		else if(e.getKeyCode() == KeyEvent.VK_A){
			if(logic.getP2().getDirection() != Player.RIGHT)
				logic.setP2Direction(Player.LEFT);
		}
		else if(e.getKeyCode() == KeyEvent.VK_D){
			if(logic.getP2().getDirection() != Player.LEFT)
				logic.setP2Direction(Player.RIGHT);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_F2)
		{
			start();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}



}
