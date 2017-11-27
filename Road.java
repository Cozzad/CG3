/**
 * The Road class describes a road starting in a city and ending in another city, 
 * the road, between these two cities, has a specific length. 
 * Furthermore a road is one-directional in that it only goes from one city(a) to another(b), 
 * therefore there does not nessesarily exist a road from city b to city a.
 *
 * @author Michael P & Alexander
 * @version 2
 */
public class Road implements Comparable<Road>
{
    /** References to the two cities connected by the road. */
    private City from;
    private City to;
    /** The length between the two cities. */
    private int length;
    
    /**
     * Constructor for objects of class Road.
     * @param from The city where the road starts.
     * @param to The city where the road ends.
     * @param length An integer value of the length of the road between the two cities.
     */
    public Road(City from, City to, int length)
    {
        this.from = from;
        this.to = to;
        this.length = length;
    }

    /**
     * Returns the city, from which the road is going.
     * @return A city objekt.
     */
    public City getFrom() {
        return from;
    }
    
    /**
     * Returns the city, to which the road is going.
     * @return A city objekt.
     */
    public City getTo()   {
        return to;
    }
    
    /**
     * Returns the length of the road between the two cities, from and to.
     * @return An integer value describing the length of the road.
     */
    public int getLength()  {
        return length;
    }
    
    /**
     * The compareTo method, inherited from the interface Comparable. 
     * Comparing two Road objekts by their start(from) cities. 
     * If the start cities are equal, determined by the compareTo method of City, 
     * then the two roads are compared by their end(to) cities, again determined by
     * the compareTo method of City.
     * 
     * @param other Another Road objekt to which the current objekt is compared.
     * @return The result of comparing the two roads as an integer value.
     */
    public int compareTo(Road other)    {
        if (getFrom().compareTo(other.getFrom())==0)    {
            return getTo().compareTo(other.getTo());
        }
        return getFrom().compareTo(other.getFrom());
    }
}
