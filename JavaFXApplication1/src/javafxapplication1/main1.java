/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author donsavinero
 */
public class main1 extends Application {
     Stage window;
    Scene scene1;
    public void start(Stage primaryStage) {
        window = primaryStage;
        
        Button button = new Button("Submit");
       IntegerProperty x = new SimpleIntegerProperty(3);
       IntegerProperty y = new SimpleIntegerProperty();
       
       //Simple binding example
       y.bind(x.multiply(10));
       System.out.println("x: "+ x.getValue());
       System.out.println("y: "+ y.getValue()+"\n");
       
       x.setValue(9);
       System.out.println("x: "+ x.getValue());
       System.out.println("y: "+ y.getValue()+"\n");
        
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(button);
        scene1 = new Scene(layout1, 200,200);
         
        window.setTitle("Main Window");
        window.setScene(scene1);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
