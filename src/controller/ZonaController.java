/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.RegistroController.LOGGER;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.AnimalFactoria;
import logic.ZonaFactoria;
import model.Animal;
import model.Entrada;
import model.Zona;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Clase de controlador FXML para gestionar entidades Zona. Este controlador
 * maneja las interacciones de la interfaz de usuario para crear, modificar y
 * eliminar zonas. También proporciona funcionalidad para filtrar y generar
 * informes.
 *
 * @author 2dam
 */
public class ZonaController {

    private final ZonaFactoria zonaFact = new ZonaFactoria();
    private ObservableList<Zona> zonaData;
    private Zona zona = new Zona();

    private List<Zona> listaZonas;

    @FXML
    private Label lbl_nombreZona;
    @FXML
    private Label lbl_descripcionZona;
    @FXML
    private Label lbl_tipoAnimal;
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
    private ComboBox txtTipoAnimalZona;
    @FXML
    private TextArea txtDescripcionZona;
    @FXML
    private TableView<Zona> tableZona;
    @FXML
    private TableColumn tbcNombre;
    @FXML
    private TableColumn tbcTipoAnimal;
    @FXML
    private TableColumn tbcDescripion;
    @FXML
    private ComboBox comboFiltrar;
    @FXML
    private TextField txtFiltrar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnInforme;
    @FXML
    private MenuItem menuItemBorrar;
    @FXML
    private MenuItem menuItemVisualizarAnimales;

    private AnimalFactoria fAnimal;

    private Stage stage;

    /**
     * Inicializa el escenario y configura el estado inicial de los componentes
     * de la interfaz de usuario.
     *
     * @param root El nodo raíz Parent de la escena.
     */
    public void initiStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Zona");

        //Los siguientes campos son visibles
        txtNombreZona.setVisible(true);
        txtDescripcionZona.setVisible(true);
        txtTipoAnimalZona.setVisible(true);
        txtFiltrar.setVisible(true);
        btnBuscar.setVisible(true);
        lbl_descripcionZona.setVisible(true);
        lbl_nombreZona.setVisible(true);
        lbl_tipoAnimal.setVisible(true);
        comboFiltrar.setVisible(true);
        txtFiltrar.setVisible(false);
        btnBuscar.setVisible(false);

        //Los siguientes campos están deshabilitados
        btnCrearZona.setDisable(false);
        btnModificarZona.setDisable(true);
        btnEliminarZona.setDisable(true);
        txtFiltrar.setDisable(true);
        btnBuscar.setDisable(true);

        //Ponemos el foco al inicio de la ventana
        txtNombreZona.requestFocus();

        //Metodo para el cambio de texto
        txtNombreZona.textProperty().addListener(this::cambioTexto);
        //Asignacion de botones
        btnCrearZona.setOnAction(this::handleCreateButtonAction);

        //Cargamos la combo con los valores posibles 
        txtTipoAnimalZona.getItems().addAll("Peces", "Anfibios", "Arácnidos", "Artrópodos", "Mamíferos", "Aves", "Reptiles", "Insectos");
        //Metodo para modificar la zona
        btnModificarZona.setOnAction(this::handleModifyButtonAction);
        //Metodo para eliminar la zona
        btnEliminarZona.setOnAction(this::handleDeleteButtonAction);

        //Metodo para filtrar por nombre o tipo animal.
        btnBuscar.setOnAction(this::handleSearchButton);
        //Metodo para imprimir informes
        btnInforme.setOnAction(this::handleImprimirAction);
        // Agregar un listener al cambio de texto en txtFiltrar
        txtFiltrar.textProperty().addListener((observable, oldValue, newValue) -> {
            // Llamar al método refreshTableIfFilterEmpty cuando el texto cambie
            refreshTableIfFilterEmpty();
        });

        menuItemBorrar.setOnAction(this::handleDeleteButtonAction);

        menuItemVisualizarAnimales.setOnAction(this::handleVisualizarAnimalesButtonAction);
        //Metodo para cargar los datos de la zona seleccionada
        tableZona.getSelectionModel().selectedItemProperty().addListener(this::handleUsersTableSelectionChanged);

        comboFiltrar.getItems().addAll("Filtrar por nombre", "Filtrar por tipo animal");

