/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author Adrian
 */
public class AnimalFactoria {
    public AnimalInterfaz getFactory() {
        AnimalInterfaz animalInf = new AnimalRESTClient();
        return animalInf;
    }
}
