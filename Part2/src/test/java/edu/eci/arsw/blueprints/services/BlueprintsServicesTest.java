package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BlueprintsServicesTest {

    @Mock
    private BlueprintsPersistence bpp;

    @InjectMocks
    private BlueprintsServices blueprintsServices;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddNewBlueprint() throws BlueprintPersistenceException {
        Blueprint bp = new Blueprint("author", "name", new Point[]{new Point(0, 0)});

        doNothing().when(bpp).saveBlueprint(bp);

        blueprintsServices.addNewBlueprint(bp);

        verify(bpp, times(1)).saveBlueprint(bp);
    }

    @Test
    public void testGetAllBlueprints() {
        List<Blueprint> blueprints = new ArrayList<>();
        blueprints.add(new Blueprint("author1", "name1", new Point[]{new Point(0, 0)}));
        blueprints.add(new Blueprint("author2", "name2", new Point[]{new Point(1, 1)}));

        when(bpp.getAllBluePrints()).thenReturn(blueprints);

        List<Blueprint> result = blueprintsServices.getAllBlueprints();

        assertEquals(2, result.size());
        assertEquals("author1", result.get(0).getAuthor());
        assertEquals("name1", result.get(0).getName());
        assertEquals("author2", result.get(1).getAuthor());
        assertEquals("name2", result.get(1).getName());
    }

    @Test
    public void testGetBlueprintSuccess() throws BlueprintNotFoundException {
        Blueprint bp = new Blueprint("author", "name", new Point[]{new Point(0, 0)});

        when(bpp.getBlueprint("author", "name")).thenReturn(bp);

        Blueprint result = blueprintsServices.getBlueprint("author", "name");

        assertEquals("author", result.getAuthor());
        assertEquals("name", result.getName());
    }

    @Test(expected = BlueprintNotFoundException.class)
    public void testGetBlueprintNotFound() throws BlueprintNotFoundException {
        when(bpp.getBlueprint("author", "name")).thenThrow(BlueprintNotFoundException.class);

        blueprintsServices.getBlueprint("author", "name");
    }

    @Test
    public void testGetBlueprintsByAuthorSuccess() throws BlueprintNotFoundException {
        Blueprint bp = new Blueprint("author", "name", new Point[]{new Point(0, 0)});

        when(bpp.getBlueprintByAuthor("author")).thenReturn(bp);

        Blueprint result = blueprintsServices.getBlueprintsByAuthor("author");

        assertEquals("author", result.getAuthor());
        assertEquals("name", result.getName());
    }

    @Test(expected = BlueprintNotFoundException.class)
    public void testGetBlueprintsByAuthorNotFound() throws BlueprintNotFoundException {
        when(bpp.getBlueprintByAuthor("author")).thenThrow(BlueprintNotFoundException.class);

        blueprintsServices.getBlueprintsByAuthor("author");
    }

    @Test
    public void testGetBlueprintsByNameSuccess() throws BlueprintNotFoundException {
        Blueprint bp = new Blueprint("author", "name", new Point[]{new Point(0, 0)});

        when(bpp.getBlueprintByName("name")).thenReturn(bp);

        Blueprint result = blueprintsServices.getBlueprintsByName("name");

        assertEquals("author", result.getAuthor());
        assertEquals("name", result.getName());
    }

    @Test(expected = BlueprintNotFoundException.class)
    public void testGetBlueprintsByNameNotFound() throws BlueprintNotFoundException {
        when(bpp.getBlueprintByName("name")).thenThrow(BlueprintNotFoundException.class);

        blueprintsServices.getBlueprintsByName("name");
    }
}
