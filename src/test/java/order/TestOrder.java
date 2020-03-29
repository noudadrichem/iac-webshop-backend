package order;

import org.junit.jupiter.api.Test;

import hu.iac.webshop.domain.Order;
import hu.iac.webshop.domain.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;

public class TestOrder {

    @Test
    @DisplayName("Test order value")
    public void testOrderValue() {

        Order order = new Order();
        Product product1 = new Product("een", 10, 55);
        Product product2 = new Product("twee", 20, 44);
        Product product3 = new Product("drie", 30, 12);

        order.addProduct(product1);
        order.addProduct(product2);
        order.addProduct(product3);

        double totalOrderValue = order.getCurrentOrderValue();
        double expectedOrderValue = 60;

        assertEquals(totalOrderValue, expectedOrderValue);
    }

}
