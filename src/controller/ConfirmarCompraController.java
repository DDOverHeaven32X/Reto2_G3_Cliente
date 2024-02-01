/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exception.UserNotFoundException;
import java.math.BigInteger;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.ClienteFactoria;
import logic.CompraFactoria;
import logic.EntradaFactoria;
import model.Cliente;
import model.Compra;
import model.CompraId;
import model.Entrada;
import model.Usuario;

/**
 * Controlador de la ventana de confirmación de compra
 *
 * @author Diego
 */
public class ConfirmarCompraController {

    @FXML
    private Button btn_cancelar;
    @FXML
    private Button btn_confirmar;
    @FXML
    private TextField txt_contraReve1;
    @FXML
    private TextField txt_contraReve2;
    @FXML
    private PasswordField pswPin;
    @FXML
    private Pane pane;

    private Stage stage;

    private Usuario user;

    private Cliente client;

    private Entrada entr;

    private ClienteFactoria clieFac = new ClienteFactoria();

    private CompraFactoria comFac = new CompraFactoria();

    private List<Cliente> listaClient;

    private static final Logger LOGGER = Logger.getLogger("/controller/ConfirmarCompraController");

    /**
     * Inicializa la ventana de confirmación de compra.
     *
     * @param root La raíz del nodo de la ventana.
     * @param user El usuario que realiza la compra.
     */
    public void initiStage(Parent root, Usuario user) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("ConfirmarCompra");
        //Activacion de componentes de la ventana
        stage.setResizable(false);
        txt_contraReve1.setDisable(false);
        txt_contraReve1.setVisible(true);
        pswPin.setVisible(true);
        pswPin.setDisable(false);
        btn_cancelar.setDisable(false);
        btn_cancelar.setVisible(true);
        btn_confirmar.setDisable(false);
        btn_confirmar.setVisible(true);
        //Métodos de los botones
        btn_cancelar.setOnAction(this::exitHandler);
        btn_confirmar.setOnAction(this::comprarEntradaHandler);

        stage.show();
        //
    }

    /**
     * Maneja el evento para salir de la ventana de confirmación.
     *
     * @param event El evento de acción que desencadena el cierre de la ventana.
     */
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

    /**
     * Maneja el evento de compra de entrada.
     *
     * @param event El evento de acción que desencadena la compra de la entrada.
     */
    @FXML
    public void comprarEntradaHandler(ActionEvent event) {
        //Datos de prueba: "5432123146788766", "7654"

        String n_tarjeta = txt_contraReve1.getText();
        String pin = pswPin.getText();

        if (n_tarjeta.length() != 16 || pin.length() != 4) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setHeaderText(null);
            alertError.setTitle(null);
            alertError.setContentText("La tarjeta debe tener 16 caracteres y el PIN debe tener 4 caracteres");

            Optional<ButtonType> answer = alertError.showAndWait();
            if (answer.isPresent() && answer.get() == ButtonType.OK) {
                return; // No continuar con la compra si la longitud no es válida
            }
        }

        try {
            List<Cliente> clienteCheck = clieFac.getFactory().filtrarPorTarjeta_XML(Cliente.class, n_tarjeta, pin);

            if (clienteCheck != null) {
                listaClient = FXCollections.observableArrayList(clienteCheck);
                if (listaClient.isEmpty()) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setHeaderText(null);
                    alert2.setTitle(null);
                    alert2.setContentText("La tarjeta introducida no existe");

                    Optional<ButtonType> answer = alert2.showAndWait();
                    if (answer.get() == ButtonType.OK) {
                        alert2.close();
                    }
                } else {
                    //Datos de prueba
                    Cliente clie = new Cliente();
                    clie.setId_user(clienteCheck.get(0).getId_user());

                    //Fecha actual del sistema cuando compra
                    LocalDate localDate = LocalDate.now();
                    Date fecha = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    Compra buy = new Compra();
                    CompraId buyId = new CompraId();
                    buy.setFecha_compra(fecha);
                    buyId.setId_entrada(entr.getId_entrada());
                    buyId.setId_user(clie.getId_user());
                    buy.setCompraId(buyId);

                    comFac.getFactory().create_XML(buy);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setTitle(null);
                    alert.setContentText("Compra realizada con éxito");

                    Optional<ButtonType> answer = alert.showAndWait();
                    if (answer.get() == ButtonType.OK) {
                        ((Stage) this.pane.getScene().getWindow()).close();

                    }

                }
            } else {
                // La lista clienteCheck es nula
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setHeaderText(null);
                alertError.setTitle(null);
                alertError.setContentText("Ha ocurrido un error al verificar la tarjeta");

                Optional<ButtonType> answer = alertError.showAndWait();
                if (answer.get() == ButtonType.OK) {
                    alertError.close();
                }
            }
        } catch (Exception e) {
            LOGGER.severe("Ha ocurrido un error inesperado");
        }

    }

    /**
     * Establece el escenario de la ventana.
     *
     * @param stage El escenario de la ventana.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Establece el usuario que realiza la compra.
     *
     * @param user El usuario que realiza la compra.
     */
    public void setUser(Usuario user) {
        this.user = user;
    }

    /**
     * Establece el cliente que realiza la compra.
     *
     * @param client El cliente que realiza la compra.
     */
    public void setClient(Cliente client) {
        this.client = client;
    }

    /**
     * Establece la entrada que se va a comprar.
     *
     * @param entr La entrada que se va a comprar.
     */
    public void setEntr(Entrada entr) {
        this.entr = entr;
    }

}
