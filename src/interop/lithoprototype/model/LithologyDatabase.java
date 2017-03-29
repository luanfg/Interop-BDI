/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.lithoprototype.model;

import interop.log.model.LogType;
import interop.lithoprototype.util.FaciesLogCorrelation;
import java.util.ArrayList;

import java.util.List;

/** Classe que contém o conjunto de protótipos de litologias (LithologyPrototype).
 * @author Bruno Zanette
 * @author eduardo
 * 
 */
public class LithologyDatabase {
    private List<LogType> logTypes;
    private List<LithologyPrototype> lithologyPrototypes;
    
    
    
    public LithologyDatabase(List<String> ltw) {
        
        logTypes = new ArrayList<>();
        lithologyPrototypes = new ArrayList<>();
        for(String log:ltw){
            LogType lt = new LogType();
            lt.setLogType(log);
            //lt.setLogDescription( TODO );
            //lt.setLogMeasureUnit( TODO );
            
            logTypes.add(lt);
        }
        
    }
    
    /**
     *
     * @return list of lithologies prototypes
     */
    public List<LithologyPrototype> getLithologiesPrototypes(){
        return getOnlyLithologiesWithMoreThan_X_Samples(10, lithologyPrototypes);
    }
    
    /*
    private int indexLogType(LogType logType)
    {
        
        for(int i=0; i<logTypes.size(); i++){
            if( compareLogType(logTypes.get(i), logType)){
                return i;
            }
        }
        return -1;//ERROR, SHOULD NEVER GET HERE
    }
    
    static private boolean compareLogType(LogType logType1, LogType logType2)
    {
        if(logType1 == logType2)
            return true;
        else if(logType1.getLogType().equals(logType2.getLogType()))
            return true;
        else
            return false;
    }
    
    */

    /**
     *  Add ( or create and add) a sample to a lithology prototype
     * @param lithology
     * @param OrganizedSample
     */
    public void feedDatabase(int lithology, List<String> OrganizedSample) {
        boolean found = false;
        for(LithologyPrototype lp:lithologyPrototypes){
            if(!found){
                if(lp.getLithologyUID()==lithology){
                    lp.feedPrototype(OrganizedSample);
                    found = true;
                }
            }
        }
        if(!found){
            LithologyPrototype newLP = new LithologyPrototype();
            newLP.setLithologyUID(lithology);
            newLP.feedPrototype(OrganizedSample);
            lithologyPrototypes.add(newLP);
        }
    }    

    /*
    Aparentemente quando usamos protótipos de litologias com poucas amostras encontramos erros no cálculo do pdf
    então usando protótipos com pelo menos 10 amostras não foram encontrados erros, entretando não garanto que 
    com outros arquivos esses erros podem ser encontrados.
    */
    private List<LithologyPrototype> getOnlyLithologiesWithMoreThan_X_Samples(int x, List<LithologyPrototype> lithologyPrototypes) {
        List<LithologyPrototype> newList = new ArrayList<>();
        for(LithologyPrototype lithologyPrototype:lithologyPrototypes){
            if(lithologyPrototype.getNumberOfSamples()>x){
                newList.add(lithologyPrototype);
            }
        }
        
        return newList;
    }
}
