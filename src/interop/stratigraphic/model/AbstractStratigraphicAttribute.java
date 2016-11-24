/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.stratigraphic.model;

import interop.model.IAttribute;

/**
 * A default class for facies attributes from Strataledge. The id value must come from a parsed Strata XML, every other attribute must come from the database.
 * @author Luan
 */
public abstract class AbstractStratigraphicAttribute implements IAttribute
{
 
    protected String value = null;
    private int id;

    protected abstract void updateValue();

    /**
     * Returns the nominal value of the attribute instance as a String. This value is filled accessing the database.
     * @return 
     */
    public String getValue()
    {
        if(value == null)
            updateValue();
        
        return value;
    }


    public void setId(int id) {
       
        this.id = id;
    }

    /**
     * Returns the code id value of the attribute.
     * @return 
     */
    public int getId() {

        return this.id;
    }
    
    
    
    
    
}
