package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import model.GridController;
import model.Model;

import org.junit.Test;

import core.Car;
import core.Direction;
import core.Grid;
import core.ICar;
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
		int y = car.getY();
		while(y>0){
			gc.driveNorth(car);
			y--;
			assertEquals(car.getY(), y);
		}
		
	}
	
	@Test
	public void testDriveSouth() {
		IGrid grid = new Grid(10, 10);
		GridController gc = new GridController(grid, new Model());
		
		ICar car = new Car();
		car.setPosition(5, 5);
		car.setSpeed(1);
		
		gc.addCar(car);
		int y = car.getY();
		while(y<9){
			gc.driveSouth(car);
			y++;
			assertEquals(car.getY(), y);
		}
		
	}
	
	@Test
	public void testDriveWest() {
		IGrid grid = new Grid(10, 10);
		GridController gc = new GridController(grid, new Model());
		
		ICar car = new Car();
		car.setPosition(5, 5);
		car.setSpeed(1);
		
		gc.addCar(car);
		int x = car.getX();
		while(x>0){
			gc.driveWest(car);
			x--;
			assertEquals(car.getX(), x);
		}
		
	}
	
	@Test
	public void testDriveEast() {
		IGrid grid = new Grid(10, 10);
		GridController gc = new GridController(grid, new Model());
		
		ICar car = new Car();
		car.setPosition(5, 5);
		car.setSpeed(1);
		
		gc.addCar(car);
		int x = car.getX();
		while(x<9){
			gc.driveEast(car);
			x++;
			assertEquals(car.getX(), x);
		}
		
	}
	
	@Test
	public void testDriveNorthEast() {
		IGrid grid = new Grid(10, 10);
		GridController gc = new GridController(grid, new Model());
		
		ICar car = new Car();
		car.setPosition(5, 5);
		car.setSpeed(1);
		
		gc.addCar(car);
		int x = car.getX();
		int y = car.getY();
		while(x<9 && y>0){
			gc.driveNorthEast(car);
			x++;
			y--;
			assertEquals(car.getX(), x);
			assertEquals(car.getY(), y);
		}
		}
		
		@Test
		public void testDriveEastSouth() {
			IGrid grid = new Grid(10, 10);
			GridController gc = new GridController(grid, new Model());
			
			ICar car = new Car();
			car.setPosition(5, 5);
			car.setSpeed(1);
			
			gc.addCar(car);
			int x = car.getX();
			int y = car.getY();
			while(x<9 && y<9){
				gc.driveEastSouth(car);
				x++;
				y++;
				assertEquals(car.getX(), x);
				assertEquals(car.getY(), y);
			
			}
		
		}
		@Test
		public void testDriveSouthWest() {
			IGrid grid = new Grid(10, 10);
			GridController gc = new GridController(grid, new Model());
			
			ICar car = new Car();
			car.setPosition(5, 5);
			car.setSpeed(1);
			
			gc.addCar(car);
			int x = car.getX();
			int y = car.getY();
			while(x>0 && y<9){
				gc.driveSouthWest(car);
				x--;
				y++;
				assertEquals(car.getX(), x);
				assertEquals(car.getY(), y);
			
			}
		
		}
		@Test
		public void testDriveWestNorth() {
			IGrid grid = new Grid(10, 10);
			GridController gc = new GridController(grid, new Model());
			
			ICar car = new Car();
			car.setPosition(5, 5);
			car.setSpeed(1);
			
			gc.addCar(car);
			int x = car.getX();
			int y = car.getY();
			while(x>0 && y>0){
				gc.driveWestNorth(car);
				x--;
				y--;
				assertEquals(car.getX(), x);
				assertEquals(car.getY(), y);
			
			}
		
		}
		
		@Test
		public void testOverTake() {
			IGrid grid = new Grid(10, 10);
			GridController gc = new GridController(grid, new Model());
			
			ICar car = new Car();
			car.setPosition(5, 5);
			car.setSpeed(1);
			
			grid.placeCarAt(6, 5, car);
			grid.setCellDirection(5, 5, Direction.EAST);
			grid.setCellDirection(6, 5, Direction.EAST);
			grid.setCellDirection(5, 6, Direction.EAST);
			grid.setCellDirection(6, 6, Direction.EAST);
			gc.addCar(car);
			
				gc.driveEast(car);

				assertEquals(6, car.getX());
				assertEquals(6, car.getY());
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
