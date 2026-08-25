/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.angelcontreras.system.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import org.angelcontreras.system.utils.ViewFactory;

/**
 *
 * @author informatica
 */
    public class RegisterController implements Initializable{

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }
    
    @FXML
    public void onCancel (MouseEvent event){
        ViewFactory viewFacto = new ViewFactory();
        viewFacto.viewLogin();
    }
    
    
}
