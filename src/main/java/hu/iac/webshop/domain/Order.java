package hu.iac.webshop.domain;

import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private Date date;
    private double totalPrice;
    private List<Product> products;

    public void getCurrentOrderValue() {
        for (Product product : products) {
            totalPrice += product.getPrice();
        };
    }
}
