package org.angelcontreras.system.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.angelcontreras.system.service.AuthenticationService;
import org.angelcontreras.system.service.AuthenticationStatus;
import org.angelcontreras.system.utils.AlertInformation;
import org.angelcontreras.system.utils.ViewFactory;

public class LoginController implements Initializable {

    @FXML private TextField txtUser;
    @FXML private PasswordField txtPassword;

    private ViewFactory viewFactory = new ViewFactory();
    private AlertInformation alertInfo = new AlertInformation();
    private AuthenticationService authService = new AuthenticationService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    @FXML
    public void onLogin(MouseEvent event) {
        String userOrEmail = txtUser.getText().trim();
        String pass = txtPassword.getText().trim();

        AuthenticationStatus status = authService.authenticate(userOrEmail, pass);

        switch (status) {
            case LOGIN_SUCCESS -> {
                System.out.println("Login exitoso - Navegando al Dashboard");
                viewFactory.loadScene("main");
            }
            case NOT_EXIST_USER -> {
                System.out.println("El usuario no existe");
                alertInfo.viewAlert(2, "Usuario no encontrado",
                    "No existe una cuenta con ese usuario/email. ¿Desea registrarse?",
                    "Login");
                viewFactory.viewRegister();
            }
            case WRONG_PASSWORD -> {
                alertInfo.viewAlert(3, "Contraseña incorrecta",
                    "La contraseña no coincide con el usuario.",
                    "Login");
            }
            case FIELDS_EMPTY -> {
                alertInfo.viewAlert(3, "Campos vacíos",
                    "Debe ingresar usuario y contraseña.",
                    "Login");
            }
            default -> {
                alertInfo.viewAlert(3, "Error",
                    "Ocurrió un error inesperado.",
                    "Login");
            }
        }
    }

    @FXML
    public void onRegister(MouseEvent event) {
        viewFactory.viewRegister();
    }
}