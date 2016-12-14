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
//import interop.movingMeans.MovingMeans;

/**
 *
 * @author Eduardo
 */
public class DrawGraphics extends JPanel{
    private int width = 1280;//TODO: mudar dimensao janela
    private int height = 720;
    ParsedLAS parsed;
    
    public DrawGraphics(ParsedLAS parsed) {
        super(); 
        this.parsed = parsed;
        setBackground(Color.lightGray);
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
             
        for( int i =0; i < parsed.getLogsList().size() ; i++ ){//for each log....
            
            double whereToPutTheLogName = i * width / (parsed.getLogsList().size()+2) ;
            
            String nome = parsed.getLogsList().get(i).getLogMeasureUnit();
            
            g.drawString(nome, (int)whereToPutTheLogName, 12);
            
            boolean dontHaveFirstBit=true; 
            double wellSize = parsed.getStopDepth() - parsed.getStartDepth();
            int x_ant=-1, y_ant=-1;
            
            //find out the max and min values of each log 
            //PS: this should be in the ParsedLAS class
            double maxValue=-99999; //LAS file can have values bigger than 99999?
            double minValue=99999;
            for(int p=0; p<(int)wellSize*5; p++ ){//for the beginning to an end of a log
                double q =parsed.getLogsList().get(i).getLogValues().get(p).getLogValue();
                if(q>maxValue && q!=parsed.getNullValue()){
                     maxValue=q;
                }
                if(q<minValue && q!=parsed.getNullValue()){
                    minValue=q;
                }
            }
                               
            for( int j=0; j<(int)wellSize*5; j++ ){//for the beginning to an end of a log // *5 pois cada poço é avaliado a cada 0.2km, wellSize é em km
                double x=parsed.getLogsList().get(i).getLogValues().get(j).getLogValue();
                double y=parsed.getLogsList().get(i).getLogValues().get(j).getDepth();
                
                if(x != parsed.getNullValue()){                       
                       double screenx = (x-minValue) * width / (maxValue-minValue) / (parsed.getLogsList().size()+2) + i* width / (parsed.getLogsList().size()+2) ;
                       double screeny = (y - parsed.getStartDepth()) * (height-25)  / (wellSize) + 25 ;//25 is the space to put the name of the log 
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
   }
    
    public void draw()
    {
        JFrame application = new JFrame();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.add(this);           
        application.setSize(width, height);
        application.setVisible(true);          
    }
    
}
