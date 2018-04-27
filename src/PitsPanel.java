import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Different styles of pits need to extend this abstract class
 * @author Team Lucky Number 7
 */
public abstract class PitsPanel extends JPanel implements ChangeListener
{
	private DrawableShape[] pitShapes1;
	private DrawableShape[] pitShapes2;
	private JLabel[] pits1;
	private JLabel[] pits2;
	private MancalaModel model;
	final int ROW = 2;
	final int COLUMN = 6;
	
	/**
	 * Construct a PitsPanel
	 * @param m the MancalaModel to be modeled
	 * @param shapes1 shapes of the pits for player 1
	 * @param shapes2 shapes of the pits for player 2
	 */
	public PitsPanel(MancalaModel m, DrawableShape[] shapes1, DrawableShape[] shapes2)
	{
		model = m;
		pitShapes1 = shapes1;
		pitShapes2 = shapes2;
		int[] stones1 = model.getPitsPlayer1();
		int[] stones2 = model.getPitsPlayer2();

		pits1 = new JLabel[stones1.length];
		pits2 = new JLabel[stones2.length];

		//Initialize pit labels
		for (int i=0; i<stones1.length; i++)
			pits1[i] = new JLabel(new PitIcon(new PitStone(stones2[i]), pitShapes1[i]));
		for (int i=0; i<stones2.length; i++)
			pits2[i] = new JLabel(new PitIcon(new PitStone(stones2[i]), pitShapes2[i]));

		setLayout(new GridLayout(ROW, COLUMN, 0, 0));

		//Drawing the pits for player 2 on top row of panel
		for (int i = pits2.length-1; i >= 0; i--)
		{
			PitIcon icon = new PitIcon(new PitStone(stones2[i]), pitShapes2[i]);
			pits2[i].setIcon(icon);
			add(pits2[i]);
			final int index = i;
			pits2[i].addMouseListener(new MouseAdapter()
			{
				@Override
				public void mousePressed(MouseEvent e)
				{
					boolean changed = model.distributeStonesPlayer2(index);

					JFrame f = new JFrame();
					if (model.finishedGame() && model.getNumStone() != 0)
					{
						int remainingStones1 = 0;
						int remainingStones2 = 0;
						for (int i=0; i<model.getPitsPlayer1().length; i++)
						{
							int s = model.getPitsPlayer1()[i];
							if (s > 0)
							{
								remainingStones1 += s;
								model.updatePit(1,i,-s);
							}
						}

						for (int i=0; i<model.getPitsPlayer2().length; i++)
						{
							int s = model.getPitsPlayer2()[i];
							if (s > 0)
							{
								remainingStones2 += s;
								model.updatePit(2,i,-s);
							}
						}

						//Add the remaining stones to the mancalas of the players
						model.updateMancala(1, remainingStones1);
						model.updateMancala(2, remainingStones2);
						int player1Score = model.getMancalas()[0];
						int player2Score = model.getMancalas()[1];

						JFrame winnerF = new JFrame();
						if (player1Score > player2Score)
							JOptionPane.showMessageDialog(winnerF, "Player 1 is the winner!!!\nPlayer 1's Score: " + player1Score + "\nPlayer 2's Score: " 
									+ player2Score, "Game is finished", JOptionPane.INFORMATION_MESSAGE);
						else if (player2Score > player1Score)
							JOptionPane.showMessageDialog(winnerF, "Player 2 is the winner!!!\nPlayer 1's Score: " + player1Score + "\nPlayer 2's Score: " 
									+ player2Score, "Game is finished", JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(winnerF, "Draw!!!\nPlayer 1's Score: " + player1Score + "\nPlayer 2's Score: " 
									+ player2Score, "Game is finished", JOptionPane.INFORMATION_MESSAGE);
						
						winnerF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

						System.exit(0);
					}
					else if (!changed)
					{
						int pitValue = model.getPitsPlayer2()[index];
						MancalaModel.Turn currentTurn = model.getTurn();

						if (currentTurn == MancalaModel.Turn.PLAYER1)
						{
							JOptionPane.showMessageDialog(f, "It's currently Player 1's Turn.", "Warning", 
									JOptionPane.WARNING_MESSAGE);
						}
						else if (pitValue == 0)
						{
							JOptionPane.showMessageDialog(f, "Invalid Pick!!!", "Warning", 
									JOptionPane.WARNING_MESSAGE);
						}
					}
				}

			});
		}

		//Drawing the pits for player 1 on bottom row of panel
		for (int i=0; i<pits1.length; i++)
		{
			PitIcon icon = new PitIcon(new PitStone(stones1[i]), pitShapes1[i]);
			pits1[i].setIcon(icon);
			add(pits1[i]);
			final int index = i;
			pits1[i].addMouseListener(new MouseAdapter()
			{
				@Override
				public void mousePressed (MouseEvent e)
				{
					boolean changed = model.distributeStonesPlayer1(index);

					JFrame f = new JFrame();

					if (model.finishedGame() && model.getNumStone() != 0)
					{
						int remainingStones1 = 0;
						int remainingStones2 = 0;
						for (int i=0; i<model.getPitsPlayer1().length; i++)
						{
							int s = model.getPitsPlayer1()[i];
							if (s > 0)
							{
								remainingStones1 += s;
								model.updatePit(1,i,-s);
							}
						}

						for (int i=0; i<model.getPitsPlayer2().length; i++)
						{
							int s = model.getPitsPlayer2()[i];
							if (s > 0)
							{
								remainingStones2 += s;
								model.updatePit(2,i,-s);
							}
						}

						model.updateMancala(1, remainingStones1);
						model.updateMancala(2, remainingStones2);
						int player1Score = model.getMancalas()[0];
						int player2Score = model.getMancalas()[1];

						JFrame winnerF = new JFrame();
						if (player1Score > player2Score)
							JOptionPane.showMessageDialog(winnerF, "Player 1 is the winner!!!\nPlayer 1's Score: " + player1Score + "\nPlayer 2's Score: " 
									+ player2Score, "Game is finished", JOptionPane.INFORMATION_MESSAGE);
						else if (player2Score > player1Score)
							JOptionPane.showMessageDialog(winnerF, "Player 2 is the winner!!!\nPlayer 1's Score: " + player1Score + "\nPlayer 2's Score: " 
									+ player2Score, "Game is finished", JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(winnerF, "Draw!!!\nPlayer 1's Score: " + player1Score + "\nPlayer 2's Score: " 
									+ player2Score, "Game is finished", JOptionPane.INFORMATION_MESSAGE);
						
						winnerF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

						repaint();
						System.exit(0);
					}
					else if (!changed)
					{
						int pitValue = model.getPitsPlayer1()[index];
						MancalaModel.Turn currentTurn = model.getTurn();

						if (currentTurn == MancalaModel.Turn.PLAYER2)
						{
							JOptionPane.showMessageDialog(f, "It's currently Player 2's Turn.", "Warning", 
									JOptionPane.WARNING_MESSAGE);
						}
						else if (pitValue == 0)
						{
							JOptionPane.showMessageDialog(f, "Invalid Pick!!!", "Warning", 
									JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			});
		}
	}
	
	/**
	 * Gets the shapes of pits for player 1
	 * @return pitShapes1
	 */
	public DrawableShape[] getPitShape1()
	{
		return pitShapes1;
	}
	
	/**
	 * Sets the shapes of pits for player 1
	 * @param s the new shapes of pits for player 
	 */
	public void setPitShape1(DrawableShape[] s)
	{
		pitShapes1 = s;
	}
	
	/**
	 * Gets the shapes of pits for player 2
	 * @return pitShapes2
	 */
	public DrawableShape[] getPitShape2()
	{
		return pitShapes2;
	}
	
	/**
	 * Sets the shapes of pits for player 2
	 * @param s the new shapes of pits for player 2
	 */
	public void setPitShape2(DrawableShape[] s)
	{
		pitShapes2 = s;
	}
	
	/**
	 * Draw the panel
	 * @param g JAVA graphical library for drawing
	 */
	public void paintComponent(Graphics g)
	{
		int[] stones1 = model.getPitsPlayer1();
		int[] stones2 = model.getPitsPlayer2();
		super.paintComponent(g);
		DrawableShape[] shapes1 = getPitShape1();
		DrawableShape[] shapes2 = getPitShape2();
		for (int i=0; i<pits1.length; i++)
			pits1[i].setIcon(new PitIcon(new PitStone(stones1[i]), shapes1[i]));
		for (int i=0; i<pits2.length; i++)
			pits2[i].setIcon(new PitIcon(new PitStone(stones2[i]), shapes2[i]));
	}

	/**
	 * Receive notification from the model and repaint components
	 */
	public void stateChanged(ChangeEvent e)
	{
		repaint();
	}

}
