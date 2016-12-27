/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.log.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a parsed LAS File, containing the most common attributes, 
 * as well as a list of the logs that are stored in the represented LAS File.
 * @author Luan
 */
public class ParsedLAS 
{
    private String version;
    private float startDepth;
    private String startDepthMeasureUnit;
    private float stopDepth;
    private String stopDepthMeasureUnit;
    private float stepValue;
    private String stepValueMeasureUnit;
    private float nullValue;
    private String company;
    private String wellName;
    
    private List<WellLog> logsList;

    /**
     * @return the logsList
     */
    public List<WellLog> getLogsList() 
    {
        if(logsList == null)
            logsList = new ArrayList<>();
        
        return logsList;
    }

    /**
     * @param logsList the logsList to set
     */
    public void setLogsList(List<WellLog> logsList) {
        this.logsList = logsList;
    }

    /**
     * @return the startDepth
     */
    public float getStartDepth() {
        return startDepth;
    }

    /**
     * @param startDepth the startDepth to set
     */
    public void setStartDepth(float startDepth) {
        this.startDepth = startDepth;
    }

    /**
     * @return the stopDepth
     */
    public float getStopDepth() {
        return stopDepth;
    }

    /**
     * @param stopDepth the stopDepth to set
     */
    public void setStopDepth(float stopDepth) {
        this.stopDepth = stopDepth;
    }

    /**
     * @return the stepValue
     */
    public float getStepValue() {
        return stepValue;
    }

    /**
     * @param stepValue the stepValue to set
     */
    public void setStepValue(float stepValue) {
        this.stepValue = stepValue;
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

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the wellName
     */
    public String getWellName() {
        return wellName;
    }

    /**
     * @param wellName the wellName to set
     */
    public void setWellName(String wellName) {
        this.wellName = wellName;
    }

    /**
     * @return the startDepthMeasureUnit
     */
    public String getStartDepthMeasureUnit() {
        return startDepthMeasureUnit;
    }

    /**
     * @param startDepthMeasureUnit the startDepthMeasureUnit to set
     */
    public void setStartDepthMeasureUnit(String startDepthMeasureUnit) {
        this.startDepthMeasureUnit = startDepthMeasureUnit;
    }

    /**
     * @return the stopDepthMeasureUnit
     */
    public String getStopDepthMeasureUnit() {
        return stopDepthMeasureUnit;
    }

    /**
     * @param stopDepthMeasureUnit the stopDepthMeasureUnit to set
     */
    public void setStopDepthMeasureUnit(String stopDepthMeasureUnit) {
        this.stopDepthMeasureUnit = stopDepthMeasureUnit;
    }

    /**
     * @return the stepValueMeasureUnit
     */
    public String getStepValueMeasureUnit() {
        return stepValueMeasureUnit;
    }

    /**
     * @param stepValueMeasureUnit the stepValueMeasureUnit to set
     */
    public void setStepValueMeasureUnit(String stepValueMeasureUnit) {
        this.stepValueMeasureUnit = stepValueMeasureUnit;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    public double getMaxValue(int log) {
        double wellSize = this.getStopDepth() - this.getStartDepth();
        double maxValue=-99999; //LAS file can have values bigger than 99999?
        //double minValue=99999;
        for(int p=0; p<(int)wellSize*5; p++ ){//for the beginning to an end of a log
            double q = this.getLogsList().get(log).getLogValues().get(p).getLogValue();
            if(q>maxValue && q!= this.getNullValue()){
                maxValue=q;
            }
        }
        return maxValue;
    }

    public double getMinValue(int log) {
        double wellSize = this.getStopDepth() - this.getStartDepth();
        //double maxValue=-99999; //LAS file can have values bigger than 99999?
        double minValue=99999;
        for(int p=0; p<(int)wellSize*5; p++ ){//for the beginning to an end of a log
            double q = this.getLogsList().get(log).getLogValues().get(p).getLogValue();
            if(q<minValue && q!=this.getNullValue()){
                minValue=q;
            }
        }
        return minValue;
    }
}
