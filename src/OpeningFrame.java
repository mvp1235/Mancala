import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Initial screen for choosing board style and number of starting stones
 * @author Team Lucky Number 7
 */
public class OpeningFrame extends JFrame
{
	/*
	 * Construct an OpeningFrame
	 */
	public OpeningFrame()
	{
		final MancalaModel model = new MancalaModel(0);

		setLayout(new FlowLayout());
		setLocation(400, 100);
		JButton s1 = new JButton("Classic Style");
		JButton s2 = new JButton("Fancy Style");
		add(s1);
		add(s2);

		//Button for Classic Style
		s1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				BoardStyle style = new ClassicStyle();
				MancalaBoard board = new MancalaBoard(model, style);

				final JFrame f = new JFrame();
				f.setLayout(new GridLayout());
				JLabel prompt = new JLabel("Number of stones (3-4)");
				f.add(prompt);

				JTextField tf = new JTextField();
				f.add(tf);

				f.setSize(300, 70);
				f.setLocation(400, 100);
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				tf.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						JTextField t = (JTextField)e.getSource();
						int num = Integer.parseInt(t.getText());
						if (num < 3 || num > 4)
						{
							JFrame errorF = new JFrame("ERROR");
							JLabel l = new JLabel("You can only choose 3 or 4. Any other inputs will be "
												+ "replaced by default value 3.");
							errorF.add(l);
							errorF.setSize(500,80);
							errorF.setVisible(true);
							errorF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

						}
						model.setNumStones(num);

						for (ChangeListener c : model.getListeners())
							c.stateChanged(new ChangeEvent(this));
						board.repaint();
						f.dispose();
					}

				});
				dispose();
			}

		});

		//Button for Fancy Style
		s2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				BoardStyle style = new FancyStyle();
				MancalaBoard board = new MancalaBoard(model, style);

				final JFrame f = new JFrame();
				f.setLayout(new GridLayout());
				JLabel prompt = new JLabel("Number of stones (3-4)");
				f.add(prompt);

				JTextField tf = new JTextField();
				f.add(tf);

				f.setSize(300, 70);
				f.setLocation(400, 100);
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				tf.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						JTextField t = (JTextField)e.getSource();
						int num = Integer.parseInt(t.getText());
						if (num < 3 || num > 4)
						{
							JFrame errorF = new JFrame("ERROR");
							JLabel l = new JLabel("You can only choose 3 or 4. Any other inputs will be "
												+ "replaced by default value 3.");
							errorF.add(l);
							errorF.setSize(500,80);
							errorF.setVisible(true);
							errorF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

						}
						model.setNumStones(num);

						for (ChangeListener c : model.getListeners())
							c.stateChanged(new ChangeEvent(this));
						board.repaint();
						f.dispose();
					}

				});
				dispose();
			}
		});

		setSize(300, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);

	}
}
