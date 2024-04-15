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
public class Table_NAZNACHENIEController implements Initializable {

    @FXML
    private JFXTextField filterField;
    
    @FXML
    private TextField txt_id_pac;

    @FXML
    private TextField txt_id_lek;

    @FXML
    private TextField txt_kol;

    @FXML
    private TextField txt_date;

    @FXML
    private TextField txt_id_doc;

    @FXML
    private TableView<NAZNACHENIE> table_users;

    @FXML
    private TableColumn<NAZNACHENIE, Integer> col_id_doc;

    @FXML
    private TableColumn<NAZNACHENIE, Integer> col_id_pac;

    @FXML
    private TableColumn<NAZNACHENIE, Integer> col_id_lek;

    @FXML
    private TableColumn<NAZNACHENIE, Integer> col_kol;

    @FXML
    private TableColumn<NAZNACHENIE, Date> col_date;

    @FXML
    private AnchorPane Main_t;
    
    @FXML
    private AnchorPane doc_b;

    @FXML
    private AnchorPane doc_t;
    
    @FXML
    private Button edit;
    

 
    ObservableList<NAZNACHENIE> listM;
    ObservableList<NAZNACHENIE> dataList;
    
   
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
   
    public void Add_users (){    
        conn = mysql.ConnectDb();
        String sql = "insert into naznachenie (id_pacient,id_doctor,id_lekarstva,kolichestvo,date)values(?,?,?,?,?,)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id_pac.getText());
            pst.setString(2, txt_id_doc.getText());
            pst.setString(3, txt_id_lek.getText());
            pst.setString(4, txt_kol.getText());
            pst.setString(5, txt_date.getText());
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Users Add succes");
            UpdateTable();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        UpdateTable();
        search_user();
    }
    

    //////// methode select users ///////
    @FXML
    void getSelected (MouseEvent event){
    index = table_users.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    txt_id_pac.setText(col_id_doc.getCellData(index).toString());
    txt_id_doc.setText(col_id_doc.getCellData(index).toString());
    txt_id_lek.setText(col_id_lek.getCellData(index).toString());
    txt_kol.setText(col_kol.getCellData(index).toString());
    txt_date.setText(col_date.getCellData(index).toString());
    UpdateTable();
        search_user();
    }

    public void Edit (){   
        try {
            conn = mysql.ConnectDb();
            String value1 = txt_id_pac.getText();
            String value2 = txt_id_doc.getText();
            String value3 = txt_id_lek.getText();
            String value4 = txt_kol.getText();
            String value5 = txt_date.getText();
         
            String sql = "update naznachenie set id_pacient= '"+value1+"',id_doctor= '"+value2+"',id_lekarstva= '"+
                    value3+"',kolichestvo= '"+value4+"',date= '"+value5+"' where id_pacient='"+value1+"' ";
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
    
    public void Delete(){
    conn = mysql.ConnectDb();
    String sql = "delete from doctors where id_doctor = ? and id_pacient = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id_doc.getText());
            pst.setString(1, txt_id_pac.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    UpdateTable();
        search_user();
    }

    
    public void UpdateTable(){
        col_id_pac.setCellValueFactory(new PropertyValueFactory<NAZNACHENIE,Integer>("id_pacient"));
        col_id_doc.setCellValueFactory(new PropertyValueFactory<NAZNACHENIE,Integer>("id_doctor"));
        col_id_lek.setCellValueFactory(new PropertyValueFactory<NAZNACHENIE,Integer>("id_lekarstva"));
        col_kol.setCellValueFactory(new PropertyValueFactory<NAZNACHENIE,Integer>("kolichesvo"));
        col_date.setCellValueFactory(new PropertyValueFactory<NAZNACHENIE,Date>("date"));
        
        listM = mysql.getDataNAZNACHENIE();
        table_users.setItems(listM);
    
        search_user();
    }
    
     @FXML
    void search_user() {          
        col_id_pac.setCellValueFactory(new PropertyValueFactory<NAZNACHENIE,Integer>("id_pacient"));
        col_id_doc.setCellValueFactory(new PropertyValueFactory<NAZNACHENIE,Integer>("id_doctor"));
        col_id_lek.setCellValueFactory(new PropertyValueFactory<NAZNACHENIE,Integer>("id_lekarstva"));
        col_kol.setCellValueFactory(new PropertyValueFactory<NAZNACHENIE,Integer>("kolichesvo"));
        col_date.setCellValueFactory(new PropertyValueFactory<NAZNACHENIE,Date>("date"));
        
        dataList = mysql.getDataNAZNACHENIE();
        table_users.setItems(dataList);
        FilteredList<NAZNACHENIE> filteredData = new FilteredList<>(dataList, b -> true);  
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(person -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;}    
            String lowerCaseFilter = newValue.toLowerCase();
             if (String.valueOf(person.getId_doctor()).indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } 
    else if (String.valueOf(person.getId_pacient()).indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
                                
         else  
          return false; // Does not match.
   });
  });  
        SortedList<NAZNACHENIE> sortedData = new SortedList<>(filteredData);  
        sortedData.comparatorProperty().bind(table_users.comparatorProperty());  
        table_users.setItems(sortedData);  
        UpdateTable();
        
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
