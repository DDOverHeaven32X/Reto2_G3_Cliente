/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import aplication.Aplicacion;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import model.Entrada;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.util.NodeQueryUtils.isVisible;
import org.testfx.util.WaitForAsyncUtils;

/**
 * Test que asegura el funcionamiento CRUD de la ventana Entradas
 *
 * @author Diego
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EntradaControllerTest extends ApplicationTest {

    //Declaracion de elementos de la ventana
    private Button btnCrear, btnModificar, btnEliminar, btnBuscar, btnInforme, btnComprar, btnTusEntradas, btnInicioSesion, btn_cancelar, btn_confirmar;

    private TextField txtPrecioEntrada, txtFiltrar, textEmail, txt_contraReve, txt_contraReve1;

    private PasswordField pswPin;

    private DatePicker dtpFecha, dtpFiltradoFecha;

    private ComboBox comboEntrada, cbcFiltro;

    private Pane pane;

    private Menu menuNavegar, menuCerrarSesion;

    private TableView<Entrada> tblEntrada;

    private TableColumn tbcIdEntrada, tbcTipo, tbcPrecio, tbcFecha;

    private MenuItem mnItemBorrar, mItemEntradas, mItemCerrarSesion;

    private PasswordField pswContraseña;

    @BeforeClass
    public static void openWindow() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Aplicacion.class);
    }

    /**
     * Método que asigna los valores de arriba a los elementos actuales de la
     * ventana
     */
    @Before
    public void getFields() {

        btnCrear = lookup("#btnCrear").query();
        btnEliminar = lookup("#btnEliminar").query();
        btnModificar = lookup("#btnModificar").query();
        btnBuscar = lookup("#btnBuscar").query();
        btnTusEntradas = lookup("#btnTusEntradas").query();
        btnInicioSesion = lookup("#btnInicioSesion").query();
        btn_cancelar = lookup("#btn_cancelar").query();
        btn_confirmar = lookup("#btn_confirmar").query();
        pswContraseña = lookup("#pswContraseña").query();

        pane = lookup("#pane").query();

        Node menuNavegar = lookup("#menuNavegar").nth(1).query();
        Node mItemEntradas = lookup("#mItemEntradas").nth(1).query();
        Node mItemBorrar = lookup("#mItemBorrar").nth(1).query();
        Node menuCerrarSesion = lookup("#menuCerrarSesion").nth(1).query();
        Node mItemCerrarSesion = lookup("#mItemCerrarSesion").nth(1).query();

        tblEntrada = lookup("#tblEntrada").query();

        Node tbcIdEntrada = lookup("#tbcIdEntrada").nth(1).query();
        Node tbcTipo = lookup("#tbcTipo").nth(1).query();
        Node tbcPrecio = lookup("#tbcPrecio").nth(1).query();
        Node tbcFecha = lookup("#tbcFecha").nth(1).query();

        txtPrecioEntrada = lookup("#txtPrecioEntrada").query();
        txtFiltrar = lookup("#txtFiltrar").query();
        txt_contraReve = lookup("#txt_contraReve").query();
        textEmail = lookup("#textEmail").query();
        txt_contraReve1 = lookup("#txt_contraRevel").query();

        pswPin = lookup("#pswPin").query();

        comboEntrada = lookup("#comboEntrada").queryComboBox();
        cbcFiltro = lookup("#cbcFiltro").queryComboBox();

        dtpFecha = lookup("#dtpFecha").query();
        dtpFiltradoFecha = lookup("#dtcFiltradoFecha").query();

    }

    /**
     * Método que limpia los campos
     */
    public void cambiarAClient() {
        clickOn("#menuCerrarSesion");
        clickOn("#mItemCerrarSesion");
        clickOn("Aceptar");

        textEmail = lookup("#textEmail").query();
        pswContraseña = lookup("#pswContraseña").query();
        btnInicioSesion = lookup("#btnInicioSesion").query();
        Node menuNavegar = lookup("#menuNavegar").nth(1).query();
        Node mItemEntradas = lookup("#mItemEntradas").nth(1).query();

        clickOn(textEmail).write("client@gmail.com");
        clickOn(pswContraseña).write("Abcd*1234");
        verifyThat(btnInicioSesion, isEnabled());
        clickOn(btnInicioSesion);

        clickOn("#menuNavegar");
        clickOn("#mItemEntradas");

    }

    @Ignore
    @Test
    public void TestA_comprobar_boton_inicio_habilitado() {
        clickOn(textEmail).write("admin@gmail.com");
        clickOn(pswContraseña).write("Abcd*1234");
        verifyThat(btnInicioSesion, isEnabled());
        clickOn(btnInicioSesion);

        clickOn("#menuNavegar");
        clickOn("#mItemEntradas");

    }

    /**
     * Test que se asegura de iniciar sesion para llegar hasta la ventana de
     * entrada
     */
    @Ignore
    @Test
    public void TestB_ComprobarCampos() {
        btnCrear = lookup("#btnCrear").query();

        clickOn(btnCrear);
        verifyThat("Antes de realizar una acción, asegúrate de no dejar ningún campo vacío.", isVisible());
        clickOn("Aceptar");

        clickOn(txtPrecioEntrada).write("150");
        clickOn(comboEntrada);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        clickOn("Infantil(0-12)");

        clickOn(dtpFecha).write("08/04/2024");

        clickOn(btnCrear);

        verifyThat("El precio de entrada debe estar entre 0 y 100, ambos no incluidos.", isVisible());

        clickOn("Aceptar");

        clickOn(txtPrecioEntrada).write("23");
        clickOn(comboEntrada);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        clickOn("Infantil(0-12)");

        dtpFecha = lookup("#dtpFecha").query();

        dtpFecha.setValue(null);

        clickOn(dtpFecha).write("08/04/2023");

        clickOn(btnCrear);

        verifyThat("No puedes ingresar fechas anteriores a la actual.", isVisible());

        clickOn("Aceptar");

    }

    /**
     * Test que comprueba la creacion de una entrada
     */
    @Ignore
    @Test
    public void testC_CrearEntrada() {

        // Genera un valor aleatorio entre 10 y 60
        // Contamos cuantas filas hay en la tabla
        int contarFilas = tblEntrada.getItems().size();
        String valorString = "24";
        clickOn(txtPrecioEntrada).write("24");
        clickOn(comboEntrada);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        clickOn("Infantil(0-12)");

        dtpFecha = lookup("#dtpFecha").query();

        clickOn(dtpFecha).write("08/04/2024");

        clickOn(btnCrear);

        //Vertifica que se ha creado una nueva fila en la base de datos
        assertEquals("No se han actualizado las filas", contarFilas + 1, tblEntrada.getItems().size());
        List<Entrada> entradas = tblEntrada.getItems();
        // Verifica si se añadió exactamente una entrada con el precio, fecha y tipo de entrada correctos
        assertEquals("La entrada no se ha añadido correctamente!",
                entradas.stream().filter(e -> e.getPrecio() == Float.parseFloat(valorString)).count(), 1);
    }

    /**
     * Test que comprueba si una entrada se ha modificado correctamente
     */
    @Ignore
    @Test
    public void testD_ModificaEntrada() {
        int rowCount = tblEntrada.getItems().size();
        assertNotEquals("La tabla no tiene datos: no se puede realizar la prueba.", rowCount, 0);

        // Buscar y seleccionar la entrada a modificar
        clickOn(tblEntrada).clickOn("08.04.2024");

        //Borramos los campos
        txtPrecioEntrada.setText("");
        dtpFecha.setValue(null);

        int selectedIndex = tblEntrada.getSelectionModel().getSelectedIndex();
        // Modificar la entrada seleccionada
        Entrada selectedEntrada = tblEntrada.getSelectionModel().getSelectedItem();
        Entrada modifiedEntrada = new Entrada();

        // Modificar el precio de la entrada
        modifiedEntrada.setPrecio(Float.parseFloat("45"));
        clickOn(txtPrecioEntrada).eraseText(selectedEntrada.getPrecio().toString().length()).write(String.valueOf(modifiedEntrada.getPrecio()));

        clickOn(comboEntrada);
        press(KeyCode.DOWN);
        press(KeyCode.DOWN);
        press(KeyCode.DOWN);
        press(KeyCode.ENTER);
        // Modificar la fecha de la entrada
        LocalDate modifiedDate = LocalDate.of(2024, 7, 9);
        dtpFecha.setValue(modifiedDate);

        // Guardar los cambios
        clickOn(btnModificar);

        // Verificar que la entrada ha sido modificada correctamente
        List<Entrada> entradas = tblEntrada.getItems();
        assertEquals("La entrada no se ha modificado correctamente!",
                1,
                entradas.stream().filter(e -> Objects.equals(e.getPrecio(), modifiedEntrada.getPrecio())).count());
    }

    /**
     * Test que comprueba si una entrada se ha borrado correctamente
     */
    @Ignore
    @Test
    public void testE_CancelarBorrarEntrada() {
        //Contamos las filas para ver si se ha borrado la entrada
        // Verificar que la tabla tenga al menos una fila
        int rowCount = tblEntrada.getItems().size();
        assertNotEquals("La tabla no tiene datos: No se puede realizar la prueba.", rowCount, 0);

        clickOn(tblEntrada).clickOn("09.07.2024");
        // Verificar que el botón de eliminar está habilitado
        verifyThat(btnEliminar, isEnabled());

        clickOn(btnEliminar);

        // Verificar que se muestra el mensaje de confirmación
        verifyThat("¿Estás seguro de que deseas eliminar la Entrada?", isVisible());
        // Confirmar la eliminación haciendo clic en el botón predeterminado del diálogo de confirmación
        clickOn("Cancelar");

    }

    @Ignore
    @Test
    public void testF_BorrarEntrada() {
        //Contamos las filas para ver si se ha borrado la entrada
        // Verificar que la tabla tenga al menos una fila
        int rowCount = tblEntrada.getItems().size();
        assertNotEquals("La tabla no tiene datos: No se puede realizar la prueba.", rowCount, 0);

        clickOn(tblEntrada).clickOn("09.07.2024");
        // Verificar que el botón de eliminar está habilitado
        verifyThat(btnEliminar, isEnabled());

        clickOn(btnEliminar);

        // Verificar que se muestra el mensaje de confirmación
        verifyThat("¿Estás seguro de que deseas eliminar la Entrada?", isVisible());
        // Confirmar la eliminación haciendo clic en el botón predeterminado del diálogo de confirmación
        clickOn("Aceptar");

        assertEquals("A row has been deleted!!!", rowCount - 1, tblEntrada.getItems().size());
    }

    @Ignore
    @Test
    public void testG_FiltrarPrecio() {
        clickOn(cbcFiltro);
        type(KeyCode.DOWN);
        clickOn("Filtrar por dinero");
        verifyThat(txtFiltrar, isVisible());
        verifyThat(txtFiltrar, isEnabled());
        clickOn(txtFiltrar);
        write("15");
        clickOn(btnBuscar);

        String valorString = "15";

        Node row = lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ", row);
        clickOn(row);

        List<Entrada> entradas = tblEntrada.getItems();
        assertEquals("La entrada no se ha encontrado", entradas.stream()
                .filter(e -> e.getPrecio() == Float.parseFloat(valorString)).count(), 1);

    }

    @Ignore
    @Test
    public void testH_FiltrarFecha() {
        clickOn(cbcFiltro);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        clickOn("Filtrar por fecha");

        verifyThat(dtpFecha, isEnabled());
        clickOn("#dtpFiltradoFecha").write("24/05/2024");
        clickOn(btnBuscar);

        Node row = lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ", row);
        clickOn(row);

        Date fecha = Date.from(LocalDate.of(2024, 05, 24).atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<Entrada> entradas = tblEntrada.getItems();
        assertEquals("La entrada no se ha encontrado",
                1,
                entradas.stream().filter(e -> e.getFecha_entrada().compareTo(fecha) == 0).count());

    }

    @Ignore
    @Test
    public void testI_ComprarEntrada() {
        //Vamos a comprar la entrada 4
        cambiarAClient();
        clickOn(tblEntrada).clickOn("17.05.2024");
        clickOn("#btnComprar");

        txt_contraReve1 = lookup("#txt_contraRevel").query();
        pswPin = lookup("#pswPin").query();
        btn_confirmar = lookup("#btn_confirmar").query();
        btn_cancelar = lookup("#btn_cancelar").query();
        btnTusEntradas = lookup("#btnTusEntradas").query();

        try {
            WaitForAsyncUtils.waitFor(5, TimeUnit.SECONDS, () -> {
                btn_confirmar = lookup("#btn_confirmar").query();

                return btn_confirmar != null;
            });
            WaitForAsyncUtils.waitFor(5, TimeUnit.SECONDS, () -> {
                txt_contraReve1 = lookup("#txt_contraReve1").query();

                return txt_contraReve1 != null;
            });
        } catch (TimeoutException ex) {
            Logger.getLogger(EntradaControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        clickOn(txt_contraReve1).write("5432123146788766");
        clickOn(pswPin).write("7654");

        clickOn("#btn_confirmar");

        clickOn("Aceptar");

        btnTusEntradas = lookup("#btnTusEntradas").query();

        clickOn(btnTusEntradas);

        verifyThat("Mostrando tus entradas compradas, pulsa aceptar para dejar de verlas", NodeMatchers.isVisible());

        clickOn("Aceptar");

    }

}
