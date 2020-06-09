import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Connect4GUI extends JFrame {
	JFrame frame;
	private boolean active = true;
	private Color white = new Color(255, 255, 255);
	private Color red = new Color(255, 0, 0);
	private Color yellow = new Color(255, 255, 0);
	private Color blue = new Color(87, 160, 211);
	BoardMechanics gameBoard;
	DrawBoard goodBoard;

	public static void main(String[] args) {
		Connect4GUI game = new Connect4GUI();
		game.frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	int turn = 0;
	JPanel selectionPanel = new JPanel();
	JPanel endPanel = new JPanel();
	JButton finishGame = new JButton("Exit to main menu");
	JButton single = new JButton("Single Player");
	JButton multi = new JButton("Multiplayer");
	JLabel title = new JLabel("Connect 4");
	JLabel instructions = new JLabel("Please Select a Game Mode");
	Component space1 = Box.createVerticalStrut(150);
	Component space2 = Box.createVerticalStrut(60);
	Component space3 = Box.createVerticalStrut(75);
	Component space4 = Box.createVerticalStrut(50);
	Component space5 = Box.createVerticalStrut(100);
	JLabel chooseTurn = new JLabel("Please choose the player who will go first");
	JButton player1 = new JButton("  Red  ");
	JButton player2 = new JButton("Yellow");
	JButton back = new JButton("Back");
	JPanel coinPanel = new JPanel();
	JPanel gamePanel = new JPanel();
	JLabel boardImage = new JLabel("");
	JButton[] drop = new JButton[7];
	Dimension d = new Dimension(700, 600);
	Font verdana = new Font("Verdana", 1, 17);

	public void titlePage() {
		active = true;
		selectionPanel.revalidate();
		selectionPanel.repaint();
		selectionPanel.add(space1);
		selectionPanel.add(title);
		title.setFont(new Font("Tahoma", Font.BOLD, 40));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectionPanel.add(space2);
		selectionPanel.add(instructions);
		instructions.setFont(new Font("Verdana", Font.BOLD, 25));
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectionPanel.add(space3);
		selectionPanel.add(single);
		single.setFont(new Font("Verdana", Font.BOLD, 25));
		single.setAlignmentX(Component.CENTER_ALIGNMENT);
		single.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click();
			}
		});
		selectionPanel.add(space4);
		multi.setMinimumSize(new Dimension(93, 23));
		multi.setMaximumSize(new Dimension(219, 40));
		multi.setPreferredSize(new Dimension(93, 23));
		selectionPanel.add(multi);
		multi.setAlignmentX(Component.CENTER_ALIGNMENT);
		multi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click();
			}
		});
		multi.setFont(new Font("Verdana", Font.BOLD, 25));
	}

	public void click() {
		selectionPanel.removeAll();
		selectionPanel.add(space1);
		selectionPanel.add(chooseTurn);
		chooseTurn.setFont(new Font("Verdana", Font.BOLD, 25));
		chooseTurn.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectionPanel.add(space2);
		selectionPanel.add(player1);
		player1.setFont(new Font("Verdana", Font.BOLD, 25));
		player1.setAlignmentX(Component.CENTER_ALIGNMENT);
		player1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turn = 1;
				createBoard();
			}
		});
		selectionPanel.add(space3);
		selectionPanel.add(player2);
		player2.setFont(new Font("Verdana", Font.BOLD, 25));
		player2.setAlignmentX(Component.CENTER_ALIGNMENT);
		player2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turn = 2;
				createBoard();
			}
		});
		back.setFont(new Font("Stencil", Font.BOLD, 30));
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectionPanel.add(space5);
		selectionPanel.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectionPanel.removeAll();
				titlePage();
			}
		});
		selectionPanel.revalidate();
		selectionPanel.repaint();
	}

	public void createBoard() {
		goodBoard = new DrawBoard(d);
		frame.add(goodBoard);
		frame.remove(selectionPanel);
		frame.repaint();
		frame.setVisible(true);
	}

	public Connect4GUI() {
		frame = new JFrame();
		gameBoard = new BoardMechanics();
		frame.setSize(700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setResizable(false);
		frame.getContentPane().add(selectionPanel, BorderLayout.CENTER);
		selectionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
		titlePage();
	}

	public class DrawBoard extends JPanel implements MouseListener {
		int startX = 0;
		int startY = 0;
		int cellWidth = 60;
		final int rows = 6;
		final int cols = 7;

		Color[][] grid = new Color[rows][cols];

		public DrawBoard(Dimension dimension) {
			JButton quit = new JButton("Quit");
			quit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					Connect4GUI game = new Connect4GUI();
					game.frame.setVisible(true);
				}
			});
			add(quit);
			setSize(dimension);
			setPreferredSize(dimension);
			addMouseListener(this);
			// 1. initialize array here
			int x = 0;
			for (int row = 0; row < grid.length; row++) {
				for (int col = 0; col < grid[0].length; col++) {
					grid[row][col] = white;
				}
			}
		}

		@Override
		public void paintComponent(Graphics g) {
			Graphics2D newGB = (Graphics2D) g;
			Dimension d = getSize();
			newGB.setColor(blue);
			newGB.fillRect(0, 0, d.width, d.height);
			// location of board
			startX = 140;
			startY = 100;

			// 2) draw grid here
			for (int row = 0; row < grid.length; row++) {
				for (int col = 0; col < grid[0].length; col++) {
					newGB.setColor(grid[row][col]);
					newGB.fillOval(startX, startY, cellWidth, cellWidth);
					startX += cellWidth;
				}
				startX = 140;
				startY += cellWidth;
			}

			newGB.setFont(verdana);
			newGB.setColor(white);
			if (turn == 1) {
				newGB.drawString("Red's Turn", 10, 20);
			} else if (turn == 2) {
				newGB.drawString("Yellow's Turn", 10, 20);
			}
		}

		public void mousePressed(MouseEvent e) {
			if (!active) {
				return;
			}
			int x = e.getX();
			int y = e.getY();
			int col = (x - 140) / cellWidth;
			int row = getOpenSlot(col);

			try {
				if (gameBoard.checkWin(col, row, turn)) {
					System.out.println("Player " + turn + " wins!");
					active = false;
				}

				if (row < 0) {
					System.out.println("Full Column");
				} else {
					if (turn == 1) {
						grid[row][col] = red;
						turn = 2;
					} else {
						grid[row][col] = yellow;
						turn = 1;
					}
					// System.out.println(row + " " + col);
				}
				repaint();
			} catch (IndexOutOfBoundsException exception) {
				System.out.println("Null Click");
			}
		}

		public void mouseReleased(MouseEvent e) {

		}

		public void mouseEntered(MouseEvent e) {

		}

		public void mouseExited(MouseEvent e) {

		}

		public void mouseClicked(MouseEvent e) {

		}

		public int getOpenSlot(int col) {
			int row = rows - 1;
			while (!(grid[row][col].equals(white)) || row < 0) {
				row--;
				if (row < 0) {
					return row;
				}
			}
			if (turn == 1) {

				Chip redChip = new Chip(1, row, col);
				gameBoard.insertCoin(redChip, row, col, turn);
			} else {
				Chip yellowChip = new Chip(2, row, col);
				gameBoard.insertCoin(yellowChip, row, col, turn);
			}
			return row;
		}
	}
}
