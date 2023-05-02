package monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyMonsterTeam {
	private final List<Map<String,Object>> playerMonsterTeam = new ArrayList<>();
	
	private MonsterController controller;
	private Player thePlayer;

	
	MyMonsterTeam(){
		
	}
	
	public List<Map<String,Object>> getplayerMonsterTeam() {
		return this.playerMonsterTeam;
	}

	public void showMyTeam () {
		System.out.println(thePlayer.getPlayerName() + "'s Team:");
		for (Map<String,Object> monster : this.playerMonsterTeam) {
			System.out.println("Nr. " + monster.get("id")
							+ "| Name: " + monster.get("name")	
							+ "| HP: " + monster.get("hp")
							+ "| Attack: " + monster.get("attack") 
							+ "| Speed: " + monster.get("speed") 
							+ "| Height: " + monster.get("height")
							+ "| Weight: " + monster.get("weight")
							+ "| Type1: " + monster.get("type1")
							+ "| Type2: " + monster.get("type2")

				);
		}
		
	}
	
}
