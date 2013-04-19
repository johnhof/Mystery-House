import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;


public class Driver extends BasicGame implements Utility
{
    float x = 64;
    float y = 64;
    private TiledMap room;
    private boolean[][] MapBounds;
    private GameObject[] loadedObjects;
    private Animation sprite, up, down, left, right;
    private ObjectFactory factory;
    private static ResourceParser parser;
  
  
	public Driver() 
	{
		super("Mystery House");
		factory = new ObjectFactory();
	}  
	
    public static void main(String[] args)throws SlickException
    {
    	parser = new ResourceParser(MANIFEST);
    	
         /*AppGameContainer app = new AppGameContainer( new Driver() );
  
         app.setDisplayMode(MAP_WIDTH, MAP_HEIGHT, false);
         app.start();*/
    }	  

//-------------------------------------------------------------------------------------------------------------------------------
//-- INIT AND UTILITIES
//-------------------------------------------------------------------------------------------------------------------------------
    
    @Override
    public void init(GameContainer gc) throws SlickException 
    {
    	/*room = factory.genMap(ENTRY_WAY);

        //Image [] movementDown = {new Image("data/wmg1_fr1.png"), new Image("data/wmg1_fr2.png")};;
        int [] duration = {150, 150};
        
		up = factory.genAnimation(new String [] {"rsc/character_u1.png","rsc/character_u2.png"}, duration);
		down = factory.genAnimation(new String [] {"rsc/character_d1.png","rsc/character_d2.png"}, duration);
		left = factory.genAnimation(new String [] {"rsc/character_l1.png","rsc/character_l2.png"}, duration);
		right = factory.genAnimation(new String [] {"rsc/character_r1.png","rsc/character_r2.png"}, duration);
		sprite = right;
         
        // build a collision map based on tile properties in the TileD map 
		MapBounds = factory.GenMapBorders(room);

		System.out.println("done\n");*/
    }
    
//-- UTILITY FUNCTIONS ----------------------------------------------------------------------------------------------------------
    
    public boolean loadRoom()
    {
		return false;
    	
    }
    
//-------------------------------------------------------------------------------------------------------------------------------
//-- UPDATE AND UTILITIES
//-------------------------------------------------------------------------------------------------------------------------------
  
    @Override
    public void update(GameContainer gc, int delta) throws SlickException
    {
        Input input = gc.getInput();
        
        moveHandler(input, delta);
    }
    
//-- UTILITY FUNCTIONS ----------------------------------------------------------------------------------------------------------
    private boolean moveHandler(Input input, int delta)
    {
        if(input.isKeyDown(Input.KEY_LEFT))
        {
            sprite = left;
            sprite.update(delta);
            if (!isBlocked((x-MOVE_MULT * delta), y))x-= MOVE_MULT * delta;
        }
  
        if(input.isKeyDown(Input.KEY_RIGHT))
        {
            sprite = right;
            sprite.update(delta);
            if (!isBlocked((x+MOVE_MULT * delta), y))
            {
            	System.out.println("moving");
            	x+= MOVE_MULT * delta;
            }
        }
  
        if(input.isKeyDown(Input.KEY_UP))
        {
            sprite = up;
            sprite.update(delta);
            if (!isBlocked(x, y-MOVE_MULT * delta)) y-= MOVE_MULT * delta;
        }
        
        if(input.isKeyDown(Input.KEY_DOWN))
        {
            sprite = down;
            sprite.update(delta);
            if (!isBlocked(x, (y+MOVE_MULT * delta)))y+= MOVE_MULT * delta;
        }
        //no movement occurred
        else return false;
    	
        return true;
    }
    
    private boolean isBlocked(float x, float y)
    {
         int xBlock = (int) ((int)x / BLOCK_SIZE);
         int yBlock = (int) ((int)y / BLOCK_SIZE);
         System.out.println("X: "+xBlock+":"+MapBounds.length+", Y: "+yBlock+":"+MapBounds[0].length);
         //if we're out of the map bounds, stop movement
         if(xBlock >=  MapBounds.length || xBlock < 0 || yBlock >= MapBounds[0].length || yBlock < 0) return false;
         return MapBounds[xBlock][yBlock];
    }
  

//-------------------------------------------------------------------------------------------------------------------------------
//-- RENDER AND UTILITIES
//-------------------------------------------------------------------------------------------------------------------------------
    
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        g.scale(SCALE, SCALE);
        room.render(0,0);
        sprite.draw(x, y);
    }
    
  //-- UTILITY FUNCTIONS ----------------------------------------------------------------------------------------------------------

    
}