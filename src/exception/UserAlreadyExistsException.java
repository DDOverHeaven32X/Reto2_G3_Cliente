/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta expecion es lanzada cuando el usuario ya existe.
 *
 * @author Ander
 */
public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException() {
        try {
            throw new Exception("El usuario ya existe ");
        } catch (Exception ex) {
            Logger.getLogger(UserAlreadyExistsException.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
