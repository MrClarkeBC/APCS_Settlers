public class Droid
{
    public Droid()
    {
        
    }
    
    public boolean Trade(int i)
    {
        if(resource < (3 * i.resource))
            return true;
        else
            return false;
    }
}
