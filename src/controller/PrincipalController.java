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
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Cliente;
import model.Usuario;

/**
 * Controlador de la ventana principal
 *
 * @author Ander, Diego, Adrían
 */
public class PrincipalController {

    @FXML
    private Pane pane;
    @FXML
    private Hyperlink hyp_cambiarContrasena;
    @FXML
    private Pane panelInicio;
    @FXML
    private Label lblUsuario;
    @FXML
    private Label lblEmail;
    @FXML
    private MenuBarController menubar = new MenuBarController();

    private Stage stage;

    private Usuario user;

    private Cliente clien;

    public void initiStage(Parent root, Usuario user, Cliente cliente) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Principal");
        menubar.setUser(user);
        menubar.setClien(cliente);
        //Ponemos el nombre y correo del usuario en sus labels
        lblUsuario.setText("Nombre de usuario: " + user.getNombre_completo());
        lblEmail.setText("Email: " + user.getLogin());
        stage.show();

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public void setClien(Cliente clien) {
        this.clien = clien;
    }

}
