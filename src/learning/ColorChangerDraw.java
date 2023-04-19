package learning;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorChangerDraw extends JFrame{
	
	private JPanel squarePanel;
    private JComboBox<String> colorComboBox;
    private JLabel squareLabel;

    private final String[] COLORS = {"Blue", "Red", "Green"};
    
	public ColorChangerDraw () {
		super("Color Changer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null); // Disable the layout manager

        squarePanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(getCurrentColor());
                g.fillRect(getWidth() / 2 - 50, getHeight() / 2 - 50, 100, 100);
            }
        };
        squarePanel.setBounds(400, 100, 100, 100); // Set position and size using setBounds()
        add(squarePanel);

        squareLabel = new JLabel("This is my asdasd square");
        squareLabel.setFont(new Font("Arial", Font.BOLD, 16));
        squareLabel.setBounds(100, 100, 150, 30); // Set position and size using setBounds()
        add(squareLabel);

        colorComboBox = new JComboBox<>(COLORS);
        colorComboBox.addActionListener(e -> squarePanel.repaint());
        colorComboBox.setBounds(100, 150, 200, 30); // Set position and size using setBounds()
        add(colorComboBox);

        setVisible(true);
    }

    private Color getCurrentColor() {
        String selectedColor = (String) colorComboBox.getSelectedItem();
        switch (selectedColor) {
            case "Red":
                return Color.RED;
            case "Blue":
                return Color.BLUE;
            case "Green":
                return Color.GREEN;
            default:
                return Color.BLACK;
        }
	}
}
