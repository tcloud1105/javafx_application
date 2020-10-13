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
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author donsavinero
 */
public class ChoiceBoxView extends Application {
     Stage window;
    Scene scene;
    Button button;
    public void start(Stage primaryStage) {
        // Swicthing between scenes
        window = primaryStage;
        window.setTitle("DropDown Menu");
        
        
        
        //Button 
        button = new Button("Order Now!");
        
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
       
        //getItems returns the ObservableList object which you can add to
        choiceBox.getItems().add("pizza");
        choiceBox.getItems().add("apples");
        choiceBox.getItems().addAll("meat", "rice","orange");
        
        //Set the default value
        choiceBox.setValue("apples");
        
        //Listen for selection changes
        choiceBox.getSelectionModel().selectedItemProperty().addListener((v,oldValue, newValue) -> System.out.println(newValue));
        
        button.setOnAction(e -> getChoice(choiceBox));
        //layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(choiceBox, button);
        
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

    //get the value of the selected item
    private void getChoice(ChoiceBox<String> choiceBox) {
        //String choice = choiceBox.getSelectionModel().getSelectedItem();
        String choice = choiceBox.getValue();
        System.out.println(choice);
    }
  
}
