/**
 * The City class describes a city with a particular name, this city got a value and the city is contained within a country.
 *
 * @author Michael P & Alexander
 * @version 2
 */
public class City implements Comparable<City>
{
    /** The name of the city */
    private String name;
    /** The value of the city */
    private int value;
    /** The initial value of the city */
    private int initialValue;
    /** The country within which the city is contained */
    private Country country;
    
    /**
     * Constructor for objects of class City.
     * @param name The name of the city.
     * @param value The value of the city. Both the value and initialValue field are set to this value when the 
     * constructor is called.
     * @param country The country within which the city is contained.
     */
    public City(String name, int value, Country country)
    {
        this.name = name;
        this.value = value;
        initialValue = value;
        this.country = country;
    }

    /**
     * Returns the name of the city.
     * @return Returns a String of the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current value of the city.
     * @return An integer value describing the value.
     */
    public int getValue()   {
        return value;
    }
    
    /**
     * Returns the country within which the city is located.
     * @return A country object.
     */
    public Country getCountry()   {
        return country;
    }
    
    /**
     * Changes the value of the city by an amount, set by a parameter.
     *
     * @param amount The amount that the value field should be changed.
     */
    public void changeValue(int amount) {
        value += amount;
    }
    
    /**
     * Resets the value field of a city to its initialvalue, stored in the initialvalue field.
     */
    public void reset() {
        value = initialValue;
    }
    
    /**
     * The compareTo method, inherited from the interface Comparable. 
     * Comparing two city objects by their name, using the compareTo method of the 
     * String class. 
     * 
     * @param other Another City object to which the current object is compared.
     * @return The result of comparing the two cities as an integer value.
     */
    public int compareTo(City other)  {
        return getName().compareTo(other.getName());
    }
    
    /**
     * Returns the value of the bonus for arriving to the city.
     * Uses the value of the city as a parameter to call the bonus method of the 
     * country within which the city is contained.
     * The calculated bonus is then deducted from the value of the city, thus resulting
     * in the city having a new, lesser, value. At the end, the method returns the value
     * of the calculated bonus.
     * 
     * @return Returns the bonus of arriving to the city as an integer value.
     */
    public int arrive() {
        int b = country.bonus(value);
        if(b>=0) {
            value -= b;
        }
        return b;
    }
    
    /**
     * The method calls the arrive() method. This method is used, to allow the subclass to override the arrive 
     * method with a parameterised arrive method.
     * 
     * @param p A player object, used in the subclass to call methods of the player Class.
     * @return Returns the bonus of arriving to the city as an integer value.
     */
    public int arrive(Player p) {
        return arrive();
    }
    
    /**
     * Returns true if the two objects are the same and false if they are not.
     * Overrides the inherited equals method.
     * 
     * @param other Another City object to compare with.
     * @return The result of comparing two cities with each other.
     */
    public boolean equals(Object other) {
        if (this==other)    {
            return true;
        }
        if (other==null)    {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        City city = (City) other;
        return name.equals(city.name) && country.equals(city.country);
        
    }
    
    /**
     * Overrides the inherited hashCode method.
     * 
     * @return Returns an int representing the hashCode.
     */
    public int hashCode()   {
        return 17*name.hashCode()+13*country.hashCode();
    }
}
