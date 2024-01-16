/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.ws.rs.core.GenericType;
import logic.EntradaFactoria;
import model.Entrada;
import model.Usuario;

/**
 * Controlador de la ventana de Entrada y sus distintas acciones
 *
 * @author Diego
 */
public class EntradaController {

    private static final Logger LOGGER = Logger.getLogger("/controller/EntradaController");
     
    private final EntradaFactoria factoryEnt = new EntradaFactoria();
    
    private Entrada entrada = new Entrada();
    
    private Usuario user = new Usuario();
    
    private ObservableList<Entrada> entradaData;
    
     
    @FXML
    private AnchorPane anchorEntrada;
    @FXML
    private Label lbl_precio;
    @FXML
    private Label lbl_tipo;
    @FXML
    private TextField txtPrecioEntrada;
    @FXML
    private Label lbl_fecha;
    @FXML
    private DatePicker dtpFecha;
    @FXML
    private Label lbl_precio1;
    @FXML
    private ComboBox comboEntrada;
    @FXML
    private Button btnCrear;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TableView<Entrada> tblEntrada;
    @FXML
    private TableColumn tbcIdEntrada;
    @FXML
    private TableColumn tbcTipo;
    @FXML
    private TableColumn tbcPrecio;
    @FXML
    private TableColumn tbcFecha;
    @FXML
    private ComboBox cbcFiltro;
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
        //La ventana no es modal
        stage.initModality(Modality.NONE);
        stage.setTitle("Entrada");
        //Los siguientes campos son visibles
        txtPrecioEntrada.setVisible(true);
        cbcFiltro.setVisible(true);
        comboEntrada.setVisible(true);
        dtpFecha.setVisible(true);
        lbl_precio.setVisible(true);
        lbl_precio1.setVisible(true);
        lbl_tipo.setVisible(true);
        lbl_fecha.setVisible(true);
        txtFiltrar.setVisible(false);
        btnBuscar.setVisible(false);
        //Los siguientes campos están deshabilitados
        btnCrear.setDisable(true);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnComprar.setDisable(true);
        txtFiltrar.setDisable(true);
        btnBuscar.setDisable(true);
        //El foco está en el campo del precio
        txtPrecioEntrada.requestFocus();
        //Combo con sus datos ya introducidos
        comboEntrada.getItems().addAll("Infantil(0-12)","Adulto","Senior(+65)","Minúsvalido");
        cbcFiltro.getItems().addAll("Filtrar por dinero", "Filtrar por fecha");
        //Establecemos las factorias para los valores de celda
        //Formateo de la fecha para formáto estándar
        try{
            tbcFecha.setCellFactory(column -> {
                TableCell<Entrada, Date> cell = new TableCell<Entrada, Date>() {
                    private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                    @Override
                    protected void updateItem(Date item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            if (item != null) {
                                setText(format.format(item));
                            }

                        }
                    }
                };
                return cell;
            });
            tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha_entrada"));
            tbcIdEntrada.setCellValueFactory(new PropertyValueFactory<>("id_entrada"));
            tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
            tbcTipo.setCellValueFactory(new PropertyValueFactory<>("tipo_entrada"));

            //cargamos los datos en la tabla
            entradaData = FXCollections.observableArrayList(factoryEnt.getFactory().findAll_XML(Entrada.class));
            tblEntrada.setItems(entradaData);
        } catch (Exception e) {
            LOGGER.severe("Error a la hora de cargar los datos");
        }
        
        stage.show();
    }    

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
}
