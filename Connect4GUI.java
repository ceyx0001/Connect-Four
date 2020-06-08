import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.*;

public class Connect4GUI extends JFrame implements ActionListener {
	JFrame frame;
	HashMap<Integer, LinkedList<Chip>> board = new HashMap<Integer, LinkedList<Chip>>();
	BoardMechanics gameBoard;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connect4GUI window = new Connect4GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	String turn = "";
	JPanel selectionPanel = new JPanel();
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
	//
	JPanel coinPanel = new JPanel();
	JPanel gamePanel = new JPanel();
	JLabel boardImage = new JLabel("");
	JButton[] drop = new JButton[7];
	JLabel displayColor = new JLabel("");
	JLabel displayTurn = new JLabel("Turn:");

	public void titlePage() {
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
				turn = "Red";
				displayColor.setText(turn);
				createBoard();
			}
		});

		selectionPanel.add(space3);

		selectionPanel.add(player2);
		player2.setFont(new Font("Verdana", Font.BOLD, 25));
		player2.setAlignmentX(Component.CENTER_ALIGNMENT);
		player2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turn = "Yellow";
				displayColor.setText(turn);
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
		frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
		gamePanel.setBackground(new Color(240, 240, 240));
		gamePanel.setLayout(null);

		selectionPanel.removeAll();
		selectionPanel.revalidate();

		boardImage.setBounds(85, 110, 640, 480);
		boardImage.setIcon(new ImageIcon("Images\\connectboard.png"));
		gamePanel.add(boardImage);

		displayTurn.setBackground(new Color(240, 240, 240));
		displayTurn.setFont(new Font("Roboto", Font.PLAIN, 28));
		displayTurn.setBounds(120, 600, 70, 60);
		gamePanel.add(displayTurn);

		displayColor.setFont(new Font("Roboto", Font.PLAIN, 28));
		displayColor.setBounds(200, 600, 250, 60);
		gamePanel.add(displayColor);

		int height = 100;
		int width = 90;
		int x = 90;
		int y = 11;
		for (int i = 0; i < 7; i++) {
			drop[i] = new JButton(Integer.toString(i + 1));
			drop[i].setBounds(x, y, width, height);
			drop[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Chip newChip = new Chip(turn);
					turn = gameBoard.insertCoin(newChip, Integer.parseInt(e.getActionCommand()), turn);
					displayColor.setText(turn);
				}
			});
			gamePanel.add(drop[i]);
			/*
			 * drop[i].setOpaque(false); drop[i].setContentAreaFilled(false);
			 * drop[i].setBorderPainted(false);
			 */
			x += 90;
		}
	}

	public Connect4GUI() {
		frame = new JFrame();
		gameBoard = new BoardMechanics();
		frame.setBounds(100, 100, 800, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setResizable(false);

		frame.getContentPane().add(selectionPanel, BorderLayout.CENTER);
		selectionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));

		titlePage();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
