import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;


public class Sprite extends GameObject implements Utility{
    private Animation state, up, down, left, right;
    int stateId;
    int delta;
    
    public Sprite(int newX, int newY, Animation newState)
    {
    	super(newX, newY);
    	state = newState;
    }
    public Sprite(float newX, float newY, Animation newUp, Animation newDown, Animation newLeft, Animation newRight)
    {
    	super(newX,newY);
    	up = newUp;
    	down = newDown;
    	left = newLeft;
    	right = newRight;
    }
    
    private void setDelta(int newDelta){delta = newDelta;}
    private int getDelta(){return delta;}
    
    public void stateUp(){setState(UP);}
    public void stateDown(){setState(DOWN);}
    public void stateLeft(){setState(LEFT);}
    public void stateRight(){setState(RIGHT);}
    public void setState(int newStateId)
    {
    	stateId = newStateId;
    	switch(stateId)
    	{
    		case UP: state = up;
    			break;
    		case DOWN: state = down;
    			break;
    		case LEFT: state = left;
    			break;
    		case RIGHT:	state = right;
    			break;
    		default: System.out.println("WARNING: invalid state ID");
    	}
        state.update(delta);
    }
    
    public int getStateId()
    {
    	return stateId;
    }
    public Animation getAnimationState()
    {
    	return state;
    }

//-------------------------------------------------------------------------------------------------------------------------------
//-- ABSTRACT INSTANTIATION
//-------------------------------------------------------------------------------------------------------------------------------
      
	@Override
	public void render() {
		state.draw(x,y);
	}
}
