import org.newdawn.slick.*;

public abstract class GameObject 
{

	protected float x;
	protected float y;
	protected Boundary [] bound; 
	protected boolean useable;	
	
	
	GameObject(float newX, float newY)
	{
		x = newX;
		y = newY;
	}
	
	public float getX(){return x;}
	public float getY(){return y;}
	public Boundary [] getBound(){return bound;}
	
	public void setY(float newY){y = newY;}
	public void setX(float newX){x = newX;}
	public void setWidth(Boundary [] newBound){bound = newBound;}
	
	abstract public void render();
}
 