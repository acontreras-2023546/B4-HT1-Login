package org.angelcontreras.system.repository;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.angelcontreras.system.config.ConexionDB;
import org.angelcontreras.system.model.Users;

/**
 *
 * @author angel
 */

public class AuthenticationRepository implements AuthenticationInterface {

    private ConexionDB conexionDB = ConexionDB.getInstanciaConexionDB();

    @Override
    public Users login(String emailOrUser, String password) {
        try {
            String sql = "{CALL sp_authenticate_user(?, ?)}";
            CallableStatement callSP = conexionDB.getConnection().prepareCall(sql);
            callSP.setString(1, emailOrUser);
            callSP.setString(2, password);

            ResultSet rs = callSP.executeQuery();

            if (rs.next()) {
                Users user = new Users();
                user.setId_user(rs.getString("id_user"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setUser(rs.getString("user"));
                user.setPassword(rs.getString("password"));
                return user;
            }
            return null; 

        } catch (SQLException e) {
            System.out.println("Error en AuthenticationRepository: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}