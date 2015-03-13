package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Arrays;

import core.*;
import events.DataEvent;
import events.EventDispatchable;
import events.EventListener;
import events.NewCarEvent;
import events.SimpleEvent;

/**
 * @author NM
 * @version 1.2
 *
 */
public class Model extends EventDispatchable implements EventListener {
	private IGrid grid; 
	private IGridController _gc;
	
	
	public static String STATUS_RUNNING = "running";
	public static String STATUS_PAUSED = "paused";
	
	public Model() {
		//grid = new Grid(40, 25); //Grid(Col, Row)
		
	//	System.out.println("has car "+grid.hasCarAt(0, 10));

		//GetMap();
		this.grid = parseMap("files/roundabout.txt");
		
		_gc = new GridController(grid, this);
		
		this.startSim();
		
		//grid.placeCarAt(7, 10, new Car());
		//grid.placeCarAt(10, 10, new Car());
		//System.out.println("has car " + grid.hasCarAt(10, 10));
		
		//System.out.println(getGrid().toString());
		//System.out.println("finished");
	}
	
	
	
	/**
 	 * Reads a map from a txt file, and creates a corresponding grid.
 	 * @param filename the name of the map file to read
	 * @return IGrid the parsed grid.
 	 */
	public IGrid parseMap(String filename){
		//* - entry
		//! - exit
		String[] eastRoad = new String[] {"1","1*", "1!"};
		String[] westRoad = new String[] {"2","2*", "2!"};
		String[] northRoad = new String[] {"3","3*", "3!"};
		String[] southRoad = new String[] {"4","4*", "4!"};
		
		IGrid rgrid = null; //return grid 
		try {	
			int row=0;

			FileReader file = new FileReader(filename);
			LineNumberReader lnr = new LineNumberReader(file);
			lnr.skip(Long.MAX_VALUE);
			
			int height = lnr.getLineNumber() + 1;
						
			BufferedReader reader =new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			
			int width = line.trim().split("\\s+").length;
			
			//System.out.println(width +":"+ height);
			
			rgrid = new Grid(width, height);
			
			String []spaces;		
			while(line != null){
				spaces = line.trim().split("\\s+");
				if(spaces.length != width){
					throw new GridException("Inconsistent number of symbols on each line");
				}

				for (int col = 0; col<width; col++) {
					String value = (String)spaces[col];
					
					if(value.equals("0"))
					{
						rgrid.setCellType(col, row, CellType.EMPTY);
					}else if(Arrays.asList(eastRoad).contains(value))
					{
						//to east
						rgrid.setCellType(col, row, CellType.ROAD);
						rgrid.setCellDirection(col, row, Direction.EAST);
					}else if(Arrays.asList(westRoad).contains(value))
					{
						//to west
						rgrid.setCellType(col, row, CellType.ROAD);
						rgrid.setCellDirection(col, row, Direction.WEST);
					}else if(Arrays.asList(northRoad).contains(value))
					{
						//to north
						rgrid.setCellType(col, row, CellType.ROAD);
						rgrid.setCellDirection(col, row, Direction.NORTH);
					}else if(Arrays.asList(southRoad).contains(value))
					{
						//to south
						rgrid.setCellType(col, row, CellType.ROAD);
						rgrid.setCellDirection(col, row, Direction.SOUTH);
					}

					else if(value.equals("5"))
					{
						//junction
						rgrid.setCellType(col, row, CellType.ROAD);
						rgrid.setCellDirection(col, row, Direction.JUNCTION);
					}
					else if(value.equals("9") || value.equals("8"))
					{
						//junction
						rgrid.setCellType(col, row, CellType.ROAD);
						rgrid.setCellDirection(col, row, Direction.ROUNDABOUT);
					}
					
					else if(value.equals("7"))
					{
						//junction
						rgrid.setCellType(col, row, CellType.EMPTY);
						rgrid.setCellDirection(col, row, Direction.CIRCLE);
					}
					
					
					
					
					// stop here if no modifier
					if(value.length() == 1){
						continue;
					}
					
					String modifier = value.substring(value.length() - 1);
					
					if(modifier.equals("!")){
						System.err.println("! exit");
						rgrid.setIsExit(col, row, true);
					}else if(modifier.equals("*")){
						rgrid.setIsEntry(col, row, true);
						System.err.println("* entry");
					}
					
					///////////////////matrix[row][col] = Integer.parseInt(spaces[col]);

				}
				
				row++; 
				line = reader.readLine();				
			}
			
		}catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
		}
		
		return rgrid;
		
	}

	public IGrid getGrid() {
		return grid;
	}
	
	public void startSim(){
		this._gc.startTimer();
	}
	public void pauseSim(){
		this._gc.stopTimer();
	}
	public String getStatus(){
		return this._gc.getStatus();
	}
	
	@Override
	public void handleSimpleEvent(SimpleEvent e) {
		if (e instanceof DataEvent){
			System.out.println("true data event");
			
			DataEvent de = (DataEvent)e;
			
			if(de.getType() == DataEvent.NEW_CAR){
				//create new car
				NewCarEvent nce = (NewCarEvent)de;
				//System.out.println("new car x: " + nce.car.getX());
				try{
					_gc.addCar(nce.car);
					//System.out.println("added car");
				}catch(Exception ex){
					System.out.println("exception of adding a car");
				}
			}
		}
		
	}
	
}
