import java.util.*;
public class AllenStrategy implements PlayerStrategy
{
    SOC.resource r1, r2, r3;
    String myName;
    SOC.Junction first;
    ArrayList<SOC.Junction> mysettlements;
    public SOC.Junction placeFirstSettlement(BoardInterface b)
    {
        int maxloc = 0;
        int max = 0; 
        for(int i = 0; i < b.availableJunctions().size(); i++)
        {
            if(score(b.availableJunctions().get(i), b) > max)
            {
                max = score(b.availableJunctions().get(i), b);
                maxloc = i;
            }

        }
        r1 = b.tileResource(b.availableJunctions().get(maxloc).address[0]);
        r2 = b.tileResource(b.availableJunctions().get(maxloc).address[1]);
        r3 = b.tileResource(b.availableJunctions().get(maxloc).address[2]);
        first = b.availableJunctions().get(maxloc);
        return b.availableJunctions().get(maxloc);
    }

    public SOC.Junction placeFirstRoad(BoardInterface b)
    {
        return b.availableRoads().get(0);
    }

    public SOC.Junction placeSecondSettlement(BoardInterface b)
    {
        myName = b.OwnerName(first.address[0], first.locations[0]);
        System.out.println(b.OwnerName(first.address[0], first.locations[0]));
        int maxloc = 0;
        int max = 0; 
        SOC.resource need = SOC.resource.WOOD;

        if(r1 != SOC.resource.WOOD && r3 != SOC.resource.WOOD && r3 != SOC.resource.WOOD)
            need = SOC.resource.WOOD;
        if(r1 != SOC.resource.SHEEP && r3 != SOC.resource.SHEEP && r3 != SOC.resource.SHEEP)
            need = SOC.resource.SHEEP;
        if(r1 != SOC.resource.WHEAT && r3 != SOC.resource.WHEAT && r3 != SOC.resource.WHEAT)
            need = SOC.resource.WHEAT;
        if(r1 != SOC.resource.BRICK && r3 != SOC.resource.BRICK && r3 != SOC.resource.BRICK)
            need = SOC.resource.BRICK;
        if(r1 != SOC.resource.ORE && r3 != SOC.resource.ORE && r3 != SOC.resource.ORE)
            need = SOC.resource.ORE;
        for(int i = 0; i < b.availableJunctions(need).size(); i++)
        {
            if(score(b.availableJunctions(need).get(i), b) > max)
            {
                max = score(b.availableJunctions(need).get(i), b);
                maxloc = i;
            }
        }
        return b.availableJunctions(need).get(maxloc);

    }

    public SOC.Road placeSecondRoad(BoardInterface b)
    {
        return b.availableRoads().get(0);
    }

    public int placeRobber()
    {
        return(int)(Math.floor(Math.random() * 35) + 1);
    }

    public int stealResource(String[] s)
    {
        return 0;
    }

    public void takeTurn(BoardInterface b)
    {   int maxloc = 0;
        int max = 0; 
        ArrayList<SOC.Junction> temp = findSettlement(b);

        if((b.resourceCount(SOC.resource.ORE) > 2) &&(b.resourceCount(SOC.resource.WHEAT) > 1) && temp.size() >0)
        {
            for(int i = 0; i < temp.size(); i++)
            {
                if(score(temp.get(i), b) > max)
                {
                    max = score(temp.get(i), b);
                    maxloc = i;
                }

            }
            if(temp.size() > 0)
                b.build(temp.get(maxloc));
        }

        if (b.availableJunctions().size() > 0)
        {
            maxloc = 0;
            max = 0;
            for(int i = 0; i < b.availableJunctions().size(); i++)
            {
                if(score(b.availableJunctions().get(i), b) > max)
                {
                    max = score(b.availableJunctions().get(i), b);
                    maxloc = i;
                }

            }
            b.build(b.availableJunctions().get(maxloc));
        }

        if((b.resourceCount(SOC.resource.ORE) > 0) &&(b.resourceCount(SOC.resource.WHEAT) > 0) && (b.resourceCount(SOC.resource.SHEEP) > 0))
        {
            b.buyCard();
        }

        if (b.availableRoads().size() > 0)
        {
            int maxrloc = 0;
            int maxr = 0;
            for(int i = 0; i < b.availableRoads().size(); i++)
            {
                if(scoreRoad(b.availableRoads().get(i), b) > maxr)
                {
                    max = scoreRoad(b.availableRoads().get(i), b);
                    maxrloc = i;
                }

            }
            b.build(b.availableRoads().get(maxrloc));

        }
        prioritizeResources(b);
    }

