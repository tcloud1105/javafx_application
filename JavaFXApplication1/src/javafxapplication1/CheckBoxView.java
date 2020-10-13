/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author donsavinero
 */
public class CheckBoxView extends Application {
     Stage window;
    Scene scene;
    Button button;
    public void start(Stage primaryStage) {
        // Swicthing between scenes
        window = primaryStage;
        window.setTitle("CheckBox View");
        
        //CheckBoxes
        CheckBox box1 = new CheckBox("pizza");
        CheckBox box2 = new CheckBox("chawarma");
        box2.setSelected(true);
        
        //Button 
        button = new Button("Order Now!");
        button.setOnAction(e -> handleOptions(box1, box2));
        
        //layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(box1, box2, button);
        
        scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    //handle checkbox options
    private void handleOptions(CheckBox box1, CheckBox box2) {
        String message = "User's order:\n";
        if(box1.isSelected())
            message += "pizza";
        if(box1.isSelected())
            message += "chawarma";
        System.out.println(message);
    }

    
    
}
