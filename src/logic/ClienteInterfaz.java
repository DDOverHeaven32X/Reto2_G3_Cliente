/*
 * Lógica relacionada con la gestión de la entidad Cliente en el lado cliente.
 */
package logic;

import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.WebApplicationException;
import model.Cliente;

/**
 * Interfaz que gestiona todos los métodos de lógica relacionados con la entidad
 * Cliente en el lado cliente.
 *
 * @author Ander
 */
public interface ClienteInterfaz {

    /**
     * Filtra una entrada por una tarjeta específica y devuelve sus datos en
     * formato XML.
     *
     * @param responseType
     * @param n_tarjeta
     * @param pin
     * @return
     * @throws WebApplicationException
     */
    public List<Cliente> filtrarPorTarjeta_XML(Class<Cliente> responseType, String n_tarjeta, String pin) throws ClientErrorException;

    /**
     * Filtra una entrada por una tarjeta específica y devuelve sus datos en
     * formato JSON.
     *
     * @param responseType
     * @param n_tarjeta
     * @param pin
     * @return
     * @throws WebApplicationException
     */
    public List<Cliente> filtrarPorTarjeta_JSON(Class<Cliente> responseType, String n_tarjeta, String pin) throws ClientErrorException;

    /**
     * Actualiza una entidad Cliente en formato XML.
     *
     * @param requestEntity
     * @param id
     * @throws WebApplicationException
     */
    public void edit_XML(Object requestEntity, String id) throws WebApplicationException;

    /**
     * Actualiza una entidad Cliente en formato JSON.
     *
     * @param requestEntity
     * @param id
     * @throws WebApplicationException
     */
    public void edit_JSON(Object requestEntity, String id) throws WebApplicationException;

    /**
     * Busca una entrada por su ID en formato XML.
     *
     * @param responseType
     * @param id
     * @return
     * @throws WebApplicationException
     */
    public Cliente find_XML(Class<Cliente> responseType, String id) throws WebApplicationException;

    /**
     * Busca una entrada por su ID en formato JSON.
     *
     * @param responseType
     * @param id
     * @return
     * @throws WebApplicationException
     */
    public Cliente find_JSON(Class<Cliente> responseType, String id) throws WebApplicationException;

    /**
     * Crea una nueva entidad Cliente en formato XML.
     *
     * @param requestEntity
     * @throws WebApplicationException
     */
    public void create_XML(Object requestEntity) throws WebApplicationException;

    /**
     * Crea una nueva entidad Cliente en formato JSON.
     *
     * @param requestEntity
     * @throws WebApplicationException
     */
    public void create_JSON(Object requestEntity) throws WebApplicationException;

    /**
     * Muestra todas las entidades Cliente disponibles en formato XML.
     *
     * @param responseType
     * @return
     * @throws WebApplicationException
     */
    public List<Cliente> findAll_XML(Class<Cliente> responseType) throws WebApplicationException;

    /**
     * Muestra todas las entidades Cliente disponibles en formato JSON.
     *
     * @param responseType
     * @return
     * @throws WebApplicationException
     */
    public List<Cliente> findAll_JSON(Class<Cliente> responseType) throws WebApplicationException;

    /**
     * Elimina una entrada existente.
     *
     * @param id
     * @throws WebApplicationException
     */
    public void remove(String id) throws WebApplicationException;

    /**
     * Busca un rango de entradas en formato XML.
     *
     * @param responseType
     * @param from
     * @param to
     * @return
     * @throws ClientErrorException
     */
    public List<Cliente> findRange_XML(Class<Cliente> responseType, String from, String to) throws ClientErrorException;

    /**
     * Busca un rango de entradas en formato JSON.
     *
     * @param responseType
     * @param from
     * @param to
     * @return
     * @throws ClientErrorException
     */
    public List<Cliente> findRange_JSON(Class<Cliente> responseType, String from, String to) throws ClientErrorException;

    /**
     * Recupera contraseña en formato XML.
     *
     * @param requestEntity
     * @throws ClientErrorException
     */
    public void RecuperarContra_XML(Object requestEntity) throws ClientErrorException;

    /**
     * Recupera contraseña en formato JSON.
     *
     * @param requestEntity
     * @throws ClientErrorException
     */
    public void RecuperarContra_JSON(Object requestEntity) throws ClientErrorException;

}
