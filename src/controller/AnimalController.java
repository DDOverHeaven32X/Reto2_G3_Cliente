/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.AnimalFactoria;
import model.Admin;
import model.Animal;
import model.Entrada;
import model.Usuario;
import model.Zona;

/**
 * FXML Controller class
 *
 * @author Adrian
 */
public class AnimalController {

    private static final Logger LOGGER = Logger.getLogger("/controller/AnimalController");

    private Admin admin = new Admin();
    private Usuario user = new Usuario();
    private final AnimalFactoria fAnimal = new AnimalFactoria();
    private ObservableList<Animal> animalData;

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
    private ComboBox txtGenero;
    @FXML
    private TextField txtEdad;
    @FXML
    private TextField txtPeso;
    @FXML
    private TextField txtAltura;
    @FXML
    private ComboBox comboSalud;
    @FXML
    private ComboBox comboAlimentacion;
    @FXML
    private TextField txtEspecie;
    @FXML
    private ComboBox comboZona;
    @FXML
    private TableView<Animal> tableAnimal;
    @FXML
    private TableColumn tbcNombre;
    @FXML
    private TableColumn tbcEspecie;
    @FXML
    private TableColumn tbcGenero;
    @FXML
    private TableColumn tbcEdad;
    @FXML
    private TableColumn tbcPeso;
    @FXML
    private TableColumn tbcAltura;
    @FXML
    private TableColumn tbcAlimentacion;
    @FXML
    private TableColumn tbcSalud;
    @FXML
    private TableColumn <Zona, ?> tbcZona;
    @FXML
    private ComboBox comboFiltrar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnInforme;
    @FXML
    private ComboBox comboFiltrarAlimentacion;
    @FXML
    private ComboBox comboFiltrarEspecie;

    private Stage stage;

