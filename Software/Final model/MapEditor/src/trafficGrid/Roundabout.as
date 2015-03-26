package trafficGrid
{
	import grid.BasicCell;
	import grid.ICell;
	import grid.ICustomShape;

	public class Roundabout implements ICustomShape
	{
		private var _cells:Array = [];
		private var _width:int;
		private var _height:int;
		private var grid:Array;

		public function Roundabout()
		{
			this._width = 10;
			this._height = 10;
			grid = new Array(_height);
			for (var i:int = 0; i<height; i++){
				var row:Array = new Array(_width);
				for (var j:int = 0; j<width; j++){
					var cell:ICell = new BasicCell(j,i);
					row[j] = cell;
				}
				grid[i] = row;
			}
			draw();
		}
		private function addCells(a:Array, type:String):void{ 
			for each (var cell:BasicCell in a){
				var newBasicCell:ICell = new BasicCell(cell.xPos, cell.yPos, type);
				//cell.type = type;
				trace('add cells, set type', newBasicCell.type);
				_cells.push(newBasicCell);
			}
		}
		private function draw():void{
			this.addCells(
			   [grid[0][3],grid[0][4],grid[0][5],grid[0][6],
				grid[2][1],grid[2][2],grid[2][3],grid[2][6],grid[2][7],grid[2][8],
				grid[3][0],grid[3][1],grid[3][2],grid[3][7],grid[3][8],grid[3][9],
				grid[4][0],grid[4][1],grid[4][8],grid[4][9],
				grid[5][0],grid[5][1],grid[5][8],grid[5][9],
				grid[6][0],grid[6][1],grid[6][2],grid[6][7],grid[6][8],grid[6][9],
				grid[7][1],grid[7][2],grid[7][3],grid[7][6],grid[7][7],grid[7][8],
				grid[9][3],grid[9][4],grid[9][5],grid[9][6]
			], TrafficCellType.ROUNDABOUT);
			this.addCells(
				[grid[2][4],grid[2][5],grid[7][4],grid[7][5],
					grid[3][3],grid[3][4],grid[3][5],grid[3][6],
					grid[6][3],grid[6][4],grid[6][5],grid[6][6],
					grid[4][2],grid[4][3],grid[4][4],grid[4][5],grid[4][6],grid[4][7],
					grid[5][2],grid[5][3],grid[5][4],grid[5][5],grid[5][6],grid[5][7]
				], TrafficCellType.ROUNDABOUT_INSIDE);
			
			for (var i:int = 2; i<8; i++){
				addCells([grid[1][i], grid[8][i]], TrafficCellType.ROUNDABOUT);
			}
		}
		
		public function get cells():Array{
			return _cells;
		}
		public function get width():int{
			return _width;
		}
		public function get height():int{
			return _height;
		}

	}
}