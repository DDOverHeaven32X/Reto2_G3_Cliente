package logic;

import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.WebApplicationException;
import model.Admin;

/**
 * Interfaz que gestiona todos los métodos de lógica relacionados con la entidad
 * Admin en el lado cliente.
 *
 * @author Diego, Adrían.
 */
public interface AdminInterfaz {

    public String countREST() throws WebApplicationException;

    /**
     * Método para editar un admin(XML)
     *
     * @param requestEntity
     * @param id
     * @throws WebApplicationException
     */
    public void edit_XML(Object requestEntity, String id) throws WebApplicationException;

    /**
     * Método para editar un admin(XML)
     *
     * @param requestEntity
     * @param id
     * @throws WebApplicationException
     */
    public void edit_JSON(Object requestEntity, String id) throws WebApplicationException;

    /**
     * Método para buscar a un admin(XML)
     *
     * @param responseType
     * @param id
     * @return
     * @throws WebApplicationException
     */
    public List<Admin> find_XML(Class<Admin> responseType, String id) throws WebApplicationException;

    /**
     * Método para buscar un admin(JSON)
     *
     * @param responseType
     * @param id
     * @return
     * @throws WebApplicationException
     */
    public List<Admin> find_JSON(Class<Admin> responseType, String id) throws WebApplicationException;

    public List<Admin> findRange_XML(Class<Admin> responseType, String from, String to) throws WebApplicationException;

    public List<Admin> findRange_JSON(Class<Admin> responseType, String from, String to) throws WebApplicationException;

    /**
     * Método para crear un Admin(XML)
     *
     * @param requestEntity
     * @throws WebApplicationException
     */
    public void create_XML(Object requestEntity) throws WebApplicationException;

    /**
     * Método para crear un Admin(JSON)
     *
     * @param requestEntity
     * @throws ClientErrorException
     */
    public void create_JSON(Object requestEntity) throws ClientErrorException;

    /**
     * Método que muestra todos los Admins(XML)
     *
     * @param responseType
     * @return
     * @throws WebApplicationException
     */
    public List<Admin> findAll_XML(Class<Admin> responseType) throws WebApplicationException;

    /**
     * Método que muestra todos los Admins(JSON)
     *
     * @param responseType
     * @return
     * @throws WebApplicationException
     */
    public List<Admin> findAll_JSON(Class<Admin> responseType) throws WebApplicationException;

    /**
     * Método para borrar Admins
     *
     * @param id
     * @throws WebApplicationException
     */
    public void remove(String id) throws WebApplicationException;

}
