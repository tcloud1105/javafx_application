package sample.addBook;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
public class AddBookController {
    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXTextField author;

    @FXML
    private JFXTextField publisher;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField title;

    @FXML
    private JFXButton saveButton;

    @FXML
    void addBook(ActionEvent event) {
        System.out.println("we are in good shape");
    }

    @FXML
    void cancel(ActionEvent event) {

    }
}
