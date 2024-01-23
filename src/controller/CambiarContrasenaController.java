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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Cliente;

/**
 * FXML Controller class
 *
 * @author Adrian
 */
public class CambiarContrasenaController {

    @FXML
    private Button btn_cancelar;
    @FXML
    private Button btn_cambiar;
    @FXML
    private TextField txt_contraReve1;
    @FXML
    private TextField txt_contraReve2;
    @FXML
    private TextField txt_contraReve3;
    @FXML
    private Button btn_verContraVieja;
    @FXML
    private ImageView img_ojo21;
    @FXML
    private Button btn_verContraNueva;
    @FXML
    private ImageView img_ojo211;
    @FXML
    private Button btn_verContraNueva2;
    @FXML
    private ImageView img_ojo212;
    @FXML
    private PasswordField pswContraseña1;
    @FXML
    private PasswordField pswContraseña2;
    @FXML
    private PasswordField pswContraseña3;

    private Stage stage;
    private Cliente clien;

    /**
     * Initializes the controller class.
     * @param root
     */
    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Cambiar Contraseña");
        stage.show();
    }

    void setStage(Stage stage, Cliente clien) {
        this.stage = stage;
        this.clien = clien;
    }

}
