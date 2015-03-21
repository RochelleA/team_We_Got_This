package
{
	//import flash.display.Sprite;
		
	import mx.graphics.SolidColor;
	import mx.graphics.SolidColorStroke;
	
	import spark.primitives.Rect;

	public class SquareCell extends VisualCell
	{
		
		private var _rect:Rect;
		
		private static const DEFAULT_STROKE:SolidColorStroke = new SolidColorStroke(0xaaaaaa);
		private static const HIGHLIGHTED_STROKE:SolidColorStroke = new SolidColorStroke(0xff0000,2);
		private static const SEMIHIGHLIGHTED_STROKE:SolidColorStroke = new SolidColorStroke(0xff0000,1,0.5);
		
		public static const EMPTY_FILL:SolidColor = new SolidColor(0xffffff);
		public static const TRANSPARENT_FILL:SolidColor = new SolidColor(0xffffff,0);

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
			
			_rect.fill = TRANSPARENT_FILL;
			_rect.stroke = DEFAULT_STROKE;
			
			this.addElement(_rect);
			
		}
		
		override public function set selected(value:Boolean):void{
			this._selected = value;
			if(value){
				this._rect.stroke = HIGHLIGHTED_STROKE;
			}else{
				this._rect.stroke = DEFAULT_STROKE;
			}
		}
		
		override public function set highlighted(value:Boolean):void{
			this._highlighted = value;
			if(value){
				this._rect.stroke = SEMIHIGHLIGHTED_STROKE;
			}else{
				this._rect.stroke = DEFAULT_STROKE;
			}
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
				break;
				default:
					colour(SquareCell.EMPTY_FILL);
			}
		}

		public static function get defaultStroke():SolidColorStroke
		{
			return DEFAULT_STROKE;
		}


	}
}