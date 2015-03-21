package
{
	public interface ITrafficGrid
	{
		function ITrafficGrid();
		function get cells():Array;
		function get allCells():Array;
		function get trafficLights():Array;
		function get cars():Array;
		
		//function init(width:int, height:int):void;
	}
}