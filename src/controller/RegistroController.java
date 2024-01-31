/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import chiper.Asimetricocliente;
import exception.IncorrectPatternException;
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.security.PublicKey;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.ClienteFactoria;
import model.Cliente;

/**
 * Esta clase funciona como el controlador de la ventana de Registro.
 *
 * @author Diego, Adrian
 */
public class RegistroController {

    private final ClienteFactoria clieFact = new ClienteFactoria();

    @FXML
    private Pane pane;
    @FXML
    private Label lbl_registro;
    @FXML
    private Label lbl_error;
    @FXML
    private Label lbl_nombre;
    @FXML
    private Label lbl_mail;
    @FXML
    private Label lbl_passwrd;
    @FXML
    private Label lbl_repeContra;
    @FXML
    private TextField txt_nombre;
    @FXML
    private TextField txt_email;
    @FXML
    private Hyperlink hyperlinkCuentaR;
    @FXML
    private Button btn_registro;
    @FXML
    private Button btn_verContra;
    @FXML
    private Button btn_verContra2;
    @FXML
    private Button btn_verPin;
    @FXML
    private ImageView img_ojo;
    @FXML
    private ImageView img_ojo2;
    @FXML
    private ImageView img_ojo3;
    @FXML
    private Label lbl_direcion;
    @FXML
    private TextField txt_direccion;
    @FXML
    private Label lbl_zip;
    @FXML
    private Label lbl_tele;
    @FXML
    private TextField txt_tarjeta;
    @FXML
    private TextField txt_zip;
    @FXML
    private TextField txt_tele;
    @FXML
    private TextField txt_contraReve;
    @FXML
    private TextField txt_contraRepeReve;
    @FXML
    private PasswordField psw_contra;
    @FXML
    private PasswordField psw_contraRepe;
    @FXML
    private PasswordField psw_pin;
    @FXML
    private TextField txt_pinReve;

    private Stage stage;

    private String email, contraseña, zip, telefono, nombre;

    private static final String patronZip = "^\\d{5}$";
    private static final Pattern zipMatcher = Pattern.compile(patronZip);

    private static final String patronPhone = "^(\\+34|0034|34)?[6|7|9][0-9]{8}$";
    private static final Pattern phoneMatcher = Pattern.compile(patronPhone);

    private static final String patronTarjeta = "^\\d{16}$";
    private static final Pattern tarjetaMatcher = Pattern.compile(patronTarjeta);

    private static final String patronPin = "^\\d{4}$";
    private static final Pattern pinMatcher = Pattern.compile(patronPin);

