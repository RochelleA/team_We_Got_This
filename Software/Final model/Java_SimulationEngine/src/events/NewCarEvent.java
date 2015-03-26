package events;

import core.ICar;

/**
 * An event of new cars.
 * @author Anton
 *
 */
public class NewCarEvent extends DataEvent {

	public ICar car;
	public NewCarEvent(Object source, ICar car) {
		super(source, DataEvent.NEW_CAR, car);
		this.car = car;
	}

}
