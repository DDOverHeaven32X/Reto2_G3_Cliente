/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.List;
import java.util.ResourceBundle;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import model.Animal;

/**
 * Jersey REST client generated for REST resource:AnimalFacadeREST
 * [entidades.animal]<br>
 * USAGE:
 * <pre>
 *        AnimalRESTClient client = new AnimalRESTClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Adrian
 */
public class AnimalRESTClient implements AnimalInterfaz {

    private WebTarget webTarget;
    private Client client;
    private final ResourceBundle configFile = ResourceBundle.getBundle("config.url");
    private final String BASE_URI = configFile.getString("BASE_URI");

    public AnimalRESTClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("entidades.animal");
    }

    @Override
    public List<Animal> findAnimalsBySpecies_XML(Class<Animal> responseType, String especie) throws WebApplicationException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("visualizarAnimalesPorEspecie/{0}", new Object[]{especie}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(new GenericType<List<Animal>>() {
        });
    }

    @Override
    public List<Animal> findAnimalsBySpecies_JSON(Class<Animal> responseType, String especie) throws WebApplicationException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("visualizarAnimalesPorEspecie/{0}", new Object[]{especie}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<Animal>>() {
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
    public List<Animal> find_XML(Class<Animal> responseType, String id_animal) throws WebApplicationException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("buscarPorID/{0}", new Object[]{id_animal}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(new GenericType<List<Animal>>() {
        });
    }

    @Override
    public List<Animal> find_JSON(Class<Animal> responseType, String id_animal) throws WebApplicationException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("buscarPorID/{0}", new Object[]{id_animal}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<Animal>>() {
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
    public List<Animal> findAnimalsInAnArea_XML(Class<Animal> responseType, String zona) throws WebApplicationException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("visualizarAnimalesDeUnaZona/{0}", new Object[]{zona}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(new GenericType<List<Animal>>() {
        });
    }

    @Override
    public List<Animal> findAnimalsInAnArea_JSON(Class<Animal> responseType, String zona) throws WebApplicationException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("visualizarAnimalesDeUnaZona/{0}", new Object[]{zona}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<Animal>>() {
        });
    }

    @Override
    public List<Animal> findAll_XML(Class<Animal> responseType) throws WebApplicationException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(new GenericType<List<Animal>>() {
        });
    }

    @Override
    public List<Animal> findAll_JSON(Class<Animal> responseType) throws WebApplicationException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<Animal>>() {
        });
    }

    @Override
    public void remove(String id_animal) throws WebApplicationException {
        webTarget.path(java.text.MessageFormat.format("borrar/{0}", new Object[]{id_animal})).request().delete();
    }

    @Override
    public List<Animal> findAnimalsByFeeding_XML(Class<Animal> responseType, String alimentacion) throws WebApplicationException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("visualizarAnimalesPorAlimentacion/{0}", new Object[]{alimentacion}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(new GenericType<List<Animal>>() {
        });
    }

    @Override
    public List<Animal> findAnimalsByFeeding_JSON(Class<Animal> responseType, String alimentacion) throws WebApplicationException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("visualizarAnimalesPorAlimentacion/{0}", new Object[]{alimentacion}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<Animal>>() {
        });
    }

    @Override
    public List<String> findSpecies_XML(Class<String> responseType) throws WebApplicationException {
        WebTarget resource = webTarget;
        resource = resource.path("listarEspecies");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(new GenericType<List<String>>() {
        });
    }

    @Override
    public List<String> findSpecies_JSON(Class<String> responseType) throws WebApplicationException {
        WebTarget resource = webTarget;
        resource = resource.path("listarEspecies");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<String>>() {
        });
    }

    public void close() {
        client.close();
    }
    
}