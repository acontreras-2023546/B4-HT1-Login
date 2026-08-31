/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.angelcontreras.system.utils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import org.angelcontreras.system.ClasePrincipal;

/**
 *
 * @author informatica
 */
public class ViewFactory {

    private final String PATH_VIEWS = "/org/angelcontreras/system/view/";

    public Scene loadFileFXML(String nameFXML, int width, int height) {
        String pathOfFile = PATH_VIEWS + nameFXML;
        try {
            FXMLLoader loaderFXML = new FXMLLoader();

            URL urlFile = ClasePrincipal.class.getResource(pathOfFile);
            loaderFXML.setBuilderFactory(new JavaFXBuilderFactory());
            loaderFXML.setLocation(urlFile);

            return new Scene(loaderFXML.load(), width, height);

        } catch (IOException e) {
            throw new UncheckedIOException(e);

        }
    }

    public void loadScene(String nameFXML) {
        Scene scene = null;
        try {
            switch (nameFXML) {
                case "login" -> {
                    SceneManager.getInstanciaSceneManager().getStagePrincipal().setTitle("LOGIN DE USUARIOS");
                    SceneManager.getInstanciaSceneManager().getStagePrincipal().setResizable(false);
                    scene = loadFileFXML("LoginView.fxml", 400, 500);
                }
                case "register" -> {
                    SceneManager.getInstanciaSceneManager().getStagePrincipal().setTitle("REGISTRO DE USUARIO");
                    SceneManager.getInstanciaSceneManager().getStagePrincipal().setResizable(false);
                    scene = loadFileFXML("RegisterView.fxml", 400, 500);
                }
                case "main" -> {
                    SceneManager.getInstanciaSceneManager().getStagePrincipal().setTitle("MENÚ PRINCIPAL");
                    SceneManager.getInstanciaSceneManager().getStagePrincipal().setResizable(true); // El dashboard suele ser redimensionable
                    scene = loadFileFXML("DashBoardView.fxml", 800, 600);
                }
                default ->
                    scene = loadFileFXML("LoginView.fxml", 400, 500);
            }
            SceneManager.getInstanciaSceneManager().changeScene(scene);
        } catch (NullPointerException objetoNUlo) {
            //Alert 
            System.out.println("error load scene");
        }
    }

    public void viewLogin() {
        loadScene("login");
    }

     public void viewRegister() {
        loadScene("register");
    }

}
