package rotl.simulate;

import rotl.entities.GameEntity;
import rotl.player.Player;

public class CombatUnit implements Runnable {
	
	final GameEntity first;
	final GameEntity second;
	
	public CombatUnit(int firstFighter, int secondFighter) {
		
		first = Player.getInstance().getFighter(firstFighter);
		second = Player.getInstance().getFighter(secondFighter);
	}
	
	@Override
	public void run() {
		
		while (!first.isDead() && !second.isDead()) {
			
			int firstDamage = first.dealDamage();
			int secondDamage = second.dealDamage();
			
			if (Thread.interrupted())
				break;
		}
	}
}
