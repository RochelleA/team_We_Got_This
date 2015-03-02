package view;
/**
 * 
 * @author Moinuddin Zaki
 * @version 1
 *
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import core.CellType;
import core.Direction;
import core.IGrid;
import events.ModelEvent;
import events.ModelEventListener;

public class TestGrid01 implements ModelEventListener {
	
	private IGrid nGrid;
	private int gHeight;
	private int gWidth;
	Image grassImg;
	Image roadImg;
	
	private GridPane tp;

    public TestGrid01(final IGrid grid) {
    	
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
                frame.setSize(450, 300);
                
                tp = new GridPane();
                tp.setGrid(grid);
                frame.add(tp);
                //tp.repaint();
       
                frame.setVisible(true);
            }
        });
    }
    

	@Override
	public void handleModelEvent(ModelEvent e) {
		// TODO Auto-generated method stub
		//UPDATE THE VISUAL REPRESENTATION!!! (repaint)
		System.out.println("update event!");
		tp.repaint();
		
	}

}