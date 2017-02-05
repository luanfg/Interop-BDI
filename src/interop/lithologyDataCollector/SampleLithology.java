/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.lithologyDataCollector;

import interop.lithoprototype.util.FaciesLogCorrelation;
import interop.log.model.LogValuePair;
import interop.log.model.ParsedLAS;
import interop.log.model.WellLog;
import interop.log.util.LASParser;
import interop.stratigraphic.model.DepositionalFacies;
import interop.stratigraphic.model.StratigraphicDescription;
import interop.stratigraphic.util.XMLReader;
import java.io.IOException;
import java.io.PrintWriter;
import static java.nio.file.Files.list;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author eduardo
 */
public class SampleLithology {
    
    private static List<String> logTypesWanted;
    private static String TAB = "\t"; 
    private static List<LithologyArchiveFormat> lithologies = new ArrayList<>();
    
    public static void main()
    {  
        logTypesWanted = new ArrayList<>();
        logTypesWanted.add("DEPT");
        logTypesWanted.add("DT");
        logTypesWanted.add("GR");
        logTypesWanted.add("ILD");
        logTypesWanted.add("NPHI");
        logTypesWanted.add("RHOB");
        logTypesWanted.add("DRHO");
        logTypesWanted.add("CALI");
        logTypesWanted.add("SP");
        logTypesWanted.add("SN");
        logTypesWanted.add("MSFL");
    
        String pathLogCP1847 = "teste6\\treino\\3-CP-1847-SE\\Perfis\\CP1847.las";
        List<String> pathDescriptionsCP1847 = new ArrayList<>(); 
        pathDescriptionsCP1847.add("teste6\\treino\\3-CP-1847-SE\\3-CP-1847-SE_(T1)_20151112101101_31.xml");
        pathDescriptionsCP1847.add("teste6\\treino\\3-CP-1847-SE\\3-CP-1847-SE_(T2)_20151112101100_29.xml");
        pathDescriptionsCP1847.add("teste6\\treino\\3-CP-1847-SE\\3-CP-1847-SE_(T3)_20151112101100_30.xml");
        pathDescriptionsCP1847.add("teste6\\treino\\3-CP-1847-SE\\3-CP-1847-SE_(T4)_20151112101102_32.xml");
        pathDescriptionsCP1847.add("teste6\\treino\\3-CP-1847-SE\\3-CP-1847-SE_(T5)_20151112101103_33.xml");
        SampleLithology.processWell(pathLogCP1847, pathDescriptionsCP1847);
    
        String pathLogSZ626 = "teste6\\treino\\3-SZ-0626-SE\\Perfis\\SZ626.las";
        List<String> pathDescriptionsSZ626 = new ArrayList<>(); 
        pathDescriptionsSZ626.add("teste6\\treino\\3-SZ-0626-SE\\3-SZ-0626-SE_(T1)_20151112101052_14.xml");
        pathDescriptionsSZ626.add("teste6\\treino\\3-SZ-0626-SE\\3-SZ-0626-SE_(T2)_20151112101053_15.xml");
        SampleLithology.processWell(pathLogSZ626, pathDescriptionsSZ626);
        
        String pathLogCP1914 = "teste6\\treino\\7-CP-1914-SE\\Perfis\\CP1914.las";
        List<String> pathDescriptionsCP1914 = new ArrayList<>(); 
        pathDescriptionsCP1914.add("teste6\\treino\\7-CP-1914-SE\\7-CP-1914-SE_(T1)_20151112101047_01.xml");
        pathDescriptionsCP1914.add("teste6\\treino\\7-CP-1914-SE\\7-CP-1914-SE_(T2)_20151112101047_02.xml");
        pathDescriptionsCP1914.add("teste6\\treino\\7-CP-1914-SE\\7-CP-1914-SE_(T3)_20151112101048_03.xml");
        SampleLithology.processWell(pathLogCP1914, pathDescriptionsCP1914);
        
        String pathLogFU128 = "teste6\\treino\\7-FU-0128-AL\\Perfis\\FU128.las";
        List<String> pathDescriptionsFU128 = new ArrayList<>(); 
        pathDescriptionsFU128.add("teste6\\treino\\7-FU-0128-AL\\7-FU-0128-AL_(T1)_20150804170346.xml");
        SampleLithology.processWell(pathLogFU128, pathDescriptionsFU128);
        
        String pathLogSZ508D = "teste6\\treino\\7-SZ-0508D-SE\\Perfis\\SZ508D.las";
        List<String> pathDescriptionsSZ508D = new ArrayList<>(); 
        pathDescriptionsSZ508D.add("teste6\\treino\\7-SZ-0508D-SE\\7-SZ-0508D-SE_(T1)_20151112101054_16.xml");
        pathDescriptionsSZ508D.add("teste6\\treino\\7-SZ-0508D-SE\\7-SZ-0508D-SE_(T2)_20151112101054_17.xml");
        pathDescriptionsSZ508D.add("teste6\\treino\\7-SZ-0508D-SE\\7-SZ-0508D-SE_(T3)_20151112101055_20.xml");
        pathDescriptionsSZ508D.add("teste6\\treino\\7-SZ-0508D-SE\\7-SZ-0508D-SE_(T4)_20151112101055_18.xml");
        SampleLithology.processWell(pathLogSZ508D, pathDescriptionsSZ508D);
        
        String pathLogGTP48 = "teste6\\treino\\7-FU-0128-AL\\Perfis\\FU128.las";
        List<String> pathDescriptionsGTP48 = new ArrayList<>(); 
        pathDescriptionsGTP48.add("teste6\\treino\\9-GTP-0048-SE\\15_descricoes (9-GTP-0048-SE).xml");
        SampleLithology.processWell(pathLogGTP48, pathDescriptionsGTP48);
        
        String pathLogSZ160 = "teste6\\treino\\9-SZ-0160-SE\\Perfis\\SZ160.las";
        List<String> pathDescriptionsSZ160 = new ArrayList<>(); 
        pathDescriptionsSZ160.add("teste6\\treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T24)_20151112101048_04.xml");
        pathDescriptionsSZ160.add("teste6\\treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T25)_20151112101048_05.xml");
        pathDescriptionsSZ160.add("teste6\\treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T26)_20151112101049_06.xml");
        pathDescriptionsSZ160.add("teste6\\treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T27)_20151112101050_07.xml");
        pathDescriptionsSZ160.add("teste6\\treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T28)_20151112101050_08.xml");
        pathDescriptionsSZ160.add("teste6\\treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T29)_20151112101051_09.xml");
        pathDescriptionsSZ160.add("teste6\\treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T30)_20151112101051_10.xml");
        pathDescriptionsSZ160.add("teste6\\treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T31)_20151112101051_11.xml");
        pathDescriptionsSZ160.add("teste6\\treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T32)_20151112101052_12.xml");
        pathDescriptionsSZ160.add("teste6\\treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T33)_20151112101052_13.xml");
        SampleLithology.processWell(pathLogSZ160, pathDescriptionsSZ160);
    
    
        for(LithologyArchiveFormat laf: lithologies){
            laf.saveToArchive();
        }
    
    }
    
