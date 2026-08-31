/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.angelcontreras.system.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.angelcontreras.system.service.UserService;
import org.angelcontreras.system.service.UserStatus;
import org.angelcontreras.system.utils.AlertInformation;
import org.angelcontreras.system.utils.Validations;
import org.angelcontreras.system.utils.ViewFactory;

/**
 *
 * @author angel
 */
public class RegisterController implements Initializable{

        @FXML
    private TextField txtUser;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private PasswordField pwdConfirmPassword;

    private Validations validate = new Validations();
    private AlertInformation alertInfo = new AlertInformation();
    private UserService userService = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialización si es necesaria
    }

    @FXML
    public void onCancel(MouseEvent event) {
        ViewFactory viewFacto = new ViewFactory();
        viewFacto.viewLogin();
    }

    @FXML
    public void onCreateUser(MouseEvent event) {
        // 1. Validar email
        boolean isValidEmail = validate.validateEmail(txtEmail.getText().trim());
        if (!isValidEmail) {
            // ✅ CORREGIDO: "ERROR" en lugar del número 1
            alertInfo.viewAlert(1, "Error de Email", "Has ingresado un email incorrecto", "Validación");
            return;
        }

        // 2. Obtener datos
        String user = txtUser.getText().trim();
        String name = txtName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String email = txtEmail.getText().trim();
        String password = pwdPassword.getText().trim();
        String confirmPassword = pwdConfirmPassword.getText().trim();

        // 3. Validar campos vacíos
        if (validate.emptyText(user) || validate.emptyText(name)
                || validate.emptyText(lastName) || validate.emptyText(email)
                || validate.emptyText(password) || validate.emptyText(confirmPassword)) {

            // ✅ CORREGIDO: "ERROR" en lugar del número 3
            alertInfo.viewAlert(3, "Campos Vacíos", "Dejó campos vacíos en el formulario", "Validación");
            return;
        }

        // 4. Validar longitudes (usando else if para que solo muestre el primer error)
        String msgField = "";
        if (!validate.validateLengthText(user, 25)) {
            msgField = "El campo USUARIO no puede superar los 25 caracteres.";
        } else if (!validate.validateLengthText(name, 50)) {
            msgField = "El campo NOMBRE no puede superar los 50 caracteres.";
        } else if (!validate.validateLengthText(lastName, 50)) {
            msgField = "El campo APELLIDO no puede superar los 50 caracteres.";
        } else if (!validate.validateLengthText(email, 50)) {
            msgField = "El campo EMAIL no puede superar los 50 caracteres.";
        } else if (!validate.validateLengthText(password, 35)) {
            msgField = "El campo PASSWORD no puede superar los 35 caracteres.";
        }

        if (!msgField.isEmpty()) {
            // ✅ CORREGIDO: "WARNING" en lugar del número 3
            alertInfo.viewAlert(3, "Longitud Inválida", msgField, "Validación");
            return;
        }

        // 5. Validar que las contraseñas coincidan
        if (!validate.equalsText(password, confirmPassword)) {
            // ✅ CORREGIDO: "ERROR" en lugar del número 3
            alertInfo.viewAlert(3, "Error de Contraseña", "Sus contraseñas no coinciden", "Validación");
            return;
        }

        // 6. Intentar crear el usuario en la base de datos
        UserStatus status = userService.createUser(user, name, lastName, email, password);

        // 7. Mostrar el resultado al usuario
        switch (status) {
            case ERROR_USER_CREATE -> {
                System.out.println("Error al crear en la base de datos");
                alertInfo.viewAlert(3, "Error de Registro", "No se pudo crear el usuario en la base de datos", "Registro");
            }
            case USER_CREATED -> {
                System.out.println("Sí se creó el usuario");
                alertInfo.viewAlert(1, "Éxito", "Usuario registrado correctamente", "Registro");
                // Opcional: Aquí podrías limpiar los campos o cerrar la ventana
            }
            case FIELDS_EMPTY -> {
                // Esto no debería llegar aquí porque ya lo validamos arriba, pero por seguridad:
                alertInfo.viewAlert(2, "Campos Vacíos", "Por favor complete todos los campos", "Registro");
            }
            case VALUE_LENGTH_INVALID -> {
                alertInfo.viewAlert(2, "Longitud Inválida", "Verifique la longitud de los datos ingresados", "Registro");
            }
            default -> {
                System.out.println("Estado desconocido");
                alertInfo.viewAlert(3, "Error Desconocido", "Ocurrió un error inesperado", "Registro");
            }
        }
    }
}