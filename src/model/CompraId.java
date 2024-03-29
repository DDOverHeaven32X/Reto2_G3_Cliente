/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase embeddable de Compra, alamcena las FK/PK de la clase compra
 *
 * @author Diego, Ander, Adrían
 */
public class CompraId implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Atributos de la subclase CompraId
     */
    private Integer id_user;
    private Integer id_entrada;

    /**
     * Getter de Id_user
     *
     * @return id_user
     */
    public Integer getId_user() {
        return id_user;
    }

    /**
     * Setter de Id_user
     *
     * @param id_user
     */
    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    /**
     * Getter de Id_entrada
     *
     * @return id_entrada
     */
    public Integer getId_entrada() {
        return id_entrada;
    }

    /**
     * Setter de Id_entrada
     *
     * @param id_entrada
     */
    public void setId_entrada(Integer id_entrada) {
        this.id_entrada = id_entrada;
    }

    /**
     * Método autogenerado de hashcode
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id_user);
        hash = 89 * hash + Objects.hashCode(this.id_entrada);
        return hash;
    }

    /**
     * Método autogenerado de equals
     *
     * @param obj
     *
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CompraId other = (CompraId) obj;
        if (!Objects.equals(this.id_user, other.id_user)) {
            return false;
        }
        if (!Objects.equals(this.id_entrada, other.id_entrada)) {
            return false;
        }
        return true;
    }

    /**
     * Método autogenerado de toString
     *
     * @return CompraID
     */
    @Override
    public String toString() {
        return "CompraID{" + "n_tarjeta=" + id_user + ", id_entrada=" + id_entrada + '}';
    }

}
