import java.awt.*;
import java.util.*;
/**
 * Write a description of class SBotStrategy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SBotStrategy implements PlayerStrategy
{
    int builds;
    int rds;
    public SOC.Junction placeFirstSettlement(BoardInterface b)
    {
        ArrayList<SOC.Junction> x = b.availableJunctions(SOC.resource.BRICK);
        ArrayList<SOC.Junction> y = b.availableJunctions(SOC.resource.WOOD);
        ArrayList<SOC.Junction> v = b.availableJunctions(6);
        ArrayList<SOC.Junction> d = b.availableJunctions(8);
        v.addAll(d);
        ArrayList<SOC.Junction> z = new ArrayList();
        ArrayList<SOC.Junction> s = new ArrayList();
        for (int i=0;i<y.size();i++)
        {
            for (int j=0;j<x.size();j++)
            {
                if (y.contains(x.get(j)))
                    z.add(y.get(i));
            }
        }
        z.trimToSize();
        if (z.size()>0)
        {
            for (int i=0;i<z.size();i++)
            {
                for (int j=0;j<v.size();j++)
                {
                    if (z.contains(v.get(j)))
                        s.add(z.get(i));
                }
            }
            if (s.size()>0)
            {
                return s.get(0);
            }
            return z.get(0);
        }
        builds++;
        return v.get(0);
    }

    public SOC.Junction placeFirstRoad(BoardInterface b)
    {
        return b.availableRoads().get(0);
    }

    public SOC.Junction placeSecondSettlement(BoardInterface b)
    {
        ArrayList<SOC.Junction> x = b.availableJunctions(SOC.resource.ORE);
        ArrayList<SOC.Junction> y = b.availableJunctions(SOC.resource.WHEAT);
        ArrayList<SOC.Junction> v = b.availableJunctions(6);
        ArrayList<SOC.Junction> d = b.availableJunctions(8);
        v.addAll(d);
        ArrayList<SOC.Junction> z = new ArrayList();
        ArrayList<SOC.Junction> s = new ArrayList();
        for (int i=0;i<y.size();i++)
        {
            for (int j=0;j<x.size();j++)
            {
                if (y.contains(x.get(j)))
                    z.add(y.get(i));
            }
        }
        z.trimToSize();
        if (z.size()>0)
        {
            for (int i=0;i<z.size();i++)
            {
                for (int j=0;j<v.size();j++)
                {
                    if (z.contains(v.get(j)))
                        s.add(z.get(i));
                }
            }
            if (s.size()>0)
            {
                return s.get(0);
            }
            return z.get(0);
        }
        builds++;
        return v.get(0);
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
        if (b.availableRoads().size() > 0)
        {
            b.build(b.availableRoads().get(0));
            rds++;
        }

        if(builds>4)
        {
            if (b.availableJunctions(SOC.resource.WHEAT).size() > 0)
                b.build(b.availableJunctions(SOC.resource.WHEAT).get(0));
            builds++;
        }

        else{
            if (b.availableJunctions(SOC.resource.WOOD).size() > 0)
                b.build(b.availableJunctions(SOC.resource.WOOD).get(0));
            builds++;
        }

        if (b.availableJunctions(6).size() > 0)
        {
            b.build(b.availableJunctions(6).get(0));
            builds++;
        }

        if (b.availableJunctions(8).size() > 0){
            b.build(b.availableJunctions(8).get(0));
            builds++;
        }

        if (b.availableJunctions(5).size() > 0){
            b.build(b.availableJunctions(5).get(0));
            builds++;
        }

        if (b.availableJunctions().size() > 0){
            b.build(b.availableJunctions().get(0));
            builds++;
        }
    }

    /*b.build(b.availableJunctions().get(
    (int)(Math.random()*b.availableJunctions().size())));*/

    public void prioritizeResources(BoardInterface b)
    {   
        if (b.availableRoads().size()==0&&b.resourceCount(SOC.resource.ORE) ==0)
        {
            
            if(b.resourceCount(SOC.resource.SHEEP) > 4)
                b.trade(SOC.resource.SHEEP, SOC.resource.ORE);
           
            if(b.resourceCount(SOC.resource.BRICK) > 4)
                b.trade(SOC.resource.BRICK, SOC.resource.ORE);
            if(b.resourceCount(SOC.resource.WOOD) > 4)
                b.trade(SOC.resource.WOOD, SOC.resource.ORE);
        }
        if (b.resourceCount(SOC.resource.BRICK) ==0)
        {
            if(b.resourceCount(SOC.resource.ORE) > 4)
                b.trade(SOC.resource.ORE, SOC.resource.BRICK);
            if(b.resourceCount(SOC.resource.SHEEP) > 4)
                b.trade(SOC.resource.SHEEP, SOC.resource.BRICK);
            if(b.resourceCount(SOC.resource.WHEAT) > 4)
                b.trade(SOC.resource.WHEAT, SOC.resource.BRICK);
           
            if(b.resourceCount(SOC.resource.WOOD) > 4)
                b.trade(SOC.resource.WOOD, SOC.resource.BRICK);
        }
        
        if (builds<5)
        {
            if(b.resourceCount(SOC.resource.ORE) > 4)
                b.trade(SOC.resource.ORE, SOC.resource.BRICK);
            if(b.resourceCount(SOC.resource.SHEEP) > 4)
                b.trade(SOC.resource.SHEEP, SOC.resource.BRICK);
            if(b.resourceCount(SOC.resource.WHEAT) > 4)
                b.trade(SOC.resource.WHEAT, SOC.resource.WOOD);
            if(b.resourceCount(SOC.resource.BRICK) > 4)
                b.trade(SOC.resource.BRICK, SOC.resource.SHEEP);
            if(b.resourceCount(SOC.resource.WOOD) > 4)
                b.trade(SOC.resource.WOOD, SOC.resource.BRICK);
        }

        if (builds>4)
        {
            if(b.resourceCount(SOC.resource.WOOD) > 4)
                b.trade(SOC.resource.WOOD, SOC.resource.SHEEP);
            if(b.resourceCount(SOC.resource.BRICK) > 4)
                b.trade(SOC.resource.BRICK, SOC.resource.WHEAT);
            if(b.resourceCount(SOC.resource.SHEEP) > 4)
                b.trade(SOC.resource.SHEEP, SOC.resource.ORE);
            if(b.resourceCount(SOC.resource.ORE) > 4)
                b.trade(SOC.resource.ORE, SOC.resource.WHEAT);
            if(b.resourceCount(SOC.resource.WHEAT) > 4)
                b.trade(SOC.resource.WHEAT, SOC.resource.ORE);
        }
    }

    public int placeRobber(){return 5;} // return tile to place robber
    public int stealResource(String[] names){return 2;} // return which player to steal from
}
