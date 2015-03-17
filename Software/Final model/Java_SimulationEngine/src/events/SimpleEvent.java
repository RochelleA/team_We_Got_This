package events;

import java.util.EventObject;

/**
 * An event which also has a type.
 * @author Anton
 *
 */
public class SimpleEvent extends EventObject {
	
	public static final String MODEL_STATUS_CHANGE = "model_status_change";
	public static final String MODEL_STEP = "model_step";
	public static final String DATA_STATUS_CHANGE = "data_status_change";
	
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
