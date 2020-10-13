/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author donsavinero
 */
public class MenuDemo extends Application {
     Stage window;
    Scene scene;
    BorderPane layout;
    
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Menu View");
   
       //File menu
       Menu fileMenu = new Menu("File");
       
       //File menu Items
       MenuItem newMenu = new MenuItem("New...");
       newMenu.setOnAction(e->System.out.println("new file created"));
       fileMenu.getItems().add(newMenu);
       fileMenu.getItems().add(new MenuItem("Open..."));
       fileMenu.getItems().add(new MenuItem("Save..."));
       fileMenu.getItems().add(new SeparatorMenuItem());
       fileMenu.getItems().add(new MenuItem("Settings..."));
       fileMenu.getItems().add(new SeparatorMenuItem());
       fileMenu.getItems().add(new MenuItem("Exit"));
       
       //Edit Menu
      Menu editMenu = new Menu("_Edit");
      editMenu.getItems().add(new MenuItem('Cut'));
      editMenu.getItems().add(new MenuItem('Copy'));
      
      MenuItem paste = new MenuItem('Paste');
      paste.setOnAction(e->System.out.println("Pasting some crap"));
      paste.setDisable(true);
      editMenu.getItems().add(paste);
      
      //Help Menu
      Menu helpMenu = new Menu('Help');
      CheckMenuItem showLines = new CheckMenuItem("Show Line Numbers");
      showLines.setOnAction(e -> {
        if(showLines.isSelected())
            System.out.println("Program will display line numbers");
        else
            System.out.println("Hiding line numbers");
      });
      CheckMenuItem autoSave = new CheckMenuItem("Enable autosave");
      autoSave.setSelected(true);
      helpMenu.getItems().addAll(showLines);
      
       //Radio Menu Item
       Menu options = new Menu("Options");
       ToggleGroup optionsToggle = new ToggleGroup();
       RadioMenuItem easy = new RadioMenuItem("Easy");
       RadioMenuItem medium = new RadioMenuItem("Medium");
       RadioMenuItem hard = new RadioMenuItem("Hard");
       easy.setToggleGroup(optionsToggle);
       medium.setToggleGroup(optionsToggle);
       hard.setToggleGroup(optionsToggle);
       options.getItems().addAll(easy, medium, hard);
       
       //Main Menu bar
       MenuBar menuBar = new MenuBar();
       menuBar.getMenus().addAll(fileMenu, editMenu,helpMenu);
       
        //layout
       layout = new BorderPane();
       layout.setTop(menuBar);
        
        
        scene = new Scene(layout, 400, 300);
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
