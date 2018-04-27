import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;

/**
 * Fancy style of the mancala panel
 * @author Team Lucky Number 7
 */
public class FancyMancalaPanel extends MancalaPanel
{
	private JLabel mancalaHole;
	
	/**
	 * Construct a FancyMancalaPanel object
	 * @param m the MancalaModel to be modeled
	 * @param player specify which player's mancala , 1 for player 1, 2 for player 2
	 */
	public FancyMancalaPanel(MancalaModel m, int player)
	{
		super(m);
		setLocation(0,0);
		setLayout(new BorderLayout());
		setPlayer(player);
		if (player == 1)
			setMancalaShape(new FancyMancalaHole("Mancala A"));	// fix
		else
			setMancalaShape(new FancyMancalaHole("Mancala B"));
		MancalaModel currentModel = getModel();
		mancalaHole = new JLabel(new MancalaIcon(new MancalaStone(currentModel.getMancalas()[getPlayer()-1]), getShape()));
		mancalaHole.setSize(200, 200);
		add(mancalaHole);
	}
	
	
	/**
	 * Receive notification from the model, and repaint components
	 */
	public void stateChanged(ChangeEvent e)
	{
		MancalaModel currentModel = getModel();
		mancalaHole.setIcon(new MancalaIcon(new MancalaStone(currentModel.getMancalas()[getPlayer()]), getShape()));
		repaint();
	}

}
