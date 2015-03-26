package trafficGrid
{
	import flash.events.MouseEvent;
	
	import grid.AdvancedGrid;
	import grid.Cell;
	import grid.ICell;
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
				if(selectedCell == _currentCell || _currentCell == null){
					deselectCell();
				}else{
					selectCell(_currentCell);
				}
			}
		}
		
		public function getAsString(title:String):String{
			var s:String = "";
			//find size of 2d array
			var rows:int=_cells.length;
			var cols:int=_cells[0].length;
			
			var trafficLights:String = "";
			s += title + " "+super.widthInCells + "x"+super.heightInCells +"\n";
			s += "===\n"
			for (var r:int=0;r<rows;r++){
				for (var c:int=0;c<cols;c++){
					var cell:ITrafficCell = _cells[r][c] as ITrafficCell;
					
					s = s + cell.toNumber();
					if(cell.isEntry){
						s += "*";
					}else if(cell.isExit){
						s += "!";
					}
					s += ' ';//add current cell to string w/ a space after
					
					switch(cell.trafficLight){
						case "None":
							break;
						case TrafficLightColour.RED:
							trafficLights += "["+cell.xPos+";"+cell.xPos+"] 1500 RED\n"
							break;
						case TrafficLightColour.RED_AMBER:
							trafficLights += "["+cell.xPos+";"+cell.xPos+"] 1500 RED_AMBER\n"
							break;
						case TrafficLightColour.GREEN:
							trafficLights += "["+cell.xPos+";"+cell.xPos+"] 1500 GREEN\n"
							break;
						case TrafficLightColour.AMBER:
							trafficLights += "["+cell.xPos+";"+cell.xPos+"] 1500 AMBER\n"
							break;
						
					}
					if (c==cols-1){
						s = s + '\n';
					}//if end of row then new line
				}
			}
			s += "===\n";
			s += trafficLights;
			
			return s;
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
						
						if(c.debugType == TrafficCellType.ROUNDABOUT_INSIDE){
							c.tempType = TrafficCellType.ROUNDABOUT_INSIDE;
						}else{
							c.tempType = TrafficCellType.ROUNDABOUT;
						}
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