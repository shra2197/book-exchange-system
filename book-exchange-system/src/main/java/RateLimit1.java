import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimit1 {
    public RateLimit1(int limit) {
        this.limit = limit;
    }

    Map<String, Queue<Request>> map = new ConcurrentHashMap<>();
    int limit;

    public synchronized boolean hit(String user, Instant timeStamp) {
        if (!map.containsKey(user)) {
            Queue<Request> requests = new LinkedList<>();
            requests.add(new Request(timeStamp, 1));
            map.put(user, requests);
            return true;
        }
        if (isTimeElapsed(user) < limit) {
            Queue<Request> requests = map.get(user);
            requests.add(new Request(timeStamp, 1));
            map.put(user, requests);
            return true;
        } else {
            Queue<Request> requests = map.get(user);
            boolean actionTaken = false;
            while (!requests.isEmpty()) {
                final Request peek = requests.peek();
                Duration duration = Duration.between(peek.getTimestamp(), timeStamp);
                if (duration.getSeconds() > 60) {
                    requests.poll();
                    actionTaken = true;
                } else {
                    break;
                }
            }
            if (actionTaken) {
                requests.add(new Request(timeStamp, 1));
                map.put(user, requests);
                return true;
            }
        }

        return false;
    }

    private int isTimeElapsed(String user) {
        return map.get(user).size();
    }

}
