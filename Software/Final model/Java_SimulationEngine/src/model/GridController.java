package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import core.Car;
import core.Direction;
import core.ICar;
import core.IGrid;

public class GridController implements ActionListener, Runnable {
	
	
		int mCase;
		int ranEnt;
	
		private Model model;
		
		IGrid grid;// = model.getGrid();
		//creating the cars
		Thread carsFactory = new Thread(this);
		
		//array of the cars
		List<ICar> listCars = new ArrayList<ICar>();
		
		
		/** The main timer. */
		Timer mainTimer = new Timer(150, this);
	
	/**
	 * A class which controls the state of the grid and sends events on changes.
	 */
	public GridController(IGrid grid, Model m){
		this.grid = grid;
		this.model = m;

		
		 setExitPoint();
		 carsFactory.start();
		 //mainTimer.start();
	}
	
	public void startTimer(){
		mainTimer.start();
		//carsFactory.start();
	}
	public void stopTimer(){
		mainTimer.stop();
		//carsFactory.
	}
	
	/**
	 * Setting exit parameters 
	 * 
	 * The cars will be removed from the list of cars and from the map once they at these points
	 * 
	 */
	public void setExitPoint(){
		
		grid.setIsExit(39, 10, true);
		grid.setIsExit(39, 11, true);
		grid.setIsExit(0, 14, true);
		grid.setIsExit(0, 13, true);
		grid.setIsExit(23, 24, true);
		grid.setIsExit(24, 24, true);
		grid.setIsExit(20, 1, true);
		grid.setIsExit(21, 1, true);
		
	}
	
	//driving to SOUTH
	private void driveSouth(ICar car){
		ICar myCar = car;
		if(!grid.hasCarAt(myCar.getX(), myCar.getY()+myCar.getSpeed())){

			grid.removeCarFrom(myCar.getX(), myCar.getY());
			grid.placeCarAt(myCar.getX(), myCar.getY()+myCar.getSpeed(), myCar);
			myCar.setY(myCar.getY()+myCar.getSpeed());
			//going to SOUTH
		}	
	}
	
	//driving to NORTH
	private void driveNorth(ICar car){
		ICar myCar = car;
		if(!grid.hasCarAt(myCar.getX(), myCar.getY()-myCar.getSpeed())){

			grid.removeCarFrom(myCar.getX(), myCar.getY());
			grid.placeCarAt(myCar.getX(), myCar.getY()-myCar.getSpeed(), myCar);
			myCar.setY(myCar.getY()-myCar.getSpeed());
			//going to NORTH
		}	
	}
	
	//driving to EAST
	private void driveEast(ICar car){
		ICar myCar = car;
		
		if(!grid.hasCarAt(myCar.getX()+myCar.getSpeed(), myCar.getY())){
			grid.removeCarFrom(myCar.getX(), myCar.getY());
			grid.placeCarAt(myCar.getX()+myCar.getSpeed(), myCar.getY(), myCar);
			myCar.setX(myCar.getX()+myCar.getSpeed());
			//going to EAST
		}		
	}
	
