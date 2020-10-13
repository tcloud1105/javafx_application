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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author donsavinero
 */
public class ComboBoxView extends Application {
     Stage window;
    Scene scene;
    Button button;
    ComboBox<String> comboBox;
    
    public void start(Stage primaryStage) {
        // Swicthing between scenes
        window = primaryStage;
        window.setTitle("ComboBox");
        
        //Button 
        button = new Button("Submit");
        
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll("man in black", "ghost", "trip to manhattan", "oversea");
        comboBox.setPromptText("What is your favorite movie?");
        comboBox.setEditable(true);// let you edit an item in the editable menu
        button.setOnAction(e -> printMovie());
        
        comboBox.setOnAction(e -> System.out.println("User selected: " + comboBox.getValue()));
        
        //layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(comboBox, button);
        
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

    private void printMovie() {
        String movie = comboBox.getValue();
        System.out.println(movie);
    }

    
}
