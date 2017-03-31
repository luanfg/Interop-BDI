/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.lithologyDataCollector;

import interop.lithoprototype.model.LithologyDatabase;
import interop.lithoprototype.util.FaciesLogCorrelation;
import interop.log.model.LogValuePair;
import interop.log.model.ParsedLAS;
import interop.log.model.WellLog;
import interop.log.util.LASParser;
import interop.stratigraphic.model.DepositionalFacies;
import interop.stratigraphic.model.StratigraphicDescription;
import interop.stratigraphic.model.StratigraphicGrainSize;
import interop.stratigraphic.model.StratigraphicRoundness;
import interop.stratigraphic.util.XMLReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import static java.nio.file.Files.list;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduardo
 */
public class SampleLithology {
    
    static List<String> logTypesWanted ;
    private static String TAB = "\t"; 
    private static List<LithologyArchiveFormat> lithologies = new ArrayList<>();
    private static LithologyDatabase db;
    public static float nullValue;
    
    public static LithologyDatabase main()
    {  
        //SELECIONA QUAIS TIPOS DE LOG QUERES PROCESSAR
        logTypesWanted = new ArrayList<>();
        //logTypesWanted.add("DEPT");
        logTypesWanted.add("DT");
        logTypesWanted.add("GR");
        logTypesWanted.add("ILD");
        logTypesWanted.add("NPHI");
        logTypesWanted.add("RHOB");
        logTypesWanted.add("DRHO");
        //logTypesWanted.add("CALI");
        logTypesWanted.add("SP");
        logTypesWanted.add("SN");
        logTypesWanted.add("MSFL");
        
        db = new LithologyDatabase(logTypesWanted);
        
        //E QUAIS ARQUIVOS, BASTA DESCOMENTAR
        
     /* //TESTE 1 TREINO
        String pathLogNA04 = "C:\\StrataDB\\Teste 1\\Treino\\NA04.las";
        List<String> pathDescriptionsNA04 = new ArrayList<>(); 
        pathDescriptionsNA04.add("C:\\StrataDB\\Teste 1\\Treino\\3-NA-04-RJS_(T1)_20150504162349_05.xml");
        pathDescriptionsNA04.add("C:\\StrataDB\\Teste 1\\Treino\\3-NA-04-RJS_(T2)_20150504162352_07.xml");
        pathDescriptionsNA04.add("C:\\StrataDB\\Teste 1\\Treino\\3-NA-04-RJS_(T4)_20150504162354_09.xml");
        pathDescriptionsNA04.add("C:\\StrataDB\\Teste 1\\Treino\\3-NA-04-RJS_(T5)_20150504162356_10.xml");
        SampleLithology.processWell(pathLogNA04, pathDescriptionsNA04);
    */
      
    /*  //TESTE 1 VALIDAÇÃO
        String pathLogNA04 = "C:\\StrataDB\\Teste 1\\Validacao\\NA04.las";
        List<String> pathDescriptionsNA04 = new ArrayList<>(); 
        pathDescriptionsNA04.add("C:\\StrataDB\\Teste 1\\Validacao\\3-NA-04-RJS_(T3)_20150504162352_08.xml");
        SampleLithology.processWell(pathLogNA04, pathDescriptionsNA04);
    */
    
    /*  //TESTE 2 TREINO
        String pathLogRJS019 = "C:\\\\StrataDB\\\\Teste 2\\Treino\\1-RJ-19-RJS\\RJS019.las";
        List<String> RJS019 = new ArrayList<>(); 
        RJS019.add("C:\\\\StrataDB\\\\Teste 2\\Treino\\1-RJ-19-RJS\\1-RJ-19-RJS_(T2)_20150504162350_06.xml");
        SampleLithology.processWell(pathLogRJS019, RJS019);
        
        String pathLogNA01A = "C:\\\\StrataDB\\\\Teste 2\\Treino\\3-NA-1A-RJS\\NA01A.las";
        List<String> NA01A = new ArrayList<>(); 
        NA01A.add("C:\\\\StrataDB\\\\Teste 2\\Treino\\3-NA-1A-RJS\\3-NA-1A-RJS_(T3)_20150504162348_04.xml");
        SampleLithology.processWell(pathLogNA01A, NA01A);
        
        String pathLogNA02 = "C:\\\\StrataDB\\\\Teste 2\\Treino\\3-NA-02-RJS\\NA02.las";
        List<String> NA02 = new ArrayList<>(); 
        NA02.add("C:\\\\StrataDB\\\\Teste 2\\Treino\\3-NA-02-RJS\\3-NA-02-RJS_(T1)_20150504162347_03.xml");
        NA02.add("C:\\\\StrataDB\\\\Teste 2\\Treino\\3-NA-02-RJS\\3-NA-02-RJS_(T2)_20150504162346_02.xml");
        NA02.add("C:\\\\StrataDB\\\\Teste 2\\Treino\\3-NA-02-RJS\\3-NA-02-RJS_(T3)_20150504162345_01.xml");
        SampleLithology.processWell(pathLogNA02, NA02);
        
        String pathLogNA04 = "C:\\\\StrataDB\\\\Teste 2\\Treino\\3-NA-04-RJS\\NA04.las";
        List<String> NA04 = new ArrayList<>(); 
        NA04.add("C:\\\\StrataDB\\\\Teste 2\\Treino\\3-NA-04-RJS\\3-NA-04-RJS_(T1)_20150504162349_05.xml");
        NA04.add("C:\\\\StrataDB\\\\Teste 2\\Treino\\3-NA-04-RJS\\3-NA-04-RJS_(T2)_20150504162352_07.xml");
        NA04.add("C:\\\\StrataDB\\\\Teste 2\\Treino\\3-NA-04-RJS\\3-NA-04-RJS_(T3)_20150504162352_08.xml");
        NA04.add("C:\\\\StrataDB\\\\Teste 2\\Treino\\3-NA-04-RJS\\3-NA-04-RJS_(T4)_20150504162354_09.xml");
        NA04.add("C:\\\\StrataDB\\\\Teste 2\\Treino\\3-NA-04-RJS\\3-NA-04-RJS_(T5)_20150504162356_10.xml");
        SampleLithology.processWell(pathLogNA04, NA04);
        
        String pathLogRJS042 = "C:\\\\StrataDB\\\\Teste 2\\Treino\\4-RJ-42-RJS\\RJS042.las";
        List<String> RJS042 = new ArrayList<>(); 
        RJS042.add("C:\\\\StrataDB\\\\Teste 2\\Treino\\4-RJ-42-RJS\\4-RJS-42-RJS_(T1)_20150504162357_11.xml");
        RJS042.add("C:\\\\StrataDB\\\\Teste 2\\Treino\\4-RJ-42-RJS\\4-RJS-42-RJS_(T2)_20150504162359_12.xml");
        SampleLithology.processWell(pathLogRJS042, RJS042);
        
    */
    
    /*//TESTE 2 VALIDACAO
        String pathLogRJS042 = "C:\\\\StrataDB\\\\Teste 2\\Validacao\\4-RJS-234-RJS\\RJS234.las";
        List<String> RJS042 = new ArrayList<>(); 
        RJS042.add("C:\\\\StrataDB\\\\Teste 2\\Validacao\\4-RJS-234-RJS\\4-RJS-234-RJS_(T1)_20150504162401_13.xml");
        RJS042.add("C:\\\\StrataDB\\\\Teste 2\\Validacao\\4-RJS-234-RJS\\4-RJS-234-RJS_(T2)_20150504162402_14.xml");
        RJS042.add("C:\\\\StrataDB\\\\Teste 2\\Validacao\\4-RJS-234-RJS\\4-RJS-234-RJS_(T3)_20150504162404_15.xml");
        RJS042.add("C:\\\\StrataDB\\\\Teste 2\\Validacao\\4-RJS-234-RJS\\4-RJS-234-RJS_(T4)_20150504162404_16.xml");
        RJS042.add("C:\\\\StrataDB\\\\Teste 2\\Validacao\\4-RJS-234-RJS\\4-RJS-234-RJS_(T5)_20150504162405_17.xml");
        RJS042.add("C:\\\\StrataDB\\\\Teste 2\\Validacao\\4-RJS-234-RJS\\4-RJS-234-RJS_(T6)_20150504162408_18.xml");
        SampleLithology.processWell(pathLogRJS042, RJS042);
    */  
    /*  //TESTE 3 TREINO
        String pathLogRJS234 = "C:\\\\StrataDB\\\\Teste 3\\Treino\\4-RJS-234-RJS\\RJS234.las";
        List<String> RJS234 = new ArrayList<>(); 
        RJS234.add("C:\\\\StrataDB\\\\Teste 3\\Treino\\4-RJS-234-RJS\\4-RJS-234-RJS_(T1)_20150504162401_13.xml");
        RJS234.add("C:\\\\StrataDB\\\\Teste 3\\Treino\\4-RJS-234-RJS\\4-RJS-234-RJS_(T2)_20150504162402_14.xml");
        RJS234.add("C:\\\\StrataDB\\\\Teste 3\\Treino\\4-RJS-234-RJS\\4-RJS-234-RJS_(T3)_20150504162404_15.xml");
        RJS234.add("C:\\\\StrataDB\\\\Teste 3\\Treino\\4-RJS-234-RJS\\4-RJS-234-RJS_(T4)_20150504162404_16.xml");
        RJS234.add("C:\\\\StrataDB\\\\Teste 3\\Treino\\4-RJS-234-RJS\\4-RJS-234-RJS_(T5)_20150504162405_17.xml");
        RJS234.add("C:\\\\StrataDB\\\\Teste 3\\Treino\\4-RJS-234-RJS\\4-RJS-234-RJS_(T6)_20150504162408_18.xml");
        SampleLithology.processWell(pathLogRJS234, RJS234);
    */
    
    /*  //TESTE 3 VALIDACAO
        String pathLogRJS234 = "C:\\\\StrataDB\\\\Teste 3\\Validacao\\NA07.las";
        List<String> RJS234 = new ArrayList<>(); 
        RJS234.add("C:\\\\StrataDB\\\\Teste 3\\Validacao\\7-NA-07-RJS_(T1)_20150504162409_19.xml");
        RJS234.add("C:\\\\StrataDB\\\\Teste 3\\Validacao\\7-NA-07-RJS_(T2)_20150504162410_20.xml");
        RJS234.add("C:\\\\StrataDB\\\\Teste 3\\Validacao\\7-NA-07-RJS_(T3)_20150504162411_21.xml");
        RJS234.add("C:\\\\StrataDB\\\\Teste 3\\Validacao\\7-NA-07-RJS_(T4)_20150504162413_22.xml");
        RJS234.add("C:\\\\StrataDB\\\\Teste 3\\Validacao\\7-NA-07-RJS_(T5)_20150504162414_23.xml");
        RJS234.add("C:\\\\StrataDB\\\\Teste 3\\Validacao\\7-NA-07-RJS_(T6)_20150504162414_24.xml");
        SampleLithology.processWell(pathLogRJS234, RJS234);
    */
    
    /*  //TESTE 4 TREINO
        String pathLogRJS019 = "C:\\StrataDB\\Teste 4\\Treino\\1-RJ-19-RJS\\RJS019.las";
        List<String> RJS019 = new ArrayList<>(); 
        RJS019.add("C:\\StrataDB\\Teste 4\\Treino\\1-RJ-19-RJS\\1-RJ-19-RJS_(T2)_20150504162350_06.xml");
        SampleLithology.processWell(pathLogRJS019, RJS019);
    */
    
    /*    //TESTE 4 VALIDACAO
        String pathLogRJS019 = "C:\\\\StrataDB\\\\Teste 4\\Validacao\\LPN2\\perfis\\LPN-2.las";
        List<String> RJS019 = new ArrayList<>(); 
        RJS019.add("C:\\\\StrataDB\\\\Teste 4\\Validacao\\LPN2\\LPN_02_ES-T1_20141030144704.xml");
        RJS019.add("C:\\\\StrataDB\\\\Teste 4\\Validacao\\LPN2\\LPN_02_ES-T2_20141030144545.xml");
        RJS019.add("C:\\\\StrataDB\\\\Teste 4\\Validacao\\LPN2\\LPN_02_ES-T3_20141031170856.xml");
        RJS019.add("C:\\\\StrataDB\\\\Teste 4\\Validacao\\LPN2\\LPN_02_ES-T4_20141031175409.xml");
        RJS019.add("C:\\\\StrataDB\\\\Teste 4\\Validacao\\LPN2\\LPN_02_ES-T5_20141103143620.xml");
        SampleLithology.processWell(pathLogRJS019, RJS019);
    */
    /*  //TESTE 5 TREINO
        String pathLogESS23 = "C:\\\\StrataDB\\\\Teste 5\\Treino\\ESS-23.las";
        List<String> ESS23 = new ArrayList<>(); 
        ESS23.add("C:\\\\StrataDB\\\\Teste 5\\Treino\\ESS023_Rangel_T1_20141021111049.xml");
        ESS23.add("C:\\\\StrataDB\\\\Teste 5\\Treino\\ESS023_RANGEL_T2_20141022131905.xml");
        ESS23.add("C:\\\\StrataDB\\\\Teste 5\\Treino\\ESS023_RANGEL_T4_20141027133209.xml");
        ESS23.add("C:\\\\StrataDB\\\\Teste 5\\Treino\\ESS023_RANGEL_T5_20141027143230.xml");
        ESS23.add("C:\\\\StrataDB\\\\Teste 5\\Treino\\ESS023_RANGEL_T6_20141027145430.xml");
        SampleLithology.processWell(pathLogESS23, ESS23);
    */
    /*
      //TESTE 6 TREINO
        String pathLogCP1847 = "C:\\\\StrataDB\\\\Teste 6\\Treino\\3-CP-1847-SE\\Perfis\\CP1847.las";
        List<String> pathDescriptionsCP1847 = new ArrayList<>(); 
        pathDescriptionsCP1847.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\3-CP-1847-SE\\3-CP-1847-SE_(T1)_20151112101101_31.xml");
        pathDescriptionsCP1847.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\3-CP-1847-SE\\3-CP-1847-SE_(T2)_20151112101100_29.xml");
        pathDescriptionsCP1847.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\3-CP-1847-SE\\3-CP-1847-SE_(T3)_20151112101100_30.xml");
        pathDescriptionsCP1847.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\3-CP-1847-SE\\3-CP-1847-SE_(T4)_20151112101102_32.xml");
        pathDescriptionsCP1847.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\3-CP-1847-SE\\3-CP-1847-SE_(T5)_20151112101103_33.xml");
        SampleLithology.processWell(pathLogCP1847, pathDescriptionsCP1847);
    
        String pathLogSZ626 = "C:\\\\StrataDB\\\\Teste 6\\Treino\\3-SZ-0626-SE\\Perfis\\SZ626.las";
        List<String> pathDescriptionsSZ626 = new ArrayList<>(); 
        pathDescriptionsSZ626.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\3-SZ-0626-SE\\3-SZ-0626-SE_(T1)_20151112101052_14.xml");
        pathDescriptionsSZ626.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\3-SZ-0626-SE\\3-SZ-0626-SE_(T2)_20151112101053_15.xml");
        SampleLithology.processWell(pathLogSZ626, pathDescriptionsSZ626);
        
        String pathLogCP1914 = "C:\\\\StrataDB\\\\Teste 6\\Treino\\7-CP-1914-SE\\Perfis\\CP1914.las";
        List<String> pathDescriptionsCP1914 = new ArrayList<>(); 
        pathDescriptionsCP1914.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\7-CP-1914-SE\\7-CP-1914-SE_(T1)_20151112101047_01.xml");
        pathDescriptionsCP1914.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\7-CP-1914-SE\\7-CP-1914-SE_(T2)_20151112101047_02.xml");
        pathDescriptionsCP1914.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\7-CP-1914-SE\\7-CP-1914-SE_(T3)_20151112101048_03.xml");
        SampleLithology.processWell(pathLogCP1914, pathDescriptionsCP1914);
        
        String pathLogFU128 = "C:\\\\StrataDB\\\\Teste 6\\Treino\\7-FU-0128-AL\\Perfis\\FU128.las";
        List<String> pathDescriptionsFU128 = new ArrayList<>(); 
        pathDescriptionsFU128.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\7-FU-0128-AL\\7-FU-0128-AL_(T1)_20150804170346.xml");
        SampleLithology.processWell(pathLogFU128, pathDescriptionsFU128);
        
        String pathLogSZ508D = "C:\\\\StrataDB\\\\Teste 6\\Treino\\7-SZ-0508D-SE\\Perfis\\SZ508D.las";
        List<String> pathDescriptionsSZ508D = new ArrayList<>(); 
        pathDescriptionsSZ508D.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\7-SZ-0508D-SE\\7-SZ-0508D-SE_(T1)_20151112101054_16.xml");
        pathDescriptionsSZ508D.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\7-SZ-0508D-SE\\7-SZ-0508D-SE_(T2)_20151112101054_17.xml");
        pathDescriptionsSZ508D.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\7-SZ-0508D-SE\\7-SZ-0508D-SE_(T3)_20151112101055_20.xml");
        pathDescriptionsSZ508D.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\7-SZ-0508D-SE\\7-SZ-0508D-SE_(T4)_20151112101055_18.xml");
        SampleLithology.processWell(pathLogSZ508D, pathDescriptionsSZ508D);
        
        String pathLogGTP48 = "C:\\\\StrataDB\\\\Teste 6\\Treino\\7-FU-0128-AL\\Perfis\\FU128.las";
        List<String> pathDescriptionsGTP48 = new ArrayList<>(); 
        pathDescriptionsGTP48.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\9-GTP-0048-SE\\15_descricoes (9-GTP-0048-SE).xml");
        SampleLithology.processWell(pathLogGTP48, pathDescriptionsGTP48);
        
        String pathLogSZ160 = "C:\\\\StrataDB\\\\Teste 6\\Treino\\9-SZ-0160-SE\\Perfis\\SZ160.las";
        List<String> pathDescriptionsSZ160 = new ArrayList<>(); 
        pathDescriptionsSZ160.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T24)_20151112101048_04.xml");
        pathDescriptionsSZ160.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T25)_20151112101048_05.xml");
        pathDescriptionsSZ160.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T26)_20151112101049_06.xml");
        pathDescriptionsSZ160.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T27)_20151112101050_07.xml");
        pathDescriptionsSZ160.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T28)_20151112101050_08.xml");
        pathDescriptionsSZ160.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T29)_20151112101051_09.xml");
        pathDescriptionsSZ160.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T30)_20151112101051_10.xml");
        pathDescriptionsSZ160.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T31)_20151112101051_11.xml");
        pathDescriptionsSZ160.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T32)_20151112101052_12.xml");
        pathDescriptionsSZ160.add("C:\\\\StrataDB\\\\Teste 6\\Treino\\9-SZ-0160-SE\\9-SZ-0160-SE_(T33)_20151112101052_13.xml");
        SampleLithology.processWell(pathLogSZ160, pathDescriptionsSZ160);
     */
    ///*
    //TESTE 6 VALIDACAO
        String pathLogsCP995 = "C:\\StrataDB\\Teste 6\\Validacao\\7-CP-0995-SE\\Perfis\\CP995.las";
        List<String> pathDescriptionsCP995 = new ArrayList<>(); 
        pathDescriptionsCP995.add("C:\\StrataDB\\Teste 6\\Validacao\\7-CP-0995-SE\\7-CP-0995-SE_(T1)_20151112101055_19.xml");
        pathDescriptionsCP995.add("C:\\StrataDB\\Teste 6\\Validacao\\7-CP-0995-SE\\7-CP-0995-SE_(T2)_20151112101056_21.xml");
        pathDescriptionsCP995.add("C:\\StrataDB\\Teste 6\\Validacao\\7-CP-0995-SE\\7-CP-0995-SE_(T3)_20151112101056_22.xml");
        pathDescriptionsCP995.add("C:\\StrataDB\\Teste 6\\Validacao\\7-CP-0995-SE\\7-CP-0995-SE_(T4)_20151112101057_23.xml");
        pathDescriptionsCP995.add("C:\\StrataDB\\Teste 6\\Validacao\\7-CP-0995-SE\\7-CP-0995-SE_(T5)_20151112101057_24.xml");
        pathDescriptionsCP995.add("C:\\StrataDB\\Teste 6\\Validacao\\7-CP-0995-SE\\7-CP-0995-SE_(T6)_20151112101058_25.xml");
        pathDescriptionsCP995.add("C:\\StrataDB\\Teste 6\\Validacao\\7-CP-0995-SE\\7-CP-0995-SE_(T7)_20151112101058_26.xml");
        pathDescriptionsCP995.add("C:\\StrataDB\\Teste 6\\Validacao\\7-CP-0995-SE\\7-CP-0995-SE_(T8)_20151112101059_27.xml");
        pathDescriptionsCP995.add("C:\\StrataDB\\Teste 6\\Validacao\\7-CP-0995-SE\\7-CP-0995-SE_(T9)_20151112101100_28.xml");
        SampleLithology.processWell(pathLogsCP995, pathDescriptionsCP995);
      //*/
      /*  
        String pathLog7CP1382DSE = "C:\\StrataDB\\NovosPocos\\7-CP-1382D-SE\\Perfil\\CP1382.las";
        List<String> pathDescriptions7CP1382DSE = new ArrayList<>();
        pathDescriptions7CP1382DSE.add("C:\\StrataDB\\NovosPocos\\7-CP-1382D-SE\\7_CP_1382D_SE_20161017212332.xml");
        SampleLithology.processWell(pathLog7CP1382DSE, pathDescriptions7CP1382DSE);
        
        String pathLogCP674 = "C:\\StrataDB\\NovosPocos\\9-CP-0674-SE\\Perfil\\CP674.las";
        List<String> pathDescriptionsCP674 = new ArrayList<>();
        pathDescriptionsCP674.add("C:\\StrataDB\\NovosPocos\\9-CP-0674-SE\\9_CP_0674_SE_20161003211554.xml");
        SampleLithology.processWell(pathLogCP674, pathDescriptionsCP674);
    
        String pathLogCP1394 = "C:\\StrataDB\\NovosPocos\\9-CP-1394-SE\\Perfil\\CP1394.las";
        List<String> pathDescriptionsCP1394 = new ArrayList<>();
        pathDescriptionsCP1394.add("C:\\StrataDB\\NovosPocos\\9-CP-1394-SE\\9_CP_1394_SE_20160926203751.xml");
        SampleLithology.processWell(pathLogCP1394, pathDescriptionsCP1394);
        */

        //COMENTAR PARA GRAVAR EM ARQUIVO .TXT
        LithologyArchiveFormat.initializeWriter();
        for(LithologyArchiveFormat laf: lithologies){
            laf.saveToArchive();
        }
        LithologyArchiveFormat.closeWriter();

        
        return db;
    
    }
    
