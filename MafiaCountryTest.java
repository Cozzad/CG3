import java.util.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MafiaCountryTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MafiaCountryTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;

    /**
     * Default constructor for test class CountryTest
     */
    public MafiaCountryTest()
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
        country2 = new MafiaCountry("Country 2", network2);
        country1.setGame(game);
        country2.setGame(game);

        // Create Cities
        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new City("City C", 40, country1);
        cityD = new City("City D", 100, country1);
        cityE = new City("City E", 50, country2);
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
        country1.addRoads(cityA, cityB, 4);
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
    public void constructor() 
    { 
        assertEquals(country1.getName(),"Country 1");
        assertEquals(country2.getName(),"Country 2");
        assertEquals(country2.getGame(),game);
        assertEquals(country1.getGame(),game);
        assertTrue(country1.getNetwork().size()==4);
        assertTrue(country1.getNetwork().get(cityA).size()==3);
        assertTrue(country2.getNetwork().get(cityF).size()==3);
    }
    
    @Test
    public void bonus()
    {
        for(int seed=0; seed<1000; seed++) {
            //Called with value 80 as parameter.
            game.getRandom().setSeed(seed);
            int robs = 0;
            int loss = 0;
            int sum = 0;
            Set<Integer> positiveValues = new HashSet<>();
            Set<Integer> values = new HashSet<>();
            for(int i=0; i<50000; i++) {
                int bonus = country2.bonus(80);
                if (bonus < 0)  {
                    robs++;
                    assertTrue(bonus > -51 && bonus < -9);
                    loss -= bonus;
                    values.add(-bonus);
                } else {
                    assertTrue(bonus <=80); //
                    sum += bonus;
                    positiveValues.add(bonus);
                }
            }
            assertTrue(robs > 9700 && robs < 10300);
            assertTrue(loss > 291000 && loss < 309000);
            assertEquals(values.size(), 41);
            
            assertTrue(393000<sum/4 && sum/4<407000);
            assertEquals(positiveValues.size(),81);
            
            //Called with value 0 as parameter.
            game.getRandom().setSeed(seed);
            robs = 0;
            loss = 0;
            sum = 0;
            positiveValues = new HashSet<>();
            values = new HashSet<>();
            for(int i=0; i<50000; i++) {
                int bonus = country2.bonus(0);
                if (bonus < 0)  {
                    robs++;
                    assertTrue(bonus > -51 && bonus < -9);
                    loss -= bonus;
                    values.add(-bonus);
                } else {
                    assertTrue(bonus == 0); //
                    sum += bonus;
                    positiveValues.add(bonus);
                }
            }
            assertTrue(robs > 9700 && robs < 10300);
            assertTrue(loss > 291000 && loss < 309000);
            assertEquals(values.size(), 41);
            
            assertTrue(sum==0);
            assertEquals(positiveValues.size(),1);
            
            //Called with value 1 as parameter.
            game.getRandom().setSeed(seed);
            robs = 0;
            loss = 0;
            sum = 0;
            positiveValues = new HashSet<>();
            values = new HashSet<>();
            for(int i=0; i<50000; i++) {
                int bonus = country2.bonus(1);
                if (bonus < 0)  {
                    robs++;
                    assertTrue(bonus > -51 && bonus < -9);
                    loss -= bonus;
                    values.add(-bonus);
                } else {
                    assertTrue(bonus < 2); //
                    sum += bonus;
                    positiveValues.add(bonus);
                }
            }
            assertTrue(robs > 9700 && robs < 10300);
            assertTrue(loss > 291000 && loss < 309000);
            assertEquals(values.size(), 41);
            
            assertTrue(4500<sum/4 && sum/4<5500);
            assertEquals(positiveValues.size(),2);
        }   
    }
}
