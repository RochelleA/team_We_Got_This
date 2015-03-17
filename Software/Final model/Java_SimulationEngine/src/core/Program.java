package core;

import view.MVCController;
import view.TestGrid01;
import model.*;

public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Model model = new Model();
		
	    DataSimulator ds = new DataSimulator(model.getGrid());
	    ds.setRunning(true);
	    
	    TestGrid01 tg = new TestGrid01(model, ds);
	    
	    model.addEventListener(tg);

	    ds.addEventListener(model);
	    ds.addEventListener(tg); //fire status change events
	    
	    MVCController c = new MVCController(model, ds);
	    tg.setMVCController(c);
	    
	    
	    	    
	    
		
		//View view = new View();
	}

}
