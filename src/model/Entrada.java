package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entidad de entrada
 *
 * @author Diego.
 */
@XmlRootElement
public class Entrada implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Atributos de la clase Entrada con sus respectivas anotaciones
     */

    private Integer id_entrada;
    private Date fecha_entrada;
    private String tipo_entrada;
    private Float precio;
    private Set<Compra> listaCompras;
    private Set<Zona> listaZonas;
    private Admin admin;

    /**
     * Getter del id de Entrada
     *
     * @return id_entrada
     */
    public Integer getId_entrada() {
        return id_entrada;
    }

    /**
     * Setter del id de Entrada
     *
     * @param id_entrada
     */
    public void setId_entrada(Integer id_entrada) {
        this.id_entrada = id_entrada;
    }

    /**
     * Getter de la fecha de entrada
     *
     * @return fecha_entrada
     */
    public Date getFecha_entrada() {
        return fecha_entrada;
    }

    /**
     * Setter de la fecha de la entrada
     *
     * @param fecha_entrada
     */
    public void setFecha_entrada(Date fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    /**
     * Getter del precio de la entrada
     *
     * @return precio
     */
    public Float getPrecio() {
        return precio;
    }

    /**
     * Setter del precio de la entrada
     *
     * @param precio
     */
    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    /**
     * Getter de la lista de compras
     *
     * @return listaCompras
     */
    public Set<Compra> getListaCompras() {
        return listaCompras;
    }

    /**
     * Setter de la lista de compras
     *
     * @param listaCompras
     */
    public void setListaCompras(Set<Compra> listaCompras) {
        this.listaCompras = listaCompras;
    }
    /**
     * Getter de la lista de zonas
     *
     * @return listaZonas
     */
    public Set<Zona> getListaZonas() {
        return listaZonas;
    }

    /**
     * Setter de la lista de Zonas
     *
     * @param listaZonas
     */
    public void setListaZonas(Set<Zona> listaZonas) {
        this.listaZonas = listaZonas;
    }

    /**
     * Getter de admin
     *
     * @return admin
     */
    public Admin getAdmin() {
        return admin;
    }

    /**
     * Setter de admin
     *
     * @param admin
     */
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    /**
     * Getter de admin
     *
     * @return tipo_entrada
     */
    public String getTipo_entrada() {
        return tipo_entrada;
    }

    /**
     * Setter de admin
     *
     * @param tipo_entrada
     */
    public void setTipo_entrada(String tipo_entrada) {
        this.tipo_entrada = tipo_entrada;
    }

    /**
     * Metodo hasCode autogenerado
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_entrada != null ? id_entrada.hashCode() : 0);
        return hash;
    }

    /**
     * Metodo equals autogenerado
     *
     * @param object
     * @return true
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entrada)) {
            return false;
        }
        Entrada other = (Entrada) object;
        if ((this.id_entrada == null && other.id_entrada != null) || (this.id_entrada != null && !this.id_entrada.equals(other.id_entrada))) {
            return false;
        }
        return true;
    }

    /**
     * Método toString autogenerado
     *
     * @return Entidades.Entrada
     */
    @Override
    public String toString() {
        return "Entidades.Entrada[ id=" + id_entrada + " ]";
    }

}
