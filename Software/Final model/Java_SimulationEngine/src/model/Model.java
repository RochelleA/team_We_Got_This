package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import core.*;
import events.DataEvent;
import events.EventDispatchable;
import events.EventListener;
import events.NewCarEvent;
import events.SimpleEvent;

/**
 * 
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
	private boolean trafficLightsDisabled;
	
	public Model() {
		
	    ds = new DataSimulator();
	    ds.setGrid(null);
	    ds.setRunning(false);
	    
	    ds.addEventListener(this);
	    
	}
	/**
	 *  Method returns number of rounds (int Value) 
	 */
	
	public int getRound(){
		if(_gc != null)
			return _gc.getRound();
		return 0;
	}
	
	/**
	 *  Method returns true if model is initialized
	 */
	public boolean getInitialized(){
		return initialized;
	}
	
	/**
	 *  Method returns the title of the Map 
	 */
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
		IGrid rgrid = null; //return grid 
		try(FileReader file = new FileReader(filename)) {	

			reader = new BufferedReader(file);
			rgrid = parseBufferReader(reader);
			reader.close();
			
		}catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
		}
		
		return rgrid;
		
	}
	
	private IGrid parseBufferReader(BufferedReader reader) throws IOException{
		//* - entry
		//! - exit
		String[] eastRoad = new String[] {"1","1*", "1!"};
		String[] westRoad = new String[] {"2","2*", "2!"};
		String[] northRoad = new String[] {"3","3*", "3!"};
		String[] southRoad = new String[] {"4","4*", "4!"};
		
		IGrid rgrid = null; //return grid 
		
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
				//System.out.println(row);
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
					{					// **********************************************************************

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
						//System.err.println("! exit");
						rgrid.setIsExit(col, row, true);
					}else if(modifier.equals("*")){
						rgrid.setIsEntry(col, row, true);
						//System.err.println("* entry");
					}
					
					col++;
						
				}
				row++;
				
			}else if(parseTrafficLights){
				if(row < gHeight){
					throw new GridException("The number of lines is less than specified: expected "+gHeight+", but read "+row);
				}
				//System.out.println("parse tl");
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
		
		return rgrid;
	}

	/**
	 *  Method returns the Grid 
	 */
	public IGrid getGrid() {
		return grid;
	}
	
	/**
	 * Method used to start the timer when the Simulation is Started
	 */
	public void startSim(){
		this._gc.startTimer();
	}
	/**
	 * Method used to pause the timer when the Simulation is Stopped
	 */
	public void pauseSim(){
		this._gc.stopTimer();
	}
	/**
	 * Method retuens the Status of the model if its paused
	 */
	public String getStatus(){
		if(!initialized){
			return Model.STATUS_PAUSED;
		}
		return this._gc.getStatus();
	}
	
	/**
     *  Method used to Update the Model from the View whenever a new Vehicle is added on the View
     */
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
	/**
     *	Method used to load the file which has the map containing the Grid of cells when
     *  is successfully loaded fires the event to Initialize the Model.
     */
	public void loadFile(String file) {
		// TODO Auto-generated method stub
		File f = new File(file);
		if(f.exists() && !f.isDirectory()) { 
			try{
				this.grid = parseMap(file);	
				ds.setGrid(this.grid);

				_gc = new GridController(grid, this);
				this.initialized = true;
				
				//System.out.println("model initialised");
				this.fireSimpleEvent(new SimpleEvent(this, SimpleEvent.MODEL_INIT));
			}catch(GridException ge){
				//System.out.println("An error was encountered while reading from file. " + ge.getMessage()+".");
				this.fireSimpleEvent(new DataEvent(this, SimpleEvent.MODEL_INIT_ERROR,"An error was encountered while reading from file.\n" + ge.getMessage()+"."));
			}catch(Exception e){
				//System.out.println("");
			}
			
		}
	}
	
	public void loadDefaultFile() {
		InputStream is = this.getClass().getResourceAsStream("/roundabout_new.txt");
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
			try{
				this.grid = parseBufferReader(br);	
				ds.setGrid(this.grid);

				_gc = new GridController(grid, this);
				this.initialized = true;
				
				//System.out.println("model initialised");
				this.fireSimpleEvent(new SimpleEvent(this, SimpleEvent.MODEL_INIT));
			}catch(GridException ge){
				//System.out.println("An error was encountered while reading from file. " + ge.getMessage()+".");
				this.fireSimpleEvent(new DataEvent(this, SimpleEvent.MODEL_INIT_ERROR,"An error was encountered while reading from file.\n" + ge.getMessage()+"."));
			}catch(Exception e){
				//System.out.println("");
			}
		try {
			br.close();
			isr.close();
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @return A DataSimulator Object
	 */
	public DataSimulator getDataSimulator() {
		return ds;
	}
	
	/**
	 *  If the Model is Initialized then the Traffic Light Colors are set.
	 *  
	 */
	public void setTrafficLightsColour(TrafficLightColour c) {
		if(!initialized){
			return;
		}
		for (ITrafficLight tl : this._gc.getTrafficLights()){
			tl.setColour(c);
		}
		if(!c.equals(TrafficLightColour.DISABLED) && getTrafficLightsDisabled())
			this.trafficLightsDisabled = false;
			this.fireSimpleEvent(new SimpleEvent(this,SimpleEvent.MODEL_STATUS_CHANGE));
	}
	/**
	 *  If the Model is Initialized method is called to Stop Traffic Lights
	 *  
	 */
	public void stopTrafficLights() {
		if(!initialized){
			return;
		}
		for (ITrafficLight tl : this._gc.getTrafficLights()){
			tl.stop();
		}
	}
	/**
	 *  If the Model is Initialized method is used to start the Traffic Lights
	 *  
	 */
	public void startTrafficLights() {
		if(!initialized){
			return;
		}
		for (ITrafficLight tl : this._gc.getTrafficLights()){
			tl.start();
		}
	}

	/**
	 *  If the Model is Initialized method is used to Disable Traffic Lights
	 */
	public void disableTrafficLights() {
		if(!initialized){
			return;
		}
		this.trafficLightsDisabled = true;
		
		for (ITrafficLight tl : this._gc.getTrafficLights()){
			tl.setColour(TrafficLightColour.DISABLED);
		}
		this.fireSimpleEvent(new SimpleEvent(this,SimpleEvent.MODEL_STATUS_CHANGE));
		
	}
	/**
	 * Returns the status of the traffic lights.
	 * @return true if traffic lights are disabled and false otherwise.
	 */
	public boolean getTrafficLightsDisabled(){
		if(!initialized){
			return true;
		}
		return this.trafficLightsDisabled;
	}
	
}
