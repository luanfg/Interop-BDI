/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.lithologyDataCollector;

import interop.lithoprototype.util.FaciesLogCorrelation;

import interop.stratigraphic.util.XMLReader;
import interop.stratigraphic.model.StratigraphicDescription;
import interop.stratigraphic.model.DepositionalFacies;

import interop.log.model.*;
import interop.log.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Bruno Zanette
 */
public class DataCollector {
    private static List<String> logTypesWanted;
    
    
    public static void main(String args[])
    {  
        logTypesWanted = new ArrayList<>();
        logTypesWanted.add("DT");
        logTypesWanted.add("GR");
        logTypesWanted.add("ILD");
        logTypesWanted.add("NPHI");
        logTypesWanted.add("RHOB");
        logTypesWanted.add("DRHO");
        logTypesWanted.add("CALI");
        logTypesWanted.add("SP");
        logTypesWanted.add("SN");
        
        
        String pathLogCP1847 = "\\teste6\\treino\\3-CP-1847-SE\\Perfis\\CP1847.las";
        List<String> pathDescriptionsCP1847 = new ArrayList<>(); 
        pathDescriptionsCP1847.add("\\teste6\\treino\\3-CP-1847-SE\\3-CP-1847-SE_(T1)_20151112101101_31.xml");
        pathDescriptionsCP1847.add("\\teste6\\treino\\3-CP-1847-SE\\3-CP-1847-SE_(T2)_20151112101100_29.xml");
        pathDescriptionsCP1847.add("\\teste6\\treino\\3-CP-1847-SE\\3-CP-1847-SE_(T3)_20151112101100_30.xml");
        pathDescriptionsCP1847.add("\\teste6\\treino\\3-CP-1847-SE\\3-CP-1847-SE_(T4)_20151112101102_32.xml");
        pathDescriptionsCP1847.add("\\teste6\\treino\\3-CP-1847-SE\\3-CP-1847-SE_(T5)_20151112101103_33");
        WellInfos wellCP1847 = new WellInfos(pathLogCP1847, pathDescriptionsCP1847);
        
        String pathLogSZ626 = "\\teste6\\treino\\3-SZ-0626-SE\\Perfis\\SZ626.las";
        List<String> pathDescriptionsSZ626 = new ArrayList<>(); 
        pathDescriptionsSZ626.add("\\teste6\\treino\\3-SZ-0626-SE\\3-SZ-0626-SE_(T1)_20151112101052_14.xml");
        pathDescriptionsSZ626.add("\\teste6\\treino\\3-SZ-0626-SE\\3-SZ-0626-SE_(T2)_20151112101053_15.xml");
        WellInfos wellSZ626 = new WellInfos(pathLogSZ626, pathDescriptionsSZ626);
        
        String pathLogCP1914 = "\\teste6\\treino\\7-CP-1914-SE\\Perfis\\CP1914.las";
        List<String> pathDescriptionsCP1914 = new ArrayList<>(); 
        pathDescriptionsCP1914.add("\\teste6\\treino\\7-CP-1914-SE\\7-CP-1914-SE_(T1)_20151112101047_01.xml");
        pathDescriptionsCP1914.add("\\teste6\\treino\\7-CP-1914-SE\\7-CP-1914-SE_(T2)_20151112101047_02.xml");
        pathDescriptionsCP1914.add("\\teste6\\treino\\7-CP-1914-SE\\7-CP-1914-SE_(T3)_20151112101048_03.xml");
        WellInfos wellCP1914 = new WellInfos(pathLogCP1914, pathDescriptionsCP1914);
        
        String pathLogFU128 = "\\teste6\\treino\\7-FU-0128-AL\\Perfis\\FU128.las";
        List<String> pathDescriptionsFU128 = new ArrayList<>(); 
        pathDescriptionsFU128.add("\\teste6\\treino\\7-FU-0128-AL\\7-FU-0128-AL_(T1)_20150804170346.xml");
        WellInfos wellFU128 = new WellInfos(pathLogFU128, pathDescriptionsFU128);
        
        String pathLogSZ508D = "\\teste6\\treino\\7-SZ-0508D-SE\\Perfis\\SZ508D.las";
        List<String> pathDescriptionsSZ508D = new ArrayList<>(); 
        pathDescriptionsSZ508D.add("\\teste6\\treino\\7-SZ-0508D-SE\\7-SZ-0508D-SE_(T1)_20151112101054_16.xml");
        pathDescriptionsSZ508D.add("\\teste6\\treino\\7-SZ-0508D-SE\\7-SZ-0508D-SE_(T2)_20151112101054_17.xml");
        pathDescriptionsSZ508D.add("\\teste6\\treino\\7-SZ-0508D-SE\\7-SZ-0508D-SE_(T3)_20151112101055_20.xml");
        pathDescriptionsSZ508D.add("\\teste6\\treino\\7-SZ-0508D-SE\\7-SZ-0508D-SE_(T4)_20151112101055_18.xml");
        WellInfos wellSZ508D = new WellInfos(pathLogSZ508D, pathDescriptionsSZ508D);
        
        String pathLogGTP48 = "\\teste6\\treino\\9-GTP-0048-SE\\Perfis\\FU128.las";
        List<String> pathDescriptionsGTP48 = new ArrayList<>(); 
        pathDescriptionsGTP48.add("\\teste6\\treino\\9-GTP-0048-SE\\15_descricoes (9-GTP-0048-SE).xml");
        WellInfos wellGTP48 = new WellInfos(pathLogGTP48, pathDescriptionsGTP48);
        
        String pathLogSZ160 = "\\teste6\\treino\\9-SZ-0160-SE\\Perfis\\SZ160.las";
        List<String> pathDescriptionsSZ160 = new ArrayList<>(); 
        pathDescriptionsSZ160.add("\\teste6\\treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T24)_20151112101048_04.xml");
        pathDescriptionsSZ160.add("\\teste6\\treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T25)_20151112101048_05.xml");
        pathDescriptionsSZ160.add("\\teste6\\treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T26)_20151112101049_06.xml");
        pathDescriptionsSZ160.add("\\teste6\\treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T27)_20151112101050_07.xml");
        pathDescriptionsSZ160.add("\\teste6\\treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T28)_20151112101050_08.xml");
        pathDescriptionsSZ160.add("\\teste6\\treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T29)_20151112101051_09.xml");
        pathDescriptionsSZ160.add("\\teste6\\treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T30)_20151112101051_10.xml");
        pathDescriptionsSZ160.add("\\teste6\\treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T31)_20151112101051_11.xml");
        pathDescriptionsSZ160.add("\\teste6\\treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T32)_20151112101052_12.xml");
        pathDescriptionsSZ160.add("\\teste6\\treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T33)_20151112101052_13.xml");
        WellInfos wellSZ160 = new WellInfos(pathLogSZ160, pathDescriptionsSZ160);
        
        
        
        
        //String currentFileString = "\\teste6\\treino_amostras\\" +
        //File file = new File("\\teste6\\treino_amostras\\");
    }