    static void processWell(String pathLog, List<String> pathDescriptions){
        
        LASParser parser = new LASParser();    
        ParsedLAS parsed = parser.parseLAS(pathLog);
            
        //FOR EVERY SAMPLE IN THE LAS FILE
        for(int i=0; i<parsed.getLogsList().get(0).getLogValues().size() ; i++){
            
            //ORGANIZE SAMPLE IN ORDER OF logsTypeWanted
            List<String> OrganizedSample = new OrganizeSample(parsed, i).Organize();
            
            //SEARCH THE LITHOLOGY IN THE LIST OF XML, IF IT EXISTS
            DiscoverLithology discoverLithology = new DiscoverLithology(pathLog, i, pathDescriptions);
            int lithology = discoverLithology.discover();
            
            //AND GET THE PATH OF LAS AND XML TO IDENTIFY IN THE OUTPUT
            String las = discoverLithology.getLasPath();
            String xml = discoverLithology.getXmlPath();
            OrganizedSample.add(las);
            OrganizedSample.add(xml);
            
            
            //if(lithology >= -1){//WITH UNUSED SAMPLES
            if(lithology != -1){//WITHOUT UNUSED SAMPLES
                
                //SEE IF LITHOLOGY ALREADY EXISTS ON THE LIST lithologies AND GET ITS INDEX
                int lithologiesIndex = indexOfLithologyArchiveFormatList(lithologies, lithology);
                
                //IF EXISTS JUST ADD THE SAMPLE
                if(lithologiesIndex != -1){
                    lithologies.get(lithologiesIndex).add(OrganizedSample);
                }
                //OTHERWISE CREATE THE LITHOLOGY .txt AND ADD THE SAMPLE
                else{
                    LithologyArchiveFormat lit = new LithologyArchiveFormat(lithology, OrganizedSample);
                    lithologies.add(lit);
                }
            }
        }
        
    }
    
