/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.stratigraphic.model;

import interop.stratigraphic.control.SQLite;

/**
 *
 * @author Luan
 */
public class StratigraphicRockColor extends AbstractStratigraphicAttribute
{
    
    protected String rgbCode = null;
    

    @Override
    protected void updateValue() {
        SQLite sql = new SQLite();
        this.value = sql.readValue(AttributeType.RockColor, this.getId());
    }

    /**
     * Returns the RGB code of that specific attribute instance as a String. This value is filled accessing the database.
     * @return 
     */
    public String getRGBCode()
    {
        if(rgbCode == null)
            updateRGBCode();
        
        return rgbCode;
    }
    
    protected void updateRGBCode(){
        SQLite sql = new SQLite();
        this.rgbCode = sql.readValue(AttributeType.RockColor, "VALUE_HEX", this.getId());
    }
    
    
    
    
}
