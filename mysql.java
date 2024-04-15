/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;

import Table.REGISTER;
import Table.PRIEM;
import Table.PACIENT;
import Table.AMBUL_KARTA;
import Table.ADMIN;
import Table.DOCTORS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Sml
 */
public class mysql {
    Connection conn = null;
    public static Connection ConnectDb(){
 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poliklinika","root","");
            //JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        } 
    }
    
    /**
     *
     * @return
     */
    public static ObservableList<users> getDatausers(){
        Connection conn = ConnectDb();
        ObservableList<users> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new users(Integer.parseInt(rs.getString("user_id")), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("type"), rs.getDate("date")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
     
      public static ObservableList<DOCTORS> getDataDOCTORS(){
        Connection conn = ConnectDb();
        ObservableList<DOCTORS> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from doctors");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new DOCTORS (Integer.parseInt(rs.getString("id_doctor")),rs.getString("doctor_name"),Integer.parseInt(rs.getString("id_spec")), rs.getString("adress"), (Integer.parseInt(rs.getString("telephone"))),rs.getDate("date"),  rs.getString("login"), rs.getString("password")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
      
     
    public static ObservableList<PRIEM> getDatauserPR(){
        Connection conn = ConnectDb();
        ObservableList<PRIEM> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from priem ");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new PRIEM (Integer.parseInt(rs.getString("id_doctor")), rs.getString("special"),(Integer.parseInt(rs.getString("id_pacient"))), rs.getDate("date_pr"), rs.getTime("time")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    
     public static ObservableList<ADMIN> getDatauserA(){
        Connection conn = ConnectDb();
        ObservableList<ADMIN> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select *, AES_DECRYPT(name, 2) from admin");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new ADMIN(Integer.parseInt(rs.getString("id")), rs.getString("name"), rs.getString("password"), rs.getString("email")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
     
     public static ObservableList<REGISTER> getDataREG(){
        Connection conn = ConnectDb();
        ObservableList<REGISTER> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from register");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new REGISTER(Integer.parseInt(rs.getString("id_pegist")), rs.getString("name"), rs.getString("adress"), rs.getDate("date"), Integer.parseInt(rs.getString("telephone")),rs.getString("login"), rs.getString("password")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
     
      public static ObservableList<PACIENT> getDataPAC(){
        Connection conn = ConnectDb();
        ObservableList<PACIENT> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from pacient");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new PACIENT(Integer.parseInt(rs.getString("id_pacient")), rs.getString("pacient_name"), rs.getDate("date"), rs.getString("pol"), rs.getString("adress"), Integer.parseInt(rs.getString("telephone")), rs.getString("email"),rs.getString("lgota")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
      
    public static ObservableList<AMBUL_KARTA> getDataAMBUL_KARTA(){
        Connection conn = ConnectDb();
        ObservableList<AMBUL_KARTA> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from ambul_karta ");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new AMBUL_KARTA (Integer.parseInt(rs.getString("id_pacient")), rs.getDate("date_pr"), rs.getString("diagnoz"), rs.getString("naznachenie")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public static ObservableList<DIAGNOZ> getDataDIAGNOZ(){
        Connection conn = ConnectDb();
        ObservableList<DIAGNOZ> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from diagnoz ");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new DIAGNOZ (Integer.parseInt(rs.getString("id_diagnoz")), rs.getString("naimenovanie"),(rs.getString("link"))));               
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public static ObservableList<LEKARSTVA> getDataLEKARSTVA(){
        Connection conn = ConnectDb();
        ObservableList<LEKARSTVA> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from lekarstva");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new LEKARSTVA (Integer.parseInt(rs.getString("id_lekarstva")), rs.getString("naimenovanie"),rs.getString("izmerenie"), Integer.parseInt(rs.getString("cena")),Integer.parseInt(rs.getString("kolichestvo"))));               
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public static ObservableList<NAZNACHENIE> getDataNAZNACHENIE(){
        Connection conn = ConnectDb();
        ObservableList<NAZNACHENIE> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from naznachenie");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new NAZNACHENIE (Integer.parseInt(rs.getString("id_pacient")), Integer.parseInt(rs.getString("id_doctor")),Integer.parseInt(rs.getString("id_lekarstva")),Integer.parseInt(rs.getString("kolichesvo")), rs.getDate("date")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public static ObservableList<OPLATA> getDataOPLATA(){
        Connection conn = ConnectDb();
        ObservableList<OPLATA> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from oplata");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new OPLATA (Integer.parseInt(rs.getString("id_pacient")), Integer.parseInt(rs.getString("id_doctor")), rs.getDate("date"),Integer.parseInt(rs.getString("summa"))));               
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public static ObservableList<SPECIAL> getDataSPECIAL(){
        Connection conn = ConnectDb();
        ObservableList<SPECIAL> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from special");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new SPECIAL (Integer.parseInt(rs.getString("id_spec")), rs.getString("naimenovanie")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
    public static ObservableList<TARIF> getDataTARIF(){
        Connection conn = ConnectDb();
        ObservableList<TARIF> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from tarif");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new TARIF (Integer.parseInt(rs.getString("id_spec")), Integer.parseInt(rs.getString("tarif_opl")),Integer.parseInt(rs.getString("tarif_skidka"))));               
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public static ObservableList<VIEW> getDataVIEW(){
        Connection conn = ConnectDb();
        ObservableList<VIEW> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT priem.id_doctor, doctors.doctor_name,priem.special, priem.id_pacient, pacient.pacient_name,  priem.date_pr, priem.time\n" +
"FROM doctors INNER JOIN priem ON doctors.id_doctor = priem.id_doctor LEFT JOIN pacient ON priem.id_pacient=pacient.id_pacient  WHERE doctors.id_doctor = 4");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new VIEW(Integer.parseInt(rs.getString("id_doctor")),rs.getString("doctor_name"), rs.getString("special"),(Integer.parseInt(rs.getString("id_pacient"))),rs.getString("pacient_name"), rs.getDate("date_pr"), rs.getTime("time")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public static ObservableList<Analyz_REZ> getDataAnaliz(){
        Connection conn = ConnectDb();
        ObservableList<Analyz_REZ> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from analizy_rez");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Analyz_REZ ((rs.getString("id_pacient")), rs.getString("research"),rs.getString("result"),rs.getString("metering"),rs.getString("standard")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
    
}


