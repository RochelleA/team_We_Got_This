package
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import spark.components.Group;
		
	/**
	 * An extension to grid with ability to draw lines, roundabouts and listen for mouse
	 * event. 
	 */
	[Event(name="cellSelected", type="flash.events.Event")]
	
	public class SquareGrid extends SimpleSquareGrid
	{
		private var downX:int, downY:int; 
		
		private var tempCells:Array = new Array();
		private var _selectedCell:IVisualCell;
		private var cellToSelect:IVisualCell;
		
		private var dummySelectCell:IVisualCell;
		
		private var _instrument:int;
		private var currentCell:ICell;
		private var mouseDown:Boolean;
		public var roadDir:String;
		
		public function SquareGrid()
		{
			trace('create advanced square grid');
			super();
			
			dummySelectCell = new SquareCell(1,1,_side);
			dummySelectCell.selected = true;
			(dummySelectCell as Group).mouseEnabled = false;
			hideDummySelectCell();
			
		}
		
		private function hideDummySelectCell():void{
			dummySelectCell.visible = false;
			dummySelectCell.x = -100;
			dummySelectCell.y = -100;
		}
		
		override public function init(width:int, height:int):void
		{
			trace('advanced grid init');
			
			super.init(width, height);
			//this.addElement(dummyHighlightCell);
			this.addElement(dummySelectCell);
			
			this.addEventListener(MouseEvent.MOUSE_DOWN, onMouseDown);
			this.addEventListener(MouseEvent.MOUSE_UP, onMouseUp);
			this.addEventListener(MouseEvent.MOUSE_MOVE, onMouseMove);
			
			this.addEventListener(MouseEvent.ROLL_OUT, onMouseOut);
			
		}
		
		protected function onMouseOut(event:MouseEvent):void
		{
			trace('mouse out');
			if(!mouseDown){
				restoreCells(tempCells);
			}
		}
		
		protected function onMouseUp(event:MouseEvent):void
		{
			mouseUp();
			mouseDown = false;
		}
		
		protected function onMouseDown(event:MouseEvent):void
		{
			if(event.target is ICell){
				var c:ICell = event.target as ICell;
				trace('mouse down on grid', c.xPos,c.yPos);
				downX = c.xPos;
				downY = c.yPos;
			}
			mouseDown = true;
		}
		
		//private function log(s:String):void{
			//(this.parentApplication as MapEditor).log.text = (this.parentApplication as MapEditor).log.text+s+"\n";
			//(this.parentApplication as MapEditor).log.scrollToRange(int.MAX_VALUE, int.MAX_VALUE); 
		//}
		
		protected function onMouseMove(event:MouseEvent):void
		{
			if( !(event.target is ICell) ){
				return;
			}
			
			var c:IVisualCell = event.target as IVisualCell;			
			if(c == currentCell){
				return;
			}else{
				currentCell = c;
			}
			
			restoreCells(tempCells);
			switch (instrument){
				case MapEditor.ROAD:
					if(mouseDown){
						plotLine2(downX, downY, c.xPos, c.yPos);
					}else{
						c.prevRoadDir = c.roadDir;
						c.roadDir = this.roadDir;
						c.prevType = c.type;
						c.tempType = CellType.ROAD;
						tempCells.push(c);
					}
					break;
				case MapEditor.RB:
					this.putRoundabout(c.xPos-5, c.yPos-5);
					break;
				case MapEditor.SELECT:					
					cellToSelect = c;
					break;
				case MapEditor.RUBBER:
					c.prevType = c.type;
					c.tempType = CellType.EMPTY;
					tempCells.push(c);
					
			}
		}
		
		private function putRoundabout(x:int, y:int):void{

			var rb:Roundabout = (this.parentApplication as MapEditor).rb;
			
			//out of bounds check
//			if(x+10>this.gridWidth){
//				x = this.gridWidth - 10;
//			}
//			if(y+10>this.gridHeight){
//				y = this.gridHeight - 10;
//			}
//			if(rbX == x && rbY == y){
//				return;
//			}else{
//				rbX = x;
//				rbY = y;
//				
//			}
			
			//log(' Move roundabout to '+rbX+':'+rbY);
			restoreCells(tempCells);
			
			for each (var cell:IVisualCell in rb.rbCells){
				try{
					var gridCell:IVisualCell = this.cells[y+cell.yPos][x+cell.xPos] as IVisualCell;
					cell.prevRoadDir = this.roadDir;
					cell.roadDir = this.roadDir;
					gridCell.prevType = gridCell.type;
					gridCell.tempType = CellType.RB;
					tempCells.push(gridCell);
				}catch(e:Error){
					trace('error'); //cell is outside the grid
				}
				
			}
		}
		
		private static function restoreCells(cells:Array):void{
			for each (var cell:IVisualCell in cells){
				cell.roadDir = cell.prevRoadDir;
				cell.type = cell.prevType;
			}
			cells.length = 0;
		}
		private static function drawCells(cells:Array):void{
			for each (var cell:IVisualCell in cells){
				cell.tempType = CellType.ROAD;
			}
		}
		
		/**
		 * @author
		 * @source http://groups.csail.mit.edu/graphics/classes/6.837/F99/grading/asst2/turnin/rdror/Bresenham.java
		 */
		private function plotLine2(x1:int, y1:int, x2:int, y2:int):void {
			trace('plot line');
			
			// If slope is outside the range [-1,1], swap x and y
			var xy_swap:Boolean = false;
			
			var temp:int;
			
			var cell:IVisualCell;
			
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
					cell = this._cells[x][y];
				}else{
					cell = this._cells[y][x];
				}
				cell.prevRoadDir = this.roadDir;
				if(cell.type != CellType.ROAD){
					cell.roadDir = this.roadDir;
				}else{
					cell.roadDir = SimpleSquareGrid.JUNCTION;
				}
				cell.prevType = cell.type;
				tempCells.push(cell);
				
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
				cell = this._cells[x][y];
			}else{
				cell = this._cells[y][x];
			}
			
			cell.prevRoadDir = this.roadDir;
			if(cell.type != CellType.ROAD){
				cell.roadDir = this.roadDir;
			}else{
				cell.roadDir = SimpleSquareGrid.JUNCTION;
			}
			cell.prevType = cell.type;
			tempCells.push(cell);
			
			//view.setPixel(y,x);
			//else view.setPixel(x,y);
			drawCells(tempCells);
				
		}
		
		public function mouseUp():void
		{
			if(instrument == MapEditor.SELECT){
				this.setSelectedCell(cellToSelect);
				return;
			}
			for each (var c:IVisualCell in tempCells){
				c.type = c.tempType;
			}
			tempCells.length = 0;
		}

		public function get selectedCell():IVisualCell
		{
			return _selectedCell;
		}
		
		private function setSelectedCell(value:IVisualCell):void{
			if(!value || value == _selectedCell){
				_selectedCell = null;
				hideDummySelectCell();
			}else{
				dummySelectCell.x = value.x;
				dummySelectCell.y = value.y;
				dummySelectCell.visible = true;
				this._selectedCell = value;
			}
			this.dispatchEvent(new Event("cellSelected"));
			
		}

		public function get instrument():int
		{
			return _instrument;
		}

		public function set instrument(value:int):void
		{
			_instrument = value;
			if(value != MapEditor.SELECT){
				hideDummySelectCell();
				this.setSelectedCell(null);
			}
		}


	}
}
