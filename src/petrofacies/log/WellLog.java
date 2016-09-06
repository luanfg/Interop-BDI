/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petrofacies.log;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luan
 */
public class WellLog 
{
    private String logType;
    private String logDescription;
    private String logMeasureUnit;
    private List<Float> depthValues;
    private List<Float> logValues;

    /**
     * @return the logName
     */
    public String getLogType() {
        return logType;
    }

    /**
     * @param logName the logName to set
     */
    public void setLogType(String logName) {
        this.logType = logName;
    }

    /**
     * @return the logDescription
     */
    public String getLogDescription() {
        return logDescription;
    }

    /**
     * @param logDescription the logDescription to set
     */
    public void setLogDescription(String logDescription) {
        this.logDescription = logDescription;
    }

    /**
     * @return the depthValues
     */
    public List<Float> getDepthValues() 
    {
        if(depthValues == null)
            depthValues = new ArrayList<>();
        return depthValues;
    }

    /**
     * @param depthValues the depthValues to set
     */
    public void setDepthValues(List<Float> depthValues) {
        this.depthValues = depthValues;
    }

    /**
     * @return the logValues
     */
    public List<Float> getLogValues() 
    {
        if(logValues == null)
            logValues = new ArrayList<>();
        return logValues;
    }

    /**
     * @param logValues the logValues to set
     */
    public void setLogValues(List<Float> logValues) {
        this.logValues = logValues;
    }

    /**
     * @return the logMeasureUnit
     */
    public String getLogMeasureUnit() {
        return logMeasureUnit;
    }

    /**
     * @param logMeasureUnit the logMeasureUnit to set
     */
    public void setLogMeasureUnit(String logMeasureUnit) {
        this.logMeasureUnit = logMeasureUnit;
    }
    
    
}
