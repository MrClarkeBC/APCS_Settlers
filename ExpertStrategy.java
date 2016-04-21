import java.util.*;

public class ExpertStrategy implements PlayerStrategy
{

    public SOC.Junction placeFirstSettlement(BoardInterface b)
    {
        int max = 0;
        int maxID = 0;
        int scores[] = scoreJunctions(b);
        for(int i = 0;i < scores.length;i++)
        {
            if(scores[i] > max)
            {
                max = scores[i];
                maxID = i;
            }
        }
        return b.availableJunctions().get(maxID);
    }

    public SOC.Junction placeFirstRoad(BoardInterface  b)
    {
        return b.availableRoads().get(0);
    }

    public SOC.Junction placeSecondSettlement(BoardInterface  b)
    {
        int max = 0;
        int maxID = 0;
        for(int i = 0;i < scoreJunctions(b).length;i++)
        {
            if(scoreJunctions(b)[i] > max)
            {
                max = scoreJunctions(b)[i];
                maxID = i;
            }
        }
        return b.availableJunctions().get(maxID);
    }

    public SOC.Road placeSecondRoad(BoardInterface  b)
    {
        return b.availableRoads().get(0);
    }

    public void takeTurn(BoardInterface  b)
    {
        if (b.availableRoads().size() > 0)
            b.build(b.availableRoads().get(0));
        if (b.availableJunctions().size() > 0)
            b.build(b.availableJunctions().get(0));
    }

    public void prioritizeResources(BoardInterface  b)
    {
        if(b.resourceCount(SOC.resource.SHEEP) > 3)
        {
            Random r = new Random();
            int n = r.nextInt(3);
            if(n == 0)
            {
                b.trade(SOC.resource.SHEEP, SOC.resource.BRICK);
            }
            if(n == 1)
            {
                b.trade(SOC.resource.SHEEP, SOC.resource.WOOD);
            }
            if(n == 2)
            {
                b.trade(SOC.resource.SHEEP, SOC.resource.ORE);
            }
        }
    }

    public int[] scoreJunctions(BoardInterface b)
    {
        ArrayList<SOC.Junction> availableJunctions = b.availableJunctions();
        int junctionScores[] = new int[availableJunctions.size()];
        boolean sheepFound = false;
        boolean wheatFound = false;
        boolean woodFound = false;
        boolean brickFound = false;
        boolean oreFound = false;

        for(int i = 0;i < availableJunctions.size();i++)
        {
            for(int j = 0;j < availableJunctions.get(i).address.length;j++)
            {
                switch(b.tileNumber(availableJunctions.get(i).address[j]))
                {
                    case 0:
                    junctionScores[i] -= 5;
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
                    case 2://Sheep
                    if(!sheepFound)
                    {
                        sheepFound = true;
                        junctionScores[i] += 2;
                    }
                    break;
                    case 3://Ore
                    if(!oreFound)
                    {
                        oreFound = true;
                        junctionScores[i] += 1;
                    }
                    break;
                    case 4://Wood
                    if(!woodFound)
                    {
                        woodFound = true;
                        junctionScores[i] += 4;
                    }
                    break;
                    case 5://Wheat
                    if(!wheatFound)
                    {
                        wheatFound = true;
                        junctionScores[i] += 3;
                    }
                    break;
                    case 6://Brick
                    if(!brickFound)
                    {
                        brickFound = true;
                        junctionScores[i] += 5;
                    }
                    break;
                    default:
                    junctionScores[i] -= 5;
                    break;
                }
            }
        }
        return junctionScores;
    }

    public int placeRobber()
    {
        return 5; // return tile to place robber
    }

    public int stealResource(String[] names)
    {
        return 0; // return which player to steal from
    }
}