	//driving to WEST
	private void driveWest(ICar car){
		ICar myCar = car;
		
		if(!grid.hasCarAt(myCar.getX()-myCar.getSpeed(), myCar.getY())){

			grid.removeCarFrom(myCar.getX(), myCar.getY());
			grid.placeCarAt(myCar.getX()-myCar.getSpeed(), myCar.getY(), myCar);
			myCar.setX(myCar.getX()-myCar.getSpeed());
			//going to WEST
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
		//System.out.println(grid.toString());
		//	System.out.println("move car ");
		
		/**
		 * A list of the car which currently on the map, the list will store all cars while they are not on the 
		 * exit points.
		 * 
		 * Iterator is going through all the cars in the list, using while loop
		 * 
		 *  So that each car is moved
		 */
	Iterator<ICar> i = listCars.iterator();
	while(i.hasNext())
	{
			//	System.out.println("iterator has next");
			ICar myCar = i.next();
			//testCrash(myCar);
			//System.out.println("cars x - " + myCar.getX() + "cars y - " + myCar.getY());

			//System.out.println(grid.getCellDirection(myCar.getX(),  myCar.getY()));



			/**
			 * Statement checks the current position of the car
			 * 
			 * if car is on exit position, then it is removed from the list of cars
			 * 
			 * if the car is not on the list, then it is moved
			 */
			
			
			if(!grid.isExit(myCar.getX(), myCar.getY()))
			{
				switch(grid.getCellDirection(myCar.getX(),  myCar.getY()))
				{

					case EAST:
						driveEast(myCar);
						break;
	
					case WEST:
						driveWest(myCar);
						break;
	
					case NORTH:
						driveNorth(myCar);
						break;
	
					case SOUTH:
						driveSouth(myCar);
						break;
						
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
											driveSouth(myCar);
												break;
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
								driveEast(myCar);
								break;
							}
						}
						if(myCar.getExitDir()==Direction.NORTH)
						{
							driveNorth(myCar);
							break;
						}
						
					}
			
						break;
				}
			}
			else{
				System.out.println("removed from the list");
				i.remove();
				grid.removeCarFrom(myCar.getX(),myCar.getY());
			}

		
	}
	
	}
	
	/**
	 * Action performed.
	 *
	 * @param e the e
	 */
	public void actionPerformed (ActionEvent e){
		//System.out.println("timer event");
		moveCar();
		model.fireEvent();
		
	}
	
	private void addCars(int mCase, int ranEnt){
		switch(mCase)
		{
		case 1:
			if(!grid.hasCarAt(1, 10+ranEnt))
			{	
				ICar car = new Car();
				car.setPosition(1, 10+ranEnt);
				car.setSpeed(1);
				grid.placeCarAt(1, 10+ranEnt, car);
				car.setEnterDir(Direction.WEST);
				car.setExitDir(Direction.SOUTH);
				listCars.add(car);
			}
			break;
			
		case 2:
			if(!grid.hasCarAt(39, 14-ranEnt))
			{	
				ICar car = new Car();
				car.setPosition(39, 14-ranEnt);
				car.setSpeed(1);
				grid.placeCarAt(39, 14-ranEnt, car);
				car.setEnterDir(Direction.EAST);
				car.setExitDir(Direction.WEST);
				listCars.add(car);
			}
			break;
		case 3:
			if(!grid.hasCarAt(23+ranEnt, 1))
			{	
				ICar car = new Car();
				car.setPosition(23+ranEnt, 1);
				car.setSpeed(1);
				grid.placeCarAt(23+ranEnt, 1, car);
				car.setEnterDir(Direction.NORTH);
				car.setExitDir(Direction.EAST);
				listCars.add(car);
			}
			break;
		case 4:
			if(!grid.hasCarAt(20+ranEnt, 24))
			{	
				ICar car = new Car();
				car.setPosition(20+ranEnt, 24);
				car.setSpeed(1);
				grid.placeCarAt(20+ranEnt, 24, car);
				car.setEnterDir(Direction.SOUTH);
				car.setExitDir(Direction.NORTH);
				listCars.add(car);
			}
			
		case 5:
			if(!grid.hasCarAt(20+ranEnt, 24))
			{	
				ICar car = new Car();
				car.setPosition(20+ranEnt, 24);
				car.setSpeed(1);
				grid.placeCarAt(20+ranEnt, 24, car);
				car.setEnterDir(Direction.SOUTH);
				car.setExitDir(Direction.EAST);
				listCars.add(car);
			}
			break;
			
		case 6:
			if(!grid.hasCarAt(20+ranEnt, 24))
			{	
				ICar car = new Car();
				car.setPosition(20+ranEnt, 24);
				car.setSpeed(1);
				grid.placeCarAt(20+ranEnt, 24, car);
				car.setEnterDir(Direction.SOUTH);
				car.setExitDir(Direction.WEST);
				listCars.add(car);
			}
			break;
			
		case 7:
			if(!grid.hasCarAt(1, 10+ranEnt))
			{	
				ICar car = new Car();
				car.setPosition(1, 10+ranEnt);
				car.setSpeed(1);
				grid.placeCarAt(1, 10+ranEnt, car);
				car.setEnterDir(Direction.WEST);
				car.setExitDir(Direction.EAST);
				listCars.add(car);
			}
			break;
			
		case 8:
			if(!grid.hasCarAt(23+ranEnt, 1))
			{	
				ICar car = new Car();
				car.setPosition(23+ranEnt, 1);
				car.setSpeed(1);
				grid.placeCarAt(23+ranEnt, 1, car);
				car.setEnterDir(Direction.NORTH);
				car.setExitDir(Direction.WEST);
				listCars.add(car);
			}
			break;
		}
	}
	/**
	 * Generate Cars
	 */
	public void run() { 
		while(true){

			Random randomCars = new Random();
			Random randomNumb = new Random();	

			try {
				Thread.sleep(randomCars.nextInt(1000));
				mCase=randomCars.nextInt(8)+1;
				ranEnt=randomNumb.nextInt(2);

				addCars(mCase, ranEnt);


			} catch (InterruptedException e) {
				e.printStackTrace();
			}


		}
	}
	
}