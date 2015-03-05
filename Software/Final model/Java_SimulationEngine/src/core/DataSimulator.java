/**
 * 
 */
package core;

import java.util.Random;

import events.DataEvent;
import events.EventDispatchable;

/**
 * A class to simulate appearances of cars on the map
 * @author Anton
 *
 */
public class DataSimulator extends EventDispatchable implements Runnable {
	private IGrid grid;
	
	public DataSimulator(IGrid grid){
		this.grid = grid;
		Thread carsFactory = new Thread(this);
		carsFactory.start();
	}
	public void dispatchEvent(){
	    Object data = "new data";
	    DataEvent de = new DataEvent(this, DataEvent.NEW_CAR, data);
		this.fireDataEvent(de);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){

			Random random = new Random();

			try {
				Thread.sleep(random.nextInt(2000)); // random delay
				//int mCase=random.nextInt(5)+1;
				//int ranEnt=random.nextInt(2);
				
				this.dispatchEvent();
				//addCars(mCase, ranEnt);


			} catch (InterruptedException e) {
				e.printStackTrace();
			}


		}
	}
	
}
