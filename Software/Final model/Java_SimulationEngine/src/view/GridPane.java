package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import core.CarsColour;
import core.CellType;
import core.Direction;
import core.IGrid;

/**
 * 
 * The main User Interface Class and the View component of the system.
 * Used to design the Layout of the cells on the Grid and setting the Dimensions
 * @author Moinuddin Zaki
 * @version 1
 *
 */

@SuppressWarnings("serial")
public class GridPane extends JComponent {
	
	
	Image grass = new ImageIcon(getClass().getResource("/grass2-20.png")).getImage();
	
	
	/** The road. */
	Image road = new ImageIcon(getClass().getResource("/road20.png")).getImage();
	Image roadEastSpaceLane = new ImageIcon(getClass().getResource("/roadEastSpaceLane.png")).getImage();
	Image roadEastLane = new ImageIcon(getClass().getResource("/roadEastLane.png")).getImage();
	Image roadWestSpaceLane = new ImageIcon(getClass().getResource("/roadWestSpaceLane.png")).getImage();
	Image roadNorthSpaceLane = new ImageIcon(getClass().getResource("/roadNorthSpaceLane.png")).getImage();
	Image roadSouthSpaceLane = new ImageIcon(getClass().getResource("/roadSouthSpaceLane.png")).getImage();
	Image roadNorthLane = new ImageIcon(getClass().getResource("/roadNorthLane.png")).getImage();
	
	Image carRed = new ImageIcon(getClass().getResource("/redCar.jpg")).getImage();
	Image carNorthRed = new ImageIcon(getClass().getResource("/redCarNorth.jpg")).getImage();
	Image carNorthEastRed = new ImageIcon(getClass().getResource("/northEast.png")).getImage();
	Image carEastSouthRed = new ImageIcon(getClass().getResource("/eastSouth.png")).getImage();
	
	Image carBlue = new ImageIcon(getClass().getResource("/blueCar.jpg")).getImage();
	Image carNorthBlue = new ImageIcon(getClass().getResource("/blueCarNorth.jpg")).getImage();
	Image carNorthEastBlue = new ImageIcon(getClass().getResource("/northEastBlue.png")).getImage();
	Image carEastSouthBlue = new ImageIcon(getClass().getResource("/eastSouthBlue.png")).getImage();
	
	Image roadNorthEast = new ImageIcon(getClass().getResource("/roadNorthEast.png")).getImage();
	Image roadEastSouth = new ImageIcon(getClass().getResource("/roadEastSouth.png")).getImage();
	Image roadSouthWest = new ImageIcon(getClass().getResource("/roadSouthWest.png")).getImage();
	Image roadWestNorth = new ImageIcon(getClass().getResource("/roadWestNorth.png")).getImage();
	
	private IGrid nGrid;
	
	private static int CELL_WIDTH = 10;
	private static int CELL_HEIGHT = 10;
	
    public GridPane(){
    
    };
    
