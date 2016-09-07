/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.log.model;

import interop.util.Pair;

/**
 * A pair of depth and log values, represented by two floats.
 * @author Luan
 */
public class LogValuePair extends Pair<Float, Float>
{
    public LogValuePair(Float depth, Float logValue) {
        super(depth, logValue);
    }
    
    public void setDepth(float depth)
    {
        super.setX(depth);
    }
    
    public float getDepth()
    {
        return super.getX();
    }
    
    public void setLogValue(float logValue)
    {
        super.setY(logValue);
    }
    
    public float getLogValue()
    {
        return super.getY();
    }
    
    public void setDepthAndLogValue(float depth, float value)
    {
        super.setX(depth);
        super.setY(value);
    }
}
