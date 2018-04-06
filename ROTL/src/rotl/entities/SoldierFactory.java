package rotl.entities;

import rotl.entities.SoldiersInfo.S_Info;

public class SoldierFactory {
	
	public Soldier getSoldier(SoldierType type) {
		
		if (type == null)
			return null;
		
		SoldiersInfo sInfo = SoldiersInfo.getInstance();
		S_Info info = sInfo.getSoldierInfo(type);
		
		if (info == null)
			return null;
		
		int life = info.getBLife();
		int armor = info.getBArmor();
		int attack = info.getBAttack();
		int missRate = info.getBMiss();
		int dodgeRate = info.getBDodge();
		
		switch (type) {
		
			case DEFENDER:
				return new Defender(life, armor, attack, missRate, dodgeRate);
			case FIGHTER:
				return new Fighter(life, armor, attack, missRate, dodgeRate);
			case WARRIOR:
				return new Warrior(life, armor, attack, missRate, dodgeRate);
			default:
				return null;
		}
	}
}
