
public class Dice
{
    int m_die1;
    int m_die2;
    public Dice()
    {
    }
    public int roll()
    {
        m_die1 = (int)(Math.random() * 6 + 1);
        m_die2 = (int)(Math.random() * 6 + 1);
        return m_die1 + m_die2;
    }
    public String toString()
    {
        return m_die1 + "," + m_die2;
    }
}
