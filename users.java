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
public class users {
    int id ;
    String username, password, email , type;
    Date date;

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public users(int id, String username, String password, String email, String type, Date date) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.type = type;
        this.date = date;
    }

    
    
}
