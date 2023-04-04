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
				switch (e.getActionCommand()) {
					case "+":
						model.add(secondNumber);
						break;
					case "-":
						model.subtract(secondNumber);
						break;
					case "*":
						model.multiply(secondNumber);
						break;
					case "/":
						model.divide(secondNumber);
						break;
					default:
						model.add(secondNumber);
				}
				view.setCalcSolution(model.getCalculationValue());
			} catch (NumberFormatException ex) {
				view.displayErrorMessage("You Need to Enter 2 Integers");
			}
		}
	}
}
