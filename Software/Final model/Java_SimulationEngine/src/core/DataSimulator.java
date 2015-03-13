package core;

import java.util.Random;

import events.EventDispatchable;
import events.NewCarEvent;

/**
 * A class to simulate appearances of cars on the map
 * @author Anton
 *
 */
public class DataSimulator extends EventDispatchable implements Runnable {
	private static int P_CAR_WILL_APPEAR = 10;
	private IGrid grid;
	private int round = 0;
	
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
			
			round++;
			System.out.println("round: " + round);

			Random random = new Random();

			try {
				Thread.sleep(1000); // 1 s delay
				
				for (ICell c : grid.getEntryCells()){
					int r = random.nextInt(100);
					if(DataSimulator.P_CAR_WILL_APPEAR > r){
						//make car!
						//check if there is already car
						this.dispatchCar(c.getX(), c.getY());
					}
				}
				

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
	
}
