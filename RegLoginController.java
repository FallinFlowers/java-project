/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sml
 */
public class RegLoginController implements Initializable {

    @FXML
    private JFXButton btn_login;
    
    @FXML
    private JFXTextField txt_username;

    @FXML
    private JFXPasswordField txt_password;
    
    @FXML
    private AnchorPane pane_login;

    @FXML
    private AnchorPane pane_signup;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    public void LoginpaneShow(){
    
        pane_login.setVisible(true);
       
    }
    
    
    @FXML
    private void Login (ActionEvent event) throws Exception{  
    conn = mysql.ConnectDb();
    String sql = "Select * from register where login = MD5(?) and password = MD5(?) ";
        try {
            
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_username.getText());
            pst.setString(2, txt_password.getText());
             
            
            rs = pst.executeQuery();
            
            if(rs.next()){ 
                JOptionPane.showMessageDialog(null, "Username And Password is Corect");
                btn_login.getScene().getWindow().hide();
                
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/FXML/RegMain.fxml"));
                Parent tableViewParent = loader.load();
                Scene tableViewScene = new Scene(tableViewParent);
                RegMainController controller = loader.getController();
                controller.initData(txt_username.getText(),txt_password.getText());
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(tableViewScene);
                window.show();
            }else {
                JOptionPane.showMessageDialog(null, "Invalide Username Or Password");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
       
    }
    
         public void handle(WindowEvent event) {
 
          event.consume();
 
         }
    
    @FXML
    void back(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Main.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();

    }    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
