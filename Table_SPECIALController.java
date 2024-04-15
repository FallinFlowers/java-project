/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Sml
 */
public class Table_SPECIALController implements Initializable {

   @FXML
    private TextField filterField;

    @FXML
    private TableView<SPECIAL> table_users;

    @FXML
    private TableColumn<SPECIAL, Integer> col_id;

    @FXML
    private TableColumn<SPECIAL, String> col_naim;

   
    ObservableList<SPECIAL> listM;
    ObservableList<SPECIAL> dataList;

    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    
    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<SPECIAL,Integer>("id_spec"));
        col_naim.setCellValueFactory(new PropertyValueFactory<SPECIAL,String>("naimenovanie"));
 
        listM = mysql.getDataSPECIAL();
        table_users.setItems(listM);
        search_user();
    }
    
     @FXML
    void search_user() {          
        col_id.setCellValueFactory(new PropertyValueFactory<SPECIAL,Integer>("id_spec"));
        col_naim.setCellValueFactory(new PropertyValueFactory<SPECIAL,String>("naimenovanie"));
        
        dataList = mysql.getDataSPECIAL();
        table_users.setItems(dataList);
        FilteredList<SPECIAL> filteredData = new FilteredList<>(dataList, b -> true);  
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(person -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;}    
            String lowerCaseFilter = newValue.toLowerCase();
             if (String.valueOf(person.getId_spec()).indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } 
                                
         else  
          return false; // Does not match.
   });
  });  
        SortedList<SPECIAL> sortedData = new SortedList<>(filteredData);  
        sortedData.comparatorProperty().bind(table_users.comparatorProperty());  
        table_users.setItems(sortedData); 
       
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       UpdateTable();
        search_user();
    }    
    
}
