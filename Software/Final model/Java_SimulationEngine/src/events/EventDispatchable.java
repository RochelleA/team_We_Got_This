package events;

import java.util.ArrayList;

/**
 * An abstract class which implements calling handle methods
 * on all added event listeners of an object.
 * @author Anton
 *
 */
public abstract class EventDispatchable {
	  private ArrayList<EventListener> _listeners = new ArrayList<EventListener>();
	  
	  public synchronized void addEventListener(EventListener listener)  {
		  _listeners.add(listener);
	  }
	  public synchronized void removeEventListener(EventListener listener)   {
		  _listeners.remove(listener);
	  }
	 
	  public synchronized void fireSimpleEvent(SimpleEvent e) {
	    for (EventListener el : _listeners){
	    	el.handleSimpleEvent(e);
	    }
	  }
	  
	  public synchronized void fireDataEvent(DataEvent de) {
	    for (EventListener el : _listeners){
	    	el.handleSimpleEvent(de);
	    }
	  }
	  

	  
	}