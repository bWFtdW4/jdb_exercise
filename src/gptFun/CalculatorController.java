package gptFun;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CalculatorController {
	private CalculatorView view;
	private CalculatorModel model;


	public CalculatorController (CalculatorView view, CalculatorModel model) {
		this.view = view;
		this.model = model;
		this.view.addCalculateListener(new CalculateListener());
	}


	class CalculateListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			int firstNumber, secondNumber = 0;
			try {
				firstNumber = view.getFirstNumber();
				secondNumber = view.getSecondNumber();
				switch (view.getOperator()) {
					case "+":
						model.add(firstNumber, secondNumber);
						break;
					case "-":
						model.subtract(firstNumber, secondNumber);
						break;
					case "*":
						model.multiply(firstNumber, secondNumber);
						break;
					case "/":
						model.divide(firstNumber, secondNumber);
						break;
					default:
						model.add(firstNumber, secondNumber);
				}
				view.setCalcSolution(model.getCalculationValue());
			} catch (NumberFormatException ex) {
				view.displayErrorMessage("You Need to Enter 2 Integers");
			}
		}
	}
}
