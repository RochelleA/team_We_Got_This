package model;

import java.util.ArrayList;

import core.ICar;
import core.ITrafficLight;

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
	
	/**
	 * Returns the status of the simulation, either running or paused
	 * @return the current status.
	 */
	public String getStatus();
	
	/**
	 * Returns current round of simulation controller.
	 * @return the current round
	 */
	public int getRound();
	
	/**
	 * Returns a list of traffic lights
	 * @return ArrayList<ITrafficLight> 
	 */
	public ArrayList<ITrafficLight> getTrafficLights();
	
	
}
