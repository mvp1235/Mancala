/**
 * Classic style of mancala board
 * @author Team Lucky Number 7
 */
public class ClassicStyle implements BoardStyle
{
	final int ROW = 2;
	final int COLUMN = 6;
	
	/**
	 * Create the left mancala for the board
	 * @return left mancala panel
	 */
	public MancalaPanel createLeftMancala(MancalaModel m)
	{
		MancalaPanel leftMancala = new ClassicMancalaPanel(m, 2);
		leftMancala.setPlayer(1);
		return leftMancala;
	}

	/**
	 * Create the right mancala for the board
	 * @return right mancala panel
	 */
	public MancalaPanel createRightMancala(MancalaModel m)
	{
		MancalaPanel rightMancala = new ClassicMancalaPanel(m, 1);
		rightMancala.setPlayer(0);
		return rightMancala;
	}

	/**
	 * Create the pits for the board
	 * @return pits panel
	 */
	public PitsPanel createPits(MancalaModel m)
	{
		DrawableShape[] shapes1 = new DrawableShape[6];
		DrawableShape[] shapes2 = new DrawableShape[6];
		
		for (int i=0; i<shapes1.length; i++)
		{
			shapes1[i] = new ClassicPitHole("A" + (i+1));
			shapes2[i] = new ClassicPitHole("B" + (i+1));
		}

		PitsPanel pits = new ClassicPitsPanel(m, shapes1, shapes2);
		return pits;
	}

}
