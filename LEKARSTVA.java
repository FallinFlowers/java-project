/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;

/**
 *
 * @author Sml
 */
public class LEKARSTVA {
    int id_lekarstva ;
    String naimenovanie;
    String izmerenie;
    int cena,kolichestvo;

    public void setId_lekarstva(int id_lekarstva) {
        this.id_lekarstva = id_lekarstva;
    }

    public void setNaimenovanie(String naimenovanie) {
        this.naimenovanie = naimenovanie;
    }

    public void setIzmerenie(String izmerenie) {
        this.izmerenie = izmerenie;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public void setKolichestvo(int kolichestvo) {
        this.kolichestvo = kolichestvo;
    }

    public int getId_lekarstva() {
        return id_lekarstva;
    }

    public String getNaimenovanie() {
        return naimenovanie;
    }

    public String getIzmerenie() {
        return izmerenie;
    }

    public int getCena() {
        return cena;
    }

    public int getKolichestvo() {
        return kolichestvo;
    }

    public LEKARSTVA(int id_lekarstva, String naimenovanie, String izmerenie, int cena, int kolichestvo) {
        this.id_lekarstva = id_lekarstva;
        this.naimenovanie = naimenovanie;
        this.izmerenie = izmerenie;
        this.cena = cena;
        this.kolichestvo = kolichestvo;
    }
    
}
