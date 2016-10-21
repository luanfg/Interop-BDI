/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.beddingDiscriminator;

import interop.log.model.LogValuePair;
import interop.log.util.LogValueList;
import interop.movingMeans.MovingMeans;

/**
 *
 * @author Cauã Antunes
 */
public class BeddingDiscriminator {
    
    // Input: list of pairs (depth, log value)
    // Output: list of pairs (depth, x),
    //        where x equals 0 if no break is found at the appointed depth
    //        or a value near to 1 otherwise
    static public LogValueList findChangePoints(LogValueList logList){
        LogValueList result = new LogValueList();
        
        // Computes the distance between two log entries
        float step = logList.get(1).getDepth()-logList.get(0).getDepth();
        // Apply the moving means with the adequate windows
        LogValueList shortWindow = MovingMeans.apply(logList, (int) (1.0/step));
        LogValueList longWindow = MovingMeans.apply(logList, (int) (10.0/step));
        
        LogValueList under;
        LogValueList over;
        int i = 0;
        
        // Iterates over both lists until a difference is found
        for(; i < logList.size() && shortWindow.get(i).getLogValue() == longWindow.get(i).getLogValue(); i++){
            LogValuePair pair = new LogValuePair(logList.get(i).getDepth(), 0f);
            result.add(pair);
        }
        
        // Stores the list with the lower value in 'under', and the one with the higher in 'over'
        if(shortWindow.get(i).getLogValue() < longWindow.get(i).getLogValue()){
            under = shortWindow;
            over = longWindow;
        } else {
            under = longWindow;
            over = shortWindow;
        }
        
        // Iterates over the remaining entries
        for(; i < logList.size(); i++){
            // Whenever the log value in 'under' is greater than the one in 'over',
            // swaps 'over' and 'under' and signals the break
            if(over.get(i).getLogValue() < under.get(i).getLogValue()){
                float deltaThen = over.get(i-1).getLogValue() - under.get(i-1).getLogValue();
                float deltaNow = under.get(i-1).getLogValue() - over.get(i-1).getLogValue();
                LogValuePair pair = new LogValuePair(logList.get(i).getDepth(), 1 + deltaThen/(deltaThen+deltaNow));
                result.add(pair);
                
                LogValueList aux = over;
                over = under;
                under = aux;
            } else {
                LogValuePair pair = new LogValuePair(logList.get(i).getDepth(), 0f);
                result.add(pair);
            }
        }
        
        return result;
    }
}
