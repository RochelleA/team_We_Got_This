package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileFilter;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.KeyStroke;

import core.DataSimulator;
import core.TrafficLightColour;
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
	private ImageIcon icon_green;
	private ImageIcon icon_red;
	private JMenuItem loadMapAction;
	private JMenuItem loadDefaultMapAction;
	private JLabel titleLabel;
	private JLabel dataRoundLabel;
	private JLabel modelRoundLabel;
	
	private JMenuItem setTrafficLightsRed;
	private JMenuItem setTrafficLightsRedAmber;
	private JMenuItem setTrafficLightsGreen;
	private JMenuItem setTrafficLightsAmber;
	private JMenuItem stopTrafficLights;
	private JMenuItem startTrafficLights;
	private JMenuItem disableTrafficLights;
	
    public TestGrid01(Model model) {
    	
    	this.model = model;
    	this.ds = model.getDataSimulator();
    	
    	this.model.addEventListener(this); //listen for model updates
    	this.ds.addEventListener(this); //listen for data status change event
    	
        frame = new JFrame("«We-Got-This» Traffic Simulation Engine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.setSize(new Dimension(850,500));
        
        
        setUpMenu();
        
        tp = new GridPane(); //simulation grid 
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(tp);
        JViewport vp = scrollPane.getViewport();
        vp.setSize(tp.getWidth(), tp.getHeight());
                                
        frame.add(scrollPane);
        
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        
        icon_green = new ImageIcon("images/16_circle_green.png");
        icon_red = new ImageIcon("images/16_circle_red.png");
        
        modelStatusLabel = new JLabel("Model Status: x",icon_green,JLabel.LEFT);
        dataStatusLabel = new JLabel("Data Status: x",icon_green,JLabel.LEFT);
        
        titleLabel = new JLabel("Map");
        dataRoundLabel = new JLabel("Data Round");
        modelRoundLabel = new JLabel("Model Round");
        
        listPane.add(titleLabel);
        listPane.add(modelRoundLabel);
        listPane.add(dataRoundLabel);
        listPane.add(modelStatusLabel);
        listPane.add(dataStatusLabel);
        
        listPane.setPreferredSize(new Dimension(225,100));
        
        frame.add(listPane, BorderLayout.LINE_END);
        
        frame.setVisible(true);
        
        getCurrentData();

    }
    
    private void getCurrentData(){
    	System.out.println("get current data " + this.model.getInitialized());
    	setModelStatus(this.model.getStatus());
        setDataStatus(this.ds.getRunning());
        setTitle();
        
        setModelRound();
        setDataRound();
        
    }
    private void setTitle(){
    	titleLabel.setText("Map: "+model.getMapTitle());
    	
    }
    private void setDataRound(){
    	dataRoundLabel.setText("Data round: "+ds.getRound());
    }
    private void setModelRound(){
    	modelRoundLabel.setText("Model round: "+model.getRound());
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
        JMenu trafficLightsMenu = new JMenu("Traffic Lights");
        
        //Managing Buttons 
        //JButton startButton = new JButton();
        
    	 menuBar.add(fileMenu);
         menuBar.add(viewMenu);
         menuBar.add(controlMenu);
         menuBar.add(dataMenu);
         menuBar.add(trafficLightsMenu);
         
         // Create and add simple menu item to one of the drop down menu
         JMenuItem newAction = new JMenuItem("New");
         
         loadMapAction = new JMenuItem("Load Map");
         loadMapAction.addActionListener(new MenuActionListener());
         loadMapAction.setAccelerator(KeyStroke.getKeyStroke('l'));
         
         loadDefaultMapAction = new JMenuItem("Load Default");
         loadDefaultMapAction.addActionListener(new MenuActionListener());
         loadDefaultMapAction.setAccelerator(KeyStroke.getKeyStroke('L'));
         
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
         
         setTrafficLightsRed = new JMenuItem("Set All Red");
         setTrafficLightsRed.addActionListener(new MenuActionListener());
         setTrafficLightsRedAmber = new JMenuItem("Set All Red-Amber");
         setTrafficLightsRedAmber.addActionListener(new MenuActionListener());
         setTrafficLightsGreen = new JMenuItem("Set All Green");
         setTrafficLightsGreen.addActionListener(new MenuActionListener());
         setTrafficLightsAmber = new JMenuItem("Set All Amber");
         setTrafficLightsAmber.addActionListener(new MenuActionListener());
         
         stopTrafficLights = new JMenuItem("Stop");
         stopTrafficLights.addActionListener(new MenuActionListener());
         startTrafficLights = new JMenuItem("Stop");
         startTrafficLights.addActionListener(new MenuActionListener());
         disableTrafficLights = new JMenuItem("Disable");
         disableTrafficLights.addActionListener(new MenuActionListener());
         
         
         controlActionStart.addActionListener(new MenuActionListener());
         controlActionPause.addActionListener(new MenuActionListener());
         
         dataActionStart.addActionListener(new MenuActionListener());
         dataActionPause.addActionListener(new MenuActionListener());
         
         controlActionStart.setAccelerator(KeyStroke.getKeyStroke('s'));
         controlActionPause.setAccelerator(KeyStroke.getKeyStroke('p'));
         
         dataActionStart.setAccelerator(KeyStroke.getKeyStroke('S'));
         dataActionPause.setAccelerator(KeyStroke.getKeyStroke('P'));

       
         // Adding the respective menu with their respective Menu Items
         //fileMenu.add(newAction);
         fileMenu.add(loadMapAction);
         fileMenu.add(loadDefaultMapAction);
         
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
         
         trafficLightsMenu.add(setTrafficLightsRed);
         trafficLightsMenu.add(setTrafficLightsRedAmber);
         trafficLightsMenu.add(setTrafficLightsGreen);
         trafficLightsMenu.add(setTrafficLightsAmber);
         trafficLightsMenu.addSeparator();
         trafficLightsMenu.add(startTrafficLights);
         trafficLightsMenu.add(stopTrafficLights);
         trafficLightsMenu.addSeparator();
         trafficLightsMenu.add(disableTrafficLights);
         
    }
    
    private void setModelStatus(String status){
    	String s = "Controller status: ";
    	controlActionStart.setEnabled(false);
    	controlActionPause.setEnabled(false);
    	
    	if(status.equals(Model.STATUS_PAUSED)){
    		if(model.getInitialized()){
    			controlActionStart.setEnabled(true);
    		}
    		this.modelStatusLabel.setIcon(icon_red);
    		s += "Paused";
    	}else if(status.equals(Model.STATUS_RUNNING)){
    		controlActionPause.setEnabled(true);
    		this.modelStatusLabel.setIcon(icon_green);
    		s += "Running";
    	}
    	this.modelStatusLabel.setText(s);
    }
    
    private void setDataStatus(boolean status){
    	String s = "Data status: ";
    	if(status){
    		dataActionStart.setEnabled(false);
    		dataActionPause.setEnabled(true);
    		this.dataStatusLabel.setIcon(icon_green);
    		s += "Running";
    	}else{
    		dataActionStart.setEnabled(true);
    		dataActionPause.setEnabled(false);
    		this.dataStatusLabel.setIcon(icon_red);
    		s += "Paused";
    	}
    	
    	if(model.getStatus().equals(Model.STATUS_PAUSED)){
    		dataActionStart.setEnabled(false);
    		dataActionPause.setEnabled(false);
    	}
    	this.dataStatusLabel.setText(s);
    }
    

	@Override
	public void handleSimpleEvent(SimpleEvent e) {
		if(e instanceof DataEvent){
			DataEvent de = ((DataEvent)e);
			if(de.getType().equals(SimpleEvent.MODEL_INIT_ERROR)){
				JOptionPane.showMessageDialog(frame, de.getData(), "Map Parse Error", JOptionPane.ERROR_MESSAGE);
				System.out.println(de.getData());
				return;
			}
		}
		String type = e.getType();
		switch (type){
		case SimpleEvent.MODEL_STEP:
			tp.repaint();
			setDataRound();
			setModelRound();
			break;
		case SimpleEvent.MODEL_INIT:
			System.out.println("model initialized");
			tp.setGrid(model.getGrid());
			tp.repaint();
		case SimpleEvent.MODEL_STATUS_CHANGE:
			//System.out.println("model status change");
			//setModelStatus(model.getStatus());
			//break;
		case SimpleEvent.DATA_STATUS_CHANGE:
			//System.out.println("data status change");
			//setDataStatus(ds.getRunning());
			//break;
		case SimpleEvent.TRAFFIC_LIGHT_CHANGE:
			System.out.println("view traffic light change");
			//setDataStatus(ds.getRunning());
			//break;
		default:
			getCurrentData();
			
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
			}else if(source == loadMapAction){
				//In response to a button click:
				System.out.println("open file choose menu");
				final JFileChooser fc = new JFileChooser();
				MapFileFilter mff = new MapFileFilter();
				fc.addChoosableFileFilter(mff);
				fc.setFileFilter(mff);
				int returnVal = fc.showOpenDialog(frame);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					System.out.println(fc.getSelectedFile().getAbsolutePath());
					c.setMapFile(fc.getSelectedFile().getAbsolutePath());
				}
			}else if(source == loadDefaultMapAction){
				c.setMapFile("files/roundabout_new.txt");
			}else if(source == setTrafficLightsRed){
				c.setTrafficLightsColour(TrafficLightColour.RED);
				tp.repaint();
			}else if(source == setTrafficLightsRedAmber){
				c.setTrafficLightsColour(TrafficLightColour.RED_AMBER);
				tp.repaint();
			}else if(source == setTrafficLightsGreen){
				c.setTrafficLightsColour(TrafficLightColour.GREEN);
				tp.repaint();
			}else if(source == setTrafficLightsAmber){
				c.setTrafficLightsColour(TrafficLightColour.AMBER);
				tp.repaint();
			}else if(source == stopTrafficLights){
				c.stopAllTrafficLights();
				//tp.repaint();
			}else if(source == startTrafficLights){
				c.startAllTrafficLights();
			}else if(source == disableTrafficLights){
				c.disableTrafficLights();
				tp.repaint();
			}
			else{
				System.out.println("Unimplemented control");
			}
			
		}
	}
	
	public void setMVCController(MVCController mvcc){
		this.c = mvcc;
	}


}