package order;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import hu.iac.webshop.controllers.OrderController;
import hu.iac.webshop.domain.Customer;
import hu.iac.webshop.domain.Order;
import hu.iac.webshop.domain.Product;
import hu.iac.webshop.repositories.OrderRepository;
import hu.iac.webshop.services.OrderService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;

@WebMvcTest(value = OrderController.class)
public class TestOrder {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderRepository mockRepository;
    @MockBean
    private OrderService mockService;

    // @Test
    // @DisplayName("Test order value calculation")
    // public void testOrderValue() {
    //     Customer customer = new Customer("Noud", "0612345678", "info@noudadrichem.com");
    //     Order order = new Order(new Date(), 0, customer);
    //     Product product1 = new Product("een", 10, 55);
    //     Product product2 = new Product("twee", 20, 44);
    //     Product product3 = new Product("drie", 30, 12);

    //     order.addProduct(product1);
    //     order.addProduct(product2);
    //     order.addProduct(product3);

    //     double totalOrderValue = order.getCurrentOrderValue();
    //     double expectedOrderValue = 60;

    //     // customer.addOrder(order);

    //     assertEquals(totalOrderValue, expectedOrderValue);
    // }

    // @Test
    // @DisplayName("Test POST order parameters")
    // public void testPostParameters() {
    //     Customer customer = new Customer("Noud", "0612345678", "info@noudadrichem.com");
    //     Order order = new Order(new Date(), 0, customer);

    //     Mockito.when(mockService.create(order)).thenReturn(order);

    //     RequestBuilder reqBUilder = MockMvcRequestBuilders.get("").accept(MediaType.APPLICATION_JSON);
    //     MvcResult result;
    //     try {
    //         result = mockMvc.perform(reqBUilder).andReturn();
    //         System.out.println(result.getResponse());
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }

    //     // String expectedStr = "{}";
    //     // JSONAssert.assertEquals(expectedStr, actualStr, strict);

    // }

    // @Test
    // @DisplayName("Test save order to database via server layer")
    // public void testOrderDbSave() {
    //     Customer customer = new Customer("Noud", "0612345678", "info@noudadrichem.com");
    //     Order order = new Order(new Date(), 0, customer);

    //     Order savedOrder = mockRepository.save(order);
    // }

}
