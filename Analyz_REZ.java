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
public class Analyz_REZ {
    String id_pacient;
    String research, result, metering, standard;

    public String getId_pacient() {
        return id_pacient;
    }

    public String getResearch() {
        return research;
    }

    public String getResult() {
        return result;
    }

    public String getMetering() {
        return metering;
    }

    public String getStandard() {
        return standard;
    }

    public void setId_pacient(String id_pacient) {
        this.id_pacient = id_pacient;
    }

    public void setResearch(String research) {
        this.research = research;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setMetering(String metering) {
        this.metering = metering;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public Analyz_REZ(String id_pacient, String research, String result, String metering, String standard) {
        this.id_pacient = id_pacient;
        this.research = research;
        this.result = result;
        this.metering = metering;
        this.standard = standard;
    }

   }
