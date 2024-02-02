package model;

import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Entidad cliente, hereda de la entidad USUARIO
 *
 * @author Diego.
 */
@XmlRootElement
public class Cliente extends Usuario {

    private static final long serialVersionUID = 1L;

    private Long n_tarjeta;
    private Integer pin;
    private Set<Compra> listaCompra;

    public Long getN_tarjeta() {
        return n_tarjeta;
    }

    public void setN_tarjeta(Long n_tarjeta) {
        this.n_tarjeta = n_tarjeta;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public Set<Compra> getListaCompra() {
        return listaCompra;
    }

    public void setListaCompra(Set<Compra> listaCompra) {
        this.listaCompra = listaCompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (n_tarjeta != null ? n_tarjeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.n_tarjeta == null && other.n_tarjeta != null) || (this.n_tarjeta != null && !this.n_tarjeta.equals(other.n_tarjeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Cliente[ id=" + n_tarjeta + " ]";
    }

}
