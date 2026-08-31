package org.angelcontreras.system.service;

import org.angelcontreras.system.model.Users;
import org.angelcontreras.system.repository.AuthenticationRepository;

public class AuthenticationService {

    private AuthenticationRepository authRepo = new AuthenticationRepository();
    private UserService userService = new UserService();

    /**
     * Método principal de autenticación.
     * Valida si el usuario existe y luego intenta loguearlo.
     */
    
    public AuthenticationStatus authenticate(String emailOrUser, String password) {
        if (emailOrUser == null || emailOrUser.trim().isEmpty() 
            || password == null || password.trim().isEmpty()) {
            return AuthenticationStatus.FIELDS_EMPTY;
        }

        if (!userService.userExists(emailOrUser.trim())) {
            return AuthenticationStatus.NOT_EXIST_USER;
        }

        Users authenticatedUser = authRepo.login(emailOrUser.trim(), password.trim());

        if (authenticatedUser != null) {
            return AuthenticationStatus.LOGIN_SUCCESS;
        } else {
            return AuthenticationStatus.WRONG_PASSWORD;
        }
    }
}