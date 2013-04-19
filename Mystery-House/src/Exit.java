
public class Exit extends Boundary{
	String source;
	String dest;
	
	
	public Exit(String newSource, String newDest, float newXStart, float newXEnd, float newYStart,float newYEnd) 
	{
		super("", newXStart, newXEnd, newYStart, newYEnd);
		source = newSource;
		dest = newDest;
		setId(newSource+"-to-"+newDest);
	}
	
	public void setSource(String newSource){source = newSource;}	
	public void setDest(String newDest){dest = newDest;}
	

	public String getSource(){return source;}
	public String getDest(){return dest;}
}
