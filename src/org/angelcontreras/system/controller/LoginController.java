package org.angelcontreras.system.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.angelcontreras.system.service.UserService;
import org.angelcontreras.system.utils.AlertInformation;
import org.angelcontreras.system.utils.ViewFactory;

public class LoginController implements Initializable {

    @FXML
    private TextField txtUser;
    
    @FXML
    private PasswordField txtPassword;

    private ViewFactory viewFactory = new ViewFactory();
    private AlertInformation alertInfo = new AlertInformation();
    private UserService userService = new UserService(); // ✅ AGREGAR

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    @FXML
    public void onLogin(MouseEvent event) {
        String user = txtUser.getText().trim();
        String pass = txtPassword.getText().trim();
        
        System.out.println("Intentando login con: " + user);
        
        // ✅ VALIDAR CONTRA LA BASE DE DATOS
        if (userService.validateLogin(user, pass)) {
            System.out.println("Login exitoso - Navegando al menú principal");
            viewFactory.loadScene("main");
        } else {
            System.out.println("Credenciales incorrectas");
            alertInfo.viewAlert(3, "Error de Acceso", 
                               "Usuario o contraseña incorrectos", 
                               "Login");
        }
    }
    
    @FXML
    public void onRegister(MouseEvent event) {
        viewFactory.viewRegister();
    }
}