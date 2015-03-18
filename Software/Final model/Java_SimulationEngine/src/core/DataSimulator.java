package core;

import java.util.Random;

import events.DataEvent;
import events.EventDispatchable;
import events.NewCarEvent;
import events.SimpleEvent;

/**
 * A class to simulate appearances of cars on the map
 * @author Anton
 *
 */
public class DataSimulator extends EventDispatchable implements Runnable {
	private int prob_car_appear = 25;
	
	private IGrid grid;
	private int round = 0;
	private boolean isRunning = true;
	private int delay = 1000;  // 1 s default delay
	private Random random = new Random();
	
	public DataSimulator(IGrid grid){
		this.grid = grid;
		Thread carsFactory = new Thread(this);
		carsFactory.start();
	}
	private void dispatchCar(int x, int y){
		ICar car = new Car();
		
		car.setSpeed(1);
		car.setPosition(x,y);
		car.setEnterDir(Direction.NORTH);
		car.setExitDir(Direction.WEST);
		
		NewCarEvent ce = new NewCarEvent(this,car);
		
		this.fireDataEvent(ce);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			if(isRunning){
				round++;
				System.out.println("round: " + round);
	
				
					
				for (ICell c : grid.getEntryCells()){
					int r = random.nextInt(100);
					if(prob_car_appear > r){
						//make car!
						//check if there is already car
						this.dispatchCar(c.getX(), c.getY());
					}
				}
			}
			
			System.out.println(isRunning);
			
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
		this.fireSimpleEvent(new SimpleEvent(this,SimpleEvent.DATA_STATUS_CHANGE));
	}
	public boolean getRunning(){
		return this.isRunning;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}

	public void setProb_car_appear(int prob_car_appear) {
		this.prob_car_appear = prob_car_appear;
	}
	
}
