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

@RestController
public class BlueprintController {
    private final BlueprintsServices bp;

    @Autowired
    public BlueprintController(BlueprintsServices bp) {
        this.bp = bp;
    }

    @RequestMapping(value = "/consultspecific", method = RequestMethod.GET)
    public ResponseEntity<String> consultSpecific(@RequestBody String author, String name) {
        try {
            return ResponseEntity.ok(bp.getBlueprint(author, name).toString());
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/consultAuthor", method = RequestMethod.GET)
    public ResponseEntity<String> consult(@RequestBody String author) {
        try {
            return ResponseEntity.ok(bp.getBlueprintsByAuthor(author).toString());
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/consultName", method = RequestMethod.GET)
    public ResponseEntity<String> consultByName(String name) {
        try {
            return ResponseEntity.ok(bp.getBlueprintsByName(name).toString());
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody Blueprint blueprint) {
        bp.addNewBlueprint(blueprint);
        return ResponseEntity.ok("Save");
    }
}
