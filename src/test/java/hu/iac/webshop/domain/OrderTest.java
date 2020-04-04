package hu.iac.webshop.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {
    @Test
    @DisplayName("Verify total order value")
    void verifyOrderValue() {
        /*arrange*/
        Customer customer = new Customer("Test", "06-12345678", "Test@Test.nl");
        Order order = new Order(new Date(), 0.0, customer);
        Category category = new Category("x", "nieuw", "Description");
        Product product1 = new Product("Komkommer", 10.0, 50, category);
        Product product2 = new Product("Telefoon", 500.0, 20, category);
        OrderProduct orderProduct1 = new OrderProduct(order, product1, 5);
        OrderProduct orderProduct2 = new OrderProduct(order, product2, 1);
        OrderProduct orderProduct3 = new OrderProduct(order, product1, 2);
        order.addProduct(orderProduct1);
        order.addProduct(orderProduct2);
        order.addProduct(orderProduct3);

        /*act*/
        order.getCurrentOrderValue();
        double actualTotal = order.getTotalPrice();

        /*assert*/
        assertEquals(570.0, actualTotal);
    }
}
