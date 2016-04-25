
public interface PlayerStrategy
{
    abstract SOC.Junction placeFirstSettlement(BoardInterface b);
    abstract SOC.Junction placeFirstRoad(BoardInterface  b);
    abstract SOC.Junction placeSecondSettlement(BoardInterface  b);
    abstract SOC.Road placeSecondRoad(BoardInterface  b);
    abstract void takeTurn(BoardInterface  b);
    abstract void prioritizeResources(BoardInterface  b);
    abstract void robbed();
    abstract int placeRobber(); // return tile to place robber
    abstract int stealResource(String[] names); // return which player to steal from

}
