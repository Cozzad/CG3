

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

/**
 * The test class CapitalCityTest.
 *
 * @author  Michael P & Alexander
 * @version 1
 */
public class CapitalCityTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;

    /**
     * Default constructor for test class CapitalCityTest
     */
    public CapitalCityTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        game = new Game(0);
        game.getRandom().setSeed(0);
        Map<City, List<Road>> network1 = new HashMap<>();
        Map<City, List<Road>> network2 = new HashMap<>();

        // Create countries
        country1 = new Country("Country 1", network1);
        country2 = new Country("Country 2", network2);
        country1.setGame(game);
        country2.setGame(game);

        // Create Cities
        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new City("City C", 40, country1);
        cityD = new CapitalCity("City D", 100, country1);
        cityE = new CapitalCity("City E", 50, country2);
        cityF = new City("City F", 90, country2);
        cityG = new City("City G", 70, country2);

        // Create road lists
        List<Road> roadsA = new ArrayList<Road>(),
        roadsB = new ArrayList<>(),
        roadsC = new ArrayList<>(),
        roadsD = new ArrayList<>(),
        roadsE = new ArrayList<>(),
        roadsF = new ArrayList<>(),
        roadsG = new ArrayList<>();

        network1.put(cityA, roadsA);
        network1.put(cityB, roadsB);
        network1.put(cityC, roadsC);
        network1.put(cityD, roadsD);
        network2.put(cityE, roadsE);
        network2.put(cityF, roadsF);
        network2.put(cityG, roadsG);

        // Create roads
        country1.addRoads(cityA, cityB, 2);
        country1.addRoads(cityA, cityC, 3);
        country1.addRoads(cityA, cityD, 5);
        country1.addRoads(cityB, cityD, 2);
        country1.addRoads(cityC, cityD, 2);
        country1.addRoads(cityC, cityE, 4);
        country1.addRoads(cityD, cityF, 3);
        country2.addRoads(cityE, cityC, 4);
        country2.addRoads(cityE, cityF, 2);
        country2.addRoads(cityE, cityG, 5);
        country2.addRoads(cityF, cityD, 3);
        country2.addRoads(cityF, cityG, 6);
    }
    
    @Test
    public void arriveOtherCountry() {        
        for(int i=0; i<1000; i++) {
            Player p = new Player(new Position(cityF, cityD, 0),250);
            
            game.getRandom().setSeed(i);
            int bonus = country1.bonus(100);
            int toll = country1.getGame().getSettings().getTollToBePaid();
            toll = (toll*p.getMoney())/100;
            
            Random r = country1.getGame().getRandom();
            int moneySpent = r.nextInt(p.getMoney()+bonus-toll+1);  
            
            game.getRandom().setSeed(i);
            assertEquals(cityD.arrive(p), bonus-toll-moneySpent);
            assertEquals(cityD.getValue(),100-bonus+toll+moneySpent);
            cityD.reset();
        }
    }
        
    @Test
    public void arriveSameCountry() {
        for(int i=0; i<1000; i++) {
            Player p = new Player(new Position(cityB, cityD, 0),250);
            
            game.getRandom().setSeed(i);
            int bonus = country1.bonus(100);
            
            Random r = country1.getGame().getRandom();
            int moneySpent = r.nextInt(p.getMoney()+bonus+1);
            
            game.getRandom().setSeed(i);
            assertEquals(cityD.arrive(p), bonus-moneySpent);
            assertEquals(cityD.getValue(),100-bonus+moneySpent);
            cityD.reset();
        }
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
