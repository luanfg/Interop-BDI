/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.lithoprototype.model;

import interop.lithologyDataCollector.SampleLithology;
import interop.util.Pair;
import interop.lithoprototype.util.FaciesLogCorrelation;
import java.util.ArrayList;
import java.util.List;

/** Classe que contém um vetor de médias.
 *  CONTEXTO: Médias de valores de logs referentes a uma litologia
 * @author Bruno Zanette
 */
public class LogAverageVector {
    private List<Double> logSum;
    private List<Integer> numberOfSamples; 
    float nullvalue = SampleLithology.nullValue;

    public LogAverageVector(){
        logSum = new ArrayList<>();
        numberOfSamples = new ArrayList<>();
        
    }
    
    /**
     * 
     * @return A list of doubles representing the average values of the logs. 
     */
    public List<Double> getAverageVector()
    {
        List<Double> averages = new ArrayList<>();
        for(int i=0; i<logSum.size(); i++){
            if(numberOfSamples.get(i)==0){
                averages.add((double)nullvalue);
            }
            else{
                averages.add( logSum.get(i) / numberOfSamples.get(i) );
            }
        }
        return averages;
    }
    
    void feedAverageVector(List<String> OrganizedSample) {
        if(logSum.isEmpty()){
            for(String value:OrganizedSample){
                logSum.add(0.0);
                numberOfSamples.add(0);
            }
        }
        //System.out.println(logAverages.size());
        for(int i=0; i<OrganizedSample.size(); i++){
            if(Float.parseFloat(OrganizedSample.get(i)) != nullvalue){
                double value = logSum.get(i) + Double.valueOf(OrganizedSample.get(i));
                logSum.set(i, value);
                numberOfSamples.set(i, numberOfSamples.get(i)+1);
            }
        }
    }
}
