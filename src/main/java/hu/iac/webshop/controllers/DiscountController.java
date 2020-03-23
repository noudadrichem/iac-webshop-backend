package hu.iac.webshop.controllers;

import hu.iac.webshop.domain.Discount;
import hu.iac.webshop.dto.product.DiscountRequest;
import hu.iac.webshop.services.DiscountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/discount/current")
    public List<Discount> getCurrentDiscounts() {
        List<Discount> discounts = this.discountService.findAllCurrent();
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

    @DeleteMapping("/discount/delete/{id}")
    public void deleteDiscount(@PathVariable long id) {
        this.discountService.deleteDiscount(id);
    }

    @PutMapping("/discount/update/{id}")
    public ResponseEntity<Discount> updateDiscount(@RequestBody DiscountRequest discountRequest, @PathVariable Long id) {
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
