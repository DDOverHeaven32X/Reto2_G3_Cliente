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
 *
 * @author Diego.
 */
public class Asimetricocliente {

    //private static final String OUTPUT_PATH = "C:\\Cifrado\\UserCredentialC.properties";
    //esta es la linea original
    private static final String PUBLIC_KEY_PATH = "./src/cipher/publicKey.der";
    
    
    public PublicKey loadPublicKey() {
        // Load Public Key from file
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

    /*public void keyGenerator() {
        Chiper.GenerarClaves gc = new Chiper.GenerarClaves();
        gc.keyGenerator("C:\\Cifrado");
    }*/
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

    private byte[] fileReader(String path) {
        byte[] ret = null;
        try {
            
           InputStream in= getClass().getResourceAsStream("publicKey.der");
           ret = toByteArray(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
