/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 * Factoria de Cliente, permite usar las instancias de el manager de entrada
 * (ClienteInterfaz) en otras clases que la soliciten
 *
 * @author Ander
 */
public class ClienteFactoria {

    public ClienteInterfaz getFactory() {
        ClienteInterfaz clientInf = new ClienteRESTClient();
        return clientInf;

    }
}
