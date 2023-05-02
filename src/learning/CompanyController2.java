package learning;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import edu.damago.jdb.tool.ConsoleEventSource;
import edu.damago.jdb.tool.JSON;


/**
 * Controller for the company application .
 */
public class CompanyController2 {
	private final List<Map<String,Object>> employees = new ArrayList<>();
	private String elementString = "";

	private final ConsoleEventSource eventSource;


	/**
	 * Initializes a new instance.
	 */
	public CompanyController2 () {
		this.eventSource = new ConsoleEventSource();

		// register event listeners
		this.eventSource.addEventListener("exit", parameterization -> this.performQuitCommand(parameterization));
		this.eventSource.addEventListener("quit", parameterization -> this.performQuitCommand(parameterization));
		this.eventSource.addEventListener("help", parameterization -> this.performHelpCommand(parameterization));

		this.eventSource.addEventListener("display-employees", parameterization -> this.performDisplayEmployeesCommand(parameterization));
		this.eventSource.addEventListener("display", parameterization -> this.performDisplayEmployeesCommand(parameterization));
		this.eventSource.addEventListener("show", parameterization -> this.performDisplayEmployeesCommand(parameterization));

		this.eventSource.addEventListener("add", parameterization -> this.performAddTestData(parameterization));
		this.eventSource.addEventListener("add-employee", parameterization -> this.performAddEmployeeCommand(parameterization));

		this.eventSource.addEventListener("remove-employee", parameterization -> this.performRemoveEmployeeCommand(parameterization));
		this.eventSource.addEventListener("del", parameterization -> this.performRemoveEmployeeCommand(parameterization));

		this.eventSource.addEventListener("update-employee", parameterization -> this.performUpdateEmployeeCommand(parameterization));
		this.eventSource.addEventListener("update", parameterization -> this.performUpdateEmployeeCommand(parameterization));
		this.eventSource.addEventListener("update2", parameterization -> this.performUpdate2(parameterization));

		this.eventSource.addEventListener("save-employees", parameterization -> this.performSaveEmployeesCommand(parameterization));
		this.eventSource.addEventListener("save", parameterization -> this.performSaveEmployeesCommand(parameterization));
		this.eventSource.addEventListener("load-employees", parameterization -> this.performLoadEmployeesCommand(parameterization));
		this.eventSource.addEventListener("load", parameterization -> this.performLoadEmployeesCommand(parameterization));
	}


	/**
	 * Returns the root view component
	 * @return the view component
	 */
	public ConsoleEventSource eventSource () {
		return this.eventSource;
	}


	/**
	 * adds test data
	 */
	public void performAddTestData (final String parameterization) throws NullPointerException {
		this.employees.clear();
		this.employees.add(JSON.parse("{ 'id': 1, 'email': 'b@z.de', 'forename': 'Boris', 'surname': 'Balao', 'phones': ['0174/897654566', '0178/115591']}"));
		this.employees.add(JSON.parse("{ 'id': 2, 'email': 'f@z.de', 'forename': 'Faskjdh', 'surname': 'Psdfg', 'phones': ['0174/4098566', '0178/115592']}"));
		this.employees.add(JSON.parse("{ 'id': 3, 'email': 'h@z.de', 'forename': 'Hsdf', 'surname': 'Hasddet', 'phones': ['0174/345676', '0178/115593']}"));
		this.employees.add(JSON.parse("{ 'id': 4, 'email': 'j@z.de', 'forename': 'Jhgfd', 'surname': 'Qaasd', 'phones': ['0123/412366', '0178/115594']}"));
		System.out.println("TEST DATA ADDED");
		this.employees.add(JSON.parse(" {\r\n"
			+ "    \"id\": 151,\r\n"
			+ "    \"dex_nr\": 151,\r\n"
			+ "    \"name_en\": \"Mew\",\r\n"
			+ "    \"name_de\": \"Mew\",\r\n"
			+ "    \"loc\": \"Kanto\",\r\n"
			+ "    \"Total\": 600,\r\n"
			+ "    \"HP\": 100,\r\n"
			+ "    \"Attack\": 100,\r\n"
			+ "    \"Defense\": 100,\r\n"
			+ "    \"SpAtk\": 100,\r\n"
			+ "    \"SpDef\": 100,\r\n"
			+ "    \"Speed\": 100,\r\n"
			+ "    \"Größe\": \"0.4 m\",\r\n"
			+ "    \"Gewicht\": \"4.0 kg\",\r\n"
			+ "    \"dex_nr__1\": 151,\r\n"
			+ "    \"en_type1\": \"PSYCHIC\",\r\n"
			+ "    \"en_type2\": \"\",\r\n"
			+ "    \"de_type1\": \"PSYCHO\",\r\n"
			+ "    \"de_type2\": \"\",\r\n"
			+ "    \"ability1\": \"Synchro\",\r\n"
			+ "    \"ability2\": \"Keine\",\r\n"
			+ "    \"hidden\": \"Keine\",\r\n"
			+ "    \"farbe\": 7,\r\n"
			+ "    \"form\": \"Neue Art\",\r\n"
			+ "    \"formNR\": 6,\r\n"
			+ "    \"formTYPE\": \"Zweibeiner mit Schweif\"\r\n"
			+ "  }}"));
		
	}


