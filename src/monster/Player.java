package monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Player {

	private MyMonsterTeam myMonsterTeam;
	private MonsterController controller;
	
	private String playerName;
	private int playerLevel;

	private final List<Map<String,Object>> playerMonsterTeam = new ArrayList<>();
	
	Player(){
		this.playerName = "name";
		this.playerLevel = 1;
	}

	public String getPlayerName () {
		System.out.println("----Log getPlayername: " + playerName);
		return this.playerName;
	}

	public void setPlayerName (String parameterization) {
		System.out.println("----Log setPlayername: " + parameterization);
		playerName = parameterization;
	}

	public int getPlayerLevel () {
		return this.playerLevel;
	}

	public void setPlayerLevel (int playerLevel) {
		this.playerLevel = playerLevel;
	}
	
	
	
}
