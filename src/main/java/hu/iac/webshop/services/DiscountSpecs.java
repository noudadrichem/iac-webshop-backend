package hu.iac.webshop.services;

import hu.iac.webshop.domain.Discount;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class DiscountSpecs {

    public static Specification<Discount> isGreaterThan() {
        return new Specification<Discount>() {
            @Override
            public Predicate toPredicate(Root<Discount> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Date currentDate = new Date();
                return criteriaBuilder.greaterThanOrEqualTo(root.get("endDate"), currentDate);
            }
        };
    }

    public static Specification<Discount> isLesserThan() {
        return new Specification<Discount>() {
            @Override
            public Predicate toPredicate(Root<Discount> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Date currentDate = new Date();
                return criteriaBuilder.lessThanOrEqualTo(root.get("startDate"), currentDate);
            }
        };
    }
}
