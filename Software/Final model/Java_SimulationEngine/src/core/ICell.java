package core;

/**
 * An interface to work with cells.
 * @author Anton
 * @version 1
 * 
 */
public interface ICell {
	/**
	 * Returns type of the cell.
	 * @return type of cell
	 * @see ICell#setType(CellType)
	 * @see CellType	 
	 */
	CellType getType();
	
	/**
	 * Sets type of the cell.
	 * @param type new type of cell.
	 * @see ICell#getType()
	 * @see CellType
	 */
	void setType(CellType type);
	
	/**
	 * Returns direction of the cell.
	 * @return direction of cell.
	 * @see ICell#setDirection(Direction)
	 */
	Direction getDirection();
	
	/**
	 * Sets the direction of the cell.
	 * @param direction new direction of cell
	 * @see ICell#getDirection()
	 */
	void setDirection(Direction direction);
	
	/**
	 * Checks if the cell if an exit node, i.e. cars will be deleted from it.
	 * @return true if exit, false otherwise.
	 * @see ICell#setIsExit(boolean)
	 */
	boolean isExit();
	
	/**
	 * Sets the cell to be an exit or not exit.
	 * @param b true to place exit marker on the node and false to remove exit marker.
	 * @see ICell#isExit()
	 */
	void setIsExit(boolean b);
	
	/**
	 * Checks if the cell if an entry node, i.e. cars will appear from it.
	 * @return true if entry, false otherwise.
	 * @see ICell#setIsEntry(boolean b)
	 */
	boolean isEntry();
	
	/**
	 * Sets the cell to be an entry or not entry.
	 * @param b true to place entry marker on the node and false to remove entry marker.
	 * @see ICell#isEntry()
	 */
	void setIsEntry(boolean b);
	
	/**
	 * Returns a car currently positioned on the cell.
	 * @return car on the cell or null if there's no car.
	 * @see ICar
	 * @see ICell#setCar(ICar)
	 */
	ICar getCar();
	
	/**
	 * Places a car on the cell. Throws an exception if cell already has a car.
	 * @param car the car to place
	 * @throws GridException
	 * @see ICar
	 * @see ICell#getCar()
	 */
	void setCar(ICar car);
	
	/**
	 * Removes the car from the cell. Throws an exception if cell does not have a car.
	 * @throws GridException
	 * @see ICar
	 * @see ICell#hasCar()
	 */
	void removeCar();
	
	/**
	 * Returns a traffic light on the cell or null if there's no traffic light.
	 * @return traffic light
	 * @see ITrafficLight
	 * @see ICell#setTrafficLight(ITrafficLight)
	 */
	ITrafficLight getTrafficLight();
	
	/**
	 * Positions a traffic light on the cell. Throws an exception if there's no traffic light.
	 * @param tl the traffic light to place
	 * @throws GridException
	 * @see ICell#getTrafficLight()
	 */
	void setTrafficLight(ITrafficLight tl);
	
	/**
	 * Returns x coordinate of the cell set during initialisation of IGrid.
	 * @return x coordinate of the cell
	 * @see Grid#Grid(int, int)
	 */
	int getX();
	
	/**
	 * Returns y coordinate of the cell set during initialisation of IGrid.
	 * @return y coordinate
	 * @see Grid#Grid(int, int)
	 */
	int getY();
	
	/**
	 * Returns string representation of the Cell: cell@(x,y)
	 * @return cell as a string
	 */
	String toString();
	
	//abstract ICell(int x, int y);
	
}
