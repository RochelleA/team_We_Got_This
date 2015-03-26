package view;

import core.DataSimulator;
import core.TrafficLightColour;
import model.Model;

/**
 * A Controller part of the MVC model.
 * It listens for user gestures and transforms it into command to the model. 
 * @author Anton
 *
 */
public class MVCController {
	private Model model;
	private DataSimulator ds;
	
	public MVCController(Model m){
		this.model = m;
		this.ds = model.getDataSimulator();
	}
	
	/*
	 * The method is used to initialize the model
	 */
	public void start(){
		if(!model.getInitialized()){
			System.out.println("model not initialized");
			return;
		}
		if(model.getStatus().equals(Model.STATUS_PAUSED)){
			model.startSim();
			ds.setRunning(true);
		}
	}
	
	/*
	 * The method is used to pause the model
	 */
	public void pause(){
		if(!model.getInitialized()){
			return;
		}
		if(model.getStatus().equals(Model.STATUS_RUNNING)){
			model.pauseSim();
			ds.setRunning(false);
		}
	}
	
	/*
	 * The method is used to start the Data Flow. Checks if the Model is initialized.
	 */
	public void dataStart() {
		if(!model.getInitialized()){
			return;
		}
		if(!model.getStatus().equals(Model.STATUS_PAUSED)){
			ds.setRunning(true);
		}
		
	}
	/*
	 * The method is used to start the Data Flow. Checks if the Model is initialized.
	 */
	public void dataPause() {
		if(!model.getInitialized()){
			return;
		}
		ds.setRunning(false);
	}
	
	/*
	 * This method is used to load the text based map file into the Model.
	 */
	public void setMapFile(String file){
		model.loadFile(file);
	}
	/*
	 * This method is used to Change/Set the Color of the Traffic Lights.
	 */
	public void setTrafficLightsColour(TrafficLightColour c){
		model.setTrafficLightsColour(c);
	}
	
	/*
	 * This method is used to Stop the Traffic Lights all at once
	 */
	public void stopAllTrafficLights(){
		model.stopTrafficLights();
	}
	
	/*
	 * This method is used to Start the Traffic Lights all at once
	 */
	public void startAllTrafficLights(){
		model.startTrafficLights();
	}
	
	/*
	 * This method is used to Disable the Traffic Light Functionality in the Model.
	 */
	public void disableTrafficLights(){
		model.disableTrafficLights();
	}

	public void loadDefaultFile() {
		// TODO Auto-generated method stub
		model.loadDefaultFile();
	}
}
