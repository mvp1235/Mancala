import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

/**
 * Shape of the pits hole for the fancy style
 * @author Team Lucky Number 7
 */
public class FancyPitHole implements DrawableShape
{
	final int WIDTH = 100;
	private String label;
	
	/**
	 * Construct a FancyPitHole object
	 * @param l label of the hole, i.e. A1, A2, ..., B5, B6
	 */
	public FancyPitHole(String l)
	{
		label = l;
	}
	
	/**
	 * Draw the shape
	 * @param g2 JAVA graphical library, used for drawing the shape
	 * @param x the top left x coordinate to draw
	 * @param y the top left y coordinate to draw
	 */
	public void draw(Graphics2D g2, int x, int y)
	{
		Random r = new Random();
		int c1 = r.nextInt(255);
		int c2 = r.nextInt(255);
		int c3 = r.nextInt(255);
		g2.setColor(new Color(c1,c2,c3));
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 30);
		g2.setFont(f);
		g2.drawString(label, x, y-100);
		Rectangle2D square = new Rectangle2D.Double(x-50, y-50, WIDTH, WIDTH);
		g2.draw(square);
	}
	
	/**
	 * Gets the height of the shape
	 * @return diameter of the circle shape
	 */
	public int getHeight()
	{
		return WIDTH;
	}
	
	/**
	 * Gets the width of the shape
	 * @return diameter of the circle shape
	 */
	public int getWidth()
	{
		return WIDTH;
	}
	
}
