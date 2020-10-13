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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author donsavinero
 */
public class BindingDemo extends Application {
     Stage window;
    Scene scene1;
    public void start(Stage primaryStage) {
        window = primaryStage;
        //Input and Label
        TextField input = new TextField();
        input.setMaxWidth(100);
        Label firstLabel = new Label("Welcome to the site ");
        Label secondLabel = new Label();
        
        HBox hbox = new HBox();
        hbox.getChildren().addAll(firstLabel, secondLabel);
        hbox.setAlignment(Pos.CENTER);
        
        Button button = new Button("Submit");
        secondLabel.textProperty().bind(input.textProperty());// data binding
        
        VBox layout1 = new VBox(20, input, hbox);
        layout1.setAlignment(Pos.CENTER);
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
