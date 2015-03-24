package core;
/**
 * An interface for working with traffic lights. Traffic lights
 * can be placed in cells, such that a cell can have only one traffic light
 * which applies for cars going in the same direction as the cell. 
 * 
 * @author Anton
 *
 */
public interface ITrafficLight {
	/**
	 * Set colour of the traffic light
	 * @param colour the new colour
	 */
	public void setColour(TrafficLightColour colour);
	
	/**
	 * Get colour of the traffic light
	 * @return the current colour
	 */
	public TrafficLightColour getColour();
	
	/**
	 * Set delay of the traffic light
	 * @param delay specifies how many ms the traffic light updates
	 */
	public void setDelay(int delay);
	
	/**
	 * Stop traffic light from changing
	 */
	public void stop();
	
	/**
	 * Start traffic light so it changes colours
	 */
	public void start();
}
