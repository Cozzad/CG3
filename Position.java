/**
 * The Position class is a way to keep track of players, where they are and their distance to the city they are travelling to.
 * 
 * @author Michael P. & Alexander
 * @version 2
   */
public class Position
{
    /** 
     * References to the two cities that the player is currently between. 
     * If the player is in a city, these two references will point to the same city.  
    */
    private City from;
    private City to;
    /** Distance to the city the player is traveling to */
    private int distance;
    /** Total distance between the two cities. */
    private int total;
    
    /**
     * Creates a position object, to determine the position of the player.  
     * 
     * @param from The city the player is travelling from.
     * @param to The city the player is travelling to.
     * @param distance Initially the distance between the two cities, 
     * both the distance and the total field gets assigned to this value.
    */
    public Position(City from, City to, int distance)
    {
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.total = distance;
    }

    /**
     * Returns the city, from which the player is going.
     * @return A city objekt.
     */
    public City getFrom()   {
        return from;
    }
    
    /**
     * Returns the city, to which the player is going.
     * @return A city objekt.
     */
    public City getTo()   {
        return to;
    }
    
    /**
     * Returns the current distance to the "to" city.
     * @return An integer value describing the distance.
     */
    public int getDistance()    {
        return distance;
    }
    
    /**
     * Returns the total distance between the two cities, from and to.
     * @return An integer value describing the total distance.
     */
    public int getTotal()   {
        return total; 
    }
    
    /**
    * Checks if the player is currently in a city, where the distance would be 0.   
    * 
    * @return Result of the player position check as a boolean value.
    */
    public boolean hasArrived() {
        if (distance == 0)  return true;
        return false;
    }
    
    /**
    * Moves the player along the road between two cities, for each move the distance gets reduced by 1.
    * If the player is currently in the city he or she is going to, the distance is already 0 
    * and the method returns a false statement, as the player didn't execute the command. 
    * If the player moves, "true" is returned.
    * 
    * @return Return depends on what happened to the player. If the player moved, 
    * returns true, otherwise returns false.
    */
    public boolean move()   {
        if (distance > 0)   {
            distance-=1;
            return true;
        }
        return false;
    }
    
    /**
    * Makes the player do a 180 degree turn on the current road.
    * This is achived by swapping the "to" and "from" cities with one another and changing
    * the distance from the remaining distance, to the total distance minus this remaining distance.
    */
    public void turnAround()    {
        City newTo = from;
        from = to;
        to = newTo;
        distance = total - distance;
    }
}
