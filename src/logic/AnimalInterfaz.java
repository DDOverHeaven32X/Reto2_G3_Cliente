/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.List;
import javax.ws.rs.WebApplicationException;
import model.Animal;

/**
 *
 * @author 2dam
 */
public interface AnimalInterfaz {

    public List<Animal> findAnimalsBySpecies_XML(Class<Animal> responseType, String especie) throws WebApplicationException;

    public List<Animal> findAnimalsBySpecies_JSON(Class<Animal> responseType, String especie) throws WebApplicationException;

    public void edit_XML(Object requestEntity) throws WebApplicationException;

    public void edit_JSON(Object requestEntity) throws WebApplicationException;

    public List<Animal> find_XML(Class<Animal> responseType, String id_animal) throws WebApplicationException;

    public List<Animal> find_JSON(Class<Animal> responseType, String id_animal) throws WebApplicationException;

    public void create_XML(Object requestEntity) throws WebApplicationException;

    public void create_JSON(Object requestEntity) throws WebApplicationException;

    public List<Animal> findAnimalsInAnArea_XML(Class<Animal> responseType, String zona) throws WebApplicationException;

    public List<Animal> findAnimalsInAnArea_JSON(Class<Animal> responseType, String zona) throws WebApplicationException;

    public List<Animal> findAll_XML(Class<Animal> responseType) throws WebApplicationException;

    public List<Animal> findAll_JSON(Class<Animal> responseType) throws WebApplicationException;

    public void remove(String id_animal) throws WebApplicationException;

    public List<Animal> findAnimalsByFeeding_XML(Class<Animal> responseType, String alimentacion) throws WebApplicationException;

    public List<Animal> findAnimalsByFeeding_JSON(Class<Animal> responseType, String alimentacion) throws WebApplicationException;

    public List<String> findSpecies_XML(Class<String> responseType) throws WebApplicationException;

    public List<String> findSpecies_JSON(Class<String> responseType) throws WebApplicationException;
}
