/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication;

import controller.InicioSesionController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author Ander.
 */
public class Application extends javafx.application.Application{

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/InicioSesion.fxml"));
        Parent root = (Parent) loader.load();
        InicioSesionController inicio = ((InicioSesionController) loader.getController());
        inicio.setStage(primaryStage);
        inicio.initStage(root);
    }

    /**
     * @param args Los argumentos de la linea de comandos.
     */
    public static void main(String[] args) {
        launch(args);
    }

}
