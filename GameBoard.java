import java.awt.color.*;
import java.awt.*;
import javax.swing.*;
public class GameBoard extends JPanel{

	private JFrame frame1;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	
	JPanel panel = new JPanel();
	JLabel board = new JLabel("");
	JButton[] drop = new JButton[7];
	JLabel[][] chipSlots = new JLabel[7][6];
	JPanel chips = new JPanel();
	
	public GameBoard() {
		frame1 = new JFrame();
		frame1.setBounds(100, 100, 800, 700);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		panel.setBackground(new Color(240, 240, 240));
		frame1.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		board.setBounds(85, 110, 640, 480);
		board.setIcon(new ImageIcon("C:\\Users\\Administrator\\eclipse-workspace\\CS\\Images\\connectboard.png"));
		panel.add(board);
		
		chips.setLayout(new GridLayout(1, 0, 0, 0));
		chips.setBounds(85, 110, 640, 480);
		panel.add(chips);
		
		
		int height = 100;
		int width = 90;
		int x = 90;
		int y = 11;
		for (int i = 0;i<7;i++)
		{
			drop[i] = new JButton();
			drop[i].setBounds(x,y,width,height);
			panel.add(drop[i]);
			drop[i].setOpaque(false);
			drop[i].setContentAreaFilled(false);
			/*drop[i].setBorderPainted(false);*/
			x+=90;
		}


		
		
		frame1.setResizable(false);
	}

		
	
	/**
	 * Initialize the contents of the frame.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					gameBoard window = new gameBoard ();
					window.frame1.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}
