package view;

import core.DataSimulator;
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
	public void pause(){
		if(!model.getInitialized()){
			return;
		}
		if(model.getStatus().equals(Model.STATUS_RUNNING)){
			model.pauseSim();
			ds.setRunning(false);
		}
	}

	public void dataStart() {
		if(!model.getInitialized()){
			return;
		}
		if(!model.getStatus().equals(Model.STATUS_PAUSED)){
			ds.setRunning(true);
		}
		
	}

	public void dataPause() {
		if(!model.getInitialized()){
			return;
		}
		ds.setRunning(false);
	}
	
	public void setMapFile(String file){
		model.loadFile(file);
	}
}
