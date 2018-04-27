import java.util.ArrayList;
import javax.swing.event.*;

/**
 * Mancala Model
 * @author Team Lucky Number 7
 */
public class MancalaModel
{
	public enum Turn
	{
		PLAYER1, PLAYER2
	}
	final int MAX_UNDO = 3;
	private int player1UndoCount;
	private int player2UndoCount;
	private boolean player1UndoAllow;
	private boolean player2UndoAllow;
	
	//Store current pits
	private int[] player1;
	private int[] player2;
	private int[] mancalas;
	//Store previous pits for undo
	private int[] oldPlayer1;
	private int[] oldPlayer2;
	private int[] oldMancalas;
	
	private int numStone;
	private ArrayList<ChangeListener> listeners;
	private Turn currentTurn;
	
	
	/**
	 * Construct a MancalaModel object
	 * @param num number of starting stones in each pit, 3 or 4 only
	 */
	public MancalaModel(int num)
	{
		player1UndoCount = 0;
		player2UndoCount = 0;
		player1UndoAllow = false;
		player2UndoAllow = false;
		
		//Player 1 has the first turn in game
		currentTurn = Turn.PLAYER1;
		player1 = new int[6];
		player2 = new int[6];
		oldPlayer1 = new int[6];
		oldPlayer2 = new int[6];
		
		for (int i=0; i<player1.length; i++)
		{
			player1[i] = num;
			oldPlayer1[i] = num;
		}
		for (int i=0; i<player2.length; i++)
		{
			player2[i] = num;
			oldPlayer2[i] = num;
		}
		
		mancalas = new int[2];
		oldMancalas = new int[2];
		for (int i=0; i<mancalas.length; i++)
		{
			mancalas[i] = 0;
			oldMancalas[i] = 0;
		}
		
		numStone = num;
		listeners = new ArrayList<>();
		
	}
	
	/**
	 * Clear all stones from chosen pit, then distribute across following pits
	 * @param position position of the chosen pit
	 * @return true if stones were distributed, false otherwise
	 */
	public boolean distributeStonesPlayer1(int position)
	{
		if (currentTurn != Turn.PLAYER1 || player1[position] == 0)
			return false;
		
		player1UndoAllow = true;
		player2UndoAllow = false;
		saveCurrentSnap();
		
		int remainingStones = player1[position];
		player1[position] = 0;
		
		boolean firstLoop = true;
		while (remainingStones > 0)
		{
			int start = position + 1;
			if (!firstLoop)
				start = 0;
			
			//Filling the remaining pits of current player
			for (; start < player1.length && remainingStones>0; start++)
			{
				updatePit(1, start);
				remainingStones--;
				//If last stone landed on an empty pit on the player's side
				if (remainingStones == 0 && player1[start] == 1)
				{
					int oppositeIndex = 5 - start;
					if (player2[oppositeIndex] > 0)
					{
						int winAmount = player2[oppositeIndex] + player1[start];
						updateMancala(1, winAmount);				//Add captured stones to player's Mancala
						updatePit(1, start, 0-player1[start]);		//Set both captured pits to 0
						updatePit(2, oppositeIndex, 0-player2[oppositeIndex]);
					}
				}
			}
			
			if (remainingStones > 0)
			{
				updateMancala(1);
				remainingStones--;
				
				//Free turn if last stone landed in the player's mancala
				if (remainingStones == 0)
				{
					for (ChangeListener c : listeners)
						c.stateChanged(new ChangeEvent(this));
					return true;
				}
			}
			
			
			if (remainingStones > 0)
			{
				for (start=0; start<player2.length && remainingStones>0; start++)
				{
					updatePit(2, start);
					remainingStones--;
				}
			}
			
			firstLoop = false;
		}
		
		for (ChangeListener c : listeners)
			c.stateChanged(new ChangeEvent(this));
		
		currentTurn = Turn.PLAYER2;
		return true;
	}
	
