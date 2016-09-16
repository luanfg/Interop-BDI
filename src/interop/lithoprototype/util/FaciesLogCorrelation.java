/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.lithoprototype.util;

import interop.stratigraphic.model.DepositionalFacies;
import interop.log.model.WellLog;
import interop.log.util.LogValueList;

import java.util.List;
import java.util.ArrayList;


/**
 *
 * @author Bruno Zanette
 */
public class FaciesLogCorrelation {
    DepositionalFacies facies;
    List<WellLog> wellLogsSlice;
    
    public void FaciesLogCorrelation(DepositionalFacies depositionalFacies, List<WellLog> wellLogs)
    {
        setDepositionalFacies(depositionalFacies);
        
        
        
    }
    
    /**
     * Set the depositional facies. 
     * OBS: It's a private function to make sure that the depositional facies and the logs will be representing the same object.
     * @param depositionalFacies a depositional facies.  
     */
    private void setDepositionalFacies(DepositionalFacies depositionalFacies)
    {
        facies = depositionalFacies;
    }
    
    /**
     * 
     * @return the depositional facies. 
     */
    public DepositionalFacies getDepositionalFacies()
    {
        return facies;
    }
    
    /**
     * 
     * @return the lithology UID.
     */
    public int getLithologyUID()
    {
        return Integer.parseInt( facies.getLithology().getValue() );
    }
    
    public float getBottomMeasure()
    {
        return facies.getBottomMeasure();
    }
    
    public float getTopMeasure()
    {
        return facies.getTopMeasure();
    }
    
    /**
     *  Slice the list of WellLog maintaining the data of the depths that corresponds to the specified depositional facies. 
     * @param wellLogs the complete wellLog received as parameter at the constructor.
     * @return the list of WellLog containing just the data of the depths that corresponds to the specified depositional facies.
     */
    private List<WellLog> sliceWellLog(List<WellLog> wellLogs)
    {
        List<WellLog> slicedWellLogs = new ArrayList();
     
        for (WellLog wellLog : wellLogs)
        {
            WellLog slicedWellLog = new WellLog();
            
            slicedWellLog.setLogType( wellLog.getLogType() );
            slicedWellLog.setNullValue( wellLog.getNullValue() );
            
            LogValueList originalLogValueList = wellLog.getLogValues();
            int i = 0, listSize = wellLogs.size();
            float currentDepth = originalLogValueList.get(i).getDepth(); // get initial depth
            while(currentDepth < facies.getTopMeasure())
            {
                currentDepth = originalLogValueList.get(i).getDepth();
                i++;
            }
            
            
            LogValueList slicedLogValueList = new LogValueList();
            while( (currentDepth >= facies.getBottomMeasure()) && (i < listSize) )
            {
                currentDepth = originalLogValueList.get(i).getDepth();
                
                slicedLogValueList.add( originalLogValueList.get(i) );
            
                i++;
            }
            
            slicedWellLog.setLogValues(slicedLogValueList);
        }
        
        return slicedWellLogs;
    }
}
