package hu.iac.webshop.controllers;

import hu.iac.webshop.domain.Discount;
import hu.iac.webshop.domain.Product;
import hu.iac.webshop.dto.product.DiscountRequest;
import hu.iac.webshop.services.DiscountService;
import hu.iac.webshop.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DiscountController {
    private final DiscountService discountService;
    private final ProductService productService;

    public DiscountController(DiscountService discountService, ProductService productService) {
        this.discountService = discountService;
        this.productService = productService;
    }

    @GetMapping("/discounts")
    public List<Discount> getDiscounts() {
        List<Discount> discounts = this.discountService.list();
        return discounts;
    }

    @GetMapping("/discounts/{id}")
    public ResponseEntity getDiscount(@PathVariable Long id){
        Optional<Discount> optionalDiscount = this.discountService.find(id);

        if (optionalDiscount.isEmpty()) {
            return new ResponseEntity<>("Discount bestaat niet", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalDiscount.get(), HttpStatus.OK);
    }

    @GetMapping("/discounts/current")
    public List<Discount> getCurrentDiscounts() {
        List<Discount> discounts = this.discountService.findAllCurrent();
        return discounts;
    }

    @PostMapping("/discounts")
    public ResponseEntity addDiscount(@Valid @RequestBody DiscountRequest discountRequest) {
        List<Product> productList = new ArrayList<>();
        for (String productId : discountRequest.getProductIdList()) {
            Long longProductId = Long.parseLong(productId);
            Optional<Product> optionalProduct = productService.find(longProductId);
            if (optionalProduct.isEmpty()){
                return new ResponseEntity<>("Invalide productid: "+ productId, HttpStatus.NOT_FOUND);
            } else {
                Product product = optionalProduct.get();
//                return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
                productList.add(product);
            }
        }
        if (productList.isEmpty()) {
            return new ResponseEntity<>("empty", HttpStatus.NOT_FOUND);
        }

        Discount newDiscount = new Discount(
            discountRequest.getStartDate(),
            discountRequest.getEndDate(),
            discountRequest.getDiscountedPrice(),
            discountRequest.getAdText(),
            productList
        );

//        return new ResponseEntity<>(productList, HttpStatus.NOT_FOUND );

        return new ResponseEntity<>(this.discountService.createDiscount(newDiscount), HttpStatus.OK);
    }

    @DeleteMapping("/discounts/{id}")
    public ResponseEntity<Long> deleteDiscount(@PathVariable long id) {
        boolean isRemoved = this.discountService.deleteDiscount(id);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("/discounts/{id}")
    public ResponseEntity<Discount> updateDiscount(@Valid @RequestBody DiscountRequest discountRequest, @PathVariable Long id) {
        Optional<Discount> discountOptional = this.discountService.findById(id);

        if (discountOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Discount discount = discountOptional.get();
        discount.setStartDate(discountRequest.getStartDate());
        discount.setEndDate(discountRequest.getEndDate());
        discount.setDiscountedPrice(discountRequest.getDiscountedPrice());
        discount.setAdText(discountRequest.getAdText());

        return new ResponseEntity<Discount>(this.discountService.updateDiscount(discount), HttpStatus.OK);
    }


}
