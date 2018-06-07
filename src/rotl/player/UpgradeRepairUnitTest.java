package rotl.player;

import rotl.entities.GameEntity;
import rotl.entities.Soldier;
import rotl.entities.SoldierFactory;
import rotl.entities.SoldierType;
import rotl.utilities.XMLParser;

public class UpgradeRepairUnitTest {

	public static void main(String[] args) {

		final String soldiersPath = "resources\\entities_info\\soldiers.xml";		
		XMLParser.parseSoldiersInfo(soldiersPath);
		
		final SoldierFactory sf = new SoldierFactory();
		final Soldier s = sf.getSoldier(SoldierType.FIGHTER);
	
		System.out.println("Soldier");
		System.out.println();
		System.out.println("Before");
		System.out.println("Life: " + s.getLife());
		System.out.println("Armor: " + s.getArmor());
		System.out.println("Attack: " + s.getAttack());
		System.out.println("MissRate: " + s.getMissRate());
		System.out.println("DodgeRate: " + s.getDodgeRate());
		System.out.println("CriticalRate: " + s.getCriticalRate());
		System.out.println("Level: " + s.getLevel());

		for (int i = 1; i <= GameEntity.MAX_LEVEL - 1; i++)
			UpgradeUnit.upgradeEntity(s, UnitOp.DO);

		System.out.println();
		System.out.println("After");
		System.out.println("Life: " + s.getLife());
		System.out.println("Armor: " + s.getArmor());
		System.out.println("Attack: " + s.getAttack());
		System.out.println("MissRate: " + s.getMissRate());
		System.out.println("DodgeRate: " + s.getDodgeRate());
		System.out.println("CriticalRate: " + s.getCriticalRate());
		System.out.println("Level: " + s.getLevel());

		int goldLife = RepairUnit.healSoldier(s, UnitOp.FEE);
		int goldArmor = RepairUnit.repairArmor(s, UnitOp.FEE);

		System.out.println();
		System.out.println("Gold life heal : " + goldLife);
		System.out.println("Gold armor repair: " + goldArmor);
		System.out.println();
	}
}
