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
 * @author UFRGS
 */
public class BeddingDiscriminator {
    
    static public LogValueList findChangePoints(LogValueList logList){
        LogValueList result = new LogValueList();
        
        float step = logList.get(1).getDepth()-logList.get(0).getDepth();
        LogValueList shortWindow = MovingMeans.apply(logList, (int) (1.0/step));
        LogValueList longWindow = MovingMeans.apply(logList, (int) (10.0/step));
        
        LogValueList under;
        LogValueList over;
        int i = 0;
        
        for(; i < logList.size() && shortWindow.get(i).getLogValue() == longWindow.get(i).getLogValue(); i++){
            LogValuePair pair = new LogValuePair(logList.get(i).getDepth(), 0f);
            result.add(pair);
        }
        if(shortWindow.get(i).getLogValue() < longWindow.get(i).getLogValue()){
            under = shortWindow;
            over = longWindow;
        } else {
            under = longWindow;
            over = shortWindow;
        }
        
        for(; i < logList.size(); i++){
            if(over.get(i).getLogValue() < under.get(i).getLogValue()){
                float deltaThen = over.get(i-1).getLogValue() - under.get(i-1).getLogValue();
                float deltaNow = under.get(i-1).getLogValue() - over.get(i-1).getLogValue();
                LogValuePair pair = new LogValuePair(logList.get(i).getDepth(), 1 + deltaThen/(deltaThen+deltaNow));
                result.add(pair);
                
                LogValueList aux = over;
                over = under;
                under = over;
            } else {
                LogValuePair pair = new LogValuePair(logList.get(i).getDepth(), 0f);
                result.add(pair);
            }
        }
        
        return result;
    }
}
