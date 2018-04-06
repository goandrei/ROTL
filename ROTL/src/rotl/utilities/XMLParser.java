package rotl.utilities;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import rotl.entities.SoldiersInfo;
import rotl.entities.SoldiersInfo.S_Info;
import rotl.entities.SoldierType;

public class XMLParser {
	
	private static S_Info getSoldierInfo(Element node) {
		
		S_Info info = new S_Info();
		
		try {
			
			Element buy = (Element) node.getElementsByTagName("buy").item(0);
			Element upgrade = (Element) node.getElementsByTagName("upgrade").item(0);
			
			/** Buy **/
			int blife = Integer.parseInt(buy.getElementsByTagName("life").item(0).getTextContent().trim());
			int barmor = Integer.parseInt(buy.getElementsByTagName("armor").item(0).getTextContent().trim());
			int battack = Integer.parseInt(buy.getElementsByTagName("attack").item(0).getTextContent().trim());
			int bgold = Integer.parseInt(buy.getElementsByTagName("gold").item(0).getTextContent().trim());
			int bmiss = Integer.parseInt(buy.getElementsByTagName("miss").item(0).getTextContent().trim());
			int bdodge = Integer.parseInt(buy.getElementsByTagName("dodge").item(0).getTextContent().trim());
			
			info.setBLife(blife);
			info.setBArmor(barmor);
			info.setBAttack(battack);
			info.setBGold(bgold);
			info.setBMiss(bmiss);
			info.setBDodge(bdodge);
			
			/** Upgrade **/
			double ulife = Double.parseDouble(upgrade.getElementsByTagName("life").item(0).getTextContent().trim());
			double uarmor = Double.parseDouble(upgrade.getElementsByTagName("armor").item(0).getTextContent().trim());
			double uattack = Double.parseDouble(upgrade.getElementsByTagName("attack").item(0).getTextContent().trim());
			double ugold = Double.parseDouble(upgrade.getElementsByTagName("gold").item(0).getTextContent().trim());
			double umiss = Double.parseDouble(upgrade.getElementsByTagName("miss").item(0).getTextContent().trim());
			double udodge = Double.parseDouble(upgrade.getElementsByTagName("dodge").item(0).getTextContent().trim());
			
			info.setULife(ulife);
			info.setUArmor(uarmor);
			info.setUAttack(uattack);
			info.setUGold(ugold);
			info.setUMiss(umiss);
			info.setUDodge(udodge);
			
		} catch (Exception ex) {
			
			System.err.println("Wrong Soldiers XML format !!!");
			ex.printStackTrace();
			return null;
		}
		
		return info;
	}
	
	private static void parseTypes(Node sTypes) {
		
		NodeList list = sTypes.getChildNodes();
		SoldiersInfo sInfo = SoldiersInfo.getInstance();
		
		for (int i = 0; i < list.getLength(); i++) {
	        	
	        Node node = list.item(i);
	       
	        if (node.getNodeType() == Node.ELEMENT_NODE) {
	        		
	        	S_Info info = getSoldierInfo((Element) node);
	        		
	        	if (info != null) {
	        			
		        	switch (node.getNodeName()) {
		        		
			        	case "defender":
			        		sInfo.addSoldierInfo(SoldierType.DEFENDER, info);
			        		break;
			        	case "fighter":
			        		sInfo.addSoldierInfo(SoldierType.FIGHTER, info);
			        		break;
			        	case "warrior":
			        		sInfo.addSoldierInfo(SoldierType.WARRIOR, info);
			        		break;
			        	default:
			        		break;
		        	}
	        	}
	        }
	    }
	}
	
	public static void parseSoldiersInfo(String path) {
		
		try {
			 
			File inputFile = new File(path);
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(inputFile);
	        doc.getDocumentElement().normalize();
	         
	        Element root = doc.getDocumentElement();
	        NodeList list = root.getChildNodes();
	        
	        for (int i = 0; i < list.getLength(); i++) {
	        	
	        	Node node = list.item(i);
	       
	        	if (node.getNodeType() == Node.ELEMENT_NODE) {
	        		
	        		switch (node.getNodeName()) {
	        		
		        		case "types":
		        			parseTypes(node);
		        			break;
		        		default:
		        			break;
	        		}
	        	}
	        }
	         
		} catch (Exception ex) {
			 
			System.err.println("Soldiers XML parsing error !!!");
			ex.printStackTrace();
		}
	}
}
