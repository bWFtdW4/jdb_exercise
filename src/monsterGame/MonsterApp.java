package monsterGame;

import java.io.IOException;

public class MonsterApp {

	public static void main (String[] args) throws IOException {
		final MonsterController controller = new MonsterController();
		controller.eventSource().execute();
	}

}
