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
public class StratigraphicStructure extends AbstractStratigraphicAttribute
{

    @Override
    protected void updateValue() {
        SQLite sql = new SQLite();
        this.value = sql.readValue(AttributeType.Structure, this.getId());
    }
    
}
