package subscription;

import java.util.Comparator;
import java.util.TreeSet;

public class Product {
    private String productId;
    private TreeSet<Subscription> subscription;

    public Product(String productId, Subscription subscription) {
        this.productId = productId;
        this.subscription = new TreeSet<>(Comparator.comparing(Subscription::getStartDate));
        this.subscription.add(subscription);
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public TreeSet<Subscription> getSubscription() {
        return subscription;
    }

    public void addSubscription(Subscription subscription) {
        this.subscription.add(subscription);
    }
}