    public static List<Double> getSample(int index, String pathLog){
        LASParser parser = new LASParser();
        ParsedLAS parsed = parser.parseLAS(pathLog);
        List<String> organized = new OrganizeSample(parsed, index).Organize();
        return parseDouble(organized);
    }
    
    public static List<Double> getSample(int index, ParsedLAS parsedlas){
        List<String> organized = new OrganizeSample(parsedlas, index).Organize();
        return parseDouble(organized);
    }
    
    private static List<Double> parseDouble(List<String> strings){
        List<Double> doubles = new ArrayList<>();
        for(String string:strings){
            doubles.add(Double.parseDouble(string));
        }
        return doubles;
    }
    
    static void processWell(String pathLog, List<String> pathDescriptions){
        
        LASParser parser = new LASParser();    
        ParsedLAS parsed = parser.parseLAS(pathLog);
        nullValue = parsed.getNullValue();
        System.out.println();
        System.out.print( "Processing Well");     
        //FOR EVERY SAMPLE IN THE LAS FILE
        for(int i=0; i<parsed.getLogsList().get(0).getLogValues().size() ; i++){
            if((10*i)/parsed.getLogsList().get(0).getLogValues().size() > (10*(i-1))/parsed.getLogsList().get(0).getLogValues().size())
                System.out.print(".");
            //ORGANIZE SAMPLE IN ORDER OF logsTypeWanted
            List<String> OrganizedSample = new OrganizeSample(parsed, i).Organize();
            
            //SEARCH THE LITHOLOGY IN THE LIST OF XML, IF IT EXISTS
            DiscoverLithology discoverLithology = new DiscoverLithology(pathLog, i, pathDescriptions);
            int lithology = discoverLithology.discover();
            //int lithology = discoverLithology.fast_discover();
            //System.out.println(lithology2 + " AND " + lithology);
            
            if(lithology!=0)
                db.feedDatabase(lithology, OrganizedSample);
            
            //AND GET THE PATH OF LAS AND XML TO IDENTIFY IN THE OUTPUT
            String las = discoverLithology.getLasPath();
            String xml = discoverLithology.getXmlPath();
            OrganizedSample.add(las);
            OrganizedSample.add(xml);
            OrganizedSample.add(discoverLithology.getLithologyName());
            //System.out.println("NAMO:" + discoverLithology.getLithologyName() );
            OrganizedSample.add(Integer.toString(lithology));
            OrganizedSample.add(String.valueOf(discoverLithology.grainSizeID));
            OrganizedSample.add(String.valueOf(discoverLithology.roundnessID));
            OrganizedSample.add(String.valueOf(discoverLithology.sphericityID));
            
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
                    LithologyArchiveFormat lit = new LithologyArchiveFormat(lithology, discoverLithology.LithologyName , OrganizedSample);
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

    public static int getNumberOfSamples(String las) {
        LASParser parser = new LASParser();
        ParsedLAS parsed = parser.parseLAS(las);
        return parsed.getLogsList().get(0).getLogValues().size();
    }
    
        
    static class DiscoverLithology{
        
        List<String> pathDescriptions = new ArrayList<>();
        float depthLAS;
        String lasFound;
        String xmlFound;
        String LithologyName;
        String grainSize;
        int grainSizeID;
        int roundnessID;
        int sortingID;
        int sphericityID;
        
        //testing the fast_discover
        static List<StratigraphicDescription> stratigraphicDescriptions = null;
        static StratigraphicDescription stratigraphicDescription;
        static DepositionalFacies facie;
        static int i=0; //indexOfWhichXmlFileWeAreLooking;
        static int j=0;//indexOfStratigraphicDescriptionList;
        static int k=0;//indexOfDepositionalFaciesList;
        
        
        public DiscoverLithology(String lasPath, int index, List<String> path){
            LASParser parser = new LASParser();    
            ParsedLAS parsed = parser.parseLAS(lasPath);
            this.pathDescriptions = path;
            this.depthLAS = parsed.getLogsList().get(0).getLogValues().get(index).getDepth();
            lasFound = lasPath;
            
            if(stratigraphicDescriptions==null){
                stratigraphicDescriptions = XMLReader.readStratigraphicDescriptionXML(path.get(0));
                facie = stratigraphicDescriptions.get(0).getFaciesList().get(0);
                mmTOm(facie);
            }
        }
        
        

        private DepositionalFacies nextDepositionalFacie() {
            if(k < stratigraphicDescriptions.get(j).getFaciesList().size() -1 )
                return stratigraphicDescriptions.get(j).getFaciesList().get(++k);
            else{
                k=0;
                if( j < stratigraphicDescriptions.size()-1)
                    return stratigraphicDescriptions.get(++j).getFaciesList().get(k);
                else{
                    k=0;
                    j=0;
                    if( i < pathDescriptions.size()-1){
                        stratigraphicDescriptions = XMLReader.readStratigraphicDescriptionXML(pathDescriptions.get(++i));
                        return stratigraphicDescriptions.get(j).getFaciesList().get(k);
                    }
                    else return null;
                }
            }
        }
        
        public String getLasPath(){
            return lasFound;
        }
        
        public String getXmlPath(){
            return xmlFound;
        }
        public String getLithologyName(){
            return this.LithologyName;
        }
        
        
        public int fast_discover()
        //same as the discover() method but with the ideia that each xml file is readed only once, but the results are the same
        {
            if(facie==null)
                return 0;
            if(facie.getBottomMeasure()>= depthLAS && facie.getTopMeasure()<depthLAS){
                   //GET THE LITHOLOGY AND THE XML IT WAS FOUND
                    xmlFound = pathDescriptions.get(i);
                    LithologyName = facie.getLithology().getValue();
                    grainSize = facie.getGrainSize().getValue();
                    grainSizeID = facie.getGrainSize().getId();
                    roundnessID = facie.getRoundness().getId();
                    sortingID = facie.getSorting().getId();
                    sphericityID = facie.getSphericity().getId();
                    return facie.getLithology().getId();
            }
            else if(depthLAS > facie.getBottomMeasure()){
                    facie = this.nextDepositionalFacie();
                    if(facie==null)
                        return 0;
                    mmTOm(facie);
                    return fast_discover();
            }
            
            return 0;
        }
        
        public int discover(){
                        
            //FOR EVERY XML FILE
            for(String path:pathDescriptions){
                List<StratigraphicDescription> listCore = XMLReader.readStratigraphicDescriptionXML(path);
                //FOR EVERY CORE IN A XML FILE
                for(StratigraphicDescription core:listCore){
                    List<DepositionalFacies> faciesList = core.getFaciesList();
                    faciesList = mmTOm(faciesList);
                    //FOR EVERY FACIES
                    for(DepositionalFacies facie:faciesList){
                        //IF THE DEPTH OF SAMPLE IS IN BETWEEN THE FACIE
                        if(facie.getBottomMeasure()>= depthLAS && facie.getTopMeasure()<depthLAS){
                            //GET THE LITHOLOGY AND THE XML IT WAS FOUND
                            xmlFound = path;
                            LithologyName = facie.getLithology().getValue();
                            grainSize = facie.getGrainSize().getValue();
                            grainSizeID = facie.getGrainSize().getId();
                            roundnessID = facie.getRoundness().getId();
                            sortingID = facie.getSorting().getId();
                            sphericityID = facie.getSphericity().getId();
                            
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
    
    private static DepositionalFacies mmTOm(DepositionalFacies dp)
    {
        dp.setBottomMeasure(dp.getBottomMeasure()/1000);
        dp.setTopMeasure(dp.getTopMeasure()/1000);
        return dp;
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
        static int counter =0;
        static PrintWriter writer = null;
        int lithologyUID;
        String lithologyName = null;
        List<List<String>> specificLog;
                
        public LithologyArchiveFormat(int UID, String lithoName,  List<String> sample){
            this.specificLog = new ArrayList<>();
            specificLog.add(sample);
            lithologyUID = UID;
        }
        
        public static void initializeWriter(){
            try {
                writer = new PrintWriter("Samples Output\\teste.txt", "UTF-8");
                for(String types: logTypesWanted){
                writer.print(types);
                writer.print(TAB);
            }
                writer.println("LAS" + TAB + "XML" + TAB + "LITHO_NAME" + TAB + "LITHO_ID" + TAB + "GRAIN_SIZE" + TAB + "ROUNDNESS_ID" + TAB + "SPHERICIRY_ID");

            } catch (IOException e) {
                System.out.println("Cannot create output file... ");
            }
        }
        
        public void add(List<String> sample){
            specificLog.add(sample);
        }
    
        public static void closeWriter(){
            writer.close();
        }
        
        //ATENTION: clean the folder of results before resaving samples, or it will save at the end
        public void saveToArchive(){
                                               
            for(List<String> sample:specificLog){
                for(String data:sample){
                    writer.print(data);
                    writer.print(TAB);
                    counter++;
                }
                writer.println();
            }
             
        }
    }
}