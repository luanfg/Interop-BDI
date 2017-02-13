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
        
     /* //TESTE 1 TREINO
        String pathLogNA04 = "Teste 1\\Treino\\NA04.las";
        List<String> pathDescriptionsNA04 = new ArrayList<>(); 
        pathDescriptionsNA04.add("Teste 1\\Treino\\3-NA-04-RJS_(T1)_20150504162349_05.xml");
        pathDescriptionsNA04.add("Teste 1\\Treino\\3-NA-04-RJS_(T2)_20150504162352_07.xml");
        pathDescriptionsNA04.add("Teste 1\\Treino\\3-NA-04-RJS_(T4)_20150504162354_09.xml");
        pathDescriptionsNA04.add("Teste 1\\Treino\\3-NA-04-RJS_(T5)_20150504162356_10.xml");
        SampleLithology.processWell(pathLogNA04, pathDescriptionsNA04);
    */
      
    /*  //TESTE 1 VALIDAÇÃO
        String pathLogNA04 = "Teste 1\\Validacao\\NA04.las";
        List<String> pathDescriptionsNA04 = new ArrayList<>(); 
        pathDescriptionsNA04.add("Teste 1\\Validacao\\3-NA-04-RJS_(T3)_20150504162352_08.xml");
        SampleLithology.processWell(pathLogNA04, pathDescriptionsNA04);
    */
    
    /*  //TESTE 2 TREINO
        String pathLogRJS019 = "Teste 2\\Treino\\1-RJ-19-RJS\\RJS019.las";
        List<String> RJS019 = new ArrayList<>(); 
        RJS019.add("Teste 2\\Treino\\1-RJ-19-RJS\\1-RJ-19-RJS_(T2)_20150504162350_06.xml");
        SampleLithology.processWell(pathLogRJS019, RJS019);
        
        String pathLogNA01A = "Teste 2\\Treino\\3-NA-1A-RJS\\NA01A.las";
        List<String> NA01A = new ArrayList<>(); 
        NA01A.add("Teste 2\\Treino\\3-NA-1A-RJS\\3-NA-1A-RJS_(T3)_20150504162348_04.xml");
        SampleLithology.processWell(pathLogNA01A, NA01A);
        
        String pathLogNA02 = "Teste 2\\Treino\\3-NA-02-RJS\\NA02.las";
        List<String> NA02 = new ArrayList<>(); 
        NA02.add("Teste 2\\Treino\\3-NA-02-RJS\\3-NA-02-RJS_(T1)_20150504162347_03.xml");
        NA02.add("Teste 2\\Treino\\3-NA-02-RJS\\3-NA-02-RJS_(T2)_20150504162346_02.xml");
        NA02.add("Teste 2\\Treino\\3-NA-02-RJS\\3-NA-02-RJS_(T3)_20150504162345_01.xml");
        SampleLithology.processWell(pathLogNA02, NA02);
        
        String pathLogNA04 = "Teste 2\\Treino\\3-NA-04-RJS\\NA04.las";
        List<String> NA04 = new ArrayList<>(); 
        NA04.add("Teste 2\\Treino\\3-NA-04-RJS\\3-NA-04-RJS_(T1)_20150504162349_05.xml");
        NA04.add("Teste 2\\Treino\\3-NA-04-RJS\\3-NA-04-RJS_(T2)_20150504162352_07.xml");
        NA04.add("Teste 2\\Treino\\3-NA-04-RJS\\3-NA-04-RJS_(T3)_20150504162352_08.xml");
        NA04.add("Teste 2\\Treino\\3-NA-04-RJS\\3-NA-04-RJS_(T4)_20150504162354_09.xml");
        NA04.add("Teste 2\\Treino\\3-NA-04-RJS\\3-NA-04-RJS_(T5)_20150504162356_10.xml");
        SampleLithology.processWell(pathLogNA04, NA04);
        
        String pathLogRJS042 = "Teste 2\\Treino\\4-RJ-42-RJS\\RJS042.las";
        List<String> RJS042 = new ArrayList<>(); 
        RJS042.add("Teste 2\\Treino\\4-RJ-42-RJS\\4-RJS-42-RJS_(T1)_20150504162357_11.xml");
        RJS042.add("Teste 2\\Treino\\4-RJ-42-RJS\\4-RJS-42-RJS_(T2)_20150504162359_12.xml");
        SampleLithology.processWell(pathLogRJS042, RJS042);
        
     */
    
    /*//TESTE 2 VALIDACAO
        String pathLogRJS042 = "Teste 2\\Validacao\\4-RJS-234-RJS\\RJS234.las";
        List<String> RJS042 = new ArrayList<>(); 
        RJS042.add("Teste 2\\Validacao\\4-RJS-234-RJS\\4-RJS-234-RJS_(T1)_20150504162401_13.xml");
        RJS042.add("Teste 2\\Validacao\\4-RJS-234-RJS\\4-RJS-234-RJS_(T2)_20150504162402_14.xml");
        RJS042.add("Teste 2\\Validacao\\4-RJS-234-RJS\\4-RJS-234-RJS_(T3)_20150504162404_15.xml");
        RJS042.add("Teste 2\\Validacao\\4-RJS-234-RJS\\4-RJS-234-RJS_(T4)_20150504162404_16.xml");
        RJS042.add("Teste 2\\Validacao\\4-RJS-234-RJS\\4-RJS-234-RJS_(T5)_20150504162405_17.xml");
        RJS042.add("Teste 2\\Validacao\\4-RJS-234-RJS\\4-RJS-234-RJS_(T6)_20150504162408_18.xml");
        SampleLithology.processWell(pathLogRJS042, RJS042);
      */  
    /*  //TESTE 3 TREINO
        String pathLogRJS234 = "Teste 3\\Treino\\4-RJS-234-RJS\\RJS234.las";
        List<String> RJS234 = new ArrayList<>(); 
        RJS234.add("Teste 3\\Treino\\4-RJS-234-RJS\\4-RJS-234-RJS_(T1)_20150504162401_13.xml");
        RJS234.add("Teste 3\\Treino\\4-RJS-234-RJS\\4-RJS-234-RJS_(T2)_20150504162402_14.xml");
        RJS234.add("Teste 3\\Treino\\4-RJS-234-RJS\\4-RJS-234-RJS_(T3)_20150504162404_15.xml");
        RJS234.add("Teste 3\\Treino\\4-RJS-234-RJS\\4-RJS-234-RJS_(T4)_20150504162404_16.xml");
        RJS234.add("Teste 3\\Treino\\4-RJS-234-RJS\\4-RJS-234-RJS_(T5)_20150504162405_17.xml");
        RJS234.add("Teste 3\\Treino\\4-RJS-234-RJS\\4-RJS-234-RJS_(T6)_20150504162408_18.xml");
        SampleLithology.processWell(pathLogRJS234, RJS234);
    */
    
    /*  //TESTE 3 VALIDACAO
        String pathLogRJS234 = "Teste 3\\Validacao\\NA07.las";
        List<String> RJS234 = new ArrayList<>(); 
        RJS234.add("Teste 3\\Validacao\\7-NA-07-RJS_(T1)_20150504162409_19.xml");
        RJS234.add("Teste 3\\Validacao\\7-NA-07-RJS_(T2)_20150504162410_20.xml");
        RJS234.add("Teste 3\\Validacao\\7-NA-07-RJS_(T3)_20150504162411_21.xml");
        RJS234.add("Teste 3\\Validacao\\7-NA-07-RJS_(T4)_20150504162413_22.xml");
        RJS234.add("Teste 3\\Validacao\\7-NA-07-RJS_(T5)_20150504162414_23.xml");
        RJS234.add("Teste 3\\Validacao\\7-NA-07-RJS_(T6)_20150504162414_24.xml");
        SampleLithology.processWell(pathLogRJS234, RJS234);
    */
    
    /*  //TESTE 4 TREINO
        String pathLogRJS019 = "Teste 4\\Treino\\1-RJ-19-RJS\\RJS019.las";
        List<String> RJS019 = new ArrayList<>(); 
        RJS019.add("Teste 4\\Treino\\1-RJ-19-RJS\\1-RJ-19-RJS_(T2)_20150504162350_06.xml");
        SampleLithology.processWell(pathLogRJS019, RJS019);
    */
    
    /*    //TESTE 4 VALIDACAO
        String pathLogRJS019 = "Teste 4\\Validacao\\LPN2\\perfis\\LPN-2.las";
        List<String> RJS019 = new ArrayList<>(); 
        RJS019.add("Teste 4\\Validacao\\LPN2\\LPN_02_ES-T1_20141030144704.xml");
        RJS019.add("Teste 4\\Validacao\\LPN2\\LPN_02_ES-T2_20141030144545.xml");
        RJS019.add("Teste 4\\Validacao\\LPN2\\LPN_02_ES-T3_20141031170856.xml");
        RJS019.add("Teste 4\\Validacao\\LPN2\\LPN_02_ES-T4_20141031175409.xml");
        RJS019.add("Teste 4\\Validacao\\LPN2\\LPN_02_ES-T5_20141103143620.xml");
        SampleLithology.processWell(pathLogRJS019, RJS019);
    */
    /*  //TESTE 5 TREINO
        String pathLogESS23 = "Teste 5\\Treino\\ESS-23.las";
        List<String> ESS23 = new ArrayList<>(); 
        ESS23.add("Teste 5\\Treino\\ESS023_Rangel_T1_20141021111049.xml");
        ESS23.add("Teste 5\\Treino\\ESS023_RANGEL_T2_20141022131905.xml");
        ESS23.add("Teste 5\\Treino\\ESS023_RANGEL_T4_20141027133209.xml");
        ESS23.add("Teste 5\\Treino\\ESS023_RANGEL_T5_20141027143230.xml");
        ESS23.add("Teste 5\\Treino\\ESS023_RANGEL_T6_20141027145430.xml");
        SampleLithology.processWell(pathLogESS23, ESS23);
    */
    
    /*  //TESTE 6 TREINO
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
    */
    
    //TESTE 6 VALIDACAO
        String pathLogSZ160 = "teste6\\Validacao\\7-CP-0995-SE\\Perfis\\CP995.las";
        List<String> pathDescriptionsSZ160 = new ArrayList<>(); 
        pathDescriptionsSZ160.add("teste6\\Validacao\\7-CP-0995-SE\\7-CP-0995-SE_(T1)_20151112101055_19.xml");
        pathDescriptionsSZ160.add("teste6\\Validacao\\7-CP-0995-SE\\7-CP-0995-SE_(T2)_20151112101056_21.xml");
        pathDescriptionsSZ160.add("teste6\\Validacao\\7-CP-0995-SE\\7-CP-0995-SE_(T3)_20151112101056_22.xml");
        pathDescriptionsSZ160.add("teste6\\Validacao\\7-CP-0995-SE\\7-CP-0995-SE_(T4)_20151112101057_23.xml");
        pathDescriptionsSZ160.add("teste6\\Validacao\\7-CP-0995-SE\\7-CP-0995-SE_(T5)_20151112101057_24.xml");
        pathDescriptionsSZ160.add("teste6\\Validacao\\7-CP-0995-SE\\7-CP-0995-SE_(T6)_20151112101058_25.xml");
        pathDescriptionsSZ160.add("teste6\\Validacao\\7-CP-0995-SE\\7-CP-0995-SE_(T7)_20151112101058_26.xml");
        pathDescriptionsSZ160.add("teste6\\Validacao\\7-CP-0995-SE\\7-CP-0995-SE_(T8)_20151112101059_27.xml");
        pathDescriptionsSZ160.add("teste6\\Validacao\\7-CP-0995-SE\\7-CP-0995-SE_(T9)_20151112101100_28.xml");
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
            OrganizedSample.add(Integer.toString(lithology));
            
            
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
            return 0;
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
                    writer.print("LAS" + TAB + "XML" + TAB + "LITHO");
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