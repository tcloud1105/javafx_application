/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;
import java.util.Random;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author donsavinero
 */
public class ProgressBarDemo extends Application {
     Stage window;
    Scene scene1;
    int numFiles = 10;
    Task copyWorker;
    public void start(Stage primaryStage) {
        // Swicthing between scenes
        window = primaryStage;
        final Label lbl = new Label("File Transfer: ");
        final ProgressBar pBar = new ProgressBar(0);
        final ProgressIndicator pIn = new ProgressIndicator();
        final Button startButton = new Button("Start");
        final Button cancelButton = new Button("Cancel");
        final TextArea textArea = new TextArea();
        
        startButton.setOnAction(e->{
           startButton.setDisable(true);
           cancelButton.setDisable(false);
           pBar.setProgress(0);
           pIn.setProgress(0);
           textArea.setText("");
           copyWorker = createWorker(numFiles);
           pBar.progressProperty().unbind();
           pBar.progressProperty().bind(copyWorker.progressProperty());
           pIn.progressProperty().unbind();
           pIn.progressProperty().bind(copyWorker.progressProperty());
           
           copyWorker.messageProperty().addListener((ObservableValue<? extends String> ob, String ov, String nv)->{
           textArea.appendText(nv+"\n");
           });
           new Thread(copyWorker).start();
        });
        
        cancelButton.setOnAction(e->{
           startButton.setDisable(false);
           cancelButton.setDisable(true);
           copyWorker.cancel(true);
           pBar.progressProperty().unbind();
           pBar.setProgress(0);
           pIn.progressProperty().unbind();
           pIn.setProgress(0);
           textArea.appendText("File Transfer Cancelled");
        });
        
        GridPane gridPane = new GridPane();
        gridPane.add(lbl, 0, 0);
        gridPane.add(pBar, 1, 0);
        gridPane.add(pIn, 2, 0);
        gridPane.add(startButton, 0, 1);
        gridPane.add(cancelButton, 1, 1);
        gridPane.add(textArea, 2, 1);
        gridPane.setAlignment(Pos.CENTER);
        
        BorderPane layout1 = new BorderPane();
        layout1.setCenter(gridPane);
        scene1 = new Scene(layout1, 600,300);
         
        window.setTitle("Progress Bar");
        window.setScene(scene1);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    //Task to perform
    private Task createWorker(final int numFiles){
    
        return new Task(){
            @Override
            protected Object call() throws Exception {
                for(int i=0; i<numFiles;i++){
                 long elapsedTime = System.currentTimeMillis();
                 copyFile("Some Src File", "Some Dest File");
                 elapsedTime = System.currentTimeMillis() - elapsedTime;
                 String status = elapsedTime + "milliseconds";
                 updateMessage(status);
                 updateProgress(i+1,numFiles);
                }
                return true;
            }
        
        };
    }
    
    private void copyFile(String src, String dest) throws InterruptedException{
      Random rd = new Random(System.currentTimeMillis());
      long millis = rd.nextInt(1000);
      Thread.sleep(millis);
      
    }
 
}