     /*
    *@param  stratigraphicDescriptions A list of stratigraphic descriptions that will be "unzipped"
    *@return A list of depositional facies containing every face of every stratigraphic description inputed.
    *@author Bruno Zanette
    */
    private static List<DepositionalFacies> getFaciesToFaciesList(List<StratigraphicDescription> stratigraphicDescriptions)
    {
        List<DepositionalFacies> listOfDepositionalFacies = new ArrayList<>();
        
        stratigraphicDescriptions.stream().forEach((sd) -> {
               listOfDepositionalFacies.addAll(sd.getFaciesList());
         });
        
        return listOfDepositionalFacies;
    }
    
    /*
    *   Written to increase the perfomance of the lithology matching.
    *   To be finished.
    *   @param wellFacies A list of facies of the same well. The user has to garantee that all facies belong to the same well.
    *   @return A list of fake facies, where the original consecutive facies of the same lithology are merged.
    *   @author Bruno Zanette
    */
    public static List<DepositionalFacies> mergeFaciesOfSameLithology(List<DepositionalFacies> wellFacies)
    {
        List<DepositionalFacies> listOfDepositionalFaciesMerged = new ArrayList<>();
        int lastElementIndex = listOfDepositionalFaciesMerged.size()-1;
        for(int i=lastElementIndex; i>-1; i--)
        {
            DepositionalFacies facies = listOfDepositionalFaciesMerged.get(i);
            int currentFaciesIndex = listOfDepositionalFaciesMerged.indexOf(facies);
            
            if(currentFaciesIndex != 0)
            {
                DepositionalFacies previousFacies = listOfDepositionalFaciesMerged.get(currentFaciesIndex-1);
                
                if((facies.getTopMeasure() == previousFacies.getBottomMeasure()) && (facies.getLithology().getId() == previousFacies.getLithology().getId()))
                {
                    previousFacies.setTopMeasure(previousFacies.getTopMeasure());
                    listOfDepositionalFaciesMerged.remove(currentFaciesIndex-1);
                }
            }
        }
        
        return listOfDepositionalFaciesMerged;
    }
    
    private static List<DepositionalFacies> mmTOm(List<DepositionalFacies> depositionalFacies)
    {
        for(DepositionalFacies dp:depositionalFacies)
        {
            dp.setBottomMeasure(dp.getBottomMeasure()/1000);
            dp.setTopMeasure(dp.getTopMeasure()/1000);
        }
        return depositionalFacies;
    }


    static class WellInfos
    {
        String logPath;
        List<String> descriptionPaths;
        ParsedLAS parsed;
        List<DepositionalFacies> wellFacies;
        List<FaciesLogCorrelation> wellFaciesLogCorrelations;
        List<String> logTypes;
        List<Integer> bijectionVector;
        
        
        public WellInfos(String lp, List<String> dp)
        {
            logPath = lp;
            descriptionPaths = dp;
    
            LASParser parser = new LASParser();
            parsed = parser.parseLAS(lp);
        
            wellFacies = new ArrayList<>();
        
            for(String dpath:dp)
            {
                List<StratigraphicDescription> xml = XMLReader.readStratigraphicDescriptionXML(dpath);
                wellFacies.addAll(DataCollector.getFaciesToFaciesList(xml));
            }
        
            logTypes = new ArrayList<>();
            for(WellLog wl:parsed.getLogsList())
            {
                logTypes.add(wl.getLogType());
            }
            
            bijectionVector = new ArrayList<>();
            for(String logp:logTypes)
            {
                bijectionVector.add(logTypesWanted.indexOf(logp));
            }
        }

    }
}


