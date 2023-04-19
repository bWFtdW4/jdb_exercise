package learning;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import edu.damago.jdb.tool.JSON;


public class CounterController {
	private final List<Map<String,Object>> inventory = new ArrayList<>();


	public void performAddTestData (final String parameterization) throws NullPointerException {
		this.inventory.add(JSON.parse("{'qty': '10','price': '9.90', 'name': 'applaae','id': 1 }"));
		this.inventory.add(JSON.parse("{ 'id': 2, 'name': 'banana', 'price': '4.90', 'qty': '10', }"));
		this.inventory.add(JSON.parse("{ 'id': 3, 'name': 'potato', 'price': '13.37', 'qty': '10', }"));
		this.inventory.add(JSON.parse("{ 'id': 4, 'name': 'tomato', 'price': '1.69', 'qty': '10', }"));
		System.out.println("TEST DATA ADDED");
	}


	public void performWelcome () {
		System.out.println("Welcome to the Counter app !");
		System.out.println("Use [help] for commands and information.");
	}


	public void performExit () {
		System.out.println("Bye Bye!");
		System.exit(0);
	}


	public void performHelpCommand (final String parameterization) throws NullPointerException {
		System.out.println("Available commands:");
		System.out.println("- [quit]: terminates this program");
		System.out.println("- [help]: displays this help");
		System.out.println("- [display-inventory]: displays inventory");
		System.out.println("- [add-item] <json>: adds an item ");
		System.out.println("- [sell-item] <json>: sells an item ");
		System.out.println("- [remove-item] <id>: removes an item ");
		System.out.println("- [load-inventory] <json-file-path>: load inventory");
		System.out.println("- [save-inventory] <json-file-path>: save inventory");
	}


	//add [name] > auto gen: [id] [name] [price] [qty] 
	public void performAddItem (final String parameterization) {
		final Map<String,Object> product = JSON.parse(parameterization);
		if (!product.containsKey("id")) throw new IllegalArgumentException("Add an ID !");

		this.inventory.add(product);

		System.out.println("Product added.");
	}


	//remove [id]
	//remove [id] [qty]
	public void performRemoveProduct (final String parameterization) {
		final String[] arguments = parameterization.split("\\s+");

		double id = Double.parseDouble(arguments[0]);
		double removeQty = Double.parseDouble(arguments[1]);

		if (parameterization.equals("")) {
			for (Map<String,Object> product : this.inventory) {
				if ((double) product.get("id") == id) {
					product.remove("id", id);
					System.out.println("Item " + id + " removed!");
				}
			}
		} else {
			for (Map<String,Object> product : this.inventory) {
				if ((double) product.get("id") == id) {
					double totalQty = (double) Double.parseDouble((String) product.get("qty"));
					double price = (double) Double.parseDouble((String) product.get("price"));
					double newQty = (totalQty - removeQty);
					System.out.println("new qty: " + newQty);
					product.put("qty", newQty);
					System.out.println("removed " + removeQty + " x " + product.get("name") + " Total: " + ((double) removeQty * (double) price));
					System.out.println(product);
				}
			}
		}
	}


	//sell [id] [qty]
	public void performSellProduct (final String parameterization) {
		final String[] arguments = parameterization.split("\\s+");

		double id = Double.parseDouble(arguments[0]);
		double sellQty = Double.parseDouble(arguments[1]);

		for (Map<String,Object> product : this.inventory) {
			if ((double) product.get("id") == id) {
				double totalQty = (double) Double.parseDouble((String) product.get("qty"));
				double price = (double) Double.parseDouble((String) product.get("price"));
				double newQty = (totalQty - sellQty);
				//System.out.println("new qty: " + newQty);
				product.put("qty", newQty);
				System.out.println("Sold " + sellQty + " x " + product.get("name") + " Total: " + ((double) sellQty * (double) price));
				System.out.println(product);
			}
		}
	}


	//show 
	//show [id]
	public void performDisplayInventory (final String parameterization) {

		if (inventory.isEmpty()) {
			System.out.println("The inventory is empty!");
		} else {
			if (parameterization.equals("")) {
				for (Map<String,Object> product : this.inventory) {
					System.out.println("inventory: " + JSON.stringify(product));
				}
			} else {
				for (Map<String,Object> product : this.inventory) {
					double id = Double.parseDouble(parameterization);
					if ((double) product.get("id") == id) {
						System.out.println("inventory: " + JSON.stringify(product));
					}
				}
			}
		}

	}


	//save [path]
	public void performSaveInventory (final String parameterization) throws IOException {

		if (parameterization.length() < 1) throw new IllegalArgumentException("please give save location!");
		final Path sinkPath = Paths.get(parameterization);
		System.out.println("Saving file: = " + sinkPath);

		final List<String> fileLines = new ArrayList<>();
		for (Map<String,Object> employee : this.inventory) {
			fileLines.add(JSON.stringify(employee));
			Files.write(sinkPath, fileLines, StandardCharsets.UTF_8);
		}
		System.out.println("file saved: " + sinkPath);

	}


	//load [path]
	public void performLoadInventory (final String parameterization) throws IOException {

		if (parameterization.length() < 1) throw new IllegalArgumentException("please give file path!");
		final Path sourcePath = Paths.get(parameterization);
		System.out.println("Loading file: " + sourcePath);

		final List<String> fileLines = Files.readAllLines(sourcePath, StandardCharsets.UTF_8);
		this.inventory.clear();
		
		for (final String fileLine : fileLines) {
			final Map<String,Object> product = JSON.parse(fileLine);
			if (product.containsKey("id"))
				this.inventory.add(product);
			else
				System.err.println("warning: skipped JSON line for lack of id attribute!");
		}
		System.out.println("File loaded.");
	}

}
