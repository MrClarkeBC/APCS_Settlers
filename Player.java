import java.awt.*;
import java.util.*;

class Player extends Polygon
{
    static int PlayerNum;
    private ArrayList<SOC.resource> m_resources;
    private ArrayList<Card> m_devcards;

    String m_name;
    int m_boardWidth;
    int m_boardHeight;
    int m_num;
    Color m_color;
    private int vp;
    int m_knights;
    boolean m_largest;
    int m_settlements;
    int m_cities;
    int m_roads;
    boolean m_PortSheep;
    boolean m_PortOre;
    boolean m_PortWood;
    boolean m_PortWheat;
    boolean m_PortBrick;
    boolean m_PortAny;

    public Player(String name)
    {
        this(name, 900, 600);
    }

    public Player(String name, int width, int height)
    {
        m_name = name;
        m_num = ++PlayerNum;
        m_boardWidth = width;
        m_boardHeight = height;
        m_resources = new ArrayList<SOC.resource>();
        m_devcards = new ArrayList<Card>();

        switch(m_num)
        {
            case 1:
            m_color = new Color(0x600033);
            break;
            case 2:
            m_color = new Color(0x00CCCC);
            break;
            case 3:
            m_color = new Color(0xFF3399);
            break;
            case 4:
            m_color = new Color(0x00C00F);
            break;

        }
        this.collectResources(SOC.resource.WOOD, 2);
        this.collectResources(SOC.resource.BRICK, 2);
        this.collectResources(SOC.resource.SHEEP, 1);
        this.collectResources(SOC.resource.WHEAT, 1);
    }

    public String toString()
    {
        String s = m_name;// + " resources:\n";
        //        for(SOC.resource r : m_resources)
        //          s += r + "\n";
        return s;
    }

    public Color getColor()
    {
        return m_color;
    }

    public String getName()
    {
        return m_name;
    }

    public void draw(Graphics2D g)
    {
        // Store before changing.
        Stroke tmpS = g.getStroke();
        Color tmpC = g.getColor();

        FontMetrics metrics = g.getFontMetrics();
        g.setColor(m_color);
        String text = String.format("%s",m_name);
        //        int w = metrics.stringWidth(text);
        //      int h = metrics.getHeight();
        int x = 0,y = 0;
        switch(m_num)
        {
            case 1:
            x = 10;
            y = 20;
            break;
            case 2:
            x = m_boardWidth - 100;
            y = 20;
            break;
            case 3:
            x = m_boardWidth - 100;
            y = m_boardHeight - 200;
            break;
            case 4:
            x = 10;
            y = m_boardHeight - 200;
            break;

        }
        int xpoints[] = {x, x + 100, x + 100, x};
        int ypoints[] = {y -5, y-5, y + 200, y + 200};
        g.drawPolygon(xpoints, ypoints, xpoints.length);

        int offset = 30;
        g.drawString(text, x + 5, y + 10);
        text = String.format("%s:%d","VP" + (largestArmy()?"*":""),vp + (largestArmy()?2:0));
        g.drawString(text, x + 5, y + offset);

        offset += 20;
        text = String.format("%s:%d","Knights",m_knights);
        g.drawString(text, x + 5, y + offset);

        
        for(SOC.resource r : SOC.resource.values())
        {
            if (r != SOC.resource.EMPTY && r != SOC.resource.DESERT && r != SOC.resource.PORTORE && r != SOC.resource.PORTSHEEP && r != SOC.resource.PORTWHEAT && r != SOC.resource.PORTBRICK && r != SOC.resource.PORTWOOD && r != SOC.resource.PORTANY)
            {
                offset +=20;
                text = String.format("%s:%d",r, numResource(r));
                g.drawString(text, x + 5, y + offset);
            }
        }

        // Set values to previous when done.
        g.setColor(tmpC);
        g.setStroke(tmpS);
    }

    boolean build(SOC.buildType b)
    {
        if (canBuild(b))
        {
            switch (b)
            {
                case ROAD:
                m_resources.remove(SOC.resource.WOOD);
                m_resources.remove(SOC.resource.BRICK);
                m_roads++;
                break;

                case SETTLEMENT:
                m_resources.remove(SOC.resource.WOOD);
                m_resources.remove(SOC.resource.WHEAT);
                m_resources.remove(SOC.resource.BRICK);
                m_resources.remove(SOC.resource.SHEEP);
                m_settlements++;
                vp++;
                break;

                case CITY:
                m_resources.remove(SOC.resource.ORE);
                m_resources.remove(SOC.resource.ORE);
                m_resources.remove(SOC.resource.ORE);
                m_resources.remove(SOC.resource.WHEAT);
                m_resources.remove(SOC.resource.WHEAT);
                m_cities++;
                m_settlements--;
                vp++;
                break;

                case DEVCARD:
                m_resources.remove(SOC.resource.ORE);
                m_resources.remove(SOC.resource.WHEAT);
                m_resources.remove(SOC.resource.SHEEP);
                break;
            }
            return true;

        }
        return false;
    }

