import java.util.*;

/**
 * The MafiaCountry class describes a country within which the player may be robbed.
 * The Class inherits all functionality, and its state, from the Country class and therefore has a particular name 
 * and consists of a network of cities and roads that connects these cities.
 *
 * @author Michael P & Alexander
 * @version 1
 */
public class MafiaCountry extends Country
{
    
    /**
     * Constructor for objects of class MafiaCountry.
     * The constructor calls the constructor of its super class, Country, with the provided parameters.
     * 
     * @param name The name of the country.
     * @param network A map containing lists of roads of the country, each road list is mapped to
     * a particular city.
     */
    public MafiaCountry(String name, Map<City,List<Road>> network)
    {
        super(name, network);
    }
    
    /**
     * Returns a positive bonus or a negative loss, depending on the risk of getting robbed.
     * The method gets a risk variable, provided by the Settings class, and compares this with 
     * a random integer between 1 and 100, both inclusive. If the risk is greater than, or equal to, the random 
     * integer, the method returns a loss, else it returns a positive bonus via a call to the bonus method in the 
     * super class, Country.
     * 
     * @param value The value used as a basis to calculate the bonus. 
     * @return Returns a random value between 1 and the value(inclusive) if the risk is lesser than the calculated
     * random variable. Otherwise returns a negative loss if the risk is greater than, or equal to, the calculated
     * random variable.
     */
    @Override
    public int bonus(int value)  {
        int risk = getGame().getSettings().getRisk();
        int robbedOrNot = getGame().getRandom().nextInt(100)+1;
        if (risk >= robbedOrNot) {
            return -getGame().getLoss();
        }
        return super.bonus(value);
    }
    
    
}
