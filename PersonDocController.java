/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;

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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sml
 */
public class PersonDocController implements Initializable {

    @FXML
    private Label L1_ID;

    @FXML
    private Label L2_Name;

    @FXML
    private Label L3_Spec;

    @FXML
    private Label L4_Adress;

    @FXML
    private Label L5_Date;

    @FXML
    private Label L6_Tel;

    @FXML
    private Label L7_Log;

    @FXML
    private Label L8_Pass;

    @FXML
    private JFXTextField txt_id;

    @FXML
    private JFXTextField txt_name;

    @FXML
    private JFXTextField txt_spec;

    @FXML
    private JFXTextField txt_adress;

    @FXML
    private JFXTextField txt_date;

    @FXML
    private JFXTextField txt_tel;

    @FXML
    private JFXTextField txt_log;

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
        String sql = "Select * from  doctors where login = MD5('" + log + "') and password = MD5('" + passw + "')" ;
            try {
            pst = conn.prepareStatement(sql);         
            rs = pst.executeQuery();
            
            if(rs.next()){ 
                String id = rs.getString(1);
                String name = rs.getString(2);
                String spec = rs.getString(3);
                String adress = rs.getString(4);
                String date = rs.getString(5);
                String tel = rs.getString(6);
               
                
                txt_id.setText(id);
                txt_name.setText(name);
                txt_spec.setText(spec);
                txt_adress.setText(adress);
                txt_date.setText(date);
                txt_tel.setText(tel);
                
                
            }else {
                JOptionPane.showMessageDialog(null, "Invalide Username Or Password");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }

    @FXML
    void edit(ActionEvent event) {
        
        try {
            conn = mysql.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_name.getText();
            String value3 = txt_spec.getText();
            String value4 = txt_adress.getText();
            String value5 = txt_tel.getText();
            String value6 = txt_date.getText();
            String value7 = txt_log.getText();
            String value8 = txt_pass.getText();
            String sql = "update doctors set id_doctor= '"+value1+"',doctor_name= '"+value2+"',id_spec= '"+
                    value3+"',adress= '"+value4+"',telephone= '"+value5+"',date= '"+value6+"',login= MD5('"+value7+"'),password= MD5('"+value8+"') where id_doctor='"+value1+"' ";
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
