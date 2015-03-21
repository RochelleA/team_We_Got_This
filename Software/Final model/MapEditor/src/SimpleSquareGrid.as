package
{

	/**
	 * A simple 2D grid
	 */
	public class SimpleSquareGrid extends VisualGrid
	{
		protected var _side:int = 20;
		
		public static const NORTH:String = "north";
		public static const EAST:String = "eash";
		public static const SOUTH:String = "south";
		public static const WEST:String = "west";
		public static const JUNCTION:String = "junction";
		public static const ROUNDABOUT:String = "roundabout";
		
		public function SimpleSquareGrid()
		{
			super();
		}
		
		override public function init(width:int, height:int):void
		{
			trace('simple grid init');
			_allCells = new Array(width*height);
			
			this._cells = new Array(height);
			
			for(var i:int=0; i<height; i++){
				var row:Array = new Array(width);
				for (var j:int=0; j<width; j++){
					var cell:IVisualCell = new SquareCell(j, i, _side);
					
					cell.x = j * _side;
					cell.y = i * _side;
					//cell.type = CellType.EMPTY;
					
					this.addElement(cell);
					
					row[j] = cell;
					_allCells[i*width+j] = cell;
				}
				cells[i] = row;
			}
			
			this.gridWidth = width;
			this.gridHeight = height;
			
		}
	}
}