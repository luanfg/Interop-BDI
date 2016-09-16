/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.lithoprototype.model;

import interop.lithoprototype.util.FaciesLogCorrelation;

import java.util.List;

/** Classe que contém o conjunto de protótipos de litologias (LithologyPrototype).
 *
 * @author Bruno Zanette
 */
public class LithologyDatabase {
    private List<Integer> lithologyList;
    private List<LithologyPrototype> lithologyPrototypes;
    
    /**
     * Feeds the database with the a facies-log correlation.
     * TO-DO: Maybe implement a binary search or a tree to find the wanted prototype faster.
     * @param faciesLogCorrelation a facies-log correlation.
     */
    public void feedDatabase(FaciesLogCorrelation faciesLogCorrelation)
    {
        for(LithologyPrototype prototype : lithologyPrototypes)
        {
            if(faciesLogCorrelation.getLithologyUID() == prototype.getLithologyUID())
            {
                prototype.feedPrototype();
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
    
    public void main()
    {
        
    }
    
}
