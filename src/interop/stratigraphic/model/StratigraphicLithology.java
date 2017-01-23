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
public class StratigraphicLithology extends AbstractStratigraphicAttribute
{
    protected String textureSVG = null;
    
    @Override
    protected void updateValue() {
        SQLite sql = new SQLite();
        this.value = sql.readValue(AttributeType.Lithology, this.getId());
    }

    /**
     * Returns the textude on svg format of that specific attribute instance as a String. This value is filled accessing the database.
     * @return 
     */
    public String getTextureSVG()
    {
        if(textureSVG == null)
            updateTextureSVG();
        
        return textureSVG;
    }
    
    protected void updateTextureSVG(){
        SQLite sql = new SQLite();
        this.textureSVG = sql.readValue(AttributeType.Lithology, "VALUE_PATTERN_SVG", this.getId());//or is it VALUE_PATTERN?
        
    }
    
}
