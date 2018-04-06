package rotl.entities;

import rotl.utilities.XMLParser;

public class TestEntities {

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		String path = "resources\\soldiers_info\\soldiers.xml";
		XMLParser.parseSoldiersInfo(path);
	
		SoldierFactory sf = new SoldierFactory();
		
		Soldier s1 = sf.getSoldier(SoldierType.WARRIOR);
		Soldier s2 = sf.getSoldier(SoldierType.WARRIOR);
			
		while (!s1.isDead() && !s2.isDead()) {
			
			int d1 = s1.dealDamage();
			int d2 = s2.dealDamage();
			
			s1.takeDamage(d2);
			s2.takeDamage(d1);
		}
		
		if (!s1.isDead())
			System.out.println("S1 wins !");
		else if (!s2.isDead())
			System.out.println("S2 wins !");
		else
			System.out.print("It's equal");
		
		System.out.println("Battle results: ");
		System.out.println("S1: ");
		System.out.println("Life: " + s1.getLife());
		System.out.println("Armor: " + s1.getArmor());
		System.out.println();
		System.out.println("S2: ");
		System.out.println("Life: " + s2.getLife());
		System.out.println("Armor: " + s2.getArmor());
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("Time: " + (endTime - startTime));
		
	}

}
