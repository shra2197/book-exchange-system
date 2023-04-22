package subscription;

import java.util.HashMap;
import java.util.Map;

public class SubscriptionDao {

    Map<String, Customer> map = new HashMap<>();
    public Customer getCustomer(String customerId){
        return map.get(customerId);
    }

    public void addCustomer(Customer customer){
        map.put(customer.getId(),customer);
    }
}
