/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 * Factoria de Entrada, permite usar las instancias de el manager de entrada (EntradaInterfaz)
 * en otras clases que la soliciten
 * @author Diego
 */
public class EntradaFactoria {
    public EntradaInterfaz getFactory(){
        EntradaInterfaz entInf = new EntradaRESTClient();
        return entInf;
    }
    
}
