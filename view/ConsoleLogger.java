package view;

import java.util.Observable;
import java.util.Observer;

import model.TicTacToeGame;

public class ConsoleLogger implements Observer 
{
	
	private static ConsoleLogger instance;
	private int count;
	
	private ConsoleLogger() { count = 0; }
	
	public static ConsoleLogger getInstance() 
	{
		if (instance == null) { instance = new ConsoleLogger(); }
		return instance;
	}
	
	@Override
	public void update(Observable o, Object arg1) 
	{
		if (o instanceof TicTacToeGame) 
		{
			if (arg1 != null) { System.out.println(arg1); }
			TicTacToeGame game = (TicTacToeGame)o;
			count++;
			System.out.println(this.toString() + " Number of updates: " + count);
			for (int row = 0; row < game.getGameSize(); row++) 
			{
				for (int col = 0; col < game.getGameSize(); col++) 
				{
					System.out.print("|"+game.getPiece(row, col));
				}
				System.out.println("|");
			}
			if ( !game.getWinner().equals(TicTacToeGame.EMPTY) || game.getMovesLeft() == 0 ) 
			{
				System.out.println("Noughts won " + game.getNoughtWins());
				System.out.println("Crosses won " + game.getCrossWins());
				System.out.println("Draws " + game.getDraws());
			}
		}
	}
}