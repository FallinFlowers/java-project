/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POLIKLINIKA;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Sml
 */
public class VIEW {
    int id_doctor;
    String doctor_name;
    String special;
    int id_pacient;
    String pacient_name;
    Date date_pr;
    Time time;

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public void setId_pacient(int id_pacient) {
        this.id_pacient = id_pacient;
    }

    public void setPacient_name(String pacient_name) {
        this.pacient_name = pacient_name;
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

    public String getDoctor_name() {
        return doctor_name;
    }

    public String getSpecial() {
        return special;
    }

    public int getId_pacient() {
        return id_pacient;
    }

    public String getPacient_name() {
        return pacient_name;
    }

    public Date getDate_pr() {
        return date_pr;
    }

    public Time getTime() {
        return time;
    }

    public VIEW(int id_doctor, String doctor_name, String special, int id_pacient, String pacient_name, Date date_pr, Time time) {
        this.id_doctor = id_doctor;
        this.doctor_name = doctor_name;
        this.special = special;
        this.id_pacient = id_pacient;
        this.pacient_name = pacient_name;
        this.date_pr = date_pr;
        this.time = time;
    }
}
