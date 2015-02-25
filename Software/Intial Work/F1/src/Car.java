import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Car {
	
	//traffic light
	 //Thread traficLight = new Thread(this);

	Image img = new ImageIcon("resources/redCar.jpg").getImage();
	//public static boolean trLight = true;
	
	int speed=0;
	int myCase=1;
	int x;
	int y=185;
	public int path=0;
	Map myMap;
	
	//return the rectangle of cars
	public Rectangle getRect() {
		return new Rectangle(x, y, 18, 7);
		
	}
	
	
	
	public Car(int myCase, int x, int y, int speed, Map myMap){
		this.x=x;
		this.y=y;
		this.speed=speed;
		this.myMap=myMap;
		this.myCase=myCase;
	
		
	}


	
	public void Drive() {
		switch(myCase)
		{
			case 1:
				
				if((x>=433 && x<=450) && (Map.trLight)){
				x=x;
				}
				else
				//else{
				{
				x+=speed;
				}
				/*
				if(x>path){
					System.out.println("********************"+path);
				path+=x;
				}
				if (x<=path){
					System.out.println("---------------------------"+path);
				x=x;
				}
				System.out.println("case "+myCase);} */
				break;
			case 2:
				//from east to west
				if((x>=485 && x<=500) && (Map.trLight)){
				x=x;
				}
				else{
				x-=speed; }
				//System.out.println("case "+myCase);
				break;
			case 3:
				//from north to south
				img = new ImageIcon("resources/redCarY.jpg").getImage();
				if((y>=157 && y<=165) && (!Map.trLight)){
				x=x;
				}
				else{
				y+=speed;}
				//System.out.println("case "+myCase);
				
				break;
			case 4:
				//from south to north
				img = new ImageIcon("resources/redCarY.jpg").getImage();
				if((y>=210 && y<=220) && (!Map.trLight)){
				x=x;
				}
				else{
				y-=speed;
				}
				//System.out.println("case "+myCase);
				break;
		}
		
		
		
		
	}


/*
	@Override
	public void run() {
		while(true){
				try {
					Thread.sleep(3000);
									if (trLight)
				{
					System.out.println("Green");
				trLight= false;
			}
			else{
				trLight=true;
				System.out.println("Red");
			}
				
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		
	}}*/

	}

