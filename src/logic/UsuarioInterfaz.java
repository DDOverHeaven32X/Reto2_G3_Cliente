/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.List;
import javax.ws.rs.WebApplicationException;
import model.Usuario;

/**
 * Intefraz que gestiona todos los métodos de logica de la entidad Usuario en el lado cliente
 * @author Diego
 */
public interface UsuarioInterfaz {
    /**
     * Método que cuenta los usuarios que hay
     * @return
     * @throws WebApplicationException 
     */
    public String countREST() throws WebApplicationException;
    /**
     * Método que edita el Usuario(XML)
     * @param requestEntity
     * @param id
     * @throws WebApplicationException 
     */
    public void edit_XML(Object requestEntity, String id) throws WebApplicationException;
    /**
     * Método que edita el Usuario(JSON)
     * @param requestEntity
     * @param id
     * @throws WebApplicationException 
     */
    public void edit_JSON(Object requestEntity, String id) throws WebApplicationException;
    /**
     * Método que compruba si un usario con el correo y contraseña dada existe o no(XML)
     * @param responseType
     * @param login
     * @param contraseU00f1a
     * @return
     * @throws WebApplicationException 
     */
    public List<Usuario> find_XML(Class<Usuario> responseType, String login, String contraseU00f1a) throws WebApplicationException;
    /**
     * Método que compruba si un usario con el correo y contraseña dada existe o no(JSON)
     * @param responseType
     * @param login
     * @param contraseU00f1a
     * @return
     * @throws WebApplicationException 
     */
    public List<Usuario> find_JSON(Class<Usuario> responseType, String login, String contraseU00f1a) throws WebApplicationException;
    
    public List<Usuario> findRange_XML(Class<Usuario> responseType, String from, String to) throws WebApplicationException;
    
    public List<Usuario> findRange_JSON(Class<Usuario> responseType, String from, String to) throws WebApplicationException;
    /**
     * Método que busca todos los usuarios(XML)
     * @param responseType
     * @return
     * @throws WebApplicationException 
     */
    public List<Usuario> findAll_XML(Class<Usuario> responseType) throws WebApplicationException;
    /**
     * Método que busca todos los usuarios(JSON)
     * @param responseType
     * @return
     * @throws WebApplicationException 
     */
    public List<Usuario> findAll_JSON(Class<Usuario> responseType) throws WebApplicationException;
    /**
     * Otro método para crear un usuario(XML)
     * @param requestEntity
     * @throws WebApplicationException 
     */
    public void createUsuario_XML(Object requestEntity) throws WebApplicationException;
    /**
     * Otro método para crear un usuario(JSON)
     * @param requestEntity
     * @throws WebApplicationException 
     */
    public void createUsuario_JSON(Object requestEntity) throws WebApplicationException;
    /**
     * Método para borrar un usuario
     * @param id
     * @throws WebApplicationException 
     */
    public void remove(String id) throws WebApplicationException;
    
}
