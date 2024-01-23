package logic;

import java.util.List;
import javax.ws.rs.WebApplicationException;
import model.Compra;

/**
 *
 * @author Diego.
 */
public interface CompraInterfaz {

    public String countREST() throws WebApplicationException;

    /**
     * Método para editar una compra(XML)
     *
     * @param requestEntity
     * @param id
     * @throws WebApplicationException
     */
    public void edit_XML(Object requestEntity, String id) throws WebApplicationException;

    /**
     * Método para editar una compra(JSON)
     *
     * @param requestEntity
     * @param id
     * @throws WebApplicationException
     */
    public void edit_JSON(Object requestEntity, String id) throws WebApplicationException;

    /**
     * Método para buscar todas las compras(XML)
     *
     * @param responseType
     * @param id
     * @return
     * @throws WebApplicationException
     */
    public List<Compra> find_XML(Class<Compra> responseType, String id) throws WebApplicationException;

    /**
     * Método para buscar todas las compras(XML)
     *
     * @param responseType
     * @param id
     * @return
     * @throws WebApplicationException
     */
    public List<Compra> find_JSON(Class<Compra> responseType, String id) throws WebApplicationException;

    public List<Compra> findRange_XML(Class<Compra> responseType, String from, String to) throws WebApplicationException;

    public List<Compra> findRange_JSON(Class<Compra> responseType, String from, String to) throws WebApplicationException;

    /**
     * Método para crear una compra(XML)
     *
     * @param requestEntity
     * @throws WebApplicationException
     */
    public void create_XML(Object requestEntity) throws WebApplicationException;

    /**
     * Método para crear una compra(JSON)
     *
     * @param requestEntity
     * @throws WebApplicationException
     */
    public void create_JSON(Object requestEntity) throws WebApplicationException;

    /**
     * Método para buscar todas las compras(XML)
     *
     * @param responseType
     * @return
     * @throws WebApplicationException
     */
    public List<Compra> findAll_XML(Class<Compra> responseType) throws WebApplicationException;

    /**
     * Método para buscar todas las compras(JSON)
     *
     * @param responseType
     * @return
     * @throws WebApplicationException
     */
    public List<Compra> findAll_JSON(Class<Compra> responseType) throws WebApplicationException;

    /**
     * Método para borrar una compra
     *
     * @param id
     * @throws WebApplicationException
     */
    public void remove(String id) throws WebApplicationException;

}
