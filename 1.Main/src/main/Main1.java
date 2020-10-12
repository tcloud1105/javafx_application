

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 *
 * @author lenovo
 */
public class Main1 extends Application {
   Button btn;
   
    @Override
    public void start(Stage primaryStage) throws Exception{
      primaryStage.setTitle("Title of the window");
      btn = new Button();
      btn.setText("click me");
     /* btn.setOnAction(new EventHandler<ActionEvent>(){
         
          public void handle(ActionEvent event){
            System.out.println("Another way to make it");
          }
      
      });
      Anonymous inner class but you can use lambda expression to do the same thing
      */
      /*btn.setOnAction(event -> 
          System.out.println("Another way to make it")
      );lambada expression with one line of code
*/
      btn.setOnAction(event -> {
          System.out.println("Another way to make it");
          System.out.println("yes i got it");
                  });
      StackPane layout = new StackPane();
      layout.getChildren().add(btn);
      
      
      Scene scene = new Scene(layout,300,250);
      primaryStage.setScene(scene);
      primaryStage.show();
    } 
    
    public static void main(String[] args) {
        launch(args);//used to set up your program as a javafx application
    }
}
