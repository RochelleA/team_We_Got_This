package view;
/**
 * 
 * @author Moinuddin Zaki
 * @version 1
 *
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import core.CellType;
import core.IGrid;

public class TestGrid01 {
	
	private IGrid nGrid;
	private int gHeight;
	private int gWidth;

    public TestGrid01(IGrid grid) {
    	
    	//moving  Nur's Grid Information to my grid
    	nGrid=grid;
    	//get The height and width of the grid
    	gHeight=grid.getHeight();
    	gWidth=grid.getWidth();
    	
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }
                

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {
    	
    	/*Grid Column and Row Count which is used to as a parameter for
    	 * for deciding the height and width values of the cell.
    	 */
    	
        private int columnCount = 10;
        private int rowCount = 10;
        
        //Rectangular Cells
        //Now i have to draw the cells based on the 
        private List<Rectangle> cells;
        
        /*Used instead of a Car just to get a look and feel about how to have a control
         * of how the Cell works by just moving the mouse
         * 
         * 
         */
        private Point selectedCell;

        public TestPane() {
        	//put all the cells column*row into an ArrayList
        	cells = new ArrayList<>(gHeight * gWidth);
        	
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
                    

                    selectedCell = new Point(column, row);
                    repaint();

                }
            };
            addMouseMotionListener(mouseHandler);
        }

        @Override
        //Dimension of the Parent Component
        public Dimension getPreferredSize() {
            return new Dimension(600, 600);
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
                for (int row = 0; row < gWidth ; row++) {
                    for (int col = 0; col < gHeight; col++) {
                    	
                    	
                    	if(nGrid.getCellType(row, col)== CellType.EMPTY){
                    		Rectangle cell = new Rectangle(
                                    xOffset + (row * cellWidth),
                                    yOffset + (col * cellHeight),
                                    cellWidth,
                                    cellHeight);
                    		
                            cells.add(cell);
                    		
                    	}
                    	else if(nGrid.getCellType(row, col)== CellType.ROAD){
                    		
                    		Rectangle cell = new Rectangle(
                                    xOffset + (row * cellWidth),
                                    yOffset + (col * cellHeight),
                                    cellWidth,
                                    cellHeight);
                    		
                            cells.add(cell);
                    	}
                    	
                    	
                        
                    }
                }
            }

            if (selectedCell != null) {

                int index = selectedCell.x + (selectedCell.y * columnCount);
                Rectangle cell = cells.get(index);
                g2d.setColor(Color.BLUE);
                g2d.fill(cell);

            }

            g2d.setColor(Color.GRAY);
            for (Rectangle cell : cells) {
                g2d.draw(cell);
            }

            g2d.dispose();
        }
    }
}