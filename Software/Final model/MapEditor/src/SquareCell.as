package
{
	//import flash.display.Sprite;
		
	import mx.graphics.BitmapFill;
	import mx.graphics.SolidColor;
	import mx.graphics.SolidColorStroke;
	
	import spark.primitives.Rect;

	public class SquareCell extends VisualCell
	{
		[Embed(source="assets/arrow-west.png")]
		protected const ROAD_FILL_WEST:Class;
		[Embed(source="assets/arrow-east.png")]
		protected const ROAD_FILL_EAST:Class;
		[Embed(source="assets/arrow-north.png")]
		protected const ROAD_FILL_NORTH:Class;
		[Embed(source="assets/arrow-south.png")]
		protected const ROAD_FILL_SOUTH:Class;
		[Embed(source="assets/junction2.png")]
		protected const ROAD_FILL_JUNCTION:Class;
		
		private var _rect:Rect;
		
		public static const DEFAULT_STROKE:SolidColorStroke = new SolidColorStroke(0xaaaaaa);
		private static const HIGHLIGHTED_STROKE:SolidColorStroke = new SolidColorStroke(0xff0000,2);
		private static const SEMIHIGHLIGHTED_STROKE:SolidColorStroke = new SolidColorStroke(0xff0000,1,0.5);
		
		public static const EMPTY_FILL:SolidColor = new SolidColor(0xffffff);
		public static const TRANSPARENT_FILL:SolidColor = new SolidColor(0xffffff,0);

		private static const SOLID_ROAD_FILL:SolidColor = new SolidColor(0x3b5f91);
		
		private var ROAD_BTMP_FILL:BitmapFill = new BitmapFill();
		
		public var partOfRoundabout:Boolean = false;
		private var _roadWest:RoadWestRect;
		
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
			//_rect.fill = ROAD_BTMP_FILL;
			//this.addElement(_roadWest);
			
		}
		
		override public function set selected(value:Boolean):void{
			this._selected = value;
			if(value){
				this._rect.stroke = HIGHLIGHTED_STROKE;
			}else{
				this._rect.stroke = DEFAULT_STROKE;
			}
		}
		
//		override public function set highlighted(value:Boolean):void{
//			this._highlighted = value;
//			if(value){
//				this._rect.stroke = SEMIHIGHLIGHTED_STROKE;
//			}else{
//				this._rect.stroke = DEFAULT_STROKE;
//			}
//		}
		
		override public function set tempType(value:int):void{
			this._tempType = value;
			colour(value, 0.5);
//			switch (value){
//				case CellType.ROAD:
//				case CellType.RB:
//					
//					break;
//				default:
//					colour(CellType.EMPTY, 0.5);
//			}
		}
		
		private function colour(colour:int, alpha:Number = 1):void{
			if(colour == CellType.ROAD){
				switch (this.roadDir){
					case SimpleSquareGrid.EAST:
						ROAD_BTMP_FILL.source = ROAD_FILL_EAST;
						break;
					case SimpleSquareGrid.SOUTH:
						ROAD_BTMP_FILL.source = ROAD_FILL_SOUTH;
						break;
					case SimpleSquareGrid.WEST:
						ROAD_BTMP_FILL.source = ROAD_FILL_WEST;
						break;
					case SimpleSquareGrid.NORTH:
						ROAD_BTMP_FILL.source = ROAD_FILL_NORTH;
						break;
					case SimpleSquareGrid.JUNCTION:
						ROAD_BTMP_FILL.source = ROAD_FILL_JUNCTION;
						break;
				}
				
				ROAD_BTMP_FILL.alpha = alpha;
				this._rect.fill = ROAD_BTMP_FILL;
			}else if(colour == CellType.EMPTY){
				this._rect.fill = EMPTY_FILL;
			}else if(colour == CellType.RB){
				this._rect.fill = SOLID_ROAD_FILL;
			}
		}
		
		override public function set type(value:int):void{
			this._type = value;
			this.colour(value, 1);
//			switch (value){
//				case CellType.ROAD:
//					colour(CellType.ROAD);
//					break;
//				case CellType.RB:
//					colour(CellType.RB);
//				break;
//				default:
//					colour(CellType.EMPTY);
//			}
		}

		public static function get defaultStroke():SolidColorStroke
		{
			return DEFAULT_STROKE;
		}


	}
}