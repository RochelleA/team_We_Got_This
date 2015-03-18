package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import events.SimpleEvent;


/**
 * TrafficLight implementation of ITrafficLight interface
 * @author Anton
 *
 */
public class TrafficLight implements ITrafficLight, ActionListener {

	ITrafficLight trLight;
	
	Timer trLightTimer = new Timer(2000, this);
	private TrafficLightColour colour;
		
		
	public TrafficLight()
	{	
		setColour(TrafficLightColour.GREEN);
		trLightTimer.start();
	}
		
	@Override
	public void setColour(TrafficLightColour colour) {
		this.colour = colour;
	}

	@Override
	public TrafficLightColour getColour() {
		// TODO Auto-generated method stub
		if(colour!=null){
			System.out.println("The colour is " + colour);
		return colour;}
		else {
			System.out.println("The colour is NULL");
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
}
