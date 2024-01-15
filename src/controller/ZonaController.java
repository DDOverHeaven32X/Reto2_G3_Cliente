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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author 2dam
 */
public class ZonaController implements Initializable {

    @FXML
    private Button btnCrearZona;
    @FXML
    private Button btnModificarZona;
    @FXML
    private Button btnEliminarZona;
    @FXML
    private AnchorPane anchorZona;
    @FXML
    private TextField txtNombreZona;
    @FXML
    private ComboBox<?> txtTipoAnimalZona;
    @FXML
    private TextArea txtDescripcionZona;
    @FXML
    private TableView<?> tableZona;
    @FXML
    private TableColumn<?, ?> tbcNombre;
    @FXML
    private TableColumn<?, ?> tbcTipoAnimal;
    @FXML
    private TableColumn<?, ?> tbcDescripion;
    @FXML
    private ComboBox<?> comboFiltrar;
    @FXML
    private TextField txtFiltrar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnInforme;
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
