/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import chiper.Asimetricocliente;
import java.net.URL;
import java.security.PublicKey;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.ws.rs.client.Client;
import logic.ClienteFactoria;
import logic.UsuarioFactoria;
import model.Cliente;
import model.Usuario;

/**
 * Controlador de la ventana de cambiar contraseña
 *
 * @author Ander
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

    private final ClienteFactoria clienfac = new ClienteFactoria();

    private Cliente client = new Cliente();

    private Usuario user;
    private Stage stage;

    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Cambiar Contraseña");

        //Inicialicazion de los elementos de la ventana
        btn_cancelar.setDisable(false);
        btn_cambiar.setDisable(false);
        btn_verContraNueva.setDisable(false);
        btn_verContraNueva2.setDisable(false);
        btn_verContraVieja.setDisable(false);
        pswContraseña1.setVisible(true);
        pswContraseña2.setVisible(true);
        pswContraseña3.setVisible(true);
        img_ojo21.setVisible(true);
        img_ojo212.setVisible(true);
        //Asignacion de botones
        btn_cambiar.setOnAction(this::cambiarContra);
        btn_verContraVieja.setOnMouseClicked(event -> revelarContra(event));
        btn_verContraNueva.setOnMouseClicked(event -> revelarContra2(event));
        btn_verContraNueva2.setOnMouseClicked(event -> revelarContra3(event));
        btn_cancelar.setOnAction(this::cerrarVentana);

        stage.show();
    }

    /**
     * Método de cambiar la contraseña
     *
     * @param actionEvent
     */
    public void cambiarContra(ActionEvent actionEvent) {
        //Comprobaciones de las contraseñas para que sean corectas

        

        if (!pswContraseña2.getText().equals(pswContraseña3.getText())
                || pswContraseña2.getText().isEmpty() || pswContraseña3.getText().isEmpty()) {
            mostrarAlerta("Contraseña incorrecta", "Las contraseñas no coinciden o están vacías.");
            return;
        }
        if (!client.getContrasena().equals(pswContraseña1.getText())) {
            mostrarAlerta("Contraseña incorrecta", "La contraseña es incorrecta.");
            return;
        }
        Asimetricocliente asi = new Asimetricocliente();
        PublicKey publicKey;
        publicKey = asi.loadPublicKey();
        //Buscamos el cliente que ha solicitado el cambio de contraseña y sobreescribimos los datos cambiados por el
        String contra_crypt_hex = javax.xml.bind.DatatypeConverter.printHexBinary(asi.encryptAndSaveData(pswContraseña2.getText(), publicKey));
        Cliente cli = clienfac.getFactory().find_XML(Cliente.class, user.getId_user().toString());
        Cliente clieNew = new Cliente();
        clieNew.setContrasena(contra_crypt_hex);
        clieNew.setId_user(user.getId_user());
        clieNew.setCod_postal(cli.getCod_postal());
        clieNew.setDireccion(cli.getDireccion());
        clieNew.setLogin(cli.getLogin());
        clieNew.setNombre_completo(cli.getNombre_completo());
        clieNew.setTelefono(cli.getTelefono());
        clieNew.setTipo_usuario(cli.getTipo_usuario());
        clieNew.setN_tarjeta(cli.getN_tarjeta());
        clieNew.setPin(cli.getPin());

        clienfac.getFactory().cambiarContra_XML(clieNew);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText(user.getNombre_completo() + " su contraseña ha sido cambiada");

        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            stage.close();
        }
    }

    // Si todas las validaciones pasan, entonces la contraseña es correcta
// Aquí deberías realizar el cambio de contraseña o cualquier otra acción necesaria
// Método para mostrar una alerta
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Método que revela las contraseñas
     *
     * @param event
     */
    private void revelarContra(MouseEvent event) {
        if (pswContraseña1.isVisible()) {
            pswContraseña1.setDisable(true);
            pswContraseña1.setVisible(false);
            txt_contraReve1.setDisable(false);
            txt_contraReve1.setVisible(true);

            if (txt_contraReve1.isVisible()) {
                txt_contraReve1.setText(pswContraseña1.getText());

            } else {
                pswContraseña1.setText(txt_contraReve1.getText());

            }

            img_ojo21.setImage(new Image(pswContraseña1.isVisible() ? "/imagenes/ojo.png" : "/imagenes/ojo2.png"));
        } else {
            txt_contraReve1.setDisable(true);
            txt_contraReve1.setVisible(false);
            pswContraseña1.setDisable(false);
            pswContraseña1.setVisible(true);

            if (pswContraseña1.isVisible()) {
                pswContraseña1.setText(txt_contraReve1.getText());

            } else {
                txt_contraReve1.setText(pswContraseña1.getText());

            }

            img_ojo21.setImage(new Image(pswContraseña1.isVisible() ? "/imagenes/ojo.png" : "/imagenes/ojo2.png"));
        }
    }

    private void revelarContra2(MouseEvent event) {
        if (pswContraseña2.isVisible()) {
            pswContraseña2.setDisable(true);
            pswContraseña2.setVisible(false);
            txt_contraReve2.setDisable(false);
            txt_contraReve2.setVisible(true);

            if (txt_contraReve2.isVisible()) {
                txt_contraReve2.setText(pswContraseña2.getText());

            } else {
                pswContraseña2.setText(txt_contraReve2.getText());

            }

            img_ojo211.setImage(new Image(pswContraseña2.isVisible() ? "/imagenes/ojo.png" : "/imagenes/ojo2.png"));
        } else {
            txt_contraReve2.setDisable(true);
            txt_contraReve2.setVisible(false);
            pswContraseña2.setDisable(false);
            pswContraseña2.setVisible(true);

            if (pswContraseña2.isVisible()) {
                pswContraseña2.setText(txt_contraReve2.getText());

            } else {
                txt_contraReve2.setText(pswContraseña2.getText());

            }

            img_ojo211.setImage(new Image(pswContraseña2.isVisible() ? "/imagenes/ojo.png" : "/imagenes/ojo2.png"));
        }
    }

    private void revelarContra3(MouseEvent event) {
        if (pswContraseña3.isVisible()) {
            pswContraseña3.setDisable(true);
            pswContraseña3.setVisible(false);
            txt_contraReve3.setDisable(false);
            txt_contraReve3.setVisible(true);

            if (txt_contraReve3.isVisible()) {
                txt_contraReve3.setText(pswContraseña3.getText());

            } else {
                pswContraseña3.setText(txt_contraReve3.getText());

            }

            img_ojo212.setImage(new Image(pswContraseña3.isVisible() ? "/imagenes/ojo.png" : "/imagenes/ojo2.png"));
        } else {
            txt_contraReve3.setDisable(true);
            txt_contraReve3.setVisible(false);
            pswContraseña3.setDisable(false);
            pswContraseña3.setVisible(true);

            if (pswContraseña3.isVisible()) {
                pswContraseña3.setText(txt_contraReve3.getText());

            } else {
                txt_contraReve3.setText(pswContraseña3.getText());

            }

            img_ojo212.setImage(new Image(pswContraseña3.isVisible() ? "/imagenes/ojo.png" : "/imagenes/ojo2.png"));
        }
    }

    /**
     * Método que cierra la ventana
     *
     * @param event
     */
    private void cerrarVentana(Event event) {
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

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

}
