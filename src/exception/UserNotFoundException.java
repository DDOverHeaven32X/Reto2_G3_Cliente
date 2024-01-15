/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta expecion es lanzada cuando el usuario no se encuentra.
 *
 * @author Ander
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        try {
            throw new Exception("Usuario no encontrado");
        } catch (Exception ex) {
            Logger.getLogger(UserNotFoundException.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
