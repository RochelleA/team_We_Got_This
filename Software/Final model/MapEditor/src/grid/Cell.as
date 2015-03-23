package grid
{
	
	import flash.events.Event;
	
	import spark.components.supportClasses.SkinnableComponent;
	import spark.primitives.Rect;
	
	[Style(name="side", inherit="no", type="int")]
	public class Cell extends SkinnableComponent
	{
		private var _type:String;
		private var _prevType:String;
		
		private var _temp:Boolean;
		private var _side:int = 20;
		
		private var _xPos:int, _yPos:int;
		
//		[SkinState(grid.CellType.EMPTY)]
//		[SkinState("road_north")]
//		[SkinState("road_west")]
//		[SkinState("road_south")]
//		[SkinState("road_east")]
//		[SkinState("road_junction")]
//		[SkinState("roundabout")]
		
		public function Cell(x:int, y:int)
		{
			super();
			this._xPos = x;
			this._yPos = y;
			this.setStyle("skinClass",CellSkin);
			this.setStyle("side", 20);
			this.type = grid.CellType.EMPTY;
			
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
		
		[SkinPart(required="true")]
		public var rect:Rect;
		
		override protected function getCurrentSkinState():String
		{
			trace(_xPos,_yPos,'get current skin state', this._type);
			return this._type;
		} 
		
		override protected function partAdded(partName:String, instance:Object) : void
		{
			super.partAdded(partName, instance);
		}
		
		override protected function partRemoved(partName:String, instance:Object) : void
		{
			super.partRemoved(partName, instance);
		}

		public function get type():String
		{
			return _type;
		}

		public function set type(value:String):void
		{
			this._prevType = _type;
			_type = value;
				
			this.invalidateSkinState();
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

		
	}
}