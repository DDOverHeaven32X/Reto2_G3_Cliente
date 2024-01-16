/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import model.Entrada;

/**
 * Jersey REST client generated for REST resource:EntradaFacadeREST
 * [entidades.entrada]<br>
 * USAGE:
 * <pre>
 *        EntradaRESTClient client = new EntradaRESTClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author conke
 */
public class EntradaRESTClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/Reto2_G3_Servidor/webresources";

    public EntradaRESTClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("entidades.entrada");
    }

    public List<Entrada>  filtrarEntradaPorFecha_XML(Class<Entrada> responseType, String fechaCon) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("verEntradasporFecha/{0}", new Object[]{fechaCon}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(new GenericType<List<Entrada>>() {
        });
    }

    public List<Entrada> filtrarEntradaPorFecha_JSON(Class<Entrada> responseType, String fechaCon) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("verEntradasporFecha/{0}", new Object[]{fechaCon}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<Entrada>>() {
        });
    }

    public void edit_XML(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public void edit_JSON(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public List<Entrada> find_XML(Class<Entrada> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("buscarEntradaPorId/{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(new GenericType<List<Entrada>>() {
        });
    }

    public List<Entrada> find_JSON(Class<Entrada> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("buscarEntradaPorId/{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<Entrada>>() {
        });
    }

    public void create_XML(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public void create_JSON(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public List<Entrada> filtrarEntradaPorPrecio_XML(Class<Entrada> responseType, String precio) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("verEntradasporPrecio/{0}", new Object[]{precio}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(new GenericType<List<Entrada>>() {
        });
    }

    public List<Entrada> filtrarEntradaPorPrecio_JSON(Class<Entrada> responseType, String precio) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("verEntradasporPrecio/{0}", new Object[]{precio}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<Entrada>>() {
        });
    }

    public List<Entrada> findAll_XML(Class<Entrada> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(new GenericType<List<Entrada>>() {
        });
    }

    public <T> T findAll_JSON(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void remove(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("borrarEntrada/{0}", new Object[]{id})).request().delete();
    }

    public <T> T filtrarEntradaDeUsuario_XML(Class<T> responseType, String login) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("verEntradaCliente/{0}", new Object[]{login}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T filtrarEntradaDeUsuario_JSON(Class<T> responseType, String login) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("verEntradaCliente/{0}", new Object[]{login}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
