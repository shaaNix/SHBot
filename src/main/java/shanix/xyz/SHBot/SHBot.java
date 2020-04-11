package shanix.xyz.SHBot;

import shanix.xyz.SHBot.db.DBManager;

/**
 * Hello world!
 *
 */
public class SHBot 
{
    public static void main( String[] args )
    {
        DBManager db = new DBManager();
        System.out.println(db.getConfigValue("token"));
    }
}
