package subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer {
    private String name;
    private String id;
    Map<String,Product> products;

    public Customer(String name, String id) {
        this.name = name;
        this.id = id;
        this.products = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProducts(String productId) {
        return products.get(productId);
    }

    public Map<String,Double>getProductCost(String productId){
        final Product product = products.get(productId);
        return product.getLastTwelveMonthCost();
    }

    public void addProduct(Product product) {
       this.products.put(product.getProductId(),product);
    }
}
