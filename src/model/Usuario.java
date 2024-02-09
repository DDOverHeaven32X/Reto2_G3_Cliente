package model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entidad de Usuario
 *
 * @author Ander.
 */
@XmlRootElement
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id_user;
    private String nombre_completo;
    private String contrasena;
    private String login;
    private String direccion;
    private Integer cod_postal;
    private Integer telefono;
    private Privilegio tipo_usuario;

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCod_postal() {
        return cod_postal;
    }

    public void setCod_postal(Integer cod_postal) {
        this.cod_postal = cod_postal;
    }

    public Privilegio getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(Privilegio tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_user != null ? id_user.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id_user == null && other.id_user != null) || (this.id_user != null && !this.id_user.equals(other.id_user))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Usuario[ id=" + id_user + " ]";
    }

}
