package rotl.utilities;

import java.io.File;
import java.util.Base64;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLLoader {
	
	private final int MAP_WIDTH = 1000, MAP_HEIGHT = 1000, NO_OF_LAYERS = 5;
	
	public int[][][] loadXMLMaps(String path){
		
		int[][][] layers = new int[MAP_HEIGHT][MAP_WIDTH][NO_OF_LAYERS];
		
		try {
	         File inputFile = new File(path);
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse("resources" + inputFile);
	         doc.getDocumentElement().normalize();
	         NodeList nList = doc.getElementsByTagName("layer");
	         
	         int length = nList.getLength();
	         
	         for(int i = 0;i < 1; ++i) {
	             Node nNode = nList.item(i);
	             
	             if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement = (Element) nNode; 
	                String parts[] = (eElement.getElementsByTagName("data").item(0).getTextContent()).split(",");
	                
	                for(int j = 0;j < MAP_HEIGHT; ++j) {
	                	for(int k = 0;k < MAP_WIDTH; ++k) {
	                		if((parts[j * MAP_WIDTH + k]).trim().equals("2147483825")) {
	                			layers[j][k][i] = 175;
	                		}else if((parts[j * MAP_WIDTH + k]).trim().equals("1073742020")) {
	                			layers[j][k][i] = 171;
	                		}else if((parts[j * MAP_WIDTH + k]).trim().equals("1073742004")) {
	                			layers[j][k][i] = 170;
	                		}else if(((parts[j * MAP_WIDTH + k]).trim()).length() > 3) {
	                			layers[j][k][i] = 205;
	                		}else {
	                			layers[j][k][i] = Integer.parseInt((parts[j * MAP_WIDTH + k]).trim());
	                		}
	                		
                		}
	                }
                }
	         }
	         
	         return layers;
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		
		return layers;
	}
}
