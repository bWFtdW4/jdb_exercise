package learning;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PokMok {

	    public static void main(String[] args) {
	        try {
	            ArrayList<HashMap<String, Object>> pokemons = parse("C:\\Java\\BM\\jdb_exercise\\src\\learning\\data.json");
	            System.out.println("loading: "+pokemons);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        System.out.println("asdaslkdalksdj");
	        displayData(null);
	    }

	    
	    public static void displayData(ArrayList<HashMap<String, Object>> pokemons) {
	        for (HashMap<String, Object> pokemon : pokemons) {
	            System.out.println("ID: " + pokemon.get("id"));
	            System.out.println("Name: " + pokemon.get("name"));
	            System.out.println("Type: " + pokemon.get("type"));
	            System.out.println("Stats: " + pokemon.get("stats"));
	            System.out.println("Abilities: " + pokemon.get("abilities"));
	            System.out.println("Moves: " + pokemon.get("moves"));
	            System.out.println();
	        }
	    }
	    
	    public static ArrayList<HashMap<String, Object>> parse(String fileName) throws IOException {
	        ArrayList<HashMap<String, Object>> pokemons = new ArrayList<>();
	        StringBuilder json = new StringBuilder();

	        FileReader fileReader = new FileReader(fileName);
	        int data;
	        while ((data = fileReader.read()) != -1) {
	            json.append((char) data);
	        }
	        fileReader.close();

	        String[] jsonArr = json.toString().split("[{},]");
	        HashMap<String, Object> pokemon = null;
	        for (String str : jsonArr) {
	            if (str.contains("\"id\":")) {
	                if (pokemon != null) {
	                    pokemons.add(pokemon);
	                }
	                pokemon = new HashMap<>();
	            }
	            if (pokemon != null) {
	                String[] keyValue = str.split(":");
	                if (keyValue.length == 2) {
	                    String key = keyValue[0].replaceAll("\"", "").trim();
	                    String value = keyValue[1].replaceAll("\"", "").trim();
	                    if (value.matches("\\d+")) {
	                        pokemon.put(key, Integer.parseInt(value));
	                    } else if (value.startsWith("[") && value.endsWith("]")) {
	                        String[] arrayValue = value.substring(1, value.length() - 1).replaceAll("\"", "").trim().split(",");
	                        pokemon.put(key, arrayValue);
	                    } else {
	                        HashMap<String, Object> nestedObj = new HashMap<>();
	                        while (str.contains("{") && str.contains("}")) {
	                            String nestedStr = str.substring(str.indexOf("{") + 1, str.indexOf("}"));
	                            String[] nestedKeyValue = nestedStr.split(":");
	                            if (nestedKeyValue.length == 2) {
	                                String nestedKey = nestedKeyValue[0].replaceAll("\"", "").trim();
	                                String nestedValue = nestedKeyValue[1].replaceAll("\"", "").trim();
	                                if (nestedValue.matches("\\d+")) {
	                                    nestedObj.put(nestedKey, Integer.parseInt(nestedValue));
	                                } else {
	                                    nestedObj.put(nestedKey, nestedValue);
	                                }
	                            }
	                            str = str.substring(str.indexOf("}") + 1);
	                        }
	                        pokemon.put(key, nestedObj);
	                    }
	                }
	            }
	        }
	        if (pokemon != null) {
	            pokemons.add(pokemon);
	        }
	        return pokemons;
	    }
	}
