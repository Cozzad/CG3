import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PositionTest.
 *
 * @author  Michael P. & Alexander
 * @version 2
 */
public class PositionTest
{
    private Country country1;
    private City cityA, cityB;
    private Position pos;
    
    @Before
    public void setUp() {
        country1 = new Country("Country 1", null);
        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 80, country1);
        pos = new Position(cityA, cityB, 3);
        
    }
    
    @Test
    public void constructor()   {
        assertEquals(pos.getFrom(), cityA);
        assertEquals(pos.getTo(), cityB);
        assertEquals(pos.getDistance(), 3);
    }
    
    @Test
    public void hasArrived()    {
        assertFalse(pos.hasArrived());
        pos.move();
        assertFalse(pos.hasArrived());
        pos.move();
        pos.move();
        assertTrue(pos.hasArrived());
    }
    
    @Test
    public void move()  {
        assertEquals(pos.getDistance(), 3);
        assertTrue(pos.move());
        assertEquals(pos.getDistance(), 2);
        assertTrue(pos.move());
        assertEquals(pos.getDistance(), 1);
        assertTrue(pos.move());
        assertEquals(pos.getDistance(), 0);
        assertFalse(pos.move());
        assertEquals(pos.getDistance(), 0);
    }
    
    @Test
    public void turnAround()    {
        pos.move();
        pos.turnAround();
        assertEquals(pos.getFrom(), cityB);
        assertEquals(pos.getTo(), cityA);
        assertEquals(pos.getDistance(), 1);
        assertEquals(3, pos.getTotal());
        
        pos.move();
        pos.turnAround();
        assertEquals(pos.getFrom(), cityA);
        assertEquals(pos.getTo(), cityB);
        assertEquals(pos.getDistance(), 3);
        assertEquals(3, pos.getTotal());
        
        pos.turnAround();
        assertEquals(pos.getFrom(), cityB);
        assertEquals(pos.getTo(), cityA);
        assertEquals(pos.getDistance(), 0);
        assertEquals(3, pos.getTotal());
    }
}

