package grid
{
	import flash.events.MouseEvent;
		
	/**
	 * Andvanced Grid can work with mouse events, draw lines and place arbitrary figures.
	 */
	public class AdvancedGrid extends Grid
	{
		private var currentCell:Cell;
		private var tempCells:Array = [];
		private var mouseDown:Boolean;

		private var downX:int, downY:int;
		
		private var mouseDownAllowed:Boolean = true;
		private var _customShape:ICustomShape;
		
		public function AdvancedGrid()
		{
			super();
		}
		
		override protected function init(width:int, height:int):void{
			trace('init advanced grid');
			super.init(width, height);
			
			this.addEventListener(MouseEvent.MOUSE_DOWN, onMouseDown);
			//this.addEventListener(MouseEvent.MOUSE_UP, onMouseUp);
			this.addEventListener(MouseEvent.MOUSE_MOVE, onMouseMove);
			
			this.addEventListener(MouseEvent.ROLL_OUT, onMouseOut);
		}
		
		protected function onMouseOut(event:MouseEvent):void
		{
			currentCell = null;
			super.hideSelectCell();
		}
		
		protected function performAction(cells:Array):void{
			for each (var c:Cell in cells){
				super.showSelectCell(c.xPos, c.yPos); // default action
				//c.temp = true;
				//c.type = newType;
			}
		}
		private function onMouseMove(event:MouseEvent):void
		{
			var c:Cell = super.getCellByCoordinated(event.localX, event.localY);
			
			if(c == null || c == currentCell){
				return;
			}else{
				currentCell = c;
				trace(c);
			}
			
			restoreCells(tempCells);
			
			var newType:String;
			
			if(mouseDown && mouseDownAllowed){
				tempCells = plotLine(downX, downY, c.xPos, c.yPos, super._cells);
			}else if(customShape){
				tempCells = plotCustomShape(c.xPos-customShape.width/2, c.yPos-customShape.height/2, _cells, customShape);
			}else{
				tempCells.push(c);
			}
			
			performAction(tempCells);
			
		}
		
		/**
		 * Calculates cells of a roundabout at particular coordinates.
		 * @param x x center of roundabout
		 * @param y y center of roundabout
		 * @param _cell 2D array of the grid
		 * @return array with cells of roundabout
		 */
		private static function plotCustomShape(x:int, y:int, grid:Array, customShape:ICustomShape):Array{
			var cells:Array = [];
			
			for each (var cell:ICell in customShape.cells){
				try{
					var gridCell:Cell = grid[y+cell.yPos][x+cell.xPos] as Cell;
					if(gridCell){
						cells.push(gridCell);
					}
				}catch(e:Error){
					trace('error'); //cell is outside the grid
				}
				
			}
			return cells;
		}
		
		private function onMouseDown(event:MouseEvent):void
		{
			trace('mouse down');
			
			mouseDown = true;
			var c:Cell = super.getCellByCoordinated(event.localX, event.localY);
			if(c == null){
				return;
			}
			downX = c.xPos;
			downY = c.yPos;
			
		}
		
		private static function restoreCells(cells:Array):void{
			for each (var cell:Cell in cells){
				cell.restore();
			}
			cells.length = 0;
		}
		
		/**
		 * Calculates which pixels (cells) are affected by a line
		 * and returns them in an array.
		 * @param x1 x coordinate of start cell
		 * @param x2 x coordinate of end cell
		 * @param y1 y coordinate of start cell
		 * @param y2 y coordinate of end cell
		 * @param _cells 2D array of cells
		 * 
		 * @return array of affected cells lying on the line
		 * @author http://groups.csail.mit.edu/graphics/classes/6.837/F99/grading/asst2/turnin/rdror/Bresenham.java
		 */
		private static function plotLine(x1:int, y1:int, x2:int, y2:int, _cells:Array):Array {
			trace('plot line');
			
			// If slope is outside the range [-1,1], swap x and y
			var xy_swap:Boolean = false;
			
			var temp:int;
			
			var cell:Cell;
			var cells:Array = [];
			
			if (Math.abs(y2 - y1) > Math.abs(x2 - x1)) {
				xy_swap = true;
				temp = x1;
				x1 = y1;
				y1 = temp;
				temp = x2;
				x2 = y2;
				y2 = temp;
			}
			// If line goes from right to left, swap the endpoints
			if (x2 - x1 < 0) {
				temp = x1;
				x1 = x2;
				x2 = temp;
				temp = y1;
				y1 = y2;
				y2 = temp;
			}
			
			var x:int,                       // Current x position
			y:int = y1,                  // Current y position
				e:Number = 0,                   // Current error
				m_num:int = y2 - y1,         // Numerator of slope
				m_denom:int = x2 - x1,       // Denominator of slope
				threshold:Number  = m_denom/2;  // Threshold between E and NE increment 
			
			for (x = x1; x < x2; x++) {
				if (xy_swap){
					cell = _cells[x][y];
				}else{
					cell = _cells[y][x];
				}
				
				cells.push(cell);
				
				e += m_num;
				
				// Deal separately with lines sloping upward and those
				// sloping downward
				if (m_num < 0) {
					if (e < -threshold) {
						e += m_denom;
						y--;
					}
				}
				else if (e > threshold) {
					e -= m_denom;
					y++;
				}
			}
			
			//add start cell
			if (xy_swap){
				cell = _cells[x][y];
			}else{
				cell = _cells[y][x];
			}
			
			cells.push(cell);
			
			//view.setPixel(y,x);
			//else view.setPixel(x,y);
			return cells;
			
		}

		public function get customShape():ICustomShape
		{
			return _customShape;
		}

		public function set customShape(value:ICustomShape):void
		{
			mouseDownAllowed = (value != null);
			_customShape = value;
		}

	}
}