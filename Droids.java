public class Droid
{
    public Droid()
    {
        
    }
    
    public boolean Trade(int i)
    {
        if(resource < (3 * i.resource))//Names of resource will be changed according to resource card class
            return true;
        else
            return false;
    }
}
