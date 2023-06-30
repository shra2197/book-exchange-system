package subscription;

import java.util.List;
import java.util.Map;

public class SubscriptionService {
    public SubscriptionDao dao = new SubscriptionDao();

    public Map<String,Double> monthlyCostList(String customerId, String productId) {
        Customer customer = dao.getCustomer(customerId);
        return customer.getProductCost(productId);
    }
    public void addCustomer(Customer customer){
        dao.addCustomer(customer);
    }

    public void addProduct(String customerId, Product product) {
        Customer customer = dao.getCustomer(customerId);
        customer.addProduct(product);
    }
}
