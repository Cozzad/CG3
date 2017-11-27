import java.util.*;
/**
 * The Country class describes a country with a particular name. The country consists of 
 * a network of cities and roads that connects these cities. Furthermore the 
 * country is storing a game objekt reference.
 *
 * @author Michael P & Alexander
 * @version 2
 */
public class Country
{
    /** The name of the country */
    private String name;
    /** A Map of the cities and lists of the roads connected to each city */
    private Map<City,List<Road>> network;
    /** Reference to a game objekt */
    private Game game;
    
    /**
     * Constructor for objects of class Country.
     * @param name The name of the country.
     * @param network A map containing lists of roads of the country, each road list is mapped to
     * a particular city.
     */
    public Country(String name, Map<City,List<Road>> network) {
        this.name = name;
        this.network = network;
    }

    /**
     * Returns the name of the country.
     * @return Returns a String of the name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the network of the country.
     * 
     * @return Returns a map containing the cities and roads of the country.
     */
    public Map<City,List<Road>> getNetwork() {
        return network;
    }
    
    /**
     * Returns a list of all the roads that starts in city c.
     * The method uses the city, c, as the key to get a list of all the roads connected
     * to the city. It then runs through all the roads of the list and adds the roads, 
     * that starts in the city, c, to a new list. This list is then returned at the end
     * of the call.
     * 
     * @param c The city of which we want the roads to start in.
     * @return Returns a list of the roads starting in city c.
     */
    public List<Road> getRoads(City c) {
        ArrayList<Road> roads = new ArrayList<>();
        if(network.containsKey(c)) {
            for(Road r : network.get(c)) {
                if(r.getFrom().equals(c)) {
                    roads.add(r);
                }
            }
        }
        return roads;
    }
    
    /**
     * Returns a sorted list of all the cities in the country.
     * Uses the keySet method of Maps to get a list of the cities in the country, it 
     * then sorts the list and returns it at the end of the call.
     * 
     * @return Returns a list of the cities of the country.
     */
    public List<City> getCities() {
        ArrayList<City> cities = new ArrayList<>(network.keySet());
        Collections.sort(cities);
        return cities;
    }
    
    /**
     * Returns the city with the given name, if it exists.
     * 
     * @param name The name of the wanted city.
     * @return Returns a city objekt, if there is a city with the given name, if
     * there is no city with the given name in the country, the method returns null. 
     */
    public City getCity(String name) {
        ArrayList<City> cities = new ArrayList<>(network.keySet());
        for(City c : cities) {
            if(c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }
    
    /**
     * Resets all the cities of the country.
     * Calls the keySet method of the Map, network, to get a set of all the cities in the country. 
     * The set is then run through, using a stream, calling the reset method of each city. 
     */
    public void reset() {
        network.keySet().stream().forEach(c -> c.reset());
    }
    
    /**
     * Calculates the size of the bonus the player recieves.
     * @param value The value used as a basis to calculate the bonus. 
     * @return Returns 0 if the value provided, as the parameter, is 0, else returns a random value between
     * 1 and the value(inclusive).
     */
    public int bonus(int value) {
        if(value==0) {
            return 0;
        }
        return game.getRandom().nextInt(value+1);
    }
    
    /**
     * Adds roads between city a and city b of a given length.
     * The method adds a road, to the list of roads connected to city a, from city a to city b 
     * if the city is contained within the country. The same analogy is used for city b.
     * @param a One of the two City objekts being connected by the new road.
     * @param b One of the two City objekts being connected by the new road.
     * @param length The length of the road being "constructed".
     */
    public void addRoads(City a, City b, int length) {
        if(network.containsKey(a)) {
            Road r = new Road(a, b, length);
            network.get(a).add(r);
        }
        if(network.containsKey(b)) {
            Road r = new Road(b, a, length);
            network.get(b).add(r);
        }
    }
    
    /**
     * Returns the posistion of a city.
     * @param city The city of which the posistion is wanted.
     * @return Returns a posistion objekt, describing the posistion of the city, given in the parameter.
     */
    public Position position(City city) {
        Position p = new Position(city,city,0);
        return p;
    }
    
    /**
     * Prepares the player to travel.
     * @param from The city from which the player is traveling.
     * @param to The city the player is traveling to.
     * @return Returns a posistion objekt describing the current posistion of the player 
     * and where the player is going.
     */
    public Position readyToTravel(City from, City to) {
        if(from.equals(to)) {
            return position(from);
        }
        if(!network.containsKey(from)) {
            return position(from);
        }
        for(Road r : network.get(from)) {
            if(r.getTo().equals(to)) {
                Position p = new Position(from,to,r.getLength());
                return p;
            }
        }
        return position(from);
    }

    /**
     * Returns the game object of the country.
     * @return A game object.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Sets the game field varible of the country.
     * @param game A game object.
     */
    public void setGame(Game game) {
        this.game = game;
    }
    
    /**
     * Returns true if the two objects are the same and false if they are not.
     * Overrides the inherited equals method.
     * @param b The object compared to the current object.
     * @return Returns a boolean value depending on the equality of the two objects.
     */
    @Override
    public boolean equals(Object b) {
        if(this==b) {
            return true;
        }
        if(b==null) {
            return false;
        }
        if(getClass() != b.getClass()) {
            return false;
        }
        Country bc = (Country) b;
        return name.equals(bc.name);
    }
    
    /**
     * Overrides the inherited hashcode method.
     * @return Returns an integer hashcode for the object.
     */
    @Override
    public int hashCode() {
        return 17*name.hashCode();
    }
}
