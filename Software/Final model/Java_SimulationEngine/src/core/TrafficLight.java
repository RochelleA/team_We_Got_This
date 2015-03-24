package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import events.EventDispatchable;
import events.SimpleEvent;


/**
 * TrafficLight implementation of ITrafficLight interface
 * @author Anton
 *
 */
public class TrafficLight extends EventDispatchable implements ITrafficLight, ActionListener {
	
	private Timer trLightTimer = new Timer(1500, this);
	private TrafficLightColour colour;
		
		
	public TrafficLight()
	{	
		setColour(TrafficLightColour.GREEN);
		this.start();
	}
		
	@Override
	public void setColour(TrafficLightColour colour) {
		this.colour = colour;
		this.fireSimpleEvent(new SimpleEvent(this, SimpleEvent.TRAFFIC_LIGHT_CHANGE));
	}

	@Override
	public TrafficLightColour getColour() {
		// TODO Auto-generated method stub
		if(colour!=null){
			
		return colour;}
		else {
		
			return null;}
	}
	
	public void actionPerformed (ActionEvent e)
		{	
			if(getColour()==TrafficLightColour.AMBER)
			{
				setColour(TrafficLightColour.GREEN);
			}
			else if(getColour()==TrafficLightColour.RED_AMBER)
			{
				setColour(TrafficLightColour.RED);
			}
			else if(getColour()==TrafficLightColour.RED)
			{
				setColour(TrafficLightColour.AMBER);
			}
			else if(getColour()==TrafficLightColour.GREEN)
			{
				setColour(TrafficLightColour.RED_AMBER);
			}
		}

	@Override
	public void setDelay(int delay) {
		// TODO Auto-generated method stub
		trLightTimer.setDelay(delay);
	}

	@Override
	public void stop() {
		trLightTimer.stop();
		
	}

	@Override
	public void start() {
		trLightTimer.start();
		
	}
}
