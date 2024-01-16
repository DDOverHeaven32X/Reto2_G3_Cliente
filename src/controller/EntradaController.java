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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 2dam
 */
public class EntradaController {

    @FXML
    private AnchorPane anchorEntrada;
    @FXML
    private Label lbl_precio;
    @FXML
    private Label lbl_zona;
    @FXML
    private TextField txtPrecioEntrada;
    @FXML
    private Label lbl_fecha;
    @FXML
    private DatePicker dtpFecha;
    @FXML
    private Label lbl_precio1;
    @FXML
    private ComboBox<?> comboEntrada;
    @FXML
    private Button btnCrear;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TableView<?> tblEntrada;
    @FXML
    private TableColumn<?, ?> tbcIdEntrada;
    @FXML
    private TableColumn<?, ?> tbcTipo;
    @FXML
    private TableColumn<?, ?> tbcPrecio;
    @FXML
    private TableColumn<?, ?> tbcFecha;
    @FXML
    private ComboBox<?> cbcFiltro;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnInforme;
    @FXML
    private Button btnComprar;
    @FXML
    private DatePicker dtpFiltradoFecha;
    @FXML
    private TextField txtFiltrar;
    @FXML
    private Button btnTusEntradas;
    

    private Stage stage;

    
    public void initiStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Entrada");
        stage.show();
    }    

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
}
