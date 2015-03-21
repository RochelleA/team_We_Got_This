package
{
	//import flash.display.Sprite;
		
	import mx.graphics.SolidColor;
	import mx.graphics.SolidColorStroke;
	
	import spark.primitives.Rect;

	public class SquareCell extends VisualCell
	{
		
		private var _rect:Rect;
		
		public static const defaultStroke:SolidColorStroke = new SolidColorStroke(0xaaaaaa);
		public static const EMPTY_FILL:SolidColor = new SolidColor(0xffffff);
		public static const ROAD_FILL:SolidColor = new SolidColor(0x3b5f91);
		
		public var partOfRoundabout:Boolean = false;
		
		/**
		 * An extension to a simple cell which can be displayed as a Rectangle
		 * for which width and height are the same, i.e. a square.
		 */
		public function SquareCell(x:int, y:int, side:int)
		{
			super(x, y);
			
			_rect = new Rect();
			_rect.width = side;
			_rect.height = side;
			
			_rect.fill = SquareCell.EMPTY_FILL;
			_rect.stroke = SquareCell.defaultStroke;
			
			this.addElement(_rect);
			//this.toolTip = "x: "+x+", y: "+y;
			
			super.type = CellType.EMPTY;

		}
		
		override public function set tempType(value:int):void{
			this._tempType = value;
			switch (value){
				case CellType.ROAD:
				case CellType.RB:
					colour(SquareCell.ROAD_FILL, 0.5);
					break;
				default:
					colour(SquareCell.EMPTY_FILL, 0.5);
			}
		}
		
		private function colour(colour:SolidColor, alpha:Number = 1):void{
			this._rect.fill = colour;
			this._rect.alpha = alpha;
		}
		
		override public function set type(value:int):void{
			this._type = value;
			switch (value){
				case CellType.ROAD:
				case CellType.RB:
					colour(SquareCell.ROAD_FILL);
					//this._rect.fill = SquareCell.ROAD_FILL;
				break;
				default:
					colour(SquareCell.EMPTY_FILL);
					//this._rect.fill = SquareCell.EMPTY_FILL;
			}
//			if(value == CellType.ROAD){
//				this._rect.fill = SquareCell.ROAD_FILL;
//				//trace('set road');
//			}else if(value == CellType.EMPTY){
//				//trace('set empty');
//				this._rect.fill = SquareCell.EMPTY_FILL;
//			}
			//super.type = value;
		}
//		
//		public function get rect():Rect
//		{
//			return _rect;
//		}


	}
}