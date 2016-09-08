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
 * @author UFRGS
 */
public class MovingMeans {
    
    static public LogValueList apply(LogValueList logList, int windowSize){
        LogValueList result = new LogValueList();
        float parSum = 0;
        int inWindow;
        for(inWindow = 0; inWindow < windowSize/2-1; inWindow++){
            parSum += logList.get(inWindow).getLogValue();
        }
        for(int i = 0; i < logList.size();i++){
            if(i+windowSize/2 < logList.size()){
                 parSum += logList.get(i+windowSize/2).getLogValue();
                 inWindow++;
            }
            if(i-1-windowSize/2 > 0){
                parSum -= logList.get(i-1-windowSize/2).getLogValue();
                inWindow--;
            }
            System.out.println(inWindow + " " + (i+windowSize/2));
            LogValuePair pair = new LogValuePair(logList.get(i).getDepth(), parSum/inWindow);
            result.add(pair);
        }
        return result;
    }
}
