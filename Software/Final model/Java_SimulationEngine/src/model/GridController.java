/*
 * 
 */
package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import javax.swing.Timer;

import core.CellType;
import core.Direction;
import core.ICar;
import core.IGrid;
import core.ITrafficLight;
import core.TrafficLight;
import core.TrafficLightColour;
import events.DataEvent;
import events.DataPoint;
import events.EventListener;
import events.SimpleEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class GridController.
 */
public class GridController implements EventListener, ActionListener, IGridController {
		
		/** The model. */
		private Model model;
		
		/** The status. */
		private String status;
		
		/** The tr light. */
		private ITrafficLight trLight;
		
		/** The cars y. */
		int carsX, carsY;
		
		
		/** The grid. */
		IGrid grid;
	
		//array of the cars
		/** The list cars. */
		List<ICar> listCars = new ArrayList<ICar>();
		
		/** The all cars. */
		List<ICar> allCars = new ArrayList<ICar>();
		
		
		/** The main timer. */
		Timer mainTimer = new Timer(150, this);
		
		/** The iterating. */
		private boolean iterating;
		
		/** The rounds. */
		private int rounds = 0;
	
	/**
	 * A class which controls the state of the grid and sends events on changes.
	 *
	 * @param grid the grid
	 * @param m the m
	 */
	public GridController(IGrid grid, Model m){
		this.grid = grid;
		this.model = m;
		trLight = new TrafficLight();
		trLight.getColour();
		this.setStatus(Model.STATUS_PAUSED);
		
		for (ITrafficLight tl : grid.getTrafficLights()){
			tl.addEventListener(this);
		}
	}
	
	/* (non-Javadoc)
	 * @see model.IGridController#startTimer()
	 */
	public void startTimer(){
		mainTimer.start();
		this.setStatus(Model.STATUS_RUNNING);
	}
	
