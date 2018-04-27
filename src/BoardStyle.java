import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * Mancala Board style interface
 * @author Team Lucky Number 7
 */
public interface BoardStyle
{
	MancalaPanel createLeftMancala(MancalaModel m);
	MancalaPanel createRightMancala(MancalaModel m);
	PitsPanel createPits(MancalaModel m);
}
