package sample.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

public class Login {
    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField username;

    Preferences preference;

    public void initialize(URL url, ResourceBundle rb){
        preference = Preferences.systemRoot();
    }

    @FXML
    void handleLoginButtonAction(ActionEvent event) {
        String uname = username.getText();
        String pword = DigestUtils.shaHex(password.getText());

        if(uname.equals(preference.getUsername()) && pword.equals(preference.getPassword())){
            closeStage();
            loadMain();
        }else{
            username.getStyleClass().add("wrong-credentials");
            password.getStyleClass().add("wrong-credentials");
        }

    }

    private void loadMain() {
        try{
            Parent parent = FXMLLoader.load(getClass().getResource("/sample/main/main.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Library Assistant");
            stage.setScene(new Scene(parent));
            stage.show();

        }catch (IOException ex){
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null,ex);
        }
    }

    @FXML
    void handleCancelButtonAction(ActionEvent event) {
        System.exit(0);
    }


    private void closeStage() {
        ((Stage) username.getScene().getWindow()).close();
    }

}
