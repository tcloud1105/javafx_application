/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author donsavinero
 */
public class Person {
    private StringProperty firstName = new SimpleStringProperty(this, "firstName", "");

    //return the firstName value (ie. "cedric")
    public String getFirstName() {
        return firstName.get();
    }

    //set the firstName value
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    
    //Returns the StringProperty object
    public StringProperty firstNameProperty(){
      return firstName;
    }
}
