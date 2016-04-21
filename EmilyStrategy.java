public class EmilyStrategy implements PlayerStrategy
{  
    public EmilyStrategy()
    {
    }

    public SOC.Junction placeFirstSettlement(BoardInterface b)
    {
        return b.availableJunctions(SOC.resource.BRICK).get(2);
    }

    public SOC.Junction placeFirstRoad(BoardInterface b)
    {
        return b.availableRoads().get(0);
    }

    public SOC.Junction placeSecondSettlement(BoardInterface b)
    {
        return b.availableJunctions(SOC.resource.WOOD).get(2);
    }

    public SOC.Road placeSecondRoad(BoardInterface b)
    {
        return b.availableRoads().get(0);
    }

    public void takeTurn(BoardInterface b)
    {
        System.out.println(b.availableJunctions());
        prioritizeResources(b);
        if(b.availableRoads().size() > 0)
            b.build(b.availableRoads().get(0));
        if(b.availableJunctions(SOC.resource.ORE).size() > 0)
            b.build(b.availableJunctions(SOC.resource.ORE).get(2));
    }

    public void prioritizeResources(BoardInterface b)
    {
        if(b.resourceCount(SOC.resource.WOOD) > 8)
            b.trade(SOC.resource.WOOD, SOC.resource.SHEEP);
        if(b.resourceCount(SOC.resource.ORE) > 8)
            b.trade(SOC.resource.ORE, SOC.resource.BRICK);
        if(b.resourceCount(SOC.resource.WHEAT) > 8)
            b.trade(SOC.resource.WHEAT, SOC.resource.WOOD);
        if(b.resourceCount(SOC.resource.SHEEP) > 8)
            b.trade(SOC.resource.SHEEP, SOC.resource.ORE);
        if(b.resourceCount(SOC.resource.BRICK) > 8)
            b.trade(SOC.resource.BRICK, SOC.resource.WHEAT);
    }

    public int scoreJunction(BoardInterface b, SOC.Junction j)
    {   
        for(int i = 0; i < j.address.length; i++)
        {
            System.out.println("Junctions REESOURCESSS" + b.tileResource(j.address[i]));
        }
        return 1;
    }

    public int placeRobber()
    {
        return 0;
    }
    
    public int stealResource(String[] names)
    {
        return 0;
    }
}
