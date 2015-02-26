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
	private int speed;
	
	public Car(){
		
		//this.x=col;
		
		//this.y=row;
		//this.speed = speed;
		
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
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
	
	@Override
	public int getSpeed(){
		return speed;
	}
	
	@Override
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	
}
