package org.angelcontreras.system.service;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.angelcontreras.system.config.ConexionDB;
import org.angelcontreras.system.model.Users;
import org.angelcontreras.system.repository.UserRepository;
import org.angelcontreras.system.utils.AlertInformation;
import org.angelcontreras.system.utils.Validations;

public class UserService {

    private Validations validate = new Validations();
    private AlertInformation alertInfo = new AlertInformation();
    private UserRepository userRepo = new UserRepository();

    public boolean validateLogin(String user, String password) {
        System.out.println("=== INICIANDO VALIDACIÓN EN BD ===");
        System.out.println("Usuario: '" + user + "'");
        System.out.println("Password: '" + password + "'");

        try {
            String sql = "{call sp_validate_login(?, ?)}";
            CallableStatement callSP = ConexionDB.getInstanciaConexionDB()
                    .getConnection()
                    .prepareCall(sql);

            callSP.setString(1, user);
            callSP.setString(2, password);

            System.out.println("Ejecutando procedimiento almacenado...");
            ResultSet rs = callSP.executeQuery();

            if (rs.next()) {
                int existe = rs.getInt(1);
                System.out.println("Resultado del COUNT: " + existe);

                if (existe > 0) {
                    System.out.println("=== LOGIN EXITOSO ===");
                    return true;
                } else {
                    System.out.println("=== USUARIO NO ENCONTRADO ===");
                }
            }

            return false;

        } catch (SQLException e) {
            System.out.println("=== ERROR SQL ===");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public UserStatus createUser(String user, String name, String lastName, String email, String password) {
        if (validate.emptyText(user) == true
                || validate.emptyText(name) == true
                || validate.emptyText(lastName) == true
                || validate.emptyText(email) == true
                || validate.emptyText(password) == true) {
            alertInfo.viewAlert(3, "ERROR DE CAMPOS VACIOS", "ERROR DE CAMPO", "DEJO CAMPOS VACIOS DEL FORMULARIO");
            return UserStatus.FIELDS_EMPTY;
        }
        try {
            Users newUser = new Users(name, lastName, email, user, password);
            userRepo.create(newUser);
            return UserStatus.USER_CREATED;
        } catch (Exception e) {
            System.out.println("ERROR REAL AL CREAR USUARIO:");
            e.printStackTrace();
            return UserStatus.ERROR_USER_CREATE;
        }
    }

    public boolean userExists(String emailOrUser) {
        try {
            String sql = "{call sp_user_exists(?)}";
            CallableStatement callSP = ConexionDB.getInstanciaConexionDB()
                    .getConnection().prepareCall(sql);
            callSP.setString(1, emailOrUser);

            ResultSet rs = callSP.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Error al buscar usuario: " + e.getMessage());
            return false;
        }
    }
}
