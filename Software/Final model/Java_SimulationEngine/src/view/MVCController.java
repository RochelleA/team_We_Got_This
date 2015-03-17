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
	
	public MVCController(Model m, DataSimulator ds){
		this.model = m;
		this.ds = ds;
	}
	
	public void start(){
		if(model.getStatus().equals(Model.STATUS_PAUSED)){
			model.startSim();
			ds.setRunning(true);
		}
	}
	public void pause(){
		if(model.getStatus().equals(Model.STATUS_RUNNING)){
			model.pauseSim();
			ds.setRunning(false);
		}
	}

	public void dataStart() {
		ds.setRunning(true);
		
	}

	public void dataPause() {
		ds.setRunning(false);
	}
}
