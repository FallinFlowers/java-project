/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Sml
 */
public class Diagnoz_TABLEController implements Initializable {

    @FXML
    private JFXTextField filterField;
    
    @FXML
    private TextField txt_tarif;

    @FXML
    private TextField txt_skid;

    @FXML
    private TextField txt_id;

    @FXML
    private TableView<DIAGNOZ> table_users;

    @FXML
    private TableColumn<DIAGNOZ, Integer> col_id;

    @FXML
    private TableColumn<DIAGNOZ, String> col_tarif;

    @FXML
    private TableColumn<DIAGNOZ, String> col_skid;

    @FXML
    private AnchorPane Main_t;
    
    @FXML
    private AnchorPane doc_b;

    @FXML
    private AnchorPane doc_t;
    
    @FXML
    private Button edit;
    

 
    ObservableList<DIAGNOZ> listM;
    ObservableList<DIAGNOZ> dataList;

    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    
    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<DIAGNOZ,Integer>("id_diagnoz"));
        //col_tarif.setCellValueFactory(new PropertyValueFactory<DIAGNOZ,String>("naimenovanie"));
        //col_skid.setCellValueFactory(new PropertyValueFactory<DIAGNOZ,String>("link"));
 
        listM = mysql.getDataDIAGNOZ();
        table_users.setItems(listM);
        
        
    }
    
    @FXML
    void openLink(MouseEvent event) {
        index = table_users.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    

    }
    
     @FXML
    void search_user() {          
        col_id.setCellValueFactory(new PropertyValueFactory<DIAGNOZ,Integer>("id_diagnoz"));
        col_tarif.setCellValueFactory(new PropertyValueFactory<DIAGNOZ,String>("naimenovanie"));
        col_skid.setCellValueFactory(new PropertyValueFactory<DIAGNOZ,String>("link"));
        
        dataList = mysql.getDataDIAGNOZ();
        table_users.setItems(dataList);
        FilteredList<DIAGNOZ> filteredData = new FilteredList<>(dataList, b -> true);  
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(person -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;}    
            String lowerCaseFilter = newValue.toLowerCase();
             if (String.valueOf(person.getId_diagnoz()).indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } 
                                
         else  
          return false; // Does not match.
   });
  });  
        SortedList<DIAGNOZ> sortedData = new SortedList<>(filteredData);  
        sortedData.comparatorProperty().bind(table_users.comparatorProperty());  
        table_users.setItems(sortedData);     
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
    }
}
