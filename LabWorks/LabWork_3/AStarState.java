import java.util.*;
/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;
//добавила два поля для открытых и закрытых вершин
    private Map<Location, Waypoint> openWaypoints = new HashMap<Location, Waypoint>();
    private Map<Location, Waypoint> closeWaypoints = new HashMap<Location, Waypoint>();

    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint()
    {
//если кол-во открытых вершин равно 0
        if(numOpenWaypoints() == 0)
            return null;
        Set keys = openWaypoints.keySet();//множество ключей
        Iterator i = keys.iterator();//объявление итератора
        float min = Float.MAX_VALUE;
        Waypoint minWaypoint = null;
        //перебираем набор открытых вершин
        while(i.hasNext()){
            Location loc = (Location) i.next();//текущее местоположение
//если у вершины для данного местоположения общая стоимость меньше минимальной
//то она становится минимальной
            if(openWaypoints.get(loc).getTotalCost() <  min){
                min = openWaypoints.get(loc).getTotalCost();
                minWaypoint = openWaypoints.get(loc);
            }
        }
    //возвращаем вершину с минимальной общей стоимостью
        return minWaypoint;
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        Location loc = newWP.getLocation();//местоположение текущей вершины
        //если для такого местоположения уже есть вершина в наборе откр.вершин
        if(openWaypoints.containsKey(loc)){
            //сравниваем стоимость для текущей и новой вершин для данного местоположения
            if(openWaypoints.get(loc).getPreviousCost() >= newWP.getPreviousCost()){
            //записываем новую вершину
                openWaypoints.put(loc, newWP);
                return true;
            }
            return false;
        //если нет
        }else{
            //добавляем
            openWaypoints.put(loc, newWP);
            return true;
        }
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()
    {
        // возвращает кол-во точек в наборе открытых вершин
        return openWaypoints.size();
    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)
    {
        // удаляем вершину, соотв.указанному местоположению из набора открытых вершин
        Waypoint waypoint = openWaypoints.remove(loc);
        //добавляем эту вершину в набор закрытых
        closeWaypoints.put(loc, waypoint); 
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
    {
        //возвращаем есть ли в наборе закрытых вершин вершина для данного местоположения
        return closeWaypoints.containsKey(loc);
    }
}
