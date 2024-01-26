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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    private final ClienteFactoria clienfac = new ClienteFactoria();

    private Cliente client = new Cliente();

    private Usuario user;
    private Stage stage;

    /**
     * Initializes the controller class.
     *
     * @param root
     * @param user
     */
    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Cambiar Contraseña");
        stage.show();

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

    }

    /**
     * Método de cambiar la contraseña
     *
     * @param actionEvent
     */
    public void cambiarContra(ActionEvent actionEvent) {

        if (pswContraseña1.getText().equals(pswContraseña2.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Contraseña redundante");
            alert.setContentText("La contraseña nueva no debe de ser igual a la anterior");
            Optional<ButtonType> answer = alert.showAndWait();
            if (answer.isPresent() && answer.get() == ButtonType.OK) {
                pswContraseña1.setText("");
                pswContraseña2.setText("");
                pswContraseña3.setText("");
                alert.close();
                try {
                    throw new Exception("Contraseñas erroneas");
                } catch (Exception ex) {
                    Logger.getLogger(CambiarContrasenaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (!pswContraseña2.getText().equals(pswContraseña3.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Las contraseñas no coinciden");
            alert.setContentText("Asegurate que la constraseña nueva es igual en la confirmacion de contraseña");
            Optional<ButtonType> answer = alert.showAndWait();
            if (answer.isPresent() && answer.get() == ButtonType.OK) {

                pswContraseña2.setText("");
                pswContraseña3.setText("");
                alert.close();
                try {
                    throw new Exception("Contraseñas erroneas");
                } catch (Exception ex) {
                    Logger.getLogger(CambiarContrasenaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        Asimetricocliente asi = new Asimetricocliente();
        PublicKey publicKey;
        publicKey = asi.loadPublicKey();

        String contra_crypt_hex = javax.xml.bind.DatatypeConverter.printHexBinary(asi.encryptAndSaveData(pswContraseña2.getText(), publicKey));
        client = clienfac.getFactory().find_XML(Cliente.class, user.getId_user().toString());
        client.setContraseña(pswContraseña2.getText());
        clienfac.getFactory().cambiarContra_XML(client);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

}
