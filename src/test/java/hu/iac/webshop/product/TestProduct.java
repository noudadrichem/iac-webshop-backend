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
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    private final String PRODUCT_URL = "/products";
    private final String POST_REQ_BODY = "{\"name\": \"Komkommer\",\"price\": 10.0,\"stock\": 50, \"discountIds\": []}";

    private final Product testProduct1 = new Product("Komkommer", 10.0, 50);
    private final Product testProduct2 = new Product("Desktop", 1250.95, 120);
    private final Product testProduct3 = new Product("Mobiel", 800.0, 420);


    @Test
    @DisplayName("Get products")
    public void shouldFetchProduct() throws Exception {
        given(productService.list()).willReturn(Arrays.asList(testProduct1, testProduct2, testProduct3));
        mvc.perform(get(PRODUCT_URL)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name", is(testProduct1.getName())))
            .andExpect(jsonPath("$[0].price", is(testProduct1.getPrice())))
            .andExpect(jsonPath("$[0].stock", is(testProduct1.getStock())));
    }

    @Test
    @DisplayName("Get one product")
    void shouldReturnUserObject_whenGetUser() throws Exception {
        Optional<Product> optProduct = Optional.of(testProduct1);
        given(productService.find(1L)).willReturn(optProduct);

        mvc.perform(get(PRODUCT_URL + "/1").content(objectMapper.writer().writeValueAsString(POST_REQ_BODY))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(testProduct1.getName())))
                .andExpect(jsonPath("$.price", is(testProduct1.getPrice())))
                .andExpect(jsonPath("$.stock", is(testProduct1.getStock())));
    }

    @Test
    @DisplayName("Create Product")
    public void shouldCreateProduct() throws Exception {
        mvc.perform(post(PRODUCT_URL).contentType(MediaType.APPLICATION_JSON).content(POST_REQ_BODY).characterEncoding("utf-8"))
            .andExpect(status().isOk());
            // .andExpect(jsonPath("$.name", is(testProduct1.getName())))
            // .andExpect(jsonPath("$.price", is(testProduct1.getPrice())))
            // .andExpect(jsonPath("$.stock", is(testProduct1.getStock())));
            // .andReturn();
    }
}
