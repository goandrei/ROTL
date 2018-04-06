package rotl.entities;

import java.util.Map;
import java.util.HashMap;

public class TowersInfo {
	
	public static final int ERROR_CODE = -1;
	
	private Map<String, Integer> buy = new HashMap<>();
	private Map<String, Double> upgrade = new HashMap<>();
	
	/** Getters **/
	
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
	
	/** Setters **/
	
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
	
	private static TowersInfo _towersInfo = null;
	
	private TowersInfo() {}
	
	public static TowersInfo getInstance() {
		
		if (_towersInfo == null)
			_towersInfo = new TowersInfo();
		
		return _towersInfo;
	}
}