	/* (non-Javadoc)
	 * @see model.IGridController#stopTimer()
	 */
	public void stopTimer(){
		mainTimer.stop();
		this.setStatus(Model.STATUS_PAUSED);
	}
	
		
	//driving to RoundAbout
	/**
	 * Drive round about.
	 *
	 * @param car the car
	 */
	private void driveRoundAbout(ICar car){
		ICar myCar = car;
				
				//if the circle is on the right hand side of the driver and there is a grass on the left side, then the car knows it should drive to North-East
				if((Direction.CIRCLE==grid.getCellDirection(myCar.getX()+4,  myCar.getY()+4) 
				|| Direction.CIRCLE==grid.getCellDirection(myCar.getX()+1,  myCar.getY()+1) && (CellType.EMPTY==grid.getCellType(myCar.getX()-3,  myCar.getY()-2))) && (Direction.SOUTH!=grid.getCellDirection(myCar.getX()+1,  myCar.getY()-5))
				&& (Direction.NORTH!=grid.getCellDirection(myCar.getX(),  myCar.getY()-2)) )
				{		
					driveNorthEast(myCar);
				}
			   
				//if the circle on right side and the road to North and South on left side then go to East
			   	else if((Direction.CIRCLE==grid.getCellDirection(myCar.getX(),  myCar.getY()+2) && (Direction.NORTH==grid.getCellDirection(myCar.getX(),  myCar.getY()-5))) 
			   			|| (Direction.CIRCLE==grid.getCellDirection(myCar.getX(),  myCar.getY()+2) && (Direction.SOUTH==grid.getCellDirection(myCar.getX()+1,  myCar.getY()-5) || Direction.NORTH==grid.getCellDirection(myCar.getX()+2,  myCar.getY()-5)))
			   			|| (Direction.NORTH==grid.getCellDirection(myCar.getX(),  myCar.getY()-2)))
				{	
					driveEast(myCar);
				}
				
				//if circle on right side and grass on left side then go to EAST-South
			   	else if(Direction.CIRCLE==grid.getCellDirection(myCar.getX()-1,  myCar.getY()+3) && (CellType.EMPTY==grid.getCellType(myCar.getX()+5,  myCar.getY())))
				{		
					driveEastSouth(myCar);
				}
			
				//if the circle is on the right side and road to WEST and EAST on the left side then go to SOUTH
			   	else if(Direction.CIRCLE==grid.getCellDirection(myCar.getX()-3,  myCar.getY()-1) && (Direction.EAST==grid.getCellDirection(myCar.getX()+5,  myCar.getY())) || Direction.WEST==grid.getCellDirection(myCar.getX()+5,  myCar.getY()+1))
				{	
					driveSouth(myCar);		
				}
				
				//if the circle is on left-top and the grass is on right, then go to SOUTH-WEST
				else if(Direction.CIRCLE==grid.getCellDirection(myCar.getX()-3,  myCar.getY()) && (CellType.EMPTY==grid.getCellType(myCar.getX(),  myCar.getY()+5)))
				{	
					driveSouthWest(myCar);
				}
				
				//if the circle is on top and the roads to NORTH and SOUTH down then drive WEST
				else if(Direction.CIRCLE==grid.getCellDirection(myCar.getX(),  myCar.getY()-3) && (Direction.NORTH==grid.getCellDirection(myCar.getX()-1,  myCar.getY()+5)) || Direction.SOUTH==grid.getCellDirection(myCar.getX()-1,  myCar.getY()+5))
				{
					driveWest(myCar);
				}
				
				//if the circle is on right-top and grass is on left, then drive WEST-NORTH
				else if(Direction.CIRCLE==grid.getCellDirection(myCar.getX(),  myCar.getY()-3) && (CellType.EMPTY==grid.getCellType(myCar.getX()-1,  myCar.getY()+5)))
				{
					driveWestNorth(myCar);
				}
				
				//if the circle is on the right side and road to WEST and EAST on the left side then go to NORTH
			   	else if(Direction.CIRCLE==grid.getCellDirection(myCar.getX()+4,  myCar.getY()-2))// && (Direction.EAST==grid.getCellDirection(myCar.getX()-5,  myCar.getY())) || Direction.WEST==grid.getCellDirection(myCar.getX()-5,  myCar.getY()))
				{
					driveNorth(myCar);		
				}
				
	}
	
	
	//driving to SOUTH-WEST
	/**
	 * Drive west north.
	 *
	 * @param car the car
	 */
	public void driveWestNorth(ICar car){
		ICar myCar = car;
		if(!grid.hasCarAt(myCar.getX()-1, myCar.getY()-1)){

			grid.removeCarFrom(myCar.getX(), myCar.getY());
			grid.placeCarAt(myCar.getX()-1, myCar.getY()-1, myCar);
			myCar.setY(myCar.getY()-1);
			myCar.setX(myCar.getX()-1);
			myCar.setcarsDir(Direction.westNorth);
		}	
	}

	
	//driving to NORTH-EAST
	/**
	 * Drive north east.
	 *
	 * @param car the car
	 */
	public void driveNorthEast(ICar car){
		ICar myCar = car;
		if(!grid.hasCarAt(myCar.getX()+1, myCar.getY()-1)){

			grid.removeCarFrom(myCar.getX(), myCar.getY());
			grid.placeCarAt(myCar.getX()+1, myCar.getY()-1, myCar);
			myCar.setY(myCar.getY()-1);
			myCar.setX(myCar.getX()+1);
			myCar.setcarsDir(Direction.northEast);
			// drive EAST-NORTH
		}	
	}
	
	//driving to SOUTH-WEST
	/**
	 * Drive south west.
	 *
	 * @param car the car
	 */
	public void driveSouthWest(ICar car){
		ICar myCar = car;
		if(!grid.hasCarAt(myCar.getX()-1, myCar.getY()+1)){

			grid.removeCarFrom(myCar.getX(), myCar.getY());
			grid.placeCarAt(myCar.getX()-1, myCar.getY()+1, myCar);
			myCar.setY(myCar.getY()+1);
			myCar.setX(myCar.getX()-1);
			myCar.setcarsDir(Direction.southWest);
		}	
	}
	
	//driving to EAST-SOUTH
	/**
	 * Drive east south.
	 *
	 * @param car the car
	 */
	public void driveEastSouth(ICar car){
			ICar myCar = car;
			if(!grid.hasCarAt(myCar.getX()+1, myCar.getY()+1)){

				grid.removeCarFrom(myCar.getX(), myCar.getY());
				grid.placeCarAt(myCar.getX()+1, myCar.getY()+1, myCar);
				myCar.setY(myCar.getY()+1);
				myCar.setX(myCar.getX()+1);
				myCar.setcarsDir(Direction.eastSouth);
			}	
		}
		
	
	//driving to NORTH
	/**
	 * Drive north.
	 *
	 * @param car the car
	 */
	public void driveNorth(ICar car){
		ICar myCar = car;
		if(!grid.hasCarAt(myCar.getX(), myCar.getY()-myCar.getSpeed())){

			grid.removeCarFrom(myCar.getX(), myCar.getY());
			grid.placeCarAt(myCar.getX(), myCar.getY()-myCar.getSpeed(), myCar);
			myCar.setY(myCar.getY()-myCar.getSpeed());
			myCar.setcarsDir(Direction.NORTH);
			//going to NORTH
		}
		else 
			overTake(car);
	}
	