	/**
	 * Performs the welcome command.
	 */
	public void performWelcome () {
		System.out.println("\"C:\\Java\\M\\Monster\\dex.json\"");
		System.out.println("Use [help] for commands and information.");
	}


	/**
	 * Performs the quit command.
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 */
	public void performQuitCommand (final String parameterization) throws NullPointerException {
		System.out.println("Bye Bye!");
		System.exit(0);
	}


	/**
	 * Performs the help command.
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 */
	public void performHelpCommand (final String parameterization) throws NullPointerException {
		System.out.println("Available commands:");
		System.out.println("- [quit]: terminates this program");
		System.out.println("- [help]: displays this help");
		System.out.println("- [display-employees]: displays the registered employees as JSON text representations");
		System.out.println("- [add-employee] <json>: adds an employee created from the given JSON text representation");
		System.out.println("- [remove-employee] <id>: removes the employee with the given ID");
		System.out.println("- [load-employees] <json-file-path>: Replaces all employees by parsing the given JSON file content");
		System.out.println("- [save-employees] <json-file-path>: Replaces the content of the given file with JSON generated from all employees");
	}


	/**
	 * Performs the display-employees command.
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 */
	public void performDisplayEmployeesCommand (final String parameterization) throws NullPointerException {

		if (employees.isEmpty()) {
			System.out.println("No employees!");
		} else {
			if (parameterization.equals("")) {
				for (Map<String,Object> employee : this.employees) {
					System.out.println("employees: " + JSON.stringify(employee));
				}
			} else {
				for (Map<String,Object> employee : this.employees) {
					double id = Double.parseDouble(parameterization);
					if ((double) employee.get("id") == id) {
						System.out.println("employees: " + JSON.stringify(employee));
					}
				}
			}
		}
	}


	/**
	 * Performs the add-employee command.
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 */
	public void performAddEmployeeCommand (final String parameterization) throws NullPointerException {

		final Map<String,Object> employee = JSON.parse(parameterization);
		this.employees.add(employee);
		System.out.println("employee added!");
	}


	/**
	 * Performs the remove-employee command.
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 */
	public void performRemoveEmployeeCommand (final String parameterization) throws NullPointerException {

		double id = Double.parseDouble(parameterization);

		for (Map<String,Object> employee : this.employees) {
			if ((double) employee.get("id") == id) {
				this.employees.remove(employee);
				System.out.println("removed id: " + id);
			}
		}
	}


