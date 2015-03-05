package core;
// TODO: Auto-generated Javadoc
/**
 * An interface which provides method for working with cars. 
 * @author Anton
 * @version 1
 * 
 */
public interface ICar {
	/**
	 * Returns x position of the car.
	 * @return x position
	 */
	public int getX();
	/**
	 * Returns y position of the car.
	 * @return y position
	 */
	public int getY();
	/**
	 * Sets y position of the car.
	 * @param y the position to set
	 */
	public void setY(int y);
	
	/**
	 * Sets x position of the car.
	 * @param x the position to set
	 */
	public void setX(int x);
	
	public void setPosition(int x, int y);
	
	/**
	 * Gets the speed.
	 *
	 * @return the speed
	 */
	public int getSpeed();
	
	public void setSpeed(int speed);
	
	public void setEnterDir(Direction dirEnt);
	
	public Direction getEnterDir();
	
	public void setExitDir(Direction dirExt);
	
	public Direction getExitDir();
}
