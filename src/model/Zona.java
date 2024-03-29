package model;

import java.io.Serializable;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entidad de Zona*
 *
 * @author Ander.
 */
@XmlRootElement
public class Zona implements Serializable {

    private static final long serialVersionUID = 1L;
    // Identificador único de la zona
    private Integer id_zona;
    //Atributos
    private String nombre;
    private String tipo_animal;
    private String descripcion;
    private Set<Animal> listaAnimales;
    private Set<Entrada> listaEntradas;
    private Admin admin;

    //Getters y Setters
    /**
     * Obtiene el identificador único de la zona.
     *
     * @return Entero que representa el identificador de la zona.
     */
    public Integer getId_zona() {
        return id_zona;
    }

    /**
     * Establece el identificador único de la zona.
     *
     * @param id_zona Nuevo identificador de la zona a establecer.
     */
    public void setId_zona(Integer id_zona) {
        this.id_zona = id_zona;
    }

    /**
     * Obtiene el nombre de la zona.
     *
     * @return Nombre de la zona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la zona.
     *
     * @param nombre Nuevo nombre de la zona a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el tipo de animal asociado a la zona.
     *
     * @return Tipo de animal de la zona.
     */
    public String getTipo_animal() {
        return tipo_animal;
    }

    /**
     * Establece el tipo de animal asociado a la zona.
     *
     * @param tipo_animal Nuevo tipo de animal de la zona a establecer.
     */
    public void setTipo_animal(String tipo_animal) {
        this.tipo_animal = tipo_animal;
    }

    /**
     * Obtiene la descripción de la zona.
     *
     * @return Descripción de la zona.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la zona.
     *
     * @param descripcion Nueva descripción de la zona a establecer.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la lista de animales asociados a la zona.
     *
     * @return Lista de animales de la zona.
     */
    public Set<Animal> getListaAnimales() {
        return listaAnimales;
    }

    /**
     * Establece la lista de animales asociados a la zona.
     *
     * @param listaAnimales Nueva lista de animales de la zona a establecer.
     */
    public void setListaAnimales(Set<Animal> listaAnimales) {
        this.listaAnimales = listaAnimales;
    }

    /**
     * Obtiene el administrador asociado a la zona.
     *
     * @return Administrador de la zona.
     */
    public Admin getAdmin() {
        return admin;
    }

    /**
     * Establece el administrador asociado a la zona.
     *
     * @param admin Nuevo administrador de la zona a establecer.
     */
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    /**
     * Obtiene la lista de entradas asociadas a la zona.
     *
     * @return Conjunto de entradas de la zona.
     */
    public Set<Entrada> getListaEntradas() {
        return listaEntradas;
    }

    /**
     * Establece la lista de entradas asociadas a la zona.
     *
     * @param listaEntradas Nuevo conjunto de entradas de la zona a establecer.
     */
    public void setListaEntradas(Set<Entrada> listaEntradas) {
        this.listaEntradas = listaEntradas;
    }

    /**
     * Calcula y devuelve un código hash único para la instancia de Zona.
     *
     * @return Código hash calculado para la instancia de Zona.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_zona != null ? id_zona.hashCode() : 0);
        return hash;
    }

    /**
     * Compara esta instancia de Zona con otro objeto para saber si es igual.
     *
     * @param object Objeto a comparar con la instancia de Zona.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zona)) {
            return false;
        }
        Zona other = (Zona) object;
        if ((this.id_zona == null && other.id_zona != null) || (this.id_zona != null && !this.id_zona.equals(other.id_zona))) {
            return false;
        }
        return true;
    }

    /**
     * Devuelve el indentificador de la zona.
     *
     * @return Cadena que representa la instancia de Zona, incluyendo su
     * identificador.
     */
    @Override
    public String toString() {
        return this.nombre;
    }

}
