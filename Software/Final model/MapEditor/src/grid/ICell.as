package grid
{
	public interface ICell
	{
		function get xPos():int;
		function get yPos():int;
		function toString():String;
		function get type():String;
		function set type(value:String):void;
	}
}