/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import org.junit.Ignore;
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

    /**
     * Prueba para comprobar la funcionalidad del menú de navegación.
     *
     * @author Adrian
     */
    @Test
    public void TestC_comprobar_menubar() {
        clickOn("#menuNavegar");
        clickOn("#mItemAnimales");
    }

    /**
     * Prueba para habilitar los botones según el tipo de usuario.
     *
     * @author Adrian
     */
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
     * Prueba para crear un nuevo animal.
     *
     * @author Adrian
     *
     */
    @Ignore
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
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        press(KeyCode.DOWN);
        press(KeyCode.ENTER);
        clickOn(txtEdad).write("7");
        clickOn(txtPeso).write("60");
        clickOn(txtAltura).write("1.1");
        clickOn(comboAlimentacion);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        press(KeyCode.DOWN);
        press(KeyCode.DOWN);
        press(KeyCode.ENTER);
        clickOn(comboZona);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
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

    /**
     * Prueba para intentar crear un animal que ya existe.
     *
     * @author Adrian
     */
    @Ignore
    @Test
    public void TestF_createAnimalExiste() {
        int rowCount = tableAnimal.getItems().size();
        // Simulate filling out zone creation fields
        clickOn(txtNombreAnimal).write("Paco");
        String nombre = txtNombreAnimal.getText();
        clickOn(txtGenero);
        press(KeyCode.DOWN);
        press(KeyCode.ENTER);
        clickOn(txtEspecie).write("Tigre");
        clickOn(comboSalud);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        press(KeyCode.DOWN);
        press(KeyCode.ENTER);
        clickOn(txtEdad).write("7");
        clickOn(txtPeso).write("60");
        clickOn(txtAltura).write("1.1");
        clickOn(comboAlimentacion);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        press(KeyCode.DOWN);
        press(KeyCode.DOWN);
        press(KeyCode.ENTER);
        clickOn(comboZona);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        press(KeyCode.DOWN);
        press(KeyCode.ENTER);
        clickOn(btnCrearAimal);

        verifyThat("El animal que intentas crear ya existe",
                isVisible());
        clickOn("Aceptar");
        assertEquals("No se ha añadido ningun animal!", rowCount, tableAnimal.getItems().size());
    }

    /**
     * Prueba para modificar un animal existente.
     *
     * @author Adrian
     */
    @Ignore
    @Test
    public void TestG_modificarAnimal() {
        // Verifica que la tabla tenga datos antes de intentar modificarlos
        int rowCount = tableAnimal.getItems().size();
        assertNotEquals("La tabla no tiene datos: no se puede realizar la prueba.", rowCount, 0);
        Animal ultimoAnimal = tableAnimal.getItems().get(rowCount - 1);

        clickOn(tableAnimal).clickOn("Diego");

        // Borra los campos y establece los valores en null
        txtNombreAnimal.setText("");
        txtEspecie.setText("");

        // Modifica el animal seleccionado
        Animal selectedAnimal = tableAnimal.getSelectionModel().getSelectedItem();
        Animal modifiedAnimal = new Animal();

        // Modifica los datos del animal
        modifiedAnimal.setNombre(selectedAnimal.getNombre() + " Modificado");
        clickOn(txtNombreAnimal).eraseText(selectedAnimal.getNombre().length()).write(modifiedAnimal.getNombre());

        modifiedAnimal.setEspecie(selectedAnimal.getEspecie() + " Modificado");
        clickOn(txtEspecie).eraseText(selectedAnimal.getEspecie().length()).write(modifiedAnimal.getEspecie());

        // Selecciona un valor en el combo de salud
        clickOn(comboSalud);
        press(KeyCode.DOWN);
        press(KeyCode.ENTER);

        // Guarda los cambios
        clickOn(btnModificarAnimal);

        // Verifica que el animal se haya modificado correctamente en la tabla
        List<Animal> animales = tableAnimal.getItems();
        assertEquals("El animal se ha modificado correctamente!",
                animales.stream().filter(a -> a.getNombre().equals(modifiedAnimal.getNombre())).count(), 1);
    }

    /**
     * Prueba para cancelar la eliminación de un animal.
     *
     * @author Adrian
     */
    @Ignore
    @Test
    //@Ignore
    public void testH_cancelar_eliminar_animal() {
        // Verificar que la tabla tenga al menos una fila
        int rowCount = tableAnimal.getItems().size();
        assertNotEquals("La tabla no tiene datos: No se puede realizar la prueba.", rowCount, 0);

        //Le decimos que haga scroll hasta la ultima posicion
        Platform.runLater(() -> {
            tableAnimal.scrollTo(rowCount);
        });

        // Buscar la primera fila en la tabla y hacer clic en ella
        clickOn(tableAnimal).clickOn(tableAnimal);

        // Verificar que el botón de eliminar está habilitado
        verifyThat(btnEliminarAnimal, isEnabled());

        clickOn(btnEliminarAnimal);

        // Verificar que se muestra el mensaje de confirmación
        verifyThat("¿Estas seguro que deseas eliminar el animal?", isVisible());

        // Confirmar la eliminación haciendo clic en el botón predeterminado del diálogo de confirmación
        clickOn("Cancelar");

        assertEquals("A row has been deleted!!!", rowCount, tableAnimal.getItems().size());
    }

    /**
     * Prueba para eliminar un animal.
     *
     * @author Adrian
     */
    @Ignore
    @Test
    public void testI_deleteAnimal() {
        // Verificar que la tabla tenga al menos una fila
        int rowCount = tableAnimal.getItems().size();
        assertNotEquals("La tabla no tiene datos: No se puede realizar la prueba.", rowCount, 0);

        //Le decimos que haga scroll hasta la ultima posicion
        Platform.runLater(() -> {
            tableAnimal.scrollTo(rowCount);
        });

        // Buscar la primera fila en la tabla y hacer clic en ella
        clickOn(tableAnimal).clickOn("Paco");

        // Verificar que el botón de eliminar está habilitado
        verifyThat(btnEliminarAnimal, isEnabled());

        clickOn(btnEliminarAnimal);

        // Verificar que se muestra el mensaje de confirmación
        verifyThat("¿Estas seguro que deseas eliminar el animal?", isVisible());

        // Confirmar la eliminación haciendo clic en el botón predeterminado del diálogo de confirmación
        clickOn("Aceptar");

        // Verificar que la fila se ha eliminado
        assertEquals("¡La fila se ha eliminado!", rowCount - 1, tableAnimal.getItems().size());

    }

    /**
     * Prueba para filtrar animales por especie.
     *
     * @author Adrian
     */
    @Ignore
    @Test
    public void testJ_FiltrarEspecie() {
        clickOn(comboFiltrar);
        type(KeyCode.DOWN);
        clickOn("Por especie");
        verifyThat(comboFiltrarEspecie, isVisible());
        verifyThat(comboFiltrarEspecie, isEnabled());
        verifyThat(btnBuscar, isVisible());
        verifyThat(btnBuscar, isEnabled());

        clickOn(comboFiltrarEspecie);
        press(KeyCode.DOWN);
        press(KeyCode.ENTER);

        clickOn(btnBuscar);

        List<Animal> animales = tableAnimal.getItems();
        long count = animales.stream().filter(a -> a.getEspecie().equals("Mandrill")).count();
        assertEquals("El número de animales con el nombre 'Mandrill' no es el esperado", 1, count);

    }

    /**
     * Prueba casos de error al crear un animal.
     *
     * @author Adrian
     */
    @Ignore
    @Test
    public void testErroresCreacionAnimal() {
        txtNombreAnimal.clear();
        txtGenero.setValue(null);
        txtEspecie.clear();
        txtEdad.clear();
        txtPeso.clear();
        txtAltura.clear();
        comboAlimentacion.setValue(null);
        comboSalud.setValue(null);

        clickOn(tableAnimal).clickOn("Simba");

        completarCampo(txtNombreAnimal, "Simba123");
        clickOn(btnCrearAimal);
        verifyThat("El nombre puede tener hasta 20 caracteres y no puede contener números.", isVisible());
        clickOn("Aceptar");

        completarCampo(txtNombreAnimal, "Simba");
        completarCampo(txtEspecie, "Tigre123");
        clickOn(btnCrearAimal);
        verifyThat("La especie puede tener hasta 20 caracteres y no puede contener números.", isVisible());
        clickOn("Aceptar");

        completarCampo(txtEspecie, "Leon");
        completarCampo(txtEdad, "550");
        clickOn(btnCrearAimal);
        verifyThat("La edad no puede ser superior a 500 ni menor a 0.", isVisible());
        clickOn("Aceptar");

        completarCampo(txtEdad, "10");
        completarCampo(txtPeso, "1800001");
        clickOn(btnCrearAimal);
        verifyThat("El peso no puede ser superior a 180000 kg ni menor a 0.", isVisible());
        clickOn("Aceptar");

        completarCampo(txtPeso, "30");
        completarCampo(txtAltura, "6");
        clickOn(btnCrearAimal);
        verifyThat("La altura no puede ser superior a 5 m ni menor a 0.", isVisible());
        clickOn("Aceptar");
    }

    private void completarCampo(TextField campo, String texto) {
        campo.clear();
        clickOn(campo).write(texto);
    }
}
