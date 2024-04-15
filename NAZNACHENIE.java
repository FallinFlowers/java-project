/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;

import java.sql.Date;

/**
 *
 * @author Sml
 */
public class NAZNACHENIE {
    int id_pacient, id_doctor,id_lekarstva, kolichesvo ;
    Date date;

    public void setId_pacient(int id_pacient) {
        this.id_pacient = id_pacient;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public void setId_lekarstva(int id_lekarstva) {
        this.id_lekarstva = id_lekarstva;
    }

    public void setKolichesvo(int kolichesvo) {
        this.kolichesvo = kolichesvo;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId_pacient() {
        return id_pacient;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public int getId_lekarstva() {
        return id_lekarstva;
    }

    public int getKolichesvo() {
        return kolichesvo;
    }

    public Date getDate() {
        return date;
    }

    public NAZNACHENIE(int id_pacient, int id_doctor, int id_lekarstva, int kolichesvo, Date date) {
        this.id_pacient = id_pacient;
        this.id_doctor = id_doctor;
        this.id_lekarstva = id_lekarstva;
        this.kolichesvo = kolichesvo;
        this.date = date;
    }
}
