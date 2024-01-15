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

/**
 * FXML Controller class
 *
 * @author 2dam
 */
public class AnimalController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
