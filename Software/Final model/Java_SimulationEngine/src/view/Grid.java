package view;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;


public class Grid extends JPanel{
	
	 //private int[][] cells= new int [100][100];  // 2D array for cells in a grid
	 private Rectangle[] cells = new Rectangle[1];
	 private int width;
	 private int height;
	 
	 public Grid(int x, int y){
		 
		 Cell cell = new Cell(10,10);
		 cell.repaint();
		 
	 }
	 


}
