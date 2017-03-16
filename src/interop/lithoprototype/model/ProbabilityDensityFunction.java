/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.lithoprototype.model;

import Jama.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo
 */
public class ProbabilityDensityFunction {
    
    Matrix matrixCovariance;
    Matrix matrixAverageVector;
    Matrix matrixSample;
    List<Integer> invalidIndex;
    
    public ProbabilityDensityFunction(List<Double> sample, List<Double> averageVector, List<List<Double>> covarianceMatrix) {
        invalidIndex = getInvalidIndex(sample, averageVector);
        matrixCovariance = new Matrix(arrayListToMatrix_nxn(covarianceMatrix, invalidIndex));
        matrixAverageVector = new Matrix(arrayListToMatrix_nx1(averageVector,invalidIndex)); 
        matrixSample = new Matrix(arrayListToMatrix_nx1(sample,invalidIndex));
    }
    
    private void printDimentions(Matrix m){
        System.out.println( m.getColumnDimension() + " " + m.getRowDimension());
    }
    
    /**
     *
     * @return double representing the probability of that sample to be part of that lithology prototype
     */
    public double calculatePDF(){
        
        double detMatrix = matrixCovariance.det();
        int k = matrixCovariance.getColumnDimension();
        double pi = Math.PI;
        Matrix X_u = this.matrixSample.minus(this.matrixAverageVector);
        
        double fraction = 1.0 / Math.sqrt( Math.pow(2*pi, k) * detMatrix );
        
        Matrix inverse = matrixCovariance.inverse();
        Matrix transpose = X_u.transpose();
        
        Matrix result = transpose.times(inverse).times(X_u).times(-0.5);
                
        return fraction * Math.exp(result.det());       
    }
    
    /**
     *Prints the covariance matrix, the average vector and the sample vector.
     *
     */
    private void printAllMatrix(){
        printMatrix(this.matrixCovariance);
        printMatrix(this.matrixAverageVector);
        printMatrix(this.matrixSample);
    }
    
    /**
     * Transform an ArrayList<>() to double[][], so that way we can create a Matrix Object, also it excludes the invalid lines and columns
     * @param matrix
     * @param invalidIndex
     * @return cleanMatrix
     */
    private double[][] arrayListToMatrix_nxn(List<List<Double>> matrix, List<Integer> invalidIndex){
        List<List<Double>> newMatrix = new ArrayList<>();
        for(int i=0; i<matrix.size(); i++){
            List<Double> newLine = new ArrayList<>();
            for(int j=0; j<matrix.size(); j++){
                if(  !invalidIndex.contains(j)){
                    newLine.add(matrix.get(i).get(j));
                }
            }
            if(!invalidIndex.contains(i)){ 
                newMatrix.add(newLine);                
            }
        }
        
        double[][] cleanMatrix = new double[newMatrix.size()][newMatrix.size()];
        for(int i=0; i<newMatrix.size(); i++){
            for(int j=0; j<newMatrix.size(); j++){
                    cleanMatrix[i][j] = newMatrix.get(i).get(j).isNaN() ? 1.0 : newMatrix.get(i).get(j);
            }
        }
        return cleanMatrix;
    }
    
    private void printMatrix(Matrix m){
        System.out.println("Printing Matrix...");
        for(int i=0; i<m.getRowDimension(); i++){
            for(int j=0; j<m.getColumnDimension(); j++){
                System.out.print(m.get(i,j)+"\t");
            }
            System.out.println();
        }
        System.out.println("...End");
    }

    private int numberNullLines(List<List<Double>> matrix) {
        int nullLines = 0;
        for(List<Double> line:matrix){
            if(isNullLine(line))
                nullLines++;
        }
        return nullLines;
    }

    private boolean isNullLine(List<Double> line) {
        for(Double value:line){
            if(!value.isNaN())
                return false; 
        }
        return true;
    }

    private List<Integer> getInvalidIndex(List<Double> sample, List<Double> averageVector){
        List<Integer> list = new ArrayList<>();
        
        if(sample.size() != averageVector.size())
            System.out.println("PROBLEM WITH THE SAMPLE SIZE!");
        
        for(int i =0; i<sample.size(); i++){
            if(sample.get(i).isNaN() || averageVector.get(i).isNaN())
                list.add(i);
        }
        return list;
    }

    private double[][] arrayListToMatrix_nx1(List<Double> averageVector, List<Integer> invalidIndex) {
        List<Double> newList = new ArrayList<>();
        
        for(int i=0; i<averageVector.size(); i++){
            if(!invalidIndex.contains(i)){
                newList.add(averageVector.get(i));
            }
        }
        double[][] m = new double[newList.size()][1];
        int i=0;
        for(Double v:newList){
            m[i++][0] = v.isNaN() ? 1.0 : v;
        }
        return m;
    }
}