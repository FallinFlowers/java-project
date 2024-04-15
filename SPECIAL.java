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
public class SPECIAL {
    int id_spec;
    String naimenovanie;

    public void setId_spec(int id_spec) {
        this.id_spec = id_spec;
    }

    public void setNaimenovanie(String naimenovanie) {
        this.naimenovanie = naimenovanie;
    }

    public int getId_spec() {
        return id_spec;
    }

    public String getNaimenovanie() {
        return naimenovanie;
    }

    public SPECIAL(int id_spec, String naimenovanie) {
        this.id_spec = id_spec;
        this.naimenovanie = naimenovanie;
    }
    
}
