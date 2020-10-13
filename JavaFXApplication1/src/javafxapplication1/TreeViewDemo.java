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
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author donsavinero
 */
public class TreeViewDemo extends Application {
     Stage window;
    Scene scene;
    TreeView<String> tree;
    
    public void start(Stage primaryStage) {
        // Swicthing between scenes
        window = primaryStage;
        window.setTitle("Tree View");
     
        TreeItem<String> root, branch1, branch2;
        
        // Root
        root = new TreeItem<>();
        root.setExpanded(true);
        
        // Branch1
        branch1 = makeBranch("don", root);// root parent of branch1
        makeBranch("savinero", branch1); // savinero is a leaf with parent branch1
        makeBranch("diego", branch1);
        makeBranch("salvado", branch1);
        
        //Branch2
        branch2 = makeBranch("sir", root);
        makeBranch("patrick", branch2); 
        makeBranch("peter", branch2);
        makeBranch("jean", branch2);
        
        // Create tree
        tree = new TreeView<>(root);
        tree.setShowRoot(false);
        tree.getSelectionModel().selectedItemProperty().addListener((v, oldvalue, newvalue) -> {
          if(newvalue != null){
            System.out.println(newvalue);
          }
        });
        
        //layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().add(tree);
        
        
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

    //Create branches
    private TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(true);
        parent.getChildren().add(item);
      return item;
    }

}
