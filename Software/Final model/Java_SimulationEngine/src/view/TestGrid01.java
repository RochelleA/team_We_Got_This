package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
//<<<<<<< HEAD
                frame.setSize(700, 500);
//=======
                frame.setSize(700, 500);
                JMenuBar menuBar = new JMenuBar();
                
                
                // Add the menubar to the frame
                frame.setJMenuBar(menuBar);
                
                // Define and add two drop down menu to the menubar
                JMenu fileMenu = new JMenu("File");
                JMenu viewMenu = new JMenu("View");
                JMenu controlMenu = new JMenu("Control");
                JMenu debugMenu = new JMenu("Debug");
                JMenu helpMenu = new JMenu("Help");
                
                //Managing Buttons 
                JButton startButton = new JButton();
           /*     try {
               	    Image img = ImageIO.read(getClass().getResource("Start.png"));
               	    startButton.setIcon(new ImageIcon(img)); 
               	  } catch (IOException ex) {
               	  }
                startButton.setLocation(250, 20);
                startButton.setSize(50,12);
                
                
                JButton stopButton = new JButton("Stop Flow");
                JButton restartButton = new JButton("Restart Flow");
                startButton.setBackground(Color.GREEN);
                stopButton.setBackground(Color.RED);
                restartButton.setBackground(Color.GREEN);
                
                
              Insets insets = contentPane.getInsets();
                Dimension size = startButton.getPreferredSize();
               startButton.setLocation(300,5);
               startButton.setSize(40,10);
                startButton.setBounds(25 + insets.left, 5 + insets.bottom,
                             size.width, size.height);
                size = stopButton.getPreferredSize();
                stopButton.setBounds(55 + insets.left, 40 + insets.left,
                             size.width, size.height);
                size = restartButton.getPreferredSize();
                restartButton.setBounds(150 + insets.left, 15 + insets.right,
                             size.width + 50, size.height + 20);
                
                
                contentPane.add(stopButton);
                contentPane.add(restartButton); */
                
                menuBar.add(fileMenu);
                menuBar.add(viewMenu);
                menuBar.add(controlMenu);
                menuBar.add(debugMenu);
                menuBar.add(helpMenu);
                
                // Create and add simple menu item to one of the drop down menu
                JMenuItem newAction = new JMenuItem("New");
                JMenuItem openAction = new JMenuItem("Open");
                JMenuItem exitAction = new JMenuItem("Exit");
                JMenuItem cutAction = new JMenuItem("Cut");
                JMenuItem copyAction = new JMenuItem("Copy");
                JMenuItem pasteAction = new JMenuItem("Paste");
                JMenuItem sizeAction = new JMenuItem("Adjust Size");
                JMenuItem browserAction = new JMenuItem("Open in Browser");
                JMenuItem controlAction = new JMenuItem("Control Flow");
                JMenuItem codeAction = new JMenuItem("Code Debug");
                JMenuItem helpAction = new JMenuItem("F1 for Help");
                
              
                // Adding the respective menu with their respective Menu Items
                fileMenu.add(newAction);
                fileMenu.add(openAction);
                fileMenu.add(cutAction);
                fileMenu.add(copyAction);
                fileMenu.add(pasteAction);
                fileMenu.add(exitAction);
                viewMenu.add(sizeAction);
                viewMenu.add(browserAction);
                controlMenu.add(controlAction);
                debugMenu.add(codeAction);
                helpMenu.add(helpAction);
///>>>>>>> c95b67bd2f7649ff2be32bff88cffb33ed470578
                
                tp = new GridPane();
                tp.setGrid(grid);                
                frame.add(tp);
              //    tp.add(frame);
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