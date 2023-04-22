package subscription;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String id;
    List<Product>products;

    public Customer(String name, String id) {
        this.name = name;
        this.id = id;
        this.products = new ArrayList<>();
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

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
       this.products.add(product);
    }
}
