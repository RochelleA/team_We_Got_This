package tests;

import static org.junit.Assert.*;
import model.GridController;
import model.Model;

import org.junit.Test;

import core.Car;
import core.Cell;
import core.Grid;
import core.ICar;
import core.ICell;
import core.IGrid;

public class GridControllerTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testDriveNorth() {
		IGrid grid = new Grid(10, 10);
		GridController gc = new GridController(grid, new Model());
		
		ICar car = new Car();
		car.setPosition(5, 5);
		car.setSpeed(1);
		
		gc.addCar(car);
		int x = car.getX();
		while(x>0){
			gc.driveNorth(car);
			x--;
			assertEquals(car.getY(), x);
		}
		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testDriveNorthOutOfBounds(){
		IGrid grid = new Grid(10, 10);
		GridController gc = new GridController(grid, new Model());
		
		ICar car = new Car();
		car.setPosition(5, 0);
		car.setSpeed(1);
		
		gc.addCar(car);
		
		gc.driveNorth(car);
		
		//assertEquals(car.getY(), 0);
	}

}
