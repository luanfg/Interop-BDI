/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.lithoprototype.model;

import java.util.List;
import interop.lithoprototype.util.FaciesLogCorrelation;


/** Contém uma tabela de covariância 
 *  
 * @author Bruno Zanette
 */
public class LogCovarianceMatrix {
    private List<List<Double>> covarianceMatrix;
    private List<List<Integer>> numberOfSamples;
    
    /**
     * 
     * @return A matrix(a list of lists) of doubles representing the covariance between logs.
     */
    public List<List<Double>> getCovarianceMatrix()
    {
        return covarianceMatrix;
    }
    
    public void feedCovarianceMatrix(FaciesLogCorrelation faciesLogCorrelation)
    {
    
    }
}
