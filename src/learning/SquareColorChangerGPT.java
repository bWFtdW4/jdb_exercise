package learning;

import java.awt.Color;
import javax.swing.JFrame;

public class SquareColorChangerGPT extends JFrame {

    private SquareDrawPanel squarePanel;
    private SquareLabelPanel labelPanel;

    public SquareColorChangerGPT() {
        super("Square Color Changer");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        squarePanel = new SquareDrawPanel(Color.RED);
        squarePanel.setBounds(100, 100, 100, 100);
        add(squarePanel);

        labelPanel = new SquareLabelPanel(squarePanel);
        labelPanel.setBounds(300, 300, 200, 30);
        add(labelPanel);
    }
}
