/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;

import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sml
 */
public class Admin_MainController implements Initializable {
    
    
    @FXML
    private Label IDLabel;

    @FXML
    private Label loginLabel;
    
    @FXML
    private TextField txt_username ;
    
    @FXML
    private TextField txt_password ;

    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    public void initData(String person, String passw)
    {
        IDLabel.setText(person);
        loginLabel.setText(passw);
        /*conn = mysql.ConnectDb();
        String sql = "Select * from admin where name = '" + person + "' and password = '" + passw + "'" ;
            try {
            //loginLabel.setText(person);
            pst = conn.prepareStatement(sql);
            //pst.setString(1, loginLabel.getText());          
            rs = pst.executeQuery();
            
            if(rs.next()){ 
                String id = rs.getString(1);
                String name = rs.getString(2);
                //String log = rs.getString(3);
                
                IDLabel.setText(id);
                //loginLabel.setText(name);
                firstNameLabel.setText(name);
                
        
        
            }else {
                JOptionPane.showMessageDialog(null, "Invalide Username Or Password");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }*/
        
    }

     @FXML
    void back(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/AdminLogin.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();

    }

    @FXML
    void pacient(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Table_PACIENTS_1.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                //((Node)(event.getSource())).getScene().getWindow().hide();

    }

    @FXML
    void register(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Table_REGISTER.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                //((Node)(event.getSource())).getScene().getWindow().hide();

    }

    @FXML
    void doctor(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Table.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                //((Node)(event.getSource())).getScene().getWindow().hide();

    }
    
   @FXML
    void person(ActionEvent event) throws IOException {
                
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/FXML/Person.fxml"));
                Parent tableViewParent = loader.load();
                Scene tableViewScene = new Scene(tableViewParent);
                PersonController controller = loader.getController();
                controller.Data(IDLabel.getText(),loginLabel.getText());
                Stage mainStage = new Stage();
                mainStage.setScene(tableViewScene);
                mainStage.show();
            
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
