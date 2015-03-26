package grid
{
	public class BasicCell implements ICell
	{
		protected var _xPos:int;
		protected var _yPos:int;
		protected var _type:String;
		
		private var _debugType:String;
		
		/**
		 * A simple cell with x, y and type attributes.
		 * @param x x coordinate
		 * @param y y coordinate
		 */
		public function BasicCell(x:int, y:int, type:String = null)
		{
			_xPos = x;
			_yPos = y;
			_type = type;
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
			return "("+_xPos+":"+_yPos+") ";
		}
		
		public function get type():String
		{
			return this._type;
		}
		
		public function set type(value:String):void
		{
			this._type = type;
		}

		public function get debugType():String
		{
			return _debugType;
		}

		public function set debugType(value:String):void
		{
			_debugType = value;
		}

	}
}