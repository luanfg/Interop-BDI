/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.lithoprototype.model;

import interop.log.model.LogType;
import interop.lithoprototype.util.FaciesLogCorrelation;

import java.util.List;

/** Classe que contém o conjunto de protótipos de litologias (LithologyPrototype).
 *          EM BREVE
 * @author Bruno Zanette
 */
public class LithologyDatabase {
    private List<LogType> logTypes;
    private List<LithologyPrototype> lithologyPrototypes;
    
    
    
    private int indexLogType(LogType logType)
    {
        
        for(LogType currentLogType:logTypes)
        {
            
        }
        return 1;
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
    
    /**
     * Feeds the database with the a facies-log correlation.
     * TO-DO: Maybe implement a binary search or a tree to find the wanted prototype faster.
     * @param faciesLogCorrelation a facies-log correlation.
     */
    public void feedDatabase(FaciesLogCorrelation faciesLogCorrelation)
    {
        List<Integer> bijectionVector;
        
        
        for(LithologyPrototype prototype : lithologyPrototypes)
        {
            if(faciesLogCorrelation.getLithologyUID() == prototype.getLithologyUID())
            {
                prototype.feedPrototype(faciesLogCorrelation);
                return;
            }
        }        
    }
    
    /**
     * Feeds the database with a list of facies-log correlations.
     * @param listOfFaciesLogCorrelation a list of facies-log correlations.
     */
    public void feedDatabase(List<FaciesLogCorrelation> listOfFaciesLogCorrelation)
    {
        for(FaciesLogCorrelation faciesLogCorrelation:listOfFaciesLogCorrelation)
        {
            feedDatabase(faciesLogCorrelation);
        }
    }
     
}
