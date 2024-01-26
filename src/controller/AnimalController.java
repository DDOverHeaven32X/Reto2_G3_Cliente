/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exception.CreateException;
import exception.DeleteException;
import exception.IncorrectPatternException;
import exception.UpdateException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.ws.rs.WebApplicationException;
import logic.AnimalFactoria;
import logic.ZonaFactoria;
import model.Admin;
import model.Alimentacion;
import model.Animal;
import model.Privilegio;
import model.Salud;
import model.Usuario;
import model.Zona;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Adrian
 */
public class AnimalController {

    private static final Logger LOGGER = Logger.getLogger("/controller/AnimalController");

    private Usuario user;
    private Admin admin = new Admin();
    private Animal animal = new Animal();
    private final AnimalFactoria fAnimal = new AnimalFactoria();
    private final ZonaFactoria fZona = new ZonaFactoria();
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
    private TableColumn<Zona, ?> tbcZona;
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
    @FXML
    private MenuItem mItemBorrar;

    private Stage stage;

    private Zona zona;

    public void initiStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        //La ventana no es modal
        stage.initModality(Modality.NONE);
        stage.setTitle("Animal");

        //la ventana no se puede redimensionar
        stage.setResizable(false);

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
        btnCrearAimal.setDisable(true);
        btnModificarAnimal.setDisable(true);
        btnEliminarAnimal.setDisable(true);
        comboFiltrarAlimentacion.setDisable(true);
        btnBuscar.setDisable(true);
        comboFiltrarEspecie.setDisable(true);
        //El foco está en el campo del precio
        txtNombreAnimal.requestFocus();

        //Activacion del cambio de texto unicamente si eres admin
        if (user.getTipo_usuario().equals(Privilegio.ADMIN)) {
            txtNombreAnimal.textProperty().addListener((observable, oldValue, newValue) -> cambioTexto(null, null, null));
            txtGenero.valueProperty().addListener((observable, oldValue, newValue) -> cambioTexto(null, null, null));
            txtEspecie.textProperty().addListener((observable, oldValue, newValue) -> cambioTexto(null, null, null));
            comboSalud.valueProperty().addListener((observable, oldValue, newValue) -> cambioTexto(null, null, null));
            txtEdad.textProperty().addListener((observable, oldValue, newValue) -> cambioTexto(null, null, null));
            txtPeso.textProperty().addListener((observable, oldValue, newValue) -> cambioTexto(null, null, null));
            txtAltura.textProperty().addListener((observable, oldValue, newValue) -> cambioTexto(null, null, null));
            comboAlimentacion.valueProperty().addListener((observable, oldValue, newValue) -> cambioTexto(null, null, null));
            comboZona.valueProperty().addListener((observable, oldValue, newValue) -> cambioTexto(null, null, null));
        }
        //Combo con sus datos ya introducidos
        txtGenero.getItems().addAll("Macho", "Hembra");
        comboFiltrar.getItems().addAll("Por alimentación", "Por especie", "");
        comboSalud.getItems().addAll("SANO", "ENFERMO");
        comboAlimentacion.getItems().addAll("HERBIVORO", "CARNIVORO", "OMNIVORO");
        cargarComboZona();
        cargarFiltroComboEspecie();
        comboFiltrarAlimentacion.getItems().addAll("HERBIVORO", "CARNIVORO", "OMNIVORO");

        //Los datos de la fila selecionada se añadirán a los campos con esto
        tableAnimal.getSelectionModel().selectedItemProperty().addListener(this::handleUsersTableSelectionChanged);

        //Asignacion de botones
        btnCrearAimal.setOnAction(this::handleCreateButtonAction);
        btnModificarAnimal.setOnAction(this::handleModifyButtonAction);
        btnEliminarAnimal.setOnAction(this::handleDeleteButtonAction);
        btnBuscar.setOnAction(this::handleSearchButton);
        btnInforme.setOnAction(this::handleImprimirAction);

