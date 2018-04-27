import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;

/**
 * Icon of the mancala hole, containing the shape of the mancala hole and shape of stones
 * @author Team Lucky Number 7
 */
public class MancalaIcon implements Icon
{
	private DrawableShape stones;
	private DrawableShape mancalaHole;
	
	/**
	 * Construct a MancalaIcon
	 * @param s shape of stones
	 * @param h shape of mancala hole
	 */
	public MancalaIcon(DrawableShape s, DrawableShape h)
	{
		stones = s;
		mancalaHole = h;
	}
	
	/**
	 * Gets the icon's height
	 * @return mancala hole's height
	 */
	public int getIconHeight()
	{
		return mancalaHole.getHeight();
	}

	/**
	 * Gets the icon's width
	 * @return mancala hole's width
	 */
	public int getIconWidth()
	{
		return mancalaHole.getWidth();
	}

	/**
	 * Draw all the shapes inside the icon
	 * @param c the component which the icon will be drawn on
	 * @param x the top left x coordinate
	 * @param y the top left y coordinate
	 */
	public void paintIcon(Component c, Graphics g, int x, int y)
	{
		stones.draw((Graphics2D)g, x, y+350);
		mancalaHole.draw((Graphics2D)g, x, y+350);
	}

}
