/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

/**
 *
 * @author AnaGabriela Barbieru
 */
public class InterpolateBox {
    
    public VBox vbox;
    public Label nrPoints;
    public TextField nrField;
    public Button submit;
    
    public InterpolateBox(){
        vbox = new VBox();
        nrPoints = new Label("Introduceti numarul de puncte pentru care doriti sa "
                + "interpolati functia, \n apoi selectati punctele pe graficul alaturat \n"
                + "si apasati pe \"Submit\":");
        nrPoints.setFont(Font.font("Verdana", FontPosture.REGULAR, 14));
        nrField = new TextField();
        submit = new Button("Submit");
        vbox.setSpacing(20);
        vbox.setStyle("-fx-background-color: #FFDAB9;");
        vbox.getChildren().addAll(nrPoints, nrField, submit);
    }
}
