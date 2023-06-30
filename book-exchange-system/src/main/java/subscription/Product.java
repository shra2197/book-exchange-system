package subscription;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.*;

public class Product {
    private String productId;
    private TreeSet<Subscription> subscriptions;

    public Product(String productId, Subscription subscriptions) {
        this.productId = productId;
        this.subscriptions = new TreeSet<>(Comparator.comparing(Subscription::getStartDate));
        this.subscriptions.add(subscriptions);
    }

    public Map<String, Double> getLastTwelveMonthCost() {
        TreeMap<String,Double> costMap= new TreeMap<>();
        final Subscription subscription = subscriptions.iterator().next();
        final LocalDate startDate = subscription.getStartDate();
        final LocalDate now = LocalDate.now();
        for (LocalDate date = startDate; date.isBefore(now); date=date.plusMonths(1)) {
            costMap.put(date.toString(),subscription.getPlan().getMonthlyCost());
        }
        return costMap;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public TreeSet<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void addSubscription(Subscription subscription) {
        this.subscriptions.add(subscription);
    }
}
