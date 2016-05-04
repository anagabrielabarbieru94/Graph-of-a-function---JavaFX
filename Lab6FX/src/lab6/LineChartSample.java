/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;
/**
 *
 * @author AnaGabriela Barbieru
 */
import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
 
public class LineChartSample extends Application {
 
    @Override public void start(Stage stage) {
        stage.setTitle("Line Chart Sample");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis("Ox", -20, 20, 2);
        final NumberAxis yAxis = new NumberAxis("Oy", -20, 50, 2);
        xAxis.setSide(Side.BOTTOM);
        xAxis.setSide(Side.LEFT);
        //xAxis.setLabel("Abscisa");
        //yAxis.setLabel("Ordonata");
        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Graficul functiei");
        //defining a series
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("f(x)");
        for(int i=-10; i<=10; i++){
            int x = i;
            int y = x*x+2;
            series1.getData().add(new XYChart.Data(x, y));
        }
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("f(x)");
        //populating the series with data
        /*series1.getData().add(new XYChart.Data(-11, -12));
        series1.getData().add(new XYChart.Data(-8, -4));
        series1.getData().add(new XYChart.Data(3, 15));
        series1.getData().add(new XYChart.Data(4, 24));
        series1.getData().add(new XYChart.Data(5, 34));
        series1.getData().add(new XYChart.Data(6, 36));
        series1.getData().add(new XYChart.Data(7, 22));
        series1.getData().add(new XYChart.Data(8, 45));
        series1.getData().add(new XYChart.Data(9, 43));
        series1.getData().add(new XYChart.Data(10, 17));
        series1.getData().add(new XYChart.Data(11, 29));
        series1.getData().add(new XYChart.Data(12, 25));
        */
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series1);
        
        /*XYChart.Series series2 = new XYChart.Series();
        series2.setName("My portfolio");
        series2.getData().add(new XYChart.Data(-1, 2));
        series2.getData().add(new XYChart.Data(2, 4));
        series2.getData().add(new XYChart.Data(3, 8));
        series2.getData().add(new XYChart.Data(4, 21));
        series2.getData().add(new XYChart.Data(5, 9));
        series2.getData().add(new XYChart.Data(6, 31));
        series2.getData().add(new XYChart.Data(7, 23));
        series2.getData().add(new XYChart.Data(8, 26));
        series2.getData().add(new XYChart.Data(9, 28));
        series2.getData().add(new XYChart.Data(10, 32));
        series2.getData().add(new XYChart.Data(11, 33));
        series2.getData().add(new XYChart.Data(12, 36));
        lineChart.getData().add(series2);
       */
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}