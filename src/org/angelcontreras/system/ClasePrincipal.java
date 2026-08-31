/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.angelcontreras.system;

import javafx.application.Application;
import javafx.stage.Stage;
import org.angelcontreras.system.utils.SceneManager;
import org.angelcontreras.system.utils.ViewFactory;

/**
 *
 * @author informatica
 */
public class ClasePrincipal extends Application {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start (Stage stageRoot){
        SceneManager.getInstanciaSceneManager().setStagePrincipal(stageRoot);
        ViewFactory viewFacto = new ViewFactory();
        viewFacto.viewLogin();
    }
    
}
