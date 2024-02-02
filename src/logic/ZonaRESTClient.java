/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.List;
import java.util.ResourceBundle;
import static javafx.scene.input.KeyCode.T;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import model.Entrada;
import model.Zona;

/**
 * Jersey REST client generated for REST resource:ZonaFacadeREST
 * [entidades.zona]<br>
 * USAGE:
 * <pre>
 *        ZonaRESTClient client = new ZonaRESTClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Ander
 */
public class ZonaRESTClient implements ZonaInterfaz {

    private WebTarget webTarget;
    private Client client;
    private final ResourceBundle configFile = ResourceBundle.getBundle("config.url");
    private final String BASE_URI = configFile.getString("BASE_URI");

    public ZonaRESTClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("entidades.zona");
    }

    @Override
    public List<Zona> filtrarZonaPorTipoAnimal_XML(Class<Zona> responseType, String tipo_animal) throws WebApplicationException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getTipo_animal/{0}", new Object[]{tipo_animal}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(new GenericType<List<Zona>>() {
        });
    }

    @Override
    public List<Zona> filtrarZonaPorTipoAnimal_JSON(Class<Zona> responseType, String tipo_animal) throws WebApplicationException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getTipo_animal/{0}", new Object[]{tipo_animal}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<Zona>>() {
        });
    }

    @Override
    public void edit_XML(Object requestEntity) throws WebApplicationException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    @Override
    public void edit_JSON(Object requestEntity) throws WebApplicationException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    @Override
    public List<Zona> find_XML(Class<Entrada> responseType, String id_zona) throws WebApplicationException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getId_zona/{0}", new Object[]{id_zona}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(new GenericType<List<Zona>>() {
        });
    }

    @Override
    public List<Zona> find_JSON(Class<Zona> responseType, String id_zona) throws WebApplicationException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getId_zona/{0}", new Object[]{id_zona}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<Zona>>() {
        });
    }

    @Override
    public void create_XML(Object requestEntity) throws WebApplicationException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    @Override
    public void create_JSON(Object requestEntity) throws WebApplicationException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    @Override
    public List<Zona> findAll_XML(Class<Zona> responseType) throws WebApplicationException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(new GenericType<List<Zona>>() {
        });
    }

    @Override
    public List<Zona> findAll_JSON(Class<Zona> responseType) throws WebApplicationException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<Zona>>() {
        });
    }

    @Override
    public void remove(String id_zona) throws WebApplicationException {
        webTarget.path(java.text.MessageFormat.format("deleteZona/{0}", new Object[]{id_zona})).request().delete();
    }

    @Override
    public List<Zona> filtrarZonaPorNombre_XML(Class<Zona> responseType, String nombre) throws WebApplicationException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getNombre/{0}", new Object[]{nombre}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(new GenericType<List<Zona>>() {
        });
    }

    @Override
    public List<Zona> filtrarZonaPorNombre_JSON(Class<Zona> responseType, String nombre) throws WebApplicationException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getNombre/{0}", new Object[]{nombre}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<Zona>>() {
        });
    }

    public void close() {
        client.close();
    }

}
