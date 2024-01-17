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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private ComboBox<?> txtTipoAnimalZona;
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
    private ComboBox<?> comboFiltrar;
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
        btnModificarZona.setDisable(false);
        btnEliminarZona.setDisable(false);
        txtFiltrar.setDisable(true);
        btnBuscar.setDisable(true);

        //Ponemos el foco al inicio de la ventana
        txtNombreZona.requestFocus();

        //Asignacion de botones
        btnCrearZona.setOnAction(this::handleCreateButtonAction);
        /*
        btnModificarZona.setOnAction(this::handleModifyButtonAction);
        btnEliminarZona.setOnAction(this::handleDeleteButtonAction);*/
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
        String nombre = txtNombreZona.getText();
        String descricpion = txtDescripcionZona.getText();
        String tipoAnimal = txtTipoAnimalZona.getSelectionModel().toString();
        zona.setNombre(nombre);
        zona.setDescripcion(descricpion);
        zona.setTipo_animal(tipoAnimal);

        //entrada.setAdmin(admin);
        zonaFact.getFactory().create_XML(zona);
        //Cargamos la tabla con el dato nuevo
        zonaData = FXCollections.observableArrayList(cargarTodo());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
