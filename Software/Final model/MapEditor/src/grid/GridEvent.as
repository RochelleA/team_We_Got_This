package grid
{
	import flash.events.Event;
	
	public class GridEvent extends Event
	{
		private var _isMouseDown:Boolean;
		private var _cells:Array;
		
		public static const MOUSE_MOVE:String = "mouse_move";
		public static const MOUSE_UP:String = "mouse_up";
		
		public function GridEvent(type:String, isMouseDown:Boolean, cells:Array, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			this._isMouseDown = isMouseDown;
			this._cells = cells;
		}

		public function get cells():Array
		{
			return _cells;
		}

		public function get isMouseDown():Boolean
		{
			return _isMouseDown;
		}


	}
}