import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

public class ObjectFactory implements Utility{
	
	//generate a tilemap from a tile map path
	public static TiledMap genMap(String mapPath)
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
	public static Animation genAnimation(String[] imgPaths, int [] duration)
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
	
	public static boolean[][] GenRoom(String roomName)
	{
		
	    return null;
	}
}
