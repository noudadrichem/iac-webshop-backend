package hu.iac.webshop.checkout;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import hu.iac.webshop.controllers.CheckoutController;
import hu.iac.webshop.controllers.OrderController;
import hu.iac.webshop.domain.Customer;
import hu.iac.webshop.domain.Order;
import hu.iac.webshop.domain.Product;
import hu.iac.webshop.dto.product.AddressRequest;
import hu.iac.webshop.dto.product.CheckoutRequest;
import hu.iac.webshop.repositories.OrderRepository;
import hu.iac.webshop.services.OrderService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;

// @RunWith
@SpringBootTest
@AutoConfigureMockMvc // need this in Spring Boot test
public class TestCheckout {

    @Autowired
    private MockMvc mockMvc;
    // @MockBean
    // private Checkout mockRepository;
    // @MockBean
    // private OrderService mockService;


    @Test
    @DisplayName("Checkout response")
    public void testPostParameters() throws Exception {
        Long mockId = Long.valueOf(1);
        CheckoutRequest mockReq = new CheckoutRequest();
        mockReq.setCustomerId(mockId);
        mockReq.setOrderId(mockId);
        mockReq.setAddressRequest(new AddressRequest(
            "Vosmaerstraat 91",
            "Delft",
            "Zuid-Holland",
            "2222DB",
            "The Netherlands"
        ));
        mockReq.setPaymentMethod("IDEAL");

        String mockRequestJSONString = "{\"paymentMethod\": \"IDEAL\",\"customerId\": 1,\"addressRequest\": {\"street\": \"Vosmaerstraat 91\",\"city\": \"Delft\",\"state\": \"Zuid-Holland\",\"postalCode\": \"2222DB\",\"country\": \"The Netherlands\"},\"orderId\": 1}";
        // Mockito.when(mockService.create(order)).thenReturn(order);

        RequestBuilder reqBUilder = MockMvcRequestBuilders.post("/checkout")
            .accept(MediaType.APPLICATION_JSON)
            .content(mockRequestJSONString)
            .contentType(MediaType.APPLICATION_JSON);


        MvcResult result = mockMvc.perform(reqBUilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals("http://localhost:9091/checkout", response.getHeader(HttpHeaders.LOCATION));
        // try {

        //     System.out.println(result.getResponse());
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

    // String expectedStr = "{}";
    // JSONAssert.assertEquals(expectedStr, actualStr, strict);

    }

}
