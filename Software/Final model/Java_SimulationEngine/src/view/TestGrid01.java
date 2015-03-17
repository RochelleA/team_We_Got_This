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
import javax.swing.KeyStroke;

import core.DataSimulator;
import model.Model;
import events.EventListener;
import events.SimpleEvent;
import events.DataEvent;

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
	private Model model;
	private DataSimulator ds;
	
	private JMenuItem controlActionStart;
	private JMenuItem controlActionPause;
	private JMenuItem dataActionStart;
	private JMenuItem dataActionPause;
	
    public TestGrid01(Model model, DataSimulator ds) {
    	
    	this.model = model;
    	this.ds = ds;
    	
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
        JMenu dataMenu = new JMenu("Data");
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
        menuBar.add(dataMenu);
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
       
        controlActionStart = new JMenuItem("Start");
        controlActionPause = new JMenuItem("Pause");
        
        dataActionStart = new JMenuItem("Start");
        dataActionPause = new JMenuItem("Pause");
        
        JMenuItem helpAction = new JMenuItem("F1 for Help");
        
        controlActionStart.addActionListener(new MenuActionListener());
        controlActionPause.addActionListener(new MenuActionListener());
        
        dataActionStart.addActionListener(new MenuActionListener());
        dataActionPause.addActionListener(new MenuActionListener());
        
        controlActionStart.setAccelerator(KeyStroke.getKeyStroke('s'));
        controlActionPause.setAccelerator(KeyStroke.getKeyStroke('p'));
        
        dataActionStart.setAccelerator(KeyStroke.getKeyStroke('S'));
        dataActionPause.setAccelerator(KeyStroke.getKeyStroke('P'));

      
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
        dataMenu.add(dataActionStart);
        dataMenu.add(dataActionPause);
        helpMenu.add(helpAction);
        
        tp = new GridPane();
        tp.setGrid(model.getGrid());                
        frame.add(tp);
   
        frame.setVisible(true);
        
        setModelStatus(this.model.getStatus());
        setDataStatus(this.ds.getRunning());

    }
    
    private void setModelStatus(String status){
    	if(status.equals(Model.STATUS_PAUSED)){
    		controlActionStart.setEnabled(true);
    		controlActionPause.setEnabled(false);
    	}else if(status.equals(Model.STATUS_RUNNING)){
    		controlActionStart.setEnabled(false);
    		controlActionPause.setEnabled(true);
    	}
    }
    
    private void setDataStatus(boolean status){
    	if(status){
    		dataActionStart.setEnabled(false);
    		dataActionPause.setEnabled(true);
    	}else{
    		dataActionStart.setEnabled(true);
    		dataActionPause.setEnabled(false);
    	}
    }
    

	@Override
	public void handleSimpleEvent(SimpleEvent e) {
		String type = e.getType();
		switch (type){
		case SimpleEvent.MODEL_STEP:
			tp.repaint();
			break;
		case SimpleEvent.MODEL_STATUS_CHANGE:
			System.out.println("model status change");
			setModelStatus(model.getStatus());
			break;
		case SimpleEvent.DATA_STATUS_CHANGE:
			System.out.println("data status change");
			setDataStatus(ds.getRunning());
			break;
		}
	}
	
	
	class MenuActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent action) {
			JMenuItem source = (JMenuItem)action.getSource();
			if (source == controlActionStart){
				System.out.println("Control Start");
				c.start();
			}else if(source == controlActionPause){
				System.out.println("Control Pause");
				c.pause();
			}else if(source == dataActionStart){
				c.dataStart();
				//System.out.println("Data Start");
			}else if(source == dataActionPause){
				c.dataPause();
				//System.out.println("Data Pause");
			}else{
				System.out.println("Unimplemented control");
			}
			
		}
	}
	
	public void setMVCController(MVCController mvcc){
		this.c = mvcc;
	}


}