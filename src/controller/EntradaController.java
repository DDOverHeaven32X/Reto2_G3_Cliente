/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import logic.EntradaFactoria;
import model.Admin;
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

    private Admin admin = new Admin();

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
        dtpFiltradoFecha.setVisible(false);
        //Los siguientes campos están deshabilitados
        btnCrear.setDisable(false);
        btnModificar.setDisable(false);
        btnEliminar.setDisable(false);
        btnComprar.setDisable(true);
        txtFiltrar.setDisable(true);
        btnBuscar.setDisable(true);
        dtpFiltradoFecha.setDisable(true);
        //El foco está en el campo del precio
        txtPrecioEntrada.requestFocus();
        //Activacion del cambio de texto
        txtPrecioEntrada.textProperty().addListener(this::cambioTexto);
        //Los datos de la fila selecionada se añadirán a los campos con esto
        tblEntrada.getSelectionModel().selectedItemProperty().addListener(this::handleUsersTableSelectionChanged);
        //Combo con sus datos ya introducidos
        comboEntrada.getItems().addAll("Infantil(0-12)", "Adulto", "Senior(+65)", "Minúsvalido");
        cbcFiltro.getItems().addAll("Filtrar por dinero", "Filtrar por fecha");
        //Asignacion de botones
        btnCrear.setOnAction(this::handleCreateButtonAction);
        btnModificar.setOnAction(this::handleModifyButtonAction);
        btnEliminar.setOnAction(this::handleDeleteButtonAction);
        btnBuscar.setOnAction(this::handleSearchButton);
        //Dependiendo que tipo de filtro se escoja, ciertos elementos de la ventana se alteran
        cbcFiltro.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (null == cbcFiltro.getValue().toString()) {
                    txtFiltrar.setDisable(true);
                    dtpFiltradoFecha.setDisable(true);
                    btnBuscar.setVisible(false);
                } else {
                    switch (cbcFiltro.getValue().toString()) {
                        case "Filtrar por dinero":
                            txtFiltrar.setDisable(false);
                            txtFiltrar.setVisible(true);
                            dtpFiltradoFecha.setDisable(true);
                            dtpFiltradoFecha.setVisible(false);
                            btnBuscar.setVisible(true);
                            btnBuscar.setDisable(false);
                            break;
                        case "Filtrar por fecha":
                            dtpFiltradoFecha.setDisable(false);
                            dtpFiltradoFecha.setVisible(true);
                            txtFiltrar.setDisable(true);
                            txtFiltrar.setVisible(false);
                            btnBuscar.setVisible(true);
                            btnBuscar.setDisable(false);
                            break;
                        default:
                            txtFiltrar.setDisable(true);
                            txtFiltrar.setVisible(false);
                            dtpFiltradoFecha.setDisable(true);
                            dtpFiltradoFecha.setVisible(false);
                            btnBuscar.setVisible(true);
                            btnBuscar.setDisable(false);
                            cargarTodo();
                            break;
                    }
                }
            }

        });
        //Establecemos las factorias para los valores de celda
        //Formateo de la fecha para formáto estándar
        try {
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

    /**
     * Método que frente a cualquier cambio de la tabla la pone en estado
     * neutral, vacia los campos y muestra los datos de la tabla haciendo un
     * select simple
     */
    @FXML
    private ObservableList<Entrada> cargarTodo() {
        ObservableList<Entrada> listEntrada;
        List<Entrada> todosEntrada;
        todosEntrada = FXCollections.observableArrayList(factoryEnt.getFactory().findAll_XML(Entrada.class));

        listEntrada = FXCollections.observableArrayList(todosEntrada);
        //Vacia los datos cuando carga datos
        txtPrecioEntrada.setText("");
        comboEntrada.setValue("");
        dtpFecha.setValue(null);
        tblEntrada.setItems(listEntrada);
        tblEntrada.refresh();
        return listEntrada;
    }

    //Método que vacia los campos si hay algúna alteracion en la ventana
    @FXML
    private void cambioTexto(ObservableValue observable, Object oldValue, Object newValue) {
        if (txtPrecioEntrada.getText().trim().isEmpty() || comboEntrada.getValue().toString().trim().isEmpty() || tbcFecha.getText().trim().isEmpty()) {
            btnCrear.setDisable(true);
            btnEliminar.setDisable(true);
            btnModificar.setDisable(true);
        } else {
            btnCrear.setDisable(false);
            btnEliminar.setDisable(false);
            btnModificar.setDisable(false);
        }
    }

    //Método para relalizar el CRUD de POST en la tabla
    @FXML
    private void handleCreateButtonAction(ActionEvent event) {
        //Conversiones necesarias para hacer la inserción
        String precio;
        precio = txtPrecioEntrada.getText();
        float precioReal = Float.parseFloat(precio);

        LocalDate fecha = dtpFecha.getValue();
        Date fechaBuena = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());

        entrada.setPrecio(precioReal);
        entrada.setTipo_entrada(comboEntrada.getValue().toString());
        entrada.setFecha_entrada(fechaBuena);
        //entrada.setAdmin(admin);
        factoryEnt.getFactory().create_XML(entrada);
        //Cargamos la tabla con el dato nuevo
        entradaData = FXCollections.observableArrayList(cargarTodo());
    }

    //Método para relalizar el CRUD de PUT en la tabla
    @FXML
    private void handleModifyButtonAction(ActionEvent event) {
        //Conversiones necesarias para hacer la inserción
        String precio;
        precio = txtPrecioEntrada.getText();
        float precioReal = Float.parseFloat(precio);

        LocalDate fecha = dtpFecha.getValue();
        Date fechaBuena = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
        //Escogemos el Id para indicar al programa cual entrada debe modificar
        entrada.setId_entrada(tblEntrada.getSelectionModel().getSelectedItem().getId_entrada());
        entrada.setPrecio(precioReal);
        entrada.setTipo_entrada(comboEntrada.getValue().toString());
        entrada.setFecha_entrada(fechaBuena);

        factoryEnt.getFactory().edit_XML(entrada);
        //Cargamos la tabla con el dato nuevo
        entradaData = FXCollections.observableArrayList(cargarTodo());
    }

    //Método para relalizar el CRUD de PUT en la tabla
    @FXML
    private void handleDeleteButtonAction(ActionEvent event) {
        //Para realizar el borrado lo hacemos mediante el id de la Entrada
        Entrada selectedEntrada = tblEntrada.getSelectionModel().getSelectedItem();
        factoryEnt.getFactory().remove(selectedEntrada.getId_entrada().toString());

        //Cargamos la tabla con el dato nuevo
        entradaData = FXCollections.observableArrayList(cargarTodo());
    }

    //Método que incrusta los datos de la tabla a los campos de parametrización
    @FXML
    private void handleUsersTableSelectionChanged(ObservableValue observable, Object oldValue, Object newValue) {

        if (newValue != null) {

            Entrada ticket = (Entrada) newValue;
            //Conversion necesaria para hacer el camambio de texto
            Date fecha = new Date();
            fecha = ticket.getFecha_entrada();
            LocalDate fechaNueva = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            txtPrecioEntrada.setText(ticket.getPrecio().toString());
            comboEntrada.setValue(ticket.getTipo_entrada());
            dtpFecha.setValue(fechaNueva);
        }
    }
    //Método de busqueda del botón, sirve para realizar las consultas parametrizadas
    @FXML
    private void handleSearchButton(ActionEvent actionEvent){
        switch(cbcFiltro.getValue().toString()){
            case("Filtrar por dinero"):
                cargarFiltro1();
            break;
            case("Filtrar por fecha"):
                cargarFiltro2();
            break;
        }
    }
    //Método que se encarga de realizar la búsqueda de entradas por un precio fijado
    @FXML
    private ObservableList<Entrada> cargarFiltro1(){
        ObservableList<Entrada> listaEntradas;
        List<Entrada> FiltradoParam;
        FiltradoParam = FXCollections.observableArrayList(factoryEnt.getFactory().filtrarEntradaPorPrecio_XML(Entrada.class, txtFiltrar.getText()));
        
        listaEntradas = FXCollections.observableArrayList(FiltradoParam);
        tblEntrada.setItems(listaEntradas);
        tblEntrada.refresh();
        return listaEntradas;
    }
    //Método que se encarga de realizar la búsqueda de entradas por una fecha fijada
    @FXML
    private ObservableList<Entrada> cargarFiltro2(){
        ObservableList<Entrada> listaEntradas;
        List<Entrada> FiltradoParam;
        FiltradoParam = FXCollections.observableArrayList(factoryEnt.getFactory().filtrarEntradaPorFecha_XML(Entrada.class, dtpFiltradoFecha.getValue().toString()));
        
        listaEntradas = FXCollections.observableArrayList(FiltradoParam);
        tblEntrada.setItems(listaEntradas);
        tblEntrada.refresh();
        return listaEntradas;
    }
    

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
