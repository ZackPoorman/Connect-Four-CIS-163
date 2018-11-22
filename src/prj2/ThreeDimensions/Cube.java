package prj2.ThreeDimensions;

/********************************************************************************
 * Cube defines a class to encapsulate the location of a cube, perhaps within
 * some three dimensional solid, as a row, column, depth triple.
 * 
 * Eleven static fields are included for navigation in eleven specific
 * directions. These eleven directions can also be identified by index into the
 * given static DIRECTION array.
 * 
 * @author <your name>
 * @version <date for today>
 *
 *********************************************************************************/
public class Cube {

	public int row;
	public int column;
	public int depth;

	public static final Cube COLUMN = new Cube(0, 1, 0);
	public static final Cube ROW = new Cube(1, 0, 0);
	public static final Cube VERTICAL = new Cube(0, 0, 1);

	public static final Cube DIAGONAL_RIGHT = new Cube(1, 1, 0);
	public static final Cube DIAGONAL_LEFT = new Cube(1, -1, 0);

	public static final Cube ROW_UP = new Cube(1, 0, 1);
	public static final Cube ROW_DOWN = new Cube(1, 0, -1);

	public static final Cube DIAGONAL_RIGHT_UP = new Cube(1, 1, 1);
	public static final Cube DIAGONAL_RIGHT_DOWN = new Cube(1, 1, -1);

	public static final Cube DIAGONAL_LEFT_UP = new Cube(1, -1, 1);
	public static final Cube DIAGONAL_LEFT_DOWN = new Cube(1, -1, -1);

	public static final Cube[] DIRECTION = {

			ROW, COLUMN, VERTICAL, DIAGONAL_RIGHT, DIAGONAL_LEFT, ROW_UP, ROW_DOWN, DIAGONAL_RIGHT_UP,
			DIAGONAL_RIGHT_DOWN, DIAGONAL_LEFT_UP, DIAGONAL_LEFT_DOWN };

	/*******************************************************************
	 * Cube constructor initializes the location fields: row, column, depth.
	 * 
	 * @param r
	 * @param c
	 * @param d
	 ********************************************************************/
	public Cube(int r, int c, int d) {
		this.reset(r, c, d);
	}

	/****************************************************************
	 * The reset method resets the row, column, depth fields with the r, c, d
	 * parameter values for a new position, perhaps on some game board.
	 * 
	 * @param r
	 * @param c
	 * @param d
	 ****************************************************************/
	public void reset(int r, int c, int d) {
		this.row = r;
		this.column = c;
		this.depth = d;
	}

	/*******************************************************************************
	 * The navigate method changes this cube by adding to its row and column fields
	 * the values of the row, column, depth fields of the change square,
	 * respectively.
	 * 
	 * @param change
	 ********************************************************************************/
	public void navigate(Cube change) {
		this.row += change.row;
		this.column += change.column;
		this.depth += change.depth;
	}

	public String toString() {
		return "(" + this.row + "," + this.column + "," + this.depth + ")";
	}

	// public static void main( String[] args) {
	//
	// Cube cube = new Cube(0,0,0);
	//
	//
	// System.out.print( "ROW: ");
	// cube.reset(4, 4, 4);
	// for (int n = 0; n < 4; n++)
	// {
	// System.out.print( cube.toString());
	// cube.navigate(ROW);
	// }
	// System.out.println();
	//
	//
	// System.out.print( "COLUMN: ");
	// cube.reset(4, 4, 4);
	// for (int n = 0; n < 4; n++)
	// {
	// System.out.print( cube.toString());
	// cube.navigate(COLUMN);
	// }
	// System.out.println();
	//
	//
	// System.out.print( "DIAGONAL_RIGHT: ");
	// cube.reset(4, 4, 4);
	// for (int n = 0; n < 4; n++)
	// {
	// System.out.print( cube.toString());
	// cube.navigate(DIAGONAL_RIGHT);
	// }
	// System.out.println();
	//
	//
	// System.out.print( "DIAGONAL_LEFT: ");
	// cube.reset(4, 4, 4);
	// for (int n = 0; n < 4; n++)
	// {
	// System.out.print( cube.toString());
	// cube.navigate(DIAGONAL_LEFT);
	// }
	// System.out.println();
	// }
}
