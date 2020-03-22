package hu.iac.webshop.services;

import hu.iac.webshop.domain.Discount;
import hu.iac.webshop.repositories.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import static hu.iac.webshop.services.DiscountSpecs.isGreaterThan;
import static hu.iac.webshop.services.DiscountSpecs.isLesserThan;


@Service
public class DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    public List<Discount> findAllCurrent() {return discountRepository.findAll(isGreaterThan().and(isLesserThan()));}

    public List<Discount> list() {return discountRepository.findAll();}

    public Discount createDiscount(Discount discount) {
        return discountRepository.save(discount);
    }

    public Discount updateDiscount(Discount newDiscount) {
        return discountRepository.save(newDiscount);

    }

    public void deleteDiscount(Discount discount) {
        discountRepository.delete(discount);
    }
}
