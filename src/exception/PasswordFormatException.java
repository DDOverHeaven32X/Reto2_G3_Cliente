/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta expecion es lanzada cuando hay un error en el formato de la contraseña.
 *
 * @author Ander
 */
public class PasswordFormatException extends Exception {

    public PasswordFormatException() {
        try {
            throw new Exception("Formato erroneo, introduce más 8 carácteres alfanuméricos"
                    + " añade una minuscula o mayúscula al menos.");
        } catch (Exception ex) {
            Logger.getLogger(UserAlreadyExistsException.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
