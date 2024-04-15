/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;


import Table.DOCTORS;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
public class TableController implements Initializable {

    @FXML
    private JFXTextField filterField;
    
    @FXML
    private TableColumn<DOCTORS, Date> col_date;

    @FXML
    private TableColumn<DOCTORS, Integer> col_id;
    
    @FXML
    private TableColumn<DOCTORS, String> col_ad;

    @FXML
    private TableColumn<DOCTORS, String> col_log;

    @FXML
    private TableColumn<DOCTORS, String> col_pass;

    @FXML
    private TableColumn<DOCTORS, Integer> col_spec;

    @FXML
    private TableColumn<DOCTORS, Integer> col_tel;

    @FXML
    private TableColumn<DOCTORS, String> col_username;

    @FXML
    private TableView<DOCTORS> table_users;

    @FXML
    private TextField txt_ad;

    @FXML
    private TextField txt_date;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_log;

    @FXML
    private TextField txt_pass;

    @FXML
    private TextField txt_spec;

    @FXML
    private TextField txt_tel;

    @FXML
    private TextField txt_username;
    
    @FXML
    private Menu Main_id;

    @FXML
    private AnchorPane Main_t;
    
    @FXML
    private AnchorPane doc_b;

    @FXML
    private AnchorPane doc_t;
    
    @FXML
    private Button edit;

 
    ObservableList<DOCTORS> listM;
    ObservableList<DOCTORS> dataList;
    
   
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    


    /**
     * Initializes the controller class.
     */
    public void Add_users (){    
        conn = mysql.ConnectDb();
        String sql = "insert into doctors (doctor_name,id_spec,adress,telephone, date, login, password)values(?,?,?,?,?,MD5(?),MD5(?) )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_username.getText());
            pst.setString(2, txt_spec.getText());
            pst.setString(3, txt_ad.getText());
            pst.setString(4, txt_tel.getText());
            pst.setString(5, txt_date.getText());
            pst.setString(6, txt_log.getText());
            pst.setString(7, txt_pass.getText());
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
    txt_username.setText(col_username.getCellData(index).toString());
    txt_spec.setText(col_spec.getCellData(index).toString());
    txt_ad.setText(col_ad.getCellData(index).toString());
    txt_tel.setText(col_tel.getCellData(index).toString());
    txt_date.setText(col_date.getCellData(index).toString());
    txt_log.setText(col_log.getCellData(index).toString());
    txt_pass.setText(col_pass.getCellData(index).toString());
   
    }

    public void Edit (){   
        try {
            conn = mysql.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_username.getText();
            String value3 = txt_spec.getText();
            String value4 = txt_ad.getText();
            String value5 = txt_tel.getText();
            String value6 = txt_date.getText();
            String value7 = txt_log.getText();
            String value8 = txt_pass.getText();
            String sql = "update doctors set id_doctor= '"+value1+"',doctor_name= '"+value2+"',id_spec= '"+
                    value3+"',adress= '"+value4+"',telephone= '"+value5+"',date= '"+value6+"',login= MD5('"+value7+"'),password= MD5('"+value8+"') where id_doctor='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            //JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    @FXML
    void spec (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Table_SPECIAL.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                //((Node)(event.getSource())).getScene().getWindow().hide();

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
        col_id.setCellValueFactory(new PropertyValueFactory<DOCTORS,Integer>("id_doctor"));
        col_username.setCellValueFactory(new PropertyValueFactory<DOCTORS,String>("doctor_name"));
        col_spec.setCellValueFactory(new PropertyValueFactory<DOCTORS,Integer>("id_spec"));
        col_ad.setCellValueFactory(new PropertyValueFactory<DOCTORS,String>("adress"));
        col_tel.setCellValueFactory(new PropertyValueFactory<DOCTORS,Integer>("telephone"));
        col_date.setCellValueFactory(new PropertyValueFactory<DOCTORS,Date>("date"));
        col_log.setCellValueFactory(new PropertyValueFactory<DOCTORS,String>("login"));
        col_pass.setCellValueFactory(new PropertyValueFactory<DOCTORS,String>("password"));
        
        listM = mysql.getDataDOCTORS();
        table_users.setItems(listM);
 
        search_user();
    }
    
     @FXML
    void search_user() {          
        col_id.setCellValueFactory(new PropertyValueFactory<DOCTORS,Integer>("id_doctor"));
        col_username.setCellValueFactory(new PropertyValueFactory<DOCTORS,String>("doctor_name"));
        col_spec.setCellValueFactory(new PropertyValueFactory<DOCTORS,Integer>("id_spec"));
        col_ad.setCellValueFactory(new PropertyValueFactory<DOCTORS,String>("adress"));
        col_tel.setCellValueFactory(new PropertyValueFactory<DOCTORS,Integer>("telephone"));
        col_date.setCellValueFactory(new PropertyValueFactory<DOCTORS,Date>("date"));
        col_log.setCellValueFactory(new PropertyValueFactory<DOCTORS,String>("login"));
        col_pass.setCellValueFactory(new PropertyValueFactory<DOCTORS,String>("password"));
        
        dataList = mysql.getDataDOCTORS();
        table_users.setItems(dataList);
        FilteredList<DOCTORS> filteredData = new FilteredList<>(dataList, b -> true);  
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(person -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;}    
            String lowerCaseFilter = newValue.toLowerCase();
             if (person.getDoctor_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }else if (String.valueOf(person.getId_spec()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    else if (String.valueOf(person.getId_doctor()).indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
                                
         else  
          return false; // Does not match.
   });
  });  
        SortedList<DOCTORS> sortedData = new SortedList<>(filteredData);  
        sortedData.comparatorProperty().bind(table_users.comparatorProperty());  
        table_users.setItems(sortedData);
        
  
    }
    
     @FXML
    
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

    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    UpdateTable();
    search_user();
    // Code Source in description
    } 

        
    
}