    private static final String patronContraseña = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).+$";
    private static final Pattern passwordMatcher = Pattern.compile(patronContraseña);

    private static final String patronEmail = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,300}$";
    private static final Pattern emailMatcher = Pattern.compile(patronEmail);
    protected static final Logger LOGGER = Logger.getLogger("/controller/RegistroController");

    /**
     * Initializes the controller class.
     *
     * @author Ander, Diego
     * @param root
     */
    public void initStage(Parent root) {
        LOGGER.info("Iniciando la ventana de Registro");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //La ventana no es redimensionable
        stage.setResizable(false);
        //La ventana tiene como título "Registro"
        stage.setTitle("Registro");
        //El foco estrá en el nombre
        txt_nombre.requestFocus();
        //El botón está deshabilitado
        btn_registro.setDisable(true);
        //El campo de mostrar errores es invisible
        lbl_error.setVisible(false);
        //El campo del hyper-link esthá habilitado
        hyperlinkCuentaR.setDisable(false);
        //Los campos de mostrar la contraseña estarán deshabilitados e invisibles
        txt_contraReve.setDisable(true);
        txt_contraReve.setVisible(false);
        txt_contraRepeReve.setDisable(true);
        txt_contraRepeReve.setVisible(false);
        txt_pinReve.setDisable(true);
        txt_pinReve.setVisible(false);
        //Evento hyper-enlace que te envia a la ventana de inicio de sesion
        hyperlinkCuentaR.setOnMouseClicked(this::tienesCuenta);
        //Eventos que habilitan la validacion de los textareas
        txt_nombre.textProperty().addListener(this::estanVacios);
        txt_email.textProperty().addListener(this::estanVacios);
        psw_contra.textProperty().addListener(this::estanVacios);
        psw_contraRepe.textProperty().addListener(this::estanVacios);
        txt_direccion.textProperty().addListener(this::estanVacios);
        txt_zip.textProperty().addListener(this::estanVacios);
        txt_tele.textProperty().addListener(this::estanVacios);
        txt_tarjeta.textProperty().addListener(this::estanVacios);
        psw_pin.textProperty().addListener(this::estanVacios);

        //Evento del botón registrarse
        btn_registro.setOnAction(this::registrarBotón);
        btn_registro.setTooltip(new Tooltip("Pulsa para registrarte"));
        //Evento de los botones de visualizar contraseña
        btn_verContra.setOnMouseClicked(event -> revelarContra(event));
        btn_verContra.setTooltip(new Tooltip("Visualizar/Ocultar contraseña"));
        btn_verContra2.setTooltip(new Tooltip("Visualizar/Ocultar contraseña"));
        btn_verPin.setTooltip(new Tooltip("Visualizar/Ocultar pin"));
        btn_verContra2.setOnMouseClicked(event -> revelarContraRepe(event));

        btn_verPin.setOnMouseClicked(event -> revelarPin(event));
        stage.setOnCloseRequest(this::cerrarVentana);
        stage.show();
    }

    //Constructor de la escena
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Abre la ventana de Inicio de sesión
     *
     * @author Diego, Adrián
     * @param event
     */
    private void tienesCuenta(MouseEvent event) {

        try {
            stage.close();
            LOGGER.info("Iniciando la ventana de Inicio de sesión");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/InicioSesion.fxml"));
            Parent root = (Parent) loader.load();
            InicioSesionController signIn = ((InicioSesionController) loader.getController());
            signIn.setStage(stage);
            signIn.initStage(root);

        } catch (IOException e) {
            LOGGER.info("Ha ocurrido un error");
        }
    }

    /**
     * Revela la contraseña de la ventana para el usuario
     *
     * @author Diego, Adrián
     * @param event
     */
    private void revelarContra(MouseEvent event) {
        if (psw_contra.isVisible()) {
            psw_contra.setDisable(true);
            psw_contra.setVisible(false);
            txt_contraReve.setDisable(false);
            txt_contraReve.setVisible(true);

            if (txt_contraReve.isVisible()) {
                txt_contraReve.setText(psw_contra.getText());

            } else {
                psw_contra.setText(txt_contraReve.getText());

            }

            img_ojo.setImage(new Image(psw_contra.isVisible() ? "/imagenes/ojo.png" : "/imagenes/ojo2.png"));
        } else {
            txt_contraReve.setDisable(true);
            txt_contraReve.setVisible(false);
            psw_contra.setDisable(false);
            psw_contra.setVisible(true);

            if (psw_contra.isVisible()) {
                psw_contra.setText(txt_contraReve.getText());

            } else {
                txt_contraReve.setText(psw_contra.getText());

            }

            img_ojo.setImage(new Image(psw_contra.isVisible() ? "/imagenes/ojo.png" : "/imagenes/ojo2.png"));
        }
    }

    /**
     * Revela la otra contraseña de la ventana para el usuario
     *
     * @author Diego, Adrián
     * @param event
     */
    private void revelarContraRepe(MouseEvent event) {
        if (psw_contraRepe.isVisible()) {
            psw_contraRepe.setDisable(true);
            psw_contraRepe.setVisible(false);
            txt_contraRepeReve.setDisable(false);
            txt_contraRepeReve.setVisible(true);

            if (txt_contraRepeReve.isVisible()) {
                txt_contraRepeReve.setText(psw_contraRepe.getText());

            } else {
                psw_contraRepe.setText(txt_contraRepeReve.getText());

            }

            img_ojo2.setImage(new Image(psw_contraRepe.isVisible() ? "/imagenes/ojo.png" : "/imagenes/ojo2.png"));
        } else {
            txt_contraRepeReve.setDisable(true);
            txt_contraRepeReve.setVisible(false);
            psw_contraRepe.setDisable(false);
            psw_contraRepe.setVisible(true);

            if (psw_contraRepe.isVisible()) {
                psw_contraRepe.setText(txt_contraRepeReve.getText());

            } else {
                txt_contraRepeReve.setText(psw_contraRepe.getText());
            }

            img_ojo2.setImage(new Image(psw_contraRepe.isVisible() ? "/imagenes/ojo.png" : "/imagenes/ojo2.png"));
        }
    }

    private void revelarPin(MouseEvent event) {
        if (psw_pin.isVisible()) {
            psw_pin.setDisable(true);
            psw_pin.setVisible(false);
            txt_pinReve.setDisable(false);
            txt_pinReve.setVisible(true);

            if (txt_pinReve.isVisible()) {
                txt_pinReve.setText(psw_pin.getText());

            } else {
                psw_pin.setText(txt_pinReve.getText());

            }

            img_ojo3.setImage(new Image(psw_pin.isVisible() ? "/imagenes/ojo.png" : "/imagenes/ojo2.png"));
        } else {
            txt_pinReve.setDisable(true);
            txt_pinReve.setVisible(false);
            psw_pin.setDisable(false);
            psw_pin.setVisible(true);

            if (psw_pin.isVisible()) {
                psw_pin.setText(txt_pinReve.getText());

            } else {
                txt_pinReve.setText(psw_pin.getText());
            }

            img_ojo3.setImage(new Image(psw_pin.isVisible() ? "/imagenes/ojo.png" : "/imagenes/ojo2.png"));
        }
    }

    /**
     * Método del botón de la ventana, recoge la información de la ventana para
     * hacer el registro
     *
     * @author Diego, Adrián
     * @param event
     */
    private void registrarBotón(ActionEvent event) {
        /*
          Método para Registrar al usuario en la base de datos del programa, realiza validaciones de patrones y otros
          protocolos de seguridad
         */
        try {
            //Comprobamos que el nombre no excede de 15 carácteres
            if (txt_nombre.getText().trim().length() > 15) {
                txt_nombre.setText("");
                throw new IncorrectPatternException("El nombre de usuario es demasiado largo.");

            }
            //Comprobamos el formato del correo y si no excecde de 40 carácteres
            email = txt_email.getText();
            if (!emailMatcher.matcher(email).matches() || email.length() > 40) {
                txt_email.setText("");
                throw new IncorrectPatternException("El formato no está permitido (ej, xxx@xxx.xxx) y no debe tener mas de 40 caracteres.");
            }
            //Comprobamos que la contraseña se alfanumerica, tenga mayúsculas, minúsculas y tenga más de 8 carácteres
            contraseña = psw_contra.getText();
            if ((!(passwordMatcher.matcher(contraseña).matches()) || contraseña.length() < 8) && (!passwordMatcher.matcher(txt_contraReve.getText()).matches() || txt_contraReve.getText().length() < 8)) {
                psw_contra.setText("");
                txt_contraReve.setText("");
                throw new IncorrectPatternException("Formato erroneo, introduce más 8 carácteres alfanuméricos"
                        + " añade una minuscula o mayúscula al menos.");
                //Comprobamos que las contraseñas coinciden
            } else if ((!psw_contra.getText().equals(psw_contraRepe.getText())) && (!passwordMatcher.matcher(txt_contraRepeReve.getText()).matches() || txt_contraRepeReve.getText().length() < 8)) {
                psw_contraRepe.setText("");
                txt_contraRepeReve.setText("");
                //throw new IncorrectCredentialException("Las contraseñas no coinciden.");
            }
            //Comprobamos que el código postal tenga un formato de 5 digitos
            zip = txt_zip.getText();
            if (!(zipMatcher.matcher(zip).matches())) {
                txt_zip.setText("");
                throw new IncorrectPatternException("El formato no está permitido, (ej, 45320).");
            }
            //Comprobamos que el teléfono tiene el patrón estándar español de 9 digitos
            telefono = "+34" + txt_tele.getText();
            if (!(phoneMatcher.matcher(telefono).matches())) {
                txt_tele.setText("");
                throw new IncorrectPatternException("El formato no está permitido, (ej, +34 643 567 453/ 945 564 234).");
            }
            if (!(tarjetaMatcher.matcher(txt_tarjeta.getText()).matches())) {
                txt_tarjeta.setText("");
                throw new IncorrectPatternException("La tarjeta debe contener extactamente 16 caracteres numericos.");
            }

            if (!(pinMatcher.matcher(psw_pin.getText()).matches())) {
                txt_pinReve.setText("");
                psw_pin.setText("");
                throw new IncorrectPatternException("El pin debe contener 4 exactamente caracteres numericos.");
            }
            /*
                Las excepciones de IncorrectPasswordException e IncorrectPatternException se mostrarán en el
                lbl_error, las excepciones genericas se mostraran en consola a través de un logger
             */
            //Si los parametros son correctos creamos el nuevo cliente
            Cliente clie = new Cliente();
            Asimetricocliente asi = new Asimetricocliente();
            PublicKey publicKey;
            //Medida para pasar datos String a integers si hace falta
            String telef = txt_tele.getText();
            String tarjeta = txt_tarjeta.getText();
            String pin = psw_pin.getText();

            String codigoPost = txt_zip.getText();

            Integer codPostal = Integer.parseInt(codigoPost);
            Integer telefono = Integer.parseInt(telef);
            Integer pinSecreto = Integer.parseInt(pin);
            Long numTarj = Long.parseLong(tarjeta);

            clie.setNombre_completo(txt_nombre.getText());
            clie.setLogin(txt_email.getText());

            publicKey = asi.loadPublicKey();

            String contra_crypt_hex = javax.xml.bind.DatatypeConverter.printHexBinary(asi.encryptAndSaveData(psw_contra.getText(), publicKey));
            System.out.println(contra_crypt_hex);
            clie.setContraseña(contra_crypt_hex);
            clie.setDireccion(txt_direccion.getText());
            clie.setCod_postal(codPostal);
            clie.setTelefono(telefono);
            clie.setN_tarjeta(numTarj);
            clie.setPin(pinSecreto);
            //Creamos el usuario
            clieFact.getFactory().create_XML(clie);

            //Mostramos al usuario que el registro ha sido satisfactorio
            Alert ventana = new Alert(Alert.AlertType.INFORMATION);
            ventana.setHeaderText(null);
            ventana.setTitle("Enhorabuena");
            ventana.setContentText("Has logrado registrarte");

            Optional<ButtonType> accion = ventana.showAndWait();
            if (accion.get() == ButtonType.OK) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/InicioSesion.fxml"));
                Parent root = (Parent) loader.load();
                InicioSesionController signIn = ((InicioSesionController) loader.getController());
                signIn.setStage(stage);
                signIn.initStage(root);
                stage.close();
            } else {
                txt_nombre.setText("");
                txt_email.setText("");
                psw_contra.setText("");
                psw_contraRepe.setText("");
                txt_contraReve.setText("");
                txt_contraRepeReve.setText("");
                txt_direccion.setText("");
                txt_zip.setText("");
                txt_tele.setText("");
                lbl_error.setText("");
                txt_pinReve.setText("");
                psw_pin.setText("");
                txt_tarjeta.setText("");
                txt_nombre.requestFocus();
                event.consume();
            }
            //Control de excepciones
        } catch (IncorrectPatternException e) {
            txt_nombre.requestFocus();
            lbl_error.setVisible(true);
            lbl_error.setText(e.getMessage());
            LOGGER.severe(e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(RegistroController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Valida los campos y habilita el botón de registro si están validados
     *
     * @author Diego, Adrián
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void estanVacios(ObservableValue observable, Object oldValue, Object newValue) {
        /*
           Comprueban que los campos de texto no están vacios, si lo están el botón estará deshabilitado
           si por el contrario, no están vacios, el botón de registro se habilita
         */
        if (!txt_nombre.getText().trim().isEmpty()
                && !txt_email.getText().trim().isEmpty()
                && !txt_direccion.getText().trim().isEmpty()
                && !txt_tele.getText().trim().isEmpty()
                && !txt_zip.getText().trim().isEmpty()
                && (!psw_contra.getText().trim().isEmpty() || !txt_contraReve.getText().trim().isEmpty())
                && (!psw_contraRepe.getText().trim().isEmpty() || !txt_contraRepeReve.getText().trim().isEmpty()) && !txt_tarjeta.getText().trim().isEmpty() && (!txt_pinReve.getText().trim().isEmpty() || !psw_pin.getText().trim().isEmpty())) {
            btn_registro.setDisable(false);
        } else {
            /*
                Cada vez que salte un error y se muestre en el lbl_error si se escribe en cualquiera
                de los campos el texto se vaciará y se hará invisible
             */
            btn_registro.setDisable(true);
            lbl_error.setText("");
            lbl_error.setVisible(false);
        }
    }

    /**
     * Método para cerrar la ventana
     *
     * @author Diego, Adrián
     * @param event
     */
    private void cerrarVentana(Event event) {
        /*
            Método para cerrar la ventana desde el botón X
         */
        Alert ventana = new Alert(Alert.AlertType.CONFIRMATION);
        ventana.setHeaderText(null);
        ventana.setTitle("Advertencia");
        ventana.setContentText("¿Estas seguro que deseas salir de la aplicacion?");
        Optional<ButtonType> accion = ventana.showAndWait();
        if (accion.get() == ButtonType.OK) {
            Platform.exit();
        } else {
            event.consume();
        }
    }
}