	/**
	 * Performs the load-employees command.
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 * @throws IOException
	 */
	public void performLoadEmployeesCommand (final String parameterization) throws NullPointerException, IOException {
		//Path sourcePath = Paths.get("C:\\Java\\Testdata\\testfile1.txt");


		if (parameterization.length() < 1) throw new IllegalArgumentException("please give file path!");
		final Path sourcePath = Paths.get(parameterization);
		System.out.println("Loading file: = " + sourcePath);

		final List<String> fileLines = Files.readAllLines(sourcePath, StandardCharsets.UTF_8);
		this.employees.clear();

		for (final String json : fileLines) {
			final Map<String,Object> employee = JSON.parse(json);
			if (employee.containsKey("id")) {
				this.employees.add(employee);
				System.out.println("added.." + employee.get("id"));
			}else {
				System.err.println("warning: skipped JSON line for lack of id attribute!");
		}
	}
	}


	/**
	 * Performs the save-employees command.
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 * @throws IOException
	 */
	public void performSaveEmployeesCommand (final String parameterization) throws NullPointerException, IOException {

		//Path sinkPath = Paths.get("C:\\Java\\Testdata\\testfile2.txt");

		if (parameterization.length() < 1) throw new IllegalArgumentException("please give save location!");
		final Path sinkPath = Paths.get(parameterization);
		System.out.println("Saving file: = " + sinkPath);

		final List<String> fileLines = new ArrayList<>();
		for (Map<String,Object> employee : this.employees) {
			fileLines.add(JSON.stringify(employee));
			Files.write(sinkPath, fileLines, StandardCharsets.UTF_8);
		}
		System.out.println("file saved: " + sinkPath);
	}


	//v1: rename [id] [newNameString]
	//v2: rename [id] [elementString] [newNameString]
	public void performUpdateEmployeeCommand (final String parameterization) throws NullPointerException, IOException {

		final String[] arguments = parameterization.split("\\s+");

		if (arguments.length == 3) {
			double id = Double.parseDouble(arguments[0]);
			this.elementString = arguments[1];
			String changeString = arguments[2];

			if (parameterization.equals("")) {} else {
				for (Map<String,Object> employee : this.employees) {
					if (employee.get("id").equals(id)) {
						System.out.println("changing: " + this.elementString + " " + employee.get(this.elementString));
						employee.put(this.elementString, changeString);
						System.out.println("NEW " + this.elementString + " = " + employee.get(this.elementString));
					}
				}
			}
		} else {
			System.err.println("please use [rename] [id] [element to change] [new input]");
			System.err.println("elements: [forename], [surname], [phones], [email]");
		}

	}


	public void performUpdate2 (final String parameterization) throws NullPointerException, IOException {
		final String[] arguments = parameterization.split("\\s+");
		System.out.println("111: " + arguments.length);

		final String[] elementsToChange = { "forename", "surname", "phones", "email" };

		double id = Double.parseDouble(arguments[0]);
		this.elementString = arguments[1];
		String changeString = arguments[2];

		if (arguments.length == 3) {
			System.out.println("error 55555");
			for (final String element : elementsToChange) {
				System.out.println("error 666666");
				System.out.println("checking element: " + element);
				//System.out.println("element2change: " + elementsToChange.toString());
				if (arguments[1].equals(element)) {
					System.out.println("error 777777");
					this.elementString = arguments[1];
					System.out.println("the element was found " + element);

					for (Map<String,Object> employee : this.employees) {
						if ((double) employee.get("id") == id) {
							System.out.println("changing: " + this.elementString + " " + employee.get(this.elementString));
							employee.put(this.elementString, changeString);
							System.out.println("NEW " + this.elementString + " = " + employee.get(this.elementString));
						} else {
							System.out.println("error 44444");
							System.err.println("please use [rename] [id] [element to change] [new input]");
							System.err.println("elements: [forename], [surname], [phones], [email]");
						}
					}
				} else {
					System.out.println(elementString + " was not in");
				}
			}

			//		if (arguments[1].equals("id")) {
			//			this.elementString = "";
			//			System.out.println("this |" + arguments[1] + "| is the bad one. ");
			//		} else {
			//			this.elementString = arguments[1];
			//			System.out.println("this |" + arguments[1] + "| is the good one. ");
			//			System.out.println();

		} else {
			System.out.println("error 55555");
			System.err.println("please use [rename] [id] [element to change] [new input]");
			System.err.println("elements: [forename], [surname], [phones], [email]");
		}
	}
}
