package monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Battle {

	private double currentBattleMonsterId;

	private MonsterController controller;
	private MyMonsterTeam myMonsterTeam;
	
	private final List<Map<String,Object>> playerMonsterTeam = myMonsterTeam.getplayerMonsterTeam();
	
	Battle(){
		
	}
	
	/**
	 * generate next monster
	 * and set setCurrentBattleMonsterId
	 */
	public void generateBattleMonster() {
		Random randomNumber = new Random();
		this.currentBattleMonsterId = (double) randomNumber.nextInt(10) + 1;
		setCurrentBattleMonsterId(randomNumber.nextInt(10) + 1);
		System.out.println("log: " + getCurrentBattleMonsterId());
	}
	
	public double getCurrentBattleMonsterId () {
		return currentBattleMonsterId;
	}

	public void setCurrentBattleMonsterId (double currentBattleMonsterId) {
		this.currentBattleMonsterId = currentBattleMonsterId;
	}
	
	public void performBattlePrep () {
		generateBattleMonster();
		//monster stats
		for (Map<String,Object> monster : playerMonsterTeam) {
			if (monster.get("id").equals(getCurrentBattleMonsterId())){
				System.out.println("A random " + monster.get("name") + " appears!");
				System.out.println("Nr. " + monster.get("id")
							+ " | Name: " + monster.get("name")
							+ " | HP: " + monster.get("hp")
							//+ " | Attack: " + monster.get("attack") 
							//+ " | Speed: " + monster.get("speed") 
							//+ " | Height: " + monster.get("height")
							//+ " | Weight: " + monster.get("weight")
							//+ " | Type1: " + monster.get("type1")
							//+ " | Type2: " + monster.get("type2")
							);
			}
		}
		 controller.gameMenuBattle();
	}
	
	public void performBattle () {
		
		performBattlePrep();
	}
	
	public void performBattleAttack (double battleMonster) {
		
	}
	
	
	
	
	
	
	
}
