package
{
	import mx.core.IVisualElement;
	
	public interface ISquareCell extends ICell, IVisualElement
	{
		function ISquareCell(x:int, y:int, side:int):void;
		//function get rect():Rect;
		//function get visualElement():SpriteVisualElement;
	}
}