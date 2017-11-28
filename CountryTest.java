import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

/**
 * The test class CountryTest.
 *
 * @author  Michael P & Alexander
 * @version 1
 */
public class CountryTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;

    /**
     * Default constructor for test class CountryTest
     */
    public CountryTest()
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
    public void addRoads() {
        //Only one road created if one city is in the country.
        assertTrue(country1.getNetwork().get(cityA).size()==3);
        assertTrue(country2.getNetwork().get(cityG).size()==2);
        country1.addRoads(cityA,cityG,5);
        assertTrue(country1.getNetwork().get(cityA).size()==4);
        assertTrue(country2.getNetwork().get(cityG).size()==2);
        country2.addRoads(cityA,cityG,5);
        assertTrue(country1.getNetwork().get(cityA).size()==4);
        assertTrue(country2.getNetwork().get(cityG).size()==3);
        //Two roads created if both cities are in the country.
        assertTrue(country1.getNetwork().get(cityB).size()==2);
        assertTrue(country1.getNetwork().get(cityC).size()==3);
        country1.addRoads(cityB,cityC,5);
        assertTrue(country1.getNetwork().get(cityB).size()==3);
        assertTrue(country1.getNetwork().get(cityC).size()==4);
        //No roads created if the cities are not in the country.
        assertTrue(country2.getNetwork().get(cityE).size()==3);
        assertTrue(country2.getNetwork().get(cityF).size()==3);
        country1.addRoads(cityE,cityF,6);
        assertTrue(country2.getNetwork().get(cityE).size()==3);
        assertTrue(country2.getNetwork().get(cityF).size()==3);
        
        //Null
        assertTrue(country2.getNetwork().get(cityF).size()==3);
        country2.addRoads(null,cityF,6);
        assertTrue(country2.getNetwork().get(cityF).size()==4);
        //Null
        assertTrue(country2.getNetwork().get(cityE).size()==3);
        country2.addRoads(cityE,null,6);
        assertTrue(country2.getNetwork().get(cityE).size()==4);
    }

    @Test
    public void reset()
    {
        cityA.arrive(); cityA.arrive(); cityA.arrive();
        cityE.arrive(); cityE.arrive(); cityE.arrive();
        int valueE = cityE.getValue();
        country1.reset();
        assertEquals(80, cityA.getValue());
        assertEquals(valueE, cityE.getValue());
    }
    
    @Test
    public void bonus()
    {
        for(int seed=0; seed<1000; seed++) {
            game.getRandom().setSeed(seed);
            int sum = 0;
            Set<Integer> values = new HashSet<>();
            for(int i=0; i<10000; i++) {
                int bonus = country1.bonus(80);
                assertTrue(0<=bonus && bonus <=80); //
                sum += bonus;
                values.add(bonus);
            }
            assertTrue(393000<sum && sum<407000);
            assertEquals(values.size(),81);
            
            game.getRandom().setSeed(seed);
            int sum2 = 0;
            Set<Integer> values2 = new HashSet<>();
            for(int i=0; i<10000; i++) {
                int bonus = country1.bonus(1);
                assertTrue(0<=bonus && bonus <=1); //
                sum2 += bonus;
                values2.add(bonus);
            }
            assertTrue(4500<sum2 && sum2<5500);
            assertEquals(values2.size(),2);
            
            game.getRandom().setSeed(seed);
            int sum3 = 0;
            Set<Integer> values3 = new HashSet<>();
            for(int i=0; i<10000; i++) {
                int bonus = country1.bonus(0);
                assertTrue(bonus==0);
                sum3 += bonus;
                values3.add(bonus);
            }
            assertTrue(sum3==0);
            assertEquals(values3.size(),1);
        }
    }

    @Test
    public void getRoads()
    {
        //City not in the country returns an empty list.
        List<Road> noroads = new ArrayList<>();
        assertEquals(noroads, country1.getRoads(cityG));
        //List of roads starting in the city returned.
        List<Road> roads = new ArrayList<>(country1.getRoads(cityA));
        assertEquals(roads.get(0).getFrom(), cityA);
        assertEquals(roads.get(1).getFrom(), cityA);
        assertEquals(roads.get(2).getFrom(), cityA);
        assertEquals(roads.size(),3);
        //4 calls to addRoads with cityF in setUp, but only 3 roads starting in cityF
        roads = new ArrayList<>(country2.getRoads(cityF));
        assertEquals(roads.get(0).getFrom(), cityF);
        assertEquals(roads.get(1).getFrom(), cityF);
        assertEquals(roads.get(2).getFrom(), cityF);
        assertEquals(roads.size(),3);
        
        roads = new ArrayList<>(country2.getRoads(null));
        assertEquals(noroads, roads);
    }

    @Test
    public void getCities()
    {
        //No cities in the country Returns an empty list.
        List<City> cities = new ArrayList<>();
        Map<City, List<Road>> networkEmpty = new HashMap<>();
        Country countryEmpty = new Country("CountryEmpty", networkEmpty);
        assertTrue(countryEmpty.getCities().size()==0);
        assertEquals(cities,countryEmpty.getCities());
        //Sorted list returned.
        cities = new ArrayList<>(country1.getNetwork().keySet());
        Collections.sort(cities);
        assertEquals(cities,country1.getCities());
    }

    @Test
    public void getCity()
    {
        assertEquals(null, country1.getCity("City G"));
        assertEquals(cityA, country1.getCity("City A"));
        assertEquals(null, country1.getCity("City doNotExist"));
    }

    @Test
    public void position()
    {
        Position p = country1.position(cityA);
        assertTrue(p.getFrom()==cityA);
        assertFalse(p.getFrom()==cityB);
        assertTrue(p.getTo()==cityA);
        assertTrue(p.getDistance()==0);
        //City in another country.
        p = country1.position(cityE);
        assertTrue(p.getFrom()==cityE);
        assertFalse(p.getFrom()==cityA);
        assertTrue(p.getTo()==cityE);
        assertTrue(p.getDistance()==0);
        
        p = country1.position(null);
        assertTrue(p.getFrom()==null);
        assertTrue(p.getTo()==null);
        assertTrue(p.getDistance()==0);
    }

    @Test
    public void readyToTravel()
    {
        //From and to two cities in the same country.
        Position p = country1.readyToTravel(cityA, cityB);
        assertEquals(p.getFrom(), cityA);
        assertEquals(p.getTo(), cityB);
        assertEquals(p.getDistance(),4);
        assertEquals(p.getTotal(),4);
        //From.equals(To)
        p = country1.readyToTravel(cityA, cityA);
        assertEquals(p.getFrom(), cityA);
        assertEquals(p.getTo(), cityA);
        assertEquals(p.getDistance(),0);
        assertEquals(p.getTotal(),0);
        //No direct path from from to to.
        p = country1.readyToTravel(cityB, cityC);
        assertEquals(p.getFrom(), cityB);
        assertEquals(p.getTo(), cityB);
        assertEquals(p.getDistance(),0);
        assertEquals(p.getTotal(),0);
        //From not in the country.
        p = country1.readyToTravel(cityG, cityA);
        assertEquals(p.getFrom(), cityG);
        assertEquals(p.getTo(), cityG);
        assertEquals(p.getDistance(),0);
        assertEquals(p.getTotal(),0);
        
        //To is in another country
        p = country1.readyToTravel(cityD, cityF);
        assertEquals(p.getFrom(), cityD);
        assertEquals(p.getTo(), cityF);
        assertEquals(p.getDistance(),3);
        assertEquals(p.getTotal(),3);
    }
    
    @Test
    public void equals()    {
        assertTrue(country1.equals(country1));
        assertFalse(country1.equals(null));
        assertFalse(country1.equals(country2));
        //Symmetric 
        Country c = country1;
        assertTrue(country1.equals(c));
        assertTrue(c.equals(country1));
        //Transitivity
        Country z = country1;
        assertTrue(c.equals(z));
        assertTrue(z.equals(country1));
        assertTrue(country1.equals(z));
        //Different classes.
        assertFalse(country1.equals(cityA));
        assertFalse(country2.equals(cityB));
        
        /** 
         * From lecture: for en mængde vil et kald add(elem) kun tilføje elementet, 
         * hvis der ikke eksisterer et element e i mængden, så elem.equals(e) er sand.
         */
        HashSet<Country> countries = new HashSet<>();
        countries.add(country1);
        countries.add(country2);
        countries.add(country1);
        assertTrue(countries.size()==2);
    }
    
    @Test
    public void hashCodeTest()  {
        assertFalse(country1.hashCode()==country2.hashCode());
        assertFalse(country1.hashCode()==0);
        
        //Konsistency
        Country c = country1;
        assertTrue(country1.equals(c));
        assertTrue(country1.hashCode()==c.hashCode());
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