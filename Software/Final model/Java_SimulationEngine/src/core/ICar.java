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
	 * Gets the stop c.
	 *
	 * @return the stop c
	 */
	public int getStopC();
	
	/**
	 * Sets the stop counter.
	 *
	 * @param stopCounter the new stop counter
	 */
	public void setStopCounter(int stopCounter);
	/**
	 * Sets the cars dir.
	 *
	 * @param carsDir the new cars dir
	 */
	public void setcarsDir(Direction carsDir);

	/**
	 * Gets the cars dir.
	 *
	 * @return the cars dir
	 */
	public Direction getcarsDir();
	
	/**
	 * Sets the numb over taking.
	 *
	 * @param numbOverTaking the new numb over taking
	 */
	public void setNumbOverTaking(int numbOverTaking);
	
	/**
	 * Gets the numb over taking.
	 *
	 * @return the numb over taking
	 */
	public int getNumbOverTaking();
	
	
	/**
	 * Sets x position of the car.
	 * @param x the position to set
	 */
	public void setX(int x);
	
	
	
	/**
	 * Gets the numb cells.
	 *
	 * @return the numb cells
	 */
	public int getNumbCells();
	
	/**
	 * Gets the numb rounds.
	 *
	 * @return the numb rounds
	 */
	public int getNumbRounds();
	
	/**
	 * Sets the numb cells.
	 *
	 * @param numbCells the new numb cells
	 */
	public void setNumbCells(int numbCells);
	
	/**
	 * Sets the numb rounds.
	 *
	 * @param numbRounds the new numb rounds
	 */
	public void setNumbRounds(int numbRounds);
	
	
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
