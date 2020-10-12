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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author lenovo
 */
public class Main extends Application implements EventHandler<ActionEvent> {
    Button btn;
    Button Alertbtn;
    Button closeProgram;
    Stage window;
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
      window.setTitle("Title of the window");
      btn = new Button();
      btn.setText("click me");
      btn.setOnAction(this);
      
      Alertbtn =new Button();
       Alertbtn.setText("Open Comfirmation Box");
      Alertbtn.setOnAction(this);
      //StackPane layout = new StackPane();
      //layout.getChildren().add(btn);
      closeProgram = new Button("close the program");
      closeProgram.setOnAction(this);
     /* closeProgram.setOnCloseRequest(e -> {
            e.consume();
              closeProgram();
        });uisng the default close button to close properly your program
      */
      VBox layout2 =new VBox();
      layout2.getChildren().addAll(Alertbtn,btn,closeProgram);
      Scene scene = new Scene(layout2,300,250);
      window.setScene(scene);
      window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);//used to set up your program as a javafx application
    }
    
    private void closeProgram(){
    Boolean result = ConfirmBox.display("Confirmation Box", "are u sure you want to close");
     if(result)
    window.close();
    }
    public void handle(ActionEvent event){
      if(event.getSource()==btn){
       System.out.println("yes i can make it");
      }
      if(event.getSource()==Alertbtn){
     boolean result= ConfirmBox.display("Alert Window","Do you want to close this box?");
     System.out.println(result);
      }
      if(event.getSource()==closeProgram){
          closeProgram();
      }
    }
}
