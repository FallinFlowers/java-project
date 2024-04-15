/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;

import Table.PRIEM;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
public class PRIEMController1 implements Initializable {

    @FXML
    private TableColumn<VIEW, Date> col_date;

    @FXML
    private TableColumn<VIEW, Integer> col_id_doc;

    @FXML
    private TableColumn<VIEW, Integer> col_id_pac;

    @FXML
    private TableColumn<VIEW, String> col_sp;

    @FXML
    private TableColumn<VIEW, Time> col_time;

    @FXML
    private TextField filterField;
    
    @FXML
    private TableView<VIEW> table_users;
    
    @FXML
    private TableColumn<VIEW, String> col_doc;

    @FXML
    private TableColumn<VIEW, String> col_pac;
    
    @FXML
    private TextField txt_nd;

    @FXML
    private TextField txt_np;


    @FXML
    private TextField txt_data;

    @FXML
    private TextField txt_id_doc;

    @FXML
    private TextField txt_id_pac;

    @FXML
    private TextField txt_sp;

    @FXML
    private TextField txt_time;
    
    @FXML
    private Button open;
    
    ObservableList<VIEW> listM;
    ObservableList<VIEW> dataList;
    
   
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
   

    @FXML
    public void Add_users() {
         conn = mysql.ConnectDb();
        String sql = "insert into priem (id_doctor,special,id_pacient,date_pr,time) values(?,?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id_doc.getText());
            pst.setString(2, txt_sp.getText());
            pst.setString(3, txt_id_pac.getText());
            pst.setString(4, txt_data.getText());
            pst.setString(5, txt_time.getText());
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
    txt_id_doc.setText(col_id_doc.getCellData(index).toString());
    txt_nd.setText(col_doc.getCellData(index).toString());
    txt_sp.setText(col_sp.getCellData(index).toString());
    txt_id_pac.setText(col_id_pac.getCellData(index).toString());
    txt_np.setText(col_pac.getCellData(index).toString());
    txt_data.setText(col_date.getCellData(index).toString());
    txt_time.setText(col_time.getCellData(index).toString());
    }

    @FXML
    public void Delete() {
        conn = mysql.ConnectDb();
    String sql = "delete from priem where id_doctor = ? and id_pacient = ? and date_pr = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id_doc.getText());
            pst.setString(2, txt_id_pac.getText());
            pst.setString(3, txt_data.getText());
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
            String value1 = txt_id_doc.getText();
            String value2 = txt_sp.getText();
            String value3 = txt_id_pac.getText();
            String value4 = txt_data.getText();
            String value5 = txt_time.getText();
            String sql = "update priem set id_doctor= '"+value1+"', special='"+value2+"', id_pacient= '"+value3+"', date_pr= '"+value4+"', time= '"+value5+"' where id_doctor='"+value1+"' and "
                    + "id_pacient= '"+value3+"' and date_pr= '"+value4+"'" ;
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
        col_id_doc.setCellValueFactory(new PropertyValueFactory<VIEW,Integer>("id_doctor"));
        col_doc.setCellValueFactory(new PropertyValueFactory<VIEW,String>("doctor_name"));
        col_sp.setCellValueFactory(new PropertyValueFactory<VIEW,String>("special"));
        col_id_pac.setCellValueFactory(new PropertyValueFactory<VIEW,Integer>("id_pacient"));
        col_pac.setCellValueFactory(new PropertyValueFactory<VIEW,String>("pacient_name"));
        col_date.setCellValueFactory(new PropertyValueFactory<VIEW,Date>("date_pr"));
        col_time.setCellValueFactory(new PropertyValueFactory<VIEW,Time>("time"));       
        
        listM = mysql.getDataVIEW();
        table_users.setItems(listM);
    
        search_user();

    }
    
    void search_user() {          
        col_id_doc.setCellValueFactory(new PropertyValueFactory<VIEW,Integer>("id_doctor"));
        col_doc.setCellValueFactory(new PropertyValueFactory<VIEW,String>("doctor_name"));
        col_sp.setCellValueFactory(new PropertyValueFactory<VIEW,String>("special"));
        col_id_pac.setCellValueFactory(new PropertyValueFactory<VIEW,Integer>("id_pacient"));
        col_pac.setCellValueFactory(new PropertyValueFactory<VIEW,String>("pacient_name"));
        col_date.setCellValueFactory(new PropertyValueFactory<VIEW,Date>("date_pr"));
        col_time.setCellValueFactory(new PropertyValueFactory<VIEW,Time>("time"));      
        
        dataList = mysql.getDataVIEW();
        table_users.setItems(dataList);
        FilteredList<VIEW> filteredData = new FilteredList<>(dataList, b -> true);  
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(person -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;}    
            String lowerCaseFilter = newValue.toLowerCase();
             if (String.valueOf(person.getId_pacient()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }else if (String.valueOf(person.getId_doctor()).toLowerCase().indexOf(lowerCaseFilter) != -1) 
     return true; // Filter matches password
                     
         else  
          return false; // Does not match.
   });
  });  
        SortedList<VIEW> sortedData = new SortedList<>(filteredData);  
        sortedData.comparatorProperty().bind(table_users.comparatorProperty());  
        table_users.setItems(sortedData);    
        
       
    }
    
    @FXML
    
    void report(ActionEvent event) {
        conn = mysql.ConnectDb();
        try{
            JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\Sml\\OneDrive\\Документы\\NetBeansProjects\\JavaFXApplication4\\src\\Report\\report1.jrxml");
        
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
    }    
    
}
