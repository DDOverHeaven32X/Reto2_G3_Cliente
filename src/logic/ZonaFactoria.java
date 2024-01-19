/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 * Factoria de zona, permite usar las instancias de el manager de zona
 * (ZonaInterfaz) en otras clases que la soliciten
 *
 * @author Ander
 */
public class ZonaFactoria {

    public ZonaInterfaz getFactory() {
        ZonaInterfaz zonaInf = (ZonaInterfaz) new ZonaRESTClient();
        return zonaInf;
    }
}
