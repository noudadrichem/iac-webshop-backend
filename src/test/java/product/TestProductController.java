package product;

import org.junit.jupiter.api.Test;

import hu.iac.webshop.controllers.ProductController;
import hu.iac.webshop.domain.Product;
import hu.iac.webshop.dto.product.ProductRequest;
import hu.iac.webshop.services.ProductService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestProductController {

    @Test
    public void testCreate() {
        ProductController productController = new ProductController(new ProductService());
        String NAME = "Test Product";
        double PRICE = 10.50;

        ProductRequest mimicRequest = new ProductRequest();
        mimicRequest.setName(NAME);
        mimicRequest.setPrice(PRICE);

        Product resultProduct = productController.addProducten(mimicRequest);

        Product shouldBeProduct =  new Product();
        shouldBeProduct.setName(NAME);
        shouldBeProduct.setPrice(PRICE);

        assertEquals(shouldBeProduct, resultProduct);
    }

}
