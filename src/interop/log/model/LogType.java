/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.log.model;

/**
 *
 * @author Bruno Zanette
 */
public class LogType {
    private String logType;
    private String logDescription;
    private String logMeasureUnit;

    
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

}
