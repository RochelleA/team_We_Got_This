package
{
	public interface ICell
	{
		function ICell(x:int, y:int);
		
		function get direction():int;
		function set direction(value:int):void;
		
		function get isExit():Boolean;
		function set isExit(value:Boolean):void;
		
		function get trafficLight():ITrafficLight;
		function set trafficLight(value:ITrafficLight):void;
		
		function get xPos():int;		
		function get yPos():int;
		
		function get type():int;
		function set type(value:int):void;	
		
		function get prevType():int;
		function set prevType(value:int):void;
		
	}
}