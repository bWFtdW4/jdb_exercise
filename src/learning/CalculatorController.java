package learning;

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
				switch (view.operatorBox.getItemAt(view.operatorBox.getSelectedIndex())) {
					case "Addition":
						model.add(firstNumber, secondNumber);
						break;
					case "Subtraction":
						model.subtract(firstNumber, secondNumber);
						break;
					case "Multiplication":
						model.multiply(firstNumber, secondNumber);
						break;
					case "Division":
						model.divide(firstNumber, secondNumber);
						break;
					default:
						view.displayErrorMessage("TBC");
				}
				view.setCalcSolution(model.getCalculationValue());
			} catch (NumberFormatException ex) {
				view.displayErrorMessage("Please add both numbers!");
			}
		}
	}
}
