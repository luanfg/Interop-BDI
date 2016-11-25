/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.lithoprototype.model;

import interop.log.model.LogType;
import interop.lithoprototype.util.FaciesLogCorrelation;
import java.util.List;

/** Classe que contém uma tabela de covariância(LogCovarianceMatrix) e um vetor de médias(LogAverageVector) pertencente a mesma litologia.
 *
 * @author Bruno Zanette
 */
public class LithologyPrototype {
    private int lithologyUID;
    private LogAverageVector lithologyLogAverageVector;
    private LogCovarianceMatrix lithologyLogCovarianceMatrix;
    
    /**
     * 
     * @param lithoUID The lithology UID.
     */
    public void setLithologyUID(int lithoUID)
    {
        lithologyUID = lithoUID;
    }
    
    /**
     * 
     * @return The lithology UID. 
     */
    public int getLithologyUID()
    {
        return lithologyUID;
    }
    
    /**
     * 
     * @return A list of doubles representing the average values of the logs. 
     */
    public List<Double> getAverageVector()
    {
        return lithologyLogAverageVector.getAverageVector();
    }
    
    /**
     * 
     * @return A matrix(a list of lists) of doubles representing the covariance between logs.
     */
    public List<List<Double>> getCovarianceMatrix()
    {
        return lithologyLogCovarianceMatrix.getCovarianceMatrix();
    }

    /**     TO-DO:  this function will receive stratigraphic and log data and feed the lithology prototype.
     *      OBS:    there will be no set function to the covariance matrix and average vector.
     */
    public void feedPrototype(FaciesLogCorrelation faciesLogCorrelation)
    {
         lithologyLogAverageVector.feedAverageVector(faciesLogCorrelation);
         lithologyLogCovarianceMatrix.feedCovarianceMatrix(faciesLogCorrelation);
    }
}
