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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author donsavinero
 */
public class TableViewDemo extends Application {
     Stage window;
    Scene scene;
    TableView<Product> table;
    TextField nameinput, priceinput, quantityinput;
    
    public void start(Stage primaryStage) {
        // Swicthing between scenes
        window = primaryStage;
        window.setTitle("Table View");
   
        // Name Column
        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        //nameColumn.setCellValueFactory(new PropertyValueProperty<>('name'));
        
        // Price Column
        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        //priceColumn.setCellValueFactory(new PropertyValueProperty<>('price'));
        
        // Quantity Column
        TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(100);
        //quantityColumn.setCellValueFactory(new PropertyValueProperty<>('quantity'));
        
        // name input
        nameinput = new TextField();
        nameinput.setPromptText('name');
        nameinput.setMinWidth(100);
        
        // price input
        priceinput = new TextField();
        priceinput.setPromptText('price');
        
        // quantity input
        quantityinput = new TextField();
        quantityinput.setPromptText('quantity');
        
        // Button
        Button addButton = new Button("add");
        addButton.setOnAction(e-> addButtonClicked());
        Button deleteButton = new Button('delete');
        deleteButton.setOnAction(e-> deleteButtonClicked());
        
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(nameinput, priceinput, quantityinput, addButton, deleteButton);
        
        table = new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(nameColumn, priceColumn, quantityColumn);
        //layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(table, hbox);
        
        
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

    //Get all of the products
    public ObservableList<Product> getProduct(){
      ObservableList<Product> products = FXCollections.observableArrayList();
      products.add(new Product("Chocolate", 12.00, 24));
      products.add(new Product("Sugar", 23.00, 12));
      products.add(new Product("Laptop", 1200.00, 23));
      products.add(new Product("Apple Juice", 12.00, 230));
      products.add(new Product("Maize", 18.00, 23));
      return products;
    }

    //Add button clicked
    private void addButtonClicked() {
        Product product = new Product();
        product.setName(nameinput.getText());
        product.setPrice(Double.parseDouble(priceinput.getText()));
        product.setQuantity(Integer.parseInt(quantityinput.getText()));
        table.getItems().add(product);
        nameinput.clear();priceinput.clear();quantityinput.clear(); 
    }

    //Delete button clcked
    private void deleteButtonClicked() {
        ObservableList<Product> productSelected, allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();
        productSelected.forEach(allProducts::remove);
    }

}
