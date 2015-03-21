package
{
	public class Roundabout extends SimpleSquareGrid
	{
		private var _rbCells:Array = [];

		public function Roundabout()
		{
			super();
			super.init(10,10);
			draw();
		}
		private function addCells(a:Array):void{ 
			for each (var cell:ICell in a){
				cell.type = CellType.RB;
				_rbCells.push(cell);
			}
		}
		private function draw():void{
			this.addCells(
			   [cells[0][3],cells[0][4],cells[0][5],cells[0][6],
				cells[2][1],cells[2][2],cells[2][3],cells[2][6],cells[2][7],cells[2][8],
				cells[3][0],cells[3][1],cells[3][2],cells[3][7],cells[3][8],cells[3][9],
				cells[4][0],cells[4][1],cells[4][8],cells[4][9],
				cells[5][0],cells[5][1],cells[5][8],cells[5][9],
				cells[6][0],cells[6][1],cells[6][2],cells[6][7],cells[6][8],cells[6][9],
				cells[7][1],cells[7][2],cells[7][3],cells[7][6],cells[7][7],cells[7][8],
				cells[9][3],cells[9][4],cells[9][5],cells[9][6]
			]);
			
			for (var i:int = 2; i<8; i++){
				addCells([cells[1][i], cells[8][i]]);
			}		
		}

		public function get rbCells():Array
		{
			return _rbCells;
		}

	}
}