public class SimplerStrategy implements PlayerStrategy
{
    public SOC.Junction placeFirstSettlement(BoardInterface b)
    {
        /*return b.availableJunctions(SOC.resource.ORE).get(0) for example will return
        *the first Junction on the board that is bordering ORE.*/
        return b.availableJunctions(SOC.resource.WOOD).get(0);
    }
    public SOC.Junction placeFirstRoad(BoardInterface b)
    {
        return b.availableRoads().get(0);
    }
    public SOC.Junction placeSecondSettlement(BoardInterface b)
    {
        return  b.availableJunctions(SOC.resource.BRICK).get(0);
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
        if (b.availableJunctions().size() > 0)
            b.build(b.availableJunctions().get(0));
    }
    
    public void prioritizeResources(BoardInterface b)
    {
        if(b.resourceCount(SOC.resource.WOOD) > 4)
        b.trade(SOC.resource.SHEEP, SOC.resource.BRICK);   
    }
    public int placeRobber() {return 0;}; // return tile to place robber
    public int stealResource(String[] names) {return 0;}; // return which player to steal from

    
}
