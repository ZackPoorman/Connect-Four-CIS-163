package viewPkg;

import prj2.TwoDimensions.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TwoDimPanel extends JPanel {
	private int boardSize = 10; // size of connect four game board
	private JLabel[][] board;
	private JButton[] selection;

	private JMenuItem gameItem;
	private JMenuItem quitItem;

	private JButton exit;
	private JButton reset;
	private JButton autoPlay;

	private ConnectFourGame game;

	private boolean computerPlays;

	public TwoDimPanel(JMenuItem pquitItem, JMenuItem pgameItem, int size) {

		boardSize = size;

		// Panel layout
		setLayout(new BorderLayout());

		JPanel top = new JPanel();
		add(BorderLayout.NORTH, top);

		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(boardSize + 1, boardSize, 1, 1)); // buttons stored in the last row
		add(BorderLayout.CENTER, bottom);

		ActionListener itemButtonListener = new ItemButtonListener();
		ButtonListener buttonListener = new ButtonListener();

		Font font = new Font("Courier New", Font.BOLD, 30);

		// Menu items
		gameItem = pgameItem;
		gameItem.addActionListener(itemButtonListener);

		quitItem = pquitItem;
		quitItem.addActionListener(itemButtonListener);

		// Buttons
		reset = new JButton("Reset");
		reset.addActionListener(itemButtonListener);
		reset.setFont(font);
		reset.setForeground(Color.red);
		top.add(reset);

		exit = new JButton("Exit");
		exit.addActionListener(itemButtonListener);
		exit.setFont(font);
		exit.setForeground(Color.red);
		top.add(exit);

		autoPlay = new JButton("Manual");
		autoPlay.addActionListener(itemButtonListener);
		autoPlay.setFont(font);
		autoPlay.setForeground(Color.red);
		top.add(autoPlay);
		computerPlays = true;

		/**
		 * Game Related Fields
		 */
		game = new ConnectFourGame(boardSize);

		// game board display of labels
		board = new JLabel[boardSize][boardSize];

		// create the board array of labels
		for (int row = 0; row < this.boardSize; row++) {
			for (int col = 0; col < this.boardSize; col++) {
				JLabel label = new JLabel("_");
				label.setForeground(Color.black);
				label.setFont(font);

				this.board[row][col] = label;
				bottom.add(this.board[row][col]);
			}
		}

		selection = new JButton[boardSize];
		// create the selection array of buttons
		for (int col = 0; col < this.boardSize; col++) {
			this.selection[col] = new JButton("Select");
			this.selection[col].addActionListener(buttonListener);
			this.selection[col].setFont(font);
			this.selection[col].setForeground(Color.BLUE);
			bottom.add(this.selection[col]);
		}
		setVisible(true);
	}

	/**
	 * Update the labels in the 2-D board array
	 */
	private void displayBoard() {
		for (int row = 0; row < this.boardSize; row++) {
			for (int col = 0; col < this.boardSize; col++) {
				this.board[row][col].setText(this.game.getValue(row, col));
				this.board[row][col].setForeground(this.game.getColor(row, col));
			}
		}
		setVisible(true);
	}

	/**
	 * 
	 * ActionListener for the buttons along the top
	 *
	 */
	private class ItemButtonListener implements ActionListener {

		// --------------------------------------------------------------
		// Handles menu items and auxiliary buttons
		// --------------------------------------------------------------
		public void actionPerformed(ActionEvent event) {
			JComponent source = (JComponent) event.getSource();
			if ((source == TwoDimPanel.this.gameItem) || (source == TwoDimPanel.this.reset)
					|| (TwoDimPanel.this.game.isWinner())) {
				TwoDimPanel.this.game.reset();
				TwoDimPanel.this.displayBoard();
			}
			if ((source == TwoDimPanel.this.quitItem) || (source == TwoDimPanel.this.exit)) {
				System.exit(1);
			}
			if (source == autoPlay) {
				computerPlays = !computerPlays;
				if (computerPlays) {
					autoPlay.setText("Manual");
				} else {
					autoPlay.setText("Automatic");
				}
			}
		}
	}

	// *****************************************************************
	// Represents a listener for button push (action) events.
	// *****************************************************************
	private class ButtonListener implements ActionListener {
		// --------------------------------------------------------------
		// Updates the game board of labels whenever a column is selected.
		// --------------------------------------------------------------

		public void actionPerformed(ActionEvent event) {
			JComponent source = (JComponent) event.getSource();
			// identify which button is the event source
			int col = 0;
			while ((col < TwoDimPanel.this.boardSize) && (TwoDimPanel.this.selection[col] != source)) {
				col++;
			}
			// handle the event for the player
			if (TwoDimPanel.this.selection[col] == source) {
				boolean validMove = move(col);
				if (validMove) {
					TwoDimPanel.this.displayBoard();
					if (TwoDimPanel.this.game.isWinner()) {
						JOptionPane.showMessageDialog(null, TwoDimPanel.this.game.getWinner() + " wins", "Winner!", 1);
						return;
					}
					TwoDimPanel.this.game.switchPlayer();
				} else {
					return;
				}
			}
			if ((TwoDimPanel.this.computerPlays) && (TwoDimPanel.this.game.moveRemaining())
					&& (TwoDimPanel.this.game.playerIsComputer())) {
				TwoDimPanel.this.game.moveAI();
				TwoDimPanel.this.displayBoard();
				if (TwoDimPanel.this.game.isWinner(1)) {
					JOptionPane.showMessageDialog(null, "Computer wins", "Winner!", 1);
					return;
				}
				TwoDimPanel.this.game.switchPlayer();	
			}
			if (!game.moveRemaining()) {
				JOptionPane.showMessageDialog(null, "Out of Moves", "Game over!", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		/**
		 * Checks to see if the selected column is full
		 * 
		 * @param col
		 * @return
		 */
		public boolean move(int col) {
			int row = game.selectCol(col);
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Column full!  Choose a different one");
				return false;
			} else {
				return true;
			}
		}

	}
}
