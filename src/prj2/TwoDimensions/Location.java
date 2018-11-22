package prj2.TwoDimensions;

/********************************************************************************
 * Location defines a class to encapsulate a location, perhaps on some game
 * board, as a row and column pair.
 * 
 * Four static fields are included for navigation in four specific directions.
 * These four directions can also be identified by index into the given static
 * DIRECTION array.
 * 
 * @author Zack Poorman & Noah Strasler
 * @version 2/26/18
 *
 *********************************************************************************/
public class Location {

	/** Two fields to identify a location */
	public int row;
	public int column;

	/** static fields to define navigation directions */
	public static final Location ROW = new Location(1, 0);
	public static final Location COLUMN = new Location(0, 1);
	public static final Location DIAGONAL_RIGHT = new Location(1, 1);
	public static final Location DIAGONAL_LEFT = new Location(1, -1);

	/** static field to define an array of navigation directions */
	public static final Location[] DIRECTION = { ROW, COLUMN, DIAGONAL_RIGHT, DIAGONAL_LEFT };

	/*******************************************************************
	 * Location constructor initializes the location fields: row and column.
	 * 
	 * @param r
	 * @param c
	 ********************************************************************/
	public Location(int r, int c) {
		this.reset(r, c);
	}

	/****************************************************************
	 * The reset method resets the row and column fields with the r and c parameter
	 * values for a new position, perhaps on some game board.
	 * 
	 * @param r
	 * @param c
	 ****************************************************************/
	public void reset(int r, int c) {
		this.row = r;
		this.column = c;
	}

	/*******************************************************************************
	 * The navigate method changes this location by adding to its row and column
	 * fields the values of the row and column fields of the change Location,
	 * respectively.
	 * 
	 * @param change
	 *******************************************************************************/
	public void navigate(Location change) {
		this.row += change.row;
		this.column += change.column;
	}

	/*******************************************************************************
	 * The toString method returns a string with the format: (r, c)
	 * 
	 * @return
	 *******************************************************************************/
	public String toString() {
		return "(" + this.row + "," + this.column + ")";
	}

	/*******************************************************************************
	 * The printReferences method prints a trace of the location references under
	 * navigation.
	 * 
	 * @param direction
	 * @param times
	 *******************************************************************************/
	public void printReferences(Location direction, int pathLength) {
		for (int n = 0; n < pathLength; n++) {
			System.out.print(this.toString());
			this.navigate(direction);
		}
		System.out.println();
	}

	/***************************************************************
	 * This main method uses the static navigation direction fields to print a
	 * sample of navigation paths.
	 * 
	 * @param args
	 ***************************************************************/
	public static void main(String[] args) {

		Location location = new Location(0, 0);

		location.reset(0, 0);
		System.out.print("Go ROW:\t\t   ");
		location.printReferences(ROW, 4);

		location.reset(0, 4);
		System.out.print("Go COLUMN:\t   ");
		location.printReferences(COLUMN, 4);

		location.reset(0, 0);
		System.out.print("Go DIAGONAL_RIGHT: ");
		location.printReferences(DIAGONAL_RIGHT, 4);

		location.reset(0, 4);
		System.out.print("Go DIAGONAL_LEFT:  ");
		location.printReferences(DIAGONAL_LEFT, 4);

		location.reset(0, 4);
		System.out.print("Go DIAGONAL_LEFT:  ");
		location.printReferences(DIAGONAL_LEFT, 7);

		System.out.println();

		String[] directions = { "Go ROW:\t\t   ", "Go COLUMN:\t   ", "Go DIAGONAL_RIGHT: ", "Go DIAGONAL_LEFT:  " };
		Location[] c = { new Location(0, 0), new Location(0, 4), new Location(0, 0), new Location(0, 4) };

		for (int n = 0; n < directions.length; n++) {
			System.out.print(directions[n]);
			c[n].printReferences(Location.DIRECTION[n], 4);
		}
	}
}
