package viewPkg;

import prj2.ThreeDimensions.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class ThreeDimPanel extends JPanel {

	private int boardSize = 10; // size of board
	private JButton[][] buttonBoard;

	private JMenuItem gameItem;
	private JMenuItem quitItem;

	private JButton exit;
	private JButton reset;

	private ConnectFourGame3D game3D;

	public ThreeDimPanel(JMenuItem pquitItem, JMenuItem pgameItem, int size) {

		boardSize = size;

		game3D = new ConnectFourGame3D(boardSize);

		setLayout(new BorderLayout());

		JPanel top = new JPanel();
		add(BorderLayout.NORTH, top);

		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(size, size, 1, 1));
		add(BorderLayout.CENTER, bottom);

		ActionListener itemButtonListener = new ItemButtonListener();
		gameItem = pgameItem;
		gameItem.addActionListener(itemButtonListener);

		quitItem = pquitItem;
		quitItem.addActionListener(itemButtonListener);

		reset = new JButton("Reset");
		reset.addActionListener(itemButtonListener);
		top.add(reset);

		exit = new JButton("Exit");
		exit.addActionListener(itemButtonListener);
		top.add(exit);

		Font font = new Font("Courier New", Font.BOLD, 16);

		buttonBoard = new JButton[boardSize][boardSize];

		// create the array of buttons
		for (int row = 0; row < this.boardSize; row++) {
			for (int col = 0; col < this.boardSize; col++) {
				this.buttonBoard[row][col] = new JButton("-");
				this.buttonBoard[row][col].addActionListener(itemButtonListener);
				this.buttonBoard[row][col].setFont(font);

				bottom.add(this.buttonBoard[row][col]);
			}
		}
	}

	/**
	 * update the button captions of the 2-d buttonBoard array
	 */
	private void display3D() {
		for (int row = 0; row < this.boardSize; row++) {
			for (int col = 0; col < this.boardSize; col++) {
				this.buttonBoard[row][col].setText(this.game3D.getPiece(row, col));
			}
		}
	}

	private class ItemButtonListener implements ActionListener {
		// --------------------------------------------------------------
		// Handles the Menu items and special buttons
		// --------------------------------------------------------------
		public void actionPerformed(ActionEvent event) {
			JComponent comp = (JComponent) event.getSource();
			if ((comp == ThreeDimPanel.this.gameItem) || (comp == ThreeDimPanel.this.reset)
					|| (ThreeDimPanel.this.game3D.isWinner())) {
				ThreeDimPanel.this.game3D.reset();
				ThreeDimPanel.this.display3D();
			}
			if ((comp == ThreeDimPanel.this.quitItem) || (comp == ThreeDimPanel.this.exit)) {
				System.exit(1);
			}
			for (int row = 0; row < ThreeDimPanel.this.boardSize; row++) {
		        for (int col = 0; col < ThreeDimPanel.this.boardSize; col++) {
		          if ((ThreeDimPanel.this.buttonBoard[row][col] == comp) && 
		            (ThreeDimPanel.this.game3D.selectCol(row, col) == -1))
		          {
		            JOptionPane.showMessageDialog(null, "Either a row or column is full! Choose again.");
		            return;
		          }
		        }
		      }
		      ThreeDimPanel.this.display3D();
		      if (ThreeDimPanel.this.game3D.isWinner()) {
		        JOptionPane.showMessageDialog(null, "Wins!", "Player lost", 1);
		      }
		      ThreeDimPanel.this.game3D.switchplayer();
		      if (!ThreeDimPanel.this.game3D.moveRemaining()) {
		        JOptionPane.showMessageDialog(null, "Out of Moves");
		}

	}
}
}

