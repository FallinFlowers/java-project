/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POLIKLINIKA;

import javafx.scene.control.Hyperlink;

/**
 *
 * @author Sml
 */
public class DIAGNOZ {
    int id_diagnoz;
    String naimenovanie;
    String link;

    public void setId_diagnoz(int id_diagnoz) {
        this.id_diagnoz = id_diagnoz;
    }

    public void setNaimenovanie(String naimenovanie) {
        this.naimenovanie = naimenovanie;
    }

    public void setLink(String websiteUrl) {
        this.link =  websiteUrl;
    }

    public int getId_diagnoz() {
        return id_diagnoz;
    }

    public String getNaimenovanie() {
        return naimenovanie;
    }

    public String getLink() {
        return link;
    }

    public DIAGNOZ(int id_diagnoz, String naimenovanie, String websiteUrl) {
        this.id_diagnoz = id_diagnoz;
        this.naimenovanie = naimenovanie;
        this.link =  websiteUrl;
    }

    
}
