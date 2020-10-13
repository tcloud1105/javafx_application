/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

/**
 *
 * @author donsavinero
 */
public class Product {
    private String name;
    private double price;
    private int quantity;
    
    public Product(){
      this.name = "";
      this.price = 0.0;
      this.quantity = 0;
    }
    
    public Product(String name, double price, int quantity){
      this.name = name;
      this.price = price;
      this.quantity = quantity;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
    @Override
    public String toString() {
        return "Product{" + "name=" + name + ", price=" + price + ", quantity=" + quantity + '}';
    }
}
