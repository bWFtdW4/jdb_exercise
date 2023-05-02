package monster;

import java.io.IOException;

public class MonsterApp {

	public static void main (String[] args) throws IOException {
		final MonsterController controller = new MonsterController();
		//System.out.println("load C:\\Java\\M\\data\\monsteList2Full");
		controller.eventSource().execute();
		
		
	}

}
