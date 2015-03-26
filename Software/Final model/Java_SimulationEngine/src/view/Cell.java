package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;


public class Cell extends JPanel {

	
	public enum CellType{
	    
		Roundabout,Empty,Road,Invalid;
	}
	public enum Direction{
	    
		North,East,South,West;
	}
	
	public Cell(int height, int width){
		
		System.out.println("The Height of each cell is "+height);
		System.out.println("The Width of each cell is "+width);
		//this.repaint();
		
		
	}
	
	public void paint(Graphics g){
		
		
		System.out.println("I am inside paint Component");
		
		g.drawRect(0,0,10, 10);
		g.setColor(Color.GRAY);
		
		
	}
	
	private CellType type;
	private Direction direction;
	private boolean isExit;
	Car car = null;
	//TrafficLight light1 = null;
	private int xPos;
	private int yPos;
	
	
}
