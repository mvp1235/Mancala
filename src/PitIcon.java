import java.awt.Component;
import java.awt.*;

import javax.swing.Icon;

/**
 * Icon containing shape of the pits and shape of stones
 * @author Team Lucky Number 7
 */
public class PitIcon implements Icon
{
	private DrawableShape stones;
	private DrawableShape pitHole;
	
	/**
	 * Construct a PitIcon
	 * @param s shape of the stones
	 * @param p shape of the pit holes
	 */
	public PitIcon(DrawableShape s, DrawableShape p)
	{
		stones = s;
		pitHole = p;
	}
	
	/**
	 * Gets the icon's height
	 * @return 0
	 */
	public int getIconHeight()
	{
		return 0;
	}

	/**
	 * Gets the icon's width
	 * return 0 //Need fix
	 */
	public int getIconWidth()
	{
		return 0;
	}

	/**
	 * Specify how the icon will be drawn
	 * @param c the components to be drawn on
	 * @param g JAVA graphical library for drawing
	 * @param x x coordinate of the top left corner
	 * @param y y coordinate of the top left corner
	 */
	public void paintIcon(Component c, Graphics g, int x, int y)
	{
		stones.draw((Graphics2D)g, x, y);
		pitHole.draw((Graphics2D)g, x, y);
	}
}