	//driving to SOUTH
	/**
	 * Drive south.
	 *
	 * @param car the car
	 */
	public void driveSouth(ICar car){
		ICar myCar = car;
		if(grid.hasCarAt(myCar.getX(), myCar.getY()+myCar.getSpeed()) && (grid.getCellDirection(myCar.getX(), myCar.getY())==Direction.JUNCTION))
		{
			myCar.setStopCounter(myCar.getStopC()+1);
		}
		else if(!grid.hasCarAt(myCar.getX(), myCar.getY()+myCar.getSpeed())){

			grid.removeCarFrom(myCar.getX(), myCar.getY());
			grid.placeCarAt(myCar.getX(), myCar.getY()+myCar.getSpeed(), myCar);
			myCar.setY(myCar.getY()+myCar.getSpeed());
			myCar.setcarsDir(Direction.SOUTH);
			//going to SOUTH
		}	
		else 
			overTake(car);
	}
	
	//driving to EAST
	/**
	 * Drive east.
	 *
	 * @param car the car
	 */
	public void driveEast(ICar car){
		ICar myCar = car;
		if(grid.hasCarAt(myCar.getX()+myCar.getSpeed(), myCar.getY()) && (grid.getCellDirection(myCar.getX(), myCar.getY())==Direction.JUNCTION))
		{
			myCar.setStopCounter(myCar.getStopC()+1);
		}
		else if(!grid.hasCarAt(myCar.getX()+myCar.getSpeed(), myCar.getY())){
			grid.removeCarFrom(myCar.getX(), myCar.getY());
			grid.placeCarAt(myCar.getX()+myCar.getSpeed(), myCar.getY(), myCar);
			myCar.setX(myCar.getX()+myCar.getSpeed());
			myCar.setcarsDir(Direction.EAST);
			//going to EAST			
		}	
		else
			overTake(car);
		
	}
	
	//driving to WEST
	/**
	 * Drive west.
	 *
	 * @param car the car
	 */
	public void driveWest(ICar car){
		ICar myCar = car;
		
		if(!grid.hasCarAt(myCar.getX()-myCar.getSpeed(), myCar.getY())){
			
			grid.removeCarFrom(myCar.getX(), myCar.getY());
			grid.placeCarAt(myCar.getX()-myCar.getSpeed(), myCar.getY(), myCar);
			
			myCar.setX(myCar.getX()-myCar.getSpeed());
			myCar.setcarsDir(Direction.WEST);
			//going to WEST
		}
		else 
			overTake(car);
		
	}
	
