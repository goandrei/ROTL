package rotl.entities;

public enum SoldierType {

	FIGHTER, DEFENDER, WARRIOR;
	
	public String getSoldierName() {
		
		String name = this.toString();
		
		name = name.toLowerCase();
		
		if (name.length() > 0)
			name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
		
		return name;
	}
}
