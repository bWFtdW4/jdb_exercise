package edu.damago.jdb.world;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import edu.damago.jdb.tool.CommandShell;
import edu.damago.jdb.tool.Strings;
import edu.damago.jdb.tool.Strings.Alignment;


/**
 * Controller for the world application.
 */
public class WorldController {
	static private final String QUERY_COUNTRIES = "SELECT * FROM Country";
	static private final String QUERY_LANGUAGE = "SELECT * FROM city INNER JOIN countrylanguage ON city.countryCode = countrylanguage.countryCode";
	static private final String QUERY_CITIES= "SELECT Country.globalName, City.* FROM Country INNER JOIN City ON Country.longCode = City.countryCode";

	private final CommandShell rootView;
	private final Connection jdbcConnection;


	/**
	 * Initialize this instance.
	 * @throws SQLException if there is an SQL related problem
	 */
	public WorldController () throws SQLException {
		this.rootView = new CommandShell();
		this.jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "root");
		this.rootView.setExceptionHandler(e -> e.printStackTrace());
		
		//performQueryLanguagesCommand(QUERY_LANGUAGE);
		//performQueryCitiesCommand(QUERY_CITIES);
		
		// register event listeners
		this.rootView.addEventListener("quit", parameterization -> this.performQuitCommand(parameterization));
		this.rootView.addEventListener("exit", parameterization -> this.performQuitCommand(parameterization));
		this.rootView.addEventListener("help", parameterization -> this.performHelpCommand(parameterization));
		this.rootView.addEventListener("query-countries", parameterization -> this.performQueryCountriesCommand(parameterization));
		this.rootView.addEventListener("query-languages", parameterization -> this.performQueryLanguagesCommand(parameterization));
		this.rootView.addEventListener("query-cities", parameterization -> this.performQueryCitiesCommand(parameterization));
		this.rootView.addEventListener("1", parameterization -> this.performQueryCountriesCommand(parameterization));
		this.rootView.addEventListener("2", parameterization -> this.performQueryLanguagesCommand(parameterization));
		this.rootView.addEventListener("3", parameterization -> this.performQueryCitiesCommand(parameterization));
	}


	/**
	 * Returns the root view.
	 * @return the root view component
	 */
	public CommandShell rootView () {
		return this.rootView;
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
		System.out.println("- query-countries: display the countries");
		System.out.println("- query-languages: displays the languages per country");
		System.out.println("- query-cities: display the cities per country");
	}


	/**
	 * Performs the query-countries command.
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 * @throws SQLException if there is an SQL related problem
	 */
	public void performQueryCountriesCommand (final String parameterization) throws NullPointerException, SQLException {
		try (PreparedStatement jdbcStatement = this.jdbcConnection.prepareStatement(QUERY_COUNTRIES)) {
			try (ResultSet tableCursor = jdbcStatement.executeQuery()) {
				displayTableSeparator(5, 4, 42, 15, 28, 13, 13);
				final String longCodeHeader = Strings.resize("LC", 3, Alignment.LEFT, ' ');
				final String shortCodeHeader = Strings.resize("SC", 2, Alignment.LEFT, ' ');
				final String globalNameHeader = Strings.resize("Name", 40, Alignment.LEFT, ' ');
				final String continentHeader = Strings.resize("Continent", 13, Alignment.LEFT, ' ');
				final String regionHeader = Strings.resize("Region", 26, Alignment.LEFT, ' ');
				final String totalPopulationHeader = Strings.resize("Population", 11, Alignment.RIGHT, ' ');
				final String surfaceAreaHeader = Strings.resize("Area (km^2)", 11, Alignment.RIGHT, ' ');
				System.out.format("| %s | %s | %s | %s | %s | %s | %s |%n", longCodeHeader, shortCodeHeader, globalNameHeader, continentHeader, regionHeader, totalPopulationHeader, surfaceAreaHeader);

				displayTableSeparator(5, 4, 42, 15, 28, 13, 13);
				while (tableCursor.next()) {
					final String longCode = tableCursor.getString("longCode");
					final String shortCode = tableCursor.getString("shortCode");
					final String globalName = Strings.resize(tableCursor.getString("globalName"), 40, Alignment.LEFT, ' ');
					final String continent = Strings.resize(tableCursor.getString("continent"), 13, Alignment.LEFT, ' ');
					final String region = Strings.resize(tableCursor.getString("region"), 26, Alignment.LEFT, ' ');
					final String totalPopulation = Strings.resize(tableCursor.getString("totalPopulation"), 11, Alignment.RIGHT, ' ');
					final String surfaceArea = Strings.resize(tableCursor.getString("surfaceArea"), 11, Alignment.RIGHT, ' ');
					System.out.format("| %s | %s | %s | %s | %s | %s | %s |%n", longCode, shortCode, globalName, continent, region, totalPopulation, surfaceArea);
				}

				displayTableSeparator(5, 4, 42, 15, 28, 13, 13);
			}
		}
	}


	/**
	 * Displays a table separator line based on the given column lengths.
	 * @param columnLengths the given column lengths (var-arg)
	 * @throws NullPointerException if the given argument is null
	 */
	static private void displayTableSeparator (final int... columnLengths) throws NullPointerException {
		System.out.print('+');
		for (final int columnLength : columnLengths) {
			System.out.print(Strings.resize("", columnLength, Alignment.LEFT, '-'));
			System.out.print('+');
		}
		System.out.println();
	}


	/**
	 * Performs the query-languages command.
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 * @throws SQLException if there is an SQL related problem
	 * LC  | Name                                     | Language                       | Official | Relevance (%) |
	 */
	public void performQueryLanguagesCommand (final String parameterization) throws NullPointerException, SQLException {
		try (PreparedStatement jdbcStatement = this.jdbcConnection.prepareStatement(QUERY_LANGUAGE)) {
			try (ResultSet tableCursor = jdbcStatement.executeQuery()) {
				displayTableSeparator(8, 22, 22, 12, 12);

				final String cityIdentityHeader = Strings.resize("CI", 6, Alignment.LEFT, ' ');
				final String nameHeader = Strings.resize("Name", 20, Alignment.LEFT, ' ');
				final String languageHeader = Strings.resize("Language", 20, Alignment.LEFT, ' ');
				final String officialHeader = Strings.resize("Official", 10, Alignment.LEFT, ' ');
				final String relevanceHeader = Strings.resize("Relevance", 10, Alignment.RIGHT, ' ');

				System.out.format("| %s | %s | %s | %s | %s |%n", cityIdentityHeader, nameHeader, languageHeader, officialHeader, relevanceHeader);

				displayTableSeparator(8, 22, 22, 12, 12);
				while (tableCursor.next()) {
					
					final String cityIdentity = Strings.resize(tableCursor.getString("countryCode"), 6, Alignment.LEFT, ' ');
					final String name = Strings.resize(tableCursor.getString("name"), 20, Alignment.LEFT, ' ');
					final String language = Strings.resize(tableCursor.getString("language"), 20, Alignment.LEFT, ' ');
					final String official = Strings.resize(tableCursor.getObject("official").toString(), 10, Alignment.LEFT, ' ');
					final String relevance = Strings.resize(tableCursor.getString("relevance"), 10, Alignment.RIGHT, ' ');

					System.out.format("| %s | %s | %s | %s | %s |%n", cityIdentity, name, language, official, relevance);
				}

				displayTableSeparator(8, 22, 22, 12, 12);
			}
		}
	}

	
	
	/**
	 * Performs the query-cities command.
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 * @throws SQLException if there is an SQL related problem
	 * globalName, 	cityIdentity 		name 			countrycode		district 			population 		capital
	 * | LC  		| Name       	|     ID	 | 		Name  		| District        | Population | 	Capital |
	 */
	public void performQueryCitiesCommand (final String parameterization) throws NullPointerException, SQLException {
		try (PreparedStatement jdbcStatement = this.jdbcConnection.prepareStatement(QUERY_CITIES)) {
			try (ResultSet tableCursor = jdbcStatement.executeQuery()) {
				displayTableSeparator(37, 7, 22, 6, 37, 12, 10);

				final String nameHeader = Strings.resize("globalName", 35, Alignment.LEFT, ' ');
				final String cityIdentityHeader = Strings.resize("CI", 5, Alignment.LEFT, ' ');
				final String name2Header = Strings.resize("id", 20, Alignment.LEFT, ' ');
				final String countryCodeHeader = Strings.resize("CC", 4, Alignment.LEFT, ' ');
				final String districtHeader = Strings.resize("district", 35, Alignment.LEFT, ' ');
				final String populationHeader = Strings.resize("population", 10, Alignment.RIGHT, ' ');
				final String capitalHeader = Strings.resize("Capital", 8, Alignment.RIGHT, ' ');
				System.out.format("| %s | %s | %s | %s | %s | %s | %s | %n", nameHeader, cityIdentityHeader, name2Header, countryCodeHeader, districtHeader,populationHeader, capitalHeader);

				displayTableSeparator(37, 7, 22, 6, 37, 12, 10);
				while (tableCursor.next()) {
					
					final String name = Strings.resize(tableCursor.getString("globalName"), 35, Alignment.LEFT, ' ');
					final String cityIdentity = Strings.resize(tableCursor.getString("cityIdentity"), 5, Alignment.LEFT, ' ');
					final String name2 = Strings.resize(tableCursor.getString("name"), 20, Alignment.LEFT, ' ');
					final String countryCode = Strings.resize(tableCursor.getString("countryCode"), 4, Alignment.LEFT, ' ');
					final String district = Strings.resize(tableCursor.getString("district"), 35, Alignment.LEFT, ' ');
					final String population = Strings.resize(tableCursor.getString("population"), 10, Alignment.RIGHT, ' ');
					final String capital = Strings.resize(Objects.toString(tableCursor.getObject("capital")), 8, Alignment.RIGHT, ' ');
					
					System.out.format("| %s | %s | %s | %s | %s | %s | %s | %n", name, cityIdentity, name2, countryCode, district, population, capital);
				}

				displayTableSeparator(37, 7, 22, 6, 37, 12, 10);
			}
		}
	}
	
	
}