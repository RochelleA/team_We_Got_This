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
	
	private static int MAX_WIDTH = 100;
	private static int MAX_HEIGHT = 100;

	private int width;
	private int height;
	private ArrayList<ArrayList<ICell>> cells;
	private ArrayList<ICell> entryCells;
	
	/**
	 * Constructs a 2D array of cells of specified width and height. 
	 * Each cells receives x and y coordinates.
	 * @param width width of the grid
	 * @param height height of the grid
	 * @throws IndexOutOfBoundsException if width or height is negative
	 * @see IGrid
	 */
	public Grid(int width, int height) {
		entryCells = new ArrayList<ICell>();
		this.width = width;
		this.height = height;
		if (width <= 0 || height <= 0){
			throw new IndexOutOfBoundsException("Width or height of the grid must be positive");
		}else if(width > MAX_WIDTH){
			throw new IndexOutOfBoundsException("WIDTH exceed MAX_WIDTH: " + MAX_HEIGHT);
		}else if(height > MAX_HEIGHT){
			throw new IndexOutOfBoundsException("Height exceed MAX_HEIGHT: " + MAX_HEIGHT);
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
//<<<<<<< HEAD
	
	/* (non-Javadoc)
	 * @see IGrid#getCellType(int, int)
	 */
//=======

	//PROPERTIES
	//====
//>>>>>>> origin/SimulationEngine
	@Override
	public CellType getCellType(int x, int y) {
		return this.getCellAt(x, y).getType();
	}

	@Override
	public void setCellType(int x, int y, CellType type) {
		this.getCellAt(x, y).setType(type);
	}

	@Override
	public Direction getCellDirection(int x, int y) {
		return this.getCellAt(x, y).getDirection();
	}

	@Override
	public void setCellDirection(int x, int y, Direction direction) {
		this.getCellAt(x, y).setDirection(direction);
	}

	@Override
	public boolean isExit(int x, int y) {
		return this.getCellAt(x, y).isExit();
	}

	@Override
	public void setIsExit(int x, int y, boolean isExit) {
		this.getCellAt(x, y).setIsExit(isExit);

	}
	
	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public boolean isEntry(int x, int y) {
		return this.getCellAt(x,y).isEntry();
	}

	@Override
	public void setIsEntry(int x, int y, boolean isEntry) {
		this.getCellAt(x, y).setIsEntry(isEntry);
		if(isEntry){
			entryCells.add(this.getCellAt(x, y));
		}else{
			entryCells.remove(this.getCellAt(x, y));
		}
		
	}
	//----

	//CARS
	//====
	@Override
	public boolean hasCarAt(int x, int y) {
		return this.getCellAt(x, y).hasCar();
	}

	@Override
	public ICar getCarAt(int x, int y) {
		return this.getCellAt(x, y).getCar();
	}

	@Override
	public void placeCarAt(int x, int y, ICar car) {
		this.getCellAt(x, y).setCar(car);
	}

	@Override
	public void removeCarFrom(int x, int y) {
		this.getCellAt(x, y).removeCar();

	}
	//----


	//TRAFFIC LIGHTS
	//====
	@Override
	public boolean hasTrafficLightAt(int x, int y) {
		return this.getCellAt(x,y).hasTrafficLight();
	}

	@Override
	public ITrafficLight getTrafficLightAt(int x, int y) {
		return this.getCellAt(x,y).getTrafficLight();
	}

	@Override
	public void placeTrafficLightAt(int x, int y, ITrafficLight tl) {
		this.getCellAt(x,y).setTrafficLight(tl);
	}

	@Override
	public void removeTrafficLightFrom(int x, int y) {
		this.getCellAt(x,y).removeTrafficLight();

	}
	//====


	@Override
	public ArrayList<ICell> getEntryCells() {
		return this.entryCells;
	}

}
