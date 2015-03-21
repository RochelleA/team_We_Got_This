package
{
	import spark.primitives.Graphic;
	
	public class VisualCell extends Graphic implements IVisualCell
	{
		
		private var _direction:int;
		private var _isExit:Boolean;
		private var _trafficLight:ITrafficLight;
		
		private var _xPos:int;
		private var _yPos:int;		
		protected var _type:int;
		protected var _prevType:int;
		protected var _tempType:int;
		protected var _highlighted:Boolean;
		protected var _selected:Boolean;
		protected var _roadDir:String;
		protected var _prevRoadDir:String;

		/**
		 * An abstract representation of one place on a map, which 
		 * has x and y coordinates and can have a traffic light,
		 * a car and be either a road in certain direction or empty.
		 * @param x x position
		 * @param y y position
		 */
		public function VisualCell(x:int, y:int)
		{
			super();
			
			_xPos = x;
			_yPos = y;
		}
		
		override public function toString():String{
			return "("+this._xPos + ":"+this._yPos+")";
		}
		public function typeToString():String{
			var s:String;
			switch (this._type){
				case CellType.EMPTY:
					s = "empty"
					break;
				case CellType.RB:
					s = "roundabout";
					break;
				case CellType.ROAD:
					s = "road,"+" "+ this.roadDir;
			}
			return s;
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

		public function get prevType():int
		{
			return _prevType;
		}

		public function set prevType(value:int):void
		{
			_prevType = value;
		}

		public function get tempType():int
		{
			return _tempType;
		}

		public function set tempType(value:int):void
		{
			_tempType = value;
		}

		public function get highlighted():Boolean
		{
			return _highlighted;
		}

		public function set highlighted(value:Boolean):void
		{
			_highlighted = value;
		}

		public function get selected():Boolean
		{
			return _selected;
		}

		public function set selected(value:Boolean):void
		{
			_selected = value;
		}

		public function get roadDir():String
		{
			return _roadDir;
		}

		public function set roadDir(value:String):void
		{
			_roadDir = value;
		}

		public function get prevRoadDir():String
		{
			return _prevRoadDir;
		}

		public function set prevRoadDir(value:String):void
		{
			_prevRoadDir = value;
		}


	}
}