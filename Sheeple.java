
public class Sheeple implements PlayerStrategy
{
    double m_bestpoints = 0;
    double m_temppoints = 0;
    SOC.Junction best;

    public SOC.Junction heuristicThis(BoardInterface b)
    {
        m_bestpoints = 0;
        m_temppoints = 0;
        best = b.availableJunctions().get(0);
        for(int i = 0; i < b.availableJunctions().size(); i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if (b.tileResource(b.availableJunctions().get(i).address[j]) == SOC.resource.BRICK)
                {
                    m_temppoints += 2.55;
                }   
                if (b.tileResource(b.availableJunctions().get(i).address[j]) == SOC.resource.ORE)
                {
                    m_temppoints += 2.41;
                }  
                if (b.tileNumber(b.availableJunctions().get(i).address[j]) == 6 ||
                b.tileNumber(b.availableJunctions().get(i).address[j]) == 8)
                {
                    m_temppoints += 1.69;
                } 
                if (b.tileResource(b.availableJunctions().get(i).address[j]) == SOC.resource.SHEEP)
                {
                    m_temppoints += 9.8;
                }
                if (b.tileResource(b.availableJunctions().get(i).address[j]) == SOC.resource.SHEEP ||
                b.tileResource(b.availableJunctions().get(i).address[j]) == SOC.resource.WHEAT ||
                b.tileResource(b.availableJunctions().get(i).address[j]) == SOC.resource.WOOD)
                {
                    m_temppoints += 1.89;
                } 
            }
            if (m_temppoints > m_bestpoints)
            {
                m_bestpoints = m_temppoints;
                best = b.availableJunctions().get(i);
            }            
            m_temppoints = 0;
        }
        return best;
    }

    public int myRoads(BoardInterface b)
    {
        int result = 0;
        for(int i = 0; i <37; i++)
        {
            for(int j = 0; j < 5; j ++)
            {
                if(!(b.OwnerName(i, SOC.location.values()[j], SOC.location.values()[j+1]).equals(null)))
                {
                    if (b.myName().equals(b.OwnerName(i, SOC.location.values()[j], SOC.location.values()[j+1])))
                    {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    public int mySettlements(BoardInterface b)
    {
        int result = 0;
        for(int i = 0; i <37; i++)
        {
            for(int j = 0; j < 6; j ++)
            {
                if(!(b.OwnerName(i, SOC.location.values()[j]).equals(null)))
                {
                    if (b.myName().equals(b.OwnerName(i, SOC.location.values()[j])))
                    {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    public SOC.Junction placeFirstSettlement(BoardInterface b)
    {
        return heuristicThis(b);
    }

    public SOC.Junction placeFirstRoad(BoardInterface b)
    {
        return b.availableRoads().get(0);
    }

    public SOC.Junction placeSecondSettlement(BoardInterface b)
    {
        return b.availableJunctions(SOC.resource.SHEEP).get(0);
    }

    public SOC.Road placeSecondRoad(BoardInterface b)
    {
        return b.availableRoads().get(0);
    }

    public void takeTurn(BoardInterface b)
    {   
        prioritizeResources(b);
        if (b.availableRoads().size() > 0 && b.availableJunctions().size() < 2)
            b.build(b.availableRoads().get(0));
        if (b.availableJunctions().size() > 0)    
            b.build(heuristicThis(b));
    }

    public void prioritizeResources(BoardInterface b)
    {
        int sheep = b.resourceCount(SOC.resource.SHEEP);
        int wood = b.resourceCount(SOC.resource.WOOD);
        int wheat = b.resourceCount(SOC.resource.WHEAT);
        int ore = b.resourceCount(SOC.resource.ORE);
        int brick = b.resourceCount(SOC.resource.BRICK);
        SOC.resource lowest = SOC.resource.SHEEP;
        if (wood < b.resourceCount(lowest)) lowest = SOC.resource.WOOD;
        if (brick < b.resourceCount(lowest)) lowest = SOC.resource.BRICK;
        if (ore < b.resourceCount(lowest)) lowest = SOC.resource.ORE;
        if (wheat < b.resourceCount(lowest)) lowest = SOC.resource.WHEAT;

        if(b.resourceCount(SOC.resource.SHEEP) > 4)
            b.trade(SOC.resource.SHEEP, lowest);
        if(b.resourceCount(SOC.resource.ORE) > 4)
            b.trade(SOC.resource.ORE, lowest);
        if(b.resourceCount(SOC.resource.WOOD) > 4)
            b.trade(SOC.resource.WOOD, lowest);
        if(b.resourceCount(SOC.resource.WHEAT) > 4)
            b.trade(SOC.resource.WHEAT, lowest);
        if(b.resourceCount(SOC.resource.BRICK) > 4)
            b.trade(SOC.resource.BRICK, lowest);
    }

    public int placeRobber()
    {
        return 5;
    }

    public int stealResource(String[] names)
    {
        return 0;
    }
}
