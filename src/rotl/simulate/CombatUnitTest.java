package rotl.simulate;

import rotl.entities.GameEntity;
import rotl.entities.SoldierType;
import rotl.utilities.XMLParser;
import rotl.player.Player;

public class CombatUnitTest {

	public static void main(String[] args) throws InterruptedException {
		
		final String soldiersPath = "resources\\entities_info\\soldiers.xml";		
		XMLParser.parseSoldiersInfo(soldiersPath);
		
		final Player player = Player.getInstance();
		final int s1Indx = player.addSoldier(SoldierType.FIGHTER);
		final int s2Indx = player.addSoldier(SoldierType.DEFENDER);
		
		final Thread combat = new Thread(new CombatUnit(s1Indx, s2Indx), "Combat");

		combat.start();
		
		combat.join();
		
		final GameEntity s1 = player.getFighter(s1Indx);
		final GameEntity s2 = player.getFighter(s2Indx);
		
		if (s1.isDead() && s2.isDead())
			System.out.println("Both died !");
		else if (s1.isDead())
			System.out.println("S2 wins");
		else if (s2.isDead())
			System.out.println("S1 wins");
	}

}
