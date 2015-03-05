package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import core.*;
import events.EventDispatchable;

/**
 * @author NM
 *
 */
public class Model extends EventDispatchable {
	private IGrid grid; 
	
	public Model() {
		grid = new Grid(40, 25); //Grid(Col, Row)
		
	//	System.out.println("has car "+grid.hasCarAt(0, 10));

		//GetMap();
		parseMap("files/mapJunction.txt");
		
		GridController gc = new GridController(grid, this);
		gc.startTimer();
		
		//grid.placeCarAt(7, 10, new Car());
		//grid.placeCarAt(10, 10, new Car());
		//System.out.println("has car " + grid.hasCarAt(10, 10));
		
		//System.out.println(getGrid().toString());
		//System.out.println("finished");
	}
	
	
	
	/**
 	 * Gets the map.
 	 * reading map from the txt file, and storing it in grid
 	 * @param filename the name of the map file to read
 	 */
	public void parseMap(String filename){
		try {	
			int row=0;

			FileReader file = new FileReader(filename);
			@SuppressWarnings("resource")
			BufferedReader reader =new BufferedReader(file);

			String line = reader.readLine();
			String []spaces;		
			while(line != null){
				spaces = line.trim().split("\\s+");

				for (int col = 0; col <40; col++) {
					if(Integer.parseInt(spaces[col])==0 || Integer.parseInt(spaces[col])==9)
					{
						grid.setCellType(col, row, CellType.EMPTY);
					}
					if(Integer.parseInt(spaces[col])==1)
					{
						//to east
						grid.setCellType(col, row, CellType.ROAD);
						grid.setCellDirection(col, row, Direction.EAST);
					}
					if(Integer.parseInt(spaces[col])==2)
					{
						//to west
						grid.setCellType(col, row, CellType.ROAD);
						grid.setCellDirection(col, row, Direction.WEST);
					}
					if(Integer.parseInt(spaces[col])==3)
					{
						//to north
						grid.setCellType(col, row, CellType.ROAD);
						grid.setCellDirection(col, row, Direction.NORTH);
					}
					if(Integer.parseInt(spaces[col])==4)
					{
						//to south
						grid.setCellType(col, row, CellType.ROAD);
						grid.setCellDirection(col, row, Direction.SOUTH);
					}

					if(Integer.parseInt(spaces[col])==5)
					{
						//junction
						grid.setCellType(col, row, CellType.ROAD);
						grid.setCellDirection(col, row, Direction.JUNCTION);
					}
					///////////////////matrix[row][col] = Integer.parseInt(spaces[col]);

				}
				
				row++; 
				line = reader.readLine();				
			}

		}catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
		}
		
	}

	public IGrid getGrid() {
		return grid;
	}
	
}
