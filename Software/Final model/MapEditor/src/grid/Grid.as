package grid
{	
	import mx.events.FlexEvent;
	import mx.graphics.SolidColorStroke;
	
	import spark.components.Group;
	import spark.primitives.Rect;
	
	[Style(name="cellSide", inherit="yes", type="int")]
	[Style(name="selectWeight", inherit="yes", type="int")]
	[Style(name="selectColour", inherit="yes", type="uint")]

	public class Grid extends Group
	{
		[Bindable]
		public var widthInCells:int;
		[Bindable]
		public var heightInCells:int;
		
		protected var _cells:Array;
		protected var _allCells:Array;
		//protected var gridWidth:int, gridHeight:int;
		
		protected var selectCell:Rect;
		private var selectCellStroke:SolidColorStroke = new SolidColorStroke();
		
		public function Grid()
		{
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, onCreationComplete);
			this.setStyle("cellSide", 20); //default cell size
			this.setStyle("selectWeight", 2);
			this.setStyle("selectColour", 0xff0000);
			
			selectCell = new Rect();
			selectCell.stroke = selectCellStroke;
			updateSelectCell();
			
		}
		
		protected function onCreationComplete(event:FlexEvent):void
		{
			this.init(widthInCells, heightInCells);
			
			this.width = widthInCells*this.getStyle("cellSide")+1;
			this.height = heightInCells*this.getStyle("cellSide")+1;
			this.mouseChildren = false;
			
			this.addElement(selectCell);
			this.hideSelectCell();
		}
		/**
		 * Stop showing select cell.
		 */
		protected function hideSelectCell():void{
			selectCell.x = 0 - selectCell.width;
			selectCell.y = 0 - selectCell.height;
			selectCell.visible = false;
		}
		/**
		 * Place select cell and show it.
		 * @param x x position
		 * @param y y position
		 */
		protected function showSelectCell(x:int, y:int):void{
			selectCell.x = x*selectCell.width;
			selectCell.y = y*selectCell.height;
			selectCell.visible = true;
			trace('position select', x, y);
		}
		
		private function updateSelectCell():void{
			if(selectCell == null){
				return;
			}
			selectCell.width = this.getStyle("cellSide");
			selectCell.height = this.getStyle("cellSide");
			selectCellStroke.weight = this.getStyle("selectWeight");
			selectCellStroke.color = this.getStyle("selectColour");
		}
		
		/**
		 * Initialise the grid by creating cells and putting them in the cells array.
		 */
		protected function init(width:int, height:int):void
		{
			this._cells = new Array(height);
			this._allCells = new Array(width*height);
			
			for(var i:int=0; i<height; i++){
				var row:Array = new Array(width);
				for (var j:int=0; j<width; j++){
					var cell:Cell = new Cell(j,i);
					cell.setStyle("side", this.getStyle("cellSide"));
					row[j] = cell;
					allCells[i*width+j] = cell;
					
					this.addElement(cell);
				}
				cells[i] = row;
			}
		}
		
		override public function setStyle(styleProp:String, newValue:*):void{
			super.setStyle(styleProp, newValue);
			if(styleProp == "cellSide"){
				for each (var c:Cell in allCells){
					c.setStyle("cellSide", newValue);
				}
				updateSelectCell();
			}else if(styleProp == "selectWidth" || styleProp == "selectColour"){
				updateSelectCell();
			}
		}
		
		private function getCellX(localX:int):int{
			return localX / this.getStyle("cellSide");
		}
		private function getCellY(localY:int):int{
			return localY / this.getStyle("cellSide");
		}
		/**
		 * Returns a cell based on local coordinates as in MouseEvent.
		 * @param x local x
		 * @param y local y
		 * @return Cell at (x;y);
		 */
		protected function getCellByCoordinated(x:int, y:int):Cell{
			return this._cells[getCellY(y)][getCellX(x)];
		}
		
		public function get cells():Array
		{
			return _cells;
		}
		
		public function get allCells():Array
		{
			return _allCells;
		}
		
		
	}
}