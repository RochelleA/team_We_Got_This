package
{

	/**
	 * A simple 2D grid
	 */
	public class SimpleSquareGrid extends VisualGrid
	{
		protected var side:int = 20;
		
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
					var cell:ISquareCell = new SquareCell(j, i, side);
					
					cell.x = j * side;
					cell.y = i * side;
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