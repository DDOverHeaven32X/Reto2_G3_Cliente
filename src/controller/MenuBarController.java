/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
 * @author 2dam
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

    @FXML
    private void miAnimales(ActionEvent event) {

        try {
            ((Stage) this.menuBar.getScene().getWindow()).close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Animal.fxml"));
            Parent root = (Parent) loader.load();
            AnimalController aniController = ((AnimalController) loader.getController());
            
            aniController.setUsuario(user);
            aniController.setStage(stage);
            aniController.initiStage(root);

        } catch (IOException e) {

        }
    }

    @FXML
    private void miZonas(ActionEvent event) {
        try {
            ((Stage) this.menuBar.getScene().getWindow()).close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Zona.fxml"));
            Parent root = (Parent) loader.load();
            ZonaController zonController = ((ZonaController) loader.getController());
            zonController.setUsuario(user);
            zonController.setStage(stage);
            zonController.initiStage(root);
        } catch (IOException e) {

        }
    }

    @FXML
    private void miEntradas(ActionEvent event) {
        try {
            ((Stage) this.menuBar.getScene().getWindow()).close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Entrada.fxml"));
            Parent root = (Parent) loader.load();
            EntradaController entController = ((EntradaController) loader.getController());
            entController.setStage(stage);
            entController.setClien(clien);
            entController.setUser(user);

            entController.initiStage(root, user, clien);
        } catch (IOException e) {

        }
    }

    @FXML
    private void miPrincipal(ActionEvent event) {
        try {
            ((Stage) this.menuBar.getScene().getWindow()).close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Principal.fxml"));
            Parent root = (Parent) loader.load();
            PrincipalController priController = ((PrincipalController) loader.getController());
            priController.setStage(stage);
            priController.setClien(clien);
            priController.setUser(user);
            priController.initiStage(root);
        } catch (IOException e) {

        }
    }

    @FXML
    private void miSesión(ActionEvent event) {
        try {
            ((Stage) this.menuBar.getScene().getWindow()).close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/InicioSesion.fxml"));
            Parent root = (Parent) loader.load();
            InicioSesionController iniController = ((InicioSesionController) loader.getController());
            iniController.setStage(stage);
            iniController.initStage(root);
        } catch (IOException e) {
            
        }
    }

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
                    System.out.println("Estas en zona");
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

    public void setUser(Usuario user) {
        this.user = user;
    }

    public void setClien(Cliente clien) {
        this.clien = clien;
    }

}
