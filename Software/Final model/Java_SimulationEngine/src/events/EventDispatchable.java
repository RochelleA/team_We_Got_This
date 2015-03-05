package events;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class EventDispatchable {
	  private ArrayList<EventListener> _listeners = new ArrayList<EventListener>();
	  public synchronized void addEventListener(EventListener listener)  {
	    _listeners.add(listener);
	  }
	  public synchronized void removeEventListener(EventListener listener)   {
	    _listeners.remove(listener);
	  }
	 
	  // call this method whenever you want to notify
	  //the event listeners of the particular event
	  public synchronized void fireEvent() {
	    SimulationEvent event = new SimulationEvent(this);
	    Iterator<EventListener> i = _listeners.iterator();
	    while(i.hasNext())  {
	      i.next().handleSimulationEvent(event);
	    }
	  }
	  
	  public synchronized void fireDataEvent(DataEvent de) {
	    Iterator<EventListener> i = _listeners.iterator();
	    while(i.hasNext())  {
	      i.next().handleSimulationEvent(de);
	    }
	  }
	  

	  
	}