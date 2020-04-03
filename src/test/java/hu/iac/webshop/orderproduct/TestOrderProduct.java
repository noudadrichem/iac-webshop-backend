package hu.iac.webshop.orderproduct;

import hu.iac.webshop.controllers.OrderProductController;
import hu.iac.webshop.domain.*;
import hu.iac.webshop.services.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderProductController.class)
@DisplayName("OrderProduct Controller")
@Tag("Controller")
public class TestOrderProduct {

    @Autowired
    private MockMvc mvc;

    /* Mocking the services used by the controller */
    @MockBean
    private OrderProductService orderProductService;
    @MockBean
    private OrderService orderService;
    @MockBean
    private ProductService productService;

    /* Setting the request parameters */
    private final String PRODUCT_URL = "/orderproducts";
    private final String POST_REQ_BODY = "{\"productId\": 1,\"orderId\": 1,\"amount\": 2}";

    /* Creating objects to test the request with */
    private final Customer customer = new Customer("Test", "06-12345678", "Test@Test.nl");
    private final Order order = new Order(new Date(), 0, customer);
    private final Product product = new Product("Komkommer", 10.0, 50);

    @Test
    @DisplayName("add product to order")
    public void shouldAddProductToOrder() throws Exception {
        Optional<Order> optionalOrder = Optional.of(order);
        given(orderService.find(1L)).willReturn(optionalOrder);

        Optional<Product> optionalProduct = Optional.of(product);
        given(productService.find(1L)).willReturn(optionalProduct);

        mvc.perform(post(PRODUCT_URL).contentType(MediaType.APPLICATION_JSON).content(POST_REQ_BODY)
            .characterEncoding("utf-8")).andExpect(status().isOk());
    }
}
