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
import javafx.scene.Scene;
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
    
    private Usuario user=sUsuario.getUser();

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
    private Menu menuCerrarSesion;

    @FXML
    private void miAnimales(ActionEvent event) {

        try {
            ((Stage) this.menuBar.getScene().getWindow()).close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Animal.fxml"));
            Parent root = (Parent) loader.load();
            AnimalController aniController = ((AnimalController) loader.getController());
            
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
    
    public void setUser(Usuario user) {
        this.user = user;
    }

    public void setClien(Cliente clien) {
        this.clien = clien;
    }
    

}
