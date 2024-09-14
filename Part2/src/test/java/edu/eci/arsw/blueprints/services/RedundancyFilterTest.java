package edu.eci.arsw.blueprints.services;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.services.RedundancyFilter;

public class RedundancyFilterTest {

    @Test
    public void testFilterPlainWithConsecutiveDuplicatePoints() {
        RedundancyFilter filter = new RedundancyFilter();
        Point[] points = {
                new Point(0, 0),
                new Point(0, 0),
                new Point(1, 1),
                new Point(1, 1),
                new Point(2, 2)
        };
        Blueprint bp = new Blueprint("author", "test", points);

        Blueprint filteredBp = filter.filterPlain(bp);

        assertEquals(3, filteredBp.getPoints().size());
        assertEquals(new Point(0, 0), filteredBp.getPoints().get(0));
        assertEquals(new Point(1, 1), filteredBp.getPoints().get(1));
        assertEquals(new Point(2, 2), filteredBp.getPoints().get(2));
    }

    @Test
    public void testFilterPlainWithNoDuplicatePoints() {
        RedundancyFilter filter = new RedundancyFilter();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3)
        };
        Blueprint bp = new Blueprint("author", "test", points);

        Blueprint filteredBp = filter.filterPlain(bp);

        assertEquals(4, filteredBp.getPoints().size());
        assertEquals(new Point(0, 0), filteredBp.getPoints().get(0));
        assertEquals(new Point(1, 1), filteredBp.getPoints().get(1));
        assertEquals(new Point(2, 2), filteredBp.getPoints().get(2));
        assertEquals(new Point(3, 3), filteredBp.getPoints().get(3));
    }

    @Test
    public void testFilterPlainWithAllPointsDuplicate() {
        RedundancyFilter filter = new RedundancyFilter();
        Point[] points = {
                new Point(0, 0),
                new Point(0, 0),
                new Point(0, 0),
                new Point(0, 0)
        };
        Blueprint bp = new Blueprint("author", "test", points);

        Blueprint filteredBp = filter.filterPlain(bp);

        assertEquals(1, filteredBp.getPoints().size());
        assertEquals(new Point(0, 0), filteredBp.getPoints().get(0));
    }

    @Test
    public void testFilterPlainWithEmptyPointsArray() {
        RedundancyFilter filter = new RedundancyFilter();
        Point[] points = {};
        Blueprint bp = new Blueprint("author", "test", points);

        Blueprint filteredBp = filter.filterPlain(bp);

        assertTrue(filteredBp.getPoints().isEmpty());
    }

    @Test
    public void testFilterPlainWithSinglePoint() {
        RedundancyFilter filter = new RedundancyFilter();
        Point[] points = {new Point(0, 0)};
        Blueprint bp = new Blueprint("author", "test", points);

        Blueprint filteredBp = filter.filterPlain(bp);

        assertEquals(1, filteredBp.getPoints().size());
        assertEquals(new Point(0, 0), filteredBp.getPoints().get(0));
    }

    @Test
    public void testFilterPlainPreservesAuthorAndName() {
        RedundancyFilter filter = new RedundancyFilter();
        Point[] points = {
                new Point(0, 0),
                new Point(0, 0),
                new Point(1, 1),
                new Point(1, 1)
        };
        Blueprint bp = new Blueprint("testAuthor", "testName", points);

        Blueprint filteredBp = filter.filterPlain(bp);

        assertEquals("testAuthor", filteredBp.getAuthor());
        assertEquals("testName", filteredBp.getName());
    }
}
