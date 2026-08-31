package org.angelcontreras.system.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import org.angelcontreras.system.utils.ViewFactory;

public class DashBoardController implements Initializable {

    private ViewFactory viewFactory = new ViewFactory();

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    @FXML
    public void onLogout(MouseEvent event) {
        // Acción Crítica del PDF: "destruir la escena actual y devolver al usuario al Login"
        // Al llamar a viewLogin(), el SceneManager reemplaza la escena, logrando este efecto.
        viewFactory.viewLogin();
    }
    
    // Métodos placeholder para los otros botones (el PDF dice "área vacía donde se cargarán futuros formularios")
    @FXML public void onInicio(MouseEvent event) {}
    @FXML public void onPerfil(MouseEvent event) {}
    @FXML public void onConfiguracion(MouseEvent event) {}
}