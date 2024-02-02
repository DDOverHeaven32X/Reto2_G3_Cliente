package model;

import java.util.Date;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entidad Admin, hereda de la entidad USUARIO
 *
 * @author Diego.
 */
@XmlRootElement
public class Admin extends Usuario {

    private static final long serialVersionUID = 1L;

    private Date fecha_creacion;
    private Set<Animal> listaAnimales;
    private Set<Zona> listaZonas;
    private Set<Entrada> listaEntrada;

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Set<Animal> getListaAnimales() {
        return listaAnimales;
    }

    public void setListaAnimales(Set<Animal> listaAnimales) {
        this.listaAnimales = listaAnimales;
    }

    public Set<Zona> getListaZonas() {
        return listaZonas;
    }

    public void setListaZonas(Set<Zona> listaZonas) {
        this.listaZonas = listaZonas;
    }

    public Set<Entrada> getListaEntrada() {
        return listaEntrada;
    }

    public void setListaEntrada(Set<Entrada> listaEntrada) {
        this.listaEntrada = listaEntrada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecha_creacion != null ? fecha_creacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admin)) {
            return false;
        }
        Admin other = (Admin) object;
        if ((this.fecha_creacion == null && other.fecha_creacion != null) || (this.fecha_creacion != null && !this.fecha_creacion.equals(other.fecha_creacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Admin[ id=" + fecha_creacion + " ]";
    }

}
