import org.newdawn.slick.Image;


public class Boundary implements Utility
{
	private String id;
	private float xStart;
	private float xEnd;
	private float yStart;
	private float yEnd;
	private float xScaledStart;
	private float xScaledEnd;
	private float yScaledStart;
	private float yScaledEnd;
	
	public Boundary(String newId, float newXStart, float newXEnd, float newYStart, float newYEnd)
	{
		id = newId;
		xStart = newXStart;
		xEnd = newXEnd;
		yStart = newYStart;
		yEnd = newYEnd;
		
		scale();
	}
	
	public void scale()
	{
		xScaledStart=xStart*SCALE;
		xScaledEnd=xEnd*SCALE;
		yScaledStart=yStart*SCALE;
		yScaledEnd=yEnd*SCALE;		
	}
	
	//getters
	public String getId(){return id;}
	
	public float getXStart(){return xStart;}
	public float getXEnd(){return xEnd;}
	public float getYStart(){return yStart;}
	public float getYEnd(){return yEnd;}
	
	public float getXScaledStart(){return xStart;}
	public float getXScaledEnd(){return xEnd;}
	public float getYScaledStart(){return yStart;}
	public float getYScaledEnd(){return yEnd;}

	
	//setters
	public void setId(String newId){id = newId;}
	public void setXStart(float newXStart){xStart = newXStart;}
	public void setXEnd(float newXEnd){xEnd = newXEnd;}
	public void setYStart(float newYStart){yStart = newYStart;}
	public void setYEnd(float newYEnd){yEnd = newYEnd;}	
}
