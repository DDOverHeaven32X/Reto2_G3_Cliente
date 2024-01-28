/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import aplication.Aplicacion;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeoutException;
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
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;


/**
 * Test que asegura el funcionamiento CRUD de la ventana Entradas
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
    
    private Menu menuNavegar;
    
    private TableView<Entrada> tblEntrada;
    
    private TableColumn tbcIdEntrada, tbcTipo, tbcPrecio, tbcFecha;
    
    private MenuItem mnItemBorrar, mItemEntradas;
    
    @BeforeClass
    public static void openWindow() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Aplicacion.class);
    }
    /**
     * Método que asigna los valores de arriba a los elementos actuales de la ventana
     */
    @Before
    public void getFields(){
        
        btnCrear = lookup("#btnCrear").query();
        btnEliminar = lookup("#btnEliminar").query();
        btnModificar = lookup("#btnModificar").query();
        btnBuscar = lookup("#btnBuscar").query();
        btnTusEntradas = lookup("#btnTusEntradas").query();
        btnInicioSesion = lookup("#btnInicioSesion").query();
        btn_cancelar = lookup("#btn_cancelar").query();
        btn_confirmar = lookup("#btn_confirmar").query();
        
        
        pane = lookup("#pane").query();
        
        Node menuNavegar = lookup("#menuNavegar").nth(1).query();
        Node mItemEntradas = lookup("#mItemEntradas").nth(1).query();
        Node mItemBorrar = lookup("#mItemBorrar").nth(1).query();
        
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
    public void limpiarCampos(){
        txtPrecioEntrada.setText("");
        dtpFecha.setValue(null);
        comboEntrada.setValue("");
    
    }
    /**
     * Test que se asegura de iniciar sesion para llegar hasta la ventana de entrada
     */
    @Test
    public void test1_InicioVentana(){
        clickOn(textEmail);
        write("admin@gmail.com");
        clickOn(txt_contraReve);
        write("Abcd*1234");
        clickOn(btnInicioSesion);
        
        //Acedemos a la ventana de Entrada
        clickOn("#menuNavegar");
        clickOn("#mItemEntradas");
        verifyThat("#pane", isVisible());
    }
    /**
     * Test que comprueba la creacion de una entrada
     */
    @Test
    public void test2_CrearEntrada() {
        // Genera un precio aleatorio
        float valor = new Random().nextFloat();
        String valorString = Float.toString(valor);

        // Contamos cuantas filas hay en la tabla
        int contarFilas = tblEntrada.getItems().size();

        clickOn(txtPrecioEntrada);
        write(valorString);

        clickOn(comboEntrada);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        clickOn("Infantil(0-12)");

        clickOn(dtpFecha);
        write("28/02/2024");

        clickOn(btnCrear);

        List<Entrada> entradas = tblEntrada.getItems();
        //Vertifica que se ha creado una nueva fila en la base de datos
        assertEquals("No se han actualizado las filas", contarFilas+1,tblEntrada.getItems().size());
        // Verifica si se añadió exactamente una entrada con el precio, fecha y tipo de entrada correctos
        assertEquals("La entrada no se ha añadido", entradas.stream()
                .filter(e -> e.getPrecio() == Float.parseFloat(valorString)
                && e.getFecha_entrada().equals(LocalDate.of(2024, 2, 28))
                && e.getTipo_entrada().equals("Infantil(0-12)"))
                .count(), 1);
    }
    /**
     * Test que comprueba si una entrada se ha modificado correctamente
     */
    @Test
    public void test3_ModificaEntrada(){
        //Genera un precio aleatorio
        float valor = new Random().nextFloat();
        String valorString = Float.toString(valor);
        //Selecionamos la entrada del adulto para modificar
        tblEntrada.getSelectionModel().select(3);
        verifyThat(btnModificar, isEnabled());
        clickOn(txtPrecioEntrada);
        txtPrecioEntrada.clear();
        write(valorString);
        clickOn(btnModificar);
        //Recuperamos las entradas
        List<Entrada> entradas = tblEntrada.getItems();
        
        assertEquals("La entrada no se ha modificado", entradas.stream()
                .filter(e -> e.getPrecio() == Float.parseFloat(valorString)).count(), 1);
    }
    /**
     * Test que comprueba si una entrada se ha borrado correctamente
     */
    @Test
    public void test4_BorrarEntrada(){
        //Contamos las filas para ver si se ha borrado la entrada
        int contarFilas = tblEntrada.getItems().size();
        //Borramos la entrada de senior
        tblEntrada.getSelectionModel().select(3);
        verifyThat(btnEliminar, isEnabled());
        clickOn(btnEliminar);
        
        assertEquals("No se han actualizado las filas", contarFilas+1,tblEntrada.getItems().size());
        
    }
    @Test
    public void test5_FiltrarPrecio(){
        clickOn(cbcFiltro);
        type(KeyCode.DOWN);
        clickOn("Filtrar por dinero");
        verifyThat(txtFiltrar, isVisible());
        verifyThat(txtFiltrar, isEnabled());
        clickOn(txtFiltrar);
        write("23");
        clickOn(btnBuscar);
        //Comprobamos que nos ha devuelto una entrada con dicho parámetro
        tblEntrada.getSelectionModel().select(0);
        assertEquals(txtPrecioEntrada.getText(), "23");
        
    }
    @Test
    public void test6_FiltrarFecha(){
        clickOn(cbcFiltro);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        clickOn("Filtrar por fecha");
        verifyThat(dtpFecha, isVisible());
        verifyThat(dtpFecha, isEnabled());
        clickOn(dtpFiltradoFecha);
        write("28/02/2024");
        clickOn(btnBuscar);
        //Comprobamos que nos devuelve una entrada con dicho parametro
        tblEntrada.getSelectionModel().select(0);
        assertEquals(dtpFecha.getValue(), "28/02/2024");
        
                
    }
    @Test
    public void test7_ComprarEntrada(){
        //Vamos a comprar la entrada 4
        tblEntrada.getSelectionModel().select(4);
        clickOn(btnComprar);
        verifyThat("#pane", isVisible());
        clickOn(txt_contraReve1);
        write("1212121212121212");
        clickOn(pswPin);
        write("1111");
        
        clickOn(btn_confirmar);
        
        clickOn("Aceptar");
        clickOn(btn_cancelar);
        clickOn("Aceptar");
        
        clickOn(btnTusEntradas);
        clickOn("Aceptar");
        
    }


}
