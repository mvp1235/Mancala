import java.awt.*;
import java.awt.geom.*;

/**
 * Shape of the mancala holes for classic style
 * @author Team Lucky Number 7
 */
public class ClassicMancalaHole implements DrawableShape
{
	final int WIDTH = 100;
	final int HEIGHT = 400;
	private String label;
	
	/**
	 * Construct an ClassicMancalaHole object
	 * @param l the label of the hole, i.e. Mancala A, Mancala B
	 */
	public ClassicMancalaHole(String l)
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
		Ellipse2D hole = new Ellipse2D.Double(x+10, y-350, WIDTH, HEIGHT);
		g2.draw(hole);
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 30);
		g2.setFont(f);
		g2.drawString(label, x-30, y-370);
	}

	/**
	 * Gets the height of the shape
	 * @return height of the ellipse
	 */
	public int getHeight()
	{
		return HEIGHT;
	}

	/**
	 * Gets the width of the shape
	 * @return width of the ellipse
	 */
	public int getWidth()
	{
		return WIDTH;
	}
	
}
