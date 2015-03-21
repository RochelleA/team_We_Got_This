package
{
	import mx.core.IVisualElement;
	
	public interface IVisualCell extends ICell, IVisualElement
	{
		function get tempType():int;
		function set tempType(value:int):void;
		
		function get highlighted():Boolean;
		function set highlighted(value:Boolean):void;
		
		function get selected():Boolean;
		function set selected(value:Boolean):void;
	}
}