/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;

import Table.ADMIN;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sml
 */
public class AdminController implements Initializable {

     @FXML
    private TableColumn<ADMIN, String> col_email;

    @FXML
    private TableColumn<ADMIN, Integer> col_id;

    @FXML
    private TableColumn<ADMIN, String> col_password;

    

    @FXML
    private TableColumn<ADMIN, String> col_username;
    
    
    @FXML
    private TableView <ADMIN> table_users;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_password;

    
    @FXML
    private TextField txt_username;
    
    

    
    ObservableList<ADMIN> listM;
    ObservableList<ADMIN> dataList;
    
   
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    /**
     * Initializes the controller class.
     */
    public void Add_users (){    
        conn = mysql.ConnectDb();
        String sql = "insert into admin (username,password,email)values(?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_username.getText());
            pst.setString(2, txt_password.getText());
            pst.setString(3, txt_email.getText());
            
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Users Add succes");
            UpdateTable();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    

    //////// methode select users ///////
    @FXML
    void getSelected (MouseEvent event){
    index = table_users.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    txt_id.setText(col_id.getCellData(index).toString());
    txt_username.setText(col_username.getCellData(index).toString());
    txt_password.setText(col_password.getCellData(index).toString());
    txt_email.setText(col_email.getCellData(index).toString());
    
    }

    public void Edit (){   
        try {
            conn = mysql.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_username.getText();
            String value3 = txt_password.getText();
            String value4 = txt_email.getText();
            
            String sql = "update admin set id= '"+value1+"',username= '"+value2+"',password= '"+
                    value3+"',email= '"+value4+"' where id='"+value1+"' ";
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
    String sql = "delete from admin where id = ?";
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
        col_id.setCellValueFactory(new PropertyValueFactory<ADMIN,Integer>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<ADMIN,String>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<ADMIN,String>("password"));
        col_email.setCellValueFactory(new PropertyValueFactory<ADMIN,String>("email"));
        
        listM = mysql.getDatauserA();
        table_users.setItems(listM);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
    }    
    
}
