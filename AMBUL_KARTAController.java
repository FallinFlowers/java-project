/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;

import Table.AMBUL_KARTA;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sml
 */
public class AMBUL_KARTAController implements Initializable {

     @FXML
    private TableColumn<AMBUL_KARTA, Date> col_date;

    @FXML
    private TableColumn<AMBUL_KARTA, String> col_diag;

    @FXML
    private TableColumn<AMBUL_KARTA, Integer> col_id;

    @FXML
    private TableColumn<AMBUL_KARTA, String> col_naz;

    @FXML
    private TableView<AMBUL_KARTA> table_users;

     @FXML
    private TextArea txt_naz;


    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_date;

    @FXML
    private TextField txt_diag;

    @FXML
    private JFXTextField filterField;
    
    ObservableList<AMBUL_KARTA> listM;
    ObservableList<AMBUL_KARTA> dataList;
      
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    public void Add_users() {
    conn = mysql.ConnectDb();
        String sql = "insert into ambul_karta (id_pacient,date_pr,diagnoz,naznachenie) values(?,?,?,? )";
        //LocalDate data=txt_date.getValueOf(); 
       

        
        
        try {
            pst = conn.prepareStatement(sql);
           
            pst.setString(1, txt_id.getText());
            pst.setString(2, txt_date.getText());
            pst.setString(3, txt_diag.getText());
            pst.setString(4, txt_naz.getText());
            pst.execute();
            
            
            //JOptionPane.showMessageDialog(null, "Users Add succes");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        

    }
    
    @FXML
    void getSelected (MouseEvent event){
    index = table_users.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    txt_id.setText(col_id.getCellData(index).toString());
    txt_date.setText(col_date.getCellData(index).toString());
    txt_diag.setText(col_diag.getCellData(index).toString());
    txt_naz.setText(col_naz.getCellData(index).toString());    
    }

    @FXML
    public void Delete() {
        conn = mysql.ConnectDb();
    String sql = "delete from ambul_karta where id_pacient = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.execute();
            //JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    @FXML
    public void Edit() {
        try {
            conn = mysql.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_date.getText();
            String value3 = txt_diag.getText();
            String value4 = txt_naz.getText();
            String sql = "update ambul_karta set id_pacient= '"+value1+"', date_pr='"+value2+"', diagnoz= '"+value3+"', naznachenie= '"+value4+"' where id_pacient='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            //JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        

    }

    @FXML
    public void UpdateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<AMBUL_KARTA,Integer>("id_pacient"));
        col_date.setCellValueFactory(new PropertyValueFactory<AMBUL_KARTA,Date>("date_pr"));
        col_diag.setCellValueFactory(new PropertyValueFactory<AMBUL_KARTA,String>("diagnoz"));
        col_naz.setCellValueFactory(new PropertyValueFactory<AMBUL_KARTA,String>("naznachenie"));  
        
        listM = mysql.getDataAMBUL_KARTA();
        table_users.setItems(listM);
        search_user();

    }
    
    void search_user() {          
        col_id.setCellValueFactory(new PropertyValueFactory<AMBUL_KARTA,Integer>("id_pacient"));
        col_date.setCellValueFactory(new PropertyValueFactory<AMBUL_KARTA,Date>("date_pr"));
        col_diag.setCellValueFactory(new PropertyValueFactory<AMBUL_KARTA,String>("diagnoz"));
        col_naz.setCellValueFactory(new PropertyValueFactory<AMBUL_KARTA,String>("naznachenie"));    
        
        dataList = mysql.getDataAMBUL_KARTA();
        table_users.setItems(dataList);
        FilteredList<AMBUL_KARTA> filteredData = new FilteredList<>(dataList, b -> true);  
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(person -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;}    
            String lowerCaseFilter = newValue.toLowerCase();
             if (person.getDiagnoz().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    // Filter matches password
    }else if (String.valueOf(person.getId_pacient()).toLowerCase().indexOf(lowerCaseFilter) != -1) 
     return true; // Filter matches password
                     
         else  
          return false; // Does not match.
   });
  });  
        SortedList<AMBUL_KARTA> sortedData = new SortedList<>(filteredData);  
        sortedData.comparatorProperty().bind(table_users.comparatorProperty());  
        table_users.setItems(sortedData); 
        
      
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
        search_user();
    }    
    
}
