import java.util.*;
public class HitTheBricks implements PlayerStrategy
{
    int buildings = 0;
    public SOC.Junction placeFirstSettlement(BoardInterface b)
    {
        /*return b.availableJunctions(SOC.resource.ORE).get(0) for example will return
        *the first Junction on the board that is bordering ORE.*/
        double max = 0;
        int maxID = 0;
        for(int i = 0; i < scoreJunctions(b).length; i++) //go through all scored junctions
        {
            if(scoreJunctions(b)[i] > max)
            {
                max = scoreJunctions(b)[i]; //set max equal to highest scored junction
                maxID = i; //set maxID equal to index of junction
            }
        }
        return b.availableJunctions().get(maxID);
    }
    public SOC.Junction placeFirstRoad(BoardInterface b)
    {
        return b.availableRoads().get(0);
    }
    public SOC.Junction placeSecondSettlement(BoardInterface b)
    {
        double max = 0;
        int maxID = 0;
        for(int i = 0; i < scoreJunctions(b).length; i++) //go through all scored junctions
        {
            if(scoreJunctions(b)[i] > max)
            {
                max = scoreJunctions(b)[i]; //set max equal to highest scored junction
                maxID = i; //set maxID equal to index of junction
            }
        }
        return b.availableJunctions().get(maxID);
    }
    public SOC.Road placeSecondRoad(BoardInterface b)
    {
        return b.availableRoads().get(0);
    }
    public void takeTurn(BoardInterface b)
    {
        double max = 0;
        int maxID = 0;
        prioritizeResources(b);
        if (b.availableRoads().size() > 0)
            b.build(b.availableRoads().get(0));
        if (b.availableJunctions().size() > 0)
        {
            for(int i = 0; i < scoreJunctions(b).length; i++) //go through all scored junctions
            {
                if(scoreJunctions(b)[i] > max)
                {
                    max = scoreJunctions(b)[i]; //set max equal to highest scored junction
                    maxID = i; //set maxID equal to index of junction
                }
            }
            b.build(b.availableJunctions().get(maxID));
            buildings++;
           
        }
    }
    public void prioritizeResources(BoardInterface b)
    {
        if(buildings < 5)
        {
            if(b.resourceCount(SOC.resource.SHEEP) > 4 && b.resourceCount(SOC.resource.WHEAT) < 2)
            {
                b.trade(SOC.resource.SHEEP, SOC.resource.WHEAT);
            }
            if(b.resourceCount(SOC.resource.WHEAT) > 4 && b.resourceCount(SOC.resource.BRICK) < 2)
            {
                b.trade(SOC.resource.WHEAT, SOC.resource.BRICK);
            }
            if(b.resourceCount(SOC.resource.BRICK) > 4 && b.resourceCount(SOC.resource.WOOD) < 2)
            {
                b.trade(SOC.resource.BRICK, SOC.resource.WOOD);
            }
            if(b.resourceCount(SOC.resource.WOOD) > 4 && b.resourceCount(SOC.resource.SHEEP) < 2)
            {
                b.trade(SOC.resource.WOOD, SOC.resource.SHEEP);
            }
        }
        if(buildings > 4 && buildings < 13)
        {
            if(b.resourceCount(SOC.resource.SHEEP) > 4 && b.resourceCount(SOC.resource.WHEAT) < 2)
            {
                b.trade(SOC.resource.SHEEP, SOC.resource.WHEAT);
            }
            if(b.resourceCount(SOC.resource.WHEAT) > 4 && b.resourceCount(SOC.resource.BRICK) < 2)
            {
                b.trade(SOC.resource.WHEAT, SOC.resource.BRICK);
            }
            if(b.resourceCount(SOC.resource.BRICK) > 4 && b.resourceCount(SOC.resource.WOOD) < 2)
            {
                b.trade(SOC.resource.BRICK, SOC.resource.WOOD);
            }
            if(b.resourceCount(SOC.resource.WOOD) > 4 && b.resourceCount(SOC.resource.ORE) < 2)
            {
                b.trade(SOC.resource.WOOD, SOC.resource.ORE);
            }
            if(b.resourceCount(SOC.resource.ORE) > 4 && b.resourceCount(SOC.resource.SHEEP) < 2)
            {
                b.trade(SOC.resource.ORE, SOC.resource.SHEEP);
            }
        }
        if(buildings > 12)
        {
            if(b.resourceCount(SOC.resource.SHEEP) > 4)
            {
                b.trade(SOC.resource.SHEEP, SOC.resource.BRICK);
            }
            if(b.resourceCount(SOC.resource.WHEAT) > 4)
            {
                b.trade(SOC.resource.WHEAT, SOC.resource.BRICK);
            }
            if(b.resourceCount(SOC.resource.ORE) > 4)
            {
                b.trade(SOC.resource.ORE, SOC.resource.BRICK);
            }
            if(b.resourceCount(SOC.resource.WOOD) > 4)
            {
                b.trade(SOC.resource.WOOD, SOC.resource.BRICK);
            }
        }
    }
    public int placeRobber()
    {
        return 5; // return tile to place robber
    }
    public int stealResource(String[] names)
    {
        return 0; // return which player to steal from
    }
    public double[] scoreJunctions(BoardInterface b)
    {
        ArrayList<SOC.Junction> availableJunctions = b.availableJunctions();
        double junctionScores[] = new double[availableJunctions.size()];
        for(int i = 0; i < availableJunctions.size(); i++)
        {
            for(int j = 0; j < availableJunctions.get(i).address.length; j++)
            {
                switch(b.tileNumber(availableJunctions.get(i).address[j]))
                {
                    case 0:
                    junctionScores[i] = junctionScores[i];
                    break;
                    case 2: case 12:
                    junctionScores[i] += 1;
                    break;
                    case 3: case 11:
                    junctionScores[i] += 2;
                    break;
                    case 4: case 10:
                    junctionScores[i] += 3;
                    break;
                    case 5: case 9:
                    junctionScores[i] += 4;
                    break;
                    case 6: case 8:
                    junctionScores[i] += 5;
                    break;
                }
                switch(b.tileResource(availableJunctions.get(i).address[j]).ordinal())
                {
                    case 2: //sheep
                    junctionScores[i] += 2;
                    break;
                    case 3: //ore
                    junctionScores[i] += 1;
                    break;
                    case 4: //wood
                    junctionScores[i] += 3;
                    break;
                    case 5: //wheat
                    junctionScores[i] += 2;
                    break;
                    case 6: //brick
                    junctionScores[i] += 10;
                    break;
                }
            }
        }
        return junctionScores;
    }
}

