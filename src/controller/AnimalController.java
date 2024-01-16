/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 2dam
 */
public class AnimalController{
    
    

    @FXML
    private Pane paneAnimal;
    @FXML
    private Button btnCrearAimal;
    @FXML
    private Button btnModificarAnimal;
    @FXML
    private Button btnEliminarAnimal;
    @FXML
    private AnchorPane anchorAnimal;
    @FXML
    private TextField txtNombreAnimal;
    @FXML
    private ComboBox<?> txtGenero;
    @FXML
    private TextField txtEdad;
    @FXML
    private TextField txtPeso;
    @FXML
    private TextField txtAltura;
    @FXML
    private ComboBox<?> comboSalud;
    @FXML
    private ComboBox<?> comboAlimentacion;
    @FXML
    private TextField txtEspecie;
    @FXML
    private ComboBox<?> comboZona;
    @FXML
    private TableView<?> tableAnimal;
    @FXML
    private TableColumn<?, ?> tbcNombre;
    @FXML
    private TableColumn<?, ?> tbcEspecie;
    @FXML
    private TableColumn<?, ?> tbcGenero;
    @FXML
    private TableColumn<?, ?> tbcEdad;
    @FXML
    private TableColumn<?, ?> tbcPeso;
    @FXML
    private TableColumn<?, ?> tbcAltura;
    @FXML
    private TableColumn<?, ?> tbcAlimentacion;
    @FXML
    private TableColumn<?, ?> tbcSalud;
    @FXML
    private TableColumn<?, ?> tbcZona;
    @FXML
    private ComboBox<?> comboFiltrar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnInforme;
    @FXML
    private ComboBox<?> comboFiltrarAlimentacion;
    @FXML
    private ComboBox<?> comboFiltrarEspecie;
    
    
    private Stage stage;

    
    public void initiStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Animal");
        stage.show();
    }    

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    
    
}
