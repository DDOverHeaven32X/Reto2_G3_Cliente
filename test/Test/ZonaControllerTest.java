/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.util.List;
import java.util.concurrent.TimeoutException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.AnimalFactoria;
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
import static sun.security.rsa.RSAUtil.KeyType.lookup;

/**
 *
 * @author 2dam
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ZonaControllerTest extends ApplicationTest {

    public ZonaControllerTest() {
    }

    private Button btnCrearZona;

    private Button btnModificarZona;

    private Button btnEliminarZona;

    private AnchorPane anchorZona;

    private TextField txtNombreZona;

    private ComboBox txtTipoAnimalZona;

    private TextArea txtDescripcionZona;

    private TableView<Zona> tableZona;

    private ComboBox comboFiltrar;

    private TextField txtFiltrar;

    private Button btnBuscar;

    private Button btnInforme;

    private TextField textEmail;

    private Button btnInicioSesion;

    private PasswordField pswContraseña;

    private Stage stage;
    
    private Pane ventanaInicio;
    /**
     *
     * @param primaryStage
     * @throws Exception
     */
    

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Initialize your attributes based on the FXML components
        btnCrearZona = lookup("#btnCrearZona").query();
        btnModificarZona = lookup("#btnModificarZona").query();
        btnEliminarZona = lookup("#btnEliminarZona").query();
        anchorZona = lookup("#anchorZona").query();
        txtNombreZona = lookup("#txtNombreZona").query();
        txtTipoAnimalZona = lookup("#txtTipoAnimalZona").query();
        txtDescripcionZona = lookup("#txtDescripcionZona").query();
        tableZona = lookup("#tableZona").query();

        comboFiltrar = lookup("#comboFiltrar").query();
        txtFiltrar = lookup("#txtFiltrar").query();
        btnBuscar = lookup("#btnBuscar").query();
        btnInforme = lookup("#btnInforme").query();

        textEmail = lookup("#textEmail").query();
        btnInicioSesion = lookup("#btnInicioSesion").query();
        pswContraseña = lookup("#pswContraseña").query();
    }

    /**
     * Starts application to be tested.
     *
     * @param stage Primary Stage object
     * @throws Exception If there is any error
     */
    /*@Override public void start(Stage stage) throws Exception {
       new Application().start(stage);
    }*/
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
     * @author Ander
     */
    @Test
    public void TestA_comprobar_ventana_abierta() {
        verifyThat("#ventanaInicio", isVisible());
    }

    /**
     * Método de prueba para verificar si el botón de inicio está habilitado
     * cuando se ingresan credenciales válidas.
     *
     * @author Ander
     */
    @Test
    public void Test2_comprobar_boton_inicio_habilitado() {
        clickOn(textEmail).write("admin@gmail.com");
        clickOn(pswContraseña).write("Abcd*1234");
        clickOn(btnInicioSesion);

    }
    /**
     * Test user creation.
     */
    /**
     * @Test //@Ignore public void testI_createUser() { //get row count int
     * rowCount = tableZona.getItems().size(); //get an existing login from
     * random generator String nombreZona = "Tundra"; //write that login on text
     * field txtNombreZona.clear(); clickOn(tfLogin); write(login);
     * doubleClickOn(tfNombre); write("anyname"); clickOn(btCrear);
     * assertEquals("The row has not been added!!!", rowCount + 1,
     * tableZona.getItems().size()); //look for user in table data model
     * List<Zona> zonas = tableZona.getItems(); assertEquals("La zona no se ha
     * añadido!!!", zonas.stream().filter(u ->
     * u.getNombre().equals(nombre)).count(), 1);
     *
     * }
     */
}
