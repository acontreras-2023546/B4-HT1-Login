/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.angelcontreras.system.utils;

/**
 *
 * @author angel
 */

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertInformation {

    /**
     * Constructor vacío público
     */
    public AlertInformation() {
    }

    /**
     * Muestra una alerta de JavaFX.
     * 
     * @param tipoAlerta  Número que define el tipo de alerta (1: Info, 2: Warning, 3: Error, 4: Confirm, default: None)
     * @param titulo      El título de la ventana de la alerta
     * @param encabezado  El texto del encabezado (puede ser null para ocultarlo)
     * @param mensaje     El mensaje principal o contenido de la alerta
     */
    public void viewAlert(int tipoAlerta, String titulo, String encabezado, String mensaje) {
        // Variable local de tipo AlertType
        AlertType tipo = switch (tipoAlerta) {
            case 1 -> AlertType.INFORMATION;
            case 2 -> AlertType.WARNING;
            case 3 -> AlertType.ERROR;
            case 4 -> AlertType.CONFIRMATION;
            case 5 -> AlertType.NONE;
            default -> AlertType.INFORMATION;
        };

        // Creación y configuración de la alerta
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(mensaje);
        
        // Mostrar la alerta y esperar a que el usuario la cierre
        alert.showAndWait();
    }
}
