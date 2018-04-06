package rotl.entities;

import java.util.Map;
import java.util.HashMap;

public class SoldiersInfo {
	
	public static final int ERROR_CODE = -1;
	
	public static class S_Info {
		
		private Map<String, Integer> buy = new HashMap<>();
		private Map<String, Double> upgrade = new HashMap<>();
		
		/** Getters **/
		
		/** Life **/
		public int getBLife() {
			
			Integer life = buy.get("life");
			
			if (life != null)
				return life;
			
			return ERROR_CODE;
		}
		
		public double getULife() {
			
			Double life = upgrade.get("life");
			
			if (life != null)
				return life;
			
			return ERROR_CODE;
		}
		
		/** Armor **/
		public int getBArmor() {
			
			Integer armor = buy.get("armor");
			
			if (armor != null)
				return armor;
			
			return ERROR_CODE;
		}
		
		public double getUArmor() {
			
			Double armor = upgrade.get("armor");
			
			if (armor != null)
				return armor;
			
			return ERROR_CODE;
		}
		
		/** Attack **/
		public int getBAttack() {
			
			Integer attack = buy.get("attack");
			
			if (attack != null)
				return attack;
			
			return ERROR_CODE;
		}
		
		public double getUAttack() {
			
			Double attack = upgrade.get("attack");
			
			if (attack != null)
				return attack;
			
			return ERROR_CODE;
		}
		
		/** Gold **/
		public int getBGold() {
			
			Integer gold = buy.get("gold");
			
			if (gold != null)
				return gold;
			
			return ERROR_CODE;
		}
		
		public double getUGold() {
			
			Double gold = upgrade.get("gold");
			
			if (gold != null)
				return gold;
			
			return ERROR_CODE;
		}
		
		
		/** Miss Rate **/
		public int getBMiss() {
			
			Integer missRate = buy.get("miss");
			
			if (missRate != null)
				return missRate;
			
			return ERROR_CODE;
		}
		
		public double getUMiss() {
			
			Double missRate = upgrade.get("miss");
			
			if (missRate != null)
				return missRate;
			
			return ERROR_CODE;
		}
		
		/** Dodge Rate **/
		public int getBDodge() {
			
			Integer dodgeRate = buy.get("dodge");
			
			if (dodgeRate != null)
				return dodgeRate;
			
			return ERROR_CODE;
		}
		
		public double getUDodge() {
			
			Double dodgeRate = upgrade.get("dodge");
			
			if (dodgeRate != null)
				return dodgeRate;
			
			return ERROR_CODE;
		}
		
		/** Critical Rate **/
		public int getBCritical() {
			
			Integer criticalRate = buy.get("critical");
			
			if (criticalRate != null)
				return criticalRate;
			
			return ERROR_CODE;
		}
		
		public double getUCritical() {
			
			Double criticalRate = upgrade.get("critical");
			
			if (criticalRate != null)
				return criticalRate;
			
			return ERROR_CODE;
		}
		
		/** Setters **/
		
		/** Life **/
		public void setBLife(int life) {
			
			life = Integer.max(life, 0);
			buy.put("life", life);
		}
		
		public void setULife(double life) {
			
			life = Double.max(life, 0);
			upgrade.put("life", life);
		}
		
		/** Armor **/
		public void setBArmor(int armor) {
			
			armor = Integer.max(armor, 0);
			buy.put("armor", armor);
		}
		
		public void setUArmor(double armor) {
			
			armor = Double.max(armor, 0);
			upgrade.put("armor", armor);
		}
		
		/** Attack **/
		public void setBAttack(int attack) {
			
			attack = Integer.max(attack, 0);
			buy.put("attack", attack);
		}
		
		public void setUAttack(double attack) {
			
			attack = Double.max(attack, 0);
			upgrade.put("attack", attack);
		}
		
		/** Gold **/
		public void setBGold(int gold) {
			
			gold = Integer.max(gold, 0);
			buy.put("gold", gold);
		}
		
		public void setUGold(double gold) {
			
			gold = Double.max(gold, 0);
			upgrade.put("gold", gold);
		}
		
		
		/** Miss Rate **/
		public void setBMiss(int missRate) {
			
			missRate = Integer.max(missRate, 0);
			missRate = Integer.min(missRate, 100);
			buy.put("miss", missRate);
		}
		
		public void setUMiss(double missRate) {
			
			missRate = Double.max(missRate, 0);
			missRate = Double.min(missRate, 100);
			upgrade.put("miss", missRate);
		}
		
		/** Dodge Rate **/
		public void setBDodge(int dodgeRate) {
			
			dodgeRate = Integer.max(dodgeRate, 0);
			dodgeRate = Integer.min(dodgeRate, 100);
			buy.put("dodge", dodgeRate);
		}
		
		public void setUDodge(double dodgeRate) {
			
			dodgeRate = Double.max(dodgeRate, 0);
			dodgeRate = Double.min(dodgeRate, 100);
			upgrade.put("dodge", dodgeRate);
		}
		
		/** Critical Rate **/
		public void setBCritical(int criticalRate) {
			
			criticalRate = Integer.max(criticalRate, 0);
			criticalRate = Integer.min(criticalRate, 100);
			buy.put("critical", criticalRate);
		}
		
		public void setUCritical(double criticalRate) {
			
			criticalRate = Double.max(criticalRate, 0);
			criticalRate = Double.min(criticalRate, 100);
			upgrade.put("critical", criticalRate);
		}
	}
	
	private Map<SoldierType, S_Info> soldierInfo = new HashMap<>();
	private static SoldiersInfo _soldiersInfo = null;
	
	private SoldiersInfo() {}
	
	public static SoldiersInfo getInstance() {
		
		if (_soldiersInfo == null)
			_soldiersInfo = new SoldiersInfo();
		
		return _soldiersInfo;
	}
	
	public S_Info getSoldierInfo(SoldierType type) {
		
		return soldierInfo.get(type);
	}
	
	public void addSoldierInfo(SoldierType type, S_Info info) {
		
		if (info != null)
			soldierInfo.put(type, info);
	}
}
