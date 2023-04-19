package learning;

public class CalculatorMain {
	public static void main (String[] args) {
		// Initialize model, view, and controller
		CalculatorModel model = new CalculatorModel();
		CalculatorView view = new CalculatorView();
		//*CalculatorController controller = new CalculatorController(model, view);
		// Add event listeners
		CalculatorController myController = new CalculatorController(view, model);
		//view.addCalculateListener(new CalculateListener());
		view.addCalculateListener(null);
		
		// Start event loop

		view.setVisible(true);

	}

}
/*
 * *In this example, the main method initializes the CalculatorModel, CalculatorView, and CalculatorController objects, and sets
 * up any necessary event listeners. The addCalculateListener() method is called to add a for button clicks and passes the user
 * input to the controller. Finally, the setVisible() method is called to make the view visible and start the event loop.
 * 
 */