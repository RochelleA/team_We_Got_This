package grid
{
	
	import flash.events.Event;
	
	import mx.events.PropertyChangeEvent;
	
	import spark.components.supportClasses.SkinnableComponent;
	import spark.primitives.Rect;
	import trafficGrid.TrafficCellType;
	
	[Style(name="side", inherit="no", type="int")]
	public class Cell extends SkinnableComponent
	{
		private var _type:String;
		private var _prevType:String;
		
		private var _temp:Boolean;
		private var _side:int = 20;
		
		private var _xPos:int, _yPos:int;
		
		private var _isRoad:Boolean = false;
		
		private var _isExit:Boolean, _isEntry:Boolean;
		
//		[SkinState(grid.CellType.EMPTY)]
//		[SkinState("road_north")]
//		[SkinState("road_west")]
//		[SkinState("road_south")]
//		[SkinState("road_east")]
//		[SkinState("road_junction")]
//		[SkinState("roundabout")]
		
		[SkinPart(required="true")]
		public var rect:Rect;
		
		public function Cell(x:int, y:int)
		{
			super();
			this._xPos = x;
			this._yPos = y;
			this.setStyle("skinClass",CellSkin);
			this.setStyle("side", 20);
			this.type = trafficGrid.TrafficCellType.EMPTY;
			
		}
		
		override public function toString():String{
			return "("+_xPos+":"+_yPos+")";
		}
		
		private function position():void{
			this.move(_xPos*this.getStyle("side"),_yPos*this.getStyle("side"));
		}
		override public function setStyle(styleProp:String, newValue:*):void{
			super.setStyle(styleProp, newValue);
			if(styleProp == "side"){
				position();
			}
		}
		
		private var _trafficLight:String;
		
		override protected function getCurrentSkinState():String
		{
			//trace(_xPos,_yPos,'get current skin state', this._type);
			return this._type;
		} 
		
		public function get type():String
		{
			return _type;
		}

		public function set type(value:String):void
		{
			this._prevType = _type;
			_type = value;
			
			_isRoad = (type != trafficGrid.TrafficCellType.ROUNDABOUT && type != trafficGrid.TrafficCellType.EMPTY);
			
			
			if(!temp && !_isRoad){
				trace('not temp, not road, set permanent values');
				_isExit = false;
				_isEntry = false;
			}
			trace(isExit, isEntry);
			//manually dispatch property change event because isRoad is changed
			this.dispatchEvent(PropertyChangeEvent.createUpdateEvent(this,"isEntry",_isEntry,isEntry));
			this.dispatchEvent(PropertyChangeEvent.createUpdateEvent(this,"isExit",_isExit,isExit));
			this.invalidateSkinState();
			trace('set type');
		}

		public function get temp():Boolean
		{
			return _temp;
		}

		public function set temp(value:Boolean):void
		{
			_temp = value;
			this.dispatchEvent(new Event("tempChanged"));
		}

		public function get yPos():int
		{
			return _yPos;
		}

		public function get xPos():int
		{
			return _xPos;
		}
		
		public function restore():void{
			this.temp = false;
			this.type = this._prevType;
		}

		[Bindable]
		public function get isExit():Boolean
		{
			return _isRoad && _isExit;
		}

		public function set isExit(value:Boolean):void
		{
			_isExit = value;
			if(value && _isEntry){
				isEntry = false;
			}
		}

		[Bindable]
		public function get isEntry():Boolean
		{
			return _isRoad && _isEntry;
		}

		public function set isEntry(value:Boolean):void
		{
			_isEntry = value;
			if(value && _isExit){
				isExit = false;
			}
		}

		public function get isRoad():Boolean
		{
			return _isRoad;
		}

		
		public function confirmNewType():void
		{
			this.temp = false;
			trace('confirm new type');
			this.type = this.type;
		}

		[Bindable]
		public function get trafficLight():String
		{
			return _trafficLight;
		}

		public function set trafficLight(value:String):void
		{
			_trafficLight = value;
			this.dispatchEvent(new Event("trafficLightChanged"));
		}

	}
}