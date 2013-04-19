import java.util.ArrayList;

import org.newdawn.slick.*;

public abstract class GameObject 
{

	protected float x;
	protected float y;
	protected ArrayList<Boundary> bounds;
	protected boolean useable;	
	
	
	GameObject(float newX, float newY)
	{
		x = newX;
		y = newY;
	}
	
	//setters
	public void setY(float newY){y = newY;}
	public void setX(float newX){x = newX;}
	public void setBounds(ArrayList<Boundary> newBounds){bounds = newBounds;}
	
	//getters
	public float getX(){return x;}
	public float getY(){return y;}
	public ArrayList<Boundary> getBound(){return bounds;}
	
	abstract public void render();
}
 