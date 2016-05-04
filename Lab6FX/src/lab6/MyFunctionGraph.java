/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

import java.io.File;
import java.io.IOException;
import static java.lang.System.exit;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;

/**
 *
 * @author AnaGabriela Barbieru
 */
public class MyFunctionGraph {

    public NumberAxis xAxis;
    public NumberAxis yAxis;
    public LineChart<Number, Number> lineChart;
    XYChart.Series series;
    public XYChart.Series[] serie = new XYChart.Series[100];
    public XYChart.Series s;
    public Label pointLabel;
    int i=0;
    public double [] xy = new double [100];

    public MyFunctionGraph() {
        xAxis = new NumberAxis();
        yAxis = new NumberAxis();
        xAxis.setLabel("Ox");
        yAxis.setLabel("Oy");
        xAxis.setSide(Side.BOTTOM);
        yAxis.setSide(Side.LEFT);
        lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setMinWidth(500);
        lineChart.setMinHeight(500);
        lineChart.setPrefWidth(700);
        lineChart.setPrefHeight(500);
        lineChart.setMaxWidth(1000);
        lineChart.setMaxHeight(1000);
        lineChart.setTitle("Graficul functiei");
        pointLabel = new Label();
        s = new XYChart.Series();
        pointLabel = createCursorGraphCoordsMonitorLabel(lineChart);
   
    }

    public MyFunctionGraph(String string) {
        xAxis = new NumberAxis("Ox", -30, 30, 2);
        yAxis = new NumberAxis("Oy", -30, 30, 2);
        xAxis.setSide(Side.BOTTOM);
        yAxis.setSide(Side.LEFT);
        lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setMinWidth(500);
        lineChart.setMinHeight(500);
        lineChart.setPrefWidth(700);
        lineChart.setPrefHeight(500);
        lineChart.setMaxWidth(1000);
        lineChart.setMaxHeight(1000);
        lineChart.setTitle("Graficul functiei");
        pointLabel = new Label();
        s = new XYChart.Series();
        xy = createInterpolateListener(lineChart);
    }
    
    private Label createCursorGraphCoordsMonitorLabel(LineChart<Number, Number> lineChart) {
        final Axis<Number> xAxis = lineChart.getXAxis();
        final Axis<Number> yAxis = lineChart.getYAxis();

        final Label cursorCoords = new Label();

        final Node chartBackground = lineChart.lookup(".chart-plot-background");
        for (Node n : chartBackground.getParent().getChildrenUnmodifiable()) {
            if (n != chartBackground && n != xAxis && n != yAxis) {
                n.setMouseTransparent(true);
            }
        }

        chartBackground.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                cursorCoords.setText(
                        String.format(
                                "(%.2f, %.2f)",
                                xAxis.getValueForDisplay(mouseEvent.getX()),
                                yAxis.getValueForDisplay(mouseEvent.getY())
                        )
                );
                Number n1 = xAxis.getValueForDisplay(mouseEvent.getX());
                Number n2 = yAxis.getValueForDisplay(mouseEvent.getY());
                System.out.println(xAxis.getValueForDisplay(mouseEvent.getX()));
                System.out.println(yAxis.getValueForDisplay(mouseEvent.getY()));
                xy[i] = (double) n1;
                i++;
                xy[i] = (double) n2;
                i++;
                //System.out.println("i: " +i);
            }
        });
        return cursorCoords;
    }

    public double[] createInterpolateListener(LineChart<Number, Number> lineChart){
        final Axis<Number> xAxis = lineChart.getXAxis();
        final Axis<Number> yAxis = lineChart.getYAxis();
        final double [] coordinates = new double [100];
        final Node chartBackground = lineChart.lookup(".chart-plot-background");
        for (Node n : chartBackground.getParent().getChildrenUnmodifiable()) {
            if (n != chartBackground && n != xAxis && n != yAxis) {
                n.setMouseTransparent(true);
            }
        }
        chartBackground.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Number n1 = xAxis.getValueForDisplay(mouseEvent.getX());
                Number n2 = yAxis.getValueForDisplay(mouseEvent.getY());
                coordinates[i] = (double) n1;
                i++;
                coordinates[i] = (double) n2;
                i++;
                System.out.println("i: " +i);
                      
            }
        });
        return coordinates;
    }
    
    public void saveAsPng() {
        
        WritableImage image = lineChart.snapshot(new SnapshotParameters(), null);
        File file = new File("chart.png");

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
