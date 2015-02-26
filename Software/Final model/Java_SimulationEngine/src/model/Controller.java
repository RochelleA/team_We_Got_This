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
		Timer mainTimer = new Timer(40, this);
	
	/**
	 * 
	 */
	public Controller(){
		
		
		 carsFactory.start();
		 mainTimer.start();
	}
	
	
	/**
	 * Move car.
	 */
	public void moveCar(){
		
		Iterator<ICar> i = listCars.iterator();
		while(i.hasNext()){
			ICar myCar = i.next();
			//testCrash(myCar);
			
			
				
		  	}
		
		
		
	}
	
	
	/**
	 * Action performed.
	 *
	 * @param e the e
	 */
	public void actionPerformed (ActionEvent e){
		
		moveCar();
		
	}
	
	
	
	public void run() {
		while(true){
			
Random randomCars = new Random();
			
			try {
				Thread.sleep(randomCars.nextInt(1500));
				int mCase=randomCars.nextInt(5);
		
					System.out.println("This is a runnable! &&&&&&&&&&&&&&&&&");
					
					
					System.out.println(model.getGrid().toString());
					
					ICar car = new Car();
					car.setPosition(mCase, 10);
					car.setSpeed(1);
					
					grid.placeCarAt(mCase, 10, car);
					
					listCars.add(car);
					
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