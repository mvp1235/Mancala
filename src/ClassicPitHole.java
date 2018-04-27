import java.awt.*;
import java.awt.geom.*;

/**
 * Shape of the pits hole for the classic style
 * @author Team Lucky Number 7
 */
public class ClassicPitHole implements DrawableShape
{
	final int DIAMETER = 100;
	private String label;
	
	/**
	 * Construct a ClassicPitHole object
	 * @param l label of the hole, i.e. A1, A2, ..., B5, B6
	 */
	public ClassicPitHole(String l)
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
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 30);
		g2.setFont(f);
		g2.drawString(label, x-20, y-100);
		Ellipse2D hole = new Ellipse2D.Double(x-50, y-50, DIAMETER, DIAMETER);
		g2.draw(hole);
		
	}
	
	/**
	 * Gets the height of the shape
	 * @return diameter of the circle shape
	 */
	public int getHeight()
	{
		return DIAMETER;
	}
	
	/**
	 * Gets the width of the shape
	 * @return diameter of the circle shape
	 */
	public int getWidth()
	{
		return DIAMETER;
	}
	
}
