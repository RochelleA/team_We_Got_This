package
{

	/**
	 * A simple 2D grid
	 */
	public class SimpleSquareGrid extends VisualGrid
	{
		protected var _side:int = 20;
		
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
					cell.type = CellType.EMPTY;
					
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