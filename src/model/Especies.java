/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Esta es una clase simple de Transferencia de Datos (DTO) que encapsula la
 * información sobre las especies.
 *
 * @author Adrian
 */
@XmlRootElement
public class Especies implements Serializable {

    /**
     * Declaramos la lista de strings para guardar las especies
     */
    private List<String> especies;

    /**
     * constructor vacio
     */
    public Especies() {
    }
    
    /**
     * constructor
     *
     * @param especies
     */
    public Especies(List<String> especies) {
        this.especies = especies;
    }

    /**
     * Getter de especies
     *
     * @return especies
     */
    public List<String> getEspecies() {
        return especies;
    }

    /**
     * Setter de especies
     *
     * @param especies
     */
    public void setEspecies(List<String> especies) {
        this.especies = especies;
    }

    @Override
    public String toString() {
        return especies+"";
    }
}