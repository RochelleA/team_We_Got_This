package events;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class EventDispatchable {
	  private ArrayList<ModelEventListener> _listeners = new ArrayList<ModelEventListener>();
	  public synchronized void addEventListener(ModelEventListener listener)  {
	    _listeners.add(listener);
	  }
	  public synchronized void removeEventListener(ModelEventListener listener)   {
	    _listeners.remove(listener);
	  }
	 
	  // call this method whenever you want to notify
	  //the event listeners of the particular event
	  public synchronized void fireEvent() {
	    ModelEvent event = new ModelEvent(this);
	    Iterator<ModelEventListener> i = _listeners.iterator();
	    while(i.hasNext())  {
	      i.next().handleModelEvent(event);
	    }
	  }
	}