    public void prioritizeResources(BoardInterface b)
    {
        SOC.resource need = null;
        SOC.resource have = null;
        if(b.resourceCount(SOC.resource.ORE) == 0)
            need = SOC.resource.ORE;
        if(b.resourceCount(SOC.resource.WHEAT) == 0)
            need = SOC.resource.WHEAT;
        if(b.resourceCount(SOC.resource.BRICK) == 0)
            need = SOC.resource.BRICK;
        if(b.resourceCount(SOC.resource.WOOD) == 0)
            need = SOC.resource.WOOD;
        if(b.resourceCount(SOC.resource.SHEEP) == 0)
            need = SOC.resource.SHEEP;

        if(b.resourceCount(SOC.resource.ORE) >= 5)
            have = SOC.resource.ORE;
        if(b.resourceCount(SOC.resource.WHEAT) >= 5)
            have = SOC.resource.WHEAT;
        if(b.resourceCount(SOC.resource.BRICK) >= 5)
            have = SOC.resource.BRICK;
        if(b.resourceCount(SOC.resource.WOOD) >= 5)
            have = SOC.resource.WOOD;
        if(b.resourceCount(SOC.resource.SHEEP) >= 5)
            have = SOC.resource.SHEEP;

        if(have != null && need != null)
            b.trade(have, need);
    }

    public int score(SOC.Junction j, BoardInterface b)
    {
        int total = 0;
        for(int i = 0; i < 3; i++)
        {
            if(b.tileNumber(j.address[i]) == 2 ||b.tileNumber(j.address[i]) == 12)
                total += 1;
            if(b.tileNumber(j.address[i]) == 3 ||b.tileNumber(j.address[i]) == 11)
                total += 2;
            if(b.tileNumber(j.address[i]) == 4 ||b.tileNumber(j.address[i]) == 10)
                total += 3;
            if(b.tileNumber(j.address[i]) == 5 ||b.tileNumber(j.address[i]) == 9)
                total += 4;
            if(b.tileNumber(j.address[i]) == 6 ||b.tileNumber(j.address[i]) == 8)
                total += 5;
        }
        return total;
    }

    public ArrayList<SOC.Junction> findSettlement(BoardInterface b)
    {
        ArrayList<SOC.Junction> mysettlements = new ArrayList<SOC.Junction>();
        for(int i = 0; i < b.allJunctions().size(); i++)
        {
            SOC.Junction j = b.allJunctions().get(i);
            if(j.ownerName() != null)
                if (j.ownerName().equals(myName))
                    mysettlements.add(b.allJunctions().get(i));
        }
        return mysettlements;
    }

    public int scoreRoad(SOC.Road r, BoardInterface b)
    {
        int total = 0;
        for(int i = 0; i < 2; i++)
        {
            if(b.tileNumber(r.address[i]) == 2 ||b.tileNumber(r.address[i]) == 12)
                total += 1;
            if(b.tileNumber(r.address[i]) == 3 ||b.tileNumber(r.address[i]) == 11)
                total += 2;
            if(b.tileNumber(r.address[i]) == 4 ||b.tileNumber(r.address[i]) == 10)
                total += 3;
            if(b.tileNumber(r.address[i]) == 5 ||b.tileNumber(r.address[i]) == 9)
                total += 4;
            if(b.tileNumber(r.address[i]) == 6 ||b.tileNumber(r.address[i]) == 8)
                total += 5;
        }
        return total;
    }
}