    boolean canBuild(SOC.buildType b)
    {
        switch (b)
        {
            case ROAD:
            if (numResource(SOC.resource.WOOD) > 0 
            && (numResource(SOC.resource.BRICK) > 0)
            && (m_roads < 15))
            {

                return true;
            }
            break;

            case SETTLEMENT:
            if (numResource(SOC.resource.WOOD) > 0 
            && (numResource(SOC.resource.WHEAT) > 0)
            && (numResource(SOC.resource.BRICK) > 0)
            && (numResource(SOC.resource.SHEEP) > 0)
            && (m_settlements < 5))
            {

                return true;
            }
            break;

            case CITY:
            if (numResource(SOC.resource.ORE) > 2 
            && (numResource(SOC.resource.WHEAT) > 1)
            && (m_cities < 4))
            {

                return true;
            }
            break;
            case DEVCARD:
            if (numResource(SOC.resource.ORE) > 0 
            && (numResource(SOC.resource.WHEAT) > 0)
            && (numResource(SOC.resource.SHEEP) > 0))
            {

                return true;
            }
            break;

        }
        return false;  
    }

    public boolean trade(SOC.resource r1, SOC.resource r2)
    {
        int numTrade = 4;
        if (m_PortAny) numTrade = 3;
        if (r1 == SOC.resource.SHEEP && m_PortSheep) numTrade = 2;
        if (r1 == SOC.resource.ORE && m_PortOre) numTrade = 2;
        if (r1 == SOC.resource.WOOD && m_PortWood) numTrade = 2;
        if (r1 == SOC.resource.WHEAT && m_PortWheat) numTrade = 2;
        if (r1 == SOC.resource.BRICK && m_PortBrick) numTrade = 2;

        if (numResource(r1) >= numTrade)
        {
            for (int i = 0;i< numTrade;i++)
                removeResource(r1);
            collectResources(r2, 1);
            return true;
        }
        return false;
    }

    public void collectResources(SOC.resource r, int count)
    {
        //System.out.println(m_name + " got " + count + " " + r);
        for (int i = 0;i<count; i++)
            m_resources.add(r);
    }

    void removeResource(SOC.resource s)
    {
        if (numResource(s) > 0)
            m_resources.remove(s);

    }

    void robbed()
    {
        int totalCount = 0;
        totalCount += numResource(SOC.resource.SHEEP);
        totalCount += numResource(SOC.resource.ORE);
        totalCount += numResource(SOC.resource.WHEAT);
        totalCount += numResource(SOC.resource.WOOD);
        totalCount += numResource(SOC.resource.BRICK);

        int newCount = totalCount/2;
        if (totalCount > 7)
        {
            while (totalCount > newCount && numResource(SOC.resource.SHEEP) > 0)
            {
                totalCount--;
                removeResource(SOC.resource.SHEEP);
            }

            while (totalCount > newCount && numResource(SOC.resource.ORE) > 0)
            {
                totalCount--;
                removeResource(SOC.resource.ORE);
            }

            while (totalCount > newCount && numResource(SOC.resource.WHEAT) > 0)
            {
                totalCount--;
                removeResource(SOC.resource.WHEAT);
            }

            while (totalCount > newCount && numResource(SOC.resource.WOOD) > 0)
            {
                totalCount--;
                removeResource(SOC.resource.WOOD);
            }

            while (totalCount > newCount && numResource(SOC.resource.BRICK) > 0)
            {
                totalCount--;
                removeResource(SOC.resource.BRICK);
            }

        }
    }
    
    int numKnights() { return m_knights; }
    
    void setLargestArmy(boolean bHasIt) { m_largest = bHasIt; };
    
    boolean largestArmy() { return m_largest;};

    int numResource(SOC.resource s)
    {
        int count = 0;
        for(SOC.resource r : m_resources)
            if (r == s) count++;    
        return count;
    }

    public void takeDevCard(Card dc)
    {
        if (dc.getType() == SOC.DEVCARDS.VPOINT)
            vp++;
            
        if (dc.getType() == SOC.DEVCARDS.KNIGHT)
            m_knights++;
        else
            m_devcards.add(dc);
    }

    int numSettlements()
    {
        return m_settlements;
    }

    public void addPort(SOC.resource p)
    {
        if (p == SOC.resource.PORTSHEEP) m_PortSheep = true;
        if (p == SOC.resource.PORTORE) m_PortOre = true;
        if (p == SOC.resource.PORTWOOD) m_PortWood = true;
        if (p == SOC.resource.PORTWHEAT) m_PortWheat = true;
        if (p == SOC.resource.PORTBRICK) m_PortBrick = true;
        if (p == SOC.resource.PORTANY) m_PortAny = true;
    }

}

