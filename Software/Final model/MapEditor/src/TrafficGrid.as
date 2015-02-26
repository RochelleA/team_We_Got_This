package
{	
	import spark.components.Group;
	
	public class TrafficGrid extends Group implements ITrafficGrid
	{
		protected var _cells:Array;
		protected var _trafficLights:Array;
		protected var _cars:Array;
		
		public function TrafficGrid()
		{
			super();
		}
		
		/**
		 * Initialise the grid by creating cells and putting them in the array.
		 */
		public function init(width:int, height:int):void
		{
			this._cells = new Array(height);
			
			for(var i:int=0; i<height; i++){
				var row:Array = new Array(width);
				for (var j:int=0; j<width; j++){
					var cell:ICell = new Cell(j,i);
					row[j] = cell;
				}
				cells[i] = row;
			}
		}
		
		public function get cells():Array
		{
			return _cells;
		}

		public function get trafficLights():Array
		{
			return _trafficLights;
		}

		public function get cars():Array
		{
			return _cars;
		}


	}
}