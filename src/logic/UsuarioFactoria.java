/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 * Factoria de Usuario que permite usar las instancias de el manager de usaurio (UsuarioInterfaz)
 * en otras clases que la soliciten
 * @author Diego
 */
public class UsuarioFactoria {
    public UsuarioInterfaz getFactory(){
        UsuarioInterfaz useInter = new UsuarioRESTClient();
        return useInter;
    }
}
