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
public class TARIF {
    int id_spec,tarif_opl,tarif_skidka;

    public void setId_spec(int id_spec) {
        this.id_spec = id_spec;
    }

    public void setTarif_opl(int tarif_opl) {
        this.tarif_opl = tarif_opl;
    }

    public void setTarif_skidka(int tarif_skidka) {
        this.tarif_skidka = tarif_skidka;
    }

    public int getId_spec() {
        return id_spec;
    }

    public int getTarif_opl() {
        return tarif_opl;
    }

    public int getTarif_skidka() {
        return tarif_skidka;
    }

    public TARIF(int id_spec, int tarif_opl, int tarif_skidka) {
        this.id_spec = id_spec;
        this.tarif_opl = tarif_opl;
        this.tarif_skidka = tarif_skidka;
    }
}
