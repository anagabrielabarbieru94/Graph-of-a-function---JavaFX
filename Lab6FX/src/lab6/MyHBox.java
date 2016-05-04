/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javafx.scene.chart.NumberAxis;

/**
 *
 * @author AnaGabriela Barbieru
 */
public class MyHBox {

    public HBox topHBox;
    public MyFunctionGraph grafic;
    public GridPane graphBox;
    public Label startLabel;
    public Label resetLabel;
    public boolean isReset = false;
    public boolean isInterpolate = false;
    public int seriesIndex = 0;
    public Label pointLabel;
    public InterpolateBox iBox;

    public MyHBox() {
        this.topHBox = new HBox();
        this.grafic = new MyFunctionGraph();
        this.graphBox = new GridPane();
        graphBox.setStyle("-fx-background-color: #FFF0B2;");
        this.startLabel = new Label(" \n \t Instructiuni:\n"
                + "\t - Pentru a vedea graficul unei functii introduceti functia dorita in caseta de text de mai sus si apasati \"Vezi graficul\";\n"
                + "\t - Penru a vedea interpolarea unei functii apasati \"Interpoleaza o functie\";\n"
                + "\t - Pentru a salva un grafic ca o imagine apasati \"Salveaza graficul\"\n"
                + "\t - Pentru a vedea imaginea salavata apasati \"Vezi imaginea\"\n"
                + "\t - Pentru a reseta un grafic sau o interpolare apasati \"Resetati graficul\"");
        startLabel.setFont(Font.font("Verdana", FontPosture.REGULAR, 14));
        graphBox.add(startLabel, 0, 0);
        topHBox.setPadding(new Insets(20, 20, 20, 20));
        topHBox.setSpacing(20);
        topHBox.setStyle("-fx-background-color: #FFA500;");

        Label functionLabel = new Label("f(x):");
        functionLabel.setFont(Font.font("Verdana", FontPosture.REGULAR, 14));
        TextField textField = new TextField();
        topHBox.setSpacing(30);
        Button graphicButton = new Button("Vezi graficul");
        graphicButton.setFont(Font.font("Verdana", FontPosture.REGULAR, 14));
        graphicButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if ((textField.getText() != null && !textField.getText().isEmpty())) {
                    System.out.println(textField.getText());
                    Expression expression = new ExpressionBuilder(textField.getText()).variables("x").build();
                    double result = 0;
                    if (isReset == true) {
                        seriesIndex = 0;
                        isReset = false;
                        grafic = new MyFunctionGraph();
                    }
                    if (startLabel != null) {
                        graphBox.getChildren().remove(startLabel);
                    }
                    if (resetLabel != null) {
                        graphBox.getChildren().remove(resetLabel);
                    }
                    grafic.serie[seriesIndex] = new XYChart.Series();
                    grafic.serie[seriesIndex].setName("f(x) = " + textField.getText());
                    if (textField.getText().startsWith("log") || textField.getText().startsWith("sqrt")) {
                        for (int i = 1; i <= 10; i++) {
                            expression.setVariable("x", i);
                            if (expression.validate().isValid()) {
                                result = expression.evaluate();
                                System.out.println("Rezultatul functiei pentru i= " + i + ": " + result);
                                grafic.serie[seriesIndex].getData().add(new XYChart.Data(i, result));
                            }
                        }
                    } else {
                        for (int i = -10; i <= 10; i++) {
                            expression.setVariable("x", i);
                            if (expression.validate().isValid()) {
                                result = expression.evaluate();
                                System.out.println("Rezultatul functiei pentru i= " + i + ": " + result);
                                grafic.serie[seriesIndex].getData().add(new XYChart.Data(i, result));
                            }
                        }
                    }
                    grafic.lineChart.getData().add(grafic.serie[seriesIndex]);
                    graphBox.getChildren().remove(grafic.lineChart);
                    graphBox.getChildren().remove(grafic.pointLabel);
                    graphBox.add(grafic.pointLabel, 0, 0);
                    graphBox.add(grafic.lineChart, 0, 1);
                    seriesIndex++;
                    isInterpolate = false;
                } else {
                    System.out.println("functie neintrodusa");
                }
            }
        });

        Button saveButton = new Button("Salveaza graficul");
        saveButton.setFont(Font.font("Verdana", FontPosture.REGULAR, 14));
        saveButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                grafic.saveAsPng();
                System.out.println("Imagine salvata");
            }
        });
        Button loadButton = new Button("Vezi imaginea");
        loadButton.setFont(Font.font("Verdana", FontPosture.REGULAR, 14));
        loadButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                File f = new File("F:\\FACULTATE\\ANULII_SEM.2\\Progr. avansata - Java\\Laboratoare\\Lab6FX\\chart.png");
                Desktop d = Desktop.getDesktop();
                try {
                    d.open(f);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        Button resetButton = new Button("Reseteaza graficul");
        resetButton.setFont(Font.font("Verdana", FontPosture.REGULAR, 14));
        resetButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                resetLabel = new Label("\n \tAti resetat graficul. Introduceti o alta functie.");
                resetLabel.setFont(Font.font("Verdana", FontPosture.REGULAR, 14));
                graphBox.getChildren().clear();
                graphBox.add(resetLabel, 0, 0);
                isReset = true;
            }
        });

        Button interButton = new Button("Interpoleaza o functie");
        interButton.setFont(Font.font("Verdana", FontPosture.REGULAR, 14));
        interButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                graphBox.getChildren().remove(startLabel);
                graphBox.getChildren().remove(resetLabel);
                graphBox.getChildren().remove(grafic.lineChart);
                graphBox.getChildren().remove(grafic.pointLabel);
                iBox = new InterpolateBox();
                graphBox.add(iBox.vbox, 0, 0);
                grafic = new MyFunctionGraph("grafic");
                graphBox.add(grafic.lineChart, 1, 0);
                double[] coordinates = grafic.createInterpolateListener(grafic.lineChart);
                iBox.submit.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent e) {
                        int nrPoints = Integer.parseInt(iBox.nrField.getText());
                        System.out.println(nrPoints);
                        double[] x = new double[nrPoints];
                        double[] y = new double[nrPoints];
                        for (int i = 0; i < nrPoints; i++) {
                            x[i] = coordinates[2 * i];
                            y[i] = coordinates[2 * i + 1];
                            System.out.println("x: " + x[i]);
                            System.out.println("y: " + y[i]);
                        }
                        System.out.println("Interpolating...");
                        graphBox.getChildren().remove(grafic.lineChart);
                        grafic = new MyFunctionGraph();
                        double result, numarator, numitor;
                        for (int i = -10; i <= 10; i = i + 2) {
                            result = 0;
                            for (int j = 0; j < nrPoints; j++) {
                                numarator = 1;
                                numitor = 1;
                                for (int k = 0; k < nrPoints; k++) {
                                    if (k != j) {
                                        numarator = numarator * (i - x[k]);
                                        numitor = numitor * (x[j] - x[k]);
                                    }
                                }
                                result = result + (numarator / numitor) * y[j];
                            }
                            System.out.println("x: " + i);
                            System.out.println("y: " + result);
                            grafic.s.getData().add(new XYChart.Data(i, result));
                        }
                        graphBox.getChildren().remove(grafic.lineChart);
                        grafic.lineChart.getData().add(grafic.s);
                        graphBox.add(grafic.lineChart, 1, 0);
                        isInterpolate = true;

                    }
                });
            }
        });
        Button exitButton = new Button("Iesire");
        exitButton.setFont(Font.font("Verdana", FontPosture.REGULAR, 14));
        exitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                System.exit(0);
            }
        });

        topHBox.getChildren().addAll(functionLabel, textField, graphicButton, saveButton, loadButton, resetButton, interButton, exitButton);
    }

    public HBox getHBox() {
        return topHBox;
    }

    public MyFunctionGraph getGraph() {
        return grafic;
    }
}
