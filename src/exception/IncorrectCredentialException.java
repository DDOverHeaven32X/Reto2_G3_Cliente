/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta expecion es lanzada cuando alguna de las credenciales es incorrecta.
 *
 * @author Ander
 */
public class IncorrectCredentialException extends Exception {

    public IncorrectCredentialException() {
        try {
            throw new Exception("Usuario o contraseña incorrectos.");
        } catch (Exception ex) {
            Logger.getLogger(UserAlreadyExistsException.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
