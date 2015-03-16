package
{
	//import flash.display.Sprite;
		
	import mx.graphics.SolidColor;
	import mx.graphics.SolidColorStroke;
	
	import spark.primitives.Rect;

	public class SquareCell extends Cell implements ISquareCell
	{
		
		private var _rect:Rect;
		
		public static const defaultStroke:SolidColorStroke = new SolidColorStroke(0xaaaaaa);
		public static const defaultFill:SolidColor = new SolidColor(0xffffff,1);
		public static const roadFill:SolidColor = new SolidColor(0x3b5f91);
		
		public var prevType:int;
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
			
			rect.fill = SquareCell.defaultFill;
			rect.stroke = SquareCell.defaultStroke;
			
			this.addElement(_rect);
			this.toolTip = "x: "+x+", y: "+y;
			
			super.type = Cell.EMPTY;

		}
		
		override public function set type(value:int):void{
			if(value == Cell.ROAD){
				this._rect.fill = SquareCell.roadFill;
				//trace('set road');
			}else if(value == Cell.EMPTY){
				//trace('set empty');
				this._rect.fill = SquareCell.defaultFill;
			}
			super.type = value;
		}
		
		public function get rect():Rect
		{
			return _rect;
		}


	}
}