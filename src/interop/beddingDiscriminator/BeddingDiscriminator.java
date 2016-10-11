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
    
    // Entrada: lista de pares (profundidade, valor do log)
    // Saída: lista de pares (profundidade, x),
    //        onde x é 0 se não é acusada uma quebra
    //        e um valor próximo de 1 em caso contrário
    static public LogValueList findChangePoints(LogValueList logList){
        LogValueList result = new LogValueList();
        
        // Calcula distância entre duas entradas no log
        float step = logList.get(1).getDepth()-logList.get(0).getDepth();
        // Aplica médias móveis com janelas de tamanho adequado
        LogValueList shortWindow = MovingMeans.apply(logList, (int) (1.0/step));
        LogValueList longWindow = MovingMeans.apply(logList, (int) (10.0/step));
        
        LogValueList under;
        LogValueList over;
        int i = 0;
        
        // Percorre os dois logs filtrados procurando a primeira diferença
        for(; i < logList.size() && shortWindow.get(i).getLogValue() == longWindow.get(i).getLogValue(); i++){
            LogValuePair pair = new LogValuePair(logList.get(i).getDepth(), 0f);
            result.add(pair);
        }
        
        // Armazena o menor em under e o maior em over
        if(shortWindow.get(i).getLogValue() < longWindow.get(i).getLogValue()){
            under = shortWindow;
            over = longWindow;
        } else {
            under = longWindow;
            over = shortWindow;
        }
        
        // Percorre resto do log
        for(; i < logList.size(); i++){
            // Sempre que o valor em under for maior do que aquele em over, inverte e acusa quebra
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
