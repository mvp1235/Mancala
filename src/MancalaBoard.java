import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Mancala Board
 * @author Team Lucky Number 7
 */
public class MancalaBoard extends JFrame implements ChangeListener
{
	private MancalaModel model;
	private BoardStyle style;
	private MancalaPanel leftMancala;
	private MancalaPanel rightMancala;
	private PitsPanel pits;

	/**
	 * Construct a MancalaBoard object
	 * @param m the MancalaModel object to be modeled
	 * @param s the style of the board
	 */
	public MancalaBoard(MancalaModel m, BoardStyle s)
	{
		model = m;
		style = s;

		leftMancala = style.createLeftMancala(model);
		rightMancala =  style.createRightMancala(model);
		pits = style.createPits(model);

		add(leftMancala, BorderLayout.WEST);
		add(pits, BorderLayout.CENTER);
		add(rightMancala, BorderLayout.EAST); 

		model.attach(leftMancala);
		model.attach(rightMancala);
		model.attach(pits);


		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		top.setLayout(new FlowLayout());
		bottom.setLayout(new FlowLayout());
		
		final JLabel undo1 = new JLabel("A's Available Undo: " + model.getAvailableUndoPlayer1());
		final JLabel undo2 = new JLabel("B's Available Undo: " + model.getAvailableUndoPlayer2());
		JButton undoPlayer1 = new JButton("Undo");
		JButton undoPlayer2 = new JButton("Undo");
		top.add(undoPlayer2);
		top.add(undo2);
		bottom.add(undoPlayer1);
		bottom.add(undo1);
		
		undoPlayer1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				boolean changed = model.undoPlayer1();
				if (changed)
				{
					undo1.setText("A's Available Undo: " + model.getAvailableUndoPlayer1());
				}
				else if (model.getAvailableUndoPlayer1() == 0)
				{
					JOptionPane.showMessageDialog(null, "Player 1 had used all 3 undos.");
				}
			}
		});

		undoPlayer2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				boolean changed = model.undoPlayer2();
				if (changed)
				{
					undo2.setText("B's Available Undo: " + model.getAvailableUndoPlayer2());
				}
				else if (model.getAvailableUndoPlayer2() == 0)
				{
					JOptionPane.showMessageDialog(null, "Player 2 had used all 3 undos.");
				}
			}
		});

		add(bottom, BorderLayout.SOUTH);
		add(top, BorderLayout.NORTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,600);
		setVisible(true);
	}

	/**
	 * Set the style of the board
	 * @param s the style to be set
	 */
	public void setStyle(BoardStyle s)
	{
		style = s;
	}

	/**
	 * Receive notification of the model and repaint components
	 * @param e the changed event source
	 */
	public void stateChanged(ChangeEvent e)
	{
		leftMancala = style.createLeftMancala(model);
		rightMancala = style.createRightMancala(model);
		pits = style.createPits(model);
		repaint();
	}


}
