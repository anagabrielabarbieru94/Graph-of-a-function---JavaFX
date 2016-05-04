/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

import javafx.scene.layout.BorderPane;

/**
 *
 * @author AnaGabriela Barbieru
 */
public class MyPane {
    
    public BorderPane border;
    public MyHBox hBox;
    
    public MyPane(){
        MyHBox box = new MyHBox();
        border = new BorderPane();
        border.setTop(box.getHBox());
        MyGraphBox graficBox = new MyGraphBox();
        border.setCenter(box.graphBox);
    }
}
