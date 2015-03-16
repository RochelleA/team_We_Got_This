package
{
	import flash.events.MouseEvent;
			
	public class SquareGrid extends TrafficGrid
	{
		private var _side:int = 20;
		private var downX:int;
		private var downY:int;
		
		private var currentX:int, currentY:int;
		
		private var lineCells:Array = new Array();

		public function SquareGrid()
		{
			trace('create');
			super();
		}
		
		override public function init(width:int, height:int):void
		{
			trace('init');
						
			this._cells = new Array(height);
			
			for(var i:int=0; i<height; i++){
				var row:Array = new Array(width);
				for (var j:int=0; j<width; j++){
					var cell:ISquareCell = new SquareCell(j, i, side);
					cell.addEventListener(MouseEvent.CLICK, onCellClick);
					this.addElement(cell);
					cell.x = j * side;
					cell.y = i * side;
					
					row[j] = cell;
				}
				cells[i] = row;
			}
			
			this.addEventListener(MouseEvent.MOUSE_DOWN, onMouseDown);
			this.addEventListener(MouseEvent.MOUSE_UP, onMouseUp);
			
		}
		
		protected function onMouseUp(event:MouseEvent):void
		{
			mouseUp();
		}
		
		protected function onMouseDown(event:MouseEvent):void
		{
			if(event.target is ICell){
				var c:ICell = event.target as ICell;
				trace('mouse down on grid', c.xPos,c.yPos);
				downX = c.xPos;
				downY = c.yPos;
			}
			
			for each (var row:Array in cells){
				for each (var cell:SquareCell in row){
					cell.prevType = cell.type;
				}
			}
			
			this.addEventListener(MouseEvent.MOUSE_MOVE, onMouseMove);
			
		}
		
		protected function onMouseMove(event:MouseEvent):void
		{
			if(event.target is ICell){
				var c:ICell = event.target as ICell;
				
				if(currentX != c.xPos || currentY != c.yPos){
					currentX = c.xPos;
					currentY = c.yPos;
					trace('mouse move', c.xPos,c.yPos);
					
					plotLine2(downX, downY, c.xPos, c.yPos);
				}
				
			}
		}
		
		private function eraseCells(cells:Array):void{
			for (var i:int = 0; i<cells.length; i++){
				if(!cells[i]){
					continue;
				}
				var cell:SquareCell = cells[i] as SquareCell;
				cell.type = cell.prevType;
			}
		}
		private function drawCells(cells:Array):void{
			for (var i:int = 0; i<cells.length; i++){
				if(!cells[i]){
					continue;
				}
				(cells[i] as SquareCell).type = Cell.ROAD;
			}
		}
		/**
		 * @author
		 * @source http://groups.csail.mit.edu/graphics/classes/6.837/F99/grading/asst2/turnin/rdror/Bresenham.java
		 */
		private function plotLine2(x1:int, y1:int, x2:int, y2:int):void {
			trace('plot line');
			eraseCells(lineCells);
			lineCells.length = 0;
			
			// If slope is outside the range [-1,1], swap x and y
			var xy_swap:Boolean = false;
			
			var temp:int;
			
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
					lineCells.push(this._cells[x][y]); //view.setPixel(y,x);
				}else{
					lineCells.push(this._cells[y][x]); //else view.setPixel(x,y);
				}				
				
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
				
			if (xy_swap){
				lineCells.push(this._cells[x][y]); //view.setPixel(y,x)
			}else{
				lineCells.push(this._cells[y][x]); //else view.setPixel(x,y);
			}
				//view.setPixel(y,x);
			//else view.setPixel(x,y);
			drawCells(lineCells);
				
		}
		
		/**
		 * Event Listener for Cell Clicks
		 */
		protected function onCellClick(e:MouseEvent):void{
			var cell:ISquareCell = e.target as ISquareCell;
			trace (cell.xPos, cell.yPos, cell.type);
			if(cell.type == Cell.EMPTY){
				cell.type = Cell.ROAD;
			}else{
				cell.type = Cell.EMPTY;
			}
		}

		public function get side():int
		{
			return _side;
		}

		public function set side(value:int):void
		{
			_side = value;
			trace('set side');
		}

		
		public function mouseUp():void
		{
			lineCells.length = 0;
			this.removeEventListener(MouseEvent.MOUSE_MOVE, onMouseMove);
		}
	}
}
