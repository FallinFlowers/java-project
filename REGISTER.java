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
public class REGISTER {
    int id_pegist;
    String name, adress;
    Date date;
    int telephone;
    String login, password;

    public void setId_pegist(int id_pegist) {
        this.id_pegist = id_pegist;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_pegist() {
        return id_pegist;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public Date getDate() {
        return date;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public REGISTER(int id_pegist, String name, String adress, Date date, int telephone, String login, String password) {
        this.id_pegist = id_pegist;
        this.name = name;
        this.adress = adress;
        this.date = date;
        this.telephone = telephone;
        this.login = login;
        this.password = password;
    }
}
