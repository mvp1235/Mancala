import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Different styles of left and right mancala need to extend this abstract class
 * @author Team Lucky Number 7
 */
public abstract class MancalaPanel extends JPanel implements ChangeListener
{
	private MancalaModel model;
	private DrawableShape mancalaShape;	//Shape of the mancala shape
	private int player;	//Specify left or right mancala based on player number
	
	/**
	 * Construct a MancalaPanel object
	 * @param m the MancalaModel object to be modeled
	 */
	public MancalaPanel(MancalaModel m)
	{
		model = m;
		setLayout(new BorderLayout());
	}
	
	/**
	 * Gets the model
	 * @return model
	 */
	public MancalaModel getModel()
	{
		return model;
	}
	
	/**
	 * Set the player
	 * @param num 1 for player 1, 2 for player 2
	 */
	public void setPlayer(int num)
	{
		player = num;
	}
	
	/**
	 * Gets the player number , 1 for player 1, 2 for player 2
	 * @return player
	 */
	public int getPlayer()
	{
		return player;
	}
	
	/**
	 * Set the shape for the mancala
	 * @param s the shape to be set
	 */
	public void setMancalaShape(DrawableShape s)
	{
		mancalaShape = s;
	}
	
	/**
	 * Gets the shape of the mancala
	 * @return mancalaShape
	 */
	public DrawableShape getShape()
	{
		return mancalaShape;
	}
	
	/**
	 * Override the preferred size of the panel
	 */
	public Dimension getPreferredSize()
	{
		return new Dimension(180,500);
	}
}
