/**
 * @author Ander
 * El paquete que contiene todos los controladores de las ventanas.
 */
package controller;

import chiper.Asimetricocliente;
import static controller.RegistroController.LOGGER;
import exception.IncorrectCredentialException;
import exception.UserNotFoundException;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.ConnectException;
import java.security.PublicKey;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.ws.rs.ProcessingException;
import logic.ClienteFactoria;
import logic.UsuarioFactoria;
import model.Admin;
import model.Cliente;
import model.Privilegio;
import model.SesionUsuario;
import model.Usuario;

/**
 * Esta clase funciona como controlador de la ventana de Inicio de Sesion.
 *
 * @author Ander, Diego, Adrian
 */
public class InicioSesionController {

    private Stage stage;

    private Usuario user;

    @FXML
    private Pane pane;
    @FXML
    private Label lbl_Inicio;
    @FXML
    private Label error;
    @FXML
    private Button btn_verContra;
    @FXML
    private ImageView img_ojo;
    @FXML
    private TextField txt_contraReve;
    @FXML
    private TextField textEmail;
    @FXML
    private Button btnInicioSesion;
    @FXML
    private Hyperlink hyperlinkCuentaIS;
    @FXML
    private Hyperlink idPasswdOlvidada;
    @FXML
    private PasswordField pswContraseña;
    //Aqui asignamos el patron del email
    private static final String patronEmail = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,300}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(patronEmail);
    //Aqui asignamos el patron de la contraseña
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).+$";
    private static final Pattern PASSWORD__PATTERN = Pattern.compile(PASSWORD_REGEX);

    private static final Logger LOGGER = Logger.getLogger("/controlador/InicioSesionController");

    private UsuarioFactoria userFact = new UsuarioFactoria();

    private ClienteFactoria clieFact = new ClienteFactoria();

    //private Usuario user = new Usuario();
    /**
     * Initializes the controller class.
     *
     * @param root
     */
    public void initStage(Parent root) {

        LOGGER.info("Iniciando la ventana de Inicio de Sesion");

        Scene scene = new Scene(root);
        stage = new Stage();

        //El boton inicio de sesion esta deshabilitado.
        btnInicioSesion.setDisable(true);
        //El campo email estará habilitado.
        textEmail.setDisable(false);
        //El campo contraseña estará habilitado.
        pswContraseña.setDisable(false);
        //El campo contraseña esta visible.
        pswContraseña.setVisible(true);
        //El campo contraReve esta deshabilitado.
        txt_contraReve.setDisable(true);
        //El campo contraReve esta invisible.
        txt_contraReve.setVisible(false);
        //El campo error esta habilitado.
        error.setDisable(false);
        //El texto cuenta esta habilitado.
        hyperlinkCuentaIS.setDisable(false);
        idPasswdOlvidada.setDisable(false);
        //Al inicio de la ventana el foco estará puesto en el campo email del usuario (textEmail).
        textEmail.requestFocus();
        //Hacemos que el lbl error no se vea
        error.setVisible(false);
        //El título de la ventana es “InicioSesion”.
        stage.setTitle("InicioSesion");

        //La ventana no es redimensionable
        stage.setResizable(false);
        //Añadimos la escena al escenario (Stage).
        stage.setScene(scene);

        //Mediante este evento llamamos al metodo de cambiar a la ventana de registro.
        hyperlinkCuentaIS.setOnMouseClicked(event -> {
            handleLblCuentaClick();
        });
        idPasswdOlvidada.setOnMouseClicked(event -> {
            recuperarContraseñaHandler();
        });
        //Mediante esta propiedad llamamos al metodo camposInformados()
        textEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            camposInformados();
        });
        //Mediante esta propiedad llamamos al metodo camposInformados()
        pswContraseña.textProperty().addListener((observable, oldValue, newValue) -> {
            camposInformados();
        });
        //Mediante esta propiedad llamamos al metodo camposInformados()
        txt_contraReve.textProperty().addListener((observable, oldValue, newValue) -> {
            camposInformados();
        });
        //Mediante el siguiente evento llamamos al evento handleBtnRespuesta cuando se hace click sobre el boton verContra.
        btn_verContra.setOnMouseClicked(this::handleBtnRespuesta);

        btn_verContra.setTooltip(new Tooltip("Visualizar/Ocultar contraseña"));
        //Mediante este evento, cuando el usuario haga click sobre el boton de inicar sesion se inicia el metodo handleSignInAction().
        btnInicioSesion.setOnAction(this::handleSignInAction);
        //Este oherramienta muestra un mensaje cuando el raton esta colocado encima del boton de inicio de sesion.
        btnInicioSesion.setTooltip(new Tooltip("Pulsa para inicar sesion"));
        //Mediante este evento llamamos al metodo handeCloseRequest cuando hacemos click sobre el boton X (Boton de cerrar la ventana).
        stage.setOnCloseRequest(this::handleCloseRequest);
        stage.show();
    }

    /**
     * Metodo que se va a ejecutar una vez pulsado el boton signIn en el cual se
     * controlan todos los campos y exception posibles
     *
     * @param event Un parametro devuelto de una accion
     */
    private void handleSignInAction(ActionEvent event) {

        try {
            error.setText("");
            Asimetricocliente asi = new Asimetricocliente();
            PublicKey publicKey;
            if (camposInformados() && maxCarecteres()) {
                Usuario user = new Usuario();
                Cliente client = new Cliente();
                user.setLogin(textEmail.getText());
                user.setContraseña(pswContraseña.getText());
                if (pswContraseña.isVisible()) {
                    user.setContraseña(pswContraseña.getText());
                } else {
                    user.setContraseña(txt_contraReve.getText());
                }
                publicKey = asi.loadPublicKey();

                String contra_crypt_hex = javax.xml.bind.DatatypeConverter.printHexBinary(asi.encryptAndSaveData(pswContraseña.getText(), publicKey));
                //Comprobamos si el usuario está registrado en la base de datos
                List<Usuario> listaUser;
                listaUser = userFact.getFactory().find_XML(Usuario.class, textEmail.getText(), contra_crypt_hex);

                //Si la consulta no devuelve nada se lanza una excepción de UserNotFoundException
                if (listaUser.isEmpty()) {
                    throw new UserNotFoundException();
                }

                Privilegio privi = listaUser.get(0).getTipo_usuario();
                //Si la consulta devuelve algo se setearan los datos User devueltos a un Cliente o un Admin
                if (privi.equals(Privilegio.ADMIN)) {
                    user = listaUser.get(0);
                    SesionUsuario sesionUsuario = SesionUsuario.getSUsuario();
                    sesionUsuario.setUser(user);

                } else {
                    user = listaUser.get(0);
                    SesionUsuario sesionUsuario = SesionUsuario.getSUsuario();
                    sesionUsuario.setUser(user);
                    //Establecemos los atributos tambien al cliente cliente
                    Cliente cliente = clieFact.getFactory().find_XML(Cliente.class, user.getId_user().toString());
                    client.setId_user(cliente.getId_user());
                    client.setN_tarjeta(cliente.getN_tarjeta());
                    client.setPin(cliente.getPin());
                }

                //Abre la ventana de Principal y pasa el dato del usuario a la ventana principal
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Principal.fxml"));
                Parent root = loader.load();
                PrincipalController princiController = ((PrincipalController) loader.getController());
                princiController.setSesionUsuario(user);
                princiController.setUser(user);
                princiController.setClien(client);
                princiController.setStage(stage);
                princiController.initiStage(root);
                stage.close();
            }

        } catch(ProcessingException | ConnectException e){
            textEmail.requestFocus();
            error.setVisible(true);
            error.setText("La conexión al servidor falló, intentelo de nuevo o más tarde");
            LOGGER.severe(e.getMessage());
        }catch (IOException ex) {
            error.setVisible(true);
            error.setText("Ha habido algun error durante el inicio de sesion.");
        } catch (UserNotFoundException e) {
            error.setVisible(true);
            error.setText("Usuario no encontrado.");
        }

    }

    /**
     * Mediante este metodo hacemos el set del escenario.
     *
     * @author Ander
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Mediante este verificamos que no haya campos vacios
     *
     * @return boolean
     */
    private boolean camposInformados() {
        //Validamos que el campo email no esta vacio
        if (textEmail.getText().trim().isEmpty()) {
            //Deshabilitamos el boton de inicio de sesion
            btnInicioSesion.setDisable(true);

            return false;
            //Validamos que el campo contraseña no esta vacio
        } else if (pswContraseña.getText().trim().isEmpty() && txt_contraReve.getText().trim().isEmpty()) {
            //Deshabilitamos el boton de inicio de sesion
            btnInicioSesion.setDisable(true);
            return false;
        } else {
            //Habilitamos el boton de inicio de sesion
            btnInicioSesion.setDisable(false);
            return true;
        }

    }

    /**
     * Este metodo sirve para validar la cantidad de caracteres que va a
     * contener el texto email y la contraseña.
     *
     * @author Ander
     * @return boolean
     */
    private boolean maxCarecteres() {
        //Comprobamos que el campo de email no contiene más de 40 caracteres
        if (textEmail.getText().trim().length() >= 40) {
            //Hacemos que el lbl error se vea
            error.setVisible(true);
            error.setText("No se puede superar los 40 caracteres en el email");
            return false;
            //Comprobamos si la contraseña cumple con el patron
        } else if (!EMAIL_PATTERN.matcher(textEmail.getText()).matches()) {
            error.setVisible(true);
            error.setText("El email no cumple con el patron ");
            return false;
        } else if ((!PASSWORD__PATTERN.matcher(pswContraseña.getText()).matches() && !PASSWORD__PATTERN.matcher(txt_contraReve.getText()).matches()) || (pswContraseña.getText().length() < 8 && txt_contraReve.getText().length() < 8)) {
            //Hacemos que el lbl error se vea
            error.setVisible(true);
            error.setText("La contraseña debe de contener al menos 8 caracteres, una mayuscula y minuscula");
            return false;
        } else {
            return true;
        }

    }

    /**
     * Mediante este label podemos ir a la ventana de Registro.
     *
     * @author Ander
     */
    private void handleLblCuentaClick() {
        try {
            Stage ventanaActual = (Stage) hyperlinkCuentaIS.getScene().getWindow();
            ventanaActual.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Registro.fxml"));
            Parent root = loader.load();

            RegistroController registro = (RegistroController) loader.getController();
            registro.setStage(stage);
            registro.initStage(root);

        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error al cargar la nueva vista", ex);
        }
    }

    /**
     * Este método maneja el evento de hacer clic en el enlace de "Recuperar
     * contraseña".
     *
     * @author Ander
     */
    private void recuperarContraseñaHandler() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RecuperarContrasena.fxml"));
            Parent root = loader.load();

            RecuperarContrasenaController recu = (RecuperarContrasenaController) loader.getController();
            recu.setStage(stage);
            recu.initStage(root);

        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error al cargar la nueva vista", ex);
        }
    }

    /**
     * Este metodo es una verificacion cuando el usuario le da al boton de
     * salir.
     *
     * @author Ander
     * @param event
     */
    private void handleCloseRequest(WindowEvent event) {
        //Creamos un nuevo objeto Alerta
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("EXIT");
        //Mostramos una alerta de confirmacion.
        alert.setContentText("¿Estas seguro que deseas salir de la aplicacion?");

        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            Platform.exit();
        } else {
            event.consume();
        }

    }

    /**
     * Este metodo altera la visibilidad de la contraseña
     *
     * @author Ander
     * @param event
     */
    private void handleBtnRespuesta(MouseEvent event) {

        if (pswContraseña.isVisible()) {
            pswContraseña.setDisable(true);
            pswContraseña.setVisible(false);
            txt_contraReve.setDisable(false);
            txt_contraReve.setVisible(true);

            if (txt_contraReve.isVisible()) {
                txt_contraReve.setText(pswContraseña.getText());

            } else {
                pswContraseña.setText(txt_contraReve.getText());

            }

            img_ojo.setImage(new Image(pswContraseña.isVisible() ? "/imagenes/ojo.png" : "/imagenes/ojo2.png"));
        } else {
            txt_contraReve.setDisable(true);
            txt_contraReve.setVisible(false);
            pswContraseña.setDisable(false);
            pswContraseña.setVisible(true);

            if (pswContraseña.isVisible()) {
                pswContraseña.setText(txt_contraReve.getText());

            } else {
                txt_contraReve.setText(pswContraseña.getText());

            }

            img_ojo.setImage(new Image(pswContraseña.isVisible() ? "/imagenes/ojo.png" : "/imagenes/ojo2.png"));
        }
    }

}
