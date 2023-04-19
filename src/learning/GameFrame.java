package learning;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameFrame extends JFrame{

	
	private JLabel testLable;
    

    private final String[] COLORS = {"Red", "Blue", "Green"};
    
    private JLabel hairBoxColorLabel;
    private JComboBox<String> hairBoxColor;
    private JLabel headBoxColorLabel;
    private JComboBox<String> headBoxColor;
    private JLabel torosBoxColorLabel;
    private JComboBox<String> torosBoxColor;
    private JLabel legsBoxColorLabel;
    private JComboBox<String> LegsBoxColor;
    
	
	
	
	
	public GameFrame () {
		
		super("This is the Name of the Game");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		
		GamePanel bodyPanel = new GamePanel();
		
		bodyPanel.setBounds(400, 100, getWidth(), getHeight());
		add(bodyPanel);
		
		hairBoxColorLabel = new JLabel("Hair color");
		hairBoxColorLabel.setFont(new Font("Arial", Font.BOLD, 16));
		hairBoxColorLabel.setBounds(100, 100, 150, 30);
		add(hairBoxColorLabel);
		
		headBoxColorLabel = new JLabel("Skin color");
		
		torosBoxColorLabel = new JLabel("Shirt color");
		
		legsBoxColorLabel = new JLabel("Pants color");
		
		
		
		
		
		/**
		//JFrame frameOfGame = new JFrame();
		frameOfGame.setTitle("Game Name");
		// get the content area of Panel.    
		Container frameContent = frameOfGame.getContentPane();
		// set the LayoutManager
		frameContent.setLayout(new BorderLayout());
		GamePanel panelOfGame = new GamePanel();
		// add Panel object into container    
		frameContent.add(panelOfGame);
		panelOfGame.setBounds(200, 200, 200, 200);
		frameOfGame.setSize(800, 600);
		frameOfGame.setVisible(true);
		frameOfGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		
		JPanel framePanel = new JPanel();
		frameOfGame.add(testLable);
		testLable.setBounds(0, 0, 100, 50);
	
		**/
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
}