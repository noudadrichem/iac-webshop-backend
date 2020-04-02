package hu.iac.webshop.order;

// import com.fasterxml.jackson.databind.ObjectMapper;
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

import hu.iac.webshop.controllers.OrderController;
// import hu.iac.webshop.controllers.ProductController;
import hu.iac.webshop.domain.Customer;
import hu.iac.webshop.domain.Order;
import hu.iac.webshop.services.CustomerService;
// import hu.iac.webshop.domain.Product;
import hu.iac.webshop.services.DiscountService;
import hu.iac.webshop.services.OrderService;
// import hu.iac.webshop.services.ProductService;
import hu.iac.webshop.services.ProductService;

import java.util.Arrays;
import java.util.Date;
// import java.util.Optional;

// import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
@DisplayName("Order Controller")
@Tag("Controller")
class TestOrder {

    @Autowired
    private MockMvc mvc;

    // @Autowired
    // private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;
    @MockBean
    private DiscountService discountService;
    @MockBean
    private CustomerService customerService;
    @MockBean
    private ProductService productService;

    private final String PRODUCT_URL = "/orders";
    // private final String POST_REQ_BODY = "{\"date\": \"2020-03-23\",\"totalPrice\": 44.5,\"customerId\": 1}";

    private final Customer customer = new Customer("Test", "06-12345678", "Test@Test.nl");
    private final Order order = new Order(new Date(), 50.55, customer);

    @Test
    @DisplayName("Fetching orders")
    public void shouldFetchOrder() throws Exception {
        given(orderService.list()).willReturn(Arrays.asList(order, order));

        mvc.perform(get(PRODUCT_URL).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
                // .andExpect(jsonPath("$[0].name", is(order.getName())))
    }

    // @Test
    // @DisplayName("Get one product")
    // void shouldReturnUserObject_whenGetUser() throws Exception {
    //     Optional<Product> optProduct = Optional.of(testProduct1);
    //     given(productService.find(1L)).willReturn(optProduct);

    //     mvc.perform(get(PRODUCT_URL + "/1").content(objectMapper.writer().writeValueAsString(POST_REQ_BODY))
    //             .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
    //             .andExpect(jsonPath("$.name", is(testProduct1.getName())))
    //             .andExpect(jsonPath("$.price", is(testProduct1.getPrice())))
    //             .andExpect(jsonPath("$.stock", is(testProduct1.getStock())));
    // }

    // @Test
    // @DisplayName("Create Product")
    // public void shouldCreateProduct() throws Exception {
    //     mvc.perform(post(PRODUCT_URL).contentType(MediaType.APPLICATION_JSON).content(POST_REQ_BODY)
    //             .characterEncoding("utf-8")).andExpect(status().isOk());
    // }
}
