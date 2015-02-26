package core;

/**
 * An implementation for ICar interface.
 * @author Anton
 * @version 1
 *
 */
public class Car implements ICar {
	

	private int x;
	private int y;
	
	
	@Override
	public int getX(){
		return x;
	}
	
	@Override
	public int getY(){
		return y;
	}
	
	@Override
	public void setX(int x){
		this.x = x;
	}
	
	@Override
	public void setY(int y){
		this.y = y;
	}
	
	
}
