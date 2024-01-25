package chiper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
    private static final String PUBLIC_KEY_PATH = "C:\\Cifrado\\publicKey.der";

    private PublicKey loadPublicKey() {
        // Load Public Key from file
        try {
            byte[] keyBytes = Files.readAllBytes(Paths.get(PUBLIC_KEY_PATH));
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(spec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void keyGenerator() {
        Chiper.GenerarClaves gc = new Chiper.GenerarClaves();
        gc.keyGenerator("C:\\Cifrado");
    }

    private void encryptAndSaveData(String message, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedData = cipher.doFinal(message.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private byte[] fileReader(String path) {
        byte[] ret = null;
        try {
            ret = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static void main(String[] args) {
        Asimetricocliente asimetricC = new Asimetricocliente();

        // Lanza el generador de claves
        asimetricC.keyGenerator();

        // Load Public Key
        PublicKey publicKey = asimetricC.loadPublicKey();

        if (publicKey != null) {
            // Create directory if it doesn't exist
            String folderPath = "C:\\Cifrado";
            File folder = new File(folderPath);

            if (!folder.exists()) {
                if (folder.mkdir()) {
                    System.out.println("Carpeta creada exitosamente en C:");
                } else {
                    System.err.println("Error al crear la carpeta");
                    return;
                }
            } else {
                System.err.println("La carpeta ya existe");
            }

            String message = "Mensjae a cifrar"; // Reemplaza esto con el mensaje que desees cifrar

            // Encrypt and Save Data
            asimetricC.encryptAndSaveData(message, publicKey);
        } else {
            System.out.println("Error: Clave pública no encontrada.");
        }
    }
}
