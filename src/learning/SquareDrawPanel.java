package learning;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SquareDrawPanel extends JPanel {

    private Color currentColor;

    public SquareDrawPanel(Color initialColor) {
        this.currentColor = initialColor;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(currentColor);
        g.fillRect(getWidth() / 2 - 50, getHeight() / 2 - 50, 100, 100);
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }
}
