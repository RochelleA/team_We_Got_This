package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import core.CellType;
import core.Direction;
import core.IGrid;
import core.Grid;

/**
 * Test cases for the Grid class.
 * @author Anton
 * @version 1
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
		//fail("Not yet implemented");
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGridNegativeWidth() {
		IGrid grid = new Grid(-1, 0);
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGridNegativeHeight() {
		IGrid grid = new Grid(0, -1);
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
		
		//fail("Not yet implemented");
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
	public void testHasCarAt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCarAt() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlaceCarAt() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveCarFrom() {
		fail("Not yet implemented");
	}

	@Test
	public void testHasTrafficLightAt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTrafficLightAt() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlaceTrafficLightAt() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveTrafficLightFrom() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWidth() {
		int width;
		int height = 5;
		for (int i=0; i<10; i++){
			width = i;
			IGrid grid = new Grid(width, height);
			assertEquals(width, grid.getWidth());
		}
		
	}

	@Test
	public void testGetHeight() {
		int width = 10;
		int height = 5;
		for (int i=0; i<10; i++){
			height = i;
			IGrid grid = new Grid(width, height);
			assertEquals(height, grid.getHeight());
		}
	}

}
