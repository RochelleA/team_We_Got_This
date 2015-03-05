package core;

import java.util.ArrayList;

/**
 * An interface for a Grid representing 2D array of cells. 
 * Provides methods to update cells (e.g. set type, direction, place
 * car and traffic lights) and get information about cells (e.g. type,
 * direction, whether they have a car or a traffic light).
 * @author Anton
 * @version 1
 *
 */
public interface IGrid {
	/**
	 * Returns the list of all entry cells.
	 * @return ArrayList of entry cells.
	 */
	ArrayList<ICell> getEntryCells();
	
	/**
	 * Returns type of a cell at (x, y).
	 * @param x x coordinate of the cell
	 * @param y y coordinate of the cell
	 * @return one of the following types: EMPTY, ROAD, ROUNDABOUT
	 * @see CellType
	 * @see IGrid#setCellType(int x, int y, CellType type)
	 */
	CellType getCellType(int x, int y);
	
	/**
	 * Change the type of a cell at (x, y).
	 * @param x x coordinate of the cell
	 * @param y y coordinate of the cell
	 * @param type new type of the cell
	 * @see CellType
	 * @see IGrid#getCellType(int x, int y)
	 */
	void setCellType(int x, int y, CellType type);
	
	/**
	 * Returns direction of a cell at (x, y).
	 * @param x x coordinate of the cell
	 * @param y y coordinate of the cell
	 * @return one of the following directions: NORTH, EAST, SOUTH, WEST.
	 * @see Direction
	 */
	Direction getCellDirection(int x, int y);
	
	/**
	 * Change the direction of a cell at (x, y).
	 * @param x x coordinate of the cell
	 * @param y y coordinate of the cell
	 * @param direction new direction of the cell
	 * @see Direction
	 * @see IGrid#getCellDirection(int x, int y)
	 */
	void setCellDirection(int x, int y, Direction direction);
	
	/**
	 * Checks if the cell is an exit, i.e. if cars will be removed from it.
	 * @param x x coordinate of the cell
	 * @param y y coordinate of the cell
	 * @return true if cell is exit, false otherwise
	 * @see IGrid#setIsExit(int x, int y, boolean isExit)
	 */
	boolean isExit(int x, int y);
	
	/**
	 * Make a cell an exit, or not an exit.
	 * @param x x coordinate of the cell
	 * @param y y coordinate of the cell
	 * @param isExit true or false
	 * @see IGrid#isExit(int x, int y) IGrid.isExit(x, y)
	 */
	void setIsExit(int x, int y, boolean isExit);
	
	/**
	 * Checks if the cell is an entry point, i.e. if cars will appear from it.
	 * @param x x coordinate of the cell
	 * @param y y coordinate of the cell
	 * @return true if cell is an entry, false otherwise
	 * @see IGrid#setIsEntry(int x, int y, boolean isExit)
	 */
	boolean isEntry(int x, int y);
	
	/**
	 * Make a cell an entry, or not an entry.
	 * @param x x coordinate of the cell
	 * @param y y coordinate of the cell
	 * @param isEntry true or false
	 * @see IGrid#isEntry(int x, int y)
	 */
	void setIsEntry(int x, int y, boolean isEntry);
	
	//cars
	/**
	 * Checks if a cell at (x, y) currently has a car positioned on it.
	 * @param x x coordinate of the cell
	 * @param y y coordinate of the cell
	 * @return true if cell has a car and false otherwise
	 */
	boolean hasCarAt(int x, int y);
	
	/**
	 * Returns the car currently positioned on the cell at (x, y), or throws exception
	 * otherwise. To avoid possibility of exception, call <i>hasCarAt</i> method first.
	 * @param x x coordinate of the cell
	 * @param y y coordinate of the cell
	 * @return car on the cell
	 * @see ICar
	 * @see IGird#hasCarAt(int x, int y)
	 * @throws GridException
	 */
	ICar getCarAt(int x, int y);
	
	/**
	 * Adds a car to a cell at specified location (x, y). 
	 * If a cell already has a car, an exception is thrown,
	 * so <i>hasCarAt</i> method must be called first if the cell is occupied.
	 * @param x x coordinate of the cell
	 * @param y y coordinate of the cell
	 * @param car car to place on the cell
	 * @see ICar
	 * @see IGird#hasCarAt(int x, int y)
	 * @throws GridException
	 */
	void placeCarAt(int x, int y, ICar car);
	
	/**
	 * Removes a car from a cell at (x, y). 
	 * If a cell does not have a car, an exception is thrown,
	 * so <i>hasCarAt</i> method must be called first.
	 * @param x x coordinate of the cell
	 * @param y y coordinate of the cell
	 * @see ICar
	 * @see IGird#hasCarAt(int x, int y)
	 * @throws GridException
	 */
	void removeCarFrom(int x, int y);
	
	//traffic lights
	/**
	 * Checks if a cell has a traffic light. 
	 * @param x x coordinate of the cell
	 * @param y y coordinate of the cell
	 * @return true if cell has a traffic light and false otherwise
	 * @see ITrafficLight
	 */
	boolean hasTrafficLightAt(int x, int y);
	
	/**
	 * Returns the traffic light of a particular cell at position (x, y)
	 * if it has one, or throws exception otherwise. To avoid possibility of 
	 * exception, call <i>hasTrafficLightAt</i> method first.
	 * @param x x coordinate of the cell
	 * @param y y coordinate of the cell
	 * @return traffic light attached to the cell
	 * @see ITrafficLight
	 * @see IGrid#hasTrafficLightAt(int x, int y)
	 * @throws GridException
	 */
	ITrafficLight getTrafficLightAt(int x, int y);
	
	/**
	 * Adds a traffic light to a cell at specified location (x, y). 
	 * If a cell already has a traffic light, an exception is thrown,
	 * so <i>hasTrafficLightAt</i> method must be called first.
	 * @param x x coordinate of the cell
	 * @param y y coordinate of the cell
	 * @param tl traffic light to place
	 * @throws GridException
	 * @see ITrafficLight
	 * @see IGrid#hasTrafficLightAt(int x, int y)
	 */
	void placeTrafficLightAt(int x, int y, ITrafficLight tl);
	
	/**
	 * Removes a traffic light from a cell at (x, y). 
	 * If a cell does not have a traffic light, an exception is thrown,
	 * so <i>hasTrafficLightAt</i> method must be called first.
	 * @param x x coordinate of the cell
	 * @param y y coordinate of the cell
	 * @see ITrafficLight
	 * @see IGrid#hasTrafficLightAt(int x, int y)
	 * @throws GridException
	 */
	void removeTrafficLightFrom(int x, int y);
	
	/**
	 * Returns the width with which the grid has been initialised.
	 * @return width of the grid
	 */
	int getWidth();
	/**
	 * Returns the height with which the grid has been initialised.
	 * @return height of the grid
	 */
	int getHeight();
	
	/**
	 * Returns string representation of the grid.
	 * @return grid as a string
	 */
	String toString();
}
