package rotl.player;

import java.util.ArrayList;
import java.util.List;

import rotl.entities.Defender;
import rotl.entities.EntitiesException;
import rotl.entities.Fighter;
import rotl.entities.GameEntity;
import rotl.entities.Soldier;
import rotl.entities.SoldierFactory;
import rotl.entities.SoldierType;
import rotl.entities.Warrior;
import rotl.simulate.SoldierInfoArena;
import rotl.statusBar.StatusBar;

public final class Player {

	private static final int GOLD = 100000000;
	private static Player _player = null;

	private Player() {
		this.gold = GOLD;
	}

	public static Player getInstance() {

		if (_player == null)
			_player = new Player();

		return _player;
	}
	
	private int gold;
	private final SoldierFactory soldierFactory = new SoldierFactory();
	private final List<Soldier> soldiers = new ArrayList<>();
	
	public int getNumberOfSoldiers() {
		
		return soldiers.size();
	}
	
	public SoldierInfoArena getSoldierInfo(int index) {
		
		if (index < 0 || index >= soldiers.size())
			throw new IllegalArgumentException();
		
		final Soldier soldier = soldiers.get(index);
		SoldierType soldierType = null;
		
		if (soldier instanceof Fighter)
			soldierType = SoldierType.FIGHTER;
		else if (soldier instanceof Defender)
			soldierType = SoldierType.DEFENDER;
		else if (soldier instanceof Warrior)
			soldierType = SoldierType.WARRIOR;
		
		final SoldierInfoArena.Builder builder = SoldierInfoArena.builder();
		
		builder.withLife(soldier.getLife())
				.withArmor(soldier.getArmor())
				.withAttack(soldier.getAttack())
				.withGold(UpgradeUnit.upgradeEntity(soldier, UnitOp.FEE))
				.withSoldierType(soldierType);
		
		return builder.build();
	}
	
	public GameEntity getFighter(int index) {
		
		if (index < 0 || index >= soldiers.size())
			throw new IllegalArgumentException();
		
		final Soldier soldier = soldiers.get(index);
		return soldier;
	}
	
	public int addSoldier(SoldierType soldierType) {
		
		try {
			
			final Soldier soldier = this.soldierFactory.getSoldier(soldierType);
			soldiers.add(soldier);
			
			final StatusBar statusBar = StatusBar.getInstanceNonAbusive();
			
			if (statusBar != null) {
				
				switch (soldierType) {
				
					case FIGHTER:
						statusBar.setNumberFighters(statusBar.getNumberFighters() + 1);
						break;
					case DEFENDER:
						statusBar.setNumberDefenders(statusBar.getNumberDefenders() + 1);
						break;
					case WARRIOR:
						statusBar.setNumberWarriors(statusBar.getNumberWarriors() + 1);
						break;
				}
			}
			
			return soldiers.size() - 1;
		
		} catch (EntitiesException ex) {
			
			ex.printStackTrace();
			return -1;
		}
	}
	
	public void removeSoldier(int index) {
		
		if (index < 0 || index >= soldiers.size())
			throw new IllegalArgumentException();
		
		final Soldier soldier = soldiers.get(index);
		SoldierType soldierType = null;
		
		if (soldier instanceof Fighter)
			soldierType = SoldierType.FIGHTER;
		else if (soldier instanceof Defender)
			soldierType = SoldierType.DEFENDER;
		else if (soldier instanceof Warrior)
			soldierType = SoldierType.WARRIOR;
		
		soldiers.remove(index);
		
		final StatusBar statusBar = StatusBar.getInstanceNonAbusive();
		
		if (statusBar != null) {
			
			switch (soldierType) {
			
				case FIGHTER:
					statusBar.setNumberFighters(statusBar.getNumberFighters() - 1);
					break;
				case DEFENDER:
					statusBar.setNumberDefenders(statusBar.getNumberDefenders() - 1);
					break;
				case WARRIOR:
					statusBar.setNumberWarriors(statusBar.getNumberWarriors() - 1);
					break;
			}
		}
	}
	
	public void upgradeSoldier(int index) {
		
		if (index < 0 || index >= soldiers.size())
			throw new IllegalArgumentException();
		
		final Soldier soldier = soldiers.get(index);
		
		/** Pay money for upgrade **/
		
		int upgradeGold = UpgradeUnit.upgradeEntity(soldier, UnitOp.FEE);
		
		if (this.gold >= upgradeGold) {
			int pastStatus = soldier.getLife();
			UpgradeUnit.upgradeEntity(soldier, UnitOp.DO);
			if(pastStatus != soldier.getLife())
				this.setGold(this.gold - upgradeGold);
		}
	}
	
	public void healSoldier(int index) {
		
		if (index < 0 || index >= soldiers.size())
			throw new IllegalArgumentException();
		
		final Soldier soldier = soldiers.get(index);
		
		/** Pay money for upgrade **/
		
		int healGold = RepairUnit.healSoldier(soldier, UnitOp.FEE);
		
		if (this.gold >= healGold) {
			
			int pastHeal = soldier.getLife();
			RepairUnit.healSoldier(soldier, UnitOp.DO);
			if(pastHeal != soldier.getLife())
				this.setGold(this.gold - healGold);
		}
	}
	
	public void repairArmorSoldier(int index) {
		
		if (index < 0 || index >= soldiers.size())
			throw new IllegalArgumentException();
		
		final Soldier soldier = soldiers.get(index);
		
		/** Pay money for upgrade **/
		
		int repairGold = RepairUnit.repairArmor(soldier, UnitOp.FEE);
		
		if (this.gold >= repairGold) {
			
			int pastArmour = soldier.getArmor();
			RepairUnit.repairArmor(soldier, UnitOp.DO);
			if(pastArmour != soldier.getArmor())
				this.setGold(this.gold - repairGold);
		}
	}
	
	/** Getters **/

	public int getGold() {

		return this.gold;
	}

	/** Setters **/

	public void setGold(int _gold) {

		_gold = Integer.max(_gold, 0);
		this.gold = _gold;
		
		final StatusBar statusBar = StatusBar.getInstanceNonAbusive();
		
		if (statusBar != null) {
			statusBar.setGold(this.gold);
		}
	}
}
