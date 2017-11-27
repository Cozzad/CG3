import java.util.*;
/**
 * The CapitalCity class describes a city that is the capital city of a country.
 * The class is a subclass of BorderCity, because all capital cities, in the north, are also bordercities.
 *
 * @author Michael P & Alexander
 * @version 1
 */
public class CapitalCity extends BorderCity
{

    /**
     * Constructor for objects of class CapitalCity.
     * The constructor calls the constructor of its super class, BorderCity, with the provided parameters.
     * 
     * @param name The name of the city.
     * @param value The value of the city. 
     * @param country The country within which the city is contained.
     */
    public CapitalCity(String name, int value, Country country) {
        super(name,value,country);
    }

    /**
     * Returns the bonus of arriving to a city, minus the toll of arriving to the city and minus the money spent in the city.
     * If the player crosses a border when going to the city, the player has to pay a 
     * toll, which is a percentage of the players current fortune. Furthermore, when a player
     * enters a capital city, the player uses a part of its fortune in the city, which is calculated 
     * using a random number between 0 and the players current value, both included.
     * 
     * @param p A player object, from which we get the value of the players fortune.
     * @return Returns the bonus of arriving to the city minus the potential toll and the money spent in the city.
    */
    @Override
    public int arrive(Player p) {
        int bonustoll = super.arrive(p);
        Random r = getCountry().getGame().getRandom();
        int moneySpent = r.nextInt(p.getMoney()+bonustoll+1);  
        //[0,p.getmoney()+bonustoll] -  money is the players fortune (after the addition of bonus and reduction of potential toll).
        changeValue(moneySpent);
        return bonustoll-moneySpent;
    }
}
