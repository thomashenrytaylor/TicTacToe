package view;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.TicTacToeGame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class GamePanel extends JPanel implements Observer 
{
	private static final long serialVersionUID = -3270710941228126933L;
	
	// DEFINE BUTTONS AND GRID
	private JButton[][] squares;
	private TicTacToeGame game;
	
	public GamePanel(TicTacToeGame game) 
	{
		this.game = game;
		setLayout(new GridLayout(game.getGameSize(), game.getGameSize(), 5, 5));
		squares = new JButton[game.getGameSize()][game.getGameSize()];
		
		// BUILD BUTTONS
		for (int row = 0; row < game.getGameSize(); row++) 
		{
			for (int col = 0; col < game.getGameSize(); col++) 
			{
				GUIController controller = new GUIController(row, col);
				squares[row][col] = new JButton(game.getPiece(row, col));
				squares[row][col].addActionListener(controller);
				add(squares[row][col]);
			}
		}
	}

	public void displayBoard() 
	{
		for (int row = 0; row < game.getGameSize(); row++) 
		{
			for (int col = 0; col < game.getGameSize(); col++) 
			{
				squares[row][col].setText(game.getPiece(row, col));
			}
		}
	}
	
	//action listener to respond when button is pressed
	class GUIController implements ActionListener 
	{
		private int row;
		private int column;
		
		public GUIController(int row, int col) 
		{
			this.row = row;
			this.column = col;
		}
		
		public void actionPerformed (ActionEvent event) 
		{
			boolean move = GamePanel.this.game.makeMove(row, column);
			if (move) {
				String winner = GamePanel.this.game.getWinner();
				if ( !winner.equals(TicTacToeGame.EMPTY) ) 
				{
					JOptionPane.showMessageDialog(null, 
							winner + " has won!", 
							"Congratulations", JOptionPane.PLAIN_MESSAGE);

					GamePanel.this.game.resetGame();
					return;
				}
				//if there is no winner but there are no moves left then it must be a draw!
				else if ( GamePanel.this.game.getMovesLeft() == 0 ) 
				{
					JOptionPane.showMessageDialog(null, 
							"The game is a draw - no-one wins!", 
							"Draw", JOptionPane.PLAIN_MESSAGE);
					GamePanel.this.game.resetGame();
					return;
				}
			}
			else 
			{
				JOptionPane.showMessageDialog(null, 
						"You cannot place piece there", 
						"Invalid move", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		displayBoard();
	}
}