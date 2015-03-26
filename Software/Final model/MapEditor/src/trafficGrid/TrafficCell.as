package trafficGrid
{
	import flash.events.Event;
	
	import grid.Cell;
	
	import instruments.Icons;

	public class TrafficCell extends Cell implements ITrafficCell
	{
		private var _trafficLight:String;
		private var _isEntry:Boolean;
		private var _isExit:Boolean;
		private var _isRoad:Boolean;
		
		public function TrafficCell(x:int, y:int)
		{
			super(x, y);
			this.setStyle("skinClass",TrafficCellSkin);
		}
		
		public function get isRoad():Boolean{
			return _isRoad;	
		}
		
		[Bindable]
		public function get isExit():Boolean{
			return _isExit;
		}
		
		[Bindable]
		public function get isEntry():Boolean{
			return _isEntry;
		}
		
		[Bindable]
		public function get trafficLight():String{
			return this._trafficLight;
		}
		
		public function set trafficLight(value:String):void
		{
			_trafficLight = value;
			trace('set traffic light');
			this.dispatchEvent(new Event("trafficLightChanged"));
		}
		
		
		override public function set type(value:String):void
		{
			super.type = value;
			this._isRoad = false;
						
			switch(value){
				case TrafficCellType.ROAD_WEST:
					super.setFill(null, Icons.ROAD_WEST);
					this._isRoad = true;
					break;
				case TrafficCellType.ROAD_NORTH:
					super.setFill(null, Icons.ROAD_NORTH);
					this._isRoad = true;
					break;
				case TrafficCellType.ROAD_EAST:
					super.setFill(null, Icons.ROAD_EAST);
					this._isRoad = true;
					break;
				case TrafficCellType.ROAD_SOUTH:
					super.setFill(null, Icons.ROAD_SOUTH);
					this._isRoad = true;
					break;
				case TrafficCellType.ROAD_JUNCTION:
					super.setFill(null, Icons.ROAD_JUNCTION);
					break;
				case TrafficCellType.ROUNDABOUT:
					super.setFill(0x3b5f91, null);
					break;
				case TrafficCellType.ROUNDABOUT_INSIDE:
					trace('set type roundabout inside');
				case TrafficCellType.EMPTY:
					super.setFill(0xffffff);
					break;
			}
		}
//
//		public function set isRoad(value:Boolean):void
//		{
//			_isRoad = value;
//		}

		public function set isExit(value:Boolean):void
		{
			_isExit = value;
			if(_isEntry && value){
				isEntry = false;
			}
		}

		public function set isEntry(value:Boolean):void
		{
			_isEntry = value;
			if(_isExit && value){
				isExit = false;
			}
		}
		
		public function toNumber():int{
			var i:int = 0;
			switch(type){
				case TrafficCellType.ROAD_WEST:
					i = 1;
					break;
				case TrafficCellType.ROAD_NORTH:
					i = 3;
					break;
				case TrafficCellType.ROAD_EAST:
					i = 2;
					break;
				case TrafficCellType.ROAD_SOUTH:
					i = 4;
					break;
				case TrafficCellType.ROAD_JUNCTION:
					i = 5;
					break;
				case TrafficCellType.ROUNDABOUT:
					i = 9;
					break;
				case TrafficCellType.ROUNDABOUT_INSIDE:
					i = 7;
					break;
				case TrafficCellType.EMPTY:
					i = 0;
					break;
			}
			return i;
		}

	}
}