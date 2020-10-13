/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.awt.Color;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author donsavinero
 */
public class ExtractDataFromInput extends Application{
     Stage window;
     Button button;
     Scene scene;
     
     public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Extract Data From Form");
        
        //Form
        TextField nameInput = new TextField();
        button = new Button("Click Me");
        button.setOnAction(e ->isInt(nameInput, nameInput.getText()));
        
        //layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(nameInput, button);
        
        scene = new Scene(layout, 300, 200);
        window.setScene(scene);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private boolean isInt(TextField input, String message) {
        try{
           int age = Integer.parseInt(input.getText());
           System.out.println("User age is :"+age);
           return true;
        }catch(NumberFormatException e){
           System.out.println("Error: "+ message + " is not a number");
           return false;
        }
    }
}
