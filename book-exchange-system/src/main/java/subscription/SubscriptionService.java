package subscription;

import java.time.Instant;
import java.util.List;

public class SubscriptionService {
    public SubscriptionDao dao = new SubscriptionDao();

    List<Product> monthlyCostList(String customerId) {
        Customer customer = dao.getCustomer(customerId);
    }

    public void addProduct(String customerId, Product product, String planId) {
        Customer customer = dao.getCustomer(customerId);
        final Instant now = Instant.now();
        new Product("Jira", new Subscription(SubscriptionPlanConstant.getPlan(planId), now))
        customer.addProduct();
    }
}
