/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 2dam
 */
public class PrincipalController {

    @FXML
    private Pane pane;
    @FXML
    private Hyperlink hyp_cambiarContrasena;
    @FXML
    private Pane panelInicio;
    
    private Stage stage;

   
    public void initiStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Principal");
        stage.show();
    }    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
}
