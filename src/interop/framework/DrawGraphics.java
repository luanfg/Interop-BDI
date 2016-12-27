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

/**
 *
 * @author Eduardo
 */
public class DrawGraphics extends JPanel{
    private int width = 1280;//TODO: mudar dimensao janela
    private int height = 720;
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
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        this.width = this.application.getWidth();
        this.height = this.application.getHeight();
        
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
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.add(this);           
        application.setSize(width, height);
        application.setVisible(true);          
    }
    
}
