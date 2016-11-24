/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.log.model;

import interop.log.util.LogValueList;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Well Log, containing its type mnemonic, descriptions, measure unit, null value and the list of depth / log values.
 * @author Luan
 */
public class WellLog 
{
    private String logType;
    private String logDescription;
    private String logMeasureUnit;
    private float nullValue;
    private LogValueList logValues;

    public WellLog()
    {
        this.logValues = new LogValueList();
    }
    
    /**
     * @return The log mnemonic.
     */
    public String getLogType() {
        return logType;
    }

    /**
     * @param logType The log mnemonic.
     */
    public void setLogType(String logType) {
        this.logType = logType;
    }

    /**
     * @return The description of the log.
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

    /**
     * @return A list of pairs of depth/log value.
     */
    public LogValueList getLogValues() {
        return logValues;
    }

    /**
     * @param logValues Set the entire list of pairs of depth/log value;
     */
    public void setLogValues(LogValueList logValues) {
        this.logValues = logValues;

    }
    
    /**
     * Convenience method to add to the list of log value pairs a single pair.
     * @param logValuePair A pair of depth/log value.
     */
    public void addLogValuePair(LogValuePair logValuePair)
    {
        this.logValues.add(logValuePair);
    }

    /**
     * @return the nullValue
     */
    public float getNullValue() {
        return nullValue;
    }

    /**
     * @param nullValue the nullValue to set
     */
    public void setNullValue(float nullValue) {
        this.nullValue = nullValue;
    }
    
    
}
