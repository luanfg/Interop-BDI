/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.framework;

import interop.log.model.ParsedLAS;
import interop.log.util.LogValueList;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import interop.movingMeans.MovingMeans;
import interop.stratigraphic.control.SQLite;
import interop.stratigraphic.model.AttributeType;
import interop.stratigraphic.model.StratigraphicDescription;
import interop.stratigraphic.util.XMLReader;
import java.util.List;

/**
 *
 * @author Eduardo
 */
public class DrawGraphics extends JPanel{
    private int width = 500;
    private int height = 500;
    ParsedLAS parsed;
    JFrame application = new JFrame();
    
    public DrawGraphics(ParsedLAS parsed) {
        super(); 
        this.parsed = parsed;
        setBackground(Color.lightGray);
    }
    
    void writesTheLogMeasureUnits(int i, Graphics g){
            double whereToPutTheLogName = i * width / (parsed.getLogsList().size()+2) ;
            String nome = parsed.getLogsList().get(i).getLogMeasureUnit();
            g.drawString(nome, (int)whereToPutTheLogName, 12);
    }
    
    void drawCore(int cod, Graphics g)
    /*PROBLEMAS: 
            1:O core nao esta paralelo aos dados geofísicos pois ele esta no range entre 0 e 883
              e os geofísicos entre 900 e 2147, nada sobre algum offset no xml, isso parece fazer sentido?
    */
    {
        List<StratigraphicDescription> listCore = XMLReader.readStratigraphicDescriptionXML("C:\\Users\\eduardo\\Documents\\NetBeansProjects\\Interop-BDI\\TG_22_20130605171952.xml");
        
        int size = listCore.get(0).getFaciesList().size();
        float endAt = listCore.get(0).getBottomMeasure();
        float startAt = listCore.get(0).getTopMeasure();
        double wellSize = endAt - startAt;
        
        for(int i = 0; i<size; i++){
                      
            float bottomMeasure = listCore.get(0).getFaciesList().get(i).getBottomMeasure();
            float topMeasure = listCore.get(0).getFaciesList().get(i).getTopMeasure();
                                   
            String color = listCore.get(0).getFaciesList().get(i).getRockColor().getValue();
                       
            int red = 0, green = 0, blue =0;
            if(color != null){
                red =  Integer.valueOf( color.substring( 1, 3 ), 16 );
                green =  Integer.valueOf( color.substring( 3, 5 ), 16 );
                blue =  Integer.valueOf( color.substring( 5, 7 ), 16 );
            }
                            
            double screeny = (((int)topMeasure - startAt) * (height-25))  / (wellSize) +25;
            double screeny_bottom = (((int)bottomMeasure - startAt) * (height-25))  / (wellSize) +25;
            
            Color finalColor = new Color(red,green,blue);
            g.setColor(finalColor);
            g.fillRect(this.width-50, (int)screeny, 40, 20);
            g.setColor(Color.BLACK);
            g.drawRect(this.width-50, (int)screeny, 40, 20);
            //g.setColor(Color.BLACK);
            
            String litho = listCore.get(0).getFaciesList().get(i).getLithology().getValue();
            if(litho!=null)
                g.drawString(litho, (int)this.width-100, (int)screeny );
        }
    }
    
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        this.width = this.application.getContentPane().getWidth();//mudei aqui, agora pega o tamanho do frame dentro da janela
        this.height = this.application.getContentPane().getHeight();
        
        for( int i =0; i < parsed.getLogsList().size() ; i++ ){//for each log....
   
            boolean dontHaveFirstBit=true;//for the first value only 
            double wellSize = parsed.getStopDepth() - parsed.getStartDepth();
            int x_ant=-1, y_ant=-1;
            
            writesTheLogMeasureUnits(i, g);
            
            double maxValue = parsed.getMaxValue(i);
            double minValue = parsed.getMinValue(i);
            
            //Applying MovingMeans function
            LogValueList list = MovingMeans.apply(parsed.getLogsList().get(i).getLogValues(), 1);
            
            for( int j=0; j<(int)wellSize*5; j++ ){//for the beginning to an end of a log // *5 pois cada poço é avaliado a cada 0.2km, wellSize é em km
                double x = list.get(j).getLogValue();
                double y = list.get(j).getDepth();
                
                if(x != parsed.getNullValue()){                       
                       double screenx = (x-minValue) * width / (maxValue-minValue) / (parsed.getLogsList().size()+2) + i* width / (parsed.getLogsList().size()+2) ;
                       double screeny = ((y - parsed.getStartDepth()) * (height-25))  / (wellSize) +25;//25 is the space to put the name of the log 
                       if(dontHaveFirstBit){
                            x_ant=(int)screenx;
                            y_ant=(int)screeny;
                            dontHaveFirstBit=false;
                       }
                       g.drawLine(x_ant, y_ant, (int)screenx, (int)screeny);
                       x_ant=(int)screenx;
                       y_ant=(int)screeny;
                }
            }
        }
        drawCore(1,g);
   }
    
    public void draw()
    {
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.add(this);           
        application.setSize(width, height);
        application.setVisible(true);
       
    }
    
}
