/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
public class Analyz_REZController implements Initializable {

    /*@FXML
    private JFXTextField filterField;*/
    
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
    private TextField filterField;

    @FXML
    private TableView<Analyz_REZ> table_users;

    @FXML
    private TableColumn<Analyz_REZ, String> col_id;

    @FXML
    private TableColumn<Analyz_REZ, String> col_naimen;

    @FXML
    private TableColumn<Analyz_REZ, String> col_izmer;

    @FXML
    private TableColumn<Analyz_REZ, String> col_cena;

    @FXML
    private TableColumn<Analyz_REZ, String> col_kol;
    

    @FXML
    private AnchorPane Main_t;
    
    @FXML
    private AnchorPane doc_b;

    @FXML
    private AnchorPane doc_t;
    
    @FXML
    private Button edit;
    

 
    ObservableList<Analyz_REZ> listM;
    ObservableList<Analyz_REZ> dataList;
    
   
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    @FXML
    void analiz(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("Analiz.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                //((Node)(event.getSource())).getScene().getWindow().hide();

    }
   
    /*@FXML
    void getSelected (MouseEvent event){
    index = table_users.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    txt_id.setText(col_id.getCellData(index).toString());
    txt_naimen.setText(col_naimen.getCellData(index).toString());
    txt_cena.setText(col_cena.getCellData(index).toString());
    txt_izmer.setText(col_izmer.getCellData(index).toString());
    txt_kol.setText(col_kol.getCellData(index).toString());
    
    }*/

    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Analyz_REZ,String>("id_pacient"));
        col_naimen.setCellValueFactory(new PropertyValueFactory<Analyz_REZ,String>("research"));
        col_cena.setCellValueFactory(new PropertyValueFactory<Analyz_REZ,String>("result"));
        col_izmer.setCellValueFactory(new PropertyValueFactory<Analyz_REZ,String>("metering"));
        col_kol.setCellValueFactory(new PropertyValueFactory<Analyz_REZ,String>("standard"));
        
        listM = mysql.getDataAnaliz();
        table_users.setItems(listM);
    }
    
     @FXML
    void search_user() {          
        col_id.setCellValueFactory(new PropertyValueFactory<Analyz_REZ,String>("id_pacient"));
        col_naimen.setCellValueFactory(new PropertyValueFactory<Analyz_REZ,String>("research"));
        col_cena.setCellValueFactory(new PropertyValueFactory<Analyz_REZ,String>("result"));
        col_izmer.setCellValueFactory(new PropertyValueFactory<Analyz_REZ,String>("metering"));
        col_kol.setCellValueFactory(new PropertyValueFactory<Analyz_REZ,String>("standard"));
        
        dataList = mysql.getDataAnaliz();
        table_users.setItems(dataList);
        FilteredList<Analyz_REZ> filteredData = new FilteredList<>(dataList, b -> true);  
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(person -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;}    
            String lowerCaseFilter = newValue.toLowerCase();
             if (person.getId_pacient().indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
                                
         else  
          return false; // Does not match.
   });
  });  
        SortedList<Analyz_REZ> sortedData = new SortedList<>(filteredData);  
        sortedData.comparatorProperty().bind(table_users.comparatorProperty());  
        table_users.setItems(sortedData);      
    }
    
    @FXML
    
    void report(ActionEvent event) {
        conn = mysql.ConnectDb();
        //String sql = "insert into oplata (id_pacient,id_doctor,date,summa)values(?,?,?,?,)";
        try{
            JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\Sml\\OneDrive\\Документы\\NetBeansProjects\\POLIKLINIKA\\src\\Report\\Otchet_AN.jrxml");
        
            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, conn);
            
            JasperViewer viewer = new JasperViewer(jPrint, false);
            
            viewer.setTitle("MarcoMan Report");
            
            viewer.setVisible(true);
            viewer.show();
            
        }catch(Exception e){}

    }
    
    @FXML
    
    void report_enc(ActionEvent event) {
        conn = mysql.ConnectDb();
        try{
            JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\Sml\\OneDrive\\Документы\\NetBeansProjects\\POLIKLINIKA\\src\\Report\\Otchet_AN_ENC.jrxml");
        
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
