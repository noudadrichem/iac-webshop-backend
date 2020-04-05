package hu.iac.webshop.controllers;

import hu.iac.webshop.auth.JwtAuthenticationProvider;
import hu.iac.webshop.config.JwtSecurityConfig;
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
@WebMvcTest(CheckoutController.class)
@DisplayName("Checkout Controller")
@Tag("Controller")
public class CheckoutControllerTest {
    @Autowired
    private MockMvc mvc;

    /* Mocking the services used by the controller */
    @MockBean
    private OrderService orderService;
    @MockBean
    private CustomerService customerService;
    @MockBean
    private AddressService addressService;
    @MockBean
    private ProductService productService;
    @MockBean
    private JwtSecurityConfig jwtSecurityConfig;
    @MockBean
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    /* Setting the request parameters */
    private final String CHECKOUT_URL = "/authed/checkout";
    private final String POST_REQ_BODY = "{\"paymentMethod\": \"IDEAL\",\"customerId\": 1,\"addressRequest\": {\"street\": \"Vosmaerstraat 91\",\"city\": \"Delft\",\"state\": \"Zuid-Holland\",\"postalCode\": \"2222DB\",\"country\": \"The Netherlands\"},\"orderId\": 1}";

    /* Creating objects to test the request with */
    private final Customer customer = new Customer("Test", "06-12345678", "Test@Test.nl");
    private final Address address = new Address("Vosmaerstraat 91", "Delft", "Zuid-Holland", "2222DB", "The Netherlands", customer);
    private final Order order = new Order(new Date(), customer);
    private final Product testProduct1 = new Product("Komkommer", 10.0, 50, "Dit is een komkommer");
    private final Product testProduct2 = new Product("Desktop", 1250.95, 120, "Dit is een Desktop");
    private final Product testProduct3 = new Product("Mobiel", 800.0, 420, "Dit is een Mobiel");
    private final OrderProduct orderProduct1 = new OrderProduct(order, testProduct1, 3);
    private final OrderProduct orderProduct2 = new OrderProduct(order, testProduct2, 119);
    private final OrderProduct orderProduct3 = new OrderProduct(order, testProduct3, 420);

