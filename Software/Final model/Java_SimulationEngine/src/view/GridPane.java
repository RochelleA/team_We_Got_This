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

import core.CellType;
import core.Direction;
import core.IGrid;
import core.TrafficLightColour;

/**
 * 
 * The main User Interface Class (View component of the system).
 * @author Moinuddin Zaki
 * @version 1
 *
 */

@SuppressWarnings("serial")
public class GridPane extends JComponent {
	
	
	Image grass = new ImageIcon("images/grass2-20.png").getImage();
	
	
	/** The road. */
	Image road = new ImageIcon("images/road20.png").getImage();
	Image roadEastSpaceLane = new ImageIcon("images/roadEastSpaceLane.png").getImage();
	Image roadEastLane = new ImageIcon("images/roadEastLane.png").getImage();
	Image roadWestSpaceLane = new ImageIcon("images/roadWestSpaceLane.png").getImage();
	Image roadNorthSpaceLane = new ImageIcon("images/roadNorthSpaceLane.png").getImage();
	Image roadSouthSpaceLane = new ImageIcon("images/roadSouthSpaceLane.png").getImage();
	Image roadNorthLane = new ImageIcon("images/roadNorthLane.png").getImage();
	Image car = new ImageIcon("images/redCar.jpg").getImage();
	Image carNorth = new ImageIcon("images/redCarNorth.jpg").getImage();

	private IGrid nGrid;
	
	private static int CELL_WIDTH = 10;
	private static int CELL_HEIGHT = 10;
	
    public GridPane(){
    
    };
    
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
					g.drawImage(grass, px, py, null);
    			
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
						if(getGrid().getCellDirection(x, y)==Direction.NORTH || getGrid().getCellDirection(x, y)==Direction.SOUTH)
						{
//	    						px=x*10;
//	            				py=y*10;
            				g.drawImage(carNorth, px, py, null);	
						}
    					else{
//	            				px=x*10;
//	        					py=y*10;
        					g.drawImage(car, px, py, null);
    					}
					}
					
    			}
				
				if(getGrid().hasTrafficLightAt(x, y)){
					System.out.println("grid has traffic light "+x+" "+y);
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
  
	public IGrid getGrid() {
		return nGrid;
	}
	public void setGrid(IGrid nGrid) {
		this.nGrid = nGrid;
		Dimension d = new Dimension(nGrid.getWidth()*CELL_WIDTH, nGrid.getHeight()*CELL_HEIGHT);
		this.setPreferredSize(d);
	}
    

}