	/**
	 * Clear all stones from chosen pit, then distribute across following pits
	 * @param position position of the chosen pit
	 * @return true if stones were distributed, false otherwise
	 */
	public boolean distributeStonesPlayer2(int position)
	{
		if (currentTurn != Turn.PLAYER2 || player2[position] == 0)
			return false;
		
		player2UndoAllow = true;
		player1UndoAllow = false;
		saveCurrentSnap();
		
		int remainingStones = player2[position];
		player2[position] = 0;
		
		boolean firstLoop = true;
		while (remainingStones > 0)
		{
			int start = position + 1;
			if (!firstLoop)
				start = 0;
			
			//Filling the remaining pits of current player
			for (; start < player2.length && remainingStones>0; start++)
			{
				updatePit(2, start);
				remainingStones--;
				
				//If last stone landed on an empty pit on the player's side
				if (remainingStones == 0 && player2[start] == 1)
				{
					int oppositeIndex = 5 - start;
					if (player1[oppositeIndex] > 0)
					{
						int winAmount = player1[oppositeIndex] + player2[start];
						updateMancala(2, winAmount);				//Add captured stones to player's Mancala
						updatePit(2, start, 0-player2[start]);		//Set both captured pits to 0
						updatePit(1, oppositeIndex, 0-player1[oppositeIndex]);
					}
				}
			}
			if (remainingStones > 0)
			{
				updateMancala(2);
				remainingStones--;
				
				//Free turn if last stone landed in the player's mancala
				if (remainingStones == 0)
				{
					for (ChangeListener c : listeners)
						c.stateChanged(new ChangeEvent(this));
					return true;
				}
			}
			if (remainingStones > 0)
			{
				for (start=0; start<player1.length && remainingStones>0; start++)
				{
					updatePit(1, start);
					remainingStones--;
				}
			}
			
			firstLoop = false;
		}
		
		for (ChangeListener c : listeners)
			c.stateChanged(new ChangeEvent(this));
		
		currentTurn = Turn.PLAYER1;
		return true;
	}
	
	/**
	 * Increment a chosen pit by 1
	 * @param player specify which player, 1 is Player1, 2 is Player2
	 * @param position the index of the pit to be changed
	 */
	private void updatePit(int player, int position)
	{
		if (player == 1)
			player1[position]++;
		else if (player == 2)
			player2[position]++;
		
		for (ChangeListener c : listeners)
			c.stateChanged(new ChangeEvent(this));
	}
	
	/**
	 * Add an amount of stones to a chosen pit
	 * @param player specify which player, 1 is Player1, 2 is Player2
	 * @param position position of the pit to be changed
	 * @param amount the amount of stone to be added
	 */
	public void updatePit(int player, int position, int amount)
	{
		if (player == 1)
			player1[position] += amount;
		else if (player == 2)
			player2[position] += amount;
		
		for (ChangeListener c : listeners)
			c.stateChanged(new ChangeEvent(this));
	}
	
	/**
	 * Increment the mancala by 1
	 * @param player specify which player, 1 is Player1, 2 is Player2
	 */
	public void updateMancala(int player)
	{
		if (player == 1)
			mancalas[0]++;
		else if (player == 2)
			mancalas[1]++;
		
		//for (ChangeListener c : listeners)
		//	c.stateChanged(new ChangeEvent(this));
		
	}
	
	/**
	 * Increment mancala by an amount
	 * @param player specify which player, 1 is Player1, 2 is Player2
	 * @param amount the number of stones to be added
	 */
	public void updateMancala(int player, int amount)
	{
		if (player == 1)
			mancalas[0] += amount;
		else if (player == 2)
			mancalas[1] += amount;
		
		for (ChangeListener c : listeners)
			c.stateChanged(new ChangeEvent(this));
	}
	
	/**
	 * Add an ChangeListener to the list of listeners
	 * @param c the ChangeListener to be added
	 */
	public void attach(ChangeListener c)
	{
		listeners.add(c);
	}

	/**
	 * Allow player 1 to undo his choice.
	 * It's only valid right after player 1 finishes his turn and before player 2 makes a move
	 * @return true if successfully undo, false otherwise
	 */
	public boolean undoPlayer1()
	{
		if (!player1UndoAllow || player1UndoCount >= 3)
			return false;
		if (!modelChanged())
			return false;
		player1UndoCount++;
		player1UndoAllow = false;
		currentTurn = Turn.PLAYER1;
		restorePreviousSnap();
		
		for (ChangeListener c : listeners)
			c.stateChanged(new ChangeEvent(this));
		
		return true;
	}
	
