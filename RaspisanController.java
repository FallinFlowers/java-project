/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;

import Table.PRIEM;
import java.awt.HeadlessException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
public class RaspisanController implements Initializable {

     @FXML
    private TableColumn<PRIEM, Date> col_date;

    @FXML
    private TableColumn<PRIEM, Integer> col_id_doc;

    @FXML
    private TableColumn<PRIEM, Integer> col_id_pac;

    @FXML
    private TableColumn<PRIEM, String> col_sp;

    @FXML
    private TableColumn<PRIEM, Time> col_time;

    @FXML
    private TextField filterField;
    
    @FXML
    private TableView<PRIEM> table_users;

    @FXML
    private TextField txt_data;

    @FXML
    private TextField txt_id_doc;

    @FXML
    private TextField txt_id_pac;

    @FXML
    private TextField txt_sp;

    @FXML
    private TextField txt_time;
    
    @FXML
    private Label txt_log;

    @FXML
    private Label txt_pass;
    
    ObservableList<PRIEM> listM;
    ObservableList<PRIEM> dataList;
    
   
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    public void Data(String log, String passw)
    {
        txt_log.setText(log);
        txt_pass.setText(passw);
        conn = mysql.ConnectDb();
        String sql = "SELECT priem.id_doctor, priem.special, priem.id_pacient, priem.date_pr, priem.time FROM priem INNER JOIN doctors ON priem.id_doctor= doctors.id_doctor WHERE doctors.login= '" + log + "' and doctors.password= '" + passw + "'" ;
        
            try {
            pst = conn.prepareStatement(sql);         
            rs = pst.executeQuery();
            
            if(rs.next()){ 
                String id = rs.getString(1);
                String name = rs.getString(2);
                String spec = rs.getString(3);
                String adress = rs.getString(4);
                String date = rs.getString(5);
               
                
                /*txt_id_doc.setText(id);
                col_sp.setText(name);
                col_id_pac.setText(spec);
                col_date.setText(adress);
                col_time.setText(date);*/
                col_id_doc.setCellValueFactory(new PropertyValueFactory<PRIEM,Integer>(id));
                col_sp.setCellValueFactory(new PropertyValueFactory<PRIEM,String>(name));
                col_id_pac.setCellValueFactory(new PropertyValueFactory<PRIEM,Integer>(spec));
                col_date.setCellValueFactory(new PropertyValueFactory<PRIEM,Date>(adress));
                col_time.setCellValueFactory(new PropertyValueFactory<PRIEM,Time>(date));       
                listM = mysql.getDatauserPR();
                table_users.setItems(listM);
                
                
            }else {
                JOptionPane.showMessageDialog(null, "Invalide Username Or Password");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        UpdateTable();
        search_user();
    }

    /*@FXML
    public void Add_users() {
         conn = mysql.ConnectDb();
        String sql = "insert into priem (id_doctor,special,id_pacient,date_pr,time) values(?,?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id_doc.getText());
            pst.setString(2, txt_sp.getText());
            pst.setString(3, txt_id_pac.getText());
            pst.setString(4, txt_data.getText());
            pst.setString(5, txt_time.getText());
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Users Add succes");
            UpdateTable();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }*/
    
    @FXML
    void getSelected (MouseEvent event){
    index = table_users.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    txt_id_doc.setText(col_id_doc.getCellData(index).toString());
    txt_sp.setText(col_sp.getCellData(index).toString());
    txt_id_pac.setText(col_id_pac.getCellData(index).toString());
    txt_data.setText(col_date.getCellData(index).toString());
    txt_time.setText(col_time.getCellData(index).toString());
           UpdateTable();
        search_user();
    }

    @FXML
    public void Delete() {
        conn = mysql.ConnectDb();
    String sql = "delete from priem where id_doctor = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id_doc.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        UpdateTable();
        search_user();

    }

    @FXML
    public void Edit() {
        try {
            conn = mysql.ConnectDb();
            String value1 = txt_id_doc.getText();
            String value2 = txt_sp.getText();
            String value3 = txt_id_pac.getText();
            String value4 = txt_data.getText();
            String value5 = txt_time.getText();
            String sql = "update priem set id_doctor= '"+value1+"', special='"+value2+"', id_pacient= '"+value3+"', date_pr= '"+value4+"', time= '"+value5+"' where id_doctor='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        UpdateTable();
        search_user();

    }

    @FXML
    public void UpdateTable() {
        col_id_doc.setCellValueFactory(new PropertyValueFactory<PRIEM,Integer>("id_doctor"));
        col_sp.setCellValueFactory(new PropertyValueFactory<PRIEM,String>("special"));
        col_id_pac.setCellValueFactory(new PropertyValueFactory<PRIEM,Integer>("id_pacient"));
        col_date.setCellValueFactory(new PropertyValueFactory<PRIEM,Date>("date_pr"));
        col_time.setCellValueFactory(new PropertyValueFactory<PRIEM,Time>("time"));       
        
        listM = mysql.getDatauserPR();
        table_users.setItems(listM);
        UpdateTable();
        search_user();

    }
    
    void search_user() {          
        col_id_doc.setCellValueFactory(new PropertyValueFactory<PRIEM,Integer>("id_doctor"));
        col_sp.setCellValueFactory(new PropertyValueFactory<PRIEM,String>("special"));
        col_id_pac.setCellValueFactory(new PropertyValueFactory<PRIEM,Integer>("id_pacient"));
        col_date.setCellValueFactory(new PropertyValueFactory<PRIEM,Date>("date_pr"));
        col_time.setCellValueFactory(new PropertyValueFactory<PRIEM,Time>("time"));     
        
        dataList = mysql.getDatauserPR();
        table_users.setItems(dataList);
        FilteredList<PRIEM> filteredData = new FilteredList<>(dataList, b -> true);  
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(person -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;}    
            String lowerCaseFilter = newValue.toLowerCase();
             if (String.valueOf(person.getId_pacient()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }else if (String.valueOf(person.getId_doctor()).toLowerCase().indexOf(lowerCaseFilter) != -1) 
     return true; // Filter matches password
                     
         else  
          return false; // Does not match.
   });
  });  
        SortedList<PRIEM> sortedData = new SortedList<>(filteredData);  
        sortedData.comparatorProperty().bind(table_users.comparatorProperty());  
        table_users.setItems(sortedData);   
    UpdateTable();
        search_user();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
