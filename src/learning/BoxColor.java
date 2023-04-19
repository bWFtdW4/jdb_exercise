package learning;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BoxColor extends JFrame {

	//hairBox
	private JPanel hairBox;
	private JComboBox<String> hairColorComboBox;
	private JLabel hairLabel;
	//faceBox
	private JPanel faceBox;
	private JComboBox<String> FaceColorComboBox;
	private JLabel faceLabel;
	//torosBox
	private JPanel torosBox;
	private JComboBox<String> torosColorComboBox;
	private JLabel torosLabel;
	//rArmBox
	private JPanel rArmBox;
	private JComboBox<String> rArmColorComboBox;
	private JLabel rArmLabel;

	//right legBox
	private JPanel rLegBox;


	//left legBox
	private JPanel lLegBox;


	private final String[] FACECOLOR = { "Magenta", "Red", "Orange" };
	private final String[] HAIRCOLOR = { "Green", "Blue", "Black" };
	private final String[] TORSOCOLOR = { "Yellow", "Pink", "Gray" };
	private final String[] ARMCOLOR = { "Black", "Pink", "Gray" };


	public BoxColor () {
		super("BoxColor");
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null); // Disable the layout manager

		//face
		faceBox = new JPanel() {
			@Override
			public void paintComponent (Graphics g) {
				super.paintComponent(g);
				g.setColor(getFaceColor());
				g.fillRect(getWidth() / 2 - 50, getHeight() / 2 - 50, 100, 100);
			}
		};
		faceBox.setBounds(410, 120, 80, 80); // Set position and size using setBounds()
		add(faceBox);

		faceLabel = new JLabel("Skin color:");
		faceLabel.setFont(new Font("Arial", Font.BOLD, 16));
		faceLabel.setBounds(30, 150, 150, 30); // Set position and size using setBounds()
		add(faceLabel);

		FaceColorComboBox = new JComboBox<>(FACECOLOR);
		FaceColorComboBox.addActionListener(e -> faceBox.repaint());
		FaceColorComboBox.setBounds(150, 150, 150, 30); // Set position and size using setBounds()
		add(FaceColorComboBox);

		//hair
		hairBox = new JPanel() {
			@Override
			public void paintComponent (Graphics g) {
				super.paintComponent(g);
				g.setColor(getHairColor());
				g.fillRect(getWidth() / 2 - 50, getHeight() / 2 - 50, 100, 100);
			}
		};
		hairBox.setBounds(400, 100, 100, 100); // Set position and size using setBounds()
		add(hairBox);

		hairLabel = new JLabel("Hair color:");
		hairLabel.setFont(new Font("Arial", Font.BOLD, 16));
		hairLabel.setBounds(30, 100, 150, 30); // Set position and size using setBounds()
		add(hairLabel);

		hairColorComboBox = new JComboBox<>(HAIRCOLOR);
		hairColorComboBox.addActionListener(e -> hairBox.repaint());
		hairColorComboBox.setBounds(150, 100, 150, 30); // Set position and size using setBounds()
		add(hairColorComboBox);

		//torosBox
		torosBox = new JPanel() {
			@Override
			public void paintComponent (Graphics g) {
				super.paintComponent(g);
				g.setColor(getTorsoColor());
				g.fillRect(getWidth() / 2 - 50, getHeight() / 2 - 50, 140, 150);
			}
		};
		torosBox.setBounds(370, 175, 140, 150); // Set position and size using setBounds()
		add(torosBox);

		torosLabel = new JLabel("Shirt color:");
		torosLabel.setFont(new Font("Arial", Font.BOLD, 16));
		torosLabel.setBounds(30, 200, 150, 30); // Set position and size using setBounds()
		add(torosLabel);

		torosColorComboBox = new JComboBox<>(TORSOCOLOR);
		torosColorComboBox.addActionListener(e -> torosBox.repaint());
		torosColorComboBox.setBounds(150, 200, 150, 30); // Set position and size using setBounds()
		add(torosColorComboBox);

		//right leg
		rLegBox = new JPanel() {
			@Override
			public void paintComponent (Graphics g) {
				super.paintComponent(g);
				//g.setColor(getlegColor());
				g.setColor(Color.BLACK);
				g.fillRect(190, 250, 45, 120);
			}
		};
		rLegBox.setBounds(400, 300, 20, 150); // Set position and size using setBounds()
		add(rLegBox);

		//left leg
		lLegBox = new JPanel() {
			@Override
			public void paintComponent (Graphics g) {
				super.paintComponent(g);
				//g.setColor(getlegColor());
				g.setColor(Color.RED);
				g.fillRect(245, 250, 45, 120);
			}
		};
		lLegBox.setBounds(245, 250, 45, 120); // Set position and size using setBounds()
		add(lLegBox);

		setVisible(true);
	}


	private Color getHairColor () {
		String selectedColor = (String) hairColorComboBox.getSelectedItem();
		switch (selectedColor) {
			case "Green":
				return Color.GREEN;
			case "Blue":
				return Color.BLUE;
			case "Black":
				return Color.BLACK;
			default:
				return Color.RED;
		}
	}


	private Color getFaceColor () {
		String selectedColor = (String) FaceColorComboBox.getSelectedItem();
		switch (selectedColor) {
			case "Magenta":
				return Color.MAGENTA;
			case "Red":
				return Color.RED;
			case "Orange":
				return Color.ORANGE;
			default:
				return Color.GREEN;
		}
	}


	private Color getTorsoColor () {
		String selectedColor = (String) torosColorComboBox.getSelectedItem();
		switch (selectedColor) {
			case "Yellow":
				return Color.YELLOW;
			case "Pink":
				return Color.PINK;
			case "Gray":
				return Color.GRAY;
			default:
				return Color.RED;
		}
	}


	private Color getArmColor () {
		String selectedColor = (String) torosColorComboBox.getSelectedItem();
		switch (selectedColor) {
			case "Black":
				return Color.BLACK;
			case "Pink":
				return Color.PINK;
			case "Gray":
				return Color.GRAY;
			default:
				return Color.BLUE;
		}
	}


	private Color getColor () {
		String selectedColor = (String) torosColorComboBox.getSelectedItem();
		switch (selectedColor) {

			case "Black":
				return Color.BLACK;
			case "Blue":
				return Color.BLUE;
			case "Cyan":
				return Color.CYAN;
			case "Dark Gray":
				return Color.DARK_GRAY;
			case "Gray":
				return Color.GRAY;
			case "Green":
				return Color.GREEN;
			case "Light Gray":
				return Color.LIGHT_GRAY;
			case "Magenta":
				return Color.MAGENTA;
			case "Pink":
				return Color.PINK;
			case "Red":
				return Color.RED;
			case "Orange":
				return Color.ORANGE;
			case "Yellow":
				return Color.YELLOW;
			default:
				return Color.WHITE;
		}
	}


	public static void main (String[] args) {
		new BoxColor();
	}
}
