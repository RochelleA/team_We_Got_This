package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import core.IGrid;
import events.SimulationEvent;
import events.EventListener;

/**
 * 
 * The main User Interface Class (View component of the system).
 * @author Moinuddin Zaki
 * @version 1
 *
 */

public class TestGrid01 implements EventListener {

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
                frame.setSize(700, 300);
                
                tp = new GridPane();
                tp.setGrid(grid);
                frame.add(tp);
                //tp.repaint();
       
                frame.setVisible(true);
            }
        });
    }
    

	@Override
	public void handleSimulationEvent(SimulationEvent e) {
		// TODO Auto-generated method stub
		//UPDATE THE VISUAL REPRESENTATION!!! (repaint)
		//System.out.println("update event!");
		tp.repaint();
		
	}

}