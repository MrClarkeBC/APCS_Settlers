
public class KaiStrategy implements PlayerStrategy
{
    public SOC.Junction placeFirstSettlement(BoardInterface b)
    {
        /*return b.availableJunctions(SOC.resource.ORE).get(0) for example will return
         *the first Junction on the board that is bordering ORE.*/
        SOC.Junction best = b.availableJunctions().get(0); 
        for(SOC.Junction  j : b.availableJunctions())
        {
            if (score(j, b) > score(best, b))
                best = j;
        }
        return best;
    }

    public SOC.Road placeFirstRoad(BoardInterface b)
    {
        SOC.Road best = b.availableRoads().get(0); 
        for(SOC.Road  r : b.availableRoads())
        {
            if (score(r, b) > score(best, b))
                best = r;
        }
        return best;
    }

    public SOC.Junction placeSecondSettlement(BoardInterface b)
    {
        SOC.Junction best = b.availableJunctions().get(0); 
        for(SOC.Junction  j : b.availableJunctions())
        {
            if (score(j, b) > score(best, b))
                best = j;
        }
        return best;
    }

    public SOC.Road placeSecondRoad(BoardInterface b)
    {
        SOC.Road best = b.availableRoads().get(0); 
        for(SOC.Road  r : b.availableRoads())
        {
            if (score(r, b) > score(best, b))
                best = r;
        }
        return best;
    }

    public void takeTurn(BoardInterface b)
    {
        prioritizeResources(b);
        prioritizeResources(b);
        if (b.availableJunctions().size() > 0)
        {
            SOC.Junction best = b.availableJunctions().get(0); 
            for(SOC.Junction  j : b.availableJunctions())
            {
                if (score(j, b) > score(best, b))
                    best = j;
            }
            b.build(best);
        }
        if (b.availableRoads().size() > 0)
        {
            SOC.Road best = b.availableRoads().get(0); 
            for(SOC.Road  r : b.availableRoads())
            {
                if (score(r, b) > score(best, b))
                    best = r;
            }
            b.build(best);
        }
    }

    public void prioritizeResources(BoardInterface b)
    {
        if(b.resourceCount(SOC.resource.SHEEP) > 4)
            b.trade(SOC.resource.SHEEP, SOC.resource.WHEAT);
        if(b.resourceCount(SOC.resource.WHEAT) > 4)
            b.trade(SOC.resource.WHEAT, SOC.resource.ORE);
        if(b.resourceCount(SOC.resource.ORE) > 4)
            b.trade(SOC.resource.ORE, SOC.resource.BRICK);
        if(b.resourceCount(SOC.resource.BRICK) > 4)
            b.trade(SOC.resource.BRICK, SOC.resource.WOOD);
        if(b.resourceCount(SOC.resource.WOOD) > 4)
            b.trade(SOC.resource.WOOD, SOC.resource.SHEEP);
    }

    private int score(SOC.Road r, BoardInterface b)
    {
        int s = 0;
        int t1 = Math.abs((b.tileNumber(r.address[0]) - 7));
        int t2 = Math.abs((b.tileNumber(r.address[1]) - 7));
        s += (5 - t1) + (5 - t2);
        if(r.address[0] == 1)
            s++;
        return s;
    }

    private int score(SOC.Junction j, BoardInterface b)
    {
        int s = 0;
        int t1 = Math.abs((b.tileNumber(j.address[0]) - 7));
        int t2 = Math.abs((b.tileNumber(j.address[1]) - 7));
        int t3 = Math.abs((b.tileNumber(j.address[2]) - 7));
        s += (5 - t1) + (5 - t2) + (5 - t3);
        for(int i = 0; i < 3; i++)
        {
            switch(b.tileResource(j.address[i]))
            {
                case SHEEP:
                s += 1;
                break;
                case BRICK:
                s += 3;
                break;
                case ORE:
                s += 2;
                break;
                case WOOD:
                s += 3;
                break;
                case WHEAT:
                s += 2;
                break;
                case PORTSHEEP:
                s += 1;
                break;
                case PORTBRICK:
                s += 1;
                break;
                case PORTORE:
                s += 1;
                break;
                case PORTWOOD:
                s += 1;
                break;
                case PORTWHEAT:
                s += 1;
                break;
                case PORTANY:
                s += 2;
                break;
                case DESERT:
                s -= 1;
                break;
                
            }
        }

        return s;
    }

    public int placeRobber()
    {
        return (int)(Math.floor(Math.random() * 36));  
    }

    public int stealResource(String[] names)
    {
        return (int)(Math.floor(Math.random() * 4)); 
    }

}

