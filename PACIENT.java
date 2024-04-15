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
public class PACIENT {
    int id_pacient;
    String pacient_name;
    Date date;
    String pol, adress;
    int telephone;
    String email;
    String lgota;

    public void setId_pacient(int id_pacient) {
        this.id_pacient = id_pacient;
    }

    public void setPacient_name(String pacient_name) {
        this.pacient_name = pacient_name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLgota(String lgota) {
        this.lgota = lgota;
    }

    public int getId_pacient() {
        return id_pacient;
    }

    public String getPacient_name() {
        return pacient_name;
    }

    public Date getDate() {
        return date;
    }

    public String getPol() {
        return pol;
    }

    public String getAdress() {
        return adress;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getLgota() {
        return lgota;
    }

    public PACIENT(int id_pacient, String pacient_name, Date date, String pol, String adress, int telephone, String email, String lgota) {
        this.id_pacient = id_pacient;
        this.pacient_name = pacient_name;
        this.date = date;
        this.pol = pol;
        this.adress = adress;
        this.telephone = telephone;
        this.email = email;
        this.lgota = lgota;
    }
}
