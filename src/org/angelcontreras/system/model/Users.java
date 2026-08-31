/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.angelcontreras.system.model;

/**
 *
 * @author informatica
 */
public class Users {
    private String name;
    private String lastname; 
    private String email;
    private String user;
    private String password;
    private String id_user;
    
    
    public Users(){
        
    }
    

    public Users(String name, String lastname, String email, String user, String password, String id_user) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.user = user;
        this.password = password;
        this.id_user = id_user;
    }

    public Users(String name, String lastname, String email, String user, String password) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.user = user;
        this.password = password;
    }
    
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    
    
}
