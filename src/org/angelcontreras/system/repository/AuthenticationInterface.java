/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.angelcontreras.system.repository;

import org.angelcontreras.system.model.Users;

/**
 *
 * @author angel
 */
public interface AuthenticationInterface {
    Users login (String emailOrUser, String password);
}