	//OVER TAKING
	/**
	 * Over take.
	 *
	 * @param car the car
	 */
	public void overTake(ICar car){
		ICar myCar = car;
	
		switch (grid.getCellDirection(myCar.getX(),  myCar.getY()))
				{
			case WEST:
			
				if((grid.hasCarAt(myCar.getX()-myCar.getSpeed(), myCar.getY()) ) && (!grid.hasCarAt(myCar.getX()-myCar.getSpeed(),myCar.getY()-1) && !grid.hasCarAt(myCar.getX(),myCar.getY()-1)  ) && (grid.getCellDirection(myCar.getX()-1,  myCar.getY()-1) == Direction.WEST ))
				{
					grid.removeCarFrom(myCar.getX(), myCar.getY());
					grid.placeCarAt(myCar.getX()-myCar.getSpeed(), myCar.getY()-1, myCar);
					myCar.setX(myCar.getX()-myCar.getSpeed());
					myCar.setY(myCar.getY()-1);
					break;
				}
				break;
				
			case EAST:
	
				if((grid.hasCarAt(myCar.getX()+myCar.getSpeed(), myCar.getY()) ) && (!grid.hasCarAt(myCar.getX()+myCar.getSpeed(),myCar.getY()+1) && !grid.hasCarAt(myCar.getX(),myCar.getY()+1)  ) && (grid.getCellDirection(myCar.getX()+1,  myCar.getY()+1) == Direction.EAST ))
				{
					grid.removeCarFrom(myCar.getX(), myCar.getY());
					grid.placeCarAt(myCar.getX()+myCar.getSpeed(), myCar.getY()+1, myCar);
					myCar.setX(myCar.getX()+myCar.getSpeed());
					myCar.setY(myCar.getY()+1);
					break;
				}
				break;
				
			case NORTH:
				if((grid.hasCarAt(myCar.getX(), myCar.getY()-myCar.getSpeed()) ) && (!grid.hasCarAt(myCar.getX()+1,myCar.getY()-myCar.getSpeed()) && !grid.hasCarAt(myCar.getX()+1,myCar.getY())) && (grid.getCellDirection(myCar.getX()+1,  myCar.getY()-1) == Direction.NORTH ))
				{
					grid.removeCarFrom(myCar.getX(), myCar.getY());
					grid.placeCarAt(myCar.getX()+1, myCar.getY()-myCar.getSpeed(), myCar);
					myCar.setX(myCar.getX()+1);
					myCar.setY(myCar.getY()-myCar.getSpeed());
					break;
				}
				break;
				
			case SOUTH:
				if((grid.hasCarAt(myCar.getX(), myCar.getY()+myCar.getSpeed()) ) && (!grid.hasCarAt(myCar.getX()-1,myCar.getY()+myCar.getSpeed()) && !grid.hasCarAt(myCar.getX()-1,myCar.getY())) && (grid.getCellDirection(myCar.getX()-1,  myCar.getY()+1) == Direction.SOUTH ))
				{
					grid.removeCarFrom(myCar.getX(), myCar.getY());
					grid.placeCarAt(myCar.getX()-1, myCar.getY()+myCar.getSpeed(), myCar);
					myCar.setX(myCar.getX()-1);
					myCar.setY(myCar.getY()+myCar.getSpeed());
					break;
				}
				break;

				
				}
	}
	
		/**
	 * Moving the car
	 * 
	 * Depending on the position of the car, and the direction the car will be moving
	 * the current position of the car is taken, if there is no car in front of it, the car is going to move.
	 * 
	 * The car will be moved depending on its speed.
	 * 
	 * 
	 */
	
