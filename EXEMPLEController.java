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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.apache.poi.ss.formula.functions.T;

/**
 * FXML Controller class
 *
 * @author Sml
 */
public class EXEMPLEController implements Initializable {

    
    /*@FXML
    private TableColumn<DIAGNOZ, Integer> col_id_doc;

    @FXML
    private TableColumn<DIAGNOZ, Hyperlink> col_id_pac;

    @FXML
    private TableColumn<DIAGNOZ, String> col_sp;


    @FXML
    private TextField filterField;
    
    @FXML
    private TableView<DIAGNOZ> table_users;

    @FXML
    private TextField txt_id_doc;

    @FXML
    private TextField txt_id_pac;

    @FXML
    private TextField txt_sp;*/
    
    /*col_id_pac = new TableColumn<>("Address");
urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
urlColumn.setCellFactory(new HyperlinkCell());*/

    
    /*ObservableList<DIAGNOZ> listM;
    ObservableList<DIAGNOZ> dataList;
    
   
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;*/

    
    
    /*@FXML
    void getSelected (MouseEvent event){
    index = table_users.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    //txt_id_doc.setText(col_id_doc.getCellData(index).toString());
    //txt_sp.setText(col_sp.getCellData(index).toString());
    //txt_id_pac.setText(col_id_pac.getCellData(index).toString());
    table_users.setRowFactory(tv -> {
        TableRow<> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && (!row.isEmpty())) {
              
            }
        });
        return row;
    })
           
    }*/

    

    /*@FXML
    public void UpdateTable() {
        col_id_doc.setCellValueFactory(new PropertyValueFactory<DIAGNOZ,Integer>("id_diagnoz"));
        col_sp.setCellValueFactory(new PropertyValueFactory<DIAGNOZ,String>("naimenovanie"));
        col_id_pac.setCellValueFactory(new PropertyValueFactory<DIAGNOZ,Hyperlink>("link"));      
        
        listM = mysql.getDatauserDIAGNOZ();
        table_users.setItems(listM);
        
        

    }
    
    void search_user() {          
        col_id_doc.setCellValueFactory(new PropertyValueFactory<DIAGNOZ,Integer>("id_diagnoz"));
        col_sp.setCellValueFactory(new PropertyValueFactory<DIAGNOZ,String>("naimenovanie"));
        col_id_pac.setCellValueFactory(new PropertyValueFactory<DIAGNOZ,Hyperlink>("link"));
        
        
        dataList = mysql.getDatauserDIAGNOZ();
        table_users.setItems(dataList);
        FilteredList<DIAGNOZ> filteredData = new FilteredList<>(dataList, b -> true);  
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(person -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;}    
            String lowerCaseFilter = newValue.toLowerCase();
             if (String.valueOf(person.getId_diagnoz()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }else if (person.getNaimenovanie().toLowerCase().indexOf(lowerCaseFilter) != -1) 
     return true; // Filter matches password
                     
         else  
          return false; // Does not match.
   });
  });  
        SortedList<DIAGNOZ> sortedData = new SortedList<>(filteredData);  
        sortedData.comparatorProperty().bind(table_users.comparatorProperty());  
        table_users.setItems(sortedData);     }*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
