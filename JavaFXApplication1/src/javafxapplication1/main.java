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
public class main extends Application {
     Stage window;
    Scene scene1;
    public void start(Stage primaryStage) {
        // Swicthing between scenes
        window = primaryStage;
        Label label = new Label("Main Scene");
        Button button1 = new Button("open the alert ");
        button1.setOnAction(e -> AlertBox.display("Alert Box","New alert box"));
        Button button2 = new Button("open the confirm box");
        button2.setOnAction(e -> {
            Boolean result = ConfirmBox.display("Confirm Box","New confirm box");
            if(result){
              System.out.println("Yes");
            }else{
              System.out.println("No");
            }
             });
        Button button3 = new Button("close program");
        button3.setOnAction(e->closeProgram());
        //Layout 1 - children are laid out in vertical column
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label, button1, button2, button3);
        scene1 = new Scene(layout1, 200,200);
         
        window.setTitle("Main Window");
        window.setScene(scene1);
        window.setOnCloseRequest(e ->{
         e.consume();
        closeProgram();
        }); // to close with the x close button
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void closeProgram() {
        Boolean answer = ConfirmBox.display("Close Window", "do you want to close?");
        if(answer){
          window.close();
        }
    }
    
}
