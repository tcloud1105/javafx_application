package sample.main.toolbar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.main.MainController;
import sample.utils.LibraryAssistantUtil;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ToolbarController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void loadAddMember(ActiveEvent event){
        LibraryAssistantUtil.loadWindow(getClass().getResource("/sample/addMember/addMember.fxml"),"Add New Member",null);
    }

    @FXML
    private void loadAddBook(ActiveEvent event){
        LibraryAssistantUtil.loadWindow(getClass().getResource("/sample/addMember/addBook.fxml"),"add new book",null);
    }

    @FXML
    private void loadMemberTable(ActiveEvent event){
        LibraryAssistantUtil.loadWindow(getClass().getResource("/sample/listMember/listMember.fxml"),"member list",null);
    }

    @FXML
    private void loadBookTable(ActiveEvent event){
        LibraryAssistantUtil.loadWindow(getClass().getResource("/sample/listBook/listBook.fxml"),"book list",null);
    }

    @FXML
    private void loadSettings(ActiveEvent event){
        LibraryAssistantUtil.loadWindow(getClass().getResource("/sample/settings/settings.fxml"),"settings",null);
    }
}
