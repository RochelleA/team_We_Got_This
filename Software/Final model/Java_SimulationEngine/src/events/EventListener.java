package events;

import java.util.EventObject;

public interface EventListener {
	public void handleSimulationEvent(SimulationEvent e);
}
