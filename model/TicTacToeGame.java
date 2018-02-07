package model;

import java.util.Observable;

public class TicTacToeGame extends Observable {

	private int gameSize;
	public static final String EMPTY = " ";
	public static final String NOUGHT = "O";
	public static final String CROSS = "X";
	private int noughtWins = 0;
	private int crossWins = 0;
	private int draws = 0;
	private int round = 0;

	private String[][] squares;
	private String current;
	private int movesLeft;

	public TicTacToeGame(int game_size) {
		this.gameSize = game_size;
		squares = new String[gameSize][gameSize];
		resetGame();
	}

	public int getGameSize() {
		return gameSize;
	}

	public int getNoughtWins() {
		return noughtWins;
	}

	public int getCrossWins() {
		return crossWins;
	}

	public int getDraws() {
		return draws;
	}

	public String getPiece(int row, int col) {
		return squares[row][col];
	}

	public String getWinner() {
		String winner = checkRows();
		if (!winner.equals(EMPTY)) {
			return winner;
		}
		winner = checkColumns();
		if (!winner.equals(EMPTY)) {
			return winner;
		}
		winner = checkDiaganols();
		if (!winner.equals(EMPTY)) {
			return winner;
		}
		return EMPTY;
	}

	public int getMovesLeft() {
		return movesLeft;
	}

	private String checkRows() {
		for (int row = 0; row < gameSize; row++) {
			boolean hasWinner = true;
			String first = squares[row][0];
			for (int col = 0; col < gameSize; col++) {
				// if there is a blank in the row or any item in the row is not
				// the same as the first
				// then this row does not have a winner
				if (squares[row][col].equals(EMPTY) || !squares[row][col].equals(first)) {
					hasWinner = false;
					break;
				}
			}
			if (hasWinner) {
				return first;
			}
		}
		return EMPTY;
	}

	private String checkColumns() {
		for (int col = 0; col < gameSize; col++) {
			boolean hasWinner = true;
			String first = squares[0][col];
			for (int row = 0; row < gameSize; row++) {
				// if there is a blank in the column or any item in the column
				// is not the same as the first
				// then this column does not have a winner
				if (squares[row][col].equals(EMPTY) || !squares[row][col].equals(first)) {
					hasWinner = false;
					break;
				}
			}
			if (hasWinner) {
				return first;
			}
		}
		return EMPTY;
	}

	private String checkDiaganols() {
		boolean hasWinner = true;
		String first = squares[0][0];
		for (int i = 0; i < gameSize; i++) {
			// if there is a blank in the diagonal or any item in the diagonal
			// is not the same as the first
			// then this diagonal does not have a winner
			if (squares[i][i].equals(EMPTY) || !squares[i][i].equals(first)) {
				hasWinner = false;
				break;
			}
		}
		if (hasWinner) {
			return first;
		}
		// check the other diagonal
		hasWinner = true;
		first = squares[0][gameSize - 1];
		for (int i = 0; i < gameSize; i++) {
			// if there is a blank in the diagonal or any item in the diagonal
			// is not the same as the first
			// then this diagonal does not have a winner
			if (squares[i][gameSize - 1 - i].equals(EMPTY) || !squares[i][gameSize - 1 - i].equals(first)) {
				hasWinner = false;
				break;
			}
		}
		if (hasWinner) {
			return first;
		}
		return EMPTY;
	}

	public void resetGame() {
		for (int row = 0; row < gameSize; row++) {
			for (int col = 0; col < gameSize; col++) {
				squares[row][col] = EMPTY;
			}
		}
		current = NOUGHT;
		movesLeft = gameSize * gameSize;
		round++;
		setChanged();
		notifyObservers("Begin round " + round);
	}

	public boolean makeMove(int row, int col) {

		// if the click was on an empty square the move is valid so update the
		// game
		if (squares[row][col].equals(EMPTY)) {
			squares[row][col] = current;
			movesLeft--;

			// check whether there is a winner
			String winner = getWinner();
			if (!winner.equals(EMPTY)) {
				if (winner.equals(NOUGHT)) {
					noughtWins++;
				} else if (winner.equals(CROSS)) {
					crossWins++;
				}
				setChanged();
				notifyObservers("Game Over. Winner is " + winner);
				return true;
			}

			// if there is no winner but there are no moves left then it must be
			// a draw!
			else if (getMovesLeft() == 0) {
				draws++;
				setChanged();
				notifyObservers("Game Over. Draw");
				return true;
			}

			// change whose turn it is
			if (current.equals(NOUGHT)) {
				current = CROSS;
			} else {
				current = NOUGHT;
			}
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}
}