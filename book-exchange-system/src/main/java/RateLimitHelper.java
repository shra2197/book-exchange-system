import javax.ws.rs.core.Link;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RateLimitHelper extends Thread{
    String user;
    RateLimit1 rateLimit;

    public RateLimitHelper(String user, RateLimit1 rateLimit) {
        this.user = user;
        this.rateLimit = rateLimit;
    }

    public void run(){
        for (int i = 1; i <= 65; i++) {
            System.out.println("User Name - " + user+ ", Time - " + i + ", rate limit: " + hit(user, Instant.now()));
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("DONE! " + getName());
    }
    public boolean hit(String user, Instant ts) {
        return rateLimit.hit(user, ts);
    }

    public static void main(String[] args) {
        RateLimit1 l= new RateLimit1(5);
//        RateLimitHelper h1= new RateLimitHelper("user1",l);
        final String user2 = "user2";
        RateLimitHelper h2= new RateLimitHelper(user2,l);
        RateLimitHelper h3= new RateLimitHelper(user2,l);
//        new Thread(h1).start();
        new Thread(h2).start();
        new Thread(h3).start();
    }
}
