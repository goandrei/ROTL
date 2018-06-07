package rotl.player;

import rotl.entities.Defender;
import rotl.entities.EntitiesException;
import rotl.entities.Fighter;
import rotl.entities.Soldier;
import rotl.entities.SoldierType;
import rotl.entities.SoldiersInfo;
import rotl.entities.SoldiersInfo.S_Info;
import rotl.entities.Warrior;

final class RepairUnit implements GameUnit {

	private RepairUnit() {
	}

	static int healSoldier(Soldier soldier, UnitOp unit) {

		try {

			if (unit == null)
				unit = UnitOp.FEE;

			if (soldier == null)
				throw new EntitiesException("Invalid soldier !");

			SoldiersInfo sInfo = SoldiersInfo.getInstance();
			S_Info info = null;

			if (soldier instanceof Defender)
				info = sInfo.getSoldierInfo(SoldierType.DEFENDER);
			else if (soldier instanceof Fighter)
				info = sInfo.getSoldierInfo(SoldierType.FIGHTER);
			else if (soldier instanceof Warrior)
				info = sInfo.getSoldierInfo(SoldierType.WARRIOR);

			if (info == null)
				throw new EntitiesException("No soldier info loaded !");

			int blife = info.getBLife();
			double ulife = info.getULife();

			int bgold = info.getBGold();
			double ugold = info.getUGold();

			int level = soldier.getLevel();

			if ((blife == SoldiersInfo.ERROR_CODE) || (ulife == SoldiersInfo.ERROR_CODE)
					|| (bgold == SoldiersInfo.ERROR_CODE) || (ugold == SoldiersInfo.ERROR_CODE))
				throw new EntitiesException("Invalid soldier info !!");

			double lifeValue = blife * GameUnit.pow(ulife, level - 1);
			int maxLife = ((lifeValue > Integer.MAX_VALUE * 1.0) ? Integer.MAX_VALUE : (int) lifeValue);

			double goldValue = bgold * GameUnit.pow(ugold, level - 1);
			int maxGold = ((goldValue > Integer.MAX_VALUE * 1.0) ? Integer.MAX_VALUE : (int) goldValue);

			switch (unit) {

			case FEE:
				return maxGold / 6;
			case DO:
				soldier.setLife(maxLife);
				return 0;
			default:
				return 0;
			}

		} catch (Exception ex) {

			ex.printStackTrace();
			return 0;
		}
	}

	static int repairArmor(Soldier soldier, UnitOp unit) {

		try {

			if (unit == null)
				unit = UnitOp.FEE;

			if (soldier == null)
				throw new EntitiesException("Invalid soldier !");

			SoldiersInfo sInfo = SoldiersInfo.getInstance();
			S_Info info = null;

			if (soldier instanceof Defender)
				info = sInfo.getSoldierInfo(SoldierType.DEFENDER);
			else if (soldier instanceof Fighter)
				info = sInfo.getSoldierInfo(SoldierType.FIGHTER);
			else if (soldier instanceof Warrior)
				info = sInfo.getSoldierInfo(SoldierType.WARRIOR);

			if (info == null)
				throw new EntitiesException("No soldier info loaded !");

			int barmor = info.getBArmor();
			double uarmor = info.getUArmor();

			int bgold = info.getBGold();
			double ugold = info.getUGold();

			int level = soldier.getLevel();

			if ((barmor == SoldiersInfo.ERROR_CODE) || (uarmor == SoldiersInfo.ERROR_CODE)
					|| (bgold == SoldiersInfo.ERROR_CODE) || (ugold == SoldiersInfo.ERROR_CODE))
				throw new EntitiesException("Invalid soldier info !!");

			double armorValue = barmor * GameUnit.pow(uarmor, level - 1);
			int maxArmor = ((armorValue > Integer.MAX_VALUE * 1.0) ? Integer.MAX_VALUE : (int) armorValue);

			double goldValue = bgold * GameUnit.pow(ugold, level - 1);
			int maxGold = ((goldValue > Integer.MAX_VALUE * 1.0) ? Integer.MAX_VALUE : (int) goldValue);

			switch (unit) {

			case FEE:
				return maxGold / 6;
			case DO:
				soldier.setArmor(maxArmor);
				return 0;
			default:
				return 0;
			}

		} catch (Exception ex) {

			ex.printStackTrace();
			return 0;
		}
	}
}