	private void moveCar(){
		

	this.iterating = true;
	Iterator<ICar> i = listCars.iterator();
	while(i.hasNext())
	{

			ICar myCar = i.next();

			/**
			 * Statement checks the current position of the car
			 * 
			 * if car is on exit position, then it is removed from the list of cars
			 * 
			 * if the car is not on the list, then it is moved
			 */
			
			if(grid.isExit(myCar.getX(), myCar.getY())){
				System.out.println(myCar);
				System.out.println(myCar.getEnterDir());
				System.out.println(myCar.getExitDir());
				System.out.println(myCar.getNumbRounds());
				System.out.println(myCar.getNumbCells());
				myCar.getNumbCells();
				System.out.println("removed from the list");
				i.remove();
				grid.removeCarFrom(myCar.getX(),myCar.getY());
				continue;
				
			}	
			
			myCar.setNumbRounds(myCar.getNumbRounds()+1);
			carsX=myCar.getX();
			carsY=myCar.getY();
				
			
			switch(grid.getCellDirection(myCar.getX(),  myCar.getY()))
			{

				case EAST:
					if(grid.hasTrafficLightAt(myCar.getX(), myCar.getY())){
					if((grid.getTrafficLightAt(myCar.getX(), myCar.getY()).getColour()==TrafficLightColour.GREEN) || (grid.getTrafficLightAt(myCar.getX(), myCar.getY()).getColour()==TrafficLightColour.DISABLED)){
						driveEast(myCar);
						break;}
					else{break;}
					}
					else{
					driveEast(myCar);
					break;
					}
				case WEST:
					if(grid.hasTrafficLightAt(myCar.getX(), myCar.getY())){
						if((grid.getTrafficLightAt(myCar.getX(), myCar.getY()).getColour()==TrafficLightColour.GREEN) || (grid.getTrafficLightAt(myCar.getX(), myCar.getY()).getColour()==TrafficLightColour.DISABLED)){
						driveWest(myCar);
						break;}
						else{break;}
						}
						else{
							driveWest(myCar);
						break;
						}
				case NORTH:
					
					if(grid.hasTrafficLightAt(myCar.getX(), myCar.getY())){
						if((grid.getTrafficLightAt(myCar.getX(), myCar.getY()).getColour()==TrafficLightColour.GREEN) || (grid.getTrafficLightAt(myCar.getX(), myCar.getY()).getColour()==TrafficLightColour.DISABLED)){
							driveNorth(myCar);
						break;}
						else{break;}
						}
						else{
							driveNorth(myCar);
						break;
						}
					
					
				case SOUTH:
					
					if(grid.hasTrafficLightAt(myCar.getX(), myCar.getY())){
						if((grid.getTrafficLightAt(myCar.getX(), myCar.getY()).getColour()==TrafficLightColour.GREEN) || (grid.getTrafficLightAt(myCar.getX(), myCar.getY()).getColour()==TrafficLightColour.DISABLED)){
							driveSouth(myCar);
						break;}
						else{break;}
						}
						else{
							driveSouth(myCar);
						break;
						}
					
					
				case JUNCTION:
	
							/** 
							* comparing the entered Direction, to know where the car is coming from, to know where to keep direction
							*
							* ***************************** COMING FROM WEST
							*/
					if(myCar.getEnterDir()==Direction.WEST)
					{
								 /**
								 * comparing the exit point, to know the direction where to turn
								 * 
								 * *******************      GOING TO SOUTH
								 */
								if(myCar.getExitDir()==Direction.SOUTH)
								{
									 /**
									 * the car is checking the direction of the road, by adding 7 to its current positon
									 *  if there is a right direction then it will turn to that way
									 *  if no, the car will move the same direction as before
									 *  
									 * 
									 */
									if(myCar.getExitDir()!=grid.getCellDirection(myCar.getX(),  myCar.getY()+7))
										{
										driveEast(myCar);
											break;
										}		
									else
									{
										if((myCar.getStopC()>1) && (grid.getCellDirection(myCar.getX()+3,  myCar.getY())==Direction.EAST)){
											myCar.setExitDir(Direction.EAST);
											break;
										}
										else{
										driveSouth(myCar);
										break;
										}
									}
								}
								
								/**
								* comparing the exit point, to know the direction where to turn
								* 
								* *******************      GOING TO SOUTH
								*/
								if(myCar.getExitDir()==Direction.EAST)
								{
									driveEast(myCar);
									break;	
								}
								
								/**
								 * comparing the exit point, to know the direction where to turn
								 * 
								 * *******************      GOING TO SOUTH
								 */
								if(myCar.getExitDir()==Direction.NORTH)
								{	
									
									if(myCar.getExitDir()!=grid.getCellDirection(myCar.getX(),  myCar.getY()-7))
									{
										driveEast(myCar);
										break;	
									}
									else
									{
										driveNorth(myCar);
										break;
									}
								}
						
				}
				else if(myCar.getEnterDir()==Direction.EAST)
				{
					if(myCar.getExitDir()==Direction.SOUTH)
					{
						if(myCar.getExitDir()!=grid.getCellDirection(myCar.getX(),  myCar.getY()+7))
						{
							driveWest(myCar);
							break;
						}
						else
						{
							driveSouth(myCar);
							break;
						}
					}
					
					if(myCar.getExitDir()==Direction.WEST)
					{
							driveWest(myCar);
							break;
					}
					if(myCar.getExitDir()==Direction.NORTH)
					{
						if(myCar.getExitDir()!=grid.getCellDirection(myCar.getX(),  myCar.getY()-7))
						{
							driveWest(myCar);
							break;
						}
						else
						{
							driveNorth(myCar);
							break;
						}
					}
				}
				else if(myCar.getEnterDir()==Direction.NORTH)
				{
					if(myCar.getExitDir()==Direction.WEST)
					{
						if(myCar.getExitDir()!=grid.getCellDirection(myCar.getX()-7,  myCar.getY()))
						{
							driveSouth(myCar);
							break;
						}
						else
						{
							driveWest(myCar);
							break;
						}
					}
					if(myCar.getExitDir()==Direction.EAST)
					{
						if(myCar.getExitDir()!=grid.getCellDirection(myCar.getX()+7,  myCar.getY()))
						{
							driveSouth(myCar);
							break;
						}
						else
						{
							driveEast(myCar);
							break;
						}
					}
					if(myCar.getExitDir()==Direction.SOUTH)
					{
						driveSouth(myCar);
						break;
					}
				}
				else if(myCar.getEnterDir()==Direction.SOUTH)
				{
					if(myCar.getExitDir()==Direction.WEST)
					{
						if(myCar.getExitDir()!=grid.getCellDirection(myCar.getX()-7,  myCar.getY()))
						{
							driveNorth(myCar);
							break;
						}
						else
						{
							driveWest(myCar);
							break;
						}
					}
					if(myCar.getExitDir()==Direction.EAST)
					{
						if(myCar.getExitDir()!=grid.getCellDirection(myCar.getX()+7,  myCar.getY()))
						{
							driveNorth(myCar);
							break;
						}
						else
						{
							if((myCar.getStopC()>1) && (grid.getCellDirection(myCar.getX(),  myCar.getY()-3)==Direction.NORTH)){
								myCar.setExitDir(Direction.NORTH);
								break;
							}
							else{
							driveEast(myCar);
							break;
							}
						}
					}
					if(myCar.getExitDir()==Direction.NORTH)
					{
						driveNorth(myCar);
						break;
					}
				}
					break;
					
					// **********************************************************************
				case ROUNDABOUT:
					
					// going to NORTH
					if(myCar.getExitDir()==Direction.NORTH
					 && myCar.getExitDir()==grid.getCellDirection(myCar.getX(),  myCar.getY()-5)) 
					{
						driveNorth(myCar);
						break;	
					}
					 // going to EAST
					else if(myCar.getExitDir()==Direction.EAST && myCar.getExitDir()==grid.getCellDirection(myCar.getX()+5,  myCar.getY()))
					{
						driveEast(myCar);
						break;	
					}
					// going to SOUTH
					else if(myCar.getExitDir()==Direction.SOUTH && myCar.getExitDir()==grid.getCellDirection(myCar.getX(),  myCar.getY()+5)) 
					{
						driveSouth(myCar);
						break;	
					}
					// going to WEST
					else if(myCar.getExitDir()==Direction.WEST && myCar.getExitDir()==grid.getCellDirection(myCar.getX()-5,  myCar.getY())) 
					{
						driveWest(myCar);
						break;	
					}
					
					else {
						driveRoundAbout(myCar);
						break;	
					}
			}		
			
			if ((carsX!=myCar.getX()) || (carsY!=myCar.getY()))
			{
				myCar.setNumbCells(myCar.getNumbCells()+1);
			}
		}
	

	this.iterating = false;
	
	}
	
	
	
