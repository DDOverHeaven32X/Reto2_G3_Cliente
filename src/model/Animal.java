package model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entidad animal
 *
 * @author Adrian.
 */
@XmlRootElement
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id_animal;
    private String nombre;
    private String genero;
    private Integer edad;
    private Float peso;
    private Float altura;
    private String especie;
    private Alimentacion alimentacion;
    private Salud salud;
    private Admin admin;
    private Zona zona;

    public Integer getId_animal() {
        return id_animal;
    }

    public void setId_animal(Integer id_animal) {
        this.id_animal = id_animal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Float getAltura() {
        return altura;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Alimentacion getAlimentacion() {
        return alimentacion;
    }

    public void setAlimentacion(Alimentacion alimentacion) {
        this.alimentacion = alimentacion;
    }

    public Salud getSalud() {
        return salud;
    }

    public void setSalud(Salud salud) {
        this.salud = salud;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_animal != null ? id_animal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id_animal fields are not set
        if (!(object instanceof Animal)) {
            return false;
        }
        Animal other = (Animal) object;
        if ((this.id_animal == null && other.id_animal != null) || (this.id_animal != null && !this.id_animal.equals(other.id_animal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Animal[ id=" + id_animal + " ]";
    }

}
