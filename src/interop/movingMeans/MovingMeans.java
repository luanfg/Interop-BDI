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
    
    // Entradas: lista de pares (profundidade, valor do log), tamanho da janela
    // Saída: lista de pares (profundidade, valor do log) filtrada pelo algoritmo
    static public LogValueList apply(LogValueList logList, int windowSize){
        LogValueList result = new LogValueList();
        float parSum = 0;
        int inWindow;
        // Inicializa a soma parcial com o somatório dos primeiros (n/2)-1 elementos
        for(inWindow = 0; inWindow < windowSize/2-1; inWindow++){
            parSum += logList.get(inWindow).getLogValue();
        }
        for(int i = 0; i < logList.size();i++){
            // Para cada iteração, adiciona à soma parcial o valor do elemento na posição i+n/2
            // onde i é a posição do elemento atual (centróide)
            if(i+windowSize/2 < logList.size()){
                 parSum += logList.get(i+windowSize/2).getLogValue();
                 inWindow++;
            }
            // Subtrai da soma o elemento imediatamente anterior à posição i-n/2
            if(i-1-windowSize/2 > 0){
                parSum -= logList.get(i-1-windowSize/2).getLogValue();
                inWindow--;
            }
            System.out.println(inWindow + " " + (i+windowSize/2));
            // Adiciona à lista de saída o valor da média associado à profundidade do elemento atual (i)
            LogValuePair pair = new LogValuePair(logList.get(i).getDepth(), parSum/inWindow);
            result.add(pair);
        }
        return result;
    }
}
