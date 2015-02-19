package tests;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test cases for the Cell class.
 * @author Anton
 * @version 1
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
		//fail("Not yet implemented");
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testNegativeX(){
		ICell cell = new Cell(-1,0);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testNegativeY(){
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

	@Test
	public void testIsExit() {
		ICell cell = new Cell(0,0);
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
	public void testGetCar() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCar() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTrafficLight() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTrafficLight() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetX() {
		
		//fail("Not yet implemented");
	}

	@Test
	public void testGetY() {
		
		//fail("Not yet implemented");
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
