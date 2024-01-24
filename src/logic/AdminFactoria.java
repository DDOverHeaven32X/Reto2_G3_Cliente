package logic;

/**
 * Factoria de Admin, permite usar las instancias de el manager de admin
 * (AdminInterfaz) en otras clases que la soliciten
 *
 * @author Diego,Adrian.
 */
public class AdminFactoria {

    public AdminInterfaz getFactory() {
        AdminInterfaz admInf = new AdminRESTClient();
        return admInf;
    }
}
