/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.util.List;
import java.util.concurrent.TimeoutException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.AnimalFactoria;
import logic.ZonaFactoria;
import model.Admin;
import model.Animal;
import model.Privilegio;
import model.Usuario;
import model.Zona;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.util.NodeQueryUtils.isVisible;

/**
 *
 * @author Adrian
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AnimalControllerTest extends ApplicationTest {

    public AnimalControllerTest() {
    }

    private Usuario user = new Usuario();
    ;
    
    private Pane paneAnimal;

    private Button btnCrearAimal;

    private Button btnModificarAnimal;

    private Button btnEliminarAnimal;

    private AnchorPane anchorAnimal;

    private TextField txtNombreAnimal;

    private ComboBox txtGenero;

    private TextField txtEdad;

    private TextField txtPeso;

    private TextField txtAltura;

    private ComboBox comboSalud;

    private ComboBox comboAlimentacion;

    private TextField txtEspecie;

    private ComboBox comboZona;

    private TableView<Animal> tableAnimal;

    private TableColumn tbcNombre;

    private TableColumn tbcEspecie;

    private TableColumn tbcGenero;

    private TableColumn tbcEdad;

    private TableColumn tbcPeso;

    private TableColumn tbcAltura;

    private TableColumn tbcAlimentacion;

    private TableColumn tbcSalud;

    private TableColumn<Zona, ?> tbcZona;

    private ComboBox comboFiltrar;

    private Button btnBuscar;

    private Button btnInforme;

    private ComboBox comboFiltrarAlimentacion;

    private ComboBox comboFiltrarEspecie;

    private MenuItem mItemBorrar;

    private TextField textEmail;

    private Button btnInicioSesion;

    private PasswordField pswContraseña;

    /**
     *
     * @param primaryStage
     * @throws Exception
     */
    public void start(Stage primaryStage) throws Exception {
        textEmail = lookup("#textEmail").query();
        btnInicioSesion = lookup("#btnInicioSesion").query();
        pswContraseña = lookup("#pswContraseña").query();

        btnCrearAimal = lookup("#btnCrearAimal").query();
        btnModificarAnimal = lookup("#btnModificarAnimal").query();
        btnEliminarAnimal = lookup("#btnEliminarAnimal").query();
        anchorAnimal = lookup("#anchorAnimal").query();

        txtNombreAnimal = lookup("#txtNombreAnimal").query();
        txtGenero = lookup("#txtGenero").query();
        txtPeso = lookup("#txtPeso").query();
        txtAltura = lookup("#txtAltura").query();
        txtEdad = lookup("#txtEdad").query();
        txtEspecie = lookup("#txtEspecie").query();
        comboAlimentacion = lookup("#comboAlimentacion").query();
        comboSalud = lookup("#comboSalud").query();
        comboZona = lookup("#comboZona").query();

        comboFiltrar = lookup("#comboFiltrar").query();
        comboFiltrarAlimentacion = lookup("#comboFiltrarAlimentacion").query();
        comboFiltrarEspecie = lookup("#comboFiltrarEspecie").query();
        btnBuscar = lookup("#btnBuscar").query();
        btnInforme = lookup("#btnInforme").query();

        tableAnimal = lookup("#tableAnimal").query();
    }

    /**
     * Set up Java FX fixture for tests. This is a general approach for using a
     * unique instance of the application in the test.
     *
     * @throws java.util.concurrent.TimeoutException
     */
    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(aplication.Aplicacion.class);
    }

    /**
     * Método de prueba para comprobar si la ventana de inicio está abierta.
     *
     * @author Adrian
     */
    @Test
    public void TestA_comprobar_ventana_abierta() {
        verifyThat("#ventanaInicio", isVisible());
    }

    /**
     * Método de prueba para verificar si el botón de inicio está habilitado
     * cuando se ingresan credenciales válidas.
     *
     * @author Adrian
     */
    @Test
    public void TestB_comprobar_boton_inicio_habilitado() {
        clickOn(textEmail).write("admin@gmail.com");
        clickOn(pswContraseña).write("Abcd*1234");
        verifyThat(btnInicioSesion, isEnabled());
        clickOn(btnInicioSesion);
    }

    @Test
    public void TestC_comprobar_menubar() {
        clickOn("#menuNavegar");
        clickOn("#mItemAnimales");
    }

    @Test
    public void TestD_HabilitarBotones() {
        if (user.getTipo_usuario() == Privilegio.ADMIN) {
            verifyThat(btnCrearAimal, isEnabled());
            verifyThat(btnModificarAnimal, isVisible());
            verifyThat(btnEliminarAnimal, isVisible());
            verifyThat(txtNombreAnimal, isEnabled());
            verifyThat(txtGenero, isEnabled());
            verifyThat(txtAltura, isEnabled());
            verifyThat(txtPeso, isEnabled());
            verifyThat(txtEdad, isEnabled());
            verifyThat(txtEspecie, isEnabled());
            verifyThat(comboAlimentacion, isEnabled());
            verifyThat(comboSalud, isEnabled());
            verifyThat(comboZona, isEnabled());
        }
        verifyThat(comboFiltrar, isEnabled());
        verifyThat(txtNombreAnimal, isVisible());
        verifyThat(txtGenero, isVisible());
        verifyThat(txtAltura, isVisible());
        verifyThat(txtPeso, isVisible());
        verifyThat(txtEdad, isVisible());
        verifyThat(txtEspecie, isVisible());
        verifyThat(comboAlimentacion, isVisible());
        verifyThat(comboSalud, isVisible());
        verifyThat(comboZona, isVisible());
        // Verificar que el botón de informe está deshabilitado
        verifyThat(btnInforme, isEnabled());

    }

    /**
     * Test para crear un animal.
     */
    @Test
    public void TestE_createAnimal() {
        int initialRowCount = tableAnimal.getItems().size();

        // Simular el llenado de los campos de creación del animal
        clickOn(txtNombreAnimal).write("Paco");
        String nombre = txtNombreAnimal.getText();
        clickOn(txtGenero);
        press(KeyCode.DOWN);
        press(KeyCode.ENTER);
        clickOn(txtEspecie).write("Tigre");
        clickOn(comboSalud);
        press(KeyCode.DOWN);
        press(KeyCode.ENTER);
        clickOn(txtEdad).write("7");
        clickOn(txtPeso).write("60");
        clickOn(txtAltura).write("1.1");
        clickOn(comboAlimentacion);
        press(KeyCode.DOWN);
        press(KeyCode.DOWN);
        press(KeyCode.ENTER);
        clickOn(comboZona);
        press(KeyCode.DOWN);
        press(KeyCode.ENTER);
        clickOn(btnCrearAimal);

        // Verificar si se ha añadido correctamente el animal
        assertEquals("El animal se ha añadido correctamente!", initialRowCount + 1, tableAnimal.getItems().size());

        // Buscar el animal recién creado en el modelo de datos de la tabla
        List<Animal> animales = tableAnimal.getItems();
        long count = animales.stream().filter(a -> a.getNombre().equals(nombre)).count();
        assertEquals("El animal se ha añadido correctamente!", 1, count);
    }
}
