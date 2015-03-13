package events;

import java.util.EventObject;

/**
 * An event which also has a type.
 * @author Anton
 *
 */
public class SimpleEvent extends EventObject {
	
	public static String MODEL_STATUS_CHANGE = "model_status_change";
	public static String MODEL_STEP = "model_step";
	
	private String type;
	
	public SimpleEvent(Object source, String type) {
		super(source);
		this.type = type;
		// TODO Auto-generated constructor stub
	}

	public String getType() {
		return type;
	}


}
