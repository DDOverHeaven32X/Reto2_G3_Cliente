/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.List;
import javax.ws.rs.WebApplicationException;
import model.Entrada;

/**
 * Intefraz que gestiona todos los métodos de logica de la entidad Entrada en el lado cliente
 * @author Diego
 */
public interface EntradaInterfaz {
    /**
     * Método que filtra una entrada por una fecha especifica, devuelve sus datos en XML
     * @param responseType
     * @param fechaCon
     * @return
     * @throws WebApplicationException 
     */
    public List<Entrada>  filtrarEntradaPorFecha_XML(Class<Entrada> responseType, String fechaCon) throws WebApplicationException;
    /**
     * Método que filtra una entrada por una fecha especifica, devuelve sus datos en JSON
     * @param responseType
     * @param fechaCon
     * @return
     * @throws WebApplicationException 
     */
    public List<Entrada> filtrarEntradaPorFecha_JSON(Class<Entrada> responseType, String fechaCon) throws WebApplicationException;
    /**
     * Método CRUD de UPDATE, modifica una Entrada(XML)
     * @param requestEntity
     * @throws WebApplicationException 
     */
    public void edit_XML(Object requestEntity) throws WebApplicationException;
    /**
     * Método CRUD de UPDATE, modifica una Entrada(JSON)
     * @param requestEntity
     * @throws WebApplicationException 
     */
    public void edit_JSON(Object requestEntity) throws WebApplicationException;
    /**
     * Método que busca una entrada por su ID(XML)
     * @param responseType
     * @param id
     * @return
     * @throws WebApplicationException 
     */
    public List<Entrada> find_XML(Class<Entrada> responseType, String id) throws WebApplicationException;
    /**
     * Método que busca una entrada por su ID(JSON)
     * @param responseType
     * @param id
     * @return
     * @throws WebApplicationException 
     */
    public List<Entrada> find_JSON(Class<Entrada> responseType, String id) throws WebApplicationException;
    /**
     * Método CRUD CREATE, crea una nueva Entrada(XML)
     * @param requestEntity
     * @throws WebApplicationException 
     */
    public void create_XML(Object requestEntity) throws WebApplicationException;
    /**
     * Método CRUD CREATE, crea una nueva Entrada(JSON)
     * @param requestEntity
     * @throws WebApplicationException 
     */
    public void create_JSON(Object requestEntity) throws WebApplicationException;
    /**
     * Método que filtra una entrada por un precio(XML)
     * @param responseType
     * @param precio
     * @return
     * @throws WebApplicationException 
     */
    public List<Entrada> filtrarEntradaPorPrecio_XML(Class<Entrada> responseType, String precio) throws WebApplicationException;
    /**
     * Método que filtra una entrada por un precio(JSON)
     * @param responseType
     * @param precio
     * @return
     * @throws WebApplicationException 
     */
    public List<Entrada> filtrarEntradaPorPrecio_JSON(Class<Entrada> responseType, String precio) throws WebApplicationException;
    /**
     * Método que mustra todas las Entradas disponibles(XML)
     * @param responseType
     * @return
     * @throws WebApplicationException 
     */
    public List<Entrada> findAll_XML(Class<Entrada> responseType) throws WebApplicationException;
    /**
     * Método que mustra todas las Entradas disponibles(JSON)
     * @param responseType
     * @return
     * @throws WebApplicationException 
     */
    public List<Entrada> findAll_JSON(Class<Entrada> responseType) throws WebApplicationException;
    /**
     * Método CRUD DELETE, borra una entrada existente
     * @param id
     * @throws WebApplicationException 
     */
    public void remove(String id) throws WebApplicationException;
    /**
     * Método que filtra entradas compradas de un cliente en específico(XML)
     * @param responseType
     * @param login
     * @return
     * @throws WebApplicationException 
     */
    public List<Entrada>  filtrarEntradaDeUsuario_XML(Class<Entrada> responseType, String login) throws WebApplicationException;
    /**
     * Método que filtra entradas compradas de un cliente en específico(JSON)
     * @param responseType
     * @param login
     * @return
     * @throws WebApplicationException 
     */
    public List<Entrada>  filtrarEntradaDeUsuario_JSON(Class<Entrada> responseType, String login) throws WebApplicationException;
    
}
