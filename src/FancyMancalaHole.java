import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Point2D.Double;
import java.util.Random;

/**
 * Shape of the pits hole for the fancy style
 * @author Team Lucky Number 7
 */
public class FancyMancalaHole implements DrawableShape
{
	final int WIDTH = 130;
	final int HEIGHT = 400;
	private String label;
	
	/**
	 * Construct a FancyMancalaHole object
	 * @param l label of the hole, i.e. A1, A2, ..., B5, B6
	 */
	public FancyMancalaHole(String l)
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
		g2.drawString(label, x-10, y-370);
		
		int newX = x;
		int newY = y-350;

		Point2D p1 = new Point2D.Double(newX, newY+HEIGHT/2);
		Point2D p2 = new Point2D.Double(newX+WIDTH/2, newY);
		Point2D p3 = new Point2D.Double(newX+WIDTH, newY+HEIGHT/2);
		Point2D p4 = new Point2D.Double(newX+WIDTH/2, newY+HEIGHT);
		
		g2.draw(new Line2D.Double(p1, p2));
		g2.draw(new Line2D.Double(p2, p3));
		g2.draw(new Line2D.Double(p3, p4));
		g2.draw(new Line2D.Double(p4, p1));
		
	}
	
	/**
	 * Gets the height of the shape
	 * @return diameter of the circle shape
	 */
	public int getHeight()
	{
		return HEIGHT;
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
