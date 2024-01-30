/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.util.List;
import java.util.concurrent.TimeoutException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.AnimalFactoria;
import model.Entrada;
import model.Privilegio;
import model.Usuario;
import model.Zona;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.control.ButtonMatchers.isDefaultButton;
import static org.testfx.util.NodeQueryUtils.isVisible;

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

    private MenuBar menuBar;

    private Menu menuNavegar;

    private MenuItem mItemZonas;

    private Usuario usuario = new Usuario();

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
    public void TestB_comprobar_boton_inicio_habilitado() {
        clickOn(textEmail).write("admin@gmail.com");
        clickOn(pswContraseña).write("Abcd*1234");
        verifyThat(btnInicioSesion, isEnabled());
        clickOn(btnInicioSesion);

    }

    @Test
    public void TestC_comprobar_menubar() {
        clickOn("#menuNavegar");
        clickOn("#mItemZonas");

    }

    @Test
    public void TestD_HabilitarBotones() {
        if (usuario.getTipo_usuario() == Privilegio.ADMIN) {
            verifyThat(btnCrearZona, isEnabled());
            verifyThat(btnModificarZona, isVisible());
            verifyThat(btnEliminarZona, isVisible());
            verifyThat(txtNombreZona, isEnabled());
            verifyThat(txtDescripcionZona, isEnabled());
            verifyThat(txtTipoAnimalZona, isEnabled());
        }
        verifyThat(comboFiltrar, isEnabled());
        verifyThat(txtNombreZona, isVisible());
        verifyThat(txtDescripcionZona, isVisible());
        verifyThat(txtTipoAnimalZona, isVisible());
        // Verificar que el botón de informe está deshabilitado
        verifyThat(btnInforme, isEnabled());

    }

    /**
     * Test user creation.
     */
    @Test
    public void TestE_createZona() {
        int rowCount = tableZona.getItems().size();

        // Simulate filling out zone creation fields
        clickOn(txtNombreZona).write("Tundra");
        String nombre = txtNombreZona.getText();
        clickOn(txtDescripcionZona).write("La tundra es un ecosistema frío y vasto, caracterizado por su suelo congelado.");
        clickOn(txtTipoAnimalZona);
        press(KeyCode.DOWN);
        press(KeyCode.ENTER);
        clickOn(btnCrearZona);

        assertEquals("La zona se ha añadido correctamente!", rowCount + 1, tableZona.getItems().size());
        //look for user in table data model
        List<Zona> zonas = tableZona.getItems();
        assertEquals("La zona se ha añadido correctamente!",
                zonas.stream().filter(z -> z.getNombre().equals(nombre)).count(), 1);
    }

    @Test
    public void TestF_createZonaExiste() {
        int rowCount = tableZona.getItems().size();
        // Simulate filling out zone creation fields
        clickOn(txtNombreZona).write("Tundra");

        clickOn(txtDescripcionZona).write("La tundra es un ecosistema frío y vasto, caracterizado por su suelo congelado.");
        clickOn(txtTipoAnimalZona);
        press(KeyCode.DOWN);
        press(KeyCode.ENTER);
        clickOn(btnCrearZona);

        verifyThat("Esa Zona ya existe. Modifica algun campo.",
                isVisible());
        clickOn("Aceptar");
        assertEquals("No se ha añadido ninguna zona!!", rowCount, tableZona.getItems().size());
    }

    @Test
    public void TestG_modifyZona() {

        int rowCount = tableZona.getItems().size();
        assertNotEquals("La tabla no tiene datos: no se puede realizar la prueba.", rowCount, 0);

        // Buscar y seleccionar la fila añadida 
        clickOn(tableZona).clickOn("Tundra");
        //Borramos los campos
        txtNombreZona.setText("");
        txtDescripcionZona.setText("");

        int selectedIndex = tableZona.getSelectionModel().getSelectedIndex();
        // Modificar la zona seleccionada
        Zona selectedZone = tableZona.getSelectionModel().getSelectedItem();
        Zona modifiedZone = new Zona();

        // Modificar el nombre de la zona
        modifiedZone.setNombre(selectedZone.getNombre() + " Modificado");

        clickOn(txtNombreZona).eraseText(selectedZone.getNombre().length()).write(modifiedZone.getNombre());

        // Modificar la descripción de la zona
        modifiedZone.setDescripcion(selectedZone.getDescripcion() + " Modificado");
        clickOn(txtDescripcionZona).eraseText(selectedZone.getDescripcion().length()).write(modifiedZone.getDescripcion());

        clickOn(txtTipoAnimalZona);
        press(KeyCode.DOWN);
        press(KeyCode.DOWN);
        press(KeyCode.DOWN);

        press(KeyCode.ENTER);

        // Guardar los cambios
        clickOn(btnModificarZona);

        // Verificar que la zona ha sido modificada correctamente
        /* assertEquals("La zona se ha modificado!!",
                modifiedZone,
                (Zona) tableZona.getItems().get(selectedIndex));*/
        List<Zona> zonas = tableZona.getItems();
        assertEquals("La zona se ha añadido correctamente!",
                zonas.stream().filter(z -> z.getNombre().equals(modifiedZone.getNombre()) && z.getDescripcion().equals(modifiedZone.getDescripcion())).count(), 1);
    }

    @Test
    //@Ignore
    public void testH_cancelar_eliminar_zona() {
        // Verificar que la tabla tenga al menos una fila
        int rowCount = tableZona.getItems().size();
        assertNotEquals("La tabla no tiene datos: No se puede realizar la prueba.", rowCount, 0);

        // Buscar la primera fila en la tabla y hacer clic en ella
        clickOn(tableZona).clickOn("Tundra Modificado");

        // Verificar que el botón de eliminar está habilitado
        verifyThat(btnEliminarZona, isEnabled());

        clickOn(btnEliminarZona);

        // Verificar que se muestra el mensaje de confirmación
        verifyThat("¿Estás seguro de que deseas eliminar la Zona?", isVisible());

        // Confirmar la eliminación haciendo clic en el botón predeterminado del diálogo de confirmación
        clickOn("Cancelar");

        assertEquals("A row has been deleted!!!", rowCount, tableZona.getItems().size());
    }

    @Test
    public void testI_deleteZona() {
        // Verificar que la tabla tenga al menos una fila
        int rowCount = tableZona.getItems().size();
        assertNotEquals("La tabla no tiene datos: No se puede realizar la prueba.", rowCount, 0);

        // Buscar la primera fila en la tabla y hacer clic en ella
        clickOn(tableZona).clickOn("Tundra Modificado");

        // Verificar que el botón de eliminar está habilitado
        verifyThat(btnEliminarZona, isEnabled());

        clickOn(btnEliminarZona);

        // Verificar que se muestra el mensaje de confirmación
        verifyThat("¿Estás seguro de que deseas eliminar la Zona?", isVisible());

        // Confirmar la eliminación haciendo clic en el botón predeterminado del diálogo de confirmación
        clickOn("Aceptar");

        // Verificar que la fila se ha eliminado
        assertEquals("¡La fila se ha eliminado!", rowCount - 1, tableZona.getItems().size());

    }

    @Test
    public void testJ_FiltrarNombre() {
        clickOn(comboFiltrar);
        type(KeyCode.DOWN);
        clickOn("Filtrar por nombre");
        verifyThat(txtFiltrar, isVisible());
        verifyThat(txtFiltrar, isEnabled());
        clickOn(txtFiltrar);
        write("Selva");
        clickOn(btnBuscar);

        String valorString = "Selva";

        List<Zona> zonas = tableZona.getItems();
        long count = zonas.stream().filter(z -> z.getNombre().equals("Selva")).count();
        assertEquals("El número de zonas con el nombre 'Selva' no es el esperado", 2, count);

    }
}
