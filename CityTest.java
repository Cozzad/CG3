import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

/**
 * The test class CityTest.
 *
 * @author  Michael P. & Alexander
 * @version 2
 */
public class CityTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;

    @Before
    public void setUp() {
        game = new Game(0);
        game.getRandom().setSeed(0);

        // Create countries
        country1 = new Country("Country 1", null);
        country2 = new MafiaCountry("Country 2", null);
        country1.setGame(game);
        country2.setGame(game);

        // Create Cities
        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new City("City C", 40, country1);
        cityD = new City("City D", 100, country1);
        cityE = new City("City E", 50, country2);
        cityF = new City("City F", 90, country2);
        cityG = new City("City G", 0, country2);
    }
    
    @Test
    public void constructor() {
        assertEquals(cityA.getCountry(), country1);
        assertEquals(cityB.getCountry(), country1);
        assertEquals(cityC.getCountry(), country1);
        assertEquals(cityD.getCountry(), country1);
        assertEquals(cityE.getCountry(), country2);
        assertEquals(cityF.getCountry(), country2);
        assertEquals(cityG.getCountry(), country2);
        assertEquals(cityA.getName(), "City A");
        assertEquals(cityF.getName(), "City F");
        assertEquals(cityA.getValue(), 80);
        assertEquals(cityF.getValue(), 90);
        assertNotEquals(cityB.getValue(), -20);
        assertNotEquals(cityF.getValue(), -10);
        assertEquals(cityG.getValue(), 0);
    }
    
    @Test
    public void changeValue() {
        assertEquals(cityA.getValue(), 80);
        cityA.changeValue(20);
        assertEquals(cityA.getValue(), 100);
        cityA.changeValue(-110);
        assertEquals(cityA.getValue(), -10);
        
        assertEquals(cityE.getValue(), 50);
        cityE.changeValue(20);
        assertEquals(cityE.getValue(), 70);
        cityE.changeValue(-80);
        assertEquals(cityE.getValue(), -10);
        
        cityA.reset();
        cityA.changeValue(-80);
        assertEquals(cityA.getValue(), 0);
        cityA.changeValue(Integer.MAX_VALUE);
        assertEquals(cityA.getValue(), 2147483647);
        cityA.changeValue(-Integer.MAX_VALUE);
        cityA.changeValue(Integer.MIN_VALUE);
        assertEquals(cityA.getValue(), -2147483648);
        
        cityE.reset();
        assertEquals(cityE.getValue(), 50);
        cityE.changeValue(0);
        assertEquals(cityE.getValue(), 50);
    }
    
    @Test
    public void reset() {
        assertEquals(cityA.getValue(), 80);
        cityA.changeValue(20);
        assertEquals(cityA.getValue(), 100);
        cityA.reset();
        assertEquals(cityA.getValue(), 80);
        
        assertEquals(cityE.getValue(), 50);
        cityE.changeValue(20);
        assertEquals(cityE.getValue(), 70);
        cityE.reset();
        assertEquals(cityE.getValue(), 50);
    }
    
    @Test
    public void arrive() {
        for(int i=0; i<1000; i++) {
            game.getRandom().setSeed(i);
            int bonus = country1.bonus(80);
            game.getRandom().setSeed(i);
            assertEquals(cityA.arrive(), bonus);
            assertEquals(cityA.getValue(),80-bonus);
            cityA.reset();
        }
        
        for(int i=0; i<1000; i++) {
            game.getRandom().setSeed(i);
            int bonus = country2.bonus(90);
            game.getRandom().setSeed(i);
            assertEquals(cityF.arrive(), bonus);
            if(bonus>=0) {
                assertEquals(cityF.getValue(),90-bonus);
            } else {
                assertEquals(cityF.getValue(),90);
            }
            cityF.reset();
        }
    }

    @Test
    public void compareTo()
    {
        assertEquals(-1, cityA.compareTo(cityB));
        assertEquals(0, cityA.compareTo(cityA));
        assertTrue(cityA.compareTo(cityB) < 0);
        assertTrue(cityB.compareTo(cityA) > 0);
        assertTrue(cityG.compareTo(cityA) > 0);
        assertEquals(cityG.compareTo(cityG), 0);
    }
    
    @Test
    public void equals()    {
        assertTrue(cityA.equals(cityA));
        assertFalse(cityA.equals(null));
        assertFalse(cityA.equals(cityB));
        //Symmetric 
        City c = cityA;
        assertTrue(cityA.equals(c));
        assertTrue(c.equals(cityA));
        //Transitivity
        City z = cityA;
        assertTrue(c.equals(z));
        assertTrue(z.equals(cityA));
        assertTrue(cityA.equals(z));
        //Different country
        City cityA2 = new City("City A", 80, country2);
        assertFalse(cityA2.equals(cityA));
        //Different classes.
        assertFalse(cityA.equals(country1));
        assertFalse(cityB.equals(country2));
        
        /** 
         * From lecture: for en mængde vil et kald add(elem) kun tilføje elementet, 
         * hvis der ikke eksisterer et element e i mængden, så elem.equals(e) er sand.
         */
        HashSet<City> cities = new HashSet<>();
        cities.add(cityA);
        cities.add(cityB);
        cities.add(cityC);
        cities.add(cityD);
        cities.add(cityE);
        cities.add(cityF);
        cities.add(cityG);
        cities.add(cityA);
        assertTrue(cities.size()==7);
    }
    
    @Test
    public void hashCodeTest()  {
        assertFalse(cityA.hashCode()==cityB.hashCode());
        assertFalse(cityB.hashCode()==cityC.hashCode());
        assertFalse(cityC.hashCode()==cityD.hashCode());
        assertFalse(cityD.hashCode()==cityE.hashCode());
        assertFalse(cityE.hashCode()==cityF.hashCode());
        assertFalse(cityF.hashCode()==cityG.hashCode());
        assertFalse(cityG.hashCode()==cityA.hashCode());
        assertFalse(cityA.hashCode()==0);
        //Different country
        City cityA2 = new City("City A", 80, country2);
        assertFalse(cityA.hashCode()==cityA2.hashCode());
        //HashCodePlusValue
        int hA = cityA.hashCode()+1;
        assertFalse(hA==cityA.hashCode());
        //Konsistency
        City c = cityA;
        assertTrue(cityA.equals(c));
        assertTrue(cityA.hashCode()==c.hashCode());
    }
}

