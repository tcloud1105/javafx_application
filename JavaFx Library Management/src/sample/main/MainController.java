package sample.main;

import com.jfoenix.controls.*;
import com.jfoenix.controls.events.JFXDialogEvent;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import sample.alert.AlertMaker;
import sample.database.DatabaseHandler;
import sample.login.Main;
import sample.utils.LibraryAssistantUtil;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController implements Initializable{
    private HBox book_info;
    private HBox member_info;
    private JFXTextField bookIDInput;
    private Label bookName;
    private Label bookAuthor;
    private Label bookStatus;
    private JFXTextField memberIDInput;
    private Label memberName;
    private Label memberMobile;
    private JFXTextField bookID;
    private StackPane rootPane;
    private JFXHamburger hamburger;
    private JFXDrawer drawer;
    private Label memberNameHolder;
    private Label memberEmailHolder;
    private Label memberContactHolder;
    private Label bookNameHolder;
    private Label bookAuthorHolder;
    private Label bookPublisherHolder;
    private Label issueDateHolder;
    private Label numberDaysHolder;
    private Label fineInfoHolder;
    private JFXButton renewButton;
    private JFXButton submissionButton;
    private AnchorPane rootAnchorPane;
    private HBox submissionDataContainer;
    private DatabaseHandler databaseHandler;
    private PieChart bookChart;
    private StackPane bookInfoContainer;
    Boolean isReadyForSubmission = false;

    @Override
    public void initialize(URL url, ResourceBundle rs){
        JFXDepthManager.setDepth(book_info,1);
        JFXDepthManager.setDepth(member_info,1);

        initDrawer();
        initGraphs();
    }

    private void initGraphs() {
        bookChart = new PieChart(databaseHandler.getBookGraphStatistics());
        bookInfoContainer.getChildren().add(bookChart);
    }

    public void loadBookInfo(ActionEvent event){
        clearCacheBook();

        String id = bookIDInput.getText();
        String query = "SELECT * FROM BOOK WHERE id= '"+id+"'";
        ResultSet rs = databaseHandler.executeQuery(query);
        Boolean flag = false;

        try{
            while(rs.next()){
               String bName = rs.getString("title");
               String bAuthor = rs.getString("author");
               boolean bStatus = rs.getBoolean("isAvail");

               bookName.setText(bName);
               bookAuthor.setText(bAuthor);
               String status = (bStatus)?"Available":"Not Available";
               bookStatus.setText(status);

               flag=true;
            }

            if(!flag){
                bookName.setText("No Such Book Available");
            }
        }catch (SQLException ex){
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void clearCacheBook() {
        bookName.setText("");
        bookAuthor.setText("");
        bookStatus.setText("");
    }

    public void loadMemberInfo(ActionEvent event){
        clearCacheMember();

        String id = memberIDInput.getText();
        String query = "SELECT * FROM MEMBER WHERE id= '"+id+"'";
        ResultSet rs = databaseHandler.executeQuery(query);
        Boolean flag = false;

        try{
            while(rs.next()){
                String mName = rs.getString("name");
                String mMobile = rs.getString("mobile");


                memberName.setText(mName);
                memberMobile.setText(mMobile);


                flag=true;
            }

            if(!flag){
                bookName.setText("No Such Member Available");
            }
        }catch (SQLException ex){
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void clearCacheMember() {
        bookName.setText("");
        bookAuthor.setText("");
        bookStatus.setText("");
    }

    private void loadBookInfo2(ActionEvent event){
        clearEntries();
        ObservableList<String> issueData = FXCollections.observableArrayList();
        isReadyForSubmission = false;

        try {
            String id = bookID.getText();
            String myQuery = "SELECT ISSUE.bookID, ISSUE.memberID,ISSUE.issueTime,ISSUE.renew_count,\n"
                    + "MEMBER.name, MEMBER.mobile, MEMBER.email,\n"
                    + "BOOK.title, BOOK.author, BOOK.publisher, BOOK.isAvail\n"
                    + "FROM issue\n"
                    + "LEFT JOIN MEMBER\n"
                    + "ON ISSUE.memberID=MEMBER.ID\n"
                    + "LEFT JOIN BOOK\n"
                    + "ON ISSUE.bookID=BOOK.ID\n"
                    + "WHERE ISSUE.bookID='" + id + "'";
            String query = "SELECT * FROM ISSUE WHERE bookID='" + id + "'";
            ResultSet rs = databaseHandler.executeQuery(query);
            if(rs.next()){
                memberNameHolder.setText(rs.getString("name"));
                memberContactHolder.setText(rs.getString("mobile"));
                memberEmailHolder.setText(rs.getString("email"));

                bookNameHolder.setText(rs.getString("name"));
                bookAuthorHolder.setText(rs.getString("author"));
                bookPublisherHolder.setText(rs.getString("publisher"));

                Timestamp mIssueTime = rs.getTimestamp("issueTime");
                Date dateOfIssue = new Date(mIssueTime.getTime());
                issueDateHolder.setText(dateOfIssue.toString());
                Long timeElapsed = System.currentTimeMillis() - mIssueTime.getTime();
                Long daysElapsed = TimeUnit.DAYS.convert(timeElapsed, TimeUnit.MILLISECONDS);
                numberDaysHolder.setText(daysElapsed.toString());
                fineInfoHolder.setText("Not Supported Yet");
            }else{
                JFXButton button = new JFXButton("Okay i'll check");
                AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(button),"No Such Book Exists In Database",null);
            }
             isReadyForSubmission = true;
            disableEnableControls(true);
            submissionDataContainer.setOpacity(1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadIssueOp(ActionEvent event){
        String memberID = memberIDInput.getText();
        String bookID = bookIDInput.getText();

        JFXButton yesButton = new JFXButton("YES");
        yesButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            String q1 = "INSERT INTO ISSUE(memberID,BookID) VALUES ("
                    +"'"+memberID+"',"
                    +"'"+bookID+"')";
            String q2 = "UPDATE BOOK SET isAvail=FALSE WHERE id='"+bookID+"'";

            if(databaseHandler.execAction(q1) && databaseHandler.execAction(q2)){
                JFXButton button = new JFXButton("Done!");
                AlertMaker.showMaterialDialog(rootPane,rootAnchorPane,Arrays.asList(button),"Book Issue Complete",null);
            }else{
                JFXButton button = new JFXButton("Ok,I'll check!");
                AlertMaker.showMaterialDialog(rootPane,rootAnchorPane,Arrays.asList(button),"Submission has been failed",null);

            }
            clearIssueEntries();
        });

        JFXButton noButton = new JFXButton("NO");
        noButton.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            JFXButton button = new JFXButton("That's ok");
            AlertMaker.showMaterialDialog(rootPane,rootAnchorPane,Arrays.asList(yesButton, noButton),"Confirm Issue","Are you sure you want to issue the book"+bookName.getText()+" to "+memberName.getText()+" ?");
            clearIssueEntries();
        });
        AlertMaker.showMaterialDialog(rootPane,rootAnchorPane,Arrays.asList(button),"Book Issue Complete",null);


    }

    private void clearIssueEntries() {
        bookIDInput.clear();
        memberIDInput.clear();
        bookName.setText("");
        bookAuthor.setText("");
        bookStatus.setText("");
        memberName.setText("");
        memberMobile.setText("");

    }

    private void loadSubmissionOp(ActionEvent event){
        if(!isReadyForSubmission){
            JFXButton btn = new JFXButton("Okay");
            AlertMaker.showMaterialDialog(rootPane,rootAnchorPane,Arrays.asList(btn),"Please select a book to submit",null);
            return;
        }

        JFXButton btn = new JFXButton("Okay");
        AlertMaker.showMaterialDialog(rootPane,rootAnchorPane,Arrays.asList(btn),"Please select a book to submit",null);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Submission operation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to return the book");

        Optional<ButtonType> response = alert.showAndWait();
        if(response.get() == ButtonType.OK){
            String id = bookID.getText();
            String q1 = "DELETE FROM ISSUE WHERE BookID='"+id+"'";
            String q2 = "UPDATE BOOK SET ISAVAIL=TRUE WHERE ID="+id;

            if(databaseHandler.execAction(q1) && databaseHandler.execAction(q2)){
                JFXButton btn = new JFXButton("Done!");
                AlertMaker.showMaterialDialog(rootPane,rootAnchorPane,Arrays.asList(btn),"Book has been submitted",null);
                disableEnableControls(false);
                submissionDataContainer.setOpacity(0);
            }else{
                JFXButton btn = new JFXButton("Okay!");
                AlertMaker.showMaterialDialog(rootPane,rootAnchorPane,Arrays.asList(btn),"Submission operation cancelled",null);

            }
        }else{
            JFXButton btn = new JFXButton("Okay,I'll check!");
            AlertMaker.showMaterialDialog(rootPane,rootAnchorPane,Arrays.asList(btn),"submission has failed",null);
        }
    }

    private void loadRenewOp(ActionEvent event){
        if(!isReadyForSubmission){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText(null);
            alert.setContentText("Please select a book to renew");
            alert.showAndWait();
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Renew operation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to return the book");

        Optional<ButtonType> response = alert.showAndWait();
        if(response.get() == ButtonType.OK){
            String id = bookID.getText();
            String q = "UPDATE ISSUE SET issueTIme=CURRENT_TIME WHERE ID="+id;

            if(databaseHandler.execAction(q1)){
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setTitle("Success");
                alert1.setHeaderText(null);
                alert1.setContentText("Book has been renewed");
                alert1.showAndWait();
                loadBookInfo2(null);
            }else{
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Failed");
                alert.setHeaderText(null);
                alert.setContentText("Renew has been failed");
                alert.showAndWait();
            }
        }else{
                Alert alert3 = new Alert(Alert.AlertType.ERROR);
                alert3.setTitle("Cancelled");
                alert3.setHeaderText(null);
                alert3.setContentText("Renew operation has been cancelled");
                alert3.showAndWait();
        }
    }

    private void clearEntries() {
        memberNameHolder.setText("");
        memberEmailHolder.setText("");
        memberContactHolder.setText("");

        bookNameHolder.setText("");
        bookAuthorHolder.setText("");
        bookPublisherHolder.setText("");

        issueDateHolder.setText("");
        numberDaysHolder.setText("");
        fineInfoHolder.setText("");
        submissionDataContainer.setOpacity(0);
    }

    private void disableEnableControls(Boolean enableFlag){
        if(enableFlag){
          renewButton.setDisable(false);
          submissionButton.setDisable(false);
        }else{
            renewButton.setDisable(true);
            submissionButton.setDisable(true);
        }
    }

    private void loadAddMember(ActionEvent event){
        LibraryAssistantUtil.loadWindow(getClass().getResource("/sample/addMember/addMember.fxml"),"Add New Member",null);
    }

    private void loadAddBook(ActionEvent event){
        LibraryAssistantUtil.loadWindow(getClass().getResource("/sample/addMember/addBook.fxml"),"add new book",null);
    }

    private void loadMemberTable(ActionEvent event){
        LibraryAssistantUtil.loadWindow(getClass().getResource("/sample/listMember/listMember.fxml"),"member list",null);
    }

    private void loadBookTable(ActionEvent event){
        LibraryAssistantUtil.loadWindow(getClass().getResource("/sample/listBook/listBook.fxml"),"book list",null);
    }

    private void loadSettings(ActionEvent event){
        LibraryAssistantUtil.loadWindow(getClass().getResource("/sample/settings/settings.fxml"),"settings",null);
    }


    private void initDrawer(){
        try {
            VBox toolbar = FXMLLoader.load(getClass().getResource("/sample/main/toolbar/toolbar.fxml"));
            drawer.setSidePane(toolbar);
            drawer.setDefaultDrawerSize(150);
        }catch(IOException ex){
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null,ex);
        }
        HamburgerSlideCloseTransition task = new HamburgerSlideCloseTransition(hamburger);
        task.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (EventHandler<Event>) event -> {
            task.setRate(task.getRate()*-1);
            task.play();
            if(drawer.isClosed()){
                drawer.open();
                drawer.setMinWidth(200);
            }else{
                drawer.close();
                drawer.setMinWidth(200);
            }
        });
    }

}
