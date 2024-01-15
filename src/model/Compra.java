package model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entidad de Compra
 *
 * @author Diego, Ander, Adrian.
 */
@XmlRootElement
public class Compra implements Serializable {

    /**
     * Atributos de la clase Compra con sus respectivas anotaciones
     */
    private static final long serialVersionUID = 1L;
    private CompraId compraId;
    private Date fecha_compra;
    private Cliente cliente;
    private Entrada entrada;

    /**
     * Getter de compraID
     *
     * @return compraId
     */
    public CompraId getCompraId() {
        return compraId;
    }

    /**
     * Setter de compraId
     *
     * @param compraId
     */
    public void setCompraId(CompraId compraId) {
        this.compraId = compraId;
    }

    /**
     * Getter de fecha_compra
     *
     * @return
     */
    public Date getFecha_compra() {
        return fecha_compra;
    }

    /**
     * Setter de fecha compra
     *
     * @param fecha_compra
     */
    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    /**
     * Getter de cliente
     *
     * @return cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Setter de cliente
     *
     * @param cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Getter de Entrada
     *
     * @return entrada
     */
    public Entrada getEntrada() {
        return entrada;
    }

    /**
     * Setter de entrada
     *
     * @param entrada
     */
    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }

    /**
     * Método autogenerado de hashcode
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecha_compra != null ? fecha_compra.hashCode() : 0);
        return hash;
    }

    /**
     * Método autogenerado de equals
     *
     * @param object
     *
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.fecha_compra == null && other.fecha_compra != null) || (this.fecha_compra != null && !this.fecha_compra.equals(other.fecha_compra))) {
            return false;
        }
        return true;
    }

    /**
     * Método autogenerado de toString
     *
     * @return Entidades.Compra
     */
    @Override
    public String toString() {
        return "Entidades.Compra[ id=" + fecha_compra + " ]";
    }

}
