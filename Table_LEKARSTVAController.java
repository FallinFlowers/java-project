/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;

import Table.DOCTORS;
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
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Sml
 */
public class Table_LEKARSTVAController implements Initializable {

    @FXML
    private JFXTextField filterField;
    
    @FXML
    private TextField txt_naimen;

    @FXML
    private TextField txt_izmer;

    @FXML
    private TextField txt_cena;

    @FXML
    private TextField txt_kol;

    @FXML
    private TextField txt_id;

    @FXML
    private TableView<LEKARSTVA> table_users;

    @FXML
    private TableColumn<LEKARSTVA, Integer> col_id;

    @FXML
    private TableColumn<LEKARSTVA, String> col_naimen;

    @FXML
    private TableColumn<LEKARSTVA, String> col_izmer;

    @FXML
    private TableColumn<LEKARSTVA, Integer> col_cena;

    @FXML
    private TableColumn<LEKARSTVA, Integer> col_kol;
    

    @FXML
    private AnchorPane Main_t;
    
    @FXML
    private AnchorPane doc_b;

    @FXML
    private AnchorPane doc_t;
    
    @FXML
    private Button edit;
    

 
    ObservableList<LEKARSTVA> listM;
    ObservableList<LEKARSTVA> dataList;
    
   
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
   
    public void Add_users (){    
        conn = mysql.ConnectDb();
        String sql = "insert into lekarstva (naimenovanie,izmerenie,cena,kolichestvo)values(?,?,?,?,)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_naimen.getText());
            pst.setString(2, txt_izmer.getText());
            pst.setString(3, txt_cena.getText());
            pst.setString(4, txt_kol.getText());
            pst.execute();
            
            //JOptionPane.showMessageDialog(null, "Users Add succes");
            UpdateTable();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    

    //////// methode select users ///////
    @FXML
    void getSelected (MouseEvent event){
    index = table_users.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    txt_id.setText(col_id.getCellData(index).toString());
    txt_naimen.setText(col_naimen.getCellData(index).toString());
    txt_izmer.setText(col_izmer.getCellData(index).toString());
    txt_cena.setText(col_cena.getCellData(index).toString());
    txt_kol.setText(col_kol.getCellData(index).toString());
    
    }

    public void Edit (){   
        try {
            conn = mysql.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_naimen.getText();
            String value3 = txt_izmer.getText();
            String value4 = txt_cena.getText();
            String value5 = txt_kol.getText();
         
            String sql = "update lekarstva set id_lekarstva= '"+value1+"',naimenovanie= '"+value2+"',izmerenie= '"+
                    value3+"',cena= '"+value4+"',kolichestvo= '"+value5+"' where id_lekarstva='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            ///JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
            search_user();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public void Delete(){
    conn = mysql.ConnectDb();
    String sql = "delete from doctors where id_doctor = ?";
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

    
    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<LEKARSTVA,Integer>("id_lekarstva"));
        col_naimen.setCellValueFactory(new PropertyValueFactory<LEKARSTVA,String>("naimenovanie"));
        col_izmer.setCellValueFactory(new PropertyValueFactory<LEKARSTVA,String>("izmerenie"));
        col_cena.setCellValueFactory(new PropertyValueFactory<LEKARSTVA,Integer>("cena"));
        col_kol.setCellValueFactory(new PropertyValueFactory<LEKARSTVA,Integer>("kolichestvo"));
        
        listM = mysql.getDataLEKARSTVA();
        table_users.setItems(listM);
    }
    
     @FXML
    void search_user() {          
        col_id.setCellValueFactory(new PropertyValueFactory<LEKARSTVA,Integer>("id_lekarstva"));
        col_naimen.setCellValueFactory(new PropertyValueFactory<LEKARSTVA,String>("naimenovanie"));
        col_izmer.setCellValueFactory(new PropertyValueFactory<LEKARSTVA,String>("izmerenie"));
        col_cena.setCellValueFactory(new PropertyValueFactory<LEKARSTVA,Integer>("cena"));
        col_kol.setCellValueFactory(new PropertyValueFactory<LEKARSTVA,Integer>("kolichestvo"));
        
        dataList = mysql.getDataLEKARSTVA();
        table_users.setItems(dataList);
        FilteredList<LEKARSTVA> filteredData = new FilteredList<>(dataList, b -> true);  
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(person -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;}    
            String lowerCaseFilter = newValue.toLowerCase();
             if (person.getNaimenovanie().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } 
    else if (String.valueOf(person.getId_lekarstva()).indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
                                
         else  
          return false; // Does not match.
   });
  });  
        SortedList<LEKARSTVA> sortedData = new SortedList<>(filteredData);  
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
