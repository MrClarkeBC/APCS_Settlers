public class TrentStrategy implements PlayerStrategy
{
    public SOC.Junction placeFirstSettlement(BoardInterface b)
    {
        return b.availableJunctions(SOC.resource.WOOD).get(0);
    }
    public SOC.Junction placeFirstRoad(BoardInterface b)
    {
        return b.availableRoads().get(0);
    }
    public SOC.Junction placeSecondSettlement(BoardInterface b)
    {
        return b.availableJunctions(SOC.resource.BRICK).get(0);
    }
    public SOC.Road placeSecondRoad(BoardInterface b)
    {
        return b.availableRoads().get(0);
    }
    public void takeTurn(BoardInterface b)
    {
        if (b.availableRoads().size() > 0)
            b.build(b.availableRoads().get(0));
        if (b.availableJunctions().size() > 0)
            b.build(b.availableJunctions().get(0));
    }
    public void prioritizeResources(BoardInterface b)
    {
        if(b.resourceCount(SOC.resource.SHEEP) > 4)
        b.trade(SOC.resource.SHEEP, SOC.resource.ORE);
    }
    public int placeRobber()
    {
        return 6;
    }
    public int stealResource(String[] names)
    {
        return 5;
    }
}
