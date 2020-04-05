package hu.iac.webshop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.iac.webshop.domain.Category;
import hu.iac.webshop.services.CategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import hu.iac.webshop.auth.JwtAuthenticationProvider;
import hu.iac.webshop.config.JwtSecurityConfig;
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
@DisplayName("Product Controller")
@Tag("Controller")
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;
    @MockBean
    private DiscountService discountService;
    @MockBean
    private JwtSecurityConfig jwtSecurityConfig;
    @MockBean
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    private final String PRODUCT_URL = "/authed/products";
    private final String POST_REQ_BODY = "{\"name\": \"Komkommer\",\"price\": 10.0,\"stock\": 50, \"discountIds\": []}";
    private final Product testProduct1 = new Product("Komkommer", 10.0, 50, "Dit is een komkommer");
    private final Product testProduct2 = new Product("Desktop", 1250.95, 120, "Dit is een Desktop");
    private final Product testProduct3 = new Product("Mobiel", 800.0, 420, "Dit is een Mobiel" );

    private final String TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUZXN0IiwiaWQiOjEsInJvbGUiOiJhZG1pbiIsImlhdCI6MTU4NjAyNjgyMiwiZXhwIjoxNTg2MDQ0ODIyfQ.jUsssDwD3agJriXKkB5nn6yVu6uC60s6qOzxf1e7vPM";
    @Test
    @DisplayName("Get products")
    public void shouldFetchProduct() throws Exception {
        given(productService.list()).willReturn(Arrays.asList(testProduct1, testProduct2, testProduct3));
        mvc.perform(get(PRODUCT_URL)
            .header("Authorization", TOKEN)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name", is(testProduct1.getName())))
            .andExpect(jsonPath("$[0].price", is(testProduct1.getOriginalPrice())))
            .andExpect(jsonPath("$[0].stock", is(testProduct1.getStock())));
    }

    @Test
    @DisplayName("Get one product")
    void shouldFetchSingleProduct() throws Exception {
        Optional<Product> optProduct = Optional.of(testProduct1);
        given(productService.find(1L)).willReturn(optProduct);

        mvc.perform(get(PRODUCT_URL + "/1")
            .header("Authorization", TOKEN)
            .content(objectMapper.writer().writeValueAsString(POST_REQ_BODY))
            .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(testProduct1.getName())))
            .andExpect(jsonPath("$.price", is(testProduct1.getOriginalPrice())))
             .andExpect(jsonPath("$.stock", is(testProduct1.getStock())));
    }

    @Test
    @DisplayName("Create product")
    public void shouldCreateProduct() throws Exception {
        mvc.perform(post(PRODUCT_URL).header("Authorization", TOKEN).contentType(MediaType.APPLICATION_JSON).content(POST_REQ_BODY).characterEncoding("utf-8"))
            .andExpect(status().isOk());
    }
}
