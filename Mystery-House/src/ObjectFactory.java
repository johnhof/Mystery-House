import java.util.ArrayList;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

public class ObjectFactory implements Utility{
	
	//generate a tilemap from a tile map path
	public TiledMap genMap(String mapPath)
	{
		try 
		{
			return new TiledMap(mapPath);
		} catch (SlickException e) {
			System.out.print("ERROR: map generation failed");
			e.printStackTrace();
			return null;
		}
	}
	
	//generate an animation from an array of image paths and durations
	public Animation genAnimation(String[] imgPaths, int [] duration)
	{
		Image [] imgArray = new Image [imgPaths.length];
		for(int i = 0; i < imgPaths.length; i++)
		{
			try 
			{
				imgArray[i] = new Image(imgPaths[i]);
			} catch (SlickException e) 
			{
				System.out.print("ERROR: Image loading failed during Animation generation");
				e.printStackTrace();
				return null;
			}
		}
		return new Animation(imgArray, duration, false);
	}

	//generate boundaries from a name, and (x1,x2,y1,y2)
	public Boundary genBoundary(String id, String xStart, String xEnd, String yStart, String yEnd)
	{
		return genBoundary(id, Integer.getInteger(xStart), Integer.getInteger(xEnd), Integer.getInteger(yStart), Integer.getInteger(yEnd));
	}
	public Boundary genBoundary(String id, int xStart, int xEnd, int yStart, int yEnd)
	{
		return new Boundary(id, xStart, xEnd, yStart, yEnd);
	}
	

	//generate boundaries from a name, and (x1,x2,y1,y2)
	public Exit genExit(String source, String dest, String xStart, String xEnd, String yStart, String yEnd)
	{
		return genExit(source, dest, Integer.getInteger(xStart), Integer.getInteger(xEnd), Integer.getInteger(yStart), Integer.getInteger(yEnd));
	}
	public Exit genExit(String source, String dest, int xStart, int xEnd, int yStart, int yEnd)
	{
		return new Exit(source, dest, xStart, xEnd, yStart, yEnd);
	}
	
	//generate a room from its pieces
	public GameRoom GenRoom(String roomName, Image background, ArrayList<Boundary> bounds, ArrayList<Exit> exits)
	{
	    return new GameRoom(roomName, background, bounds, exits);
	}
}
