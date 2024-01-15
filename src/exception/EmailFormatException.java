/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta expecion es lanzada cuando hay un error en el formato del email.
 *
 * @author Ander
 */
public class EmailFormatException extends Exception {

    public EmailFormatException() {
        try {
            throw new Exception("El formato no está permitido (ej, xxx@xxx.xxx) y no debe tener mas de 40 caracteres.");
        } catch (Exception ex) {
            Logger.getLogger(UserAlreadyExistsException.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
