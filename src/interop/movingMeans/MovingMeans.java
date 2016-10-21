/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.movingMeans;

import interop.log.model.LogValuePair;
import interop.log.util.LogValueList;

/**
 *
 * @author Cauã Antunes
 */
public class MovingMeans {
    
    // Input: list of pairs (depth, log value), window size
    // Output: list of pairs (depth, log value) after the filter is applied
    static public LogValueList apply(LogValueList logList, int windowSize){
        LogValueList result = new LogValueList();
        float parSum = 0;
        int inWindow;
        // Initialize the partial sum with the sum of the first (n/2)-1 entries
        for(inWindow = 0; inWindow < windowSize/2-1; inWindow++){
            parSum += logList.get(inWindow).getLogValue();
        }
        for(int i = 0; i < logList.size();i++){
            // For each iteration, adds to the partial sum the value from entry i+n/2,
            // where i is the index of the current entry (centroid)
            if(i+windowSize/2 < logList.size()){
                 parSum += logList.get(i+windowSize/2).getLogValue();
                 inWindow++;
            }
            // Subtracts the entry prior to i-n/2 from the sum
            if(i-1-windowSize/2 > 0){
                parSum -= logList.get(i-1-windowSize/2).getLogValue();
                inWindow--;
            }
            // Adds the mean value to the output list, paired with the depth of the current entry (i)
            LogValuePair pair = new LogValuePair(logList.get(i).getDepth(), parSum/inWindow);
            result.add(pair);
        }
        return result;
    }
}
