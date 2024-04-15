/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class AnalizController implements Initializable {

    @FXML
    private TextField txt_data;

    @FXML
    private TextField txt_id_pac;

    @FXML
    private TextField txt_np;
    
    @FXML
    private ComboBox type;
    
    ObservableList<VIEW> listM;
    ObservableList<VIEW> dataList;
    
   
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    /*@FXML
    void open(ActionEvent event) throws IOException {
        new POLIKLINIKA.Admin_MainController1().pacient(event);

    }*/

    @FXML
    public void Add_users() {
         conn = mysql.ConnectDb();
        String sql = "insert into analizy (id_pacient,telephone,date,type) values(?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id_pac.getText());
            pst.setString(2, txt_np.getText());
            pst.setString(3, txt_data.getText());
            pst.setString(4, type.getValue().toString());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Анализы добавлены");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.getItems().addAll("Гепаптит A","Гепаптит B","Гепаптит С");
    }    
    
}
