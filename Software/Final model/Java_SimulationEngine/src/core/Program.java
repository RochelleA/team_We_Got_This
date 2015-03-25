package core;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import events.DataEvent;
import events.DataPoint;
import events.EventListener;
import events.SimpleEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.stage.Stage;
import view.MVCController;
import view.StatGraph;
import view.TestGrid01;
import model.*;

public class Program extends Application implements EventListener {

	private static XYChart.Series seriesCar;
	private static Model model;

	/**
	 * Draw Line Chart for statistics.
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) {
		model.addEventListener(this);
		createStage(primaryStage);
	}
	
	private static void createStage(Stage stage) {
		// TODO Auto-generated method stub
		try {
			//BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			stage.setTitle("Traffic Statistics");
			final NumberAxis xAxis = new NumberAxis();
	        final NumberAxis yAxis = new NumberAxis();
	        int noOfCells;
	        int noOfRounds;
	        int timerInSec;
	        float ratioCellsByRounds;
			final LineChart<Number,Number> lineChart = 
	                new LineChart<Number,Number>(xAxis,yAxis);
			xAxis.setLabel("Time (Rounds)");
			yAxis.setLabel(" Y Axis");
			
			lineChart.setTitle("Traffic Statistics");
			int xvalue = 10;
			int yvalue = 20;
			Scene scene = new Scene(lineChart,400,400);
			
			 seriesCar = new XYChart.Series();
			 XYChart.Series seriesAvgCellsCrossed = new XYChart.Series();
		        seriesCar.setName("Volume of Cars");
		        seriesAvgCellsCrossed.setName("Number of Cells Crossed");
		        //populating the series with data
		        //Need to get the number of cars in the Number Format from the Nur's Code.
		     /*   for(int i=1;i<100000;i=i+10){
		        	for(int j=1;j<100000;j=j+5){
		        		
		        		seriesCar.getData().add(new XYChart.Data(i, j));
		        		seriesCellsCrossed.getData().add(new XYChart.Data(j,i));
		        	}
		        }*/

		        
		        
		       
		        XYChart.Data<Integer, Integer> point1 = new Data<Integer, Integer>((Integer)xvalue,(Integer)yvalue);
		        // instead of Point 1 we can a XY-data point that 
//		        seriesCar.getData().add(point1);
//		        seriesCar.getData().add(new XYChart.Data(1, 23));
//		        seriesCar.getData().add(new XYChart.Data(2, 14));
//		        seriesCar.getData().add(new XYChart.Data(3, 15));
//		        seriesCar.getData().add(new XYChart.Data(4, 24));
//		        seriesCar.getData().add(new XYChart.Data(5, 34));
//		        seriesCar.getData().add(new XYChart.Data(6, 36));
//		        seriesCar.getData().add(new XYChart.Data(7, 22));
//		        seriesCar.getData().add(new XYChart.Data(8, 45));
//		        seriesCar.getData().add(new XYChart.Data(9, 43));
//		        seriesCar.getData().add(new XYChart.Data(10, 17));
//		        seriesCar.getData().add(new XYChart.Data(11, 29));
//		        seriesCar.getData().add(new XYChart.Data(12, 25));
		        
//		        seriesCellsCrossed.getData().add(new XYChart.Data(1,45));
//		        seriesCellsCrossed.getData().add(new XYChart.Data(2,10));
//		        seriesCellsCrossed.getData().add(new XYChart.Data(3,22));
//		        seriesCellsCrossed.getData().add(new XYChart.Data(4,20));
//		        seriesCellsCrossed.getData().add(new XYChart.Data(5,35));
//		        seriesCellsCrossed.getData().add(new XYChart.Data(6,24));
//		        seriesCellsCrossed.getData().add(new XYChart.Data(7,38));
//		        seriesCellsCrossed.getData().add(new XYChart.Data(8,15));
//		        seriesCellsCrossed.getData().add(new XYChart.Data(9,33));
		        	
		        
		        lineChart.getData().add(seriesCar);
		       lineChart.getData().add(seriesAvgCellsCrossed);
		       
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		        
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void addData(int x, int y){
		seriesCar.getData().add(new XYChart.Data(x, y));
	}
	
	public static void main(String[] args) {
		model = new Model();
		
		//System.out.println(model.getGrid().getWidth() + " "+model.getGrid().getHeight());

	    TestGrid01 tg = new TestGrid01(model);
	    
	    //ds.addEventListener(model);
	    //ds.addEventListener(tg); //fire status change events
	    
	    MVCController c = new MVCController(model);
	    
	    tg.setMVCController(c);
	    
	    Application.launch(args);
	    //c.showGraph();
	    
		//View view = new View();
	}

	@Override
	public void handleSimpleEvent(SimpleEvent e) {
		if(e.getType().equals(SimpleEvent.MODEL_NEW_DATA)){
			
			DataPoint dp = (DataPoint)((DataEvent)e).getData();
			System.out.println("data event " + dp.getX() + " "+dp.getY());
			this.addData(dp.getX(), dp.getY());
		}else if(e.getType().equals(SimpleEvent.MODEL_INIT)){
			System.out.println("model init");
			 seriesCar.getData().clear();
		}
	}

}
