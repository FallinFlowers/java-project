/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sml
 */
public class Table_TARIFController implements Initializable {

   @FXML
    private TextField filterField;
    
    @FXML
    private TextField txt_tarif;

    @FXML
    private TextField txt_skid;

    @FXML
    private TextField txt_id;

    @FXML
    private TableView<TARIF> table_users;

    @FXML
    private TableColumn<TARIF, Integer> col_id;

    @FXML
    private TableColumn<TARIF, Integer> col_tarif;

    @FXML
    private TableColumn<TARIF, Integer> col_skid;

    @FXML
    private AnchorPane Main_t;
    
    @FXML
    private AnchorPane doc_b;

    @FXML
    private AnchorPane doc_t;
    
    @FXML
    private Button edit;
    

 
    ObservableList<TARIF> listM;
    ObservableList<TARIF> dataList;

    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    
    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<TARIF,Integer>("id_spec"));
        col_tarif.setCellValueFactory(new PropertyValueFactory<TARIF,Integer>("tarif_opl"));
        col_skid.setCellValueFactory(new PropertyValueFactory<TARIF,Integer>("tarif_skidka"));
 
        listM = mysql.getDataTARIF();
        table_users.setItems(listM);
        search_user();
        
    }
    
     @FXML
    void search_user() {          
        col_id.setCellValueFactory(new PropertyValueFactory<TARIF,Integer>("id_spec"));
        col_tarif.setCellValueFactory(new PropertyValueFactory<TARIF,Integer>("tarif_opl"));
        col_skid.setCellValueFactory(new PropertyValueFactory<TARIF,Integer>("tarif_skidka"));
        
        dataList = mysql.getDataTARIF();
        table_users.setItems(dataList);
        FilteredList<TARIF> filteredData = new FilteredList<>(dataList, b -> true);  
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
        SortedList<TARIF> sortedData = new SortedList<>(filteredData);  
        sortedData.comparatorProperty().bind(table_users.comparatorProperty());  
        table_users.setItems(sortedData);     
        
        
    }
    
    /* @FXML
    
    void report(ActionEvent event) {
        conn = mysql.ConnectDb();
        try{
            JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\Sml\\OneDrive\\Документы\\NetBeansProjects\\JavaFXApplication4\\src\\Report\\newReport1.jrxml");
        
            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, conn);
            
            JasperViewer viewer = new JasperViewer(jPrint, false);
            
            viewer.setTitle("MarcoMan Report");
            
            viewer.setVisible(true);
            viewer.show();
            
        }catch(Exception e){}

    }*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
        search_user();
    }    
    
}
