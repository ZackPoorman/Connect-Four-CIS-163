package prj2.TwoDimensions;

import java.util.*;
import java.awt.*;

/*
 * Simulates the Connect Four game:
 * handles all of the game activities.
 * 
 * @author Zack Poorman & Noah Strasler
 * @version 2/26/18
 */

public class ConnectFourGame {

	private int[][] gameBoard;
	private int size;
	private int player;
	private int playerCount;
	private int pathLength;

	public static final int USER = 0;
	public static final int COMPUTER = 1;

	/*******************************************************************
	 * ConnectFourGame Constructor
	 * 
	 * 
	 * @param pSize
	 ********************************************************************/
	public ConnectFourGame(int pSize) {

		player = 0;
		playerCount = 2;
		pathLength = 4;
		this.size = pSize;
		gameBoard = new int[size][size];
		this.reset();

	}

	/*******************************************************************
	 * ConnectFourGame Constructor
	 * 
	 * 
	 * @param pSize
	 * @param gameBoard
	 ********************************************************************/
	public ConnectFourGame(int pSize, int[][] gameBoard) {

		player = 0;
		playerCount = 2;
		pathLength = 4;
		this.size = pSize;
		gameBoard = new int[size][size];
		this.reset();

	}

	/******************************************************************
	 * Selection of a column
	 * 
	 * 
	 * @param pCol
	 * @return
	 *****************************************************************/
	public int selectCol(int pCol) {
		for (int row = this.size - 1; row >= 0; row--) {
			if (this.gameBoard[row][pCol] == -1) {
				this.gameBoard[row][pCol] = this.player;
				return row;
			}
		}
		return -1;
	}

	/****************************************************************
	 * The reset method
	 * 
	 * 
	 ****************************************************************/
	public void reset() {
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				this.gameBoard[i][j] = -1;
			}
		}
		this.player = 0;
	}

	/**
	 * Switches player
	 * 
	 * @return
	 */
	public int switchPlayer() {
		this.player = ((this.player + 1) % this.playerCount);
		return this.player;
	}

	/**
	 * Getters
	 * 
	 * @return
	 */
	public int getCurrentPlayer() {
		return this.player;
	}

	public boolean playerIsComputer() {
		return this.player == 1;
	}

	public boolean isWinner() {
		return isWinner(this.player);
	}

	public String getWinner() {
		if (player == USER) {
			return "User";
		} else {
			return "Computer";
		}
	}

	/**
	 * Checks to see if there are any remaining moves
	 * 
	 * @return
	 */
	public boolean moveRemaining() {
		for (int col = 0; col < this.size; col++) {
			if (this.gameBoard[0][col] == -1) {
				return true;
			}
		}
		return false;
	}

	/****************************************************************
	 * Checks to see if the current player is a winner
	 * 
	 * 
	 * @param person
	 * @return
	 ****************************************************************/
	public boolean isWinner(int person) {
		Location location = new Location(0, 0);
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				for (int n = 0; n < Location.DIRECTION.length; n++) {
					location.reset(row, col);
					if (isConnected(location, Location.DIRECTION[n], pathLength, player)) {
						return true;
					}
				}
			}
		}
		return false;

	}

	/**
	 * The isConnected method returns whether each Location in the path of Squares
	 * contains the target value, and that the path does not go out of bounds during
	 * the navigation.
	 * 
	 * 
	 * @param Location
	 * @param direction
	 * @param pathLength
	 * @param target
	 * @return
	 ****************************************************************/
	public boolean isConnected(Location location, Location direction, int pathLength, int player) {
		for (int i = 0; i < pathLength; i++) {
			if (!inBounds(location) || (gameBoard[location.row][location.column] != player)) {
				return false;
			}
			location.navigate(direction);
		}

		return true;
	}

	/**
	 * The inBounds method returns whether the Location parameter identifies a
	 * location in the gameBoard. This means that both the row and the column fields
	 * are contained within the half-open interval [0, size).
	 * 
	 * @param Location
	 ****************************************************************/
	private boolean inBounds(Location location) {
		return inRange(location.row) && inRange(location.column);
	}

	/**
	 * The inRange method returns whether the parameter number is contained within
	 * the half-open interval [0, size).
	 * 
	 * @param r
	 * @param c
	 ****************************************************************/
	private boolean inRange(int number) {
		return 0 <= number && number < size;
	}

	/**
	 * 
	 * The moveAI method uses isConnected and isWinner along with other methods to
	 * evaluate current board conditions. These conditions are then used to either
	 * block the player or win.
	 */
	public void moveAI() {

		// 1. See if you can win, if so, select that column and win.
		// player is computer
		for (int col = 0; col < this.size; col++) {
			int row = selectCol(col);
			if (row != -1) {
				if (isWinner(USER)) {
					return;
				}
				this.gameBoard[row][col] = -1;
			}
		}
		switchPlayer();

		// 2. See if you are going to lose, if so, select the column that blocks that
		// move
		for (int col = 0; col < this.size; col++) {
			int row = selectCol(col);
			if (row != -1) {
				if (isWinner(0)) {
					switchPlayer();
					this.gameBoard[row][col] = 1;
					return;
				}
				this.gameBoard[row][col] = -1;
			}
		}
		switchPlayer();

		// 3. If rules 1 and 2 are not in play, then develop tactics to select a column
		// that may help you in the future.
		int row = -1;
		while (row == -1) {
			row = selectCol(new Random().nextInt(this.size));
		}
	}

	/**
	 * Getter
	 * @param row
	 * @param col
	 * @return
	 */
	public String getValue(int row, int col) {
		if (gameBoard[row][col] == 0) {
			return "0";
		} else if (gameBoard[row][col] == 1) {
			return "1";
		}
		return "_";
	}

	/**
	 * Getter
	 * @param row
	 * @param col
	 * @return
	 */
	public Color getColor(int row, int col) {
		if (gameBoard[row][col] == 0) {
			return Color.red;
		} else if (gameBoard[row][col] == 1) {
			return Color.blue;
		}
		return Color.gray;
	}

	/**
	 * Prints the board
	 */
	public void printGameBoardTwo() {
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				printSquare(r, c);
			}
			System.out.println();
		}
	}

	/**
	 * Prints the board
	 * @param board
	 */
	public void printGameBoard(int[][] board) {
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				printSquare(r, c);
			}
			System.out.println();
		}
	}
	
/**
 * Prints the square
 * 
 * @param r
 * @param c
 */
	private void printSquare(int r, int c) {
		String s = " ";

		s += gameBoard[r][c] + " ";
		s = s.substring(s.length() - 3);

		System.out.print(s);
	}
}
