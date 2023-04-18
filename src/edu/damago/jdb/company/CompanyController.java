package edu.damago.jdb.company;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import edu.damago.jdb.tool.JSON;


/**
 * Controller for the company application.
 */
public class CompanyController {
	private final List<Map<String,Object>> employees = new ArrayList<>();


	/**
	 * adds test data
	 */
	public void performAddTestData (final String parameterization) throws NullPointerException {
		this.employees.add(JSON.parse("{ 'id': 1, 'email': 'b@z.de', 'forename': 'Boris', 'surname': 'Balao', 'phones': ['0174/897654566', '0178/115591']}"));
		this.employees.add(JSON.parse("{ 'id': 2, 'email': 'f@z.de', 'forename': 'Faskjdh', 'surname': 'Psdfg', 'phones': ['0174/4098566', '0178/115592']}"));
		this.employees.add(JSON.parse("{ 'id': 3, 'email': 'h@z.de', 'forename': 'Hsdf', 'surname': 'Hasddet', 'phones': ['0174/345676', '0178/115593']}"));
		this.employees.add(JSON.parse("{ 'id': 4, 'email': 'j@z.de', 'forename': 'Jhgfd', 'surname': 'Qaasd', 'phones': ['0123/412366', '0178/115594']}"));
		System.out.println("TEST DATA ADDED");
	}


	/**
	 * Performs the welcome command.
	 */
	public void performWelcome () {
		System.out.println("Welcome to the employee app !");
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
		System.out.println("- quit: terminates this program");
		System.out.println("- help: displays this help");
		System.out.println("- display-employees: displays the registered employees as JSON text representations");
		System.out.println("- add-employee <json>: adds an employee created from the given JSON text representation");
		System.out.println("- remove-employee <id>: removes the employee with the given ID");
		System.out.println("- load-employees <json-file-path>: Replaces all employees by parsing the given JSON file content");
		System.out.println("- save-employees <json-file-path>: Replaces the content of the given file with JSON generated from all employees");
	}


	/**
	 * Performs the display-employees command.
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 */
	public void performDisplayEmployeesCommand (final String parameterization) throws NullPointerException {
		// TODO
		//System.out.println("employees: " + this.employees);
		//System.out.println("employees: " + JSON.stringify(employees.toArray()));
		if (employees.isEmpty()) System.out.println("No employees!");

		for (Map<String,Object> employee : this.employees) {
			System.out.println("employees: " + JSON.stringify(employee));
		}

	}


	/**
	 * Performs the add-employee command.
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 */
	public void performAddEmployeeCommand (final String parameterization) throws NullPointerException {
		// TODO
		//final String[] arguments = parameterization.split("\\s+");
		//System.out.println("arguments: " + Arrays.toString(arguments));

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
		// TODO
		final String[] arguments = parameterization.split("\\s+");
		//System.out.println("arguments: " + Arrays.toString(arguments));

		double id = Double.parseDouble(parameterization);

		for (Map<String,Object> employee : this.employees) {
			if ((double) employee.get("id") != id) {
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
		// TODO
		final String[] arguments = parameterization.split("\\s+");
		//System.out.println("arguments: " + Arrays.toString(arguments));
		//Path sourcePath = Paths.get("C:\\Java\\Testdata\\testfile1.txt");

		this.employees.clear();

		if (parameterization.length() < 1) throw new IllegalArgumentException("please give load location!");
		final Path sourcePath = Paths.get(parameterization);
		System.out.println("Loading file: = " + sourcePath);

		//final String fileContent = Files.readString(sourcePath, StandardCharsets.UTF_8);
		//System.out.println(fileContent);

		final List<String> fileLines = Files.readAllLines(sourcePath, StandardCharsets.UTF_8);
		for (final String fileLine : fileLines) {
			//performAddEmployeeCommand(fileLine);
			System.out.println(fileLine);
		}

	}


	/**
	 * Performs the save-employees command.
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 * @throws IOException
	 */
	public void performSaveEmployeesCommand (final String parameterization) throws NullPointerException, IOException {
		// TODO
		final String[] arguments = parameterization.split("\\s+");
		//System.out.println("arguments: " + Arrays.toString(arguments));

		if (parameterization.length() < 1) throw new IllegalArgumentException("please give save location!");
		final Path sinkPath = Paths.get(parameterization);
		System.out.println("Saving file: = " + sinkPath);

		//Path sinkPath = Paths.get("C:\\Java\\Testdata\\testfile2.txt");

		final List<String> fileLines = new ArrayList<>();
		for (Map<String,Object> employee : this.employees) {
			fileLines.add(JSON.stringify(employee));
			Files.write(sinkPath, fileLines, StandardCharsets.UTF_8);
			//System.out.println("saving " + employee);
		}
		System.out.println("file saved: " + sinkPath);
	}
}