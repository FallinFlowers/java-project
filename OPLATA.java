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
public class OPLATA {
    int id_pacient, id_doctor;
    Date date;
    int summa;

    public void setId_pacient(int id_pacient) {
        this.id_pacient = id_pacient;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSumma(int summa) {
        this.summa = summa;
    }

    public int getId_pacient() {
        return id_pacient;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public Date getDate() {
        return date;
    }

    public int getSumma() {
        return summa;
    }

    public OPLATA(int id_pacient, int id_doctor, Date date, int summa) {
        this.id_pacient = id_pacient;
        this.id_doctor = id_doctor;
        this.date = date;
        this.summa = summa;
    }
}
