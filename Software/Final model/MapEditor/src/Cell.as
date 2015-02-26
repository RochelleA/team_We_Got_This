package
{
	import spark.primitives.Graphic;
	
	public class Cell extends Graphic implements ICell
	{
		private var _direction:int;
		private var _isExit:Boolean;
		private var _trafficLight:ITrafficLight;
		
		private var _xPos:int;
		private var _yPos:int;		
		private var _type:int;
		
		public static const ROAD:int = 1;
		public static const EMPTY:int = 0;

		/**
		 * An abstract representation of one place on a map, which 
		 * has x and y coordinates and can have a traffic light,
		 * a car and be either a road in certain direction or empty.
		 * @param x x position
		 * @param y y position
		 */
		public function Cell(x:int, y:int)
		{
			super();
			
			_xPos = x;
			_yPos = y;
		}
		
		public function get direction():int
		{
			return this._direction;
		}
		
		public function set direction(value:int):void
		{
			this._direction = value;
		}
		
		public function get isExit():Boolean
		{
			return this._isExit;
		}
		
		public function set isExit(value:Boolean):void
		{
			this._isExit = value;
		}
		
		public function get trafficLight():ITrafficLight
		{
			return this._trafficLight;
		}
		
		public function set trafficLight(value:ITrafficLight):void
		{
			this._trafficLight = value;
		}
		
		public function get xPos():int
		{
			return _xPos;
		}
		
		public function get yPos():int
		{
			return _yPos;
		}
		
		public function get type():int
		{
			return _type;
		}
		
		public function set type(value:int):void
		{
			_type = value;
		}
	}
}