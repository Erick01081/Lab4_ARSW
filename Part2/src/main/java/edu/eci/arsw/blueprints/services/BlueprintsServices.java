/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class BlueprintsServices {


    @Autowired
    BlueprintsPersistence bpp;

    public void addNewBlueprint(Blueprint bp) {
        try {
            bpp.saveBlueprint(bp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

    public List<Blueprint> getAllBlueprints() {
        return bpp.getAllBluePrints();
    } 

    /**
     * 
     * @param author blueprint's author
     * @param name   blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author, String name) throws BlueprintNotFoundException {
        try {
            return bpp.getBlueprint(author, name);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Blueprint getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        try {
            return bpp.getBlueprintByAuthor(author);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public Blueprint getBlueprintsByName(String name) throws BlueprintNotFoundException {

        try {
            return bpp.getBlueprintByName(name);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

}
