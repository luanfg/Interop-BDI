/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.stratigraphic.model;

/**
 * Types that are used to retrieve data from the database.
 * @author Luan
 */
public enum AttributeType
{
    Contact ("TYPE_CONTACT"),
    Fossil ("TYPE_FOSSIL"),
    GrainSize ("TYPE_GRAIN_SIZE"),
    Lithology ("TYPE_LITHOLOGY"),
    RockColor ("TYPE_ROCK_COLOR"),
    Roundness ("TYPE_ROUNDNESS"),
    Sorting ("TYPE_SORTING"),
    Sphericity ("TYPE_SPHERICITY"),
    Structure ("TYPE_STRUCTURE");

    private final String type;

    AttributeType(String type) {
        this.type = type;
    }
    
    public String toString()
    {
        return this.type;
    }
}