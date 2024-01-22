package logic;

/**
 * Factoria de Compra, permite usar las instancias de el manager de compra
 * (CompraInterfaz) en otras clases que la soliciten
 *
 * @author Diego.
 */
public class CompraFactoria {

    public CompraInterfaz getFactory() {
        CompraInterfaz comInf = new CompraRESTClient();
        return comInf;
    }
}
