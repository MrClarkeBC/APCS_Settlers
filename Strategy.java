public class Strategy implements PlayerStrategy
{
    public SOC.Junction placeFirstSettlement(BoardInterface b)
    {
        return b.availableJunctions(8).get(0);
    }

    public SOC.Junction placeFirstRoad(BoardInterface b)
    {
        return b.availableRoads().get(0);
    }

    public SOC.Junction placeSecondSettlement(BoardInterface b)
    {
        return b.availableJunctions(SOC.resource.ORE).get(0);
    }

    public SOC.Road placeSecondRoad(BoardInterface b)
    {
        return b.availableRoads().get(0);
    }

    public void takeTurn(BoardInterface b)
    {
        /*if (b.resourceCount(SOC.resource.ORE > 3) && 
        b.resourceCount(SOC.resource.WHEAT) > 2))
        b*/
        prioritizeResources(b);
        if (b.availableJunctions(SOC.resource.BRICK).size() > 0)
            b.build(b.availableJunctions(SOC.resource.BRICK).get(0));
        if (b.availableJunctions(6).size() > 0)
            b.build(b.availableJunctions(6).get(0));
        if (b.availableJunctions(8).size() > 0)
            b.build(b.availableJunctions(8).get(0));
        if (b.availableJunctions(5).size() > 0)
            b.build(b.availableJunctions(5).get(0));
        if (b.availableJunctions().size() > 0)
            b.build(b.availableJunctions().get(0));
        if (b.availableRoads().size() > 0)
            b.build(b.availableRoads().get(0));
    }

    /*b.build(b.availableJunctions().get(
    (int)(Math.random()*b.availableJunctions().size())));*/

    public void prioritizeResources(BoardInterface b)
    {
        if(b.resourceCount(SOC.resource.ORE) > 4)
            b.trade(SOC.resource.ORE, SOC.resource.BRICK);
        if(b.resourceCount(SOC.resource.WOOD) > 4)
            b.trade(SOC.resource.WOOD, SOC.resource.WHEAT);
        if(b.resourceCount(SOC.resource.WHEAT) > 4)
            b.trade(SOC.resource.WHEAT, SOC.resource.SHEEP);
        if(b.resourceCount(SOC.resource.SHEEP) > 4)
            b.trade(SOC.resource.SHEEP, SOC.resource.BRICK);
    }

    public int placeRobber(){return 5;} // return tile to place robber
    public int stealResource(String[] names){return 2;} // return which player to steal from
}