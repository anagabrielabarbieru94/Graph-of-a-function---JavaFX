/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author AnaGabriela Barbieru
 */
public class MyStage extends Application{

    public MyPane pane;
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        pane = new MyPane();
        StackPane root = new StackPane();
        root.getChildren().add(pane.border);
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Graphic");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
