import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
	private String launchRoom;
	private ArrayList<String> roomPaths;
	private ArrayList<String> spritePaths;
	private Document xmlManifest;
	
	DocumentBuilderFactory dbFactory;
	ObjectFactory objFactory;
	
	Map<String, Sprite> spriteCache;
	Map<String, GameRoom> roomCache;
	Sprite characterCache;
	
	
	public ResourceParser(String newPath)
	{
		manifestPath = newPath;
		roomPaths = new ArrayList<String>();
		spritePaths = new ArrayList<String>();
		
		dbFactory = DocumentBuilderFactory.newInstance();
		objFactory = new ObjectFactory();
		
		spriteCache = new HashMap<String, Sprite>();
		roomCache = new HashMap <String, GameRoom>();
		
		ParseManifest();
	}
	
	public void setResource(String newPath){manifestPath = newPath;}
	public String getResource(){return manifestPath;}
	
	public void ParseManifest()
	{
		//set up the internal document
		xmlManifest = getDocument(new File("rsc/xml/GameManifest.xml"));
		
		//parse out the other resource files
		System.out.println("\nretrieving map file paths");
		roomPaths = parsePaths("rooms", xmlManifest);
		
		System.out.println("\nretrieving map object paths");
		spritePaths = parsePaths("sprites", xmlManifest);
		
		//get the room name to launch to
		System.out.println("\nretrieving the launch map name");

		ArrayList<Element> launchList = parseElements("launchroom", xmlManifest);
		if(launchList.size()==1)launchRoom = launchList.get(0).getTextContent();
		else System.out.print("WARNING: could not parse a room to launch to");
		
		//parse subfiles
		parseRooms();
		parseSprites();
	}
	

	public Map<String, GameRoom> getCachedRooms(){return roomCache;}
	public void parseRooms()
	{
		System.out.println("\nretrieving rooms");	
		//parse out each room file
		Document xmlRooms;	
		for(int i = 0; i < roomPaths.size(); i ++)
		{
			xmlRooms = getDocument(new File(roomPaths.get(i)));
			ArrayList<Element> roomElements = parseElements("room", xmlRooms);
			
			for(int j = 0; j < roomElements.size(); j++)
			{
				parseRoomFromElement(roomElements.get(j));
			}
		}
	}

	public Sprite getCachedSprite(){return characterCache;}	
	public Map<String, Sprite> getCachedSprites(){return spriteCache;}
	public void parseSprites()
	{
		System.out.println("\nretrieving sprites");	
		//parse out each room file
		Document xmlSprites;	
		for(int i = 0; i < spritePaths.size(); i ++)
		{
			xmlSprites = getDocument(new File(spritePaths.get(i)));
			ArrayList<Element> spriteElements = parseElements("sprite", xmlSprites);
			
		}
	}
	

//-------------------------------------------------------------------------------------------------------------------------------
//-- UTILITIES
//-------------------------------------------------------------------------------------------------------------------------------
	    
	private ArrayList<String> parsePaths(String pathTag, Document doc)
	{
		ArrayList<String> paths = new ArrayList<String>();
		NodeList elemenNodes = doc.getElementsByTagName(pathTag);
		
		for(int i = 0; i < elemenNodes.getLength(); i++)
		{
			Node elementNode = elemenNodes.item(i);
			if(elementNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element element = (Element)elementNode;
				
				System.out.println("Parsing path: "+element.getElementsByTagName("path").item(0).getTextContent());
				paths.add(element.getElementsByTagName("path").item(0).getTextContent());
			}
		}
		return paths;		
	}
    
	private ArrayList<Element> parseElements(String elementTag, Document doc)
	{
		ArrayList<Element> elements = new ArrayList<Element>();
		NodeList elemenNodes = doc.getElementsByTagName(elementTag);
		
		for(int i = 0; i < elemenNodes.getLength(); i++)
		{
			Node elementNode = elemenNodes.item(i);
			if(elementNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element element = (Element)elementNode;
				
				if(element.getElementsByTagName("name").getLength() > 0)System.out.println("Parsing element: "+element.getElementsByTagName("name").item(0).getTextContent());
				else System.out.println("Parsing element: "+element.getTextContent());
				elements.add(element);
			}
		}
		return elements;		
	}
	private GameRoom parseRoomFromElement(Element element)
	{
		//get the name
		String name = element.getElementsByTagName("name").item(0).getTextContent();
		
		//get the background
		Image background = null;
		String backgroundPath = element.getElementsByTagName("background").item(0).getTextContent();
		try 
		{
			background = new Image(backgroundPath);
		} 
		catch (SlickException e) {e.printStackTrace();}
		
		//get the boundaries
		ArrayList<Boundary> bounds = new ArrayList<Boundary>(0); 
		Element boundsElement = (Element)element.getElementsByTagName("bounds").item(0);
		NodeList boundList = boundsElement.getElementsByTagName("boundary");
		
		for(int i = 0; i < boundList.getLength(); i++)
		{
			Element boundElement = (Element)boundList.item(i);
			String id = boundElement.getElementsByTagName("id").item(0).getTextContent();
			String xStart = boundElement.getElementsByTagName("xstart").item(0).getTextContent();
			String xEnd = boundElement.getElementsByTagName("xend").item(0).getTextContent();
			String yStart = boundElement.getElementsByTagName("ystart").item(0).getTextContent();
			String yEnd = boundElement.getElementsByTagName("yend").item(0).getTextContent();
			
			bounds.add(objFactory.genBoundary(id, xStart, xEnd, yStart, yEnd));
		}
		
		//get the exits
		ArrayList<Exit> exits = new ArrayList<Exit>();
		Element exitElement = (Element)element.getElementsByTagName("bounds").item(0);
		NodeList exitList = exitElement.getElementsByTagName("boundary");
		for(int i = 0; i < exitList.getLength(); i++)
		{
			Element boundElement = (Element)exitList.item(i);
			
			String dest = boundElement.getElementsByTagName("target").item(0).getTextContent();
			String xStart = boundElement.getElementsByTagName("xstart").item(0).getTextContent();
			String xEnd = boundElement.getElementsByTagName("xend").item(0).getTextContent();
			String yStart = boundElement.getElementsByTagName("ystart").item(0).getTextContent();
			String yEnd = boundElement.getElementsByTagName("yend").item(0).getTextContent();
			
			exits.add(objFactory.genExit( name, dest, xStart, xEnd, yStart, yEnd));
		}
		
		objFactory.GenRoom(name, background, bounds, exits);
		return null;
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
