import java.awt.*;
import java.awt.geom.*;
import java.util.Random;
/**
 * Stones inside the pits
 * @author Team Lucky Number 7
 */
public class PitStone implements DrawableShape
{
	final double DIAMETER = 10;
	private int numStone;
	
	/**
	 * Construct PitStone object
	 * @param num number of starting stones
	 */
	public PitStone(int num)
	{
		numStone = num;
	}
	
	/**
	 * Draw the stones
	 * @param g2 JAVA Graphical libary
	 * @param x x coordinate of starting corner 
	 * @param y y coordinate of starting corner
	 */
	public void draw(Graphics2D g2, int x, int y)
	{
		int num = 0;
		x -= 20;
		y -= 20;
		int oldX = x;
		
		for (int i=0; i<numStone; i++)
		{
			Ellipse2D stone = new Ellipse2D.Double(x, y, DIAMETER, DIAMETER);
			g2.fill(stone);
			num++;
			x += 12;
			if (num == 3)
			{
				y += 10;
				x = oldX;
				num = 0;
			}
		}
	}

	/**
	 * Gets the height
	 * @return diameter of stones
	 */
	public int getHeight()
	{
		return (int)DIAMETER;
	}

	/**
	 * Gets the width
	 * @return diameter of stones
	 */
	public int getWidth()
	{
		return(int)DIAMETER;
	}
}
