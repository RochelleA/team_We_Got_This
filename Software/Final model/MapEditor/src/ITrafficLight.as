package
{
	public interface ITrafficLight
	{
		function ITrafficLight(color:int, direction:int);
		function startTimer(delay:int = 5000):void;
		
		/**
		 * Get current colour of the traffic light
		 */
		function get colour():int;
		
		/**
		 * Get direction of the traffic light. Possible values are:
		 * NORTH, EAST, SOUTH and WEST.
		 */
		function get direction():int;
		
		
	}
}