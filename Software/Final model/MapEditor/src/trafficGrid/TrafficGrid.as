package trafficGrid
{
	import flash.events.MouseEvent;
	
	import grid.AdvancedGrid;
	import grid.Cell;
	import grid.ICustomShape;
	
	import instruments.Instruments;
	
	public class TrafficGrid extends AdvancedGrid
	{
		private var _instrument:String;
		
		public static const ROUNDABOUT_SHAPE:ICustomShape = new Roundabout();
		
		public function TrafficGrid()
		{
			super();
		}
		override protected function onMouseUp(event:MouseEvent):void
		{
			super.onMouseUp(event);
			if(instrument == Instruments.SELECT){
				if(selectedCell == _currentCell){
					deselectCell();
				}else{
					selectCell(_currentCell);
				}
			}
		}
		
		override protected function performAction(cells:Array):void{
			for each (var c:Cell in cells){
				switch(instrument){
					case Instruments.ROAD_EAST:
						c.tempType = TrafficCellType.ROAD_EAST;
						break;
					case Instruments.ROAD_SOUTH:
						c.tempType = TrafficCellType.ROAD_SOUTH;
						break;
					case Instruments.ROAD_WEST:
						c.tempType = TrafficCellType.ROAD_WEST;
						break;
					case Instruments.ROAD_NORTH:
						c.tempType = TrafficCellType.ROAD_NORTH;
						break;
					case Instruments.ROAD_JUNCTION:
						c.tempType = TrafficCellType.ROAD_JUNCTION;
						break;
					case Instruments.ROUNDABOUT:
						c.tempType = TrafficCellType.ROUNDABOUT;
						break;
					case Instruments.RUBBER:
						c.tempType = TrafficCellType.EMPTY;
						break;
					case Instruments.SELECT:
						trace('show select');
						//super.showSelectCell(c.xPos, c.yPos);
						break;
				}
			}
		}
		override protected function init(width:int, height:int):void{
			for(var i:int=0; i<height; i++){
				var row:Array = new Array(width);
				for (var j:int=0; j<width; j++){
					var cell:TrafficCell = new TrafficCell(j,i);
					cell.setStyle("side", this.getStyle("cellSide"));
					cell.type = TrafficCellType.EMPTY;
					row[j] = cell;
					allCells[i*width+j] = cell;
					
					this.addElement(cell);
				}
				cells[i] = row;
			}
		}

		public function get instrument():String
		{
			return _instrument;
		}

		public function set instrument(value:String):void
		{
			_instrument = value;
			//reset properties
			deselectCell();
			super.removeCustomShape();
			
			if(value == Instruments.SELECT || value == Instruments.ROUNDABOUT){
				super.mouseDownAllowed = false;
			}else{
				super.mouseDownAllowed = true;
			}
			if(value == Instruments.ROUNDABOUT){
				super.customShape = TrafficGrid.ROUNDABOUT_SHAPE;
			}
		}
		
	}
}