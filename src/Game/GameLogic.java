package Game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

public class GameLogic {
	private Player player1, player2;
	private int width, height;
	private Color colorP1, colorP2;

	private Bullet bullet1, bullet2;

	// **** Invisibility *****

	private boolean inVisibilityHappend;
	private Invisibility invisibleMod;


	public GameLogic(int maxWidth, int maxHeight)
	{
		width = maxWidth;
		height = maxHeight;
		colorP1 = new Color(3,3,173);
		colorP2 = new Color(173, 3, 3 );

		inVisibilityHappend = false;
		invisibleMod = new Invisibility(maxWidth, maxHeight);

		player1 = new Player(getNewName("Please enter Player 1 Name"), Player.LEFT, colorP1, width - 300, 420, 0);  //starts from right to left!
		player2 = new Player(getNewName("Now, Please enter Player 2 Name"), Player.RIGHT, colorP2, 0 + 300, 420, 0);  //starts from left to right!
	}


	private String getNewName(String s){
		return JOptionPane.showInputDialog(s);
	}

	public void setP1Direction(int direct)
	{
		player1.setDirection(direct);
	}

	public void setP2Direction(int direct)
	{
		player2.setDirection(direct);
	}

	public String getP1Name()
	{
		return player1.getName();
	}

	public String getP2Name()
	{
		return player2.getName();
	}

	public int getPlayer1Point()
	{
		return player1.getPoints();
	}

	public int getPlayer2Point()
	{
		return player2.getPoints();
	}

	public Player getP1(){
		return player1;
	}

	public Player getP2(){
		return player2;
	}




	public Bullet getBullet1() {
		return bullet1;
	}


	public Bullet getBullet2() {
		return bullet2;
	}


	public boolean stepAndOk()
	{

		player1.step();
		player2.step();
		inVisibilityHappend = invisibleMod.makePoint(player1, player2);
		if(inVisibilityHappend)
		{
			checkPlayersVisibility();
		}
		if(player1.loosed(player2, width, height))
		{
			player2.addPoints();
			return false;
		}
		else if(player2.loosed(player1, width, height))
		{
			player1.addPoints();
			return false;
		}
		return true;
	}

	public void fire(Player p)
	{
		if(!p.isCanFire())
			return;
		if(p == player1)
		{
			bullet1 = new Bullet(player1.getHead().getX(), player1.getHead().getY(), player1.getDirection(), player2.getHead());
			return;
		}
		else if(p == player2)
		{
			bullet2 = new Bullet(player2.getHead().getX(), player2.getHead().getY(), player2.getDirection(), player1.getHead());
			return;
		}
	}




	public void bulletStep(Graphics g, int bulletState)
	{

		//Player1 Shoots
		try{					//Freezes game and shoots
			if(bulletState == Bullet.STILL_GO)
				bullet1.draw(g);
			else
				player1.setCanFire(false);
		}catch (Exception e) {
			System.out.println("no bullet to p1");
		}
		//Player2 shoots
		try{					//Freezes game and shoots
			bulletState = bullet2.fireStep(width, height); 
			if(bulletState == Bullet.STILL_GO)
				bullet2.draw(g);
			else
				player2.setCanFire(false);
		}catch (Exception e) {
			System.out.println("no bullet to p2");
		}
	}

	public void  restart()
	{
		player1 = new Player(player1.getName(), Player.LEFT, colorP1,width - 300, 420, player1.getPoints());
		player2 = new Player(player2.getName(), Player.RIGHT, colorP2, 0 + 300, 420, player2.getPoints());
		inVisibilityHappend = false;
		invisibleMod = new Invisibility(width, height);
	}

	public void checkPlayersVisibility()
	{
		int x = invisibleMod.getX();
		int y = invisibleMod.getY();
		
		if(player1.getHead().getX() == x && player1.getHead().getY() == y)
			invisibleModOn(player1, true);
		else if(player2.getHead().getX() == x && player2.getHead().getY() == y)
			invisibleModOn(player2, true);
	}

	public void invisibleModOn(Player p, boolean switcher)
	{
		if(switcher)
		{
			System.out.println("got The Shit!!!");
			p.setIsVisible(false);
			invisibleMod.setX(-200);
			invisibleMod.setY(-200);
		}
		else
			p.setIsVisible(true);
	}



	public void draw(Graphics g)
	{
		player1.draw(g);
		player2.draw(g);
		if(inVisibilityHappend)
			invisibleMod.draw(g);
	}

}
