
/**
 * The BorderCity class describes a city that is located at a border between two countries.
 *
 * @author Michael P & Alexander
 * @version 1
 */
public class BorderCity extends City
{

    /**
     * Constructor for objects of class BorderCity.
     * The constructor calls the constructor of its super class, City, with the provided parameters.
     * 
     * @param name The name of the city.
     * @param value The value of the city. 
     * @param country The country within which the city is contained.
     */
    public BorderCity(String name, int value, Country country) {
        super(name,value,country);
    }

    /**
     * Returns the bonus of arriving to a city, minus the toll of arriving to the city.
     * If the player crosses a border when going to the city, the player has to pay a 
     * toll, which is a percentage of the players current fortune.
     * 
     * @param p A player object, from which we get the value of the players fortune.
     * @return Returns the bonus of arriving to the city minus the potential toll.
     */
    @Override
    public int arrive(Player p) {
        int b = getCountry().bonus(getValue());
        int toll = 0;
        if(b>=0) {
            changeValue(-b);
        }
        if(getCountry() != p.getCountryFrom()) {
            toll = getCountry().getGame().getSettings().getTollToBePaid(); //int [0,100]
            toll = (toll*p.getMoney())/100;
            changeValue(toll);
        }
        return b-toll;
    }
}
