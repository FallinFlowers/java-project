/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Sml
 */
public class OTCHET {
     int id_doctor;
    String special;
    int id_pacient;
    Date date_pr;
    Time time;

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public void setId_pacient(int id_pacient) {
        this.id_pacient = id_pacient;
    }

    public void setDate_pr(Date date_pr) {
        this.date_pr = date_pr;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public String getSpecial() {
        return special;
    }

    public int getId_pacient() {
        return id_pacient;
    }

    public Date getDate_pr() {
        return date_pr;
    }

    public Time getTime() {
        return time;
    }

    public OTCHET(int id_doctor, String special, int id_pacient, Date date_pr, Time time) {
        this.id_doctor = id_doctor;
        this.special = special;
        this.id_pacient = id_pacient;
        this.date_pr = date_pr;
        this.time = time;
    }
    
}
