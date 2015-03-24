package grid
{
	
	import spark.components.supportClasses.SkinnableComponent;
	import spark.primitives.Rect;
		
	[Style(name="side", inherit="no", type="int")]
	public class Cell extends SkinnableComponent
	{
		private var _type:String;
		private var _prevType:String;
		
		private var _temp:Boolean;
		
		private var _xPos:int, _yPos:int;
		
		[SkinState("normal")]
		[SkinState("temp")]
		
		[SkinPart(required="true")]
		public var transparentFillRect:Rect;
		[SkinPart(required="false")]
		public var solidFillRect:Rect;
		[SkinPart(required="false")]
		public var bitmapFillRect:Rect;
		
		public function Cell(x:int, y:int)
		{
			super();
			this._xPos = x;
			this._yPos = y;
			setDefaultStyles();			
		}
		
		private function setDefaultStyles():void{
			this.setStyle("skinClass",CellSkin);
			this.setStyle("side", 20);
			this.setStyle("strokeColour", 0xaaaaaa);
			this.setFill(null, null);
		}

		/**
		 * Use this function to set fills.
		 */
		protected function setFill(solidFillColour:* = null, bitmapFill:* = null):void{
			if(solidFillColour != null){
				this.setStyle("isSolidFill", true);
				this.setStyle('solidFill', solidFillColour);
			}else{
				this.setStyle("isSolidFill", false);
			}
			
			if(bitmapFill != null){
				this.setStyle("isBitmapFill", true);
				this.setStyle("bitmapFill", bitmapFill);
			}else{
				this.setStyle("isBitmapFill", false);
			}

		}
		
		override public function toString():String{
			return "("+_xPos+":"+_yPos+")";
		}
		
		private function position():void{
			this.move(_xPos*this.getStyle("side"),_yPos*this.getStyle("side"));
		}
		
		/**
		 * Overwrite to change side
		 */
		override public function setStyle(styleProp:String, newValue:*):void{
			super.setStyle(styleProp, newValue);
			if(styleProp == "side"){
				position();
			}
		}
				
		override protected function getCurrentSkinState():String
		{
			var s:String = "";
			if(this.temp){
				s = "temp";
			}else{
				s = "normal";
			}
			return s;
		} 
		
		public function get type():String
		{
			return _type;
		}

		public function set type(value:String):void
		{
			this._prevType = _type;
			trace('set type');
			_type = value;
		}

		public function get temp():Boolean
		{
			return _temp;
		}

		public function set temp(value:Boolean):void
		{
			_temp = value;
			trace('set temp');
			this.invalidateSkinState();
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
		
		public function confirmNewType():void
		{
			this.temp = false;
			this.type = this.type;
		}

	}
}