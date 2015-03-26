package grid
{
	public class BasicCell implements ICell
	{
		protected var _xPos:int;
		protected var _yPos:int;
		protected var _type:String;
		
		/**
		 * A simple cell with x, y and type attributes.
		 * @param x x coordinate
		 * @param y y coordinate
		 */
		public function BasicCell(x:int, y:int)
		{
			_xPos = x;
			_yPos = y;
		}
		
		public function get xPos():int
		{
			return _xPos;
		}
		
		public function get yPos():int
		{
			return _yPos;
		}
		
		public function toString():String
		{
			return "("+_xPos+":"+_yPos+")";
		}
		
		public function get type():String
		{
			return this._type;
		}
		
		public function set type(value:String):void
		{
			this._type = type;
		}
	}
}