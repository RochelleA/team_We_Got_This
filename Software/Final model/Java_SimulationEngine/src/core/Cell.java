package core;

/**
 * An implementation for ICell interface.
 * @author Anton
 * @version 1.1
 *
 */
public class Cell implements ICell {

	private CellType type;
	private int x, y;
	private Direction dir;
	private boolean isExit;
	private boolean isEntry;
	private ICar car;
	private ITrafficLight tl;
	
	/**
	 * A single cell on a grid. The object should only be created by an instance of an IGrid during
	 * initialisation.
	 * @param x x coordinate of the new cell
	 * @param y y coordinate of the new cell
	 * @see Grid#Grid(int, int)
	 */
	public Cell(int x, int y) {
		this.car = null;
		if(x < 0 || y < 0){
			throw new IndexOutOfBoundsException("X and Y of the cell must be positive");
		}
		this.x = x;
		this.y = y;
	}

	@Override
	public CellType getType() {
		return this.type;
	}

	@Override
	public void setType(CellType type) {
		this.type = type;
	}

	@Override
	public Direction getDirection() {
		return this.dir;
	}

	@Override
	public void setDirection(Direction direction) {
		this.dir = direction;

	}

	@Override
	public boolean isExit() {
		return this.isExit;
	}

	@Override
	public void setIsExit(boolean b) {
		this.isExit = b;
	}
	
	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}
	
	@Override
	public String toString(){
		return "cell@("+this.x+";"+this.y+")";
	}

	@Override
	public boolean isEntry() {
		return this.isEntry;
	}

	@Override
	public void setIsEntry(boolean b) {
		this.isEntry = b;
		
	}

	//CARS
	//====
	@Override
	public void setCar(ICar car) {
		if(this.car == null){
			this.car = car;
		}else{
			throw new GridException("Cell already has a car");
		}

	}	
	
	@Override
	public boolean hasCar() {
		return this.car != null;
	}
	
	@Override
	public ICar getCar() {
		if(this.car != null){
			return this.car;
		}else{
			throw new GridException("Cell does not have a car to return");
		}
	}
	
	@Override
	public void removeCar(){
		if(this.car != null){
			this.car = null;
		}else{
			throw new GridException("Cell does not have a car");
		}
	}

	
	//TRAFFIC LIGHTS
	//====
	@Override
	public void setTrafficLight(ITrafficLight tl) {
		if(this.tl == null){
			this.tl = tl;
		}else{
			throw new GridException("Cell already has a traffic light");
		}

	}
	
	@Override
	public boolean hasTrafficLight() {
		return this.tl != null;
	}
	
	@Override
	public ITrafficLight getTrafficLight() {
		if (this.tl != null){
			return this.tl;
		}else{
			throw new GridException("Cell does not have a traffic light");
		}
	}
	
	@Override
	public void removeTrafficLight() {
		if(this.tl != null){
			this.tl = null;
		}else{
			throw new GridException("Cell does not have a traffic light to remove");
		}
		
	}
	//====


}
