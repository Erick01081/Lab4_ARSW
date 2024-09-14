/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.blueprints.persistence.impl.Tuple;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class InMemoryPersistenceTest {

    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        InMemoryBlueprintPersistence persistence;
        Map<Tuple<String, String>, Blueprint> blueprints;


        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);

        ibpp.saveBlueprint(bp0);

        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);

        ibpp.saveBlueprint(bp);

        assertNotNull("Loading a previously stored blueprint returned null.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()));

        assertEquals("Loading a previously stored blueprint returned a different blueprint.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()), bp);

    }


    @Test
    public void saveExistingBpTest() {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);

        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }

        Point[] pts2=new Point[]{new Point(10, 10),new Point(20, 20)};
        Blueprint bp2=new Blueprint("john", "thepaint",pts2);

        try{
            ibpp.saveBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        }
        catch (BlueprintPersistenceException ex){

        }


    }

    @Test
    public void getBlueprintByAuthorTest() throws BlueprintPersistenceException, BlueprintNotFoundException {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();

        Point[] pts1 = new Point[]{new Point(1, 1)};
        Blueprint bp1 = new Blueprint("author1", "name1", pts1);
        ibpp.saveBlueprint(bp1);

        Point[] pts2 = new Point[]{new Point(2, 2)};
        Blueprint bp2 = new Blueprint("author2", "name2", pts2);
        ibpp.saveBlueprint(bp2);

        Blueprint retrievedBlueprint = ibpp.getBlueprintByAuthor("author1");
        assertNotNull("Blueprint by author1 should not be null.", retrievedBlueprint);
        assertEquals("Blueprint by author1 should be the correct one.", bp1, retrievedBlueprint);
    }

    @Test
    public void getBlueprintByNameTest() throws BlueprintPersistenceException, BlueprintNotFoundException {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();

        Point[] pts1 = new Point[]{new Point(1, 1)};
        Blueprint bp1 = new Blueprint("author1", "name1", pts1);
        ibpp.saveBlueprint(bp1);

        Point[] pts2 = new Point[]{new Point(2, 2)};
        Blueprint bp2 = new Blueprint("author2", "name2", pts2);
        ibpp.saveBlueprint(bp2);

        Blueprint retrievedBlueprint = ibpp.getBlueprintByName("name1");
        assertNotNull("Blueprint by name1 should not be null.", retrievedBlueprint);
        assertEquals("Blueprint by name1 should be the correct one.", bp1, retrievedBlueprint);
    }

    @Test
    public void getBlueprintTest() throws BlueprintPersistenceException, BlueprintNotFoundException {
        // Crear instancia de la clase de persistencia
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();

        // Crear y guardar un Blueprint
        Point[] pts = new Point[]{new Point(5, 5), new Point(10, 10)};
        Blueprint bp = new Blueprint("author1", "blueprint1", pts);
        ibpp.saveBlueprint(bp);

        // Obtener el Blueprint guardado
        Blueprint retrievedBlueprint = ibpp.getBlueprint("author1", "blueprint1");

        // Verificar que el Blueprint recuperado no es nulo y es el correcto
        assertNotNull("Expected to find a blueprint with the given author and name.", retrievedBlueprint);
        assertEquals("The retrieved blueprint should be equal to the saved blueprint.", bp, retrievedBlueprint);
    }
}
