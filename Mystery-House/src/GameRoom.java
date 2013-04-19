import java.util.ArrayList;

import org.newdawn.slick.*;

public class GameRoom extends GameObject{
	private String name;
	private Image background;
	private ArrayList<Exit> exits;
	
	
	public GameRoom(String newName, Image newBackground, ArrayList<Boundary> newBounds, ArrayList<Exit> newExits) 
	{
		super(0, 0);
		name = newName;
		bounds = newBounds;
		exits = newExits;
	}
	//setters
	public void setExits(ArrayList<Exit> newExits){exits = newExits;}
	public void setBackground(Image newBackground){background = newBackground;}

	//getters
	public ArrayList<Exit> getExits(){return exits;}
	public Image getBackground(){return background;}
	
//-------------------------------------------------------------------------------------------------------------------------------
//-- ABSTRACT INSTANTIATION
//-------------------------------------------------------------------------------------------------------------------------------
	      
	@Override
	public void render() {
		
	}
}
