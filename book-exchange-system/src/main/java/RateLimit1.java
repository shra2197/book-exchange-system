import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimit1<T> {
    public RateLimit1(int limit) {
        this.limit = limit;
    }

    Map<T, Queue<Instant>> map = new ConcurrentHashMap<>();
    int limit;

    public synchronized boolean hit(T user, Instant timeStamp) {
        if (!map.containsKey(user)) {
            Queue<Instant> requests = new LinkedList<>();
            requests.add(timeStamp);
            map.put(user, requests);
            return true;
        }
        if (map.get(user).size() < limit) {
            Queue<Instant> requests = map.get(user);
            requests.add(timeStamp);
            map.put(user, requests);
            return true;
        } else {
            Queue<Instant> requests = map.get(user);
            boolean actionTaken = false;
            while (!requests.isEmpty()) {
                 Instant peek = requests.peek();
                Duration duration = Duration.between(peek, timeStamp);
                if (duration.getSeconds() > 60) {
                    requests.poll();
                    actionTaken = true;
                } else {
                    break;
                }
            }
            if (actionTaken) {
                requests.add(timeStamp);
                map.put(user, requests);
                return true;
            }
        }
        return false;
    }

}
