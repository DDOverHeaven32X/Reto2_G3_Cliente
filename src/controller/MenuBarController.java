/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import model.Cliente;
import model.SesionUsuario;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author Diego, Ander, Adrian
 */
public class MenuBarController {

    private Stage stage;

    private Cliente clien;

    SesionUsuario sUsuario = SesionUsuario.getSUsuario();

    private Usuario user = sUsuario.getUser();

    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuPrincipal;
    @FXML
    private Menu menuNavegar;
    @FXML
    private MenuItem mItemAnimales;
    @FXML
    private MenuItem mItemZonas;
    @FXML
    private MenuItem mItemEntradas;
    @FXML
    private Menu menuAyuda;
    @FXML
    private MenuItem mItemManual;
    @FXML
    private MenuItem mItemDocumentacion;
    @FXML
    private Menu menuCerrarSesion;

    /**
     * Acción del menú para ver la ventana de administración de animales.
     *
     * @param event Evento que desencadena la acción.
     */
    @FXML
    private void miAnimales(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Animal.fxml"));
            Parent root = (Parent) loader.load();
            AnimalController aniController = ((AnimalController) loader.getController());

            aniController.setUsuario(user);
            aniController.setStage(stage);
            aniController.initiStage(root);
            ((Stage) this.menuBar.getScene().getWindow()).close();
        } catch (IOException e) {

        }
    }

    /**
     * Acción del menú para ver la ventana de administración de zonas.
     *
     * @param event Evento que desencadena la acción.
     */
    @FXML
    private void miZonas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Zona.fxml"));
            Parent root = (Parent) loader.load();
            ZonaController zonController = ((ZonaController) loader.getController());
            zonController.setUsuario(user);
            zonController.setStage(stage);
            zonController.initiStage(root);
            ((Stage) this.menuBar.getScene().getWindow()).close();
        } catch (IOException e) {

        }
    }

    /**
     * Acción del menú para ver la ventana de administración de entradas.
     *
     * @param event Evento que desencadena la acción.
     */
    @FXML
    private void miEntradas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Entrada.fxml"));
            Parent root = (Parent) loader.load();
            EntradaController entController = ((EntradaController) loader.getController());
            entController.setStage(stage);
            entController.setClien(clien);
            entController.setUser(user);
            entController.initiStage(root, user, clien);
            ((Stage) this.menuBar.getScene().getWindow()).close();
        } catch (IOException e) {

        }
    }

    /**
     * Acción del menú para volver a la ventana principal.
     *
     * @param event Evento que desencadena la acción.
     */
    @FXML
    private void miPrincipal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Principal.fxml"));
            Parent root = (Parent) loader.load();
            PrincipalController priController = ((PrincipalController) loader.getController());
            priController.setStage(stage);
            priController.setClien(clien);
            priController.setUser(user);
            priController.initiStage(root);
            ((Stage) this.menuBar.getScene().getWindow()).close();
        } catch (IOException e) {

        }
    }

    /**
     * Acción del menú para cerrar la sesión actual y volver a la ventana de
     * inicio de sesión.
     *
     * @param event Evento que desencadena la acción.
     */
    @FXML
    private void miSesión(ActionEvent event) {
        try {
            //Creamos un nuevo objeto Alerta
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("EXIT");
            //Mostramos una alerta de confirmacion.
            alert.setContentText("¿Estas seguro que deseas cerrar sesion?");

            Optional<ButtonType> answer = alert.showAndWait();
            if (answer.get() == ButtonType.OK) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/InicioSesion.fxml"));
                Parent root = (Parent) loader.load();
                InicioSesionController iniController = ((InicioSesionController) loader.getController());
                iniController.setStage(stage);
                iniController.initStage(root);
                ((Stage) this.menuBar.getScene().getWindow()).close();
            } else {
                event.consume();
            }
        } catch (IOException e) {

        }
    }

    /**
     * Acción del menú para acceder a la sección de ayuda según la ventana
     * actual.
     *
     * @param event Evento que desencadena la acción.
     */
    @FXML
    private void miAyuda(ActionEvent event) {
        try {
            FXMLLoader loader;
            Parent root;

            switch (((Stage) this.menuBar.getScene().getWindow()).getTitle()) {
                case "Animal":
                    loader = new FXMLLoader(getClass().getResource("/view/AyudaAnimal.fxml"));
                    root = (Parent) loader.load();
                    AyudaAnimalController ayudaController = ((AyudaAnimalController) loader.getController());
                    ayudaController.setStage(stage);
                    ayudaController.initStage(root);
                    break;
                case "Zona":
                    loader = new FXMLLoader(getClass().getResource("/view/AyudaZona.fxml"));
                    root = (Parent) loader.load();
                    AyudaZonaController zonaController = ((AyudaZonaController) loader.getController());
                    zonaController.setStage(stage);
                    zonaController.initStage(root);
                    break;
                case "Entrada":
                    loader = new FXMLLoader(getClass().getResource("/view/AyudaEntrada.fxml"));
                    root = (Parent) loader.load();
                    AyudaEntradaController ayuController = ((AyudaEntradaController) loader.getController());
                    ayuController.setStage(stage);
                    ayuController.initStage(root);
                    break;
                default:

            }
        } catch (IOException e) {

        }
    }

    /**
     * Establece el usuario actual en el controlador de la barra de menú.
     *
     * @param user Usuario actual.
     */
    public void setUser(Usuario user) {
        this.user = user;
    }

    /**
     * Establece el cliente actual en el controlador de la barra de menú.
     *
     * @param clien Cliente actual.
     */
    public void setClien(Cliente clien) {
        this.clien = clien;
    }

}
