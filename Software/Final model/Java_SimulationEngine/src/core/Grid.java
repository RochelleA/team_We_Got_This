package core;

import java.util.ArrayList;

/**
 * A grid class is the implementation of the IGrid interface.
 * @see IGrid
 * @author Anton
 * @version 1
 *
 */
public class Grid implements IGrid {

	private int width;
	private int height;
	private ArrayList<ArrayList<ICell>> cells;
	
	/**
	 * Constructs a 2D array of cells of specified width and height. 
	 * Each cells receives x and y coordinates.
	 * @param width width of the grid
	 * @param height height of the grid
	 * @throws IndexOutOfBoundsException if width or height is negative
	 * @see IGrid
	 */
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		if (width < 0 || height < 0){
			throw new IndexOutOfBoundsException("Width or height of the grid cannot be negative");
		}
		cells = new ArrayList<ArrayList<ICell>>(height);
		for (int i=0; i<height; i++){
			ArrayList<ICell> row = new ArrayList<ICell>(width);
			for (int j=0; j<width; j++){
				row.add(j, new Cell(j, i));
			}
			cells.add(i, row);
		}
	}
	
	@Override
	public String toString(){
		String s = "";
		String separator = " ";
		for (int i=0; i<this.height; i++){
			for (int j=0; j<width; j++){
				if(this.getCellType(j, i)==CellType.EMPTY)
				{
				s += "*"+separator;
				}
				if(this.getCellType(j, i)==CellType.ROAD)
				{
					if(hasCarAt(j, i)){
						s += "@"+separator;
						
					}
					else{
				s += "#"+separator;}
				}
				
			}
			s += "\n";
		}
		//s = s.substring(0, s.length()-2); //remove new line in the end
		return s;
	}
	
	private ICell getCellAt(int x, int y){
		if(x > this.width-1 || x < 0 || y > this.height-1 || y < 0){
			throw new IndexOutOfBoundsException("Required parameters are out of bounds");
		}
		return cells.get(y).get(x);
	}

	/* (non-Javadoc)
	 * @see IGrid#getCellType(int, int)
	 */
	@Override
	public CellType getCellType(int x, int y) {
		return this.getCellAt(x, y).getType();
	}

	/* (non-Javadoc)
	 * @see IGrid#setCellType(int, int, CellType)
	 */
	@Override
	public void setCellType(int x, int y, CellType type) {
		this.getCellAt(x, y).setType(type);
	}

	/* (non-Javadoc)
	 * @see IGrid#getCellDirection(int, int)
	 */
	@Override
	public Direction getCellDirection(int x, int y) {
		return this.getCellAt(x, y).getDirection();
	}

	/* (non-Javadoc)
	 * @see IGrid#setCellDirection(int, int, Direction)
	 */
	@Override
	public void setCellDirection(int x, int y, Direction direction) {
		this.getCellAt(x, y).setDirection(direction);
	}

	/* (non-Javadoc)
	 * @see IGrid#isExit(int, int)
	 */
	@Override
	public boolean isExit(int x, int y) {
		return this.getCellAt(x, y).isExit();
	}

	/* (non-Javadoc)
	 * @see IGrid#setIsExit(int, int)
	 */
	@Override
	public void setIsExit(int x, int y, boolean isExit) {
		this.getCellAt(x, y).setIsExit(isExit);

	}

	/* (non-Javadoc)
	 * @see IGrid#hasCarAt(int, int)
	 */
	@Override
	public boolean hasCarAt(int x, int y) {
		// TODO Auto-generated method stub
		if(this.getCellAt(x, y).getCar()!=null)
		{
			return true;
		}
		else{
		return false;
		}
	}

	/* (non-Javadoc)
	 * @see IGrid#getCarAt(int, int)
	 */
	@Override
	public ICar getCarAt(int x, int y) {
		// TODO Auto-generated method stub
		
		return null;
	}

	/* (non-Javadoc)
	 * @see IGrid#placeCarAt(int, int, ICar)
	 */
	@Override
	public void placeCarAt(int x, int y, ICar car) {
		// TODO Auto-generated method stub
		this.getCellAt(x, y).setCar(car);
		System.out.println("Setting the car at " +x + " and " +y);
		
	}

	/* (non-Javadoc)
	 * @see IGrid#removeCarFrom(int, int)
	 */
	@Override
	public void removeCarFrom(int x, int y) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see IGrid#hasTrafficLightAt(int, int)
	 */
	@Override
	public boolean hasTrafficLightAt(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see IGrid#getTrafficLightAt(int, int)
	 */
	@Override
	public ITrafficLight getTrafficLightAt(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see IGrid#placeTrafficLightAt(int, int, ITrafficLight)
	 */
	@Override
	public void placeTrafficLightAt(int x, int y, ITrafficLight tl) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see IGrid#removeTrafficLightFrom(int, int)
	 */
	@Override
	public void removeTrafficLightFrom(int x, int y) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see IGrid#getWidth()
	 */
	@Override
	public int getWidth() {
		return this.width;
	}

	/* (non-Javadoc)
	 * @see IGrid#getHeight()
	 */
	@Override
	public int getHeight() {
		return this.height;
	}

}
