package org.aec.facade;

import java.util.*;

public class Building
{
    public enum SIDE{MAIN_BUILDING_NORTH, MAIN_BUILDING_WEST, MAIN_BUILDING_SOUTH, MAIN_BUILDING_EAST, MAIN_BUILDING_SOUTH_STREET_LEVEL, FUTURELAB_SOUTH, FUTURELAB_EAST, FUTURELAB_NORTH};

    public Building()
    {
        sides = new ArrayList<Side>();
        nrRows = 0;
        nrColumns = 0;
        sides.add(new SideMainBuildingNorth());
        sides.add(new SideMainBuildingWest());
        sides.add(new SideMainBuildingSouth());
        sides.add(new SideMainBuildingEast());
        sides.add(new SideMainBuildingSouthStreetLevel());
        sides.add(new SideFuturelabSouth());
        sides.add(new SideFuturelabEast());
        sides.add(new SideFuturelabNorth());
        for(Iterator iterator = sides.iterator(); iterator.hasNext();)
        {
            Side side = (Side)iterator.next();
            if(side.getEndRow() + 1 > nrRows)
                nrRows = side.getEndRow() + 1;
            if(side.getNrColumns() > nrColumns)
                nrColumns = side.getNrColumns();
        }

    }

    public Side getSide(SIDE s)
    {
        return (Side)sides.get(s.ordinal());
    }

    public List getSides()
    {
        return sides;
    }

    public int getNrRows()
    {
        return nrRows;
    }

    public int getNrColumns()
    {
        return nrColumns;
    }

    private List sides;
    private int nrRows;
    private int nrColumns;
}
