/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exception.UserNotFoundException;
import java.math.BigInteger;
import java.net.URL;
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
import javafx.stage.Stage;
import logic.ClienteFactoria;
import logic.EntradaFactoria;
import model.Cliente;
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
    
    private Stage stage;

    private Usuario user;
    
    private ClienteFactoria clieFac;
    
    private List<Cliente> listaClient;
    
    private static final Logger LOGGER = Logger.getLogger("/controller/ConfirmarCompraController");
    
    
    public void initiStage(Parent root, Usuario user) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("ConfirmarCompra");
        //Activacion de componentes de la ventana
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
    //Método para cerrar la ventana si no se desea comprar
    @FXML
    public void exitHandler(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¿Deseas salir?");

        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            stage.close();
        }
    }
    //Método que realiza la compra de una entrada mediante el id de la entrada y del cliente
    @FXML
    public void comprarEntradaHandler(ActionEvent event){
        //Datos de prueba: "5432123146788766", "7654"
        
        String n_tarjeta = txt_contraReve1.getText();
        String pin = pswPin.getText();
        try{
            List<Cliente> clienteCheck = clieFac.getFactory().filtrarPorTarjeta_XML(Cliente.class, n_tarjeta, pin);
            
            if (clienteCheck != null) {
                listaClient = FXCollections.observableArrayList(clienteCheck);
                if (listaClient.isEmpty()) {
                    throw new UserNotFoundException();
                } else {
                    System.out.println("Puedes comprar");
                }
            } else {
                LOGGER.severe("El método filtrarPorTarjeta_XML ha devuelto un valor nulo");
            }
        }catch(UserNotFoundException e){
            LOGGER.severe("Los datos proporcionados son erroneos o no existen, reviselos de nuevo");
        }catch(Exception e){
            LOGGER.severe("Ha ocurrido un error inesperado");
        }
        
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
    
    
}
