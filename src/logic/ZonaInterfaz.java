/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.List;
import javax.ws.rs.WebApplicationException;
import model.Entrada;
import model.Zona;

/**
 * Interfaz que gestiona todos los métodos de logica de la entidad Zona en el
 * lado cliente
 *
 * @author Ander
 */
public interface ZonaInterfaz {

    /**
     * Método que filtra una zona por un tipo de animal, devuelve sus datos en
     * XML
     *
     * @param responseType
     * @param tipoAnimal
     * @return
     * @throws WebApplicationException
     */
    public List<Zona> filtrarZonaPorTipoAnimal_XML(Class<Zona> responseType, String tipoAnimal) throws WebApplicationException;

    /**
     * Método que filtra una zona por un tipo de animal especifico, devuelve sus
     * datos en JSON
     *
     * @param responseType
     * @param tipoAnimal
     * @return
     * @throws WebApplicationException
     */
    public List<Zona> filtrarZonaPorTipoAnimal_JSON(Class<Zona> responseType, String tipoAnimal) throws WebApplicationException;

    /**
     * Método CRUD de UPDATE, modifica una Entrada(XML)
     *
     * @param requestEntity
     * @throws WebApplicationException
     */
    public void edit_XML(Object requestEntity) throws WebApplicationException;

    /**
     * Método CRUD de UPDATE, modifica una Zona(JSON)
     *
     * @param requestEntity
     * @throws WebApplicationException
     */
    public void edit_JSON(Object requestEntity) throws WebApplicationException;

    /**
     * Método que busca una zona por su ID(XML)
     *
     * @param responseType
     * @param id
     * @return
     * @throws WebApplicationException
     */
    public List<Zona> find_XML(Class<Entrada> responseType, String id) throws WebApplicationException;

    /**
     * Método que busca una zona por su ID(JSON)
     *
     * @param responseType
     * @param id
     * @return
     * @throws WebApplicationException
     */
    public List<Zona> find_JSON(Class<Zona> responseType, String id) throws WebApplicationException;

    /**
     * Método CRUD CREATE, crea una nueva Zona(XML)
     *
     * @param requestEntity
     * @throws WebApplicationException
     */
    public void create_XML(Object requestEntity) throws WebApplicationException;

    /**
     * Método CRUD CREATE, crea una nueva Zona(JSON)
     *
     * @param requestEntity
     * @throws WebApplicationException
     */
    public void create_JSON(Object requestEntity) throws WebApplicationException;

    /**
     * Método que filtra una entrada por un precio(XML)
     *
     * @param responseType
     * @param nombre
     * @return
     * @throws WebApplicationException
     */
    public List<Zona> filtrarZonaPorNombre_XML(Class<Zona> responseType, String nombre) throws WebApplicationException;

    /**
     * Método que filtra una zona por un precio(JSON)
     *
     * @param responseType
     * @param nombre
     * @return
     * @throws WebApplicationException
     */
    public List<Zona> filtrarZonaPorNombre_JSON(Class<Zona> responseType, String nombre) throws WebApplicationException;

    /**
     * Método que mustra todas las Zonas disponibles(XML)
     *
     * @param responseType
     * @return
     * @throws WebApplicationException
     */
    public List<Zona> findAll_XML(Class<Zona> responseType) throws WebApplicationException;

    /**
     * Método que mustra todas las Zonas disponibles(JSON)
     *
     * @param responseType
     * @return
     * @throws WebApplicationException
     */
    public List<Zona> findAll_JSON(Class<Zona> responseType) throws WebApplicationException;

    /**
     * Método CRUD DELETE, borra una zona existente
     *
     * @param id
     * @throws WebApplicationException
     */
    public void remove(String id) throws WebApplicationException;

}
