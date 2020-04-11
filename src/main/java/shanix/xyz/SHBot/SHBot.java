package shanix.xyz.SHBot;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import shanix.xyz.SHBot.db.DBManager;
import shanix.xyz.SHBot.jda.ping.PingListener;

/**
 * Hello world!
 *
 */
public class SHBot 
{
    public static void main( String[] args )
    {
        DBManager db = new DBManager();
        String token = db.getConfigValue("token");
        System.out.println("Discord-token: " + token);
        
        try {
			JDA api = JDABuilder.createDefault(token).build();
			api.addEventListener(new PingListener());
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
