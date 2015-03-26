package grid
{
	public interface ICell
	{
		function get xPos():int;
		function get yPos():int;
		function toString():String;
		function get type():String;
		function set type(value:String):void;
		
		function set debugType(value:String):void;
		function get debugType():String;
	}
}