    public void initiStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        //La ventana no es modal
        stage.initModality(Modality.NONE);
        stage.setTitle("Animal");
        //Campos visibles
        txtNombreAnimal.setVisible(true);
        comboFiltrar.setVisible(true);
        txtGenero.setVisible(true);
        txtEdad.setVisible(true);
        txtPeso.setVisible(true);
        txtAltura.setVisible(true);
        comboSalud.setVisible(true);
        comboAlimentacion.setVisible(true);
        txtEspecie.setVisible(true);
        comboZona.setVisible(true);
        //Campos invisibles
        comboFiltrarAlimentacion.setVisible(false);
        btnBuscar.setVisible(false);
        comboFiltrarEspecie.setVisible(false);
        //Campos deshabilitados
        btnCrearAimal.setDisable(false);
        btnModificarAnimal.setDisable(false);
        btnEliminarAnimal.setDisable(false);
        comboFiltrarAlimentacion.setDisable(true);
        btnBuscar.setDisable(true);
        comboFiltrarEspecie.setDisable(true);
        //El foco está en el campo del precio
        txtNombreAnimal.requestFocus();
        //Activacion del cambio de texto
        txtNombreAnimal.textProperty().addListener(this::cambioTexto);
        //Combo con sus datos ya introducidos
        txtGenero.getItems().addAll("Macho", "Hembra");
        comboFiltrar.getItems().addAll("Filtrar por alimentación", "Filtrar por especie");
        comboSalud.getItems().addAll("SANO", "ENFERMO");
        comboZona.getItems().addAll(fAnimal.getFactory());
        cargarFiltroComboEspecie(comboFiltrarEspecie);
        comboFiltrarAlimentacion.getItems().addAll("HERBIVORO", "CARNIVORO", "OMNIVORO");
        //Los datos de la fila selecionada se añadirán a los campos con esto
        tableAnimal.getSelectionModel().selectedItemProperty().addListener(this::handleUsersTableSelectionChanged);
        //Asignacion de botones
        /*btnCrearAimal.setOnAction(this::handleCreateButtonAction);
        btnModificarAnimal.setOnAction(this::handleModifyButtonAction);
        btnEliminarAnimal.setOnAction(this::handleDeleteButtonAction);*/
        //Dependiendo que tipo de filtro se escoja, ciertos elementos de la ventana se alteran
        comboFiltrar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (null == comboFiltrar.getValue().toString()) {
                    comboFiltrarAlimentacion.setDisable(true);
                    comboFiltrarEspecie.setDisable(true);
                    btnBuscar.setVisible(false);
                } else {
                    switch (comboFiltrar.getValue().toString()) {
                        case "Filtrar por alimentación":
                            comboFiltrarAlimentacion.setDisable(false);
                            comboFiltrarAlimentacion.setVisible(true);
                            comboFiltrarEspecie.setDisable(true);
                            comboFiltrarEspecie.setVisible(false);
                            btnBuscar.setVisible(true);
                            btnBuscar.setDisable(false);
                            break;
                        case "Filtrar por especie":
                            comboFiltrarEspecie.setDisable(false);
                            comboFiltrarEspecie.setVisible(true);
                            comboFiltrarAlimentacion.setDisable(true);
                            comboFiltrarAlimentacion.setVisible(false);
                            btnBuscar.setVisible(true);
                            btnBuscar.setDisable(false);
                            break;
                        default:
                            comboFiltrarAlimentacion.setDisable(true);
                            comboFiltrarAlimentacion.setVisible(false);
                            comboFiltrarEspecie.setDisable(true);
                            comboFiltrarEspecie.setVisible(false);
                            btnBuscar.setVisible(true);
                            btnBuscar.setDisable(false);
                            cargarTodo();
                            break;
                    }
                }
            }
        });

        //Establecemos las factorias para los valores de celda
        try {
            tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            tbcEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
            tbcGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
            tbcEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
            tbcPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
            tbcAltura.setCellValueFactory(new PropertyValueFactory<>("altura"));
            tbcAlimentacion.setCellValueFactory(new PropertyValueFactory<>("alimentacion"));
            tbcSalud.setCellValueFactory(new PropertyValueFactory<>("salud"));
            tbcZona.setCellValueFactory(new PropertyValueFactory<>("zona"));

            //cargamos los datos en la tabla
            animalData = FXCollections.observableArrayList(fAnimal.getFactory().findAll_XML(Animal.class));
            tableAnimal.setItems(animalData);
        } catch (Exception e) {
            LOGGER.severe("Error a la hora de cargar los datos");
        }

        stage.show();
    }

    /**
     * Método que frente a cualquier cambio de la tabla la pone en estado
     * neutral, vacia los campos y muestra los datos de la tabla.
     */
    @FXML

    private ObservableList<Animal> cargarTodo() {
        ObservableList<Animal> listAnimales;
        List<Animal> todosAnimales;
        todosAnimales = FXCollections.observableArrayList(fAnimal.getFactory().findAll_XML(Animal.class));
        listAnimales = FXCollections.observableArrayList(todosAnimales);
        txtNombreAnimal.setText("");
        txtGenero.setValue(null);
        txtEspecie.setText("");
        comboSalud.setValue(null);
        txtEdad.setText("");
        txtPeso.setText("");
        txtAltura.setText("");
        comboAlimentacion.setValue(null);
        comboZona.setValue(null);
        tableAnimal.setItems(listAnimales);
        tableAnimal.refresh();
        return listAnimales;
    }

    //Método que vacia los campos si hay algúna alteracion en la ventana
    @FXML
    private void cambioTexto(ObservableValue observable, Object oldValue, Object newValue) {
        if (txtNombreAnimal.getText().trim().isEmpty() || txtGenero.getValue().toString().trim().isEmpty() || txtEspecie.getText().trim().isEmpty() || comboSalud.getValue().toString().trim().isEmpty() || txtEdad.getText().trim().isEmpty() || txtPeso.getText().trim().isEmpty() || txtAltura.getText().trim().isEmpty() || comboAlimentacion.getValue().toString().trim().isEmpty() || comboZona.getValue().toString().trim().isEmpty()) {
            btnCrearAimal.setDisable(true);
            btnModificarAnimal.setDisable(true);
            btnEliminarAnimal.setDisable(true);
        } else {
            btnCrearAimal.setDisable(false);
            btnModificarAnimal.setDisable(false);
            btnEliminarAnimal.setDisable(false);
        }
    }

    //Método que introduce los datos de la tabla aal panel
    @FXML
    private void handleUsersTableSelectionChanged(ObservableValue observable, Object oldValue, Object newValue) {

        if (newValue != null) {

            Animal animal = (Animal) newValue;

            txtNombreAnimal.setText(animal.getNombre());
            txtGenero.setValue(animal.getGenero());
            txtEspecie.setText(animal.getEspecie());
            comboSalud.setValue(animal.getSalud());
            txtEdad.setText(animal.getEdad().toString());
            txtPeso.setText(animal.getPeso().toString());
            txtAltura.setText(animal.getAltura().toString());
            comboAlimentacion.setValue(animal.getAlimentacion());
            comboZona.setValue(animal.getZona().getNombre());
        }
    }

    @FXML
    private void cargarFiltroComboEspecie(ComboBox comboFiltrarEspecie1) {
        ObservableList<String> especies = FXCollections.observableArrayList(fAnimal.getFactory().findSpecies_XML(String.class));
        List<String> todasLasEspecies = FXCollections.observableArrayList(especies);
        comboFiltrarEspecie1.getItems().addAll(todasLasEspecies);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
