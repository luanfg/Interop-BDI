/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petrofacies.framework;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import petrofacies.control.LASParser;
import petrofacies.control.XMLHandler;
import petrofacies.control.XMLReader;
import petrofacies.model.stratigraphicmodel.DepositionalFacies;
import petrofacies.model.stratigraphicmodel.StratigraphicDescription;

/**
 *
 * @author Luan
 */
public class PetrofaciesFramework extends Application {
    
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
        parser.parseLAS("D:\\Desenvolvimento\\Java Projects\\Petrofacies Framework\\FU128.las");
       
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
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
