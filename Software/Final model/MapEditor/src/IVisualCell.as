package
{
	import mx.core.IVisualElement;
	
	public interface IVisualCell extends ICell, IVisualElement
	{
		function get tempType():int;
		function set tempType(value:int):void;
	}
}