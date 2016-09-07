/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interop.model.stratigraphicmodel.util;

/**
 *
 * @author Luan
 */
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import interop.model.IDescription;
import interop.model.stratigraphicmodel.DepositionalFacies;
import interop.model.stratigraphicmodel.StratigraphicDescription;
import interop.model.stratigraphicmodel.StratigraphicStructure;

public class XMLHandler extends DefaultHandler 
{
    
    boolean bWellDescription = false;
    boolean bWellName = false;
    boolean bWellDescriptionTopMeasure = false;
    boolean bWellDescriptionBottomMeasure = false;
    boolean bFacies = false;
    boolean bFaciesTopMeasure = false;
    boolean bFaciesBottomMeasure = false;
    boolean bFaciesRockClass = false;
    boolean bGrainSize = false;
    boolean bRockColor = false;
    boolean bFaciesName = false;
    boolean bLithology = false;
    boolean bMainStructure = false;
    boolean bStructure = false;
    boolean bSorting = false;
    boolean bRoundness = false;
    boolean bSphericity = false;
    boolean bContact = false;
    String wellName;
    
   
    private List<StratigraphicDescription> descriptionsList = new ArrayList<>();
    private StratigraphicDescription description;
    private DepositionalFacies depositionalFacies;
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        
        if(qName.equalsIgnoreCase("WellDescription"))
        {
            
            bWellDescription = true;
            description = new StratigraphicDescription();
        }
        
        if(qName.equalsIgnoreCase("WellDescriptionName"))
            bWellName = true;
        
        if(qName.equalsIgnoreCase("WellDescriptionTopMeasure"))
            bWellDescriptionTopMeasure = true;
        
        if(qName.equalsIgnoreCase("WellDescriptionBottomMeasure"))
            bWellDescriptionBottomMeasure = true;
        
        if(qName.equalsIgnoreCase("Facies")) {
           //if(attributes.getValue("FaciesEmpty").equalsIgnoreCase("false"))
            {
                bFacies = true;
                depositionalFacies = new DepositionalFacies();
            }
        }
 
        if(qName.equalsIgnoreCase("FaciesTopMeasure")) 
            bFaciesTopMeasure = true;
        
 
        if(qName.equalsIgnoreCase("FaciesBottomMeasure")) 
            bFaciesBottomMeasure = true;
        
        
        if(qName.equalsIgnoreCase("FaciesRockClassUID")) 
            bFaciesRockClass = true;
        
        if(qName.equalsIgnoreCase("GrainSizeUID")) 
            bGrainSize = true;
        
        if(qName.equalsIgnoreCase("RockColorUID"))
            bRockColor = true;
        
        if(qName.equalsIgnoreCase("Name"))
            bFaciesName = true;
        
        if(qName.equalsIgnoreCase("LithologyUID"))
            bLithology = true;
        
        if(qName.equalsIgnoreCase("StructureMAINUID"))
            bMainStructure = true;
        
        if(qName.equalsIgnoreCase("StructureUID"))
            bStructure = true;
        
        if(qName.equalsIgnoreCase("SortingUID"))
            bSorting = true;
        
        if(qName.equalsIgnoreCase("RoundnessUID"))
            bRoundness = true;
        
        if(qName.equalsIgnoreCase("SphericityUID"))
            bSphericity = true;
        
        if(qName.equalsIgnoreCase("ContactTypeUID"))
            bContact = true;
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
        
        if(qName.equalsIgnoreCase("WellDescription"))
        {
         
            descriptionsList.add(description);
            bWellDescription = false;
        }
        
        if(qName.equalsIgnoreCase("WellDescriptionName"))
            bWellName = false;
        
        if(qName.equalsIgnoreCase("WellDescriptionTopMeasure"))
            bWellDescriptionTopMeasure = false;
        
        if(qName.equalsIgnoreCase("WellDescriptionBottomMeasure"))
            bWellDescriptionBottomMeasure = false;
        
        if(qName.equalsIgnoreCase("Facies")) {
            if(bFacies)
            {
                description.addFacies(depositionalFacies);
                bFacies = false;
            }
        }
 
        if(qName.equalsIgnoreCase("FaciesTopMeasure")) 
            bFaciesTopMeasure = false;
        
        if(qName.equalsIgnoreCase("FaciesBottomMeasure")) 
            bFaciesBottomMeasure = false;
        
        if(qName.equalsIgnoreCase("FaciesRockClassUID")) 
            bFaciesRockClass = false;
        
       if(qName.equalsIgnoreCase("GrainSizeUID")) 
            bGrainSize = false;
        
       if(qName.equalsIgnoreCase("RockColorUID"))
            bRockColor = false;
       
       if(qName.equalsIgnoreCase("Name"))
            bFaciesName = false;
       
       if(qName.equalsIgnoreCase("LithologyUID"))
            bLithology = false;
       
       if(qName.equalsIgnoreCase("StructureMAINUID"))
            bMainStructure = false;
       
       if(qName.equalsIgnoreCase("StructureUID"))
            bStructure = false;
       
       if(qName.equalsIgnoreCase("SortingUID"))
            bSorting = false;
       
       if(qName.equalsIgnoreCase("RoundnessUID"))
            bRoundness = false;
        
        if(qName.equalsIgnoreCase("SphericityUID"))
            bSphericity = false;
        
       if(qName.equalsIgnoreCase("ContactTypeUID"))
            bContact = false;
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException 
    {
        
        if(bWellName)
            description.setWellName(new String(ch, start, length));
        
        if(bWellDescriptionTopMeasure)
            description.setTopMeasure(Float.parseFloat(new String(ch, start, length)));
        
        if(bWellDescriptionBottomMeasure)
            description.setBottomMeasure(Float.parseFloat((new String(ch, start, length))));
        
        if(bFaciesTopMeasure)
            depositionalFacies.setTopMeasure(Float.parseFloat(new String(ch, start, length)));
        
        if(bFaciesBottomMeasure)
            depositionalFacies.setBottomMeasure(Float.parseFloat(new String(ch, start, length)));

        if(bGrainSize)
            depositionalFacies.getGrainSize().setId(Integer.parseInt(new String(ch, start, length)));
        
        if(bRockColor)
            depositionalFacies.getRockColor().setId(Integer.parseInt(new String(ch, start, length)));
        
        if(bLithology)
            depositionalFacies.getLithology().setId(Integer.parseInt(new String(ch, start, length)));
        
        if(bMainStructure)
            depositionalFacies.getMainStructure().setId(Integer.parseInt(new String(ch, start, length)));
        
        if(bStructure)
        {
            StratigraphicStructure structure = new StratigraphicStructure();
            structure.setId(Integer.parseInt(new String(ch, start, length)));
            depositionalFacies.getSecondaryStructuresList().add(structure);
        }
        
        if(bSorting)
            depositionalFacies.getSorting().setId(Integer.parseInt(new String(ch, start, length)));
        
        if(bRoundness)
            depositionalFacies.getSphericity().setId(Integer.parseInt(new String(ch, start, length)));
        
        if(bSphericity)
            depositionalFacies.getSphericity().setId(Integer.parseInt(new String(ch, start, length)));
    }

    /**
     * @return the description
     */
    public List<StratigraphicDescription> getDescriptionsList() {
        
        return descriptionsList;
    }
            
}