	/**
	 * Allow player 2 to undo his choice.
	 * It's only valid right after player 2 finishes his turn and before player 1 makes a move
	 * @return true if successfully undo, false otherwise
	 */
	public boolean undoPlayer2()
	{
		if (!player2UndoAllow || player2UndoCount >= 3)
			return false;
		if (!modelChanged())
			return false;
		
		player2UndoCount++;
		player2UndoAllow = false;
		currentTurn = Turn.PLAYER2;
		restorePreviousSnap();
		
		for (ChangeListener c : listeners)
			c.stateChanged(new ChangeEvent(this));
		
		return true;
	}
	
	/**
	 * Checks whether any player made any changes before allowing undo
	 * @return
	 */
	public boolean modelChanged()
	{
		for (int i=0; i<player1.length; i++)
			if (oldPlayer1[i] != player1[i])
				return true;
		for (int i=0; i<player2.length; i++)
			if (oldPlayer2[i] != player2[i])
				return true;
		for (int i=0; i<mancalas.length; i++)
			if (oldMancalas[i] != mancalas[i])
				return true;
		
		return false;
	}
	
	/**
	 * Save the current state of the game to prepare for players' undo
	 */
	public void saveCurrentSnap()
	{
		for (int i=0; i<player1.length; i++)
			oldPlayer1[i] = player1[i];
		for (int i=0; i<player2.length; i++)
			oldPlayer2[i] = player2[i];
		for (int i=0; i<mancalas.length; i++)
			oldMancalas[i] = mancalas[i];
	}
	
	/**
	 * Restore the previous state of the game after a player undo
	 */
	public void restorePreviousSnap()
	{
		for (int i=0; i<player1.length; i++)
			player1[i] = oldPlayer1[i];
		for (int i=0; i<player2.length; i++)
			player2[i] = oldPlayer2[i];
		for (int i=0; i<mancalas.length; i++)
			mancalas[i] = oldMancalas[i];
	}
	
	/**
	 * Checks whether the game has been finished
	 * @return true if at least one side of the board has no stones, false otherwise
	 */
	public boolean finishedGame()
	{
		int sum1 = 0, sum2 = 0;
		for (int i=0; i<player1.length; i++)
			sum1 += player1[i];
		for (int i=0; i<player2.length; i++)
			sum2 += player2[i];
		
		return sum1 == 0 || sum2 == 0;
	}
	
	/**
	 * Gets the amount of undo available for player 1
	 * @return the available times which player 1 can undo
	 */
	public int getAvailableUndoPlayer1()
	{
		return MAX_UNDO - player1UndoCount;
	}
	
	/**
	 * Gets the amount of undo available for player 2
	 * @return the available times which player 2 can undo
	 */
	public int getAvailableUndoPlayer2()
	{
		return MAX_UNDO - player2UndoCount;
	}
	
	/**
	 * Gets the pits for player 1
	 * @return player 1's pits
	 */
	public int[] getPitsPlayer1()
	{
		return player1;
	}
	
	/**
	 * Gets the pits for player 2
	 * @return player 2's pits
	 */
	public int[] getPitsPlayer2()
	{
		return player2;
	}
	
	/**
	 * Gets the mancalas of both players, mancalas[0] is player 1, mancalas[1] is player 2
	 * @return mancalas of the players
	 */
	public int[] getMancalas()
	{
		return mancalas;
	}
	
	/**
	 * Gets the starting number of stones
	 * @return numStone
	 */
	public int getNumStone()
	{
		return numStone;
	}
	
	/**
	 * Sets the amount of starting stones
	 * @param num number of starting stones
	 * precondition: num = 3 or num = 4 only. Any other value will be automatically changed to 3
	 */
	public void setNumStones(int num)
	{
		if (num < 3 || num > 4)
			num = 3;
		
		numStone = num;
		for (int i=0; i<player1.length; i++)
			player1[i] = num;
		for (int i=0; i<player2.length; i++)
			player2[i] = num;
		for (int i=0; i<mancalas.length; i++)
			mancalas[i] = 0;
		
		for (ChangeListener c : listeners)
			c.stateChanged(new ChangeEvent(this));
	}
	
	/**
	 * Gets the current turn
	 * @return the current turn
	 */
	public Turn getTurn()
	{
		return currentTurn;
	}
	
	/**
	 * Gets the list of attached listeners
	 * @return listeners
	 */
	public ArrayList<ChangeListener> getListeners()
	{
		return listeners;
	}
}
