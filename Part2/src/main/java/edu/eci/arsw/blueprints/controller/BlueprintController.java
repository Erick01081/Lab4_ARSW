package edu.eci.arsw.blueprints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.services.BlueprintsServices;


public class BlueprintController {

    @Autowired
    private final BlueprintsServices bp;


    public BlueprintController(BlueprintsServices bp) {
        this.bp = bp;
    }

    public Blueprint consultSpecific(String author, String name) {
        try {
            return bp.getBlueprint(author, name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    
    public Blueprint consult(String author) {
        try {
            return bp.getBlueprintsByAuthor(author);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public Blueprint consultByName(String name) {
        try {
            return bp.getBlueprintsByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public void register(Blueprint blueprint) {
        bp.addNewBlueprint(blueprint);
    }
}