	/**
	 * Action performed.
	 *
	 * @param e the e
	 */
	public void actionPerformed (ActionEvent e){
		//System.out.println("timer event");
		if(!iterating){
			try{
				rounds++;
				moveCar();
				System.out.println("Current number of the cars - " + listCars.size());
				System.out.println("Total number of the cars - " + allCars.size());
			}catch(ConcurrentModificationException cme){
				System.out.println("exception");
				this.iterating = false;
			}
			
			model.fireSimpleEvent(new SimpleEvent(this,SimpleEvent.MODEL_STEP));
			
			
			DataPoint dp = new DataPoint(getRound(), listCars.size());
			
			DataEvent de = new DataEvent(this, SimpleEvent.MODEL_NEW_DATA, dp);
			model.fireDataEvent(de);
			
		}else{
			System.out.println("iteration, wait");
		}
		
	}

	/* (non-Javadoc)
	 * @see model.IGridController#addCar(core.ICar)
	 */
	@Override
	public void addCar(ICar car) {
		
		grid.placeCarAt(car.getX(), car.getY(), car);
		listCars.add(car);
		allCars.add(car);

	}

	/* (non-Javadoc)
	 * @see model.IGridController#getStatus()
	 */
	@Override
	public String getStatus() {
		return this.status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	private void setStatus(String status) {
		this.status = status;
		model.fireSimpleEvent(new SimpleEvent(this, SimpleEvent.MODEL_STATUS_CHANGE));
	}

	/* (non-Javadoc)
	 * @see model.IGridController#getRound()
	 */
	@Override
	public int getRound() {
		// TODO Auto-generated method stub
		return rounds;
	}

	/* (non-Javadoc)
	 * @see model.IGridController#getTrafficLights()
	 */
	@Override
	public ArrayList<ITrafficLight> getTrafficLights() {
		return grid.getTrafficLights();
	}

	/* (non-Javadoc)
	 * @see events.EventListener#handleSimpleEvent(events.SimpleEvent)
	 */
	@Override
	public void handleSimpleEvent(SimpleEvent e) {
		if(e.getType().equals(SimpleEvent.TRAFFIC_LIGHT_CHANGE)){
			model.fireSimpleEvent(new SimpleEvent(this, SimpleEvent.TRAFFIC_LIGHT_CHANGE));
		}
		
	}
}
