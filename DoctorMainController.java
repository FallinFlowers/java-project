/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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


public class DoctorMainController implements Initializable {
    
    @FXML
    private Label IDLabel;

    @FXML
    private Label loginLabel;
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    public void initData(String person, String passw)
    {
        IDLabel.setText(person);
        loginLabel.setText(passw);
                
    }
    

     @FXML
    void back(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/DoctorLogin.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();

    }

    @FXML
    void pacient(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Table_PACIENTS.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                //((Node)(event.getSource())).getScene().getWindow().hide();

    }

    @FXML
    void priem(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PRIEM_1.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                //((Node)(event.getSource())).getScene().getWindow().hide();
                /*FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Raspisan.fxml"));
                Parent tableViewParent = loader.load();
                Scene tableViewScene = new Scene(tableViewParent);
                RaspisanController controller = loader.getController();
                controller.Data(IDLabel.getText(),loginLabel.getText());
                Stage mainStage = new Stage();
                mainStage.setScene(tableViewScene);
                mainStage.show();*/

    }

    @FXML
    void ambul_karta(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/AMBUL_KARTA.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                //((Node)(event.getSource())).getScene().getWindow().hide();

    }
    
    @FXML
    void analiz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Analyz_REZ_1.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                //((Node)(event.getSource())).getScene().getWindow().hide();

    }
    
    @FXML
    void person(ActionEvent event) throws IOException {
                
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/FXML/PersonDoc.fxml"));
                Parent tableViewParent = loader.load();
                Scene tableViewScene = new Scene(tableViewParent);
                PersonDocController controller = loader.getController();
                controller.Data(IDLabel.getText(),loginLabel.getText());
                Stage mainStage = new Stage();
                mainStage.setScene(tableViewScene);
                mainStage.show();
            
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
