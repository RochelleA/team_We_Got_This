/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import core.*;

/**
 * @author NM
 *
 */
public class Model {
	private IGrid grid; 
	
	public Model() {
		grid = new Grid(25,40);
		grid.setCellDirection(5, 5, Direction.NORTH);
		
		System.out.println("has car "+grid.hasCarAt(5, 5));

		GetMap();
		
		grid.placeCarAt(5, 5, new Car());
		System.out.println("has car "+grid.hasCarAt(5, 5));
		System.out.println(getGrid().toString());
		System.out.println("finished");
	}
	
	public void GetMap(){
try {	
			
			int row=0;
			int size=0;
		
			FileReader file = new FileReader("files/map.txt");
			@SuppressWarnings("resource")
			BufferedReader reader =new BufferedReader(file);
			
			
			String line = reader.readLine();
			String []spaces;		
			while(line != null)
			{
				spaces = line.trim().split("\\s+");
				
		        for (int col = 0; col <40; col++) {
		        	if(Integer.parseInt(spaces[col])==0 || Integer.parseInt(spaces[col])==9)
		        			{
		        	grid.setCellType(row, col, CellType.EMPTY);
		        			}
		        	if(Integer.parseInt(spaces[col])==1)
		        			{
		        		//to east
		        	grid.setCellType(row, col, CellType.ROAD);
		        	grid.setCellDirection(row, col, Direction.EAST);
		        			}
		        	if(Integer.parseInt(spaces[col])==2)
		        			{
		        		//to west
		        	grid.setCellType(row, col, CellType.ROAD);
		        	grid.setCellDirection(row, col, Direction.WEST);
		        			}
		        	if(Integer.parseInt(spaces[col])==3)
        			{
		        		//to north
		        		grid.setCellType(row, col, CellType.ROAD);
		        		grid.setCellDirection(row, col, Direction.NORTH);
        			}
		        	if(Integer.parseInt(spaces[col])==4)
        			{
		        		//to south
		        		grid.setCellType(row, col, CellType.ROAD);
		        		grid.setCellDirection(row, col, Direction.SOUTH);
        			}
		      
		            System.out.println(grid.getCellType(row, col));
		        	
		            ///////////////////matrix[row][col] = Integer.parseInt(spaces[col]);
		        
		        }
		
		        row++; 
				line = reader.readLine();				
			}
			
			
			
			} catch (IOException e) 
			{
		    System.err.println("Caught IOException: " + e.getMessage());
		    }
		
		
		
		
	}

	public IGrid getGrid() {
		
		
		return grid;
	}

}
