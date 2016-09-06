/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petrofacies.model.stratigraphicmodel;

import java.util.ArrayList;
import java.util.List;
import petrofacies.model.IDescription;

/**
 *
 * @author Luan
 */
public class StratigraphicDescription implements IDescription
{
    private String wellName;
    private float topMeasure;
    private float bottomMeasure;
    
    private List<DepositionalFacies> faciesList;

    /**
     * @return the faciesList
     */
    public List<DepositionalFacies> getFaciesList() {
        
        return faciesList;
    }

    public void addFacies(DepositionalFacies df)
    {
        if(faciesList == null)
           faciesList = new ArrayList<>();
        
        this.faciesList.add(df);
    }

    /**
     * @return the wellName
     */
    public String getWellName() {
        return wellName;
    }

    /**
     * @param wellName the wellName to set
     */
    public void setWellName(String wellName) {
        this.wellName = wellName;
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
}
