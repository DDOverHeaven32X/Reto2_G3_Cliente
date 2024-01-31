/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exception.CreateException;
import exception.DeleteException;
import exception.IncorrectCredentialException;
import exception.IncorrectPatternException;
import exception.ReadException;
import exception.UpdateException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.stage.WindowEvent;
import logic.EntradaFactoria;
import model.Admin;
import model.Cliente;
import model.Entrada;
import model.Privilegio;
import model.Usuario;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Controlador de la ventana de Entrada y sus distintas acciones
 *
 * @author Diego
 */
public class EntradaController {

    private static final Logger LOGGER = Logger.getLogger("/controller/EntradaController");

    private final EntradaFactoria factoryEnt = new EntradaFactoria();

    private Entrada entrada = new Entrada();

    private Usuario user;

    private Cliente clien;

    private Admin admin = new Admin();

    private ObservableList<Entrada> entradaData;

    private List<Entrada> listaEntrada;

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
    @FXML
    private MenuItem mnItemBorrar;
    @FXML
    private MenuItem mnItemVerEntradas;

    private Stage stage;

    public void initiStage(Parent root, Usuario user, Cliente cliente) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);

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
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnComprar.setDisable(false);
        txtFiltrar.setDisable(true);
        btnBuscar.setDisable(true);
        dtpFiltradoFecha.setDisable(true);
        //Los clientes no podrán usar ni los campos ni los botones relacionados con el CRUD
        if (user.getTipo_usuario().equals(Privilegio.CLIENT)) {
            txtPrecioEntrada.setVisible(true);
            txtPrecioEntrada.setDisable(true);
            cbcFiltro.setVisible(true);
            comboEntrada.setVisible(true);
            comboEntrada.setDisable(true);
            dtpFecha.setDisable(true);
            dtpFecha.setVisible(true);
            lbl_precio.setVisible(true);
            lbl_precio1.setVisible(true);
            lbl_tipo.setVisible(true);
            lbl_fecha.setVisible(true);
            txtFiltrar.setVisible(true);
            btnBuscar.setVisible(true);
            dtpFiltradoFecha.setVisible(true);
            btnCrear.setDisable(true);
            btnCrear.setVisible(false);
            btnModificar.setDisable(true);
            btnModificar.setVisible(false);
            btnEliminar.setDisable(true);
            btnEliminar.setVisible(false);
        } else {
            btnComprar.setVisible(false);
            btnComprar.setDisable(true);
            btnTusEntradas.setVisible(false);
            btnTusEntradas.setDisable(true);

        }
        //El foco está en el campo del precio
        txtPrecioEntrada.requestFocus();
        //Activacion del cambio de texto
        txtPrecioEntrada.textProperty().addListener(this::cambioTexto);
        txtFiltrar.textProperty().addListener((observable, oldValue, newValue) -> {
            // Llamar al método refreshTableIfFilterEmpty cuando el texto cambie
            refreshTableIfFilterEmpty();
        });
        dtpFiltradoFecha.valueProperty().addListener((observable, oldValue, newValue) -> {

            refreshTableIfFilterEmpty();
        });

        //Los datos de la fila selecionada se añadirán a los campos con esto
        tblEntrada.getSelectionModel().selectedItemProperty().addListener(this::handleUsersTableSelectionChanged);
        //Combo con sus datos ya introducidos
        comboEntrada.getItems().addAll(null, "Infantil(0-12)", "Adulto", "Senior(+65)", "Minúsvalido");
        cbcFiltro.getItems().addAll("Filtrar por dinero", "Filtrar por fecha");
        //Asignacion de botones
        //System.out.println(cliente.toString());
        btnCrear.setOnAction(this::handleCreateButtonAction);
        btnModificar.setOnAction(this::handleModifyButtonAction);
        btnEliminar.setOnAction(this::handleDeleteButtonAction);
        btnBuscar.setOnAction(this::handleSearchButton);
        btnTusEntradas.setOnAction(this::handlerEntradasClient);
        btnComprar.setOnAction(this::handlerCompraEntrada);
        btnInforme.setOnAction(this::handleImprimirAction);

        //Menu de contexto unicamente si eres admin
        if (user.getTipo_usuario().equals(Privilegio.ADMIN)) {
            mnItemBorrar.setOnAction(this::handleDeleteButtonAction);
            mnItemVerEntradas.setVisible(false);
            mnItemVerEntradas.setDisable(true);
        } else {
            mnItemBorrar.setVisible(false);
            mnItemBorrar.setDisable(true);
            mnItemVerEntradas.setOnAction(this::handlerEntradasClient);
        }
        //Codigo que guarda el valor selecionado
        //Dependiendo que tipo de filtro se escoja, ciertos elementos de la ventana se alteran
        cbcFiltro.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (!txtFiltrar.getText().trim().isEmpty() || dtpFiltradoFecha.getValue() == null) {
                    String filtro = cbcFiltro.getValue().toString();
                    switch (filtro) {
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
                    }
                } else {
                    refreshTableIfFilterEmpty();
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Campos Vacíos");
                    alert.setHeaderText(null);
                    alert.setContentText("Por favor introduce algun texto para filtrar.");
                    alert.showAndWait();
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
        stage.setOnCloseRequest(this::handleCloseRequest);
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
        if (tblEntrada.getSelectionModel().getSelectedItem() == null) {
            btnEliminar.setDisable(true);
            btnModificar.setDisable(true);
        } else {
            btnEliminar.setDisable(false);
            btnModificar.setDisable(false);
        }
    }

    //Método que resetea
    @FXML
    private void refreshTableIfFilterEmpty() {
        if (txtFiltrar.getText().trim().isEmpty() || (dtpFiltradoFecha.getValue() == null) || dtpFiltradoFecha.getValue().toString().trim().isEmpty()) {
            entradaData = FXCollections.observableArrayList(cargarTodo());
            tblEntrada.setItems(entradaData);
            tblEntrada.refresh();
        }
    }

    //Método para relalizar el CRUD de POST en la tabla
    @FXML
    private void handleCreateButtonAction(ActionEvent event) {
        try {
            // Conversiones necesarias para hacer la inserción
            if (!validacionesCampos()) {
                throw new IncorrectPatternException("Error en el patron de algun campo!");
            } else {

                String precio = txtPrecioEntrada.getText();
                float precioReal = Float.parseFloat(precio);

                LocalDate fecha = dtpFecha.getValue();
                Date fechaBuena = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());

                // Comprobar si la entrada ya existe
                if (entradaExiste(fechaBuena, comboEntrada.getValue().toString())) {
                    Alert ventanita = new Alert(Alert.AlertType.ERROR);
                    ventanita.setHeaderText(null);
                    ventanita.setTitle("Error");
                    ventanita.setContentText("La entrada que intentas crear ya existe");
                    ventanita.showAndWait();
                } else {
                    // Si no existe, proceder con la inserción

                    Admin admin = new Admin();
                    admin.setId_user(user.getId_user());
                    entrada.setPrecio(precioReal);
                    entrada.setTipo_entrada(comboEntrada.getValue().toString());
                    entrada.setFecha_entrada(fechaBuena);
                    entrada.setAdmin(admin);
                    if (factoryEnt != null) {
                        factoryEnt.getFactory().create_XML(entrada);
                    } else {
                        throw new CreateException();
                    }

                    // Cargamos la tabla con el dato nuevo
                    entradaData = FXCollections.observableArrayList(cargarTodo());

                }
            }
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
        } catch (IncorrectPatternException ex) {
            Logger.getLogger(EntradaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que comprueba si una entrada existe antes de crearla
     *
     * @param fecha
     * @param tipoEntrada
     * @return true si la entrada ya existe, false si no existe
     */
    private boolean entradaExiste(Date fecha, String tipoEntrada) {
        // Buscar entradas con la misma fecha y tipo de entrada
        listaEntrada = factoryEnt.getFactory().findAll_XML(Entrada.class);
        for (Entrada e : listaEntrada) {
            if (e.getFecha_entrada().equals(fecha) && e.getTipo_entrada().equals(tipoEntrada)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método que valida si los campos no están vacios o contienen datos erronos
     */
    public boolean validacionesCampos() {
        boolean validacionesExitosas = true;

        // Verificar campos vacíos
        if (txtPrecioEntrada.getText().isEmpty() || comboEntrada.getValue() == null || dtpFecha.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle(null);

            alert.setContentText("Antes de realizar una acción, asegúrate de no dejar ningún campo vacío.");

            Optional<ButtonType> answer = alert.showAndWait();

        } else {
            // Si no hay campos vacíos, validar patrones
            try {
                Double precio = Double.parseDouble(txtPrecioEntrada.getText());
                if (precio <= 0 || precio >= 100) {
                    mostrarAlerta("Error de Validación", "El precio de entrada debe estar entre 0 y 100, ambos no icluidos.");
                    txtPrecioEntrada.setText("");
                    validacionesExitosas = false;
                }
            } catch (NumberFormatException e) {
                mostrarAlerta("Error de Validación", "Has introducido caracteres no válidos en el campo de Precio de Entrada.");
                txtPrecioEntrada.setText("");
                validacionesExitosas = false;
            }

            if (dtpFecha.getValue() != null && dtpFecha.getValue().isBefore(java.time.LocalDate.now())) {
                mostrarAlerta("Error de Validación", "No puedes ingresar fechas anteriores a la actual.");
                dtpFecha.setValue(null);
                validacionesExitosas = false;
            }
        }

        return validacionesExitosas;
    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);

        alert.setContentText(contenido);

        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.isPresent() && answer.get() == ButtonType.OK) {
            alert.close();
        }
    }

    //Método para relalizar el CRUD de PUT en la tabla
    @FXML
    private void handleModifyButtonAction(ActionEvent event) {

        try {
            if (!validacionesCampos()) {
                throw new IncorrectPatternException("Error en el patron de algun campo.");
            } else {
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
                if (factoryEnt != null) {
                    factoryEnt.getFactory().edit_XML(entrada);
                } else {
                    throw new UpdateException();
                }
                //Cargamos la tabla con el dato nuevo
                entradaData = FXCollections.observableArrayList(cargarTodo());
            }
        } catch (IncorrectPatternException e) {
            LOGGER.severe(e.getMessage());
        } catch (UpdateException ex) {
            Logger.getLogger(EntradaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Método para relalizar el CRUD de DELETE en la tabla
    @FXML
    private void handleDeleteButtonAction(ActionEvent event) {
        try {            
            Entrada selectedEntrada = tblEntrada.getSelectionModel().getSelectedItem();     
            
            if (selectedEntrada == null) {
                
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText(null);
                errorAlert.setTitle("Error");
                errorAlert.setContentText("Por favor, selecciona una entrada para borrar.");
                errorAlert.showAndWait();
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Confirmación");
            alert.setContentText("¿Deseas borrar esta entrada?");            
            Optional<ButtonType> answer = alert.showAndWait();            
            if (answer.get() == ButtonType.OK) {
                if (factoryEnt != null) {                    
                    factoryEnt.getFactory().remove(selectedEntrada.getId_entrada().toString());       
                } else {    
                    throw new DeleteException();
                }
                //Recargamos la tabla
                entradaData = FXCollections.observableArrayList(cargarTodo());
            }
        } catch (DeleteException ex) {
            Logger.getLogger(EntradaController.class.getName()).log(Level.SEVERE, "Error al intentar borrar la entrada", ex);
        }
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
        } else {
            btnEliminar.setDisable(true);
            btnModificar.setDisable(true);
        }
    }

    //Método de busqueda del botón, sirve para realizar las consultas parametrizadas
    @FXML
    private void handleSearchButton(ActionEvent actionEvent) {
        switch (cbcFiltro.getValue().toString()) {
            case ("Filtrar por dinero"):
                cargarFiltro1();
                break;
            case ("Filtrar por fecha"):
                cargarFiltro2();
                break;
        }
    }

    //Método que se encarga de realizar la búsqueda de entradas por un precio fijado
    @FXML
    private ObservableList<Entrada> cargarFiltro1() {
        ObservableList<Entrada> listaEntradas;
        List<Entrada> FiltradoParam;
        if (!txtFiltrar.getText().equals("")) {
            FiltradoParam = FXCollections.observableArrayList(factoryEnt.getFactory().filtrarEntradaPorPrecio_XML(Entrada.class, txtFiltrar.getText()));
            listaEntradas = FXCollections.observableArrayList(FiltradoParam);
            tblEntrada.setItems(listaEntradas);
            tblEntrada.refresh();
            return listaEntradas;
        } else {
            listaEntradas = cargarTodo();
            return listaEntradas;
        }
    }

    //Método que se encarga de realizar la búsqueda de entradas por una fecha fijada
    @FXML
    private ObservableList<Entrada> cargarFiltro2() {
        ObservableList<Entrada> listaEntradas;
        List<Entrada> FiltradoParam;
        if (dtpFiltradoFecha.getValue() != null) {

            FiltradoParam = FXCollections.observableArrayList(factoryEnt.getFactory().filtrarEntradaPorFecha_XML(Entrada.class, dtpFiltradoFecha.getValue().toString()));
            listaEntradas = FXCollections.observableArrayList(FiltradoParam);
            tblEntrada.setItems(listaEntradas);
            tblEntrada.refresh();
            return listaEntradas;
        } else {
            listaEntradas = cargarTodo();
            return listaEntradas;
        }

    }

    //Método que muestra las entradas compradas de un cliente
    @FXML
    private ObservableList<Entrada> entradasClient() {
        ObservableList<Entrada> listaEntradas;
        List<Entrada> EntradasFiltro;

        String loginPrueba = user.getLogin();
        EntradasFiltro = FXCollections.observableArrayList(factoryEnt.getFactory().filtrarEntradaDeUsuario_XML(Entrada.class, loginPrueba));
        listaEntradas = FXCollections.observableArrayList(EntradasFiltro);
        try {

            if (EntradasFiltro.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                //Mostramos una alerta de error.
                alert.setContentText("No tienes entradas compradas");

                Optional<ButtonType> answer = alert.showAndWait();
                if (answer.get() == ButtonType.OK) {
                    alert.close();
                    try {
                        throw new Exception("Entradas vacias");
                    } catch (Exception ex) {
                        LOGGER.severe("Entradas vacias");
                    }
                }
            }

            tblEntrada.setItems(listaEntradas);
            tblEntrada.refresh();

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setHeaderText(null);
            alert2.setTitle(null);
            alert2.setX(350);
            alert2.setY(135);
            alert2.setContentText("Mostrando tus entradas compradas, pulsa aceptar para dejar de verlas");

            Optional<ButtonType> answer = alert2.showAndWait();
            if (answer.get() == ButtonType.OK) {
                alert2.close();
                cargarTodo();
            }

        } catch (Exception e) {
            LOGGER.severe("Ha ocurrido un error");
        }
        return listaEntradas;
    }

    @FXML
    public void handlerCompraEntrada(ActionEvent event) {

        try {
            Entrada data = tblEntrada.getSelectionModel().getSelectedItem();
            if (data != null) {
                entrada = new Entrada();
                entrada.setId_entrada(data.getId_entrada());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ConfirmarCompra.fxml"));
                Parent root = loader.load();
                ConfirmarCompraController confiController = ((ConfirmarCompraController) loader.getController());
                confiController.setStage(stage);
                confiController.setUser(user);

                confiController.setEntr(entrada);
                confiController.initiStage(root, user);

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle(null);

                alert.setContentText("No has seleccionado ninguna entrada para comprar");

                Optional<ButtonType> answer = alert.showAndWait();
                if (answer.get() == ButtonType.OK) {
                    alert.close();

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(EntradaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Método que imprime los datos de la tabla a un informe JasperReport
    @FXML
    private void handleImprimirAction(ActionEvent event) {
        try {
            JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/report/EntradaReport.jrxml"));
            JRBeanCollectionDataSource dataItems = new JRBeanCollectionDataSource((Collection<Entrada>) this.tblEntrada.getItems());
            Map<String, Object> parameters = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataItems);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch (JRException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error al imprimir");
            alert.showAndWait();
        }
    }

    private void handleCloseRequest(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("EXIT");
        alert.setContentText("¿Estas seguro que deseas salir de la aplicacion?");

        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            Platform.exit();
        } else {
            event.consume();
        }

    }

    //Esto a mejorar
    @FXML
    private void handlerEntradasClient(ActionEvent event) {
        entradasClient();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public void setClien(Cliente clien) {
        this.clien = clien;
    }

}
