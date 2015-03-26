package trafficGrid
{
	import grid.ICell;

	public interface ITrafficCell extends ICell
	{
		function set trafficLight(value:String):void;
		function set isEntry(value:Boolean):void;
		function set isExit(value:Boolean):void;
		
		function get trafficLight():String;
		function get isRoad():Boolean;
		function get isEntry():Boolean;
		function get isExit():Boolean;
		
		function toNumber():int;
		
	}
}