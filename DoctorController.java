/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;


import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sml
 */
public class DoctorController implements Initializable {
    
    @FXML
    private TableColumn<users, String> col_email;

    @FXML
    private TableColumn<users, Integer> col_id;

    @FXML
    private TableColumn<users, String> col_password;

    @FXML
    private TableColumn<users, String> col_type;

    @FXML
    private TableColumn<users, String> col_username;
    
    @FXML
    private TableColumn<users, Date> col_date;

    @FXML
    private TableView <users> table_users;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_type;

    @FXML
    private TextField txt_username;
    
    @FXML
    private TextField txt_date;

    
    ObservableList<users> listM;
    ObservableList<users> dataList;
    
   
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    /**
     * Initializes the controller class.
     */
    public void Add_users (){    
        conn = mysql.ConnectDb();
        String sql = "insert into users (username,password,email,type,date)values(?,MD5(?),?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_username.getText());
            pst.setString(2, txt_password.getText());
            pst.setString(3, txt_email.getText());
            pst.setString(4, txt_type.getText());
            pst.setString(5, txt_date.getText());
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Users Add succes");
            UpdateTable();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    

    //////// methode select users ///////
    @FXML
    public void getSelected (MouseEvent event){
    index = table_users.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    txt_id.setText(col_id.getCellData(index).toString());
    txt_username.setText(col_username.getCellData(index).toString());
    txt_password.setText(col_password.getCellData(index).toString());
    txt_email.setText(col_email.getCellData(index).toString());
    txt_type.setText(col_type.getCellData(index).toString());
    txt_date.setText(col_date.getCellData(index).toString());
    }

    public void Edit (){   
        try {
            conn = mysql.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_username.getText();
            String value3 = txt_password.getText();
            String value4 = txt_email.getText();
            String value5 = txt_type.getText();
            String value6 = txt_date.getText();
            String sql = "update users set user_id= '"+value1+"',username= '"+value2+"',password= MD5('"+
                    value3+"'),email= '"+value4+"',type= '"+value5+"',date= '"+value6+"' where user_id='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public void Delete(){
    conn = mysql.ConnectDb();
    String sql = "delete from users where user_id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
    }

    
    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<users,Integer>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<users,String>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<users,String>("password"));
        col_email.setCellValueFactory(new PropertyValueFactory<users,String>("email"));
        col_type.setCellValueFactory(new PropertyValueFactory<users,String>("type"));
        col_date.setCellValueFactory(new PropertyValueFactory<users,Date>("date"));
        listM = mysql.getDatausers();
        table_users.setItems(listM);
    }
    
    

    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    UpdateTable();
    
    // Code Source in description
    } 
   
    
}
