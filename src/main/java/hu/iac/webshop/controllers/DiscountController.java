package hu.iac.webshop.controllers;

import hu.iac.webshop.domain.Discount;
import hu.iac.webshop.dto.product.DiscountRequest;
import hu.iac.webshop.services.DiscountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiscountController {
    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("/discount")
    public List<Discount> getDiscounts() {
        List<Discount> discounts = this.discountService.list();
        return discounts;
    }

    @PostMapping("/discount/new")
    public Discount addDiscount(@RequestBody DiscountRequest discountRequest) {
        Discount newDiscount = new Discount(
                discountRequest.getStartDate(),
                discountRequest.getEndDate(),
                discountRequest.getDiscountedPrice(),
                discountRequest.getAdText()
        );

        return this.discountService.createDiscount(newDiscount);
    }

    @DeleteMapping("/discount/delete")
    public void deleteDiscount(@RequestBody DiscountRequest discountRequest) {
        Discount currentDiscount = new Discount(
                discountRequest.getStartDate(),
                discountRequest.getEndDate(),
                discountRequest.getDiscountedPrice(),
                discountRequest.getAdText()
        );

        this.discountService.deleteDiscount(currentDiscount);
    }

    @PutMapping("/discount/update")
    public void updateDiscount(@RequestBody DiscountRequest discountRequest) {
        Discount currentDiscount = new Discount(
                discountRequest.getEndDate(),
                discountRequest.getStartDate(),
                discountRequest.getDiscountedPrice(),
                discountRequest.getAdText()
        );
    }


}
