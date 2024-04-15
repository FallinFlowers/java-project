/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;

import com.jfoenix.controls.JFXTextField;
import java.awt.HeadlessException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sml
 */
public class PersonController implements Initializable {

    @FXML
    private Label login;

    @FXML
    private Label email;

    @FXML
    private Label pass;

    @FXML
    private Label IDLabel;
    @FXML
    private JFXTextField usreID;

    @FXML
    private JFXTextField txt_log;

    @FXML
    private JFXTextField txt_email;

    @FXML
    private JFXTextField txt_pass;

    
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    public void Data(String log, String passw)
    {
        txt_log.setText(log);
        txt_pass.setText(passw);
        conn = mysql.ConnectDb();
        String sql = "Select * from admin where name = '" + log + "' and password = MD5('" + passw + "')" ;
            try {
            //loginLabel.setText(person);
            pst = conn.prepareStatement(sql);
            //pst.setString(1, loginLabel.getText());          
            rs = pst.executeQuery();
            
            if(rs.next()){ 
                String id = rs.getString(1);
                String name = rs.getString(3);
                //String login = rs.getString(3);
                //String pass = rs.getString(4);
                
                usreID.setText(id);
                txt_email.setText(name);
                //login.setText(log);
                //pass.setText(passw);
                
        
        
            }else {
                JOptionPane.showMessageDialog(null, "Invalide Username Or Password");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
   

    @FXML
    public void edit() {
        
        try {
            conn = mysql.ConnectDb();
            String value1 = usreID.getText();
            String value2 = txt_log.getText();
            String value3 = txt_email.getText();
            String value4 = txt_pass.getText();
            String sql = "update admin set id= '"+value1+"', name='"+value2+"', email= '"+value3+"', password= MD5('"+value4+"') where id='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
               
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        

    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
