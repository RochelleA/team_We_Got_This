package tests;

import static org.junit.Assert.*;
import core.*;

import org.junit.Test;

/**
 * Test cases for the Cell class.
 * @author Anton
 * @version 1.1
 *
 */
public class CellTest {

	@Test
	public void testCell() {
		for (int i=0; i<10; i++){
			for (int j=0; j<10; j++){
				ICell cell = new Cell(i, j);
				assertEquals("X shoud be "+i, i, cell.getX());
				assertEquals("Y shoud be "+j, j, cell.getY());
			}
		}
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetNegativeX(){
		ICell cell = new Cell(-1,0);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetNegativeY(){
		ICell cell = new Cell(0,-1);
	}

	@Test
	public void testGetType() {
		ICell cell = new Cell(0,0);
		cell.setType(CellType.EMPTY);
		assertEquals(CellType.EMPTY, cell.getType());
		cell.setType(CellType.ROAD);
		assertEquals(CellType.ROAD, cell.getType());
		cell.setType(CellType.ROUNDABAOUT);
		assertEquals(CellType.ROUNDABAOUT, cell.getType());
	}

	@Test
	public void testSetType() {
		testGetType();
	}

	@Test
	public void testGetDirection() {
		ICell cell = new Cell(0,0);
		cell.setDirection(Direction.NORTH);
		assertEquals(Direction.NORTH, cell.getDirection());
		cell.setDirection(Direction.EAST);
		assertEquals(Direction.EAST, cell.getDirection());
		cell.setDirection(Direction.SOUTH);
		assertEquals(Direction.SOUTH, cell.getDirection());
		cell.setDirection(Direction.WEST);
		assertEquals(Direction.WEST, cell.getDirection());
	}

	@Test
	public void testSetDirection() {
		testGetDirection();
	}

	//====
	//MODIFIERS
	@Test
	public void testIsExit() {
		ICell cell = new Cell(0,0);
		assertFalse(cell.isExit());
		cell.setIsExit(false);
		assertFalse(cell.isExit());
		cell.setIsExit(true);
		assertTrue(cell.isExit());
	}

	@Test
	public void testSetIsExit() {
		testIsExit();
	}
	
	@Test
	public void testIsEntry(){
		ICell cell = new Cell(0,0);
		assertFalse(cell.isExit());
		cell.setIsExit(false);
		assertFalse(cell.isExit());
		cell.setIsExit(true);
		assertTrue(cell.isExit());
	}
	public void testSetIsEntry(){
		testIsEntry();
	}
	
	
	//====
	//CARS TESTS
	@Test
	public void testSetCar() {
		ICell cell = new Cell(0,0);
		ICar car = new Car();
		cell.setCar(car);
		assertEquals(car, cell.getCar());
		assertTrue(cell.hasCar());
	}
	@Test(expected = GridException.class)
	public void testSetCarWhenCellHasCar(){
		ICell cell = new Cell(0,0);
		ICar car = new Car();
		cell.setCar(car);
		cell.setCar(car);
	}
	
	@Test
	public void testGetCar() {
		ICell cell = new Cell(0,0);
		ICar car = new Car();
		cell.setCar(car);
		assertEquals(car, cell.getCar());
	}
	@Test(expected = GridException.class)
	public void testGetCarWhenCellDoesNotHaveCar(){
		ICell cell = new Cell(0,0);
		cell.getCar();
	}
	@Test
	public void testHasCar(){
		ICell cell = new Cell(0,0);
		ICar car = new Car();
		cell.setCar(car);
		assertTrue(cell.hasCar());
		cell.removeCar();
		assertFalse(cell.hasCar());
	}
	@Test
	public void testRemoveCar() {
		ICell cell = new Cell(0,0);
		ICar car = new Car();
		cell.setCar(car);
		assertEquals(car, cell.getCar());
		assertTrue(cell.hasCar());
		cell.removeCar();
		assertFalse(cell.hasCar());
	}
	
	@Test(expected = GridException.class)
	public void testRemoveCarWhenCellDoesNotHaveCar(){
		ICell cell = new Cell(0,0);
		cell.removeCar();
	}
	
	
	
	//====
	//TRAFFIC LIGHTS TESTS
	@Test
	public void testSetTrafficLight() {
		ICell cell = new Cell(0,0);
		ITrafficLight tl = new TrafficLight();
		cell.setTrafficLight(tl);
		assertEquals(tl, cell.getTrafficLight());
		assertTrue(cell.hasTrafficLight());
	}	
	@Test(expected = GridException.class)
	public void testSetTrafficLightWhenCellHasTrafficLight(){
		ICell cell = new Cell(0,0);
		cell.setTrafficLight(new TrafficLight());
		cell.setTrafficLight(new TrafficLight());
	}
	@Test
	public void testGetTrafficLight() {
		ICell cell = new Cell(0,0);
		ITrafficLight tl = new TrafficLight();
		cell.setTrafficLight(tl);
		assertEquals(tl, cell.getTrafficLight());
	}
	@Test(expected = GridException.class)
	public void testGetTLWhenMissing() {
		ICell cell = new Cell(0,0);
		cell.getTrafficLight();
	}
	@Test
	public void testHasTrafficLight(){
		ICell cell = new Cell(0,0);
		assertFalse(cell.hasTrafficLight());
		cell.setTrafficLight(new TrafficLight());
		assertTrue(cell.hasTrafficLight());
	}
	@Test
	public void testRemoveTrafficLight(){
		ICell cell = new Cell(0,0);
		ITrafficLight tl = new TrafficLight();
		cell.setTrafficLight(tl);
		assertEquals(tl, cell.getTrafficLight());
		assertTrue(cell.hasTrafficLight());
		cell.removeTrafficLight();
		assertFalse(cell.hasTrafficLight());
	}
	
	@Test(expected = GridException.class)
	public void testRemoveTLWhenMissing(){
		ICell cell = new Cell(0,0);
		cell.removeTrafficLight();
	}

	//====

	@Test
	public void testGetX() {
		for (int i=0; i<10; i++){
			ICell cell = new Cell(i, 0);
			assertEquals(i,cell.getX());
		}
	}

	@Test
	public void testGetY() {
		for (int i=0; i<10; i++){
			ICell cell = new Cell(0, i);
			assertEquals(i,cell.getY());
		}
	}

	@Test
	public void testToString() {
		ICell cell = new Cell(0,0);
		assertEquals("cell@(0;0)",cell.toString());
		cell = new Cell(1,0);
		assertEquals("cell@(1;0)",cell.toString());
		cell = new Cell(0,1);
		assertEquals("cell@(0;1)",cell.toString());
	}

}
