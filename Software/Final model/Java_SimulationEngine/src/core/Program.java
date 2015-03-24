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
		
		//System.out.println(model.getGrid().getWidth() + " "+model.getGrid().getHeight());

	    
	    TestGrid01 tg = new TestGrid01(model);
	    
	    //ds.addEventListener(model);
	    //ds.addEventListener(tg); //fire status change events
	    
	    MVCController c = new MVCController(model);
	    tg.setMVCController(c);
	    
	    
	    	    
	    
		
		//View view = new View();
	}

}
