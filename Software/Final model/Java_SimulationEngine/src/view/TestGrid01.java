package view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.Model;

import events.EventListener;
import events.SimpleEvent;

/**
 * 
 * The main User Interface Class (View component of the system).
 * @author Moinuddin Zaki
 * @version 1.2
 *
 */

public class TestGrid01 implements EventListener {

	Image grassImg;
	Image roadImg;
	
	private GridPane tp;
	private MVCController c;
	
    public TestGrid01(Model model) {
    	
        JFrame frame = new JFrame("«We-Got-This» Traffic Simulation Engine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

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
        JMenuItem controlActionStart = new JMenuItem("Start");
        JMenuItem controlActionPause = new JMenuItem("Pause");
        JMenuItem codeAction = new JMenuItem("Code Debug");
        JMenuItem helpAction = new JMenuItem("F1 for Help");
        
        controlActionStart.addActionListener(new MenuActionListener());
        controlActionPause.addActionListener(new MenuActionListener());
      
        // Adding the respective menu with their respective Menu Items
        fileMenu.add(newAction);
        fileMenu.add(openAction);
        fileMenu.add(cutAction);
        fileMenu.add(copyAction);
        fileMenu.add(pasteAction);
        fileMenu.add(exitAction);
        viewMenu.add(sizeAction);
        viewMenu.add(browserAction);
        controlMenu.add(controlActionStart);
        controlMenu.add(controlActionPause);
        debugMenu.add(codeAction);
        helpMenu.add(helpAction);
        
        tp = new GridPane();
        tp.setGrid(model.getGrid());                
        frame.add(tp);
   
        frame.setVisible(true);

    }
    

	@Override
	public void handleSimpleEvent(SimpleEvent e) {
		if(e.getType().equals(SimpleEvent.MODEL_STEP)){
			tp.repaint();
		}else if(e.getType().equals(SimpleEvent.MODEL_STATUS_CHANGE)){
			System.out.println("model status change");
		}
		
	}
	
	
	class MenuActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent action) {
			switch (action.getActionCommand()){
				case "Start":
					System.out.println("Start");
					c.start();
					break;
				case "Pause":
					System.out.println("Pause");
					c.pause();
					break;
				default:
					//c.pause();
					System.out.println("Unimplemented control");
			}
			
		}
	}
	
	public void setMVCController(MVCController mvcc){
		this.c = mvcc;
	}


}