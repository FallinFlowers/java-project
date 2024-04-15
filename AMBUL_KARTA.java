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
public class AMBUL_KARTA {
    int id_pacient;
    Date date_pr;
    String diagnoz,naznachenie;

    public void setId_pacient(int id_pacient) {
        this.id_pacient = id_pacient;
    }

    public void setDate_pr(Date date_pr) {
        this.date_pr = date_pr;
    }

    public void setDiagnoz(String diagnoz) {
        this.diagnoz = diagnoz;
    }

    public void setNaznachenie(String naznachenie) {
        this.naznachenie = naznachenie;
    }

    public int getId_pacient() {
        return id_pacient;
    }

    public Date getDate_pr() {
        return date_pr;
    }

    public String getDiagnoz() {
        return diagnoz;
    }

    public String getNaznachenie() {
        return naznachenie;
    }

    public AMBUL_KARTA(int id_pacient, Date date_pr, String diagnoz, String naznachenie) {
        this.id_pacient = id_pacient;
        this.date_pr = date_pr;
        this.diagnoz = diagnoz;
        this.naznachenie = naznachenie;
    }
    
}
