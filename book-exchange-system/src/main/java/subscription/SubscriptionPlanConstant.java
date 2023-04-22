package subscription;

import java.util.HashMap;
import java.util.Map;

public class SubscriptionPlanConstant {
    static Map<String, Plan> map = new HashMap<>();

    static {
        map.put("BASIC", new Plan("BASIC", 9.99));
        map.put("STANDARD", new Plan("BASIC", 49.99));
        map.put("PREMIUM", new Plan("BASIC", 249.99));
    }

    public static Plan getPlan(String planId) {
        return map.get(planId);
    }
}
