/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

import javafx.scene.layout.VBox;

/**
 *
 * @author AnaGabriela Barbieru
 */
public class MyGraphBox {
    VBox graphBox;
    //MyFunctionGraph grafic;
    
    public MyGraphBox(){
        
        this.graphBox = new VBox();
        //this.grafic = new MyFunctionGraph();
        //graphBox.getChildren().add(grafic.lineChart);
    }
    
    public VBox getGraphicBox(){
        return graphBox;
    }
}
