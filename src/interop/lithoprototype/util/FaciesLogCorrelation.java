/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.lithoprototype.util;

import interop.stratigraphic.model.DepositionalFacies;
import interop.log.model.*;
import interop.log.util.*;

import java.util.List;
import java.util.ArrayList;


/**
 *
 * @author Bruno Zanette
 */
public class FaciesLogCorrelation {
    DepositionalFacies facies;
    List<WellLog> wellLogsSlice;
    
    /*
    
    */
    public FaciesLogCorrelation(DepositionalFacies depositionalFacies, ParsedLAS parsed)
    {
        setDepositionalFacies(depositionalFacies);
        wellLogsSlice = this.sliceWellLog(parsed);
    }
    
    public static List<FaciesLogCorrelation> logAndDescriptionsToListOfFaciesLogCorrelations(List<DepositionalFacies> depositionalFacies, ParsedLAS parsed)
    {
        List<FaciesLogCorrelation> listOfFaciesLogCorrelation = new ArrayList<>();
        
        for(DepositionalFacies df:depositionalFacies)
        {
            FaciesLogCorrelation flc = new FaciesLogCorrelation(df, parsed);
            listOfFaciesLogCorrelation.add(flc);
        }
        
        return listOfFaciesLogCorrelation;
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
        return facies.getLithology().getId();
    }
    
    public float getBottomMeasure()
    {
        return facies.getBottomMeasure();
    }
    
    public float getTopMeasure()
    {
        return facies.getTopMeasure();
    }
    
    public int getNumberOfSamples()
    {
        WellLog anyWellLog = wellLogsSlice.get(0);
        LogValueList anyLogValueList = anyWellLog.getLogValues();
        return anyLogValueList.size();
    }
    
    /**
     *  Slice the list of WellLog maintaining the data of the depths that corresponds to the specified depositional facies. 
     * @param wellLogs the complete wellLog received as parameter at the constructor.
     * @return the list of WellLog containing just the data of the depths that corresponds to the specified depositional facies.
     */
    private List<WellLog> sliceWellLog(ParsedLAS parsed)
    {
        //System.out.println("Number of log samples: " + wellLogs.get(0).getLogValues().size());
        List<WellLog> slicedWellLogs = new ArrayList<>();
        //System.out.println("**In sliceWellLog of FaciesLogCorrelation**\n Well Logs Size: " + wellLogs.size() + "\n***********\n");
        
        
        float faciesTopMeasure = facies.getTopMeasure();
        float faciesBottomMeasure = facies.getBottomMeasure();
        float logTopMeasure = parsed.getStartDepth();
        float logBottomMeasure = parsed.getStopDepth();
        float logStep = parsed.getStepValue();
        int startIndex=0;
        int stopIndex=0;
        
        //System.out.println("faciesTopMeasure: " + faciesTopMeasure + "\nfaciesBottomMeasure: " + faciesBottomMeasure + "\nlogTopMeasure: " + logTopMeasure + "\nlogBottomMeasure: " + logBottomMeasure + "\nlogStep: " + logStep);
        if(faciesTopMeasure >= logTopMeasure)
        {
            float diffTop = faciesTopMeasure - logTopMeasure;
            int diffTopSteps = (int)(diffTop/logStep);
       
            /*
            System.out.println("diffTop: " + diffTop + "\ndiffTopSteps: " + diffTopSteps);
            System.out.println((diffTopSteps-(int)diffTopSteps));
            System.out.println("Calculated Log Top Measure: " + parsed.getLogsList().get(0).getLogValues().get(diffTopSteps).getDepth());
            System.out.println();
            */
            startIndex = diffTopSteps;
            
            if(faciesBottomMeasure > logBottomMeasure)
            {
                float diff = faciesBottomMeasure - faciesTopMeasure;
                int diffBottomSteps = diffTopSteps + (int)(diff/logStep);
                
                stopIndex = diffBottomSteps;
            }
            else
            {
                stopIndex = parsed.getLogsList().get(0).getLogValues().size()-1;
            }
        }
        
        List<WellLog> wellLogs = parsed.getLogsList();
        
        for(WellLog wl:wellLogs)
        {
            WellLog slicedWellLog = new WellLog();
            slicedWellLog.setLogType(wl.getLogType());
            slicedWellLog.setLogMeasureUnit(wl.getLogMeasureUnit());
            slicedWellLog.setLogDescription(wl.getLogDescription());
            slicedWellLog.setNullValue(wl.getNullValue());
            
            LogValueList slicedLVL = new LogValueList();
            slicedLVL.addAll( wl.getLogValues().subList(startIndex, stopIndex)      );       
          
            slicedWellLog.setLogValues(slicedLVL);
            slicedWellLogs.add(slicedWellLog);
        }
     
        return slicedWellLogs;
    }
    
    public void print()
    {
        System.out.println("***Facies Log Correlation***");
        
        System.out.println("Top Measure: " + this.getTopMeasure());
        System.out.println("Bottom Measure: " + this.getBottomMeasure());
        System.out.println("Lithology UID; " + this.getLithologyUID());
        System.out.println("Number of Samples: " + this.getNumberOfSamples());
        
        /*
        System.out.println("SAMPLES: \nDEPTH\t" + wellLogsSlice.get(0).getLogType());
        for(LogValuePair lvp:wellLogsSlice.get(0).getLogValues())
        {
            System.out.println(lvp.getDepth() + "\t" + lvp.getLogValue());
        }
        */
        
        System.out.println("****************************\n");
    }
}
