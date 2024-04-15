/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;

import Table.PACIENT;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.ComboBox;
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
public class Table_PACIENTSController1 implements Initializable {

    @FXML
    private TableColumn<PACIENT, String> col_adr;

    @FXML
    private TableColumn<PACIENT,Date> col_date;

    @FXML
    private TableColumn<PACIENT, String> col_email;

    @FXML
    private TableColumn<PACIENT, Integer> col_id;

    @FXML
    private TableColumn<PACIENT, String> col_name;

    @FXML
    private TableColumn<PACIENT, Integer> col_tel;

    @FXML
    private TableColumn<PACIENT, String> col_type;
    
     @FXML
    private TableColumn<PACIENT, String> col_lgot;

     @FXML
    private TextField filterField;

    
    @FXML
    private TableView<PACIENT> table_users;

   
    
    ObservableList<PACIENT> listM;
    ObservableList<PACIENT> dataList;
    
   
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    

    @FXML
    public void UpdateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<PACIENT,Integer>("id_pacient"));
        col_name.setCellValueFactory(new PropertyValueFactory<PACIENT,String>("pacient_name"));
        col_date.setCellValueFactory(new PropertyValueFactory<PACIENT,Date>("date"));
        col_type.setCellValueFactory(new PropertyValueFactory<PACIENT,String>("pol"));
        col_adr.setCellValueFactory(new PropertyValueFactory<PACIENT,String>("adress"));
        col_tel.setCellValueFactory(new PropertyValueFactory<PACIENT,Integer>("telephone"));
        col_email.setCellValueFactory(new PropertyValueFactory<PACIENT,String>("email"));
        col_lgot.setCellValueFactory(new PropertyValueFactory<PACIENT,String>("lgota"));
        listM = mysql.getDataPAC();
        table_users.setItems(listM);
     
        search_user();
        
    }
    
    @FXML
    void search_user() {          
        col_id.setCellValueFactory(new PropertyValueFactory<PACIENT,Integer>("id_pacient"));
        col_name.setCellValueFactory(new PropertyValueFactory<PACIENT,String>("pacient_name"));
        col_date.setCellValueFactory(new PropertyValueFactory<PACIENT,Date>("date"));
        col_type.setCellValueFactory(new PropertyValueFactory<PACIENT,String>("pol"));
        col_adr.setCellValueFactory(new PropertyValueFactory<PACIENT,String>("adress"));
        col_tel.setCellValueFactory(new PropertyValueFactory<PACIENT,Integer>("telephone"));
        col_email.setCellValueFactory(new PropertyValueFactory<PACIENT,String>("email"));
        col_lgot.setCellValueFactory(new PropertyValueFactory<PACIENT,String>("lgota"));
        
        dataList = mysql.getDataPAC();
        table_users.setItems(dataList);
        FilteredList<PACIENT> filteredData = new FilteredList<>(dataList, b -> true);  
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(person -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;}    
            String lowerCaseFilter = newValue.toLowerCase();
             if (person.getPacient_name().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } 
    else if (String.valueOf(person.getId_pacient()).indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
                                
         else  
          return false; // Does not match.
   });
  });  
        SortedList<PACIENT> sortedData = new SortedList<>(filteredData);  
        sortedData.comparatorProperty().bind(table_users.comparatorProperty());  
        table_users.setItems(sortedData);      
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
