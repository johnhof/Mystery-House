import org.newdawn.slick.*;

public class GameRoom extends GameObject{
	private String name;
	private String mapPath;
	private Boundary [] boundaries;
	
	public GameRoom(int newX, int newY, String newName, String newMapPath) 
	{
		super(newX, newY);
		name = newName;
		mapPath = newMapPath;
		loadBoundaries();
	}
	
	public void loadBoundaries()
	{
		
	}

//-------------------------------------------------------------------------------------------------------------------------------
//-- ABSTRACT INSTANTIATION
//-------------------------------------------------------------------------------------------------------------------------------
	      
	@Override
	public void render() {
		
	}
}
