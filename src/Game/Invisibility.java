package Game;

import java.awt.Color;
import java.awt.Graphics;

public class Invisibility {

	private boolean goodPoint;
	private static int SIZE = 5;
	private int width, height, x, y;
	private Color color;
	private boolean show;

	public Invisibility(int width, int height)
	{
		goodPoint = false;
		this.width = width;
		this.height = height;
		color = Color.CYAN;
		show = false;


	}

	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillRect(x, y, SIZE, SIZE);
	}

	public boolean makePoint(Player p1, Player p2)
	{
		if(goodPoint)
			if(showPowerUp() || show)
				return true;
			else
				return false;

		x = (int)(Math.random() * width);
		if(x % Player.THIKNES != 0)
			return false;
		y = (int)(Math.random() * height);
		if(y % Player.THIKNES != 0)
			return false;

		for(LengthNode p = p1.getHead(); p.getNext() != null ; p = p.getNext())
		{
			if(x == p.getX() && y == p.getY())
				return false;
		}

		for(LengthNode p = p2.getHead(); p.getNext() != null ; p = p.getNext())
		{
			if(x == p.getX() && y == p.getY())
				return false;
		}
		goodPoint = true;
		return true;

	}

	public boolean isGoodPoint() {
		return goodPoint;
	}

	public void setGoodPoint(boolean goodPoint) {
		this.goodPoint = goodPoint;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean showPowerUp()
	{
		int c = (int)(Math.random() * 10000);
		if(c <= 10)
		{
			show = true;
			System.out.println("POWER UP!!!!!");
			return true;
		}
		return false;
	}




}
