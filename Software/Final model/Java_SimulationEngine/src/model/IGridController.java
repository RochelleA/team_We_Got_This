package model;

import core.ICar;

/**
 * @author Anton
 *
 */
public interface IGridController {
	
	/**
	 * Places a car on the grid.
	 * @param car car to place
	 */
	public void addCar(ICar car);
	
	/**
	 * Start simulation.
	 */
	public void startTimer();
	
	/**
	 * Pause simulation.
	 */
	public void stopTimer();
	
}
