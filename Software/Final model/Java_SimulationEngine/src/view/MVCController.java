package view;

import model.Model;

/**
 * A Controller part of the MVC model.
 * It listens for user gestures and transforms it into command to the model. 
 * @author Anton
 *
 */
public class MVCController {
	private Model model;
	
	public MVCController(Model m){
		this.model = m;
	}
	
	public void start(){
		if(model.getStatus().equals(Model.STATUS_PAUSED)){
			model.startSim();
		}
	}
	public void pause(){
		if(model.getStatus().equals(Model.STATUS_RUNNING)){
			model.pauseSim();
		}
	}
}
