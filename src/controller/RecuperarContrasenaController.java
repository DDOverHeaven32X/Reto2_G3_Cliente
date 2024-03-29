/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import chiper.Asimetricocliente;
import static controller.RegistroController.LOGGER;
import exception.UserNotFoundException;
import java.net.URL;
import java.security.PublicKey;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
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
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.ClienteFactoria;
import logic.UsuarioFactoria;
import model.Cliente;
import model.Usuario;

/**
 * Controlador de la ventana de recuperar contraseña
 *
 * @author Adrian
 */
public class RecuperarContrasenaController {

    private Usuario user;

    private Stage stage;

    private final ClienteFactoria clienfac = new ClienteFactoria();

    private final UsuarioFactoria userfac = new UsuarioFactoria();

    private List<Cliente> listaCliente;

    @FXML
    private TextField txt_email;
    @FXML
    private Button btn_cancelar;
    @FXML
    private Button btn_enviar;
    //Aqui asignamos el patron del email
    private static final String patronEmail = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,300}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(patronEmail);

    /**
     * Método que inicializa la ventana con sus valores predeterminados
     *
     * @param root
     */
    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.setTitle("Recuperar Contraseña");
        txt_email.setDisable(false);
        txt_email.requestFocus();
        btn_cancelar.setDisable(false);
        btn_enviar.setDisable(false);
        //Asignacion de botones
        btn_cancelar.setOnAction(this::cerrarVentana);
        btn_enviar.setOnAction(this::sendMail);
        stage.show();

    }

    /**
     * Este método maneja el evento de enviar el correo electrónico para
     * recuperar la contraseña.
     *
     * @param actionevent El evento de acción que desencadena el envío del
     * correo electrónico.
     *
     * @author Diego
     */
    private void sendMail(ActionEvent actionevent) {
        try {
            if (txt_email.getText().trim().length() >= 40) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Demasiados caracteres");
                alert.setContentText("Su correo tiene demasiados caracteres, solo se permite hasta máximo 39 caracteres");

                Optional<ButtonType> answer = alert.showAndWait();
                if (answer.isPresent() && answer.get() == ButtonType.OK) {
                    txt_email.setText("");
                    alert.close();
                    try {
                        throw new Exception("Demasiados caracteres");
                    } catch (Exception ex) {
                        Logger.getLogger(RecuperarContrasenaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return;
                }
            } else if (!EMAIL_PATTERN.matcher(txt_email.getText()).matches()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Patrón incorrecto");
                alert.setContentText("El formato del correo electrónico es incorrecto");

                Optional<ButtonType> answer = alert.showAndWait();
                if (answer.isPresent() && answer.get() == ButtonType.OK) {
                    alert.close();
                    txt_email.setText("");
                    try {
                        throw new Exception("Patrón incorrecto");
                    } catch (Exception ex) {
                        Logger.getLogger(RecuperarContrasenaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return;
                }
            }

            //Si todo sale bien
            List<Usuario> listaUser;
            listaUser = userfac.getFactory().findLogin_XML(Usuario.class, txt_email.getText());
            //Comprueba si existe un usuario con el correo introducido
            if (listaUser.isEmpty()) {
                throw new UserNotFoundException();
            }
            //Si existe recupera el cliente con dicho producto
            Cliente clieNew = new Cliente();

            clieNew.setLogin(txt_email.getText());
            Cliente cli = clienfac.getFactory().find_XML(Cliente.class, listaUser.get(0).getId_user().toString());
            //Setea todos sus datos
            clieNew.setId_user(cli.getId_user());
            clieNew.setCod_postal(cli.getCod_postal());
            clieNew.setDireccion(cli.getDireccion());
            clieNew.setLogin(cli.getLogin());
            clieNew.setNombre_completo(cli.getNombre_completo());
            clieNew.setTelefono(cli.getTelefono());
            clieNew.setTipo_usuario(cli.getTipo_usuario());
            clieNew.setN_tarjeta(cli.getN_tarjeta());
            clieNew.setPin(cli.getPin());
            //Realiza el envio de correo y cambia la contraseña
            clienfac.getFactory().RecuperarContra_XML(clieNew);
            clienfac.getFactory().cambiarContra_XML(clieNew);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle(null);
            alert.setContentText("Se ha enviado un correo con su contraseña temporal");

            Optional<ButtonType> answer = alert.showAndWait();
            if (answer.get() == ButtonType.OK) {
                alert.close();
            }

        } catch (UserNotFoundException e) {
            LOGGER.severe("Patrón erroneo");
        }

    }

    /**
     * Este método maneja el evento de cierre de la ventana.
     *
     * @author Diego
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

    /**
     * Setter de stage
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Setter de user
     *
     * @param user
     */
    public void setUser(Usuario user) {
        this.user = user;
    }

}
