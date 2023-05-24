package edu.damago.jdb.world;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import edu.damago.jdb.tool.CommandShell;
import edu.damago.jdb.tool.JSON;
import edu.damago.jdb.tool.Strings;
import edu.damago.jdb.tool.Strings.Alignment;


/**
 * Controller for the world application.
 */
public class WorldController {
	static private final String QUERY_COUNTRIES = "SELECT * FROM Country";
	static private final String QUERY_LANGUAGE = "SELECT Country.globalName, countrylanguage.countryCode, countrylanguage.language "
		+ "FROM CountryLanguage INNER JOIN Country ON countryCode = longCode "
		+ "WHERE"
		+ "(? IS NULL OR Country.globalName = ?) AND"
		+ "(? IS NULL OR countrylanguage.countryCode = ?) AND"
		+ "(? IS NULL OR countrylanguage.language = ?)"
		;
	static private final String QUERY_CITIES= "SELECT country.globalName, city.countryCode, city.name, city.district, city.population FROM city INNER JOIN country ON country.longCode = city.countryCode WHERE"
		+ "(? IS NULL OR city.population >= ?) AND"
		+ "(? IS NULL OR city.population <= ?)"
		;
	static private final String QQ = "SELECT Country.globalName, countrylanguage.countryCode, countrylanguage.language "
		+ "FROM CountryLanguage INNER JOIN Country ON countryCode = longCode "
		+ "WHERE"
		+ "(? IS NULL OR Country.globalName = ?) AND"
		+ "(? IS NULL OR countrylanguage.countryCode = ?) AND"
		+ "(? IS NULL OR countrylanguage.language = ?)"
		;
	//"SELECT Country.globalName AS countryName, countrylanguage.countryCode, countrylanguage.language FROM CountryLanguage INNER JOIN Country ON countryCode = longCode ORDER BY language ASC, globalName ASC" +
	
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
		this.rootView.addEventListener("4", parameterization -> this.performQueryTestCommand(parameterization));
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
		System.out.println("- query-countries [<JSON>]: display the countries matching the given JSON filter criteria");
		System.out.println("  (longCode, shortCode, name, continent, region, capitalName, minTotalPopulation, maxTotalPopulation, minSurfaceArea, maxSurfaceArea");
		System.out.println("- query-languages [<JSON>]: displays the languages per country matching the given JSON filter criteria");
		//query-languages {"language": "German", "minRelevance": 50}
		System.out.println("  (countryCode, country, language, official, minRelevance, maxRelevance");
		System.out.println("- query-cities [<JSON>]: display the cities per country matching the given JSON filter criteria");
		System.out.println("  (countryCode, country, city, district, minPopulation, maxPopulation, capital)");
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
		final Map<String,Object> filterCriteria = JSON.parse(parameterization);
			
