package grid
{
	import flash.events.Event;
	import flash.events.MouseEvent;
		
	import instruments.Instruments;
	import old_files.ICell;
	import trafficGrid.TrafficCellType;
	
	[Event(name="cellSelected", type="flash.events.Event")]
	[Style(name="cellSide", inherit="yes", type="int")]
	public class SquareGrid extends Grid
	{
		private var downX:int, downY:int; 
		
		private var tempCells:Array = new Array();
		
		private var _selectedCell:Cell;
		private var cellToSelect:Cell;
		
		private var dummySelectCell:Cell;
		
		private var _instrument:String;
		private var _originalInstrument:String;

		private var currentCell:Cell;
		private var mouseDown:Boolean;
		public var roadDir:String;
		private var _roadType:String;
		
		public function SquareGrid()
		{
			trace('create advanced square grid');
			super();
			
			dummySelectCell = new Cell(0,0);
			dummySelectCell.mouseEnabled = false;
			dummySelectCell.mouseChildren = false;
			dummySelectCell.type = "dummySelect";
			hideDummySelectCell(dummySelectCell);
			
		}
		
		private static function hideDummySelectCell(dc:Cell):void{
			dc.visible = false;
			dc.x = -100;
			dc.y = -100;
		}
		
		override public function init(width:int, height:int):void{
			super.init(width, height);
			this.width = width*this.getStyle("cellSide")+1;
			this.height = height*this.getStyle("cellSide")+1;
			this.addElement(dummySelectCell);
			dummySelectCell.setStyle("side", this.getStyle("cellSide"));
			
			this.addEventListener(MouseEvent.MOUSE_DOWN, onMouseDown);
			this.addEventListener(MouseEvent.MOUSE_UP, onMouseUp);
			this.addEventListener(MouseEvent.MOUSE_MOVE, onMouseMove);
			
			this.addEventListener(MouseEvent.ROLL_OUT, onMouseOut);
		}
		
		protected function onMouseMove(event:MouseEvent):void
		{
			//trace('mouse move');
			if( !(event.target is CellSkin) ){
				return;
			}
			
			var c:Cell = (event.target as CellSkin).owner as Cell;
//			if(c.type == "dummySelect"){
//				return;
//			}
			//trace(c);
			if(c == currentCell){
				return;
			}else{
				currentCell = c;
			}
			
			restoreCells(tempCells);
			
			var newType:String;
			
			switch (instrument){
				case Instruments.ROAD:
					newType = this._roadType;
					if(mouseDown){
						tempCells = plotLine(downX, downY, c.xPos, c.yPos, super._cells);
					}else{
						tempCells.push(c);
					}
					break;
				case Instruments.ROUNDABOUT:
					newType = trafficGrid.TrafficCellType.ROUNDABOUT;
					tempCells = putRoundabout(c.xPos-5, c.yPos-5, super._cells);
					break;
				case Instruments.SELECT:					
					cellToSelect = c;
					break;
				case Instruments.RUBBER:
					newType = trafficGrid.TrafficCellType.EMPTY;
					tempCells.push(c);
			}
			for each (c in tempCells){
				c.temp = true;
				c.type = newType;
			}
		}
		
		/**
		 * Calculates cells of a roundabout at particular coordinates.
		 * @param x x center of roundabout
		 * @param y y center of roundabout
		 * @param _cell 2D array of the grid
		 * @return array with cells of roundabout
		 */
		private static function putRoundabout(x:int, y:int, _cells:Array):Array{
			var tempCells:Array = [];
			
			for each (var cell:ICell in MapEditor.rb.rbCells){
				try{
					var gridCell:Cell = _cells[y+cell.yPos][x+cell.xPos] as Cell;
					if(gridCell){
						tempCells.push(gridCell);
					}
				}catch(e:Error){
					trace('error'); //cell is outside the grid
				}
				
			}
			return tempCells;
		}
		
		private static function restoreCells(cells:Array):void{
			//trace('restore', cells.length);
			for each (var cell:Cell in cells){
				cell.restore();
			}
			cells.length = 0;
		}
		
		protected function onMouseOut(event:MouseEvent):void
		{
			trace('mouse out');
			currentCell = null;
			if(!mouseDown){
				restoreCells(tempCells);
			}
		}
		
		protected function onMouseUp(event:MouseEvent):void
		{
			//mouseUp();
			mouseDown = false;
			
			if(instrument == Instruments.SELECT){
				setSelectedCell(cellToSelect);
				return;
			}
			
			for each (var c:Cell in tempCells){
				c.confirmNewType();
			}
			tempCells.length = 0;
		}
		
		private function setSelectedCell(cellToSelect:Cell):void
		{
			trace(cellToSelect);
			if(!cellToSelect || cellToSelect == _selectedCell){
				_selectedCell = null;
				hideDummySelectCell(dummySelectCell);
			}else{
				dummySelectCell.x = cellToSelect.x;
				dummySelectCell.y = cellToSelect.y;
				dummySelectCell.visible = true;
				this._selectedCell = cellToSelect;
			}
			this.dispatchEvent(new Event("cellSelected"));
		}
		
		protected function onMouseDown(event:MouseEvent):void
		{
			trace('mouse down');

			mouseDown = true;
			if(event.target is CellSkin){
				var c:Cell = (event.target as CellSkin).owner as Cell;
				downX = c.xPos;
				downY = c.yPos;
			}
			
		}

		public function get instrument():String
		{
			return _instrument;
		}

		public function set instrument(value:String):void
		{
			_instrument = value;

			switch (value){
				case Instruments.ROAD_EAST:
					_instrument = Instruments.ROAD;
					this._roadType = trafficGrid.TrafficCellType.ROAD_EAST;
					break;
				case Instruments.ROAD_SOUTH:
					_instrument = Instruments.ROAD;
					this._roadType = trafficGrid.TrafficCellType.ROAD_SOUTH;
					break;
				case Instruments.ROAD_NORTH:
					_instrument = Instruments.ROAD;
					this._roadType = trafficGrid.TrafficCellType.ROAD_NORTH;
					break;
				case Instruments.ROAD_WEST:
					_instrument = Instruments.ROAD;
					this._roadType = trafficGrid.TrafficCellType.ROAD_WEST;
					break;
				case Instruments.ROAD_JUNCTION:
					_instrument = Instruments.ROAD;
					this._roadType = trafficGrid.TrafficCellType.ROAD_JUNCTION;
					break;
			}
			this.setSelectedCell(null); //stop selecting a cell
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
			var tempCells:Array = [];
			
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
				cell = _cells[x][y];
			}else{
				cell = _cells[y][x];
			}
			
			tempCells.push(cell);
			
			//view.setPixel(y,x);
			//else view.setPixel(x,y);
			return tempCells;
			
		}

		public function get selectedCell():Cell
		{
			return _selectedCell;
		}


	}
}