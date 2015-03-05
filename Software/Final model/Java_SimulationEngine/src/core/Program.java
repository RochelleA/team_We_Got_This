package core;

import events.DataEvent;
import view.TestGrid01;
import model.*;

public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Model model = new Model();
		Model model = new Model();
		
		//GridController cont = new GridController();
		//IGrid grid = cont.model.getGrid();
		
	    TestGrid01 tg = new TestGrid01(model.getGrid());
	    
	    model.addEventListener(tg);
	    
	    DataSimulator ds = new DataSimulator(model.getGrid());
	    
	    ds.addEventListener(model);
	    	    
	    
	    
		
		//View view = new View();
	}

}
