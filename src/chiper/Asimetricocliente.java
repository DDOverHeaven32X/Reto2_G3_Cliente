package chiper;

import static com.google.common.io.ByteStreams.toByteArray;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import javax.crypto.Cipher;

/**
 * Clase que se encarga de cifrar la contraseña del cliente de forma asimetrica
 * usando la clave publica de este
 *
 * @author Diego.
 */
public class Asimetricocliente {

    //Ruta de la clave
    private static final String PUBLIC_KEY_PATH = "./src/cipher/publicKey.der";

    /**
     * Método que carga la llave publica
     *
     * @return
     */
    public PublicKey loadPublicKey() {

        try {
            byte[] keyBytes = fileReader(PUBLIC_KEY_PATH);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(spec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método que se encarga de cifrar la contraseña usando la clave pública
     *
     * @param message
     * @param publicKey
     * @return
     */
    public byte[] encryptAndSaveData(String message, PublicKey publicKey) {
        byte[] encryptedData = null;
        try {

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            encryptedData = cipher.doFinal(message.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedData;
    }

    /**
     * Método que lee la llave pública
     *
     * @param path
     * @return
     */
    private byte[] fileReader(String path) {
        byte[] ret = null;
        try {

            InputStream in = getClass().getResourceAsStream("publicKey.der");
            ret = toByteArray(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
