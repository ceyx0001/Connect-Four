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
	/**
	 * serialization
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Connect4GUI game = new Connect4GUI();
		game.frame.setVisible(true);
	}

	/**
	 * Components used to create the board and interface
	 */
	JFrame frame;
	private boolean active = true; // determines whether the board should be operating
	/**
	 * colors used to create the chips and board
	 */
	private Color white = new Color(255, 255, 255);
	private Color red = new Color(255, 0, 0);
	private Color yellow = new Color(255, 255, 0);
	private Color blue = new Color(87, 160, 211);

	/**
	 * Board Mechanics determines who wins and adds chips to board DrawBoard creates
	 * the connect 4 baord
	 */
	BoardMechanics gameBoard;
	DrawBoard goodBoard;

	/**
	 * components needed to display images, texts, and fonts
	 */
	JPanel selectionPanel = new JPanel();
	JPanel endPanel = new JPanel();
	JButton finishGame = new JButton("Exit to main menu");
	JButton single = new JButton("Single Player");
	JButton multi = new JButton("Multiplayer");
	JLabel title = new JLabel("Connect 4");
	JLabel instructions = new JLabel("Please Select a Game Mode");
	JLabel ai = new JLabel("Coming Soon...");
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

	int turn = 0; // alternates between 1 and 0
	boolean win;
	boolean draw;
	boolean AI = false;

	/**
	 * create the title page
	 */
	public void titlePage() {
		// as long as active, the board will accept input
		active = true;
		selectionPanel.revalidate();
		selectionPanel.repaint();
		selectionPanel.add(space1);
		selectionPanel.add(title); // add the main title
		title.setFont(new Font("Tahoma", Font.BOLD, 40));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectionPanel.add(space2);
		selectionPanel.add(instructions); // add the instructions
		instructions.setFont(new Font("Verdana", Font.BOLD, 25));
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectionPanel.add(space3);
		selectionPanel.add(single);
		/**
	 	*	setFont,setAlignment to make interface more user friendly
	 	*/
		single.setFont(new Font("Verdana", Font.BOLD, 25));
		single.setAlignmentX(Component.CENTER_ALIGNMENT);

		// single player button
		single.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AI = true;
				comingSoon();
			}
		});
		selectionPanel.add(space4);
		multi.setMinimumSize(new Dimension(93, 23));
		multi.setMaximumSize(new Dimension(219, 40));
		multi.setPreferredSize(new Dimension(93, 23));
		selectionPanel.add(multi);
		multi.setAlignmentX(Component.CENTER_ALIGNMENT);
		// multiplayer button
		multi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(); // user clicked on multiplayer
			}
		});
		multi.setFont(new Font("Verdana", Font.BOLD, 25));
	}

	public void comingSoon()
		{
			selectionPanel.removeAll();
			selectionPanel.add(space1);
			selectionPanel.add(ai);
			ai.setFont(new Font("Tahoma", Font.BOLD, 40));
			ai.setAlignmentX(Component.CENTER_ALIGNMENT);

			back.setFont(new Font("Stencil", Font.BOLD, 30));
			back.setAlignmentX(Component.CENTER_ALIGNMENT);
			selectionPanel.add(space5);
			selectionPanel.add(back); // add a back button to exit to title page
			back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectionPanel.removeAll();
				titlePage();
			}
			});
			selectionPanel.revalidate();
			selectionPanel.repaint();
		}

	/**
	 *	after clicking a button on title page

	 	player selects which color they want
	 */
	public void click() {
		selectionPanel.removeAll(); // remove all components from previous page
		selectionPanel.add(space1);
		selectionPanel.add(chooseTurn); // add an instruction
		chooseTurn.setFont(new Font("Verdana", Font.BOLD, 25));
		chooseTurn.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectionPanel.add(space2);
		selectionPanel.add(player1); // add a "red" button
		player1.setFont(new Font("Verdana", Font.BOLD, 25));
		player1.setAlignmentX(Component.CENTER_ALIGNMENT);
		player1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turn = 1;
				createBoard(); // when red is clicked, turn = 1
			}
		});
		selectionPanel.add(space3);
		selectionPanel.add(player2); // add a "yellow" button
		player2.setFont(new Font("Verdana", Font.BOLD, 25));
		player2.setAlignmentX(Component.CENTER_ALIGNMENT);
		player2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turn = 2;
				createBoard(); // when yellow is clicked, turn = 2
			}
		});
		back.setFont(new Font("Stencil", Font.BOLD, 30));
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectionPanel.add(space5);
		selectionPanel.add(back); // add a back button to exit to title page
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectionPanel.removeAll();
				titlePage();
			}
		});
		selectionPanel.revalidate();
		selectionPanel.repaint();
	}

	/**
	 * draws the connect 4 board
	 */
	public void createBoard() {
		goodBoard = new DrawBoard(d); // refer to DrawBoard class
		frame.add(goodBoard);
		frame.remove(selectionPanel);
		frame.repaint();
		frame.setVisible(true);
	}

	/**
	 * Initializes the frame and container for game.
	 * main method only runs this method
	 */
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
	/**
	 *	Create a Panel to put board in
	 */
	public class DrawBoard extends JPanel implements MouseListener {
		/**
		 * serialization
		 */
		private static final long serialVersionUID = 1L;

		// variables to initialize board location
		int startX = 0;
		int startY = 0;
		int cellWidth = 60; // the size of the chips
		final int rows = 6;
		final int cols = 7;

		// 2D array for storing chips
		Color[][] grid = new Color[rows][cols];

		// implement a quit button to exit to title page
		// can be clicked anytime 
		public DrawBoard(Dimension dimension) {
			JButton quit = new JButton("Quit");
			quit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose(); // delete the current frame
					Connect4GUI game = new Connect4GUI(); // recall a new connect 4 game method
					game.frame.setVisible(true);
				}
			});
			add(quit); // add button to panel
			setSize(dimension);
			setPreferredSize(dimension);
			addMouseListener(this);
			// change each slot on the board to white
			for (int row = 0; row < grid.length; row++) {
				for (int col = 0; col < grid[0].length; col++) {
					grid[row][col] = white;
				}
			}
		}
		/**
	 	*	fill the board with colors
	 	*/
		@Override
		public void paintComponent(Graphics g) {
			Graphics2D newGB = (Graphics2D) g; // declare the board
			Dimension d = getSize(); // initialize its size
			newGB.setColor(blue);
			newGB.fillRect(0, 0, d.width, d.height);
			// location of board
			startX = 140;
			startY = 100;

			// 2) draw grid here
			// iterate through each row, then add a 6 white circles with size cellWidth
			for (int row = 0; row < grid.length; row++) {
				for (int col = 0; col < grid[0].length; col++) {
					newGB.setColor(grid[row][col]);
					newGB.fillOval(startX, startY, cellWidth, cellWidth);
					startX += cellWidth;
				}
				startX = 140;
				startY += cellWidth;
			}
			/**
	 		*	Display the state of the game
	 		*/
			newGB.setFont(verdana);
			newGB.setColor(white);
			if (win) { // will display who wins if true
				if (turn == 1) {
					newGB.drawString("Red wins!", 10, 20);
				} else {
					newGB.drawString("Yellow wins!", 10, 20);
				}
				repaint();
			} else if (draw) { // display draw if no more moves are avalaible
				newGB.drawString("Draw!", 10, 20);
			} else if (turn == 1) {
				newGB.drawString("Red's Turn", 10, 20);
			} else if (turn == 2) {
				newGB.drawString("Yellow's Turn", 10, 20);
			}

		}
		/**
	 	*	Insert coins when mouse is pressed at certain locations	
		*/
		public void mousePressed(MouseEvent e) {
			if (!active) { // if active is false, the game is paused 
				return;
			}
			int x = e.getX(); // get the x value of the mouse click
			int col = (x - 140) / cellWidth; // use the x value to calculate the column

			// when clicked on somewhere outside of board, catch the exception
			try {
				int row = getOpenSlot(col); // call method that will get the available row of the column

				win = gameBoard.checkWin(col, row, turn); // if the next chip makes 4 in a row, set win to true
				draw = gameBoard.checkDraw(); // store true if no more moves are available
				if (win || draw) {
					active = false; // pause the game if either draw or win are true
				}
				// if there are no more spaces available in column, display the restriction
				if (row < 0) {
					System.out.println("Full Column");
				} else {
					if (turn == 1) {
						grid[row][col] = red; // add red if turn = 1
						if (!win) {
							turn = 2; // change turn
						}
					} else {
						grid[row][col] = yellow; // add yellow if turn = 2
						if (!win) {
							turn = 1; // change turn
						}
					}
				}
			} catch (IndexOutOfBoundsException exception) {
			}

			repaint();

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
			int row = rows - 1; // -1 to prevent array out of bounds

			// checks if the slot has a chip
			while (!(grid[row][col].equals(white)) || row < 0) {
				row--; 
				if (row < 0) {
					return row; // get row if a slot is open
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
