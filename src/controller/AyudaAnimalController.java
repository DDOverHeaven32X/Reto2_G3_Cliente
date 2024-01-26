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
 * FXML Controller class
 *
 * @author 2dam
 */
public class AyudaAnimalController {

    @FXML
    private AnchorPane anchor;
    @FXML
    private WebView webAnimal;
    
    Stage stage = new Stage();
    
    

    public void initStage(Parent root) {

        //init the scene with the root you got from singInController
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnShowing(this::showWindow);
        stage.setTitle("Manual de ventana Animales");
        stage.setResizable(false);
        stage.show();

    }

    private void showWindow(WindowEvent event) {
        WebEngine webEngine = webAnimal.getEngine();
        webEngine.load(getClass().getResource("/html/animal.html").toExternalForm());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }    
    
}
