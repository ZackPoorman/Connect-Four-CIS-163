package viewPkg;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.*;

public class ConnectFourGUI {

	// -----------------------------------------------------------------
	// Creates and displays the main program frame.
	// -----------------------------------------------------------------
	public static void main(String[] args) {

		JMenuBar menus;
		JMenu fileMenu;
		JMenuItem quitItem;
		JMenuItem gameItem;

		JFrame frame = new JFrame("Connect Four");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		fileMenu = new JMenu("File");
		quitItem = new JMenuItem("Quit");
		gameItem = new JMenuItem("New Game");

		fileMenu.add(gameItem);
		fileMenu.add(quitItem);
		menus = new JMenuBar();
		frame.setJMenuBar(menus);
		menus.add(fileMenu);

		String num = JOptionPane.showInputDialog("Please enter the size of the board (10)", 10);
		int size = (num.equals("")) ? 10 : Integer.parseInt(num);

		num = JOptionPane.showInputDialog("2 - 2D game,3 for 3D game", 2);
		int gameType = (num.equals("")) ? 2 : Integer.parseInt(num);

		if (gameType == 2) {
			TwoDimPanel panel2 = new TwoDimPanel(quitItem, gameItem, size);
			frame.getContentPane().add(panel2);
		} else {
			ThreeDimPanel panel3 = new ThreeDimPanel(quitItem, gameItem, size);
			frame.getContentPane().add(panel3);
		}

		// frame.pack();
		frame.setSize(800, 600);
		frame.setVisible(true);
	}
}
