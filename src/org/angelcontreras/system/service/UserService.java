/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.angelcontreras.system.service;
/**
 *
 * @author angel
 */


import org.angelcontreras.system.repository.UserRepository;
import org.angelcontreras.system.utils.AlertInformation;
import org.angelcontreras.system.utils.Validations;
import org.angelcontreras.system.model.Users;

/**
 *
 * @author informatica
 */
public class UserService {
    
    private Validations validate = new Validations();
    private AlertInformation alertInfo = new AlertInformation();
    private UserRepository userRepo = new UserRepository();
    
    public UserStatus createUser(String user, String name, String lastName, String email, String password){
        
        if (validate.emptyText(user) == true
                || validate.emptyText(name) == true
                || validate.emptyText(lastName) == true
                || validate.emptyText(email) == true
                || validate.emptyText(password) == true ){
            alertInfo.viewAlert(3, "ERROR DE CAMPOS VACIOS", "ERROR DE CAMPO", "DEJO CAMPOS VACIOS DEL FORMULARIO");
            return UserStatus.FIELDS_EMPTY;
        }
        try {
            Users newUser = new Users(password, email, name, lastName, user);
            userRepo.create(newUser);
            return UserStatus.USER_CREATED;
            
        } catch (Exception e) {
    System.out.println("ERROR REAL AL CREAR USUARIO:");
    e.printStackTrace();
    return UserStatus.ERROR_USER_CREATE;
}
    }
}