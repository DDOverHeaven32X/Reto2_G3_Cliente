/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Usuario;

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

    private Usuario user;
    private Stage stage;

    /**
     * Initializes the controller class.
     *
     * @param root
     */
    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        btn_cancelar.setOnAction(this::exitHandler);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Cambiar Contraseña");
        stage.show();
    }

    //Método para cerrar la ventana si no se desea comprar
    @FXML
    public void exitHandler(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¿Deseas salir?");

        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            stage.close();
        }
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }

    void setUser(Usuario user) {
        this.user = user;
    }

}
