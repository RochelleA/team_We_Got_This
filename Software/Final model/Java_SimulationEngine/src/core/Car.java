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
	private int numbCells;
	private int numbRounds;
	private int numbOverTaking;
	
	private Direction dirEnt;
	private Direction dirExt;
	
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
	public void setEnterDir(Direction dirEnt){
		this.dirEnt = dirEnt;
	}
	
	@Override
	public Direction getEnterDir(){
		return dirEnt;
	}
	
	@Override
	public void setExitDir(Direction dirExt){
		this.dirExt = dirExt;
	}
	
	@Override
	public Direction getExitDir(){
		return dirExt;
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
	public int getNumbCells(){
		return numbCells;
	}
	
	@Override
	public int getNumbRounds(){
		return numbRounds;
	}
	
	@Override
	public void setNumbCells(int numbCells){
		this.numbCells = numbCells;
	}
	
	@Override
	public void setNumbRounds(int numbRounds){
		this.numbRounds = numbRounds;
	}
	
	@Override
	public int getSpeed(){
		return speed;
	}
	
	@Override
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	@Override
	public void setNumbOverTaking(int numbOverTaking){
		this.numbOverTaking = numbOverTaking;
	}
	
	@Override
	public int getNumbOverTaking(){
		return numbOverTaking;
	}
	
}
