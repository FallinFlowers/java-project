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
public class Table_PACIENTSController implements Initializable {

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
    private JFXTextField filterField;

    
    @FXML
    private TableView<PACIENT> table_users;

    @FXML
    private TextField txt_adr;

    @FXML
    private TextField txt_date;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_tel;

    @FXML
    private ComboBox txt_lgot;
     
    @FXML
    private ComboBox type1;
    
    ObservableList<PACIENT> listM;
    ObservableList<PACIENT> dataList;
    
   
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    public void Add_users() {
        conn = mysql.ConnectDb();
        String sql = "insert into pacient (pacient_name,date,pol,adress,telephone,email,lgota)values(?,?,?,?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_name.getText());
            pst.setString(2, txt_date.getText());
            pst.setString(3, type1.getValue().toString());
            pst.setString(4, txt_adr.getText());
            pst.setString(5, txt_tel.getText());
            pst.setString(6, txt_email.getText());
            pst.setString(7, txt_lgot.getValue().toString());
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
    txt_date.setText(col_date.getCellData(index).toString());
    type1.setValue(col_type.getCellData(index).toString());
    txt_adr.setText(col_adr.getCellData(index).toString());
    txt_tel.setText(col_tel.getCellData(index).toString());
    txt_email.setText(col_email.getCellData(index).toString());
    txt_lgot.setValue(col_lgot.getCellData(index).toString());
    }

    @FXML
    public void Delete() {
        conn = mysql.ConnectDb();
    String sql = "delete from pacient where id_pacient = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.execute();
            //JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
       

    }

    @FXML
    public void Edit() {
        try {
            conn = mysql.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_name.getText();
            String value3 = txt_date.getText();
            String value4 = type1.getValue().toString();
            String value5 = txt_adr.getText();
            String value6 = txt_tel.getText();
            String value7 = txt_email.getText();
            String value8 = txt_lgot.getValue().toString();
            String sql = "update pacient set id_pacient= '"+value1+"',pacient_name= '"+value2+"',date= '"+
                    value3+"',pol= '"+value4+"',adress= '"+value5+"',telephone= '"+value6+"',email= '"+value7+"',lgota= '"+value8+"' where id_pacient='" +value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            //JOptionPane.showMessageDialog(null,"Изменено");
            //UpdateTable();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        UpdateTable();
        search_user();
        

    }

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
        UpdateTable();
        search_user();
         type1.getItems().addAll("муж","жен");
         txt_lgot.getItems().addAll("true","false");
    }    
    
}
