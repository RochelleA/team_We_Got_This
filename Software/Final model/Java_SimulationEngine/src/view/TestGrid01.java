package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
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
	private JFrame frame;
	private JLabel modelStatusLabel;
	private JLabel dataStatusLabel;
	
    public TestGrid01(Model model, DataSimulator ds) {
    	
    	this.model = model;
    	this.ds = ds;
    	
        frame = new JFrame("«We-Got-This» Traffic Simulation Engine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.setSize(new Dimension(750,500));
        
        
        setUpMenu();
        
        tp = new GridPane(); //simulation grid 
        tp.setGrid(model.getGrid());        
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(tp);
        JViewport vp = scrollPane.getViewport();
        System.out.println(vp.getSize());
        vp.setSize(tp.getWidth(), tp.getHeight());
        System.out.println(vp.getSize());
        
        System.out.println("rect:"+tp.getWidth() + ":"+tp.getHeight());
        
        //System.out.println("width: "+tp.getSize().width);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        scrollPane.setSize(200,200);
        
        frame.add(scrollPane);
        
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        
        modelStatusLabel = new JLabel("Model Status: x");
        dataStatusLabel = new JLabel("Data Status: x");
        
        listPane.add(modelStatusLabel);
        listPane.add(dataStatusLabel);
        
        frame.add(listPane, BorderLayout.LINE_END);
        //frame.add(tp);
        //frame.add(listPane);
        
        frame.setVisible(true);
        
        getCurrentData();

    }
    
    private void getCurrentData(){
    	setModelStatus(this.model.getStatus());
        setDataStatus(this.ds.getRunning());
    }
    
    private void setUpMenu(){
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
        //JButton startButton = new JButton();
        
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