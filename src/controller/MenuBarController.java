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

/**
 * FXML Controller class
 *
 * @author 2dam
 */
public class MenuBarController{
    
    private Stage stage;

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
    private void miAnimales(ActionEvent event){
        
        try{
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Animal.fxml"));
            Parent root = (Parent) loader.load();
            AnimalController aniController = ((AnimalController) loader.getController());
            aniController.setStage(stage);
            aniController.initiStage(root);
            
        } catch (IOException e) {
            
        }
    }
    @FXML
    private void miZonas(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Zona.fxml"));
            Parent root = (Parent) loader.load();
            ZonaController zonController = ((ZonaController) loader.getController());
            zonController.setStage(stage);
            zonController.initiStage(root);
        } catch (IOException e) {
            
        }
    }
    @FXML
    private void miEntradas(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Entrada.fxml"));
            Parent root = (Parent) loader.load();
            EntradaController entController = ((EntradaController) loader.getController());
            entController.setStage(stage);
            entController.initiStage(root);
        } catch (IOException e) {
            
        }
    }
    @FXML
    private void miPrincipal(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Principal.fxml"));
            Parent root = (Parent) loader.load();
            PrincipalController priController = ((PrincipalController) loader.getController());
            priController.setStage(stage);
            priController.initiStage(root);
        } catch (IOException e) {
            
        }
    }
    @FXML
    private void miSesión(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/InicioSesion.fxml"));
            Parent root = (Parent) loader.load();
            InicioSesionController iniController = ((InicioSesionController) loader.getController());
            iniController.setStage(stage);
            iniController.initStage(root);
        } catch (IOException e) {
            
        }
    }
    


}
