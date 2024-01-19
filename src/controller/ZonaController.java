/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.RegistroController.LOGGER;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.ZonaFactoria;
import model.Entrada;
import model.Zona;

/**
 * FXML Controller class
 *
 * @author 2dam
 */
public class ZonaController {

    private final ZonaFactoria zonaFact = new ZonaFactoria();
    private ObservableList<Zona> zonaData;
    private Zona zona = new Zona();

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

    private Stage stage;

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

        // Agregar un listener al cambio de texto en txtFiltrar
        txtFiltrar.textProperty().addListener((observable, oldValue, newValue) -> {
            // Llamar al método refreshTableIfFilterEmpty cuando el texto cambie
            refreshTableIfFilterEmpty();
        });
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

    //Método para relalizar el CRUD de POST en la tabla
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

    //Método para relalizar el CRUD de PUT en la tabla
    @FXML
    private void handleModifyButtonAction(ActionEvent event) {
        //Conversiones necesarias para hacer la inserción
        String nombre = txtNombreZona.getText();
        String descricpion = txtDescripcionZona.getText();
        String tipoAnimal = txtTipoAnimalZona.getSelectionModel().getSelectedItem().toString();

        //Escogemos el Id para indicar al programa cual entrada debe modificar
        zona.setId_zona(tableZona.getSelectionModel().getSelectedItem().getId_zona());
        zona.setNombre(nombre);
        zona.setDescripcion(descricpion);
        zona.setTipo_animal(tipoAnimal);

        zonaFact.getFactory().edit_XML(zona);
        //Cargamos la tabla con el dato nuevo
        zonaData = FXCollections.observableArrayList(cargarTodo());
    }

    @FXML
    private void handleDeleteButtonAction(ActionEvent event) {
        //Para realizar el borrado lo hacemos mediante el id de la Entrada
        Zona selectedZona = tableZona.getSelectionModel().getSelectedItem();
        zonaFact.getFactory().remove(selectedZona.getId_zona().toString());

        //Cargamos la tabla con el dato nuevo
        zonaData = FXCollections.observableArrayList(cargarTodo());

    }

    //Metodo que introduce los datos en los fields de la zona seleccionada.
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

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
