/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author 2dam
 */
public class SesionUsuario {

    private static SesionUsuario sUsuario = null;
    private Usuario user;

    public SesionUsuario() {
        this.user = null;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public static SesionUsuario getSUsuario() {
        if (sUsuario==null) {
            sUsuario=new SesionUsuario();
        }
        return sUsuario;

    }

}
