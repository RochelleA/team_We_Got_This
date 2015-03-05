/**
 * 
 */
package events;

/**
 * @author Anton
 *
 */

public class DataEvent extends SimulationEvent {

	public static String NEW_CAR = "new_car";

	private Object data;
	private String type;

	public DataEvent(Object source, String type, Object data) {
		super(source);
		this.data = data;
		this.type = type;
	}
	public Object getData(){
		return this.data;
	}
	public String getType(){
		return this.type;
	}

}
