package rotl.simulate;

import rotl.entities.GameEntity;
import rotl.player.Player;
import java.util.Random;

final class CombatUnit implements Runnable {
	
	private final GameEntity firstFighter;
	private final GameEntity secondFighter;
	private final Random turn = new Random();
	private static final int TIME_TO_SLEEP = 250;
	
	CombatUnit(int firstFighter, int secondFighter) {
		
		final Player player = Player.getInstance();
		this.firstFighter = player.getFighter(firstFighter);
		this.secondFighter = player.getFighter(secondFighter);
	}
	
	@Override
	public void run() {
		
		while (!firstFighter.isDead() && !secondFighter.isDead()) {
			
			if (Thread.interrupted())
				break;
			
			final int firstDamage = firstFighter.dealDamage();
			final int secondDamage = secondFighter.dealDamage();
			final int whoAttacks = turn.nextInt(2);
			
			switch (whoAttacks) {
			
				case 0:
					secondFighter.takeDamage(firstDamage);
					break;
				case 1:
					firstFighter.takeDamage(secondDamage);
					break;
			}
			
			/** Sleep time **/
			try {
				
				Thread.sleep(TIME_TO_SLEEP);
			
			} catch (InterruptedException ex) {
				break;
			}
			
			/** Update UI **/
			final Fight fight = Fight.getInstanceNonAbusive();
			
			if (fight != null) {
				
				fight.updateFightersInfo();
			}
		}
	}
}
