package learning;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


// MyPanel extends JPanel, which will eventually be placed in a JFrame
public class GamePanel extends JPanel {
	// custom painting is performed by the paintComponent method
	JFrame frameOfGame;
	//GamePanel panelOfGame;
	
	@Override
	public void paintComponent (Graphics g) {
		// clear the previous painting
		super.paintComponent(g);

		
		Graphics2D hairBox = (Graphics2D) g;
		hairBox.setColor(Color.green);
		hairBox.fillRect(200, 50, 80, 80);
		
		Graphics2D headBox = (Graphics2D) g;
		headBox.setColor(Color.red);
		headBox.fillRect(210, 70, 60, 60);

		Graphics2D torosBox = (Graphics2D) g;
		torosBox.setColor(Color.blue);
		torosBox.fillRect(190, 130, 100, 120);
		
		Graphics2D rLegsBox = (Graphics2D) g;
		rLegsBox.setColor(Color.yellow);
		rLegsBox.fillRect(190, 250, 45, 120);
		
		Graphics2D lLegsBox = (Graphics2D) g;
		lLegsBox.setColor(Color.yellow);
		lLegsBox.fillRect(245, 250, 45, 120);
		
		Graphics2D rArmBox = (Graphics2D) g;
		rArmBox.setColor(Color.black);
		rArmBox.fillRect(170, 140, 20, 120);
		
		Graphics2D lArmBox = (Graphics2D) g;
		lArmBox.setColor(Color.black);
		lArmBox.fillRect(290, 140, 20, 120);
	
	

	}
	
	
}