    static int indexOfLithologyArchiveFormatList(List<LithologyArchiveFormat> list, int UID){
        
        for(int i =0; i<list.size(); i++){
            if(list.get(i).lithologyUID == UID){
                return i;
            }
        }
        return -1;
    }

    
    static class DiscoverLithology{
        
        List<String> pathDescriptions = new ArrayList<>();
        float depthLAS;
        String lasFound;
        String xmlFound;
        
        public DiscoverLithology(String lasPath, int index, List<String> path){
            LASParser parser = new LASParser();    
            ParsedLAS parsed = parser.parseLAS(lasPath);
            this.pathDescriptions = path;
            this.depthLAS = parsed.getLogsList().get(0).getLogValues().get(index).getDepth();
            lasFound = lasPath; 
        }
        
        public String getLasPath(){
            return lasFound;
        }
        
        public String getXmlPath(){
            return xmlFound;
        }
        public int discover(){
                        
            //FOR EVERY XML FILE
            for(String path:pathDescriptions){
                List<StratigraphicDescription> listCore = XMLReader.readStratigraphicDescriptionXML(path);
                //FOR EVERY CORE CORE IN A XML FILE
                for(StratigraphicDescription core:listCore){
                    List<DepositionalFacies> faciesList = core.getFaciesList();
                    faciesList = mmTOm(faciesList);
                    //FOR EVERY FACIES
                    for(DepositionalFacies facie:faciesList){
                        //IF THE DEPTH OF SAMPLE IS IN BETWEEN THE FACIE
                        if(facie.getBottomMeasure()>= depthLAS && facie.getTopMeasure()<depthLAS){
                            //GET THE LITHOLOGY AND THE XML IT WAS FOUND
                            xmlFound = path;
                            return facie.getLithology().getId();
                        }
                    }
                }
            }
            return -1;
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
    }
          
    static class OrganizeSample{
        ParsedLAS parsed;
        int sampleIndex;
        
        public OrganizeSample(ParsedLAS psd, int index){
            this.parsed = psd;
            this.sampleIndex = index;
        }
         
        
        public List<String> Organize(){
            List<String> organizedSample = new ArrayList<>();
            
            for(String type:logTypesWanted){
                organizedSample.add( Float.toString(getSpecificSampleValue(type)));
            }
                       
            return organizedSample;
        }
        
        public float getSpecificSampleValue(String type){
            float nullValue = parsed.getLogsList().get(0).getNullValue();
            float value = nullValue;
            
            for(WellLog wl:parsed.getLogsList()){
                if(type.equalsIgnoreCase("DEPT"))
                    value = wl.getLogValues().get(sampleIndex).getDepth();
                else if(type.equalsIgnoreCase(wl.getLogType()) 
                        && wl.getLogValues().get(sampleIndex).getLogValue() != nullValue)//IN CASE THERE IS MORE THAN ONE LOG OF THE SAME MEASURE KIND
                    value = wl.getLogValues().get(sampleIndex).getLogValue();
                
            }
            return value; 
        }
        
    }
     
    static class LithologyArchiveFormat{
        int lithologyUID;
        List<List<String>> specificLog;
                
        public LithologyArchiveFormat(int UID, List<String> sample){
            this.specificLog = new ArrayList<>();
            specificLog.add(sample);
            lithologyUID = UID;
        }
        
        public void add(List<String> sample){
            specificLog.add(sample);
        }
    
        public void saveToArchive(){
            String currentFileString = "teste6\\treino_amostras2\\" + lithologyUID + ".txt";

            try{
                    PrintWriter writer = new PrintWriter(currentFileString, "UTF-8");
                    
                    for(String types: logTypesWanted){
                        writer.print(types);
                        writer.print(TAB);
                    }
                    writer.println();
                                      
                    for(List<String> sample:specificLog){
                        for(String data:sample){
                            writer.print(data);
                            writer.print(TAB);
                        }
                        writer.println();
                        //System.out.println(count++ + " saved samples.");
                    }
                            
                   writer.close();
            } catch (IOException e) {
                System.out.println("Cannot create file " +  currentFileString);
            }
        }
    }
}