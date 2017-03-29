/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.framework;

import interop.beddingDiscriminator.BeddingDiscriminator;
import interop.lithologyDataCollector.SampleLithology;
import interop.lithoprototype.model.LithologyDatabase;
import interop.lithoprototype.model.LithologyPrototype;
import interop.lithoprototype.model.ProbabilityDensityFunction;
import interop.log.model.LogValuePair;
import interop.log.model.ParsedLAS;
import interop.log.util.LASParser;
import interop.log.util.LogValueList;
import java.util.ArrayList;
import java.util.List;

/**
 * This class uses the power of sample the las file, create lithologies prototypes 
 * and calculate the PDF for extrapolating the final results. 
 * @author Eduardo
 */
public class Extrapolate {
    
    LithologyDatabase database;
    List<LithologyPrototype> lithologiesPrototypes;
    ParsedLAS parsed;
    LogValueList changePoints;
    String lasPath = null;
    
    public Extrapolate(String lasPath){
        this.lasPath = lasPath;
        database = SampleLithology.main();
        lithologiesPrototypes = database.getLithologiesPrototypes();
        LASParser parser = new LASParser();
        parsed = parser.parseLAS(lasPath);
        changePoints = BeddingDiscriminator.findChangePoints(parsed.getLogsList().get(1).getLogValues());
    }
    
     
    /**
     *Main function, it is printing the extrapolated core in order,
     * ps: indexes are not real depth
     */
    public void extrapolateCore(){
        int firstIndex = 0;
        int secondIndex = this.getNextBreakPointIndex(firstIndex);
        while(secondIndex < changePoints.size()-1){
            System.out.println(getBestLitho_mostCommonMethod(firstIndex, secondIndex));
            //System.out.println(getBestLitho_higherMeanProbabilityMethod(firstIndex, secondIndex));
            
            firstIndex = secondIndex;
            secondIndex = getNextBreakPointIndex(secondIndex);
        }
    }
    
    private int getNextBreakPointIndex(int recent){
        for(int i=recent+1; i<changePoints.size(); i++){
            if(changePoints.get(i).getLogValue() > 0.0){
                return i;
            }
        }
        return changePoints.size()-1;
    }
     
    //primeiro métodos para decidir qual a litologia resultante
    private int getBestLitho_mostCommonMethod(int firstIndex, int secondIndex){
        List<Integer> bestList = new ArrayList<>();
        
        for(int i=firstIndex; i< secondIndex; i++){
            List<Double> sample = SampleLithology.getSample(i, parsed);
            int bestLitho = 0; double bestLithoValue = 0.0;
            
            for(LithologyPrototype lpt:lithologiesPrototypes){
                List<Double> averageVector = lpt.getAverageVector();
                List<List<Double>> covarianceMatrix = lpt.getCovarianceMatrix();
                ProbabilityDensityFunction pdf = new ProbabilityDensityFunction(sample, averageVector, covarianceMatrix);
                double pdfValue = pdf.calculatePDF();
                
                if(pdfValue > bestLithoValue && !Double.isInfinite(pdfValue)){
                    bestLitho = lpt.getLithologyUID();
                    bestLithoValue = pdfValue;
                }
                if(bestLitho != 0)
                    bestList.add(bestLitho);
            }
        }
        return mostCommon(bestList);
    }

    private int mostCommon(List<Integer> bestList) {
        List<List<Integer>> stack = new ArrayList<>();
        for(int number:bestList){
            boolean added = false;
            for(List<Integer> list:stack){
                if(list.get(0) == number){
                    list.add(number);
                    added = true;
                }
            }
            if(!added){
                List<Integer> newList = new ArrayList<>();
                newList.add(number);
                stack.add(newList);
            }
        }
        if(stack.isEmpty()){
            System.out.println("Not even one lithology looks right to this samples...");
            return 0;
        }
        int indexResult = 0;
        int biggestSize = stack.get(0).size();
        
        for(int i =1; i< stack.size(); i++){
            if(stack.get(i).size() > biggestSize){
                biggestSize = stack.get(i).size();
                indexResult = i;
            }
        }
        
        return stack.get(indexResult).get(0);
    }

    //segundo metido para resolver...
    private int getBestLitho_higherMeanProbabilityMethod(int firstIndex, int secondIndex){
        
        //ARRAY OF BEST LITHOLOGIES
        List<Integer> bestList = new ArrayList<>();
        //STACK OF VALUES FOR EACH LITHOLOGY
        List<List<Double>> stackProbability = new ArrayList<>();  
        
        for(int i=firstIndex; i< secondIndex; i++){
            List<Double> sample = SampleLithology.getSample(i, parsed);
                        
            for(LithologyPrototype lpt:lithologiesPrototypes){
                List<Double> averageVector = lpt.getAverageVector();
                List<List<Double>> covarianceMatrix = lpt.getCovarianceMatrix();
                ProbabilityDensityFunction pdf = new ProbabilityDensityFunction(sample, averageVector, covarianceMatrix);
                double pdfValue = pdf.calculatePDF();
                
                if(lpt.getLithologyUID() != 0)
                    bestList = addLithology(bestList, lpt.getLithologyUID());
                    int indexOfLitho = bestList.indexOf(lpt.getLithologyUID());
                    stackProbability = addProbabilityToStack(stackProbability, pdfValue, indexOfLitho);
            }
        }
        return higherMeanProbability(bestList, stackProbability);
    }
    
    private List<Integer> addLithology(List<Integer> bestList, int lithologyUID) {
        boolean foundLitho = false;
        for(int lit:bestList){
            if(lit==lithologyUID){
                foundLitho=true;
            }
        }
        if(!foundLitho)
            bestList.add(lithologyUID);
        return bestList;
    }

    private List<List<Double>> addProbabilityToStack(List<List<Double>> stackProbability, double pdfValue, int indexOfLitho) {
        if(stackProbability.size()<=indexOfLitho){
            List<Double> newList = new ArrayList<>();
            newList.add(pdfValue);
            stackProbability.add(newList);
        }
        else{
            stackProbability.get(indexOfLitho).add(pdfValue);
        }
        
        return stackProbability;
    }

    private int higherMeanProbability(List<Integer> bestList, List<List<Double>> stackProbability) {
        List<Double> means = new ArrayList<>();
        for(List<Double> listValues:stackProbability){
            means.add(mean(listValues));
        }
        return bestList.get(mostProbable(means));
    }

    private Double mean(List<Double> listValues) {
        int count =0;
        double sum = 0.0;
        for(Double value:listValues){
           count++;
           sum+=value;
        }
        return sum/count;
    }

    private int mostProbable(List<Double> means) {
        double result = 0.0;
        for(double value:means){
            if(value>result){
                result = value;
            }
        }
        return means.indexOf(result);
    }
}