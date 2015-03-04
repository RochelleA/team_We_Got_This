package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import core.Car;
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
	
	
	/**
	 * Moving the car
	 * 
	 * Depending on the position of the car, and the direction the car will be moving
	 * the current position of the car is taken, if there is no car in front of it, the car is going to move.
	 * 
	 * The car will be moved depending on its speed.
	 */
	private void moveCar(){
		//System.out.println(grid.toString());
		//	System.out.println("move car ");
		
		/**
		 * A list of the car which currently on the map, the list will store all cars while they are not on the 

exit points.
		 * 
		 * Iterator is going through all the cars in the list, using while loop
		 * 
		 *  So that each car is moved
		 */
		Iterator<ICar> i = listCars.iterator();
		while(i.hasNext()){
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
					if(!grid.hasCarAt(myCar.getX()+myCar.getSpeed(), myCar.getY())){
						grid.removeCarFrom(myCar.getX(), myCar.getY());
						grid.placeCarAt(myCar.getX()+myCar.getSpeed(), myCar.getY(), myCar);
						myCar.setX(myCar.getX()+myCar.getSpeed());
						//System.out.println("moved car to " +(myCar.getX()+myCar.getSpeed()));
					}				
					break;

				case WEST:
					if(!grid.hasCarAt(myCar.getX()-myCar.getSpeed(), myCar.getY())){

						grid.removeCarFrom(myCar.getX(), myCar.getY());
						grid.placeCarAt(myCar.getX()-myCar.getSpeed(), myCar.getY(), myCar);
						myCar.setX(myCar.getX()-myCar.getSpeed());
						//System.out.println("moved car to " +(myCar.getX()+myCar.getSpeed()));
					}	

					break;

				case NORTH:
					if(!grid.hasCarAt(myCar.getX(), myCar.getY()-myCar.getSpeed())){

						grid.removeCarFrom(myCar.getX(), myCar.getY());
						grid.placeCarAt(myCar.getX(), myCar.getY()-myCar.getSpeed(), myCar);
						myCar.setY(myCar.getY()-myCar.getSpeed());
						//System.out.println("moved car to " +(myCar.getX()+myCar.getSpeed()));
					}	


					break;

				case SOUTH:

					if(!grid.hasCarAt(myCar.getX(), myCar.getY()+myCar.getSpeed())){

						grid.removeCarFrom(myCar.getX(), myCar.getY());
						grid.placeCarAt(myCar.getX(), myCar.getY()+myCar.getSpeed(), myCar);
						myCar.setY(myCar.getY()+myCar.getSpeed());
						//System.out.println("moved car to " +(myCar.getX()+myCar.getSpeed()));
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

				listCars.add(car);
			}
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
				Thread.sleep(randomCars.nextInt(2000));
				mCase=randomCars.nextInt(5)+1;
				ranEnt=randomNumb.nextInt(2);

				addCars(mCase, ranEnt);


			} catch (InterruptedException e) {
				e.printStackTrace();
			}


		}
	}
	
}