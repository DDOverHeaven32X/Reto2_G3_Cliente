/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Controlador FXML para la ventana de ayuda de la sección de animales.
 * Permite mostrar un manual relacionado con el funcionamiento de la sección de animales.
 * Esta clase carga un archivo HTML que contiene la ayuda en un WebView.
 * El método initStage inicializa la ventana y muestra el contenido HTML.
 *
 * @author Adrian
 */
public class AyudaAnimalController {

    @FXML
    private AnchorPane anchor;
    @FXML
    private WebView webAnimal;
    
    Stage stage = new Stage();
    
    /**
     * Inicializa la ventana de ayuda de la sección de animales.
     * Carga el contenido HTML en un WebView.
     *
     * @param root El nodo raíz de la escena de la ventana de ayuda.
     */
    public void initStage(Parent root) {

        // Inicializa la escena con el nodo raíz obtenido del controlador singInController
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnShowing(this::showWindow);
        stage.setTitle("Manual de ventana Animales");
        stage.setResizable(false);
        stage.show();

    }

    /**
     * Carga el contenido HTML en el WebView cuando se muestra la ventana.
     *
     * @param event El evento de ventana que indica que la ventana está siendo mostrada.
     */
    private void showWindow(WindowEvent event) {
        WebEngine webEngine = webAnimal.getEngine();
        webEngine.load(getClass().getResource("/html/animal.html").toExternalForm());
    }

    /**
     * Establece el Stage de la ventana.
     *
     * @param stage El Stage de la ventana.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }  
}