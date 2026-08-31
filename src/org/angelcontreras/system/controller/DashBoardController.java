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
        viewFactory.viewLogin();
    }
    
    @FXML public void onInicio(MouseEvent event) {}
    @FXML public void onPerfil(MouseEvent event) {}
    @FXML public void onConfiguracion(MouseEvent event) {}
}