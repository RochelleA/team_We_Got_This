package
{
	public class TrafficLightColour
	{
		public static const RED:String = "red_color";
		public static const RED_AMBER:String = "red_amber_color";
		public static const GREEN:String = "green_color";
		public static const AMBER:String = "amber_color";
		
		[Embed(source="/../assets/traffic_lights/red.png")]
		public static const RED_ICON:Class;
		
		
[Embed(source="/../assets/traffic_lights/red-amber.png")]
		public static const RED_AMBER_ICON:Class;
		
		[Embed(source="/../assets/traffic_lights/amber.png")]
		public static const AMBER_ICON:Class;
		
		[Embed(source="/../assets/traffic_lights/green.png")]
		public static const GREEN_ICON:Class;
		
		public static function getIconForColour(colour:String):Class{
			var c:Class;
			switch(colour){
				case TrafficLightColour.RED:
					c = TrafficLightColour.RED_ICON;
					break;
				case TrafficLightColour.RED_AMBER:
					c = TrafficLightColour.RED_AMBER_ICON;
					break;
				case TrafficLightColour.GREEN:
					c = TrafficLightColour.GREEN_ICON;
					break;
				case TrafficLightColour.AMBER:
					c = TrafficLightColour.AMBER_ICON;
					break;
			}
			return c;
		}
		
	}
}
