package sample.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DatabaseHandler {
    private static DatabaseHandler handler;

    private static final String DB_URL = "jdbc:derby:database;create=true";
    private static Connection conn = null;
    private static Statement stmt = null;

    private DatabaseHandler(){
        createConnection();
        setupBookTable();
        setupMemberTable();
        setupIssueTable();
    }

    public static DatabaseHandler getInstance(){
        if(handler==null){
            handler = new DatabaseHandler();
        }

        return handler;
    }

    public boolean execAction(String query){
        try{
          stmt = conn.createStatement();
          stmt.execute(query);
          return true;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error:"+ex.getMessage(),"Error Occurred",JOptionPane.ERROR_MESSAGE);
           System.out.println("Exception at execQuery:dataHandler"+ex.getLocalizedMessage());

          return false;
        }
    }

    public boolean deleteBook(Book book){
        try{
           String deleteStatement = "DELETE FROM BOOK WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(deleteStatement);
            stmt.setString(1, book.getID());
            int res = stmt.executeUpdate();
            if(res==1){
                return true;
            }
        }catch (SQLException ex){
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE,null,ex);
        }
        return false;
    }
    private void setupIssueTable() {

    }

    private void setupMemberTable() {
    }

    private void setupBookTable() {
    }

    private void createConnection() {
    }

    public ObservableList<PieChart.Data> getBookGraphStatistics(){
       ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
       String query1 = "SELECT count(*) FROM BOOK";
       String query2 = "SELECT count(*) FROM ISSUE";


        try {
            ResultSet rs = execQuery(query1);
            if(rs.next()){
               int count = rs.getInt(1);
               data.add(new PieChart.Data("Total Books("+count+")",count));
            }

            rs=execQuery(query2);
            if(rs.next()){
                int count = rs.getInt(1);
                data.add(new PieChart.Data("Issued Books("+count+")",count));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return data;
    }

    private ResultSet execQuery(String query1) {

    }
}
