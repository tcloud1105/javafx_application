/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author donsavinero
 */
public class ListViewDemo extends Application {
     Stage window;
    Scene scene;
    Button button;
    ListView<String> listView;
    
    public void start(Stage primaryStage) {
        // Swicthing between scenes
        window = primaryStage;
        window.setTitle("ListView");
        
        //Button 
        button = new Button("Submit");
        
        listView = new ListView<>();
        listView.getItems().addAll("24 Hours", "Prison Break", "Blind Spot", "Gold");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);// let u select multiple items in the list
        
        button.setOnAction(e -> buttonClicked());
        
        //layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(listView,button);
        
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

    // Print Our Movies
    private void buttonClicked() {
        String messages = "";
        ObservableList<String> movies;
       movies = listView.getSelectionModel().getSelectedItems();
       for(String movie : movies){
           messages += movie + " ";
       }
       System.out.println(messages);
    }
}
