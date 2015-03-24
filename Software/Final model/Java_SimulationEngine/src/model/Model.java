package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
	
	private boolean initialized = false;
	
	public static final String STATUS_RUNNING = "running";
	public static final String STATUS_PAUSED = "paused";
	private static BufferedReader reader;
	private DataSimulator ds;
	
	private String mapTitle;
	
	public Model() {
		
	    ds = new DataSimulator();
	    ds.setGrid(null);
	    ds.setRunning(false);
	    
	    ds.addEventListener(this);
	    
	}
	
	public int getRound(){
		//_gc.getRound();
		return 0;
		
	}
	
	public boolean getInitialized(){
		return initialized;
	}
	
	public String getMapTitle(){
		String s = "";
		if(!initialized){
			s = "not loaded";
		}else{
			s = mapTitle;
		}
		return s;
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
		try(FileReader file = new FileReader(filename)) {	

			reader = new BufferedReader(file);
			String line = reader.readLine();
			
			String pattern = "(\\w+) (\\d+)x(\\d+)";
			String titleStr = line.replaceAll(pattern, "$1");
			String widthStr = line.replaceAll(pattern, "$2");
			int gWidth = Integer.parseInt(widthStr);
			String heightStr = line.replaceAll(pattern, "$3");
			int gHeight = Integer.parseInt(heightStr);
			
			System.out.println(titleStr+";"+widthStr+";"+heightStr);
			mapTitle = titleStr;
			
			rgrid = new Grid(gWidth, gHeight);
			
			boolean parseGrid = false;
			boolean parseTrafficLights = false;
			int row = 0, col = 0;
			
			while((line = reader.readLine()) != null){
				if(line.equals("===") && !parseGrid){
					parseGrid = true;
					
					continue;
				}else if(line.equals("===") && parseGrid){
					parseGrid = false;
					parseTrafficLights = true;
					
					continue;
				}
				//System.out.println(line);
				if(parseGrid){
					System.out.println(row);
					if(row >= gHeight){
						throw new GridException("Number of rows is "+gHeight+", but "+row+" found");
					}
					String[] spaces = line.trim().split("\\s+");
					if(spaces.length != gWidth){
						throw new GridException("Number of columns is "+gWidth+", but extra symbols found on line "+(row+1));
					}
					col = 0;
					for (String value : spaces){
						//System.out.println(col+"_"+row);
						
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
							//roundabout
							rgrid.setCellType(col, row, CellType.EMPTY);
							rgrid.setCellDirection(col, row, Direction.CIRCLE);
						}
						
						// stop here if no modifier
						if(value.length() == 1){
							col++;
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
						
						col++;
							
					}
					row++;
					
				}else if(parseTrafficLights){
					System.out.println("parse tl");
					pattern = "\\[(\\d+);(\\d+)\\] (\\d+) (\\w+)";
					String tlX = line.replaceAll(pattern, "$1");
					String tlY = line.replaceAll(pattern, "$2");
					//int gWidth = Integer.parseInt(widthStr);
					String tlDelay = line.replaceAll(pattern, "$3");
					String type = line.replaceAll(pattern, "$4");
					TrafficLightColour tlc = TrafficLightColour.GREEN;
					if(type.equals("RED")){
						tlc = TrafficLightColour.RED;
					}
					//int gHeight = Integer.parseInt(heightStr);
					System.out.println(tlX+"_"+tlY+"_"+tlDelay);
					try{
						ITrafficLight tl = new TrafficLight();
						tl.setDelay(Integer.parseInt(tlDelay));
						tl.setColour(tlc);
						rgrid.placeTrafficLightAt(Integer.parseInt(tlX), Integer.parseInt(tlY), tl);
					}catch(Exception e){
						throw new GridException("Could not parse traffic light: "+line);
					}
					
					
				}
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
		if(!initialized){
			return Model.STATUS_PAUSED;
		}
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

	public void loadFile(String file) {
		// TODO Auto-generated method stub
		File f = new File(file);
		if(f.exists() && !f.isDirectory()) { 
			try{
				this.grid = parseMap(file);	
				ds.setGrid(this.grid);

				_gc = new GridController(grid, this);
				this.initialized = true;
				
				System.out.println("model initialised");
				this.fireSimpleEvent(new SimpleEvent(this, SimpleEvent.MODEL_INIT));
			}catch(GridException ge){
				System.out.println("An error was encountered while reading from file. " + ge.getMessage()+".");
			}catch(Exception e){
				//System.out.println("");
			}
			
		}
	}

	public DataSimulator getDataSimulator() {
		return ds;
	}
	
}
