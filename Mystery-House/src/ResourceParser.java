import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.newdawn.slick.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ResourceParser 
{
	private String manifestPath;
	private ArrayList<String> roomPaths;
	private ArrayList<String> objectPaths;
	private ArrayList characterPath;
	private Document xmlManifest;
	
	DocumentBuilderFactory dbFactory;
	
	Map<String, Sprite> spriteCache;
	Map<String, GameRoom> roomCache;
	Sprite characterCache;
	
	
	public ResourceParser(String newPath)
	{
		manifestPath = newPath;
		roomPaths = new ArrayList<String>();
		objectPaths = new ArrayList<String>();
		dbFactory = DocumentBuilderFactory.newInstance();
		ParseManifest();
	}
	
	public void setResource(String newPath){manifestPath = newPath;}
	public String getResource(){return manifestPath;}
	
	public void ParseManifest()
	{
		//set up the internal document
		xmlManifest = getDocument(new File("rsc/xml/GameManifest.xml"));
		
		//parse out the other resource files
		System.out.println("retrieving map file paths");
		objectPaths = parseElements("roomPaths", xmlManifest);
		
		System.out.println("retrieving map object paths");
		objectPaths = parseElements("sprites", xmlManifest);
		
		//get the room name to launch to
		System.out.println("retrieving the launch map name");
		parseLaunchName();
		
		//parse subfiles
		parseRooms();
		parseCharacter();
		parseSprites();
	}
	

	public Sprite getCachedSprite(){return characterCache;}
	public Sprite parseCharacter() 
	{
		Document xmlCharacter;

		return characterCache;
	}

	public String parseLaunchName()
	{
		return null;
	}
	
	public Map<String, GameRoom> getCachedRooms(){return roomCache;}
	public void parseRooms()
	{
		Document xmlRooms;
		System.out.println("retrieving rooms");		
	}

	public Map<String, Sprite> getCachedSprites(){return spriteCache;}
	public void parseSprites()
	{
		Document xmlSprites;
		System.out.println("retrieving sprites");
		
	}
	

//-------------------------------------------------------------------------------------------------------------------------------
//-- UTILITIES
//-------------------------------------------------------------------------------------------------------------------------------
	    
	private ArrayList<String> parseElements(String elementTag, Document doc)
	{
		ArrayList<String> elements = new ArrayList<String>();
		NodeList elemenNodes = doc.getElementsByTagName(elementTag);
		for(int i = 0; i < elemenNodes.getLength(); i++)
		{
			Node elementNode = elemenNodes.item(i);
			System.out.println("Current Element: "+elementNode.getNodeName());
			if(elementNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element roomElement = (Element)elementNode;
				
				System.out.println("adding path: "+roomElement.getElementsByTagName("path").item(0).getTextContent());
				elements.add(roomElement.getElementsByTagName("path").item(0).getTextContent());
			}
		}
		return elements;		
	}
	private Document getDocument(File file)
	{
		Document doc = null;
		try 
		{
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(file);
		} 
		catch (SAXException | IOException |ParserConfigurationException e)
		{
			e.printStackTrace();
			return null;
		} 
		return doc;
	}
	
}
