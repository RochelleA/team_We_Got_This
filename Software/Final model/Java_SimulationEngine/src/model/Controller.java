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

public class Controller  implements ActionListener, Runnable {
	
	
		public Model  model = new Model();
		
		IGrid grid = model.getGrid();
		//creating the cars
		Thread carsFactory = new Thread(this);
		
		//array of the cars
		List<ICar> listCars = new ArrayList<ICar>();
		
		
		/** The main timer. */
		Timer mainTimer = new Timer(150, this);
	
	/**
	 * 
	 */
	public Controller(){
		/*
		ICar car = new Car();
		car.setPosition(0, 10);
		car.setSpeed(1);
		grid.placeCarAt(0, 10, car);
		
		grid.setIsExit(39, 10, true);
		
		listCars.add(car);*/
		 carsFactory.start();
		 mainTimer.start();
	}
	
	
	/**
	 * Move car.
	 */
	public void moveCar(){
		System.out.println(model.getGrid().toString());
	//	System.out.println("move car ");
		Iterator<ICar> i = listCars.iterator();
		while(i.hasNext()){
		//	System.out.println("iterator has next");
			ICar myCar = i.next();
			//testCrash(myCar);
			//System.out.println("cars x - " + myCar.getX() + "cars y - " + myCar.getY());
		
			//System.out.println(grid.getCellDirection(myCar.getX(),  myCar.getY()));
			
			

			if((myCar.getX()>=0 && myCar.getX()<grid.getWidth()-1) && (myCar.getY()>0 && myCar.getY()<20))
			{
			switch(grid.getCellDirection(myCar.getX(),  myCar.getY()))
			{
				
				case EAST:
					if(!grid.hasCarAt(myCar.getX()+myCar.getSpeed(), 10)){
						
						grid.removeCarFrom(myCar.getX(),10);
						
					
						
						grid.placeCarAt(myCar.getX()+myCar.getSpeed(), 10, myCar);
						myCar.setX(myCar.getX()+myCar.getSpeed());
						//System.out.println("moved car to " +(myCar.getX()+myCar.getSpeed()));
					}				
					break;
					
				case WEST:
					
					break;
					
				case NORTH:
					
					break;
					
				case SOUTH:
					
					break;
			}
				
		  	}
		else{
		//	System.out.println("removed from the list");
			i.remove();
			grid.removeCarFrom(myCar.getX(),10);
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
		
	}
	
	
	
	public void run() { 
		while(true){
			
Random randomCars = new Random();
			
			try {
				Thread.sleep(randomCars.nextInt(2000));
				int mCase=randomCars.nextInt(5);
		
				//	System.out.println("This is a runnable! &&&&&&&&&&&&&&&&&");
					
					
				//	System.out.println(model.getGrid().toString());
					Iterator<ICar> i = listCars.iterator();
					
					//check if the there is a car already, if not, then create new one
					if(!grid.hasCarAt(mCase, 10)){
						
						ICar car = new Car();
						car.setPosition(mCase, 10);
						car.setSpeed(1);
						grid.placeCarAt(mCase, 10, car);
						
						listCars.add(car);
					}
					
					////////// moving cars ******************
				
					///////// **********************8
					
				/*
				switch(mCase)
				{
					case 1:
						System.out.println("carrrrr");
						//from west to east    randomCars.nextInt(5)+1
						listCars.add(new Car(1, 10, 1, 5));
						break;
					case 2:
						System.out.println("carrrrr");
						//from west to east    randomCars.nextInt(5)+1
						listCars.add(new Car(2, 10, 1, 5));
						break;
						
				}
				
		*/
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			

			}
	}
	
}