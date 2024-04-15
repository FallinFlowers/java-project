/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;
import Table.REGISTER;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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
public class Table_REGISTERController implements Initializable {

    @FXML
    private TableColumn<REGISTER, String> col_adr;

    @FXML
    private TableColumn<REGISTER,Date > col_date;

    @FXML
    private TableColumn<REGISTER, Integer> col_id;

    @FXML
    private TableColumn<REGISTER, String> col_log;

    @FXML
    private TableColumn<REGISTER, String> col_name;

    @FXML
    private TableColumn<REGISTER, String> col_pass;

    @FXML
    private TableColumn<REGISTER, Integer> col_tel;
    
    @FXML
    private JFXTextField filterField;

    @FXML
    private TableView<REGISTER> table_users;

    @FXML
    private TextField txt_adr;

    @FXML
    private TextField txt_date;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_log;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_pass;

    @FXML
    private TextField txt_tel;
    
    ObservableList<REGISTER> listM;
    ObservableList<REGISTER> dataList;
    
   
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    public void Add_users() {
        conn = mysql.ConnectDb();
        String sql = "insert into register (name,adress,date,telephone,login,password)values(?,?,?,?,MD5(?),MD5(?))";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_name.getText());
            pst.setString(2, txt_adr.getText());
            pst.setString(3, txt_date.getText());
            pst.setString(4, txt_tel.getText());
            pst.setString(5, txt_log.getText());
            pst.setString(6, txt_pass.getText());
            pst.execute();
            
            //JOptionPane.showMessageDialog(null, "Users Add succes");
            UpdateTable();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        UpdateTable();
        search_user();

    }
    
    @FXML
    void getSelected (MouseEvent event){
    index = table_users.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    txt_id.setText(col_id.getCellData(index).toString());
    txt_name.setText(col_name.getCellData(index).toString());
    txt_adr.setText(col_adr.getCellData(index).toString());
    txt_date.setText(col_date.getCellData(index).toString());
    txt_tel.setText(col_tel.getCellData(index).toString());
    txt_log.setText(col_log.getCellData(index).toString());
    txt_pass.setText(col_pass.getCellData(index).toString());
    }

    public void Edit (){   
        try {
            conn = mysql.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_name.getText();
            String value3 = txt_adr.getText();
            String value4 = txt_date.getText();
            String value5 = txt_tel.getText();
            String value6 = txt_log.getText();
            String value7 = txt_pass.getText();
            String sql = "update register set id_pegist= '"+value1+"',name= '"+value2+"',adress= '"+
                    value3+"',date= '"+value4+"',telephone= '"+value5+"',login= MD5('"+value6+"'),password= MD5('"+value7+"') where id_pegist='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            //JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        UpdateTable();
        search_user();
    }

    @FXML
    public void Delete() {
        conn = mysql.ConnectDb();
    String sql = "delete from register where id_pegist = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.execute();
            //JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        UpdateTable();
        search_user();
    
    }

    @FXML
    public void UpdateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<REGISTER,Integer>("id_pegist"));
        col_name.setCellValueFactory(new PropertyValueFactory<REGISTER,String>("name"));
        col_adr.setCellValueFactory(new PropertyValueFactory<REGISTER,String>("adress"));
        col_date.setCellValueFactory(new PropertyValueFactory<REGISTER,Date>("date"));
        col_tel.setCellValueFactory(new PropertyValueFactory<REGISTER,Integer>("telephone"));
        col_log.setCellValueFactory(new PropertyValueFactory<REGISTER,String>("login"));
        col_pass.setCellValueFactory(new PropertyValueFactory<REGISTER,String>("password"));
        listM = mysql.getDataREG();
        table_users.setItems(listM);
         search_user();

    }
    
    @FXML
    void search_user() {          
        col_id.setCellValueFactory(new PropertyValueFactory<REGISTER,Integer>("id_pegist"));
        col_name.setCellValueFactory(new PropertyValueFactory<REGISTER,String>("name"));
        col_adr.setCellValueFactory(new PropertyValueFactory<REGISTER,String>("adress"));
        col_date.setCellValueFactory(new PropertyValueFactory<REGISTER,Date>("date"));
        col_tel.setCellValueFactory(new PropertyValueFactory<REGISTER,Integer>("telephone"));
        col_log.setCellValueFactory(new PropertyValueFactory<REGISTER,String>("login"));
        col_pass.setCellValueFactory(new PropertyValueFactory<REGISTER,String>("password"));
        
        dataList = mysql.getDataREG();
        table_users.setItems(dataList);
        FilteredList<REGISTER> filteredData = new FilteredList<>(dataList, b -> true);  
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(person -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;}    
            String lowerCaseFilter = newValue.toLowerCase();
             if (person.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    else if (String.valueOf(person.getId_pegist()).indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
                                
         else  
          return false; // Does not match.
   });
  });  
        SortedList<REGISTER> sortedData = new SortedList<>(filteredData);  
        sortedData.comparatorProperty().bind(table_users.comparatorProperty());  
        table_users.setItems(sortedData);      
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
        search_user();
    }    
    
}
