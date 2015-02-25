package  {
	import flash.display.*;
	import flash.events.*;
	
	public class TrafficFlow extends MovieClip {
		
		//VARIABLES
		const SQUARE:int = 10;
		const COLUMNS:int = 11;
		const ROWS:int = 11;
		const offset:int = 0;
		
		//ARRAYS
		var visualBoard:Array = new Array();
		var logicBoard:Array = new Array();

		public function TrafficFlow() {
			// constructor code
			logicBoard = [[3,3,3,1,1,2,1,1,3,3,3],
						  [3,3,3,1,1,2,1,1,3,3,3],
						  [3,3,3,1,1,2,1,1,3,3,3],
						  [1,1,1,1,1,1,1,1,1,1,1],
						  [1,1,1,1,1,1,1,1,1,1,1],
						  [2,2,2,1,1,1,1,1,2,2,2],
						  [1,1,1,1,1,1,1,1,1,1,1],
						  [1,1,1,1,1,1,1,1,1,1,1],
						  [3,3,3,1,1,2,1,1,3,3,3],
						  [3,3,3,1,1,2,1,1,3,3,3],
						  [3,3,3,1,1,2,1,1,3,3,3]];
						  
			buildBoard();
		}
	
		function buildBoard(){
			//define instances
			var road:Road;
			var grass:Grass;
			var divider:Divider;

			
			//TASK 2: USE A NESTED FOR LOOP TO CONSTRUCT THE BOARD
			for (var row:int=0; row<ROWS; row++){
				for (var col:int=0; col<COLUMNS; col++){
					if(logicBoard[row][col] == 1){
							road = new Road();
							road.x = SQUARE * col + offset;
							road.y = SQUARE * row + offset;
							addChild(road);
							visualBoard.push(road);
						}
					else if (logicBoard[row][col] == 2){
							divider = new Divider();
							divider.x = SQUARE * col + offset;
							divider.y = SQUARE * row + offset;
							addChild(divider);
							visualBoard.push(divider);
					}
					else{
						grass = new Grass();
						grass.x = SQUARE * col + offset;
						grass.y = SQUARE * row + offset;
						addChild(grass);
						visualBoard.push(grass);
					}
				}
			}
		}//end buildBoard
		
		
	}
	
}