        //Menu de contexto unicamente si eres admin
        if (user.getTipo_usuario().equals(Privilegio.ADMIN)) {
            mItemBorrar.setOnAction(this::handleDeleteButtonAction);
        } else {
            mItemBorrar.setVisible(false);
            mItemBorrar.setDisable(true);
        }

        //Si el usuario es cliente se deshabilitan los campos donde se ve la informacion y se hacen invisibles los siguientes botones
        if (user.getTipo_usuario().equals(Privilegio.CLIENT)) {
            txtNombreAnimal.setDisable(true);
            txtGenero.setDisable(true);
            txtEdad.setDisable(true);
            txtPeso.setDisable(true);
            txtAltura.setDisable(true);
            comboSalud.setDisable(true);
            comboAlimentacion.setDisable(true);
            txtEspecie.setDisable(true);
            comboZona.setDisable(true);

            btnCrearAimal.setDisable(true);
            btnModificarAnimal.setDisable(true);
            btnEliminarAnimal.setDisable(true);
            btnCrearAimal.setVisible(false);
            btnModificarAnimal.setVisible(false);
            btnEliminarAnimal.setVisible(false);
        }

        //Dependiendo que tipo de filtro se escoja, ciertos elementos de la ventana se alteran
        comboFiltrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                switch (comboFiltrar.getValue().toString()) {
                    case "Por alimentación":
                        comboFiltrarAlimentacion.setDisable(false);
                        comboFiltrarAlimentacion.setVisible(true);
                        comboFiltrarEspecie.setDisable(true);
                        comboFiltrarEspecie.setVisible(false);
                        btnBuscar.setVisible(true);
                        btnBuscar.setDisable(false);
                        break;
                    case "Por especie":
                        comboFiltrarEspecie.setDisable(false);
                        comboFiltrarEspecie.setVisible(true);
                        comboFiltrarAlimentacion.setDisable(true);
                        comboFiltrarAlimentacion.setVisible(false);
                        btnBuscar.setVisible(true);
                        btnBuscar.setDisable(false);
                        break;
                    case "":
                        comboFiltrarAlimentacion.setDisable(true);
                        comboFiltrarAlimentacion.setVisible(false);
                        comboFiltrarEspecie.setDisable(true);
                        comboFiltrarEspecie.setVisible(false);
                        btnBuscar.setVisible(false);
                        btnBuscar.setDisable(true);
                        comboFiltrar.setValue("Filtrar");
                        refrescarTabla();
                        break;
                    default:
                        comboFiltrarAlimentacion.setDisable(true);
                        comboFiltrarAlimentacion.setVisible(false);
                        comboFiltrarEspecie.setDisable(true);
                        comboFiltrarEspecie.setVisible(false);
                        btnBuscar.setVisible(false);
                        btnBuscar.setDisable(true);
                        cargarTodo();
                        break;
                }

            }

            private void refrescarTabla() {
                animalData = FXCollections.observableArrayList(cargarTodo());
                tableAnimal.setItems(animalData);
                tableAnimal.refresh();
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
        } catch (WebApplicationException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

        //Mediante este evento llamamos al metodo handeCloseRequest cuando hacemos click sobre el boton X (Boton de cerrar la ventana).
        stage.setOnCloseRequest(this::handleCloseRequest);
        stage.show();
    }

    //Método para relalizar el CRUD de POST en la tabla
    @FXML
    private void handleCreateButtonAction(ActionEvent event) {
        try {
            if (validarCamposAnimal()) {
                //Conversiones necesarias para hacer la inserción
                String edad = txtEdad.getText();
                int edadI = Integer.parseInt(edad);
                String altura = txtAltura.getText();
                Float alturaF = Float.parseFloat(altura);
                String peso = txtPeso.getText();
                Float pesoF = Float.parseFloat(peso);

                animal.setNombre(txtNombreAnimal.getText());
                animal.setGenero(txtGenero.getValue().toString());
                animal.setEspecie(txtEspecie.getText());
                if (comboSalud.getValue().toString().equals("SANO")) {
                    animal.setSalud(Salud.SANO);
                } else if (comboSalud.getValue().toString().equals("ENFERMO")) {
                    animal.setSalud(Salud.ENFERMO);
                }
                animal.setEdad(edadI);
                animal.setPeso(pesoF);
                animal.setAltura(alturaF);
                switch (comboAlimentacion.getValue().toString()) {
                    case "HERBIVORO":
                        animal.setAlimentacion(Alimentacion.HERBIVORO);
                        break;
                    case "CARNIVORO":
                        animal.setAlimentacion(Alimentacion.CARNIVORO);
                        break;
                    case "OMNIVORO":
                        animal.setAlimentacion(Alimentacion.OMNIVORO);
                        break;
                    default:
                        break;
                }
                animal.setZona((Zona) comboZona.getValue());
                admin.setId_user(user.getId_user());
                animal.setAdmin(admin);

                // Comprobar si el animal ya existe
                if (animalExiste()) {
                    Alert ventanita = new Alert(Alert.AlertType.ERROR);
                    ventanita.setHeaderText(null);
                    ventanita.setTitle("Error");
                    ventanita.setContentText("El animal que intentas crear ya existe");
                    Optional<ButtonType> action = ventanita.showAndWait();
                    if (action.get() == ButtonType.OK) {
                        txtNombreAnimal.setText("");
                        txtGenero.setValue(null);
                        txtEspecie.setText("");
                        comboSalud.setValue(null);
                        txtEdad.setText("");
                        txtPeso.setText("");
                        txtAltura.setText("");
                        comboAlimentacion.setValue(null);
                        comboZona.setValue(null);
                        ventanita.close();
                    }
                    return;
                }
                if (fAnimal != null) {
                    fAnimal.getFactory().create_XML(animal);
                } else {
                    throw new CreateException();

                }
                //Cargamos la tabla con el animal nuevo
                animalData = FXCollections.observableArrayList(cargarTodo());

            } else {
                throw new IncorrectPatternException("Error de patron en algun campo.");
            }

        } catch (WebApplicationException e) {
            try {
                LOGGER.log(Level.SEVERE, null, e);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Error al crear el animal.");
                throw new CreateException();
            } catch (CreateException ex) {
                Logger.getLogger(AnimalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IncorrectPatternException ex) {
            Logger.getLogger(AnimalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CreateException ex) {
            Logger.getLogger(AnimalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Método para relalizar el CRUD de PUT en la tabla
    @FXML
    private void handleModifyButtonAction(ActionEvent event) {
        try {

            if (validarCamposAnimal()) {
                //Conversiones necesarias para hacer la inserción
                String edad = txtEdad.getText();
                int edadI = Integer.parseInt(edad);
                String altura = txtAltura.getText();
                Float alturaF = Float.parseFloat(altura);
                String peso = txtPeso.getText();
                Float pesoF = Float.parseFloat(peso);
                //Escogemos el Id para indicar al programa que animal debe modificar
                animal.setId_animal(tableAnimal.getSelectionModel().getSelectedItem().getId_animal());

                animal.setNombre(txtNombreAnimal.getText());
                animal.setGenero(txtGenero.getValue().toString());
                animal.setEspecie(txtEspecie.getText());
                if (comboSalud.getValue().toString().equals("SANO")) {
                    animal.setSalud(Salud.SANO);
                } else if (comboSalud.getValue().toString().equals("ENFERMO")) {
                    animal.setSalud(Salud.ENFERMO);
                }
                animal.setEdad(edadI);
                animal.setPeso(pesoF);
                animal.setAltura(alturaF);
                switch (comboAlimentacion.getValue().toString()) {
                    case "HERBIVORO":
                        animal.setAlimentacion(Alimentacion.HERBIVORO);
                        break;
                    case "CARNIVORO":
                        animal.setAlimentacion(Alimentacion.CARNIVORO);
                        break;
                    case "OMNIVORO":
                        animal.setAlimentacion(Alimentacion.OMNIVORO);
                        break;
                    default:
                        break;
                }
                animal.setZona((Zona) comboZona.getValue());
                if (fAnimal != null) {
                    fAnimal.getFactory().edit_XML(animal);
                } else {
                    throw new UpdateException();
                }

                //Cargamos la tabla con el dato nuevo
                animalData = FXCollections.observableArrayList(cargarTodo());
            } else {
                throw new IncorrectPatternException("Error en el patron de algun campo.");
            }

        } catch (WebApplicationException e) {
            try {
                LOGGER.log(Level.SEVERE, null, e);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Error al modificar el animal.");
                throw new UpdateException();
            } catch (UpdateException ex) {
                Logger.getLogger(AnimalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (UpdateException ex) {
            Logger.getLogger(AnimalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IncorrectPatternException ex) {
            Logger.getLogger(AnimalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Método para relalizar el CRUD de DELETE en la tabla
    @FXML
    private void handleDeleteButtonAction(ActionEvent event) {
        try {
            //Creamos un nuevo objeto Alerta
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Borrar Animal");
            //Mostramos una alerta de confirmacion.
            alert.setContentText("¿Estas seguro que deseas eliminar el animal?");

            Optional<ButtonType> answer = alert.showAndWait();
            if (answer.get() == ButtonType.OK) {
                //Para realizar el borrado lo hacemos mediante el id de la Entrada
                Animal animalElegido = tableAnimal.getSelectionModel().getSelectedItem();
                if (fAnimal != null) {
                    fAnimal.getFactory().remove(animalElegido.getId_animal().toString());
                } else {
                    throw new DeleteException();
                }
                //Cargamos la tabla con el dato nuevo
                animalData = FXCollections.observableArrayList(cargarTodo());
            } else {
                event.consume();
            }

        } catch (Exception e) {
            try {
                LOGGER.log(Level.SEVERE, null, e);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Error al eliminar el animal.");
                throw new DeleteException();
            } catch (DeleteException ex) {
                Logger.getLogger(AnimalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void handleImprimirAction(ActionEvent event) {
        try {
            JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/report/AnimalReport.jrxml"));
            JRBeanCollectionDataSource dataItems = new JRBeanCollectionDataSource((Collection<Animal>) this.tableAnimal.getItems());
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

    //Método de busqueda del botón, sirve para realizar los filtros
    @FXML
    private void handleSearchButton(ActionEvent actionEvent) {
        switch (comboFiltrar.getValue().toString()) {
            case ("Por alimentación"):
                cargarFiltroAlimentacion();
                break;
            case ("Por especie"):
                cargarFiltroEspecie();
                break;
        }
    }

    /**
     * Método que frente a cualquier cambio de la tabla la pone en estado
     * neutral, vacia los campos y muestra los datos de la tabla.
     */
    @FXML
    private ObservableList<Animal> cargarTodo() {
        try {
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
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Error al cargar la tabla.");
        }
        return null;
    }

    //Método que vacia los campos si hay algúna alteracion en la ventana
    @FXML
    private void cambioTexto(ObservableValue observable, Object oldValue, Object newValue) {
        try {
            if (txtNombreAnimal.getText().trim().isEmpty() || txtGenero.getValue() == null || txtEspecie.getText().trim().isEmpty() || comboSalud.getValue() == null || txtEdad.getText().trim().isEmpty() || txtPeso.getText().trim().isEmpty() || txtAltura.getText().trim().isEmpty() || comboAlimentacion.getValue() == null || comboZona.getValue() == null) {
                btnCrearAimal.setDisable(true);
                btnModificarAnimal.setDisable(true);
                btnEliminarAnimal.setDisable(true);
            } else {
                btnCrearAimal.setDisable(false);
                btnModificarAnimal.setDisable(false);
                btnEliminarAnimal.setDisable(false);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    //Método que introduce los datos de la tabla aal panel
    @FXML
    private void handleUsersTableSelectionChanged(ObservableValue observable, Object oldValue, Object newValue) {
        try {
            if (newValue != null) {

                Animal a = (Animal) newValue;

                txtNombreAnimal.setText(a.getNombre());
                txtGenero.setValue(a.getGenero());
                txtEspecie.setText(a.getEspecie());
                comboSalud.setValue(a.getSalud());
                txtEdad.setText(a.getEdad().toString());
                txtPeso.setText(a.getPeso().toString());
                txtAltura.setText(a.getAltura().toString());
                comboAlimentacion.setValue(a.getAlimentacion());
                comboZona.setValue(a.getZona());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

    }

    @FXML
    private void cargarFiltroComboEspecie() {
        try {
            List<Animal> animales = fAnimal.getFactory().findAll_XML(Animal.class);
            Set<String> especiesUnicas = new HashSet<>();

            for (Animal a : animales) {
                especiesUnicas.add(a.getEspecie());
            }

            comboFiltrarEspecie.getItems().addAll(especiesUnicas);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Error al cargar el filtro de especies.");
        }
    }

    @FXML
    private void cargarComboZona() {
        try {
            ObservableList<Zona> todaszonas = FXCollections.observableArrayList(fZona.getFactory().findAll_XML(Zona.class));
            List<Zona> zonas = FXCollections.observableArrayList(todaszonas);
            comboZona.getItems().addAll(zonas);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Error al cargar el filtro de zonas.");
        }

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private ObservableList<Animal> cargarFiltroAlimentacion() {
        ObservableList<Animal> listaAnimales;
        List<Animal> FiltradoParam;
        FiltradoParam = FXCollections.observableArrayList(fAnimal.getFactory().findAnimalsByFeeding_XML(Animal.class, comboFiltrarAlimentacion.getValue().toString()));

        listaAnimales = FXCollections.observableArrayList(FiltradoParam);
        tableAnimal.setItems(listaAnimales);
        tableAnimal.refresh();
        return listaAnimales;
    }

    @FXML
    private ObservableList<Animal> cargarFiltroEspecie() {
        ObservableList<Animal> listaAnimales;
        List<Animal> FiltradoParam;
        FiltradoParam = FXCollections.observableArrayList(fAnimal.getFactory().findAnimalsBySpecies_XML(Animal.class, comboFiltrarEspecie.getValue().toString()));

        listaAnimales = FXCollections.observableArrayList(FiltradoParam);
        tableAnimal.setItems(listaAnimales);
        tableAnimal.refresh();
        return listaAnimales;
    }

    public ObservableList<Animal> cargarFiltroAnimales() {
        ObservableList<Animal> listaAnimales;
        List<Animal> filtradoParam;
        filtradoParam = FXCollections.observableArrayList(fAnimal.getFactory().findAnimalsInAnArea_XML(Animal.class, zona.getId_zona().toString()));
        listaAnimales = FXCollections.observableArrayList(filtradoParam);
        tableAnimal.setItems(listaAnimales);
        tableAnimal.refresh();
        if (tableAnimal.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("TABLA VACIA");
            alert.setHeaderText(null);
            alert.setContentText("No hay ningun animal en esa zona.");
            alert.showAndWait();
        }
        return listaAnimales;
    }

    private boolean validarCamposAnimal() {
        String nombre = txtNombreAnimal.getText();
        String genero = txtGenero.getValue().toString();
        String especie = txtEspecie.getText();
        String salud = comboSalud.getValue().toString();
        String edad = txtEdad.getText();
        String peso = txtPeso.getText();
        String altura = txtAltura.getText();
        String alimentacion = comboAlimentacion.getValue().toString();
        String zona = comboZona.getValue().toString();

        if (nombre.length() > 20 || !nombre.matches("^[^0-9]+$")) {
            mostrarAlerta("Error de validación en el nombre", "El nombre puede tener hasta 20 caracteres y no puede contener números.");
            return false;
        }

        if (!genero.equals("Macho") && !genero.equals("Hembra")) {
            mostrarAlerta("Error de validación en el genero", "El genero solo puede ser macho o hembra.");
            return false;
        }

        if (especie.length() > 20 || !especie.matches("^[^0-9]+$")) {
            mostrarAlerta("Error de validación en la especie", "La especie puede tener hasta 20 caracteres y no puede contener números.");
            return false;
        }

        if (!salud.equals("SANO") && !salud.equals("ENFERMO")) {
            mostrarAlerta("Error de validación en salud", "La salud solo puede ser sano o enfermo.");
            return false;
        }

        try {
            int edadN = Integer.parseInt(edad);
            if (edadN < 0 || edadN > 500) {
                mostrarAlerta("Error de validación en la edad", "La edad no puede ser superior a 500 ni menor a 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, null, e);
            mostrarAlerta("Error de validación en la edad", "Solo puedes introcucir numeros enteros.");
            return false;
        }

        try {
            Float pesoN = Float.parseFloat(peso);
            if (pesoN < 0 || pesoN > 180000) {
                mostrarAlerta("Error de validación en el peso", "El peso no puede ser superior a 180000kg ni menor a 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, null, e);
            mostrarAlerta("Error de validación en el peso", "Solo puedes introcucir numeros.");
            return false;
        }

        try {
            Float alturaN = Float.parseFloat(altura);
            if (alturaN < 0 || alturaN > 5) {
                mostrarAlerta("Error de validación en la altura", "La altura no puede ser superior a 5m ni menor a 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, null, e);
            mostrarAlerta("Error de validación en la altura", "Solo puedes introcucir numeros.");
            return false;
        }

        if (!alimentacion.equals("HERBIVORO") && !alimentacion.equals("CARNIVORO") && !alimentacion.equals("OMNIVORO")) {
            mostrarAlerta("Error de validación en la alimentacion", "La alimentacion solo puede ser herbivoro, carnivoro o omnivoro.");
            return false;
        }

        if (zona == null) {
            mostrarAlerta("Error de validación en la zona", "La zona no puede ser nula.");
            return false;
        }

        return true;
    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private boolean animalExiste() {
        // Buscar animal que sea igual a los ya introducidos
        List<Animal> listaAnimal = fAnimal.getFactory().findAll_XML(Animal.class);
        Animal animalACorregir = new Animal();
        animalACorregir.setNombre(txtNombreAnimal.getText());
        animalACorregir.setGenero(txtGenero.getValue().toString());
        animalACorregir.setEspecie(txtEspecie.getText());
        if (comboSalud.getValue().toString().equals("SANO")) {
            animalACorregir.setSalud(Salud.SANO);
        } else if (comboSalud.getValue().toString().equals("ENFERMO")) {
            animalACorregir.setSalud(Salud.ENFERMO);
        }
        animalACorregir.setEdad(Integer.parseInt(txtEdad.getText()));
        animalACorregir.setPeso(Float.parseFloat(txtPeso.getText()));
        animalACorregir.setAltura(Float.parseFloat(txtAltura.getText()));
        switch (comboAlimentacion.getValue().toString()) {
            case "HERBIVORO":
                animalACorregir.setAlimentacion(Alimentacion.HERBIVORO);
                break;
            case "CARNIVORO":
                animalACorregir.setAlimentacion(Alimentacion.CARNIVORO);
                break;
            case "OMNIVORO":
                animalACorregir.setAlimentacion(Alimentacion.OMNIVORO);
                break;
            default:
                break;
        }

        for (Animal a : listaAnimal) {
            if (a.getNombre().equals(animalACorregir.getNombre()) && a.getGenero().equals(animalACorregir.getGenero()) && a.getEspecie().equals(animalACorregir.getEspecie()) && a.getSalud().equals(animalACorregir.getSalud()) && a.getEdad().equals(animalACorregir.getEdad()) && a.getPeso().equals(animalACorregir.getPeso()) && a.getAltura().equals(animalACorregir.getAltura()) && a.getAlimentacion().equals(animalACorregir.getAlimentacion())) {
                return true;
            }
        }
        return false;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    /**
     * Este metodo es una verificacion cuando el usuario le da al boton de
     * salir.
     *
     * @author Adrian
     * @param event
     */
    private void handleCloseRequest(WindowEvent event) {
        //Creamos un nuevo objeto Alerta
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("EXIT");
        //Mostramos una alerta de confirmacion.
        alert.setContentText("¿Estas seguro que deseas salir de la aplicacion?");

        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            Platform.exit();
        } else {
            event.consume();
        }

    }

    private void showErrorAlert(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setUsuario(Usuario user) {
        this.user = user;
    }
}
