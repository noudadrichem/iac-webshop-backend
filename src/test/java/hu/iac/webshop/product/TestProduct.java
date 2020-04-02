package hu.iac.webshop.product;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import hu.iac.webshop.controllers.ProductController;
import hu.iac.webshop.domain.Product;
import hu.iac.webshop.services.DiscountService;
import hu.iac.webshop.services.ProductService;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
@DisplayName("User Controller")
@Tag("Controller")
class TestProduct {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;
    @MockBean
    private DiscountService discountService;

    private final Product testProduct1 = new Product("Komkommer", 10.0, 50);
    private final Product testProduct2 = new Product("Desktop", 1250.95, 120);
    private final Product testProduct3 = new Product("Mobiel", 800.0, 420);

    private final String POST_REQ_BODY = "{\"name\": \"Komkommer\",\"price\": 10.0,\"stock\": 50, \"discountIds\": []}";

    @Test
    @DisplayName("Get products")
    public void shouldFetchProduct() throws Exception {
        given(productService.list()).willReturn(Arrays.asList(testProduct1, testProduct2, testProduct3));

        mvc.perform(get("/products")
            .content(objectMapper.writer().writeValueAsString(POST_REQ_BODY))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name", is(testProduct1.getName())))
            .andExpect(jsonPath("$[0].price", is(testProduct1.getPrice())))
            .andExpect(jsonPath("$[0].stock", is(testProduct1.getStock())));
    }


    // @Test
    // @DisplayName("Delete address")
    // void shouldDeleteUser_whenDeleteUser() throws Exception {
    // mvc.perform(delete("/addresses/1")
    // .contentType(MediaType.APPLICATION_JSON))
    // .andExpect(status().isOk());
    //
    // verify(addressService, atLeastOnce()).delete(1L);
    // }

}
