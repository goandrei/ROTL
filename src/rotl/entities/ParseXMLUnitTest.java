package rotl.entities;

import rotl.utilities.XMLParser;

public class ParseXMLUnitTest {

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();

		String soldiersPath = "resources\\entities_info\\soldiers.xml";
		XMLParser.parseSoldiersInfo(soldiersPath);
		
		SoldiersInfo sInfo = SoldiersInfo.getInstance();

		for (SoldierType type : SoldierType.values()) {

			SoldiersInfo.S_Info s_info = sInfo.getSoldierInfo(type);

			System.out.println(type);
			System.out.println("Buy: ");
			System.out.println(s_info.getBLife());
			System.out.println(s_info.getBArmor());
			System.out.println(s_info.getBAttack());
			System.out.println(s_info.getBGold());
			System.out.println(s_info.getBMiss());
			System.out.println(s_info.getBDodge());
			System.out.println(s_info.getBCritical());
			System.out.println("Upgrade: ");
			System.out.println(s_info.getULife());
			System.out.println(s_info.getUArmor());
			System.out.println(s_info.getUAttack());
			System.out.println(s_info.getUGold());
			System.out.println(s_info.getUMiss());
			System.out.println(s_info.getUDodge());
			System.out.println(s_info.getUCritical());
		}

		long endTime = System.currentTimeMillis();
		System.out.println("Time: " + (endTime - startTime));
	}
	
}
