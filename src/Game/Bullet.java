package Game;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
	private int x, y, direction;
	private LengthNode other;
	private Color color;
	public static int SIZE = Player.THIKNES, PIX_PER_STEP = 1,
			STILL_GO = 0, HIT = 1, OUT_OF_GRID = 2;
	

	private int hitPointX, hitPointY;
	public Bullet(int x, int y, int direction, LengthNode other)
	{
		color = Color.WHITE;
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.other = other;
	}



	public int getX() {
		return x;
	}



	public int getY() {
		return y;
	}
	



	public int getHitPointX() {
		return hitPointX;
	}



	public int getHitPointY() {
		return hitPointY;
	}



	public int fireStep(int maxWidth, int maxHeight)
	{
		switch(direction)
		{
		case 1: 		//up
			y -= PIX_PER_STEP;
			return presentState(maxWidth, maxHeight);

		case 2: 		//down
			y += PIX_PER_STEP;
			return presentState(maxWidth, maxHeight);

		case 3: 		//left
			x -= PIX_PER_STEP;
			return presentState(maxWidth, maxHeight);

		case 4: 		//right
			x += PIX_PER_STEP;
			return presentState(maxWidth, maxHeight);	
		}
		return presentState(maxWidth, maxHeight);
	}

	private int presentState(int maxWidth, int maxHeight)
	{
		if(didHit())
			return HIT;
		else if(outOfGrid(maxWidth, maxHeight))
			return OUT_OF_GRID;
		else
			return STILL_GO;
	}


	private boolean didHit()
	{
		LengthNode p = other;
		for(; p.getNext() != null; p = p.getNext()){
			if(p.getNext().getX() == this.x && this.y == p.getNext().getY()){
				System.out.println("HIT! HIT! HIT!");
				p.setNext(null);
				x = -1000;
				y = -1000;
				return true;
			}
		}
		//****** DEBUG ****
		System.out.println("not hit");
		// **** end debug ****
		return false;
	}



	private boolean outOfGrid(int maxWidth, int maxHeight)
	{
		if((this.x < 0 - SIZE) || (this.y < 0 - SIZE) || (this.x >= maxWidth + SIZE) || (this.y >= maxHeight + SIZE)){
			// *** DEBUG
			
			System.out.println("OUT!");
			return true;
		}
		else
			return false;
	}

	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillOval(x, y, SIZE, SIZE);
	}



}
