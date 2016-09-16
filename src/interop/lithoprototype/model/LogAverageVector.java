/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.lithoprototype.model;

import java.util.List;

/** Classe que contém um vetor de médias.
 *  CONTEXTO: Médias de valores de logs referentes a uma litologia
 * @author Bruno Zanette
 */
public class LogAverageVector {
    private List<Double> logAverages;
    private List<Integer> numberOfSamples; 

    /**
     * 
     * @return A list of doubles representing the average values of the logs. 
     */
    public List<Double> getAverageVector()
    {
        return logAverages;
    }
}
