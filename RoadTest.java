import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

/**
 * The test class RoadTest.
 *
 * @author  Michael P. & Alexander
 * @version 2
 */
public class RoadTest
{
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityNull;
    
    @Before
    public void setUp() {
        // Create countries
        country1 = new Country("Country 1", null);
        country2 = new Country("Country 2", null);
        // Create Cities
        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new City("City C", 40, country1);
        cityD = new City("City D", 100, country1);
        cityE = new City("City E", 50, country2);
        cityF = new City("City F", 90, country2);
        cityNull = new City(null, 70, country2);

    }
    
    @Test
    public void constructor()   {
        Road road1 = new Road(cityA, cityB, 4);
        Road road2 = new Road(cityB, cityC, 4);
        Road road3 = new Road(cityC, cityD, -8);
        Road road4 = new Road(cityD, cityE, 0);
        Road road5 = new Road(cityE, cityF, 4);
        Road roadNull = new Road(cityA, cityNull, 4);
        assertEquals(roadNull.getTo(), cityNull);
        assertEquals(road1.getFrom(), cityA);
        assertEquals(road3.getLength(), -8);
        assertEquals(road4.getLength(), 0);
        assertEquals(road1.getLength(), 4);
        
    }
    
    @Test
    public void compareTo() {
        Road road1 = new Road(cityA, cityB, 0);
        Road road2 = new Road(cityA, cityC, 0);
        Road road3 = new Road(cityB, cityA, 0);
        assertTrue(road1.compareTo(road2) < 0);
        assertTrue(road2.compareTo(road1) > 0);
        assertTrue(road3.compareTo(road2) > 0);
        assertEquals(road1.compareTo(road1), 0);
        assertEquals(road2.compareTo(road2), 0);
        assertEquals(road3.compareTo(road3), 0);
    }

    
}
