/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.angelcontreras.system.repository;

/**
 *
 * @author informatica
 */
import java.sql.CallableStatement;
import java.sql.SQLException;
import org.angelcontreras.system.config.ConexionDB;
import org.angelcontreras.system.model.Users;

public class UserRepository implements UserInterface {

    private CallableStatement callSP;

    private ConexionDB conexionDB = ConexionDB.getInstanciaConexionDB();

    public UserRepository(){}
    
    @Override
    public void create(Users users){
        try {
            callSP = conexionDB.getConnection().prepareCall("{call sp_create_users(?, ?, ?, ?, ?)}");
            callSP.setString(1, users.getName());
            callSP.setString(2, users.getLastname());
            callSP.setString(3, users.getEmail());
            callSP.setString(4, users.getUser());
            callSP.setString(5, users.getPassword());
            
            callSP.execute();
            
            callSP.close();//Liberar los recursos utilizados
            
        } catch (SQLException e) {
            System.out.println("Error al crear el usuario");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

}
