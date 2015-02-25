/**
 * 
 */
package model;

import core.*;

/**
 * @author NM
 *
 */
public class Model {
	private IGrid grid; 
	
	public Model() {
		grid = new Grid(10, 10);
		grid.setCellDirection(5, 5, Direction.NORTH);
		
		System.out.println("has car "+grid.hasCarAt(5, 5));

		grid.placeCarAt(5, 5, new Car());
		
		System.out.println("has car "+grid.hasCarAt(5, 5));
		System.out.println(getGrid().toString());
		System.out.println("finished");
	}
	


	public IGrid getGrid() {
		
		
		return grid;
	}

}
