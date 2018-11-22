package prj2.ThreeDimensions;

public class ConnectFourGame3D {

	private int[][][] board;
	private int size;
	private int player;
	private int playerCount;
	private int connections;

	/**
	 * Constructor
	 * 
	 * @param pSize
	 */
	public ConnectFourGame3D(int pSize) {
		this.size = pSize;
		this.board = new int[pSize][pSize][pSize];
		this.playerCount = 2;
		this.connections = 4;
		this.player = 0;
		reset();
	}

	/**
	 * Secondary constructor
	 * @param pSize
	 * @param bd
	 */
	public ConnectFourGame3D(int pSize, int[][][] bd) {
		this.size = pSize;
		this.board = board;
		this.playerCount = 2;
		this.connections = 4;
		this.player = 0;
	}

	/**
	 * Reset Method
	 */
	public void reset() {
		for (int row = 0; row < this.size; row++) {
			for (int col = 0; col < this.size; col++) {
				for (int depth = 0; depth < this.size; depth++) {
					this.board[row][col][depth] = -1;
				}
			}
		}
		this.player = 0;
	}
	/******************************************************************
	 * Selection of a column
	 * 
	 * 
	 * @param pCol
	 * @return
	 *****************************************************************/
	public int selectCol(int row, int col) {
		for (int depth = this.size - 1; depth >= 0; depth--) {
			if (this.board[row][col][depth] == -1) {
				this.board[row][col][depth] = this.player;
				return depth;
			}
		}
		return -1;
	}
	
	/**
	 * Switches player
	 * 
	 * @return
	 */
	public int switchplayer() {
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

	/**
	 * Checks to see if there are any moves left
	 * 
	 * @return
	 */
	public boolean moveRemaining() {
		for (int col = 0; col < this.size; col++) {
			for (int row = 0; row < this.size; row++) {
				if (this.board[row][col][0] == -1) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Getter
	 * @return
	 */
	public boolean isWinner() {
		return isWinner(this.player);
	}

	/****************************************************************
	 * Checks to see if the current player is a winner
	 * 
	 * 
	 * @param person
	 * @return
	 ****************************************************************/
	public boolean isWinner(int person) {
		boolean winner = false;
		Cube cube = new Cube(0, 0, 0);
		for (int row = 0; row < this.size; row++) {
			for (int col = 0; col < this.size; col++) {
				for (int depth = 0; depth < this.size; depth++) {
					for (int n = 0; n < Cube.DIRECTION.length; n++) {
						cube.reset(row, col, depth);
						winner = isConnected(cube, Cube.DIRECTION[n], this.connections, person);
						if (winner) {
							return winner;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * The inRange method returns whether the parameter number is contained within
	 * the half-open interval [0, size).
	 * 
	 * @param r
	 * @param c
	 ****************************************************************/
	private boolean inRange(int number) {
		return (0 <= number) && (number < this.size);
	}

	/**
	 * The inBounds method returns whether the Location parameter identifies a
	 * location in the gameBoard. This means that both the row and the column fields
	 * are contained within the half-open interval [0, size).
	 * 
	 * @param Location
	 ****************************************************************/
	private boolean inBounds(Cube cube) {
		return (inRange(cube.row)) && (inRange(cube.column)) && (inRange(cube.depth));
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
	private boolean isConnected(Cube cube, Cube direction, int count, int target) {
		for (int i = 0; i < count; i++) {
			if ((!inBounds(cube)) || (this.board[cube.row][cube.column][cube.depth] != target)) {
				return false;
			}
			cube.navigate(direction);
		}
		return true;
	}

	/**
	 * Getter 
	 * @param row
	 * @param col
	 * @return
	 */
	public String getPiece(int row, int col) {
		String temp = "";
		for (int depth = 0; depth < this.size; depth++) {
			if (this.board[row][col][depth] != -1) {
				temp = this.board[row][col][depth] + temp;
			}
		}
		return temp;
	}

	/**
	 * Prints the current board
	 * @param board
	 */
	public void printBoard(int[][] board) {
		for (int r = 0; r < 7; r++) {
			for (int c = 0; c < 7; c++) {
				if (board[r][c] == -1) {
					System.out.print("  ");
				} else {
					System.out.print(board[r][c] + " ");
				}
			}
			System.out.println();
		}
	}
}
