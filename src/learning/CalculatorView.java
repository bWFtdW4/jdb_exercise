package learning;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CalculatorView extends JFrame {
	private JLabel firstNumberLable = new JLabel("First Number: ");
	private JTextField firstNumber = new JTextField(10);
	//private JLabel additionLabel = new JLabel("+");
	private JLabel operatorLable = new JLabel("Operator:");
	private JTextField operator = new JTextField(10);
	private JMenu operatorMenu = new JMenu();
	private JLabel secondNumberLabel = new JLabel("Second Number:");
	private JTextField secondNumber = new JTextField(10);
	private JButton calculateButton = new JButton("Calculate");
	private JLabel calcSolutionLabel = new JLabel("Result: ");
	private JTextField calcSolution = new JTextField(10);
	
	
	
	CalculatorView () {
		JPanel calcPanel = new JPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 300);
		this.setTitle("Calulator");
		this.setResizable(false);

		
		calcPanel.setLayout(null);
		calcPanel.setVisible(true);
		//calcPanel.setBackground(Color.blue);
		//calcPanel.add(operatorMenu);
		calcPanel.add(firstNumberLable);
		calcPanel.add(firstNumber);
		calcPanel.add(operatorLable);
		calcPanel.add(operator);
		calcPanel.add(secondNumberLabel);
		calcPanel.add(secondNumber);
		calcPanel.add(calculateButton);
		calcPanel.add(calcSolutionLabel);
		calcPanel.add(calcSolution);
		calcPanel.add(operatorBox);
		
		
		
		
		
        
		
		//operatorMenu.setBounds	(0,		0,  100, 20);
		firstNumberLable.setBounds	(20,   30,  100, 20);
		firstNumber.setBounds		(150,  30,  100,  20);
		operatorLable.setBounds		(20,   60,  100,  20);
		//operator.setBounds		(100,  60,  100,  20);
		operatorBox.setBounds		(150,  60,  100,  20);
		secondNumberLabel.setBounds	(20,   90,  100,  20);
		secondNumber.setBounds		(150,  90,  100,  20);
		calcSolutionLabel.setBounds	(20,   120, 100,  20);
		calcSolution.setBounds		(150,  120, 100,  20);
		calculateButton.setBounds	(150,  200, 100, 20);
		
		
		this.add(calcPanel);
	}
	
	String[] optionsToChoose = {
								"Addition",
								"Subtraction",
								"Multiplication",
								"Division",
								"TBC",
								
	};
	JComboBox<String> operatorBox = new JComboBox<>(optionsToChoose);


	public int getFirstNumber () {
		return Integer.parseInt(firstNumber.getText());
	}


	public int getSecondNumber () {
		return Integer.parseInt(secondNumber.getText());
	}

	public String getOperator () {
		return operator.getText();
	}

	public int getCalcSolution () {
		return Integer.parseInt(calcSolution.getText());
	}


	public void setCalcSolution (int solution) {
		calcSolution.setText(Integer.toString(solution));
	}


	void addCalculateListener (ActionListener listenForCalcButton) {
		calculateButton.addActionListener(listenForCalcButton);
	}


	void displayErrorMessage (String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}
}