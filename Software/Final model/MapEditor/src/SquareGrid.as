package
{
	import flash.events.MouseEvent;
		
	/**
	 * An extension to grid with ability to draw lines, roundabouts and listen for mouse
	 * event. 
	 */
	public class SquareGrid extends SimpleSquareGrid
	{
		private var _side:int = 20;
		private var downX:int;
		private var downY:int;
		
		private var currentX:int, currentY:int;
		
		private var tempCells:Array = new Array();
		
		private var dragginRoundabout:Boolean = true;
		private var realDragging:Boolean = false;
		private var rbX:int, rbY:int;
		
		public function SquareGrid()
		{
			trace('create');
			super();
		}
		
		override public function init(width:int, height:int):void
		{
			trace('grid init');
			super.init(width, height);
			//super.init(width, height);
			for each(var c:VisualCell in this.allCells){	
				//trace(c);
				c.addEventListener(MouseEvent.CLICK, onCellClick);

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
			realDragging = false;
			if(event.target is ICell){
				var c:ICell = event.target as ICell;
				trace('mouse down on grid', c.xPos,c.yPos);
				downX = c.xPos;
				downY = c.yPos;
			}
			
			for each (var cell:ICell in allCells){
				cell.prevType = cell.type;
			}
			
			this.addEventListener(MouseEvent.MOUSE_MOVE, onMouseMove);
			
		}
		
		private function log(s:String):void{
			(this.parentApplication as MapEditor).log.text = (this.parentApplication as MapEditor).log.text+s+"\n";
			(this.parentApplication as MapEditor).log.scrollToRange(int.MAX_VALUE, int.MAX_VALUE); 
		}
		
		protected function onMouseMove(event:MouseEvent):void
		{
			if(event.target is ICell){
				var c:ICell = event.target as ICell;			
				if(currentX == c.xPos && currentY == c.yPos){
					return;
				}else{
					currentX = c.xPos;
					currentY = c.yPos;
					
				}
				
				if(dragginRoundabout){
					realDragging = true;
					putRoundabout(c.xPos,c.yPos);
				}else{ //plot a line
					trace('mouse move', c.xPos,c.yPos);
					
					plotLine2(downX, downY, c.xPos, c.yPos);
				}
			}
		}
		
		private function putRoundabout(x:int, y:int):void{

			var rb:Roundabout = (this.parentApplication as MapEditor).rb;
			
			if(x+10>this.gridWidth){
				x = this.gridWidth - 10;
			}
			if(y+10>this.gridHeight){
				y = this.gridHeight - 10;
			}
			if(rbX == x && rbY == y){
				return;
			}else{
				rbX = x;
				rbY = y;
				
			}
			
			log(' Move roundabout to '+rbX+':'+rbY);
			restoreCells(tempCells);
			
			for each (var cell:ICell in rb.rbCells){
				var gridCell:IVisualCell = this.cells[rbY+cell.yPos][rbX+cell.xPos] as IVisualCell;
				//gridCell.prevType = cell.type;
				gridCell.tempType = CellType.RB;
				tempCells.push(gridCell);
			}
		}
		
		private function restoreCells(cells:Array):void{
			for each (var cell:ICell in cells){
				cell.type = cell.prevType;
			}
			cells.length = 0;
		}
		private function drawCells(cells:Array):void{
			for each (var cell:ICell in cells){
				cell.type = CellType.ROAD;
			}
		}
		
		/**
		 * @author
		 * @source http://groups.csail.mit.edu/graphics/classes/6.837/F99/grading/asst2/turnin/rdror/Bresenham.java
		 */
		private function plotLine2(x1:int, y1:int, x2:int, y2:int):void {
			trace('plot line');
			restoreCells(tempCells);
			
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
					tempCells.push(this._cells[x][y]); //view.setPixel(y,x);
				}else{
					tempCells.push(this._cells[y][x]); //else view.setPixel(x,y);
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
				tempCells.push(this._cells[x][y]); //view.setPixel(y,x)
			}else{
				tempCells.push(this._cells[y][x]); //else view.setPixel(x,y);
			}
				//view.setPixel(y,x);
			//else view.setPixel(x,y);
			drawCells(tempCells);
				
		}
		
		/**
		 * Event Listener for Cell Clicks
		 */
		protected function onCellClick(e:MouseEvent):void{
			var cell:ICell = e.target as ICell;
			trace (cell.xPos, cell.yPos, cell.type);
			if(cell.type == VisualCell.EMPTY){
				cell.type = VisualCell.ROAD;
				log('Add road at '+downX+':'+downY);
			}else{
				cell.type = VisualCell.EMPTY;
				log('Remove road at '+downX+':'+downY);
			}
			
		}
		
		public function mouseUp():void
		{
			if(realDragging){
				log('Place roundabout at '+rbX+':'+rbY);
			}
			tempCells.length = 0;
			this.removeEventListener(MouseEvent.MOUSE_MOVE, onMouseMove);
		}
	}
}
