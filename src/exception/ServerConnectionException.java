/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta expecion es lanzada cuando hay un error de conexion entre el cliente
 * y el servidor.
 *
 * @author Ander
 */
public class ServerConnectionException extends Exception {

    public ServerConnectionException() {
        try {
            throw new Exception("Error al intentar conectar con el servidor.");
        } catch (Exception ex) {
            Logger.getLogger(UserAlreadyExistsException.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
