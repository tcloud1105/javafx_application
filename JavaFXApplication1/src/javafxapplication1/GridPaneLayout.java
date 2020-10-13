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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 *
 * @author donsavinero
 */
public class GridPaneLayout extends Application{
    Stage window;
     public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("GridPane Layout");
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        
        //Name label
        Label nameLabel = new Label("Username: ");
        nameLabel.setId("bold-label");// apply css using id
        nameLabel.setStyle("-fx-text-fill:#b8b8b8");// inline style
        GridPane.setConstraints(nameLabel, 0, 0);
        
        //Name input
        TextField nameInput = new TextField();
        nameInput.setPromptText("Enter your username");
        GridPane.setConstraints(nameInput, 1,0);
        
        //Password label
        Label passLabel = new Label("Password: ");
        passLabel.setStyle("-fx-text-fill:#b8b8b8");
        GridPane.setConstraints(passLabel, 0, 1);
        
        //Password input
        TextField passInput = new TextField();
        passInput.setPromptText("Enter your password");
        GridPane.setConstraints(passInput, 1, 1);
        
        //Login
        Button loginButton = new Button("Log In");
        GridPane.setConstraints(loginButton, 1, 2);
        
        //Sign Up
        Button signUpButton = new Button("sign up");
        signUpButton.getStyleClass().add("button-blue"); //Add a custom stylesheet using classes
        GridPane.setConstraints(signUpButton, 1, 3);
        
//        loginButton.setOnAction(e->{
//          setUserAgentStylesheet(STYLESHEET_CASPIAN);
//        });
        grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton, signUpButton);
        Scene scene = new Scene(grid, 300, 200);
        scene.getStylesheets().add("style.css");// CSS themes and style
        
        window.setScene(scene);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