    @Test
    @DisplayName("Should checkout")
    public void shouldCheckout() throws Exception {
        order.addProduct(orderProduct1);
        order.addProduct(orderProduct2);
        order.addProduct(orderProduct3);

        Optional<Customer> optionalCustomer = Optional.of(customer);
        given(customerService.find(1L)).willReturn(optionalCustomer);

        Optional<Address> optionalAddress = Optional.of(address);
        given(addressService.find(1L)).willReturn(optionalAddress);

        Optional<Order> optionalOrder = Optional.of(order);
        given(orderService.find(1L)).willReturn(optionalOrder);

        mvc.perform(post(CHECKOUT_URL).contentType(MediaType.APPLICATION_JSON).content(POST_REQ_BODY)
            .characterEncoding("utf-8")
            .header("Moetje", "Test")
            .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUZXN0IiwiaWQiOjEsInJvbGUiOiJhZG1pbiIsImlhdCI6MTU4NjAyNTYzNCwiZXhwIjoxNTg2MDQzNjM0fQ.xspHGVdEtnRc7jNbahvrkaNdrxkMQZjQx_Dlgu2_FS0")
            ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should fail checkout on empty order")
    public void shouldFailOnEmptyOrder() throws Exception {
        Optional<Customer> optionalCustomer = Optional.of(customer);
        given(customerService.find(1L)).willReturn(optionalCustomer);

        Optional<Address> optionalAddress = Optional.of(address);
        given(addressService.find(1L)).willReturn(optionalAddress);

        Optional<Order> optionalOrder = Optional.of(order);
        given(orderService.find(1L)).willReturn(optionalOrder);

        mvc.perform(post(CHECKOUT_URL).contentType(MediaType.APPLICATION_JSON).content(POST_REQ_BODY)
            .characterEncoding("utf-8")).andExpect(status().isNotAcceptable());
    }

    @Test
    @DisplayName("Should fail checkout on already processed order")
    public void shouldFailOnProcessedOrder() throws Exception {
        order.addProduct(orderProduct1);
        order.addProduct(orderProduct2);
        order.addProduct(orderProduct3);
        order.setCheckedOutTrue();

        Optional<Customer> optionalCustomer = Optional.of(customer);
        given(customerService.find(1L)).willReturn(optionalCustomer);

        Optional<Address> optionalAddress = Optional.of(address);
        given(addressService.find(1L)).willReturn(optionalAddress);

        Optional<Order> optionalOrder = Optional.of(order);
        given(orderService.find(1L)).willReturn(optionalOrder);

        mvc.perform(post(CHECKOUT_URL).contentType(MediaType.APPLICATION_JSON).content(POST_REQ_BODY)
            .characterEncoding("utf-8")).andExpect(status().isNotAcceptable());
    }

    @Test
    @DisplayName("Should fail checkout because stock too low")
    public void shouldFailOnTooLowStock() throws Exception {
        order.addProduct(orderProduct3);
        order.addProduct(orderProduct3);

        Optional<Customer> optionalCustomer = Optional.of(customer);
        given(customerService.find(1L)).willReturn(optionalCustomer);

        Optional<Address> optionalAddress = Optional.of(address);
        given(addressService.find(1L)).willReturn(optionalAddress);

        Optional<Order> optionalOrder = Optional.of(order);
        given(orderService.find(1L)).willReturn(optionalOrder);

        mvc.perform(post(CHECKOUT_URL).contentType(MediaType.APPLICATION_JSON).content(POST_REQ_BODY)
            .characterEncoding("utf-8")).andExpect(status().isNotAcceptable());
    }

    @Test
    @DisplayName("Should fail checkout on wrong payment")
    public void shouldFailOnWrongPayment() throws Exception {
        String paymentChanged = "{\"paymentMethod\": \"Natura\",\"customerId\": 1,\"addressRequest\": {\"street\": \"Vosmaerstraat 91\",\"city\": \"Delft\",\"state\": \"Zuid-Holland\",\"postalCode\": \"2222DB\",\"country\": \"The Netherlands\"},\"orderId\": 1}";

        order.addProduct(orderProduct1);
        order.addProduct(orderProduct2);
        order.addProduct(orderProduct3);

        Optional<Customer> optionalCustomer = Optional.of(customer);
        given(customerService.find(1L)).willReturn(optionalCustomer);

        Optional<Address> optionalAddress = Optional.of(address);
        given(addressService.find(1L)).willReturn(optionalAddress);

        Optional<Order> optionalOrder = Optional.of(order);
        given(orderService.find(1L)).willReturn(optionalOrder);

        mvc.perform(post(CHECKOUT_URL).contentType(MediaType.APPLICATION_JSON).content(paymentChanged)
            .characterEncoding("utf-8")).andExpect(status().isNotAcceptable());
    }

    @Test
    @DisplayName("Should fail checkout on customer")
    public void shouldFailOnCustomer() throws Exception {
        order.addProduct(orderProduct1);
        order.addProduct(orderProduct2);
        order.addProduct(orderProduct3);

        Optional<Customer> optionalCustomer = Optional.of(customer);
        given(customerService.find(2L)).willReturn(optionalCustomer);

        Optional<Address> optionalAddress = Optional.of(address);
        given(addressService.find(1L)).willReturn(optionalAddress);

        Optional<Order> optionalOrder = Optional.of(order);
        given(orderService.find(1L)).willReturn(optionalOrder);

        mvc.perform(post(CHECKOUT_URL).contentType(MediaType.APPLICATION_JSON).content(POST_REQ_BODY)
            .characterEncoding("utf-8")).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should fail checkout on address")
    public void shouldFailOnAddress() throws Exception {
        order.addProduct(orderProduct1);
        order.addProduct(orderProduct2);
        order.addProduct(orderProduct3);

        Optional<Customer> optionalCustomer = Optional.of(customer);
        given(customerService.find(1L)).willReturn(optionalCustomer);

        Optional<Address> optionalAddress = Optional.of(address);
        given(addressService.find(2L)).willReturn(optionalAddress);

        Optional<Order> optionalOrder = Optional.of(order);
        given(orderService.find(1L)).willReturn(optionalOrder);

        mvc.perform(post(CHECKOUT_URL).contentType(MediaType.APPLICATION_JSON).content(POST_REQ_BODY)
            .characterEncoding("utf-8")).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should fail checkout on order")
    public void shouldFailOnOrder() throws Exception {
        order.addProduct(orderProduct1);
        order.addProduct(orderProduct2);
        order.addProduct(orderProduct3);

        Optional<Customer> optionalCustomer = Optional.of(customer);
        given(customerService.find(1L)).willReturn(optionalCustomer);

        Optional<Address> optionalAddress = Optional.of(address);
        given(addressService.find(1L)).willReturn(optionalAddress);

        Optional<Order> optionalOrder = Optional.of(order);
        given(orderService.find(2L)).willReturn(optionalOrder);

        mvc.perform(post(CHECKOUT_URL).contentType(MediaType.APPLICATION_JSON).content(POST_REQ_BODY)
            .characterEncoding("utf-8")).andExpect(status().isNotFound());
    }
}
