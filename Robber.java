import java.util.*;

public class Robber
{
    private int m_location; 
    Player stealfro;
    Player giveto;
    Board c;
    List<Player> p = Arrays.asList(c.getT()[m_location].getP());
    public Robber(int loc,Player gto,Board b,String steal)
    {
        m_location = loc;
        c=b;
        giveto=gto;
        switch(steal)
        {
            case "P1":
            if (p.contains(c.getP()[0]))
                stealfro = c.getP()[0];
            case "P2":
            if (p.contains(c.getP()[0]))
                stealfro = c.getP()[1];
            case "P3":
            if (p.contains(c.getP()[0]))
                stealfro = c.getP()[2];
            case "P4":
            if (p.contains(c.getP()[0]))
                stealfro = c.getP()[3];
        }
    }

    public int getLocation()
    {
        return m_location;
    }

    public void newLocation(int loc)
    {
        m_location = loc;
    }

    public void newgive(Player g){giveto=g;};

    public void newsteal(String steal)
    {
        switch(steal)
        {
            case "P1":
            if (p.contains(c.getP()[0]))
                stealfro = c.getP()[0];
            case "P2":
            if (p.contains(c.getP()[0]))
                stealfro = c.getP()[1];
            case "P3":
            if (p.contains(c.getP()[0]))
                stealfro = c.getP()[2];
            case "P4":
            if (p.contains(c.getP()[0]))
                stealfro = c.getP()[3];
        }
    }

    public void steal()
    {
        int x = (int)((Math.random()*stealfro.getr().size())+1);
        SOC.resource s=stealfro.getr().get(x);
        stealfro.getr().remove(s);
        giveto.getr().add(s);
    }
}
