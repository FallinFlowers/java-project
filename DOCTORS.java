/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Table;

import java.sql.Date;



/**
 *
 * @author Sml
 */
public class DOCTORS {
    
    int id_doctor ;
    String doctor_name;
    int id_spec;
    String adress;
    int telephone;
    Date date;
    String login,password;

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public void setId_spec(int id_spec) {
        this.id_spec = id_spec;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public int getId_spec() {
        return id_spec;
    }

    public String getAdress() {
        return adress;
    }

    public int getTelephone() {
        return telephone;
    }

    public Date getDate() {
        return date;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public DOCTORS(int id_doctor, String doctor_name, int id_spec, String adress, int telephone, Date date, String login, String password) {
        this.id_doctor = id_doctor;
        this.doctor_name = doctor_name;
        this.id_spec = id_spec;
        this.adress = adress;
        this.telephone = telephone;
        this.date = date;
        this.login = login;
        this.password = password;
    }
   
}