    /**
     * Overrides the PaintComponent of JComponent
     * Used to paint the Cells on the Grid
     */
    @Override
	public void paintComponent (Graphics g) {
		
    	if(nGrid == null){
    		return;
    	}
    	
		int px=0;
		int py=0;
		
		for(int x=0; x<nGrid.getWidth(); x++)
		{
			for(int y=0; y<nGrid.getHeight(); y++)
			{
				px=x*CELL_WIDTH;
				py=y*CELL_HEIGHT;
					
				if(getGrid().getCellType(x, y)==CellType.EMPTY){
					
					if((x<nGrid.getWidth()-1 && x>1) &&(y<nGrid.getHeight()-1 && y>1)){
						
						
					 if ((getGrid().getCellDirection(x+1, y)==Direction.ROUNDABOUT) && ((getGrid().getCellType(x, y-1)==CellType.EMPTY) && (getGrid().getCellDirection(x, y-1)!=Direction.CIRCLE)) ){
						g.drawImage(roadNorthEast, px, py, null);
						}
					 
					 else if ((getGrid().getCellDirection(x-1, y)==Direction.ROUNDABOUT) && ((getGrid().getCellType(x, y-1)==CellType.EMPTY) && (getGrid().getCellDirection(x, y-1)!=Direction.CIRCLE)) ){
							g.drawImage(roadEastSouth, px, py, null);
							}
					 
					 else if ((getGrid().getCellDirection(x-1, y)==Direction.ROUNDABOUT) && ((getGrid().getCellType(x, y+1)==CellType.EMPTY) && (getGrid().getCellDirection(x, y+1)!=Direction.CIRCLE)) ){
							g.drawImage(roadSouthWest, px, py, null);
							}
					 else if ((getGrid().getCellDirection(x+1, y)==Direction.ROUNDABOUT) && ((getGrid().getCellType(x, y+1)==CellType.EMPTY) && (getGrid().getCellDirection(x, y+1)!=Direction.CIRCLE)) ){
							g.drawImage(roadWestNorth, px, py, null);
							}
					 
					 else{
							g.drawImage(grass, px, py, null);
							}
					
					
					}
					else{
					g.drawImage(grass, px, py, null);
					}
    			
				}else if(getGrid().getCellType(x, y)==CellType.ROAD){
					
					if((getGrid().getCellDirection(x, y)==Direction.EAST) && (getGrid().getCellType(x, y-1)==CellType.EMPTY))
					{
						g.drawImage(roadEastSpaceLane, px, py, null);
					}
					else if((getGrid().getCellDirection(x, y)==Direction.EAST) && (getGrid().getCellType(x, y+1)==CellType.ROAD) && (getGrid().getCellType(x, y-1)!=CellType.EMPTY) )
					{
						g.drawImage(roadEastLane, px, py, null);
					}
					
					
					
					/*else if((getGrid().getCellDirection(x, y)==Direction.WEST) && (getGrid().getCellType(x, y+1)==CellType.EMPTY))
					{
						g.drawImage(roadWestSpaceLane, px, py, null);
					}
					
					*/
					
					else if((getGrid().getCellDirection(x, y)==Direction.WEST) && (getGrid().getCellType(x, y+1)==CellType.ROAD))
					{
						g.drawImage(roadEastSpaceLane, px, py, null);
					}
					
					else if((getGrid().getCellDirection(x, y)==Direction.NORTH) && (getGrid().getCellType(x-1, y)==CellType.EMPTY))
					{
						g.drawImage(roadNorthSpaceLane, px, py, null);
					}
					
					else if((getGrid().getCellDirection(x, y)==Direction.NORTH) && (getGrid().getCellType(x+1, y)==CellType.ROAD) && (getGrid().getCellType(x-1, y)!=CellType.EMPTY) )
					{
						g.drawImage(roadNorthLane, px, py, null);
					}
					else if((getGrid().getCellDirection(x, y)==Direction.SOUTH) && (getGrid().getCellType(x+1, y)==CellType.EMPTY))
					{
						g.drawImage(roadSouthSpaceLane, px, py, null);
					}
					
					else{
						g.drawImage(road, px, py, null);
						
					}
					if(getGrid().hasCarAt(x, y)){
						if(getGrid().getCarAt(x, y).getcarsDir()==Direction.EAST || getGrid

().getCarAt(x, y).getcarsDir()==Direction.WEST)
						{
							if(getGrid().getCarAt(x, y).getCarsColour()==CarsColour.RED){
							g.drawImage(carRed, px, py, null);}
							
							else if(getGrid().getCarAt(x, y).getCarsColour

()==CarsColour.BLUE){
								g.drawImage(carBlue, px, py, null);}
						}
						else if(getGrid().getCarAt(x, y).getcarsDir()==Direction.NORTH || getGrid

().getCarAt(x, y).getcarsDir()==Direction.SOUTH)
						{
							if(getGrid().getCarAt(x, y).getCarsColour()==CarsColour.RED){
							g.drawImage(carNorthRed, px, py, null);}
							else if(getGrid().getCarAt(x, y).getCarsColour

()==CarsColour.BLUE){
								g.drawImage(carNorthBlue, px, py, null);
							}
						}
						else if(getGrid().getCarAt(x, y).getcarsDir()==Direction.eastSouth || 

getGrid().getCarAt(x, y).getcarsDir()==Direction.westNorth)
						{
							if(getGrid().getCarAt(x, y).getCarsColour()==CarsColour.RED){
							g.drawImage(carEastSouthRed, px, py, null);}
							else if(getGrid().getCarAt(x, y).getCarsColour

()==CarsColour.BLUE){
								g.drawImage(carEastSouthBlue, px, py, null);
							}
						}
						else if(getGrid().getCarAt(x, y).getcarsDir()==Direction.northEast || 

getGrid().getCarAt(x, y).getcarsDir()==Direction.southWest)
						{
							if(getGrid().getCarAt(x, y).getCarsColour()==CarsColour.RED){
							g.drawImage(carNorthEastRed, px, py, null);}
							else if(getGrid().getCarAt(x, y).getCarsColour

()==CarsColour.BLUE){
								g.drawImage(carNorthEastBlue, px, py, null);
							}
						}
						

					}
					
    			}
				
				if(getGrid().hasTrafficLightAt(x, y)){
					Color c = new Color(255, 255, 255);
					Graphics2D g2 = (Graphics2D) g;
					
					boolean drawSemiCircle = false;
					switch(getGrid().getTrafficLightAt(x, y).getColour()){
						case RED:
							c = new Color(255, 0, 0);
							break;
						case AMBER:
							c = new Color(255, 255, 0);
							break;
						case GREEN:
							c = new Color(102, 204, 0);
							break;
						case RED_AMBER:
							drawSemiCircle = true;
							c = new Color(255, 0, 255); 
						case DISABLED:
							c = new Color(10, 10, 10); 
					};
					if(drawSemiCircle){
						Arc2D leftArc = new Arc2D.Double(px, py, 5, 5, 90, 180, Arc2D.OPEN);
						Arc2D rightArc = new Arc2D.Double(px, py, 5, 5, 90, -180, Arc2D.OPEN);
						g2.setPaint(new Color(255, 255, 0));
						g2.fill(leftArc);
						g2.draw(leftArc);
						
						g2.setPaint(new Color(255, 0, 0));
						g2.fill(rightArc);
						g2.draw(rightArc);
					}else{
						
						Ellipse2D ellipse = new Ellipse2D.Double(px, py, 5, 5);
						
						g2.setPaint(c);
						g2.fill(ellipse);
						g2.draw(ellipse );
					}
				}
				
			}
			
		
		}
    }
    
    /**
     * @return The Grid Object
     */
	public IGrid getGrid() {
		return nGrid;
	}
	/**
	 * Function used to assign the Dimensions and set the Width and Height of the Grid 
	 * @param nGrid
	 */
	public void setGrid(IGrid nGrid) {
		this.nGrid = nGrid;
		Dimension d = new Dimension(nGrid.getWidth()*CELL_WIDTH, nGrid.getHeight()*CELL_HEIGHT);
		this.setPreferredSize(d);
	}
    

}