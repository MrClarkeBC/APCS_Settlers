public class DevonStrategy implements PlayerStrategy
{
    public SOC.Junction placeFirstSettlement(BoardInterface b)
    {
        /*return b.availableJunctions(SOC.resource.ORE).get(0) for example will return
         *the first Junction on the board that is bordering ORE.*/
        return b.availableJunctions(SOC.resource.BRICK).get(0);
    }

    public SOC.Junction placeFirstRoad(BoardInterface b)
    {
        return b.availableRoads().get(0);
    }

    public SOC.Junction placeSecondSettlement(BoardInterface b)
    {
        return b.availableJunctions(SOC.resource.WOOD).get(0);
    }

    public SOC.Road placeSecondRoad(BoardInterface b)
    {
        return b.availableRoads().get(0);
    }

    public void takeTurn(BoardInterface b)
    {
        prioritizeResources(b);
        if (b.availableRoads().size() > 0)
            b.build(b.availableRoads().get(0));
        if (b.availableJunctions(SOC.resource.SHEEP).size() > 0)
            b.build(b.availableJunctions(SOC.resource.SHEEP).get(0));
    }

    public void prioritizeResources(BoardInterface b)
    {
        if(b.resourceCount(SOC.resource.ORE) > 4)
            b.trade(SOC.resource.ORE, SOC.resource.SHEEP);
        if(b.resourceCount(SOC.resource.SHEEP) > 4)
            b.trade(SOC.resource.SHEEP, SOC.resource.ORE);
        if(b.resourceCount(SOC.resource.WOOD) > 10)
            b.trade(SOC.resource.WOOD, SOC.resource.SHEEP);
        if(b.resourceCount(SOC.resource.ORE) > 10)
            b.trade(SOC.resource.ORE, SOC.resource.WOOD);
        if(b.resourceCount(SOC.resource.ORE) > 15)
            b.trade(SOC.resource.ORE, SOC.resource.WHEAT);
        if(b.resourceCount(SOC.resource.BRICK) > 10)
            b.trade(SOC.resource.BRICK, SOC.resource.WOOD);
        if(b.resourceCount(SOC.resource.BRICK) > 5)
            b.trade(SOC.resource.BRICK, SOC.resource.WHEAT);
        if(b.resourceCount(SOC.resource.SHEEP) > 5)
            b.trade(SOC.resource.SHEEP, SOC.resource.WHEAT);
        if(b.resourceCount(SOC.resource.WHEAT) > 5)
            b.trade(SOC.resource.WHEAT, SOC.resource.SHEEP);
        
    }
    public int stealResource(String players[]) {return 4;};
 public int placeRobber() {return 0;}; // return tile to place robber

}
