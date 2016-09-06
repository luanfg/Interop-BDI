/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petrofacies.model.stratigraphicmodel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luan
 */
public class DepositionalFacies 
{
    
    private float topMeasure;
    private float bottomMeasure;
    private StratigraphicGrainSize grainSize;
    private StratigraphicFossil fossil;
    private StratigraphicLithology lithology;
    private StratigraphicRockColor rockColor;
    private StratigraphicRoundness roundness;
    private StratigraphicSorting sorting;
    private StratigraphicSphericity sphericity;
    private StratigraphicStructure mainStructure;
    private List<StratigraphicStructure> secondaryStructureList;
    
    
    /**
     * @return the grainSize
     */
    public StratigraphicGrainSize getGrainSize() {
        
        if(grainSize == null)
            grainSize = new StratigraphicGrainSize();
        
        return grainSize;
    }   

    /**
     * @return the topMeasure
     */
    public float getTopMeasure() {
        return topMeasure;
    }

    /**
     * @param topMeasure the topMeasure to set
     */
    public void setTopMeasure(float topMeasure) {
        this.topMeasure = topMeasure;
    }

    /**
     * @return the bottomMeasure
     */
    public float getBottomMeasure() {
        return bottomMeasure;
    }

    /**
     * @param bottomMeasure the bottomMeasure to set
     */
    public void setBottomMeasure(float bottomMeasure) {
        this.bottomMeasure = bottomMeasure;
    }

    /**
     * @return the fossil
     */
    public StratigraphicFossil getFossil() {
        
        if(fossil == null)
            fossil = new StratigraphicFossil();
        
        return fossil;
    }

    /**
     * @return the lithology
     */
    public StratigraphicLithology getLithology() {
        
        if(lithology == null)
            lithology = new StratigraphicLithology();
        
        return lithology;
    }

    /**
     * @return the rockColor
     */
    public StratigraphicRockColor getRockColor() {
        
        if(rockColor == null)
            rockColor = new StratigraphicRockColor();
        
        return rockColor;
    }

    /**
     * @return the roundness
     */
    public StratigraphicRoundness getRoundness() {
        
        if(roundness == null)
            roundness = new StratigraphicRoundness();
        
        return roundness;
    }

    /**
     * @return the sorting
     */
    public StratigraphicSorting getSorting() {
        
        if(sorting == null)
            sorting = new StratigraphicSorting();
        
        return sorting;
    }

    /**
     * @return the sphericity
     */
    public StratigraphicSphericity getSphericity() {
        
        if(sphericity == null)
            sphericity = new StratigraphicSphericity();
        
        return sphericity;
    }

    /**
     * @return the structure
     */
    public StratigraphicStructure getMainStructure() {
        
        if(mainStructure == null)
            mainStructure = new StratigraphicStructure();
        
        return mainStructure;
    }
    
    public List<StratigraphicStructure> getSecondaryStructuresList()
    {
        if(secondaryStructureList == null)
            secondaryStructureList = new ArrayList<>();
        
        return secondaryStructureList;
    }   
}
