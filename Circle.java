import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.color.*;
import java.awt.*;
import javax.swing.*;

public class Circle extends JPanel{

	public void paint(Graphics g)
	{
		setSize(600,700);
		g.drawOval(50,50,50,50);
	}
	public static void main(String[] args) {
		JFrame mainFrame = new JFrame();
		mainFrame.setSize(800,700);
		Circle circlePanel = new Circle();
		
		mainFrame.add(circlePanel);
		mainFrame.setVisible(true);
	}
}
