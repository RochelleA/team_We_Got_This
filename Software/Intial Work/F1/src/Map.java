import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Map extends JPanel implements ActionListener, Runnable{

	
	Timer mainTimer = new Timer(30, this);
	Image img = new ImageIcon("resources/map.jpg").getImage();
	Image light = new ImageIcon("resources/red.png").getImage();

	
	//parallel creating cars
	Thread carsFactory = new Thread(this);
	
	public static boolean trLight = true;
	
	// keep list of the cars
	List<Car> listCars = new ArrayList<Car>();
	
	public Map(){
		mainTimer.start();
		carsFactory.start();
		//traficLight.start();

	}
	
	public void paint (Graphics g) {
		g=(Graphics2D) g;
		g.drawImage(img, 0, 0, null);
		
		
		//drawing the car		
		Iterator<Car> i = listCars.iterator();
		while(i.hasNext()){
			Car myCar = i.next();
			
				if((myCar.x>-60 && myCar.x<1010) && (myCar.y>-20 && myCar.y<510))
				{
				  
				  myCar.Drive();
			      g.drawImage(myCar.img, myCar.x, myCar.y, null);
			      g.drawImage(light, 438, 180, null);
				}
				else{
					i.remove();
				}
			      	}

	}
	
	public void actionPerformed (ActionEvent e){
		
		repaint();

		
		//System.out.println(listCars.size());
	}

	@Override
	public void run() {
		while(true){
		
			
			try {
				Thread.sleep(2000);
								if (trLight)
			{
				System.out.println("Green");
				light = new ImageIcon("resources/green.png").getImage();
				
			trLight= false;
		}
		else{
			light = new ImageIcon("resources/red.png").getImage();
			trLight=true;
			System.out.println("Red");
		}
			
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	

				Random randomCars = new Random();
			
			try {
				Thread.sleep(randomCars.nextInt(3000));
				int mCase=randomCars.nextInt(4)+1;
		
				
				
				switch(mCase)
				{
					case 1:
						//from west to east    randomCars.nextInt(5)+1
						listCars.add(new Car(mCase, -25, 185, randomCars.nextInt(5)+1 , this));
						break;
						
					case 2:
						//from east to west
						listCars.add(new Car(mCase, 1000, 200, randomCars.nextInt(5)+1, this));
						break;
					
					case 3:
						//from north to sought
						listCars.add(new Car(mCase, 477, -5, randomCars.nextInt(5)+1, this));
						break;
					
					case 4:
						//from sought to north
						listCars.add(new Car(mCase, 464, 450, randomCars.nextInt(5)+1, this));
						break;
					
				}
				
		
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
