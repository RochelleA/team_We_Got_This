package core;

/**
 * An implementation for ICell interface.
 * @author Anton
 * @version 1
 *
 */
public class Cell implements ICell {

	private CellType type;
	private int x, y;
	private Direction dir;
	private boolean isExit;
	private ICar car;
	private ITrafficLight tl;
	/**
	 * A single cell on a grid. The object should only be created by an IGrid during
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

	/* (non-Javadoc)
	 * @see ICell#getType()
	 */
	@Override
	public CellType getType() {
		return this.type;
	}

	/* (non-Javadoc)
	 * @see ICell#setType(CellType)
	 */
	@Override
	public void setType(CellType type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see ICell#getDirection()
	 */
	@Override
	public Direction getDirection() {
		return this.dir;
	}

	/* (non-Javadoc)
	 * @see ICell#setDirection(Direction)
	 */
	@Override
	public void setDirection(Direction direction) {
		this.dir = direction;

	}

	/* (non-Javadoc)
	 * @see ICell#isExit()
	 */
	@Override
	public boolean isExit() {
		return this.isExit;
	}

	/* (non-Javadoc)
	 * @see ICell#setIsExit(boolean)
	 */
	@Override
	public void setIsExit(boolean b) {
		this.isExit = b;
	}

	/* (non-Javadoc)
	 * @see ICell#getCar()
	 */
	@Override
	public ICar getCar() {
		return this.car;
	}

	/* (non-Javadoc)
	 * @see ICell#setCar(ICar)
	 */
	@Override
	public void setCar(ICar car) {
		if(this.car == null){
			this.car = car;
		}else{
			throw new GridException("Cell already has a car");
		}
		// TODO Auto-generated method stub

	}
	
	//todo: add to interface
	public void removeCar(){
		this.car = null;
	}

	/* (non-Javadoc)
	 * @see ICell#getTrafficLight()
	 */
	@Override
	public ITrafficLight getTrafficLight() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see ICell#setTrafficLight(ITrafficLight)
	 */
	@Override
	public void setTrafficLight(ITrafficLight tl) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see ICell#getX()
	 */
	@Override
	public int getX() {
		return this.x;
	}

	/* (non-Javadoc)
	 * @see ICell#getY()
	 */
	@Override
	public int getY() {
		return this.y;
	}
	
	@Override
	public String toString(){
		return "cell@("+this.x+";"+this.y+")";
	}

}
