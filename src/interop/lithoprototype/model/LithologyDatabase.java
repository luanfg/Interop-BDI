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
 *          EM BREVE
 * @author Bruno Zanette
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
    
    public List<LithologyPrototype> getLithologiesPrototypes(){
        return lithologyPrototypes;
    }
    
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
}
