/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.framework;

import interop.log.model.LogValuePair;
import interop.log.model.ParsedLAS;
import interop.log.model.WellLog;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import interop.log.util.LASParser;
import interop.log.util.LogValueList;
import interop.movingMeans.MovingMeans;


/**
 *
 * @author Luan
 */
public class InteropFramework extends Application {
    
    @Override
    public void start(Stage primaryStage) {
                
       /* List<StratigraphicDescription> descriptionsList = XMLReader.readStratigraphicDescriptionXML("D:\\Desenvolvimento\\Java Projects\\Petrofacies Framework\\TG_22_20130605171952.xml");
            
        for(StratigraphicDescription description : descriptionsList)
        {
            System.out.println(description.getWellName()+ " Top: " + description.getTopMeasure() + "Bottom: " + description.getBottomMeasure());

            for(DepositionalFacies facies : description.getFaciesList())
            {
               System.out.println("\tTop: " + facies.getTopMeasure());
               System.out.println("\tBottom: " + facies.getBottomMeasure());
               System.out.println("\tLithology: " + facies.getLithology().getId());
               System.out.println("\tGrain Size: " + facies.getGrainSize().getId());
               System.out.println();
            }
        }*/

        LASParser parser = new LASParser();    
        ParsedLAS parsed = parser.parseLAS("FU128.las");
//        System.out.println(parser.parseLAS("FU128.las").getLogsList().size());
       
        Button btn = new Button();
        btn.setText("Print Logs");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                DrawGraphics dg = new DrawGraphics(parsed);
                dg.draw();
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);     
    }
    
}
