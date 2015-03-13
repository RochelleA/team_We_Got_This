package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import core.CellType;
import core.Direction;
import core.IGrid;

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
	
    	
    	//public class Map extends JPanel implements ActionListener, Runnable{
    	/** The main timer. */
    	//Timer mainTimer = new Timer(30, this);
    	
    	/*Grid Column and Row Count which is used to as a parameter for
    	 * for deciding the height and width values of the cell.
    	 */
    	
      //  private int columnCount = 10;
       // private int rowCount = 10;
        
        //Rectangular Cells
        //Now i have to draw the cells based on the 
       // private List<Rectangle> cells;
        
        /*Used instead of a Car just to get a look and feel about how to have a control
         * of how the Cell works by just moving the mouse
         * 
         * 
         */
      //  private Point selectedCell;

        public GridPane() {
        	
        	
        	//mainTimer.start();
        	
        	
        	//put all the cells column*row into an ArrayList
        	//cells = new ArrayList<>(gHeight * gWidth);
        /*	
        	//Mouse Adapter class methods to get awareness of the kind of 
            MouseAdapter mouseHandler = new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    Point point = e.getPoint();

                    int width = getWidth();
                    int height = getHeight();

                    int cellWidth = width / gWidth;
                    int cellHeight = height /gHeight;

                    int column = e.getX() / cellWidth; 
                    int row = e.getY() / cellHeight;
                    

                    selectedCell = new Point(row,column);
                    repaint();

                }
            };
            addMouseMotionListener(mouseHandler);
        }

        @Override
        //Dimension of the Parent Component
        public Dimension getPreferredSize() {
            return new Dimension(1000, 1000);
        }
        //invalidate the cells not required but, just as a Good Coding practice.
        @Override
        public void invalidate() {
            cells.clear();
            selectedCell = null;
            super.invalidate();
        }

        @Override
        protected void paintComponent(Graphics g) {
        
        	super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            int cellCount=0;
            try{
            	//Is used to fetch the grass image
                File inGrass = new File("./files/grass2-20.png");
                grassImg = ImageIO.read(inGrass);
                
                //uSed to fetch the road image
                File inRoad = new File("./files/road20.png");
                roadImg = ImageIO.read(inRoad);
                }
                catch(IOException ie)
                {
                    System.out.println(ie.getMessage());
                }
            
            //to get the width and height of the component
            //25/4 not required.
           int width = getWidth();
           int height = getHeight();
            
            //based on the width and height of the outer Component
            
            int cellWidth = width / gWidth;
            int cellHeight = height / gHeight;
            
            // why did i write this i have to figure out.
            int xOffset = (width - (gWidth * cellWidth)) / 2;
            int yOffset = (height - (gHeight * cellHeight)) / 2;
            
            
            
            // Good coding practice just to clear if any previous traces remain in the junk
            if (cells.isEmpty()) {
                for (int row = 0; row < gHeight ; row++) {
                    for (int col = 0; col < gWidth; col++) {
                    	
                    	
        /*           	if(nGrid.getCellType(row, col)== CellType.EMPTY){
                    		Rectangle cell = new Rectangle(
                                    xOffset + (row * cellHeight),
                                    yOffset + (col * cellWidth),
                                    cellHeight,
                                    cellWidth);
                    		
                            cells.add(cell);
                            cellCount++;
                    		
                    	}
                    	else if(nGrid.getCellType(row, col)== CellType.ROAD){
                    		
                    		Rectangle cell = new Rectangle(
                    				 xOffset + (row * cellHeight),
                                     yOffset + (col * cellWidth),
                                     cellHeight,
                                     cellWidth);
                    		
                            cells.add(cell);
                            cellCount++;
                    	}
        	
        	
        	
                    	/////////////////////////////
        	
        	
        	
                     if(nGrid.getCellType(row, col)== CellType.EMPTY){
                    		
                    	 Rectangle cell = new Rectangle(
                				 xOffset + (row * cellHeight),
                                 yOffset + (col * cellWidth),
                                 cellHeight,
                                 cellWidth);
                     
                     		//g.drawImage(grassImg, row, col, xOffset + (row * cellHeight),  yOffset + (col * cellWidth),null);
                     		
                        //    cells.add(g.drawImage(grassImg, row, col, xOffset + (row * cellHeight),  yOffset + (col * cellWidth),null));
                            cellCount++;
                    		
                    	}
                     	else if(nGrid.getCellType(row, col)== CellType.ROAD){
                    		
                    		Rectangle cell = new Rectangle(
                    				 xOffset + (row * cellHeight),
                                     yOffset + (col * cellWidth),
                                     cellHeight,
                                     cellWidth);
                    		
                    		
                          //  cells.add(cell);
                            cellCount++;
                    	}
                    	
                    	
                    	System.out.println("The no of Column are: "+col);   
                    }
                    System.out.println("The no of rows are: "+row);
                    
                }
                System.out.println("The No of cells is: "+cellCount);
            }

            if (selectedCell != null) {

                int index = selectedCell.x + (selectedCell.y * columnCount);
                Rectangle cell = cells.get(index);
                g2d.setColor(Color.BLUE);
               // Shape sh = (Shape)cell;
                g2d.fill(cell);

            }

            g2d.setColor(Color.GRAY);
            for (Rectangle cell : cells) {
                g2d.draw(cell);
               // g2d.drawImage(cell,null,null);
            }

            g2d.dispose();
        }*/
        	
    }
        @Override
		public void paint (Graphics g) {
    		g=g;
    		
    		int px=0;
    		int py=0;
    		
    		for(int x=0; x<nGrid.getWidth(); x++)
    		{
				for(int y=0; y<nGrid.getHeight(); y++)
				{
					px=x*10;
					py=y*10;
						
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
    			}
    			
    		
    		}
        
        }
      
		public IGrid getGrid() {
			return nGrid;
		}
		public void setGrid(IGrid nGrid) {
			this.nGrid = nGrid;
		}
    

    }