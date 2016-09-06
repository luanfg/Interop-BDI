/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petrofacies.model.stratigraphicmodel;

import petrofacies.model.IAttribute;

/**
 *
 * @author Luan
 */
public abstract class AbstractStratigraphicAttribute implements IAttribute
{
 
    private String value;
    private int id;

    protected abstract void updateValue();

    
    public String getValue()
    {
        if(value == null)
            updateValue();
        
        return value;
    }


    public void setId(int id) {
       
        this.id = id;
    }


    public int getId() {

        return this.id;
    }
    
    
    
    
    
}
