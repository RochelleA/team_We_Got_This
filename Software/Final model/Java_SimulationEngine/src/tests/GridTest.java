package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import core.Car;
import core.CellType;
import core.Direction;
import core.GridException;
import core.ICar;
import core.ICell;
import core.IGrid;
import core.Grid;
import core.TrafficLight;

/**
 * Test cases for the Grid class.
 * @author Anton
 * @version 1.1
 *
 */
public class GridTest {

	@Test
	public void testGrid() {
		int width = 10;
		int height = 5;
		IGrid grid = new Grid(width, height);
		assertEquals("Width should be "+width, width, grid.getWidth());
		assertEquals("Height should be "+height, height, grid.getHeight());
		
		width = 15;
		height = 20;
		grid = new Grid(width, height);
		assertEquals("Width should be "+width, width, grid.getWidth());
		assertEquals("Height should be "+height, height, grid.getHeight());
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGridExceedsMaxWidth() {
		IGrid grid = new Grid(200, 100);
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGridExceedsMaxHeight() {
		IGrid grid = new Grid(100, 200);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGridNegativeWidth() {
		IGrid grid = new Grid(-1, 1);
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGridNegativeHeight() {
		IGrid grid = new Grid(1, -1);
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGridZeroWidth() {
		IGrid grid = new Grid(0, 1);
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGridZeroHeight() {
		IGrid grid = new Grid(1, 0);
	}
	
	@Test 
	public void testGetEntryCells(){
		IGrid grid = new Grid(1, 5);
		grid.setIsEntry(0, 0, true);
		grid.setIsEntry(0, 1, true);
		grid.setIsEntry(0, 2, true);
		grid.setIsEntry(0, 3, true);
		grid.setIsEntry(0, 4, true);
		ArrayList<ICell> al = grid.getEntryCells();
		assertEquals(al.size(), 5);
		for (int i=0; i<al.size(); i++){
			assertTrue(al.get(i).isEntry());
		}
		grid.setIsEntry(0, 4, false);
		assertEquals(al.size(), 4);
		grid.setIsEntry(0, 3, false);
		assertEquals(al.size(), 3);
		grid.setIsEntry(0, 2, false);
		assertEquals(al.size(), 2);
		grid.setIsEntry(0, 1, false);
		assertEquals(al.size(), 1);
		grid.setIsEntry(0, 0, false);
		assertEquals(al.size(), 0);
		
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCellType() {
		IGrid grid = new Grid(1, 1);
		grid.setCellType(0, 0, CellType.EMPTY);
		assertEquals(CellType.EMPTY, grid.getCellType(0, 0));
		grid.setCellType(0, 0, CellType.ROAD);
		assertEquals(CellType.ROAD, grid.getCellType(0, 0));
		grid.setCellType(0, 0, CellType.ROUNDABAOUT);
		assertEquals(CellType.ROUNDABAOUT, grid.getCellType(0, 0));
	}

	@Test
	public void testSetCellType() {
		testGetCellType();
	}

	@Test
	public void testGetCellDirection() {
		IGrid grid = new Grid(1, 1);
		grid.setCellDirection(0, 0, Direction.NORTH);
		assertEquals(Direction.NORTH, grid.getCellDirection(0, 0));
		grid.setCellDirection(0, 0, Direction.EAST);
		assertEquals(Direction.EAST, grid.getCellDirection(0, 0));
		grid.setCellDirection(0, 0, Direction.SOUTH);
		assertEquals(Direction.SOUTH, grid.getCellDirection(0, 0));
		grid.setCellDirection(0, 0, Direction.WEST);
		assertEquals(Direction.WEST, grid.getCellDirection(0, 0));
	}

	@Test
	public void testSetCellDirection() {
		testGetCellDirection();
		//fail("Not yet implemented");
	}

	@Test
	public void testIsExit() {
		IGrid grid = new Grid(1, 1);
		grid.setIsExit(0, 0, true);
		assertTrue(grid.isExit(0, 0));
		grid.setIsExit(0, 0, false);
		assertFalse(grid.isExit(0, 0));
	}

	@Test
	public void testSetIsExit() {
		testIsExit();
	}
	
	@Test
	public void testSetIsEntry(){
		IGrid grid = new Grid(1, 1);
		grid.setIsEntry(0, 0, true);
		assertTrue(grid.isEntry(0, 0));
		grid.setIsEntry(0, 0, false);
		assertFalse(grid.isEntry(0, 0));
	}
	@Test
	public void testIsEntry(){
		testSetIsEntry();
	}

	//====
	//CARS
	@Test
	public void testHasCarAt() {
		IGrid grid = new Grid(1, 1);
		assertFalse(grid.hasCarAt(0, 0));
		ICar car = new Car();
		grid.placeCarAt(0, 0, car);
		assertTrue(grid.hasCarAt(0, 0));
	}

	@Test
	public void testGetCarAt() {
		IGrid grid = new Grid(1, 1);
		ICar car = new Car();
		grid.placeCarAt(0, 0, car);
		assertEquals(car, grid.getCarAt(0, 0));
	}
	@Test(expected = GridException.class)
	public void testGetCarAtWhenMissing(){
		IGrid grid = new Grid(1, 1);
		grid.getCarAt(0, 0);
	}

	@Test
	public void testPlaceCarAt() {
		IGrid grid = new Grid(1, 1);
		ICar car = new Car();
		grid.placeCarAt(0, 0, car);
		assertEquals(car, grid.getCarAt(0, 0));
	}
	@Test(expected = GridException.class)
	public void testPlaceCarAtWhenCarPresent(){
		IGrid grid = new Grid(1, 1);
		ICar car = new Car();
		grid.placeCarAt(0, 0, car);
		grid.placeCarAt(0, 0, car);
	}

	@Test
	public void testRemoveCarFrom() {
		IGrid grid = new Grid(1, 1);
		assertFalse(grid.hasCarAt(0, 0));
		ICar car = new Car();
		grid.placeCarAt(0, 0, car);
		assertTrue(grid.hasCarAt(0, 0));
		grid.removeCarFrom(0, 0);
		assertFalse(grid.hasCarAt(0, 0));
	}
	@Test(expected = GridException.class)
	public void testRemoveCarFromWhenMissing() {
		IGrid grid = new Grid(1, 1);
		grid.removeCarFrom(0, 0);
	}
	//----
	
	//====
	//TRAFFIC LIGHTS
	@Test 
	public void testGetTrafficLights(){
		IGrid grid = new Grid(1, 2);
		assertEquals(0, grid.getTrafficLights().size());
		grid.placeTrafficLightAt(0, 0, new TrafficLight());
		assertEquals(1, grid.getTrafficLights().size());
		grid.placeTrafficLightAt(0, 1, new TrafficLight());
		assertEquals(2, grid.getTrafficLights().size());
		grid.removeTrafficLightFrom(0, 1);
		assertEquals(1, grid.getTrafficLights().size());
	}
	@Test
	public void testHasTrafficLightAt() {
		IGrid grid = new Grid(1, 1);
		assertFalse(grid.hasTrafficLightAt(0, 0));
		grid.placeTrafficLightAt(0, 0, new TrafficLight());
		assertTrue(grid.hasTrafficLightAt(0, 0));
	}

	@Test
	public void testGetTrafficLightAt() {
		IGrid grid = new Grid(1, 1);
		TrafficLight tl = new TrafficLight();
		grid.placeTrafficLightAt(0, 0, tl);
		assertEquals(tl, grid.getTrafficLightAt(0, 0));
	}
	@Test(expected = GridException.class)
	public void testGetTrafficLightAtWhenMissing(){
		IGrid grid = new Grid(1, 1);
		grid.getTrafficLightAt(0, 0);
	}

	@Test
	public void testPlaceTrafficLightAt() {
		IGrid grid = new Grid(1, 1);
		TrafficLight tl = new TrafficLight();
		grid.placeTrafficLightAt(0, 0, tl);
		assertEquals(tl, grid.getTrafficLightAt(0, 0));
	}
	@Test(expected = GridException.class)
	public void testPlaceTLAtWhenPresent(){
		IGrid grid = new Grid(1, 1);
		grid.placeTrafficLightAt(0, 0, new TrafficLight());
		grid.placeTrafficLightAt(0, 0, new TrafficLight());
	}

	@Test
	public void testRemoveTrafficLightFrom() {
		IGrid grid = new Grid(1, 1);
		TrafficLight tl = new TrafficLight();
		grid.placeTrafficLightAt(0, 0, tl);
		assertTrue(grid.hasTrafficLightAt(0, 0));
		assertEquals(tl, grid.getTrafficLightAt(0, 0));
		grid.removeTrafficLightFrom(0, 0);
		assertFalse(grid.hasTrafficLightAt(0, 0));
		
	}
	@Test(expected = GridException.class)
	public void testRemoveTLFromWhenMissing(){
		IGrid grid = new Grid(1, 1);
		grid.removeTrafficLightFrom(0, 0);
	}
	//----

	@Test
	public void testGetWidth() {
		int width;
		int height = 5;
		for (int i=1; i<10; i++){
			width = i;
			IGrid grid = new Grid(width, height);
			assertEquals(width, grid.getWidth());
		}
		
	}

	@Test
	public void testGetHeight() {
		int width = 10;
		int height = 5;
		for (int i=1; i<10; i++){
			height = i;
			IGrid grid = new Grid(width, height);
			assertEquals(height, grid.getHeight());
		}
	}

}
