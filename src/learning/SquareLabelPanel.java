package learning;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SquareLabelPanel extends JPanel {

    private JComboBox<String> colorComboBox;
    private SquareDrawPanel squarePanel;

    private final String[] COLORS = {"Red", "Blue", "Green"};

    public SquareLabelPanel(SquareDrawPanel squarePanel) {
        this.squarePanel = squarePanel;

        JLabel squareLabel = new JLabel("This is my f***ing square");
        squareLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(squareLabel);
        

        colorComboBox = new JComboBox<>(COLORS);
        colorComboBox.addActionListener(e -> squarePanel.repaint());
        colorComboBox.setBounds(100, 150, 200, 30); // Set position and size using setBounds()
        add(colorComboBox);
    }

    public Color getCurrentColor() {
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
