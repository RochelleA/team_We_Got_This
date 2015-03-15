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
		
	    TestGrid01 tg = new TestGrid01(model);
	    
	    model.addEventListener(tg);
	    
	    DataSimulator ds = new DataSimulator(model.getGrid());
	    ds.setRunning(true);

	    ds.addEventListener(model);
	    
	   // MVCController c = new MVCController(model, ds);
	    tg.setMVCController(c);
	    	    
	    
		
		//View view = new View();
	}

}