		try (PreparedStatement jdbcStatement = this.jdbcConnection.prepareStatement(QUERY_LANGUAGE)) {
			int index = 0;
			for (final String key : new String[] {"globalName", "countryCode", "language"}) {
				final Object value = filterCriteria.get(key);
				jdbcStatement.setObject(++index, value);
				jdbcStatement.setObject(++index, value);
			}
			
			try (ResultSet tableCursor = jdbcStatement.executeQuery()) {
				displayTableSeparator(37, 5, 22);

				final String nameHeader = Strings.resize("Name", 35, Alignment.LEFT, ' ');
				final String countryCodeHeader = Strings.resize("CC", 3, Alignment.LEFT, ' ');
				final String languageHeader = Strings.resize("Language", 20, Alignment.LEFT, ' ');

				System.out.format("| %s | %s | %s | %n", nameHeader, countryCodeHeader, languageHeader);

				displayTableSeparator(37, 5, 22);
				while (tableCursor.next()) {
					
					final String name = Strings.resize(tableCursor.getString("globalName"), 35, Alignment.LEFT, ' ');
					final String countryCode = Strings.resize(tableCursor.getString("countryCode"), 3, Alignment.LEFT, ' ');
					final String language = Strings.resize(tableCursor.getString("language"), 20, Alignment.LEFT, ' ');
					
					System.out.format("| %s | %s | %s | %n", name, countryCode, language);
				}

				displayTableSeparator(37, 5, 22);
			}
		}
	}

	
	
	/**
	 * Performs the query-cities command.
	 * @param parameterization the command parameterization, empty for none
	 * @throws NullPointerException if the given parameterization is null
	 * @throws SQLException if there is an SQL related problem
	 * globalName, 	cityIdentity 		name 			countryCode		district 			population 		capital
	 * //| LC  		| Name       	|     ID	 | 		Name  		| District        | Population | 	Capital |
	 * //SELECT country.globalName, city.countryCode, city.name, city.district, city.population
	 */
	public void performQueryCitiesCommand (final String parameterization) throws NullPointerException, SQLException {
		
		final Map<String,Object> filterCriteria = JSON.parse(parameterization);
		try (PreparedStatement jdbcStatement = this.jdbcConnection.prepareStatement(QUERY_CITIES)) {
			int index = 0;
			for (final String key : new String[] { "minPopulation", "maxPopulation" }) {
				final Object value = filterCriteria.get(key);
				jdbcStatement.setObject(++index, value);
				jdbcStatement.setObject(++index, value);
			} 
			try (ResultSet tableCursor = jdbcStatement.executeQuery()) {
				displayTableSeparator(37, 5, 37, 22, 13);

				final String globalNameHeader = Strings.resize("globalName", 35, Alignment.LEFT, ' ');
				final String countryCodeHeader = Strings.resize("countryCode", 3, Alignment.LEFT, ' ');
				final String nameHeader = Strings.resize("name", 35, Alignment.LEFT, ' ');
				final String districtHeader = Strings.resize("district", 20, Alignment.LEFT, ' ');
				final String populationHeader = Strings.resize("population", 11, Alignment.RIGHT, ' ');
				System.out.format("| %s | %s | %s | %s | %s | %n", globalNameHeader, countryCodeHeader, nameHeader, districtHeader, populationHeader);

				displayTableSeparator(37, 5, 37, 22, 13);
				while (tableCursor.next()) {
					
					final String globalName = Strings.resize(tableCursor.getString("globalName"), 35, Alignment.LEFT, ' ');
					final String countryCode = Strings.resize(tableCursor.getString("countryCode"), 3, Alignment.LEFT, ' ');
					final String name = Strings.resize(tableCursor.getString("name"), 35, Alignment.LEFT, ' ');
					final String district = Strings.resize(tableCursor.getString("district"), 20, Alignment.LEFT, ' ');
					final String population = Strings.resize(tableCursor.getString("population"), 11, Alignment.RIGHT, ' ');
					
					System.out.format("| %s | %s | %s | %s | %s | %n", globalName, countryCode, name, district, population);
				}

				displayTableSeparator(37, 5, 37, 22, 13);
			}
		}
	}
	
	
	public void performQueryTestCommand (String parameterization) throws NullPointerException, SQLException {
		if (parameterization.isBlank()) parameterization = "{ }";
		
		final Map<String,Object> filterCriteria = JSON.parse(parameterization);

		try (PreparedStatement jdbcStatement = this.jdbcConnection.prepareStatement(QQ)) {
			int index = 0;
			for (final String key : new String[] {"globalName", "countryCode", "language"}) {
				final Object value = filterCriteria.get(key);
				jdbcStatement.setObject(++index, value);
				jdbcStatement.setObject(++index, value);
			}
			
			try (ResultSet tableCursor = jdbcStatement.executeQuery()) {
				displayTableSeparator(20, 20, 20);

				final String nameHeader = Strings.resize("globalName", 20, Alignment.LEFT, ' ');
				final String countryCodeHeader = Strings.resize("countryCode", 20, Alignment.LEFT, ' ');
				final String languageHeader = Strings.resize("Language", 20, Alignment.LEFT, ' ');
			
				System.out.format("| %s | %s | %s | %n", nameHeader, countryCodeHeader, languageHeader);

				displayTableSeparator(20, 20, 20);
				while (tableCursor.next()) {
					
					final String name = Strings.resize(tableCursor.getString("globalName"), 20, Alignment.LEFT, ' ');
					final String countryCode = Strings.resize(tableCursor.getString("countryCode"), 20, Alignment.LEFT, ' ');
					final String language = Strings.resize(tableCursor.getString("language"), 20, Alignment.LEFT, ' ');

					System.out.format("| %s | %s | %s | %n", name, countryCode, language);
				}

				displayTableSeparator(20, 20, 20);
			}
		}
	}
	
	
	
}