        comboFiltrar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (comboFiltrar.getValue().toString() != null) {
                    txtFiltrar.setDisable(false);
                    txtFiltrar.setVisible(true);
                    btnBuscar.setDisable(false);
                    btnBuscar.setVisible(true);
                }
            }

        });
        //Establecemos las factorias para los valores de celda
        try {

            tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            tbcDescripion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            tbcTipoAnimal.setCellValueFactory(new PropertyValueFactory<>("tipo_animal"));

            //cargamos los datos en la tabla
            zonaData = FXCollections.observableArrayList(zonaFact.getFactory().findAll_XML(Zona.class));
            tableZona.setItems(zonaData);
        } catch (Exception e) {
            LOGGER.severe("Error a la hora de cargar los datos");
        }

        cargarTodo();
        stage.show();
    }

    /**
     * Método que frente a cualquier cambio de la tabla la pone en estado
     * neutral, vacia los campos y muestra los datos de la tabla haciendo un
     * select simple
     */
    @FXML
    private ObservableList<Zona> cargarTodo() {
        ObservableList<Zona> listZonas;
        List<Zona> todasZonas;
        todasZonas = FXCollections.observableArrayList(zonaFact.getFactory().findAll_XML(Zona.class));

        listZonas = FXCollections.observableArrayList(todasZonas);
        //Vacia los datos cuando carga datos
        txtNombreZona.setText("");
        txtDescripcionZona.setText("");
        txtTipoAnimalZona.setValue(null);
        tableZona.setItems(listZonas);
        tableZona.refresh();
        return listZonas;
    }

    /**
     * Maneja la acción de crear una nueva Zona.
     *
     * @param event El ActionEvent desencadenado por el clic en el botón.
     */
    @FXML
    private void handleCreateButtonAction(ActionEvent event) {
        //Conversiones necesarias para hacer la inserción
        if (!camposZonaInformados()) {

        } else {
            // Utiliza el método validarCamposZona para verificar restricciones
            String nombre = txtNombreZona.getText();
            String descripcion = txtDescripcionZona.getText();
            String tipoAnimal = txtTipoAnimalZona.getSelectionModel().getSelectedItem().toString();
            if (validarCamposZona(nombre, descripcion)) {
                if (zonaExiste(nombre, descripcion, tipoAnimal)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Datos repetidos");
                    alert.setHeaderText(null);
                    alert.setContentText("Esa Zona ya existe. Modifica algun campo.");
                    alert.showAndWait();
                } else {
                    zona.setNombre(nombre);
                    zona.setDescripcion(descripcion);
                    zona.setTipo_animal(tipoAnimal);

                    //entrada.setAdmin(admin);
                    zonaFact.getFactory().create_XML(zona);
                    //Cargamos la tabla con el dato nuevo
                    zonaData = FXCollections.observableArrayList(cargarTodo());
                }
            }
        }

    }

    /**
     * Maneja la acción de modificar una Zona existente.
     *
     * @param event El ActionEvent desencadenado por el clic en el botón.
     */
    @FXML
    private void handleModifyButtonAction(ActionEvent event) {

        if (!camposZonaInformados()) {

        } else {

            //Conversiones necesarias para hacer la inserción
            String nombre = txtNombreZona.getText();
            String descripcion = txtDescripcionZona.getText();
            String tipoAnimal = txtTipoAnimalZona.getSelectionModel().getSelectedItem().toString();
            if (zonaExiste(nombre, descripcion, tipoAnimal)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Datos repetidos");
                alert.setHeaderText(null);
                alert.setContentText("Esa Zona ya existe. Modifica algun campo.");
                alert.showAndWait();
            } else {
                //Escogemos el Id para indicar al programa cual entrada debe modificar
                zona.setId_zona(tableZona.getSelectionModel().getSelectedItem().getId_zona());
                zona.setNombre(nombre);
                zona.setDescripcion(descripcion);
                zona.setTipo_animal(tipoAnimal);

                zonaFact.getFactory().edit_XML(zona);
                //Cargamos la tabla con el dato nuevo
                zonaData = FXCollections.observableArrayList(cargarTodo());
            }
        }
    }

    /**
     * Maneja la acción de eliminar una Zona existente.
     *
     * @param event El ActionEvent desencadenado por el clic en el botón.
     */
    @FXML
    private void handleDeleteButtonAction(ActionEvent event) {
        // Obtener la Zona seleccionada
        Zona selectedZona = tableZona.getSelectionModel().getSelectedItem();

        // Confirmar la eliminación
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar Eliminación");
        confirmacion.setHeaderText("¿Estás seguro de que deseas eliminar la Zona?");
        confirmacion.setContentText("Esta acción no se puede deshacer.");

        Optional<ButtonType> resultado = confirmacion.showAndWait();

        if (resultado.get() == ButtonType.OK) {
            // Eliminar la Zona si el usuario confirma
            zonaFact.getFactory().remove(selectedZona.getId_zona().toString());

            // Recargar la tabla con los datos actualizados
            zonaData = FXCollections.observableArrayList(cargarTodo());
        }
    }

    /**
     * Maneja la acción de visualizar animales para la Zona seleccionada. Cierra
     * la ventana actual y abre una nueva ventana para visualizar los animales
     * de la Zona.
     *
     * @param event El ActionEvent desencadenado por el clic en el botón.
     */
    @FXML
    public void handleVisualizarAnimalesButtonAction(ActionEvent event) {
        Zona selectedZona = tableZona.getSelectionModel().getSelectedItem();

        try {
            if (selectedZona == null) {
                // Mostrar un mensaje al usuario indicando que debe seleccionar una zona.
                mostrarAlerta("Error de selección", "Por favor, seleccione una zona para visualizar los animales.");
                return;
            }
            // Verificar si hay animales en la zona

            // Cerrar la ventana actual
            Stage ventanaActual = (Stage) tableZona.getScene().getWindow();
            ventanaActual.close();

            // Abrir la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Animal.fxml"));
            Parent root = loader.load();

            AnimalController animalController = ((AnimalController) loader.getController());
            animalController.setZona(selectedZona);
            animalController.setStage(stage);
            animalController.initiStage(root);
            animalController.cargarFiltroAnimales();

        } catch (IOException ex) {
            // Manejo de excepciones de E/S
            mostrarAlerta("Error de E/S", "Error al cargar la vista de animales.");
            Logger.getLogger(ZonaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            // Manejo de excepciones generales
            mostrarAlerta("Error", "Ocurrió un error inesperado.");
            Logger.getLogger(ZonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Maneja el cambio de selección en la tabla de Zonas. Introduce los datos
     * de la Zona seleccionada en los campos correspondientes.
     *
     * @param observable La propiedad observable.
     * @param oldValue El valor antiguo.
     * @param newValue El nuevo valor.
     */
    @FXML
    private void handleUsersTableSelectionChanged(ObservableValue observable, Object oldValue, Object newValue) {
        if (newValue != null) {
            Zona zona = (Zona) newValue;
            //Conversion necesaria para hacer el camambio de texto
            txtNombreZona.setText(zona.getNombre());
            txtDescripcionZona.setText(zona.getDescripcion());
            txtTipoAnimalZona.setValue(zona.getTipo_animal());
        } else {
            // Si no hay ninguna zona seleccionada, deshabilitar los botones
            btnModificarZona.setDisable(true);
            btnEliminarZona.setDisable(true);
        }
    }

    /**
     * Maneja la acción del botón de búsqueda, realizando consultas
     * parametrizadas en la tabla de Zonas.
     *
     * @param actionEvent El ActionEvent desencadenado por el clic en el botón
     * de búsqueda.
     */
    //Método de busqueda del botón, sirve para realizar las consultas parametrizadas
    @FXML
    private void handleSearchButton(ActionEvent actionEvent) {
        if (!txtFiltrar.getText().trim().isEmpty()) {
            switch (comboFiltrar.getValue().toString()) {
                case ("Filtrar por nombre"):
                    cargarFiltroNombre();
                    break;
                case ("Filtrar por tipo animal"):
                    cargarFiltroTipoAnimal();
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

    /**
     * Realiza un filtro en la tabla de Zonas por el nombre especificado.
     *
     * @return La lista observable de Zonas después de aplicar el filtro por
     * nombre.
     */
    //Tipo de filtro que carga la tabla por el nombre de la zona
    @FXML
    private ObservableList<Zona> cargarFiltroNombre() {
        ObservableList<Zona> listaZonas;
        List<Zona> FiltradoParam;
        FiltradoParam = FXCollections.observableArrayList(zonaFact.getFactory().filtrarZonaPorNombre_XML(Zona.class, txtFiltrar.getText()));

        listaZonas = FXCollections.observableArrayList(FiltradoParam);
        tableZona.setItems(listaZonas);
        tableZona.refresh();
        return listaZonas;
    }

    /**
     * Realiza un filtro en la tabla de Zonas por el tipo de animal
     * especificado.
     *
     * @return La lista observable de Zonas después de aplicar el filtro por
     * tipo de animal.
     */
    // //Tipo de filtro que carga la tabla por el tipo de animal de la zona
    @FXML
    private ObservableList<Zona> cargarFiltroTipoAnimal() {
        ObservableList<Zona> listaZonas;
        List<Zona> FiltradoParam;
        FiltradoParam = FXCollections.observableArrayList(zonaFact.getFactory().filtrarZonaPorTipoAnimal_XML(Zona.class, txtFiltrar.getText()));

        listaZonas = FXCollections.observableArrayList(FiltradoParam);
        tableZona.setItems(listaZonas);
        tableZona.refresh();
        return listaZonas;
    }

    /**
     * Maneja el cambio en el texto de los campos de filtro. Actualiza la tabla
     * si el campo de filtro está vacío.
     *
     * @param observable La propiedad observable.
     * @param oldValue El valor antiguo.
     * @param newValue El nuevo valor.
     */
    // Método que vacía los campos si hay alguna alteración en la ventana
    @FXML
    private void cambioTexto(ObservableValue observable, Object oldValue, Object newValue) {
        if (tableZona.getSelectionModel().getSelectedItem() == null) {
            btnEliminarZona.setDisable(true);
            btnModificarZona.setDisable(true);
        } else {
            btnEliminarZona.setDisable(false);
            btnModificarZona.setDisable(false);
        }
    }

    /**
     * Método que verifica si todos los campos necesarios para la
     * creación/modificación de una Zona están informados.
     *
     * @return true si todos los campos están informados, false si algún campo
     * está vacío.
     */
    private boolean camposZonaInformados() {
        if (txtNombreZona.getText().trim().isEmpty() || txtDescripcionZona.getText().trim().isEmpty() || txtTipoAnimalZona.getValue() == null) {
            // Si alguno de los campos está vacío, muestra un mensaje de alerta
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Vacíos");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, complete todos los campos.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private boolean validarCamposZona(String nombre, String descripcion) {
        nombre = txtNombreZona.getText().trim();
        descripcion = txtDescripcionZona.getText().trim();

        if (nombre.length() > 20 || !nombre.matches("^[^0-9]+$")) {
            mostrarAlerta("Error de validación", "El nombre debe tener hasta 20 caracteres y no puede contener números.");

            return false;
        }

        if (descripcion.length() > 200) {
            mostrarAlerta("Error de validación", "La descripción debe tener hasta 200 caracteres.");
            return false;
        }

        return true;
    }

    /**
     * Método que refresca la tabla si el campo de filtro está vacío.
     */
    //Metodo que verifica que no hay nada en el textfield filtrar
    @FXML
    private void refreshTableIfFilterEmpty() {
        if (txtFiltrar.getText().trim().isEmpty()) {
            // If txtFiltrar is empty, refresh the table with all data
            zonaData = FXCollections.observableArrayList(cargarTodo());
            tableZona.setItems(zonaData);
            tableZona.refresh();
        }
    }

    /**
     * Método que muestra una alerta con un título y contenido específicos.
     *
     * @param titulo El título de la alerta.
     * @param contenido El contenido de la alerta.
     */
    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    /**
     * Método que comprueba si una zona con el mismo nombre, descripción y tipo
     * de animal ya existe.
     *
     * @param nombre El nombre de la Zona.
     * @param descripcion La descripción de la Zona.
     * @param tipoAnimal El tipo de animal de la Zona.
     * @return true si la Zona ya existe, false si no.
     */
    private boolean zonaExiste(String nombre, String descripcion, String tipoAnimal) {
        // Buscar entradas con la misma fecha y tipo de entrada
        listaZonas = zonaFact.getFactory().findAll_XML(Zona.class);
        for (Zona z : listaZonas) {
            if (z.getNombre().equals(nombre) && z.getDescripcion().equals(descripcion) && z.getTipo_animal().equals(tipoAnimal)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método que establece el escenario (Stage) para este controlador.
     *
     * @param stage El Stage para este controlador.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Maneja la acción de generar y mostrar un informe para las Zonas.
     *
     * @param event El ActionEvent desencadenado por el clic en el botón.
     */
    @FXML
    private void handleImprimirAction(ActionEvent event) {
        try {
            LOGGER.info("Comenzando la impresion...");
            JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/report/ZonaReport.jrxml"));
            //Data for the report: a collection of UserBean passed as a JRDataSource 
            //implementation 
            JRBeanCollectionDataSource dataItems = new JRBeanCollectionDataSource((Collection<Zona>) this.tableZona.getItems());
            //Map of parameter to be passed to the report
            Map<String, Object> parameters = new HashMap<>();
            //Fill report with data
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataItems);
            //Create and show the report window. The second parameter false value makes 
            //report window not to close app.
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
            // jasperViewer.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        } catch (JRException ex) {

        }
    